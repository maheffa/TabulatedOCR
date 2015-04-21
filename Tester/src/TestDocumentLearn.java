// File:    TestDocumentLearn.java
// Created: 21/04/2015

import java.io.File;
import java.util.Arrays;
import java.util.Iterator;

/**
 * @author mahefa
 */
public class TestDocumentLearn extends Tester{

    public TestDocumentLearn() {
    }

    public static void main(String[] args) {
        for (int i = 1; i < 8; i++) {
            String path = "Test/learn/rchar" + i + ".jpg";
            String text = "Test/learn/scan" + i + ".txt";
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

            System.out.println("Creating document learning");
            DocumentLearn documentLearn = new DocumentLearn(doc, text);
            documentLearn.prepareDocument();
            documentLearn.learn(0.7, 0.3, 0.01);
            OCREngine engine = documentLearn.getOCREngine();
            log.info("Starting recogniztion");
            Iterator<ConnectedPixel> iterator = doc.iterateConnectedPixels();
            while (iterator.hasNext()) {
                char c = engine.recognize(iterator.next());
                System.out.print(c + ", ");
            }
            log.info("Done");
        }
    }

}
