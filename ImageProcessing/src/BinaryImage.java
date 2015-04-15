// File:    BinaryImage.java
// Created: 19/02/2015

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.util.Arrays;

/**
 * @author mahefa
 */
public class BinaryImage {

    public static int BLACK = 0x00, WHITE = 0xFF;

    private int height, width;
    private int[] data;

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
        binarize();
    }

    public BinaryImage(int height, int width) {
        this.height = height;
        this.width = width;
        data = new int[height * width];
        Arrays.fill(data, WHITE);
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

    public void setPixel(int i, int j, int val) {
        if (i < 0 || i >= height || j < 0 || j >= width) {
            System.err.println("index ("+i+","+j+") out of bound");
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
        int n = height * width;
        int n1 = index / width + area / 2, n2 = index % width + area / 2;
        int s = 0;
        for (int i = index / width - area / 2; i <= n1; i++) {
            if (i < 0 || i >= height) continue;
            for (int j = index % width - area / 2; j <= n2; j++) {
                if (j < 0 || j >= width) continue;
                m += src[i * width + j];
                s++;
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
        int s = 0;
        for (int i = index / width - area / 2; i <= n1; i++) {
            if (i < 0 || i >= height) continue;
            for (int j = index % width - area / 2; j <= n2; j++) {
                if (j < 0 || j >= width) continue;
                ii = i * width + j;
                res += (src[ii] - means[ii]) * (src[ii] - means[ii]);
                s++;
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
            int r1 = 0xFF, r2 = 0xFF, c1 = 0xFF, c2 = 0xFF;
            boolean br1 = false, br2 = false, bc1 = false, bc2 = false;
            int lr1 = 0, lr2 = 0, lc1 = 0, lc2 = 0;
            int i, k, v = 0xFF;
            int d = 10;
            int line = index - index % width;
            // go right
            i = index;
            while (i < line + width && dst[i] == 0) {
                i++; lr1++;
            }
            if (i < line + width) {
                r1 = dst[i];
                br1 = true;
            }
            // go left
            i = index;
            while (i >= line && dst[i] == 0) {
                i--; lr2++;
            }
            if (i >= line) {
                r2 = dst[i];
                br2 = true;
            }
            // go down
            i = index;
            while (i < dst.length && dst[i] == 0) {
                i += width; lc1++;
            }
            if (i < dst.length) {
                c1 = dst[i];
                bc1 = true;
            }
            // go up
            i = index;
            while (i >= 0 && dst[i] == 0) {
                i -= width; lc2++;
            }
            if (i >= 0) {
                c2 = dst[i];
                bc2 = true;
            }
            if (!br1 || !br2) {
                r1 = Math.min(r1, r2); r2 = Math.min(r1, r2);
            }
            if (!bc1 || !bc2) {
                c1 = Math.min(c1, c2); c2 = Math.min(c1, c2);
            }
            int mr = (int) (r1 + ((1.0 * (r2 - r1)) / (lr2 + lr1)) * lr1);
            int mc = (int) (c1 + ((1.0 * (c2 - c1)) / (lc2 + lc1)) * lc1);
            return (mr + mc) / 2;
        }
    }


}
