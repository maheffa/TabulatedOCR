// File:    CharacterPixel.java
// Created: 16/02/2015

import java.awt.image.BufferedImage;

/**
 * @author mahefa
 */
public class CharacterPixel {
    private int height, width;
    private int centerX = 0, centerY = 0;
    private int[] data = null;

    public CharacterPixel(int height, int width) {
        this.height = height;
        this.width = width;
        data = new int[height * width];
    }

    public CharacterPixel(int height, int width, int x, int y) {
        this(height, width);
        centerX = x;
        centerY = y;
    }

    public void scaleInto(int squareSize) {
        double sc = Math.min((double)squareSize / height, (double)squareSize / width);
        boolean isHeight = Math.abs((double)squareSize / height - sc) < 0.001;
        int newHeight = (int) (height * sc), newWidth = (int) (width * sc);
        int iniH = !isHeight ? squareSize / 2 - newHeight / 2 : 0;
        int iniW = !isHeight ? 0 : squareSize / 2 - newWidth / 2;
        int[] newData = new int[squareSize * squareSize];
        for (int i = 0; i < newHeight; i++) {
            for (int j = 0; j < newWidth; j++) {
                newData[(iniH + i) * squareSize + iniW + j] = this.getPixel((int) (i / sc), (int) (j / sc));
            }
        }
        height = squareSize;
        width = squareSize;
        data = newData;
    }

    public int[] getData() {
        return this.data;
    }

    public void scale(double factor) {
        int ideal = Math.max(height, width);
        scaleInto((int)(factor * ideal));
    }

    public BufferedImage rasterize() {
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                img.setRGB(i, j, ImgProcUtil.getRGB(this.getPixel(i, j)));
            }
        }
        return img;
    }

    public void writeOnImage(BinaryImage img) {
        int iniX = centerX == 0 ? 0 : centerX - height / 2;
        int iniY = centerY == 0 ? 0 : centerY - width / 2;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                img.setPixel(iniY + j, iniX + i, this.getPixel(i, j));
            }
        }
    }

    public void writeOnImage(BinaryImage img, int background) {
        int iniX = centerX == 0 ? 0 : centerX - height / 2;
        int iniY = centerY == 0 ? 0 : centerY - width / 2;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                img.setPixel(iniX + i, iniY + j,
                        this.getPixel(i, j) == BinaryImage.BLACK ? BinaryImage.BLACK : background);
            }
        }
    }

    public int getPixel(int i, int j) {
        if (i < 0 || i >= height || j < 0 || j >= width) {
            System.err.println("Pixel out of bound: attempted to get pixel at (" +
                    i + ", " + j + ")\nImage size: (" + height + ", " + width + ")");
            return BinaryImage.BLACK;
        } else {
            return data[i * width + j];
        }
    }

    public void setPixel(int i, int j, int c) {
        if (i < 0 || i >= height || j < 0 || j >= width) {
            System.err.println("Pixel out of bound: attempted to set pixel at (" +
                    i + ", " + j + ")\nImage size: (" + height + ", " + width + ")");
        } else {
            data[i * width + j] = c;
        }
    }

}
