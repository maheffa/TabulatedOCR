// File:    TestDocument.java
// Created: 15/04/2015

import java.util.Arrays;

/**
 * @author mahefa
 */
public class TestDocument {

    public TestDocument() {
    }

    public static void main(String[] args) {
        for (int i = 0; i < 2; i++) {
            if (i == 2) continue;
            Document doc = new Document("Test/learn/scan"+i+".jpg");
//            Document doc = new Document("Test/v"+i+".jpg");
            doc.rasterizeBinaryImage(true);
            doc.detectCharacters(3, 1, 5, 60);
            doc.rasterizeDetectedCharacter(true, -1);
        }
    }

}
