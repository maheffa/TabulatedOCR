package com.maheffa.TabulatedOCR.ImageProcessing;// File:    com.maheffa.TabulatedOCR.ImageProcessing.ImgProcUtil.java
// Created: 19/02/2015

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.io.File;
import java.io.IOException;

/**
 * @author mahefa
 */
public class ImgProcUtil {

    public static String[] IMAGE_EXTENSION = new String[]{ "png", "PNG", "jpg", "jpeg", "JPG", "JPEG", "tiff", "TIFF" };

    public ImgProcUtil() {
    }

    public static boolean isAcceptableImage(File f) {
        String[] pbe = getPathBaseExtension(f.getAbsolutePath());
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
            System.out.println("reading image: " + filePath);
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
            System.out.println("Writing image to " + filePath);
            File f = new File(filePath);
            ImageIO.write(img, format, f);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeImage(String filePath, BufferedImage img) {
        writeImage(filePath, img, "png");
    }

    public static String getImageInfoHTML(BufferedImage image) {
        StringBuilder str = new StringBuilder();
        str.append("<b>Width: </b>").append(image.getWidth()).append("<br/>");
        str.append("<b>Height: </b>").append(image.getHeight()).append("<br/>");
        ColorModel cm = image.getColorModel();
        str.append("<b>Pixel size: </b>").append(cm.getPixelSize()).append("<br/>");
        str.append("<b>Channel: </b>").append(cm.getNumComponents()).append("<br/>");
        str.append("<b>Alpha: </b>").append(cm.hasAlpha()).append("<br/>");
        return str.toString();
    }

    public static String[] getPathBaseExtension(String path) {
        String[] res = new String[3];
        File f = new File(path);
        String pathN;
        try {
            pathN = f.getCanonicalPath();
            int i0 = pathN.lastIndexOf(File.separator);
            res[0] = pathN.substring(0, i0);
            String name = pathN.substring(i0 + 1, pathN.length());
            int i1 = name.lastIndexOf('.');
            if (i1 > 0) {
                res[1] = name.substring(0, i1);
                res[2] = name.substring(i1 + 1);
            } else {
                res[1] = name;
                res[2] = "";
            }
            return res;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
