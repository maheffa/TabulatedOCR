// File:    ImgProcUtil.java
// Created: 19/02/2015

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import static org.bytedeco.javacpp.opencv_core.*;
import static org.bytedeco.javacpp.opencv_imgproc.*;
/**
 * @author mahefa
 */
public class ImgProcUtil {

    public ImgProcUtil() {
    }

    public static int getRGB(int binaryColor) {
        int v = binaryColor & 0xFF;
        return v | (v << 8) | (v << 16);
    }

    public static BufferedImage readImage(String filePath) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(filePath));
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

    public static BufferedImage resize(BufferedImage img, double scale) {
        IplImage iplImg = IplImage.createFrom(img);
        IplImage outImg = IplImage.create(
                (int) (iplImg.width() * scale),
                (int) (iplImg.height() * scale),
                iplImg.depth(), iplImg.nChannels());
        cvResize(iplImg, outImg, CV_INTER_LINEAR);
        return outImg.getBufferedImage();
    }

    public static BufferedImage boxblur(BufferedImage img, int boxSize) {
        Mat min = Mat.createFrom(img);
        Mat mout = new Mat();
        blur(min, mout, new Size(boxSize, boxSize));
        return mout.getBufferedImage();
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

    public static String[] getPathBaseExtension(String path) {
        String[] res = new String[3];
        File f = new File(path);
        String name = f.getName();
        String pathN = f.getPath();
        res[0] = pathN.substring(0, pathN.length() - name.length());
        int dotIndex = name.lastIndexOf('.');
        res[1] = name.substring(0, dotIndex);
        res[2] = name.substring(dotIndex + 1);
        return res;
    }
}
