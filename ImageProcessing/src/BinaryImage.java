// File:    BinaryImage.java
// Created: 19/02/2015

import java.awt.image.BufferedImage;

/**
 * @author mahefa
 */
public class BinaryImage {

    private static int NEIGHBOR = 15;
    private int height, width;
    private int[] data;
    private ImageProcessor iproc;

    public BinaryImage(String filePath) {
        iproc = new ImageProcessor();
        BufferedImage img = ImgProcUtil.readImage(filePath);
        height = img.getHeight();
        width = img.getWidth();
        int[] preData = new int[height * width];
        data = new int[height * width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                setPixel(i, j, convertToGrayscale(img.getRGB(j, i), 1), preData);
            }
        }
        long t = System.currentTimeMillis();
        iproc.process(preData, data, height, width, new NiblackBinarization(-0.2, NEIGHBOR));
        System.out.println("time: " + (System.currentTimeMillis() - t));
    }

    /**
     *
     * @param pixel input rgb pixel
     * @param method 0 - average, 1 - luminance, 2 - desaturate
     * @return grayscaled pixel
     */
    private int convertToGrayscale(int pixel, int method) {
        int r = (pixel >> 16) & 0xFF;
        int g = (pixel >> 8) & 0xFF;
        int b = pixel & 0xFF;
        int v;
        switch (method) {
            case 0 : v = (r + g + b) / 3; break;
            case 1 : v = (int) (0.21 * r + 0.67 * g + 0.12 * b); break;
            case 2 : v = (Math.min(Math.min(r, g),b) + Math.max(Math.max(r, g), b)) / 2; break;
            default: v = (int) (0.21 * r + 0.67 * g + 0.12 * b); break;
        }
        return v;
    }

    public BufferedImage rasterize() {
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int t = getPixel(i, j) & 0xFF;
                img.setRGB(j, i, (t << 16) + (t << 8) + t);
            }
        }
        return img;
    }

    private void setPixel(int i, int j, int pixel, int[] data) {
        if (i < 0 || i >= height || j < 0 || j >= width) {
            System.err.println("index ("+i+","+j+") out of bound");
        } else {
            data[i * width + j] = pixel;
        }
    }

    public int[] getData() {
        return this.data;
    }

    public int getPixel(int i, int j) {
        if (i < 0 || i >= height || j < 0 || j >= width) {
            System.err.println("index ("+i+","+j+") out of bound");
            return 0;
        } else {
            return data[i * width + j];
        }
    }

}

class NiblackBinarization implements  ProcessorFunction {

    private double k;
    private int neighbor;

    public NiblackBinarization(double k, int neighbor) {
        this.k = k;
        this.neighbor = neighbor;
    }

    @Override
    public int processPoint(int index, int[] src, int height, int width) {
        int imin = Math.max(0, index / width - neighbor);
        int imax = Math.min(height, index / width + neighbor + 1);
        int jmin = Math.max(0, index % width - neighbor);
        int jmax = Math.min(width, index % width + neighbor + 1);
        int m = 0;
        int std = 0;
        int n = (jmax - jmin) * (imax - imin);
        for (int i = imin; i < imax; i++) {
            for (int j = jmin; j < jmax; j++) {
                m += src[i * width + j];
            }
        }
        m /= n;
        for (int i = imin; i < imax; i++) {
            for (int j = jmin; j < jmax; j++) {
                int t = src[i * width + j] - m;
                std += t * t;
            }
        }
        std = (int) Math.sqrt(std / n);
        return src[index] >= Math.min(m + (int) (k * std), 0xFF) ? 0xFF : 0x00;
    }
}

class BernsenBinarization implements ProcessorFunction {

    private int treshold, neighbor, squareSize;

    public BernsenBinarization(int treshold, int neighbor) {
        this.treshold = treshold;
        this.neighbor = neighbor;
        this.squareSize = (2 * neighbor + 1) * (2 * neighbor +  1);
    }

    @Override
    public int processPoint(int index, int[] src, int height, int width) {
        // TODO: can be optimized
        int i0 = index / width, j0 = index % width;
        int ii = i0 < neighbor ? neighbor : i0 >= height - neighbor ? height - neighbor - 1 : i0;
        int jj = j0 < neighbor ? neighbor : j0 >= width - neighbor ? width - neighbor - 1 : j0;
        int max = 0, min = 255;
        for (int i = ii - neighbor; i <= ii + neighbor; i++) {
            for (int j = jj - neighbor; j <= jj + neighbor; j++) {
                int t = src[i * width + j] & 0xFF;
                if (t > max) {
                    max = t;
                }
                if (t < min) {
                    min = t;
                }
            }
        }
        return max - min < treshold
                ? (byte) 0xFF
                : ((src[index] & 0xFF) > ((max - min) / 2) ? (byte) 0xFF : 0x00);
    }
}
