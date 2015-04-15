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
        Document doc = new Document("Test/v0.jpg");
        doc.rasterizeBinaryImage(true);
    }

}
