// File:    BinaryImage.java
// Created: 19/02/2015

import java.awt.image.BufferedImage;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

/**
 * @author mahefa
 */
public class BinaryImage {

    class ParallelBinarization extends RecursiveAction {

        private int lo, hi, method;
        private byte[] binary, grayscale;

        public ParallelBinarization() {

        }

        public ParallelBinarization(int lo, int hi, byte[] binary, byte[] grayscale, int method) {
            this.lo = lo;
            this.hi = hi;
            this.binary = binary;
            this.grayscale = grayscale;
            this.method = method;
        }

        @Override
        protected void compute() {
            if (hi - lo > MIN_CHUNK) {
                ParallelBinarization t =
                        new ParallelBinarization(lo, (hi + lo)/2, binary, grayscale, method);
                ParallelBinarization t2 =
                        new ParallelBinarization((hi + lo)/2 + 1, hi, binary, grayscale, method);
                t.fork();
                t2.compute();
            } else {
                if (hi < lo) return;
                switch (method) {
                    case 0: {
                        for (int i = Math.max(0, lo); i < Math.min(binary.length, hi); i++) {
//                            System.out.println(i + " * " + (grayscale[i] & 0xFF));
                            binary[i] = (grayscale[i] & 0xFF) >= niblack(i / width, i % width, grayscale, -0.2)
                                    ? (byte) 0xFF : (byte) 0x00;
//                            binary[i] = 126;
//                            System.out.println("binary => " + binary[i] + " : " + niblack(i / width, i % width, grayscale, 1));
                        }
                    }
                }
            }
        }

        public int niblack(int i0, int j0, byte[] data, double k) {
            int ii = i0 < NEIGHBOR ? NEIGHBOR : i0 >= height - NEIGHBOR ? height - NEIGHBOR - 1 : i0;
            int jj = j0 < NEIGHBOR ? NEIGHBOR : j0 >= width - NEIGHBOR ? width - NEIGHBOR - 1 : j0;
            int m = 0;
            int std = 0;
            int max = 0, min = 255;
            for (int i = ii - NEIGHBOR; i <= ii + NEIGHBOR; i++) {
                for (int j = jj - NEIGHBOR; j <= jj + NEIGHBOR; j++) {
                    m += data[i * width + j] & 0xFF;
                }
            }
            m /= SQUARE_SIZE;
            for (int i = ii - NEIGHBOR; i <= ii + NEIGHBOR; i++) {
                for (int j = jj - NEIGHBOR; j <= jj + NEIGHBOR; j++) {
                    int t = data[i * width + j] & 0xFF;
                    std += (t - m) * (t - m);
                }
            }
            std = (int) Math.sqrt(std / SQUARE_SIZE);
//            System.out.println(max + ", " + min + ", " + m + ", " + std);
            return Math.min(m + (int) (k * std), 0xFF);
        }

        public int bernsen(int i0, int j0, byte[] data, int tres) {
            // TODO: can be optimized
            int ii = i0 < NEIGHBOR ? NEIGHBOR : i0 >= height - NEIGHBOR ? height - NEIGHBOR - 1 : i0;
            int jj = j0 < NEIGHBOR ? NEIGHBOR : j0 >= width - NEIGHBOR ? width - NEIGHBOR - 1 : j0;
            int max = 0, min = 255;
            for (int i = ii - NEIGHBOR; i <= ii + NEIGHBOR; i++) {
                for (int j = jj - NEIGHBOR; j <= jj + NEIGHBOR; j++) {
                    int t = data[i * width + j];
                    if (t > max) {
                        max = t;
                    }
                    if (t < min) {
                        min = t;
                    }
                }
            }
            return max - min < tres ? -1 : (max - min) /2;
        }
    }

    class NiblackBinarization implements  ProcessorFunction {

        private double k;

        public NiblackBinarization(double k) {
            this.k = k;
        }

        @Override
        public byte processPoint(int index, byte[] src, int height, int width) {
            int i0 = index / width, j0 = index % width;
            int ii = i0 < NEIGHBOR ? NEIGHBOR : i0 >= height - NEIGHBOR ? height - NEIGHBOR - 1 : i0;
            int jj = j0 < NEIGHBOR ? NEIGHBOR : j0 >= width - NEIGHBOR ? width - NEIGHBOR - 1 : j0;
            int m = 0;
            int std = 0;
            for (int i = ii - NEIGHBOR; i <= ii + NEIGHBOR; i++) {
                for (int j = jj - NEIGHBOR; j <= jj + NEIGHBOR; j++) {
                    m += data[i * width + j] & 0xFF;
                }
            }
            m /= SQUARE_SIZE;
            for (int i = ii - NEIGHBOR; i <= ii + NEIGHBOR; i++) {
                for (int j = jj - NEIGHBOR; j <= jj + NEIGHBOR; j++) {
                    int t = data[i * width + j] & 0xFF;
                    std += (t - m) * (t - m);
                }
            }
            std = (int) Math.sqrt(std / SQUARE_SIZE);
            return data[i] >= Math.min(m + (int) (k * std), 0xFF) ? (byte) 0xFF : 0x00;
        }
    }

    class BernsenBinarization implements  ProcessorFunction {

        private int treshold;

        public BernsenBinarization(int treshold) {
            this.treshold = treshold;
        }

        @Override
        public byte processPoint(int index, byte[] src, int height, int width) {
            // TODO: can be optimized
            int i0 = index / width, j0 = index % width;
            int ii = i0 < NEIGHBOR ? NEIGHBOR : i0 >= height - NEIGHBOR ? height - NEIGHBOR - 1 : i0;
            int jj = j0 < NEIGHBOR ? NEIGHBOR : j0 >= width - NEIGHBOR ? width - NEIGHBOR - 1 : j0;
            int max = 0, min = 255;
            for (int i = ii - NEIGHBOR; i <= ii + NEIGHBOR; i++) {
                for (int j = jj - NEIGHBOR; j <= jj + NEIGHBOR; j++) {
                    int t = data[i * width + j];
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

    private static int NEIGHBOR = 14;
    private static int SQUARE_SIZE = (2 * NEIGHBOR + 1) * (2 * NEIGHBOR + 1);
    private static int MIN_CHUNK = 100000;
    private int height, width;
    private byte[] data;
    private ImageProcessor iproc;

    public BinaryImage(String filePath) {
        iproc = new ImageProcessor();
        BufferedImage img = ImgProcUtil.readImage(filePath);
        height = img.getHeight();
        width = img.getWidth();
        byte[] preData = new byte[height * width];
        byte[] sdata = new byte[height * width];
        data = new byte[height * width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                setPixel(i, j, convertToGrayscale(img.getRGB(j, i), 1), preData);
            }
        }
        ParallelBinarization pb = new ParallelBinarization();
        long t = System.currentTimeMillis();
        for (int i = 0; i < data.length; i++) {
            sdata[i] = (preData[i] & 0xFF) >= pb.niblack(i / width, i % width, preData, -0.2)
                    ? (byte) 0xFF : (byte) 0x00;
//            int v = pb.bernsen(i / width, i % width, preData, 15);
//            if (v < 0) {
//                data[i] = i > 0 ? data[i-1] : (byte) 0xFF;
//            } else {
//                data[i] = (preData[i] & 0xFF) >= v ? (byte) 0xFF : (byte) 0x00;
//            }
        }
        System.out.println("one: " + (System.currentTimeMillis() - t));
        t = System.currentTimeMillis();
//        ForkJoinPool fjp = new ForkJoinPool();
//        ParallelBinarization pbt = new ParallelBinarization(0, data.length, data, preData, 0);
//        fjp.invoke(pbt);
        iproc.process(preData, data, height, width, new NiblackBinarization(-0.2));
        System.out.println("two: " + (System.currentTimeMillis() - t));

    }

    /**
     *
     * @param pixel input rgb pixel
     * @param method 0 - average, 1 - luminance, 2 - desaturate
     * @return grayscaled pixel
     */
    private byte convertToGrayscale(int pixel, int method) {
        int r = (pixel >> 16) & 0xFF;
        int g = (pixel >> 8) & 0xFF;
        int b = pixel & 0xFF;
        int v = 0;
        switch (method) {
            case 0 : v = (r + g + b) / 3; break;
            case 1 : v = (int) (0.21 * r + 0.67 * g + 0.12 * b); break;
            case 2 : v = (Math.min(Math.min(r, g),b) + Math.max(Math.max(r, g), b)) / 2; break;
            default: v = (int) (0.21 * r + 0.67 * g + 0.12 * b); break;
        }
        return (byte) (v & 0xFF);
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

    private void setPixel(int i, int j, byte pixel, byte[] data) {
        if (i < 0 || i >= height || j < 0 || j >= width) {
            System.err.println("index ("+i+","+j+") out of bound");
        } else {
            data[i * width + j] = pixel;
        }
    }

    public byte[] getData() {
        return this.data;
    }

    public byte getPixel(int i, int j) {
        if (i < 0 || i >= height || j < 0 || j >= width) {
            System.err.println("index ("+i+","+j+") out of bound");
            return 0;
        } else {
            return data[i * width + j];
        }
    }

}
