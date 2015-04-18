// File:    TestDocument.java
// Created: 15/04/2015

import java.io.File;
import java.util.Arrays;

/**
 * @author mahefa
 */
public class TestDocument {

    public TestDocument() {
    }

    public static void main(String[] args) {
        for (int i = 0; i < 6; i++) {
            String path = "Test/learn/scan" + i + ".jpg";
            if (!(new File(path)).exists()) {
                continue;
            }
//            Stirng path = "Test/v"+i+".jpg";
            Document doc = new Document(path);
//            System.out.println("Rasterizing binary image");
//            doc.rasterizeBinaryImage(true);
            System.out.println("Detecting character");
            doc.detectCharacters(5, 1, 10, 60);
//            doc.rasterizeDetectedCharacter(true, -1);
            System.out.println("Rasterizing ordered character");
            doc.rasterizeInOrder(true);
        }
    }

}
