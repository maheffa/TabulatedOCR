// File:    BinaryImage.java
// Created: 19/02/2015

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

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
        int[] inData = new int[height * width];
        long s = System.currentTimeMillis();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                setPixel(i, j, convertToGrayscale(img.getRGB(j, i), 1), preData);
            }
        }
        System.out.println("prevtime : " + (System.currentTimeMillis() - s));
        long t = System.currentTimeMillis();
        byte[] raw = ((DataBufferByte) img.getRaster().getDataBuffer()).getData();
        for (int i = 0, j = 0; i < inData.length; i++, j+=3) {
            preData[i] = convertToGrayscale(raw[j] & 0xFF, raw[j+1] & 0xFF, raw[j+2] & 0xFF, 1);
        }
//        data = new int[height * width];
//        iproc.process(preData, data, height, width, new NiblackBinarization(-0.2, NEIGHBOR));
        System.out.println("time: " + (System.currentTimeMillis() - t));
        data = preData;
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

    private int convertToGrayscale(int r, int g, int b, int method) {
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

class GrayScaler implements ProcessorFunction {
    private class AverageGrayscale implements ProcessorFunction {
        public int processPoint(int index, int[] src, int height, int width) {
            return (((src[index] >> 16) & 0xFF) + ((src[index] >> 8) & 0xFF) + (src[index] & 0xFF)) / 3;
        }
    }
    private class LuminanceGrayscale implements ProcessorFunction {
        public int processPoint(int index, int[] src, int height, int width) {
            return (int) (0.21 * ((src[index] >> 16) & 0xFF) +
                    0.67 * ((src[index] >> 8) & 0xFF) +
                    0.12 * (src[index] & 0xFF));
        }
    }
    private class DesaturateGrayscale implements ProcessorFunction {
        public int processPoint(int index, int[] src, int height, int width) {
            int r = (src[index] >> 16) & 0xFF, g = (src[index] >> 8) & 0xFF, b = src[index] & 0xFF;
            return (Math.min(r, Math.min(g, b)) + Math.max(r, Math.max(g, b))) / 2;
        }
    }

    public static int AVERAGE = 0, LUMINANCE = 1, DESATURATE = 2;
    private ProcessorFunction pf;

    public GrayScaler(int method) {
        switch (method) {
            case 0 : pf = new AverageGrayscale(); break;
            case 1 : pf = new LuminanceGrayscale(); break;
            case 3 : pf = new DesaturateGrayscale(); break;
            default: pf = new LuminanceGrayscale(); break;
        }
    }

    @Override
    public int processPoint(int index, int[] src, int height, int width) {
        return pf.processPoint(index, src, height, width);
    }
}
