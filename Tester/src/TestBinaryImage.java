// File:    TestBinaryImage.java
// Created: 19/02/2015

import java.awt.image.BufferedImage;

/**
 * @author mahefa
 */
public class TestBinaryImage extends Tester{

    public TestBinaryImage() {
    }

    @Override
    public void runTest() {
        for (int i = 0; i < 7; i++) {
//            if (i == 2) continue;
            BinaryImage bimg = new BinaryImage("Test/t"+i+".jpg");
            long a = System.currentTimeMillis();
            bimg.binarize();
            a = System.currentTimeMillis() - a;
            ImgProcUtil.writeImage("Test/u"+i+".jpg", bimg.rasterize(), "jpeg");
            System.out.println("Done " + i + " in " + a + " ms");
        }
    }

}
