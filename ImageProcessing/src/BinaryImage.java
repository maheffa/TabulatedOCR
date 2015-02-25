// File:    BinaryImage.java
// Created: 19/02/2015

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author mahefa
 */
public class BinaryImage {

    class MidData {
        int[] val;
        int height, width;

        public MidData(int[] val, int height, int width) {
            this.val = val;
            this.height = height;
            this.width = width;
        }
    }

    private int height, width;
    private int[] data;
    public ArrayList<MidData> midData = new ArrayList<MidData>();

    public BinaryImage(String filePath) {
        BufferedImage img = ImgProcUtil.readImage(filePath);
        height = img.getHeight();
        width = img.getWidth();
        data = new int[height * width];
        byte[] raw = ((DataBufferByte) img.getRaster().getDataBuffer()).getData();
        if (raw.length == data.length) {
            for (int i = 0; i < data.length; i++) {
                data[i] = raw[i] & 0xFF;
            }
        } else {
            for (int i = 0, j = 0; i < data.length; i++, j += 3) {
                data[i] = convertToGrayscale(raw[j] & 0xFF, raw[j + 1] & 0xFF, raw[j + 2] & 0xFF, 1);
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

    public void binarize() {
        ImageProcessor imageProcessor = new ImageProcessor();
        /*
        Step 1 : resize to workable document size
         */
//        System.out.println("Resizing ...");
//        int maxSize = 500;
//        double scale = 1.0 * maxSize / Math.max(width, height);
//        byte[] nwData = ((DataBufferByte) ImgProcUtil.resize(rasterize(), scale)
//                .getRaster().getDataBuffer()).getData();
//        width = (int) (width * scale);
//        height = (int) (height * scale);
//        assert height * width == nwData.length;
//        data = new int[nwData.length];
//        for (int i = 0; i < nwData.length; i++) {
//            data[i] = nwData[i] & 0xFF;
//        }
//        memorize(data);
        /*
        Step 2 : find mean values
         */
        System.out.println("Finding mean ...");
        int area = 10;
        int[] means = new int[height * width];
        imageProcessor.process(data, means, height, width, area, new MeanFinder());
        memorize(means);
        /*
        Step 3 : calculate variance
         */
        System.out.println("Finding variance ...");
        VarianceFinder.means = means;
        int[] variance = new int[height * width];
        imageProcessor.process(data, variance, height, width, area, new VarianceFinder());
        memorize(variance);
        /*
        Step 4 : wider mean variance
         */
        System.out.println("Finding mean variance ...");
        int[] vmeans = new int[height * width];
        imageProcessor.process(variance, vmeans, height, width, 3 * area, new MeanFinder());
        memorize(vmeans);
        /*
        Step 5 : finding noise threshold;
         */
        System.out.println("Finding noise threshold ...");
        int vnoise = 16;
        double h = 0.3;
        int counter = 0, count = 0;
        for (int i = 0; i < vmeans.length; i++) {
            if (variance[i] < h * vmeans[i] + vnoise) {
                count += variance[i];
                counter++;
            }
        }
        if (counter > 0) {
            vnoise = count / counter;
        }
        System.out.println("Counter " + counter);
        /*
        Step 6 : removing foreground;
         */
        System.out.println("Removing foreground ...");
        for (int i = 0; i < data.length; i++) {
            if (variance[i] > h * vmeans[i]  + vnoise) {
                data[i] = 0;
            }
        }


        /*
        resizing back
         */
//        System.out.println("Resizing back");
//        BufferedImage rimg = ImgProcUtil.resize(rasterize(), 1 / scale);
//        nwData = ((DataBufferByte) rimg.getRaster().getDataBuffer()).getData();
//        height = rimg.getHeight();
//        width = rimg.getWidth();
//        assert nwData.length == height * width;
//        data = new int[nwData.length];
//        for (int i = 0; i < nwData.length; i++) {
//            data[i] = nwData[i] & 0xFF;
//        }
    }

    private void memorize(int[] val) {
        midData.add(new MidData(val, height, width));
    }

    public BufferedImage rasterize() {
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
        img.getRaster().setPixels(0, 0, width, height, data);
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

class MeanFinder implements ProcessorFunction {

    @Override
    public int processPoint(int index, int[] src, int height, int width, int area) {
        int m = 0;
        int ii, n = height * width;
        int n1 = index / width + area / 2, n2 = index % width + area / 2;
        int s = (area + 1) * (area + 1);
        for (int i = index / width - area / 2; i <= n1; i++) {
            for (int j = index % width - area / 2; j <= n2; j++) {
                ii = i * width + j;
                if (ii >= 0 && ii < n) {
                    m += src[ii];
                }
            }
        }
        return m / s;
    }
}

class VarianceFinder implements ProcessorFunction {
    public static int[] means;

    @Override
    public int processPoint(int index, int[] src, int height, int width, int area) {
        int ii, n = height * width;
        int res = 0;
        int n1 = index / width + area / 2, n2 = index % width + area / 2;
        int s = (area + 1) * (area + 1);
        for (int i = index / width - area / 2; i <= n1; i++) {
            for (int j = index % width - area / 2; j <= n2; j++) {
                ii = i * width + j;
                if (ii >= 0 && ii < n) {
                    res += (src[ii] - means[ii]) * (src[ii] - means[ii]);
                }
            }
        }
        return res / s;
    }
}

class Interpolator implements ProcessorFunction {
    private static int[] dst;

    public static void initGoal(int[] src) {
        dst = Arrays.copyOf(src, src.length);
    }

    @Override
    public int processPoint(int index, int[] src, int height, int width, int area) {
        if (dst[index] != 0) {
            return dst[index];
        } else {
            int r1, r2, c1, c2;
            int lr, lc;
            int i;
            i = index;
            while (dst[i] == 0) i++;

        }
        return 0;
    }
}
