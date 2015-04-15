// File:    Document.java
// Created: 14/04/2015

import java.util.ArrayList;

/**
 * @author mahefa
 */
public class Document {
    private String path;
    private BinaryImage binaryImage;
    private ConnectedPixel connectedPixel;
    private ArrayList<ConnectedPixel> connectedPixels;
    private ArrayList<CharacterPixel> characterPixels;

    public Document(String path) {
        this.path = path;
        this.binaryImage = new BinaryImage(path);
    }

    public void detectCharacters(int radius, int margin, int minCharSize, int maxCharSize, int charSize) {
        connectedPixels = new ArrayList<ConnectedPixel>();
        characterPixels = new ArrayList<CharacterPixel>();
        for (ConnectedPixel cp : ConnectedPixel.getConnectedPixels(radius, margin, binaryImage)) {
            if (cp.getHeight() >= minCharSize
                    && cp.getHeight() <= maxCharSize
                    && cp.getWidth() >= minCharSize
                    && cp.getWidth() <= maxCharSize) {
                connectedPixels.add(cp);
                characterPixels.add(cp.createCharaterPixel());
            }
        }
    }

    public void detectStructure() {
        // TODO
    }

}
