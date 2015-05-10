// File:    TestBinaryImage.java
// Created: 19/02/2015

import java.awt.image.BufferedImage;
import java.io.File;

/**
 * @author mahefa
 */
public class TestBinaryImage extends Tester{

    public TestBinaryImage() {
    }

    public static void main(String[] args) {
//        for (int i = 0; i < 7; i++) {
//            if (i == 2) continue;
//            BinaryImage bimg = new BinaryImage("Test/t"+i+".jpg");
//            long a = System.currentTimeMillis();
//            bimg.binarize();
//            a = System.currentTimeMillis() - a;
//            ImgProcUtil.writeImage("Test/u"+i+".jpg", bimg.rasterize(), "jpeg");
//            System.out.println("Done " + i + " in " + a + " ms");
//        }
//
        File f = new File("Test/real");
        File[] files = f.listFiles();
        if (files != null) {
            for (File file : files) {
                String[] pbe = SerializerUtil.getPathBaseExtension(file.getAbsolutePath());
                if (pbe[1].contains(".") || !ImgProcUtil.isAcceptableImage(file)) continue;
                System.out.println("Working with " + file.getAbsolutePath());
//                BinaryImage bimg = new BinaryImage(file.getAbsolutePath());
////                bimg.binarize();
//                ImgProcUtil.writeImage(pbe[0] + File.separator + pbe[1] + ".bin.png", bimg.rasterize(), "png");
                BufferedImage bimg = ImgProcUtil.readImage(file.getAbsolutePath());
                bimg = TableDetector.rotate(bimg, Math.PI / 6);
                ImgProcUtil.writeImage(file.getAbsolutePath() + ".1", bimg);
                bimg = TableDetector.rotate(bimg, -Math.PI / 3);
                ImgProcUtil.writeImage(file.getAbsolutePath() + ".2", bimg);
            }
        }
    }

}
