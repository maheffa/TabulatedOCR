// File:    DocumentLearn.java
// Created: 18/04/2015

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

/**
 * @author mahefa
 */
public class DocumentLearn {
    private Document document;
    private String textPath;
    private char[] textChar;
    private BackPropagationNetwork neuralNetwork;
    private int[] layers;

    public DocumentLearn(Document document, String textPath) {
        this.document = document;
        this.textPath = textPath;
        this.textChar = readChars(textPath);
        this.layers = new int[] {
                Document.pixelDefinition,
                (Document.pixelDefinition + Document.charNumber) / 2,
                Document.charNumber
        };
        this.neuralNetwork = new BackPropagationNetwork(layers);
    }

    public DocumentLearn(Document document, String textPath, BackPropagationNetwork network) {
        this(document, textPath);
        this.neuralNetwork = network;
    }

    public static char[] readChars(String textPath) {
//        BufferedReader reader = new BufferedReader();
        StringBuilder str = new StringBuilder();
        try {
            List<String> lines = Files.readAllLines((new File(textPath).toPath()), StandardCharsets.UTF_8);
            for (String s : lines) {
                for (char c : s.toCharArray()) {
                    if (c != ' ') {
                        str.append(c);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error: can't read file");
        }
        System.out.println("Read chars: " + str.length());
        return str.toString().toCharArray();
    }

    public void prepareDocument() {
        System.out.println("Detecting character");
        document.detectCharacters(5, 1, 10, 2, 60);
    }

    public boolean learn() {

        return true;
    }

}
