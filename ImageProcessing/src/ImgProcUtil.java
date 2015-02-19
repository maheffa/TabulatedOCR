// File:    ImgProcUtil.java
// Created: 19/02/2015

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author mahefa
 */
public class ImgProcUtil {

    public ImgProcUtil() {
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
}
