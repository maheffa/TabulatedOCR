// File:    Document.java
// Created: 14/04/2015

import java.awt.image.BufferedImage;
import java.io.File;
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
        File file = new File(path);

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

    public BufferedImage rasterizeBinaryImage(boolean writeToFile) {
        BufferedImage res = binaryImage.rasterize();
        if (writeToFile) {
            String[] initialFile = ImgProcUtil.getPathBaseExtension(path);
            String newName = initialFile[0] + initialFile[1] + ".bin" + initialFile[2];
            ImgProcUtil.writeImage(newName, res, "jpeg");
        }
        return res;
    }

}
