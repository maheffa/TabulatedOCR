// File:    TestCellExtractor.java
// Created: 10/05/2015

import java.awt.image.BufferedImage;
import java.io.File;

/**
 * @author mahefa
 */
public class TestCellExtractor {

    public TestCellExtractor() {
    }

    public static void main(String[] args) {
        File f = new File("Test/bro.jpg");
        BufferedImage img = ImgProcUtil.readImage("Test/bro.jpg");
        CellExtractor cellExtractor = new CellExtractor(new BinaryImage(img, 100), 30);
        for (CellImage cellImage : cellExtractor.getCells()) {
            System.out.println(cellImage);
        }
        ImgProcUtil.writeImage("Test/bro1.png" , img);
    }

}
