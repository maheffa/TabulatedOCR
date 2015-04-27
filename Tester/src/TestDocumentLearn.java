// File:    TestDocumentLearn.java
// Created: 21/04/2015

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;

/**
 * @author mahefa
 */
public class TestDocumentLearn extends Tester{

    public TestDocumentLearn() {
    }

    public static void main(String[] args) {
        for (int i = 1; i < 2; i++) {
//            String path = "Test/learn/rchar" + i + ".jpg";
//            String text = "Test/learn/scan" + i + ".txt";
            String path = "Test/learn/litlerandom.jpg";
            String text = "Test/learn/litlerandom.txt";
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

            doc.rasterizeBinaryImage(true);
            doc.detectCharacters(5, 1, 10, 2, 60);
            doc.rasterizeInOrder(true);

            System.out.println("Creating document learning");
            DocumentLearn documentLearn = new DocumentLearn(doc, text);
            documentLearn.prepareDocument();
            documentLearn.learn(0.6, 0.2, 0.01);
            OCREngine engine = documentLearn.getOCREngine();
            log.info("Starting recogniztion");
            i = 0;
            char[] textChar = documentLearn.getTextChar();
            int success = 0, count = 0;
            BufferedWriter writer = null;
            try {
                writer = new BufferedWriter(new FileWriter("dataToRecognize.txt", false));
            } catch (IOException e) {
                e.printStackTrace();
            }
            for (ConnectedPixel cp : documentLearn.getDocument().getConnectedPixels()) {
                char c = engine.recognize(cp);
                try {
                    writer.write(Arrays.toString(cp.createCharaterPixel().scaleInto(Document.sizeCharacter).getData()));
                    writer.newLine();
                    writer.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("expected " + textChar[i] + ", index: " + Document.getCharIndex(textChar[i]));
                if (c == textChar[i++]) {
                    success++;
                }
                System.out.println("result " + c);
                count++;
            }
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("result : " + success + "/" + count + " ("+ ((success*100)/count) + "%)");
            log.info("Done");
        }
    }

}
