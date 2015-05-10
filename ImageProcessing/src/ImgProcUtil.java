// File:    ImgProcUtil.java
// Created: 19/02/2015

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

/**
 * @author mahefa
 */
public class ImgProcUtil {

    public static String[] IMAGE_EXTENSION = new String[]{ "png", "PNG", "jpg", "jpeg", "JPG", "JPEG", "tiff", "TIFF" };

    public ImgProcUtil() {
    }

    public static boolean isAcceptableImage(File f) {
        String[] pbe = SerializerUtil.getPathBaseExtension(f.getAbsolutePath());
        for (String extension : IMAGE_EXTENSION) {
            if (extension.equals(pbe[2])) {
                return true;
            }
        }
        return false;
    }

    public static int getRGB(int binaryColor) {
        int v = binaryColor & 0xFF;
        return v | (v << 8) | (v << 16);
    }

    public static BufferedImage readImage(String filePath) {
        BufferedImage img = null;
        try {
//            System.out.println("reading " + filePath);
            img = ImageIO.read(new File(filePath));
            BufferedImage rgbImg = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
            for (int i = 0; i < img.getWidth(); i++) {
                for (int j = 0; j < img.getHeight(); j++) {
                    rgbImg.setRGB(i, j, img.getRGB(i, j));
                }
            }
            img = rgbImg;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }

    public static void writeImage(String filePath, BufferedImage img, String format) {
        try {
            File f = new File(filePath);
            ImageIO.write(img, format, f);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeImage(String filePath, BufferedImage img) {
        writeImage(filePath, img, "png");
    }

    public static BufferedImage rasterizeGrayarray(int[] src, int height, int width) {
        BufferedImage img = new BufferedImage(height, width, BufferedImage.TYPE_3BYTE_BGR);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int t = src[i * width + j];
                img.setRGB(j, i, (t << 16) & (t << 8) & t);
            }
        }
        return img;
    }

    public static BufferedImage rasterizeArray(int[] src, int height, int width) {
        BufferedImage img = new BufferedImage(height, width, BufferedImage.TYPE_3BYTE_BGR);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                img.setRGB(j, i, src[i * width + j]);
            }
        }
        return img;
    }

    public static int[] getArrayFromImg(BufferedImage img) {
        int height = img.getHeight(), width = img.getWidth();
        int[] dst = new int[height * width];
        for (int i = 0; i < dst.length; i++) {
            dst[i] = img.getRGB(i % width, i / width);
        }
        return dst;
    }

    public static int[][] getRGBChannels(BufferedImage img) {
        int height = img.getHeight(), width = img.getWidth();
        int[][] t = new int[3][height * width];
        for (int i = 0; i < height * width; i++) {
            int p = img.getRGB(i % width, i / width);
            t[0][i] = p & 0xFF;
            p >>= 8;
            t[1][i] = p & 0xFF;
            p >>= 8;
            t[2][i] = p & 0xFF;
        }
        return t;
    }

    public static BufferedImage writeFromRGBChannels(int[][] c, int height, int width) {
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
        for (int i = 0; i < c[0].length; i++) {
            img.setRGB(i % width, i / width,
                    (c[0][i] & 0xFF) + ((c[1][i] & 0xFF) << 8) + ((c[2][i] & 0xFF) << 16));
        }
        return img;
    }



}
