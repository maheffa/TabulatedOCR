// File:    DocumentLearn.java
// Created: 18/04/2015

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.ConnectException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

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
                ((Document.pixelDefinition - Document.charNumber) * 2) / 3,
                ((Document.pixelDefinition - Document.charNumber) * 1) / 3,
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
                    if (Document.getCharIndex(c) >= 0) {
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

    public boolean learn(double learningRate, double momentum, double minError) {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter("dataForLearning.txt", false));
        } catch (IOException e) {
            e.printStackTrace();
        }
        int indexChar = 0;
        Iterator<ConnectedPixel> connectedPixelIterator = document.iterateConnectedPixels();
        TreeSet<ConnectedPixel> connectedPixels = document.getConnectedPixels();
        if (textChar.length != document.getNumberOfDetectedCharacter()) {
            System.out.println("Error: cannot learn document with provided text. Size are different");
            System.out.println("Dectected characters = " + document.getNumberOfDetectedCharacter());
            System.out.println("Number of text in file = " + textChar.length);
            System.out.println(Arrays.toString(textChar));
            System.out.println(false);
        }
        System.out.println("Adding each character to training set");
        for (ConnectedPixel cp : connectedPixels) {
            char c = textChar[indexChar++];
            CharacterPixel charPix = cp.createCharaterPixel();
            charPix = charPix.scaleInto(Document.sizeCharacter);
            System.out.println("Adding\n" + charPix);
            neuralNetwork.addTraining(charPix.getNeuralNetworkData(), Document.getCharIndex(c));
            try {
                writer.write(Arrays.toString(charPix.getData()));
                writer.newLine();
                writer.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Starting learning");
        neuralNetwork.train(learningRate, momentum, minError);
        neuralNetwork.checkLeraningSuccess();
        return true;
    }

    public Document getDocument() {
        return this.document;
    }

    public OCREngine getOCREngine() {
        return new OCREngine(neuralNetwork);
    }

    public char[] getTextChar() {
        return textChar;
    }
}
