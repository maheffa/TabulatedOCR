// File:    TestOpenCV.java
// Created: 20/02/2015

import java.awt.image.BufferedImage;

/**
 * @author mahefa
 */
public class TestOpenCV extends Tester{

    public TestOpenCV() {
    }

    public void runTest() {
        for(int i = 6; i < 9; i++) {
            BufferedImage img = ImgProcUtil.readImage("Test/t"+i+".jpg");
//        img = ImgProcUtil.resize(img, 1.0 / 4.0);
//        img = ImgProcUtil.resize(img, 4.0);
            BufferedImage img1, img2;
            ImageProcessor ip = new ImageProcessor();
            MeanFinder bb = new MeanFinder();
            long a = System.currentTimeMillis();
            img1 = ImgProcUtil.boxblur(img, 5);
            a = System.currentTimeMillis() - a;
            System.out.println("done opencv");
            long b = System.currentTimeMillis();
            int[][] c = ImgProcUtil.getRGBChannels(img);
            int[][] d = new int[3][c[0].length];
            for (int j = 0; j < 3; j++) {
                ip.process(c[j], d[j], img.getHeight(), img.getWidth(), 5, bb);
            }
            img2 = ImgProcUtil.writeFromRGBChannels(d, img.getHeight(), img.getWidth());
            b = System.currentTimeMillis() - b;
            System.out.println("Opencv : " + a);
            System.out.println("Mine : " + b);
            ImgProcUtil.writeImage("Test/h" + i + "0.jpg", img1, "jpeg");
            ImgProcUtil.writeImage("Test/h"+i+"1.jpg", img2, "jpeg");
        }
    }
}
