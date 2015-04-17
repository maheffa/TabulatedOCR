// File:    Document.java
// Created: 14/04/2015

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author mahefa
 */
public class Document {
    private String path;
    private BinaryImage binaryImage;
    private ArrayList<ConnectedPixel> connectedPixels;
    private ArrayList<CharacterPixel> characterPixels;

    public Document(String path) {
        this.path = path;
        this.binaryImage = new BinaryImage(path);
        File file = new File(path);

    }

    public void detectCharacters(int radius, int margin, int minCharSize, int maxCharSize) {
        connectedPixels = new ArrayList<ConnectedPixel>();
        characterPixels = new ArrayList<CharacterPixel>();
        for (ConnectedPixel cp : ConnectedPixel.getConnectedPixels(radius, margin, binaryImage)) {
            if (cp.getHeight() >= minCharSize
                    && cp.getHeight() <= maxCharSize
                    && cp.getWidth() >= minCharSize
                    && cp.getWidth() <= maxCharSize) {
                connectedPixels.add(cp);
                characterPixels.add(cp.createCharaterPixel());
                System.out.println(Arrays.toString(cp.createCharaterPixel().getData()));
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
            String newName = initialFile[0] + initialFile[1] + ".bin." + initialFile[2];
            ImgProcUtil.writeImage(newName, res, "jpeg");
        }
        return res;
    }

    public BufferedImage rasterizeDetectedCharacter(boolean writeToFile, int charSize) {
        BinaryImage bImg = new BinaryImage(binaryImage.getHeight(), binaryImage.getWidth());
        int[] backgroundColor = new int[]{64, 110, 156};
        int iBack = 0;
        for (CharacterPixel characterPixel : characterPixels) {
            if (charSize > 0) {
                characterPixel = characterPixel.scaleInto(charSize);
            }
            characterPixel.writeOnImage(bImg);
//            characterPixel.writeOnImage(bImg, backgroundColor[(iBack++) % backgroundColor.length]);
        }
        BufferedImage res = bImg.rasterize();
        if (writeToFile) {
            String[] initialFile = ImgProcUtil.getPathBaseExtension(path);
            String newName = initialFile[0] + initialFile[1] + ".char." + initialFile[2];
            ImgProcUtil.writeImage(newName, res, "jpeg");
        }
        return res;
    }

}
