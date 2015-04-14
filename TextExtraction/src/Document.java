// File:    Document.java
// Created: 14/04/2015

/**
 * @author mahefa
 */
public class Document {
    private String path;
    private BinaryImage binaryImage;
    private ConnectedPixel connectedPixel;

    public Document(String path) {
        this.path = path;
        this.binaryImage = new BinaryImage(path);
    }




}
