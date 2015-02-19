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
        for (int i = 6; i < 7; i++) {
            BinaryImage bimg = new BinaryImage("Test/t"+i+".jpg");
            ImgProcUtil.writeImage("Test/u"+i+".jpg", bimg.rasterize(), "jpeg");
            System.out.println("Done " + i);
        }
    }

}
