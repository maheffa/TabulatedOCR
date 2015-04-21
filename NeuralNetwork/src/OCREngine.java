// File:    OCREngine.java
// Created: 21/04/2015

import java.net.ConnectException;

/**
 * @author mahefa
 */
public class OCREngine {

    private BackPropagationNetwork network;

    public OCREngine (BackPropagationNetwork network) {
        this.network = network;
    }

    public char recognize(ConnectedPixel cp) {
        CharacterPixel charPix = cp.createCharaterPixel().scaleInto(Document.sizeCharacter);
        int solution = network.recognize(charPix.getData());
        return Document.chars[solution];
    }
}
