// File:    TestDocument.java
// Created: 15/04/2015

import java.io.File;

/**
 * @author mahefa
 */
public class TestDocument {

    public TestDocument() {
    }

    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
//            String path = "Test/learn/rchar" + i + ".jpg";
            String path = "Test/learn/scan" + i + ".jpg";
            long fileSize = 0;
            File f = null;
            if (!(f = new File(path)).exists()) {
                fileSize = f.length();
                continue;
            }
            System.out.println("[File: " + path + "]");
            System.out.println("[Filesize: " + fileSize + "]");
//            Stirng path = "Test/v"+i+".jpg";
            System.out.println("Creating document");
            Util.startChrono();
            Document doc = new Document(path);
            Util.stopChrono();
            Util.outputChrono();

//            System.out.println("Rasterizing binary image");
//            doc.rasterizeBinaryImage(true);
            System.out.println("Detecting character");
            Util.startChrono();
            doc.detectCharacters(5, 1, 10, 2, 60);
            Util.stopChrono();
            Util.outputChrono();

//            doc.rasterizeDetectedCharacter(true, -1);

            System.out.println("Rasterizing ordered character");
            Util.startChrono();
            doc.rasterizeInOrder(true);
            Util.stopChrono();
            Util.outputChrono();
        }
    }

}
