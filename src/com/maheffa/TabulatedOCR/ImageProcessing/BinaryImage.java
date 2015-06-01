package com.maheffa.TabulatedOCR.ImageProcessing;
// File:    com.maheffa.TabulatedOCR.ImageProcessing.BinaryImage.java
// Created: 19/02/2015

import org.apache.commons.math3.fraction.Fraction;

import java.awt.image.BufferedImage;
import java.util.Arrays;

/**
 * @author mahefa
 */
public class BinaryImage {

    public static final int BLACK = 0x00;
    public static final int WHITE = 0xFF;
    public static final int BLUE = 0x01;
    public static final int GREEN = 0x02;
    public static final int RED = 0x03;

    private int height, width;
    private int[] data;
    private BufferedImage img;

    public BinaryImage(String filePath) {
        this(ImgProcUtil.readImage(filePath));
    }

    public BinaryImage(BufferedImage img) {
        this.img = img;
        height = img.getHeight();
        width = img.getWidth();
        data = new int[height * width];
        data = new int[height * width];
    }

    public BinaryImage (BufferedImage img, int threshold) {
        height = img.getHeight();
        width = img.getWidth();
        data = new int[width * height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                data[j * width + i] = grayValue(img.getRGB(i, j), 1) > threshold ? BinaryImage.WHITE : BinaryImage.BLACK;
            }
        }
    }

    public BinaryImage(int height, int width) {
        this.height = height;
        this.width = width;
        data = new int[height * width];
        Arrays.fill(data, WHITE);
    }

    public void convertToGrayScale(int method) {
        System.out.println("Converting image into grayscale using method " + method);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int rgb = img.getRGB(j, i);
                data[i * width + j] = convertToGrayscale(
                        (rgb >> 16) & 0xFF,
                        (rgb >> 8) & 0xFF,
                        rgb & 0xFF,
                        1
                );
            }
        }
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

    private int grayValue(int argb, int method) {
        return convertToGrayscale((argb >> 16) & 0xFF, (argb >> 8) & 0xFF, argb & 0xFF, method) & 0xFF;
    }

    public void localBinarize() {
        int[] res = new int[data.length];
        System.out.println("Data length " + data.length);
        ImageProcessor imgProc = new ImageProcessor();
        imgProc.process(data, res, this.height, this.width, 11, new BinarizationKMean());
        data = res;
    }

    public void binarize(boolean useClustering) {
        System.out.println("Starting binarization ...");
        if (useClustering) {
            KMeanCluster dataSet = new KMeanCluster();
            KMeanCluster[] clusters;
            KMeanCluster background, foreground;
            int maxIterations = 1000;
            int maxPixelToAnalyze = 2000000;
            boolean reducePixel = false;
            int[] binarizedHistogram = new int[256];

            // adding the pixel to the set
            int[] dataToAnalyze;
            if (data.length > maxPixelToAnalyze && reducePixel) {
                Fraction fraction = new Fraction(maxPixelToAnalyze, data.length);
                int num = fraction.getNumerator(), den = fraction.getDenominator();
                int i = 0, j = 0;
                dataToAnalyze = new int[maxPixelToAnalyze];
                while (i < data.length) {
                    int k0 = 0, k1 = 0;
                    while (k0 < num && i < data.length && j < maxPixelToAnalyze) {
                        dataToAnalyze[j++] = data[i++];
                        k0++;
                    }
                    while (k1 < den && i < data.length) {
                        i++;
                        k1++;
                    }
                }
            } else {
                dataToAnalyze = Arrays.copyOf(data, data.length);
            }
            for (int pixelColor : dataToAnalyze) {
                dataSet.add(pixelColor);
            }
//        System.out.println("Set size = " + dataSet.size());

            // clustering into two sets till no changes or iteration less than maxIterations
            clusters = dataSet.cluster(2, maxIterations);

            // deciding which one is the fore and background
            if (clusters[0].getMean() < clusters[1].getMean()) {
                foreground = clusters[0];
                background = clusters[1];
            } else {
                background = clusters[0];
                foreground = clusters[1];
            }

            // revealing clusters containement, foregrounds to 1, backgrounds to -1
            for (int val : foreground.getData()) {
                binarizedHistogram[val] = 1;
            }
            for (int val : background.getData()) {
                binarizedHistogram[val] = -1;
            }

            // binarizing
            for (int i = 0; i < data.length; i++) {
                // if foregrounds then black, otherwise white
                data[i] = binarizedHistogram[data[i]] > 0 ? BinaryImage.BLACK : BinaryImage.WHITE;
            }
        } else {
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {
                    data[j * width + i] = grayValue(img.getRGB(i, j), 1) > 128 ? BinaryImage.WHITE : BinaryImage.BLACK;
                }
            }
        }
        System.out.println("Done with binarization ...");
    }

    public BufferedImage rasterize() {
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
        img.getRaster().setPixels(0, 0, width, height, data);
        return img;
    }

    public BufferedImage rasterizeWithColor() {
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int color;
                switch (data[j * width + i]) {
                    case BinaryImage.WHITE : color = 0xFFFFFF; break;
                    case BinaryImage.BLACK : color = 0x0; break;
                    case BinaryImage.BLUE : color = 0x0000FF; break;
                    case BinaryImage.GREEN : color = 0x00FF00; break;
                    case BinaryImage.RED : color = 0xFF0000; break;
                    default : color = 0xFFFFFF; break;
                }
                img.setRGB(i, j, color);
            }
        }
        return img;
    }

    public void fillRect(int x, int y, int width, int height, int color) {
        for (int i = x; i < x + width; i++) {
            for (int j = y; j < y + height; j++) {
                this.setPixel(j, i, color);
            }
        }
    }

    public void setPixel(int i, int j, int val) {
        if (i < 0 || i >= height || j < 0 || j >= width) {
            System.err.println("Setting index ("+i+","+j+") out of bound");
            for(StackTraceElement e : Thread.currentThread().getStackTrace()) {
                System.err.println(e.toString());
            }
            System.exit(-1);
        } else {
            data[i * width + j] = val;
        }
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int[] getData() {
        return this.data;
    }

    public int getPixel(int i, int j) {
        if (i < 0 || i >= height || j < 0 || j >= width) {
            System.err.println("Getting index ("+i+","+j+") out of bound");
            for(StackTraceElement e : Thread.currentThread().getStackTrace()) {
                System.err.println(e.toString());
            }
            System.exit(-1);
            return 0;
        } else {
            return data[i * width + j];
        }
    }

}

class BinarizationKMean implements ProcessorFunction {

    @Override
    public int processPoint(int index, int[] src, int height, int width, int area) {
        KMeanCluster inData = new KMeanCluster();
        KMeanCluster[] result;
        int i0 = index / width;
        int j0 = index % width;
        for (int i = i0 - area; i < i0 + area; i++) {
            if (i < 0 || i >= height) continue;
            for (int j = j0 - area; j < j0 + area; j++) {
                if (j < 0 || j >= width) continue;
                inData.add(src[i * width + j]);
            }
        }
        result = inData.cluster(2, 50);
        KMeanCluster a = result[0];
        KMeanCluster b = result[1];
        if (a.getMean() > b.getMean()) {
            KMeanCluster c = a;
            a = b;
            b = c;
        }
        return Math.abs(src[index] - a.getMean()) < Math.abs(src[index] - b.getMean())
                ? BinaryImage.BLACK
                : BinaryImage.WHITE;
    }
}

