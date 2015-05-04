// File:    Document.java
// Created: 14/04/2015

import java.awt.image.BufferedImage;
import java.util.*;

/**
 * @author mahefa
 */
public class Document {
    public static int sizeCharacter = 20;
    public static int pixelDefinition = sizeCharacter * sizeCharacter;
    public static int space = 10;
//    public static char[] chars = new char[] {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
//            'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A',
//            'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
//            'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    public static char[] chars = new char[] {'a', 'b', 'c'};
    public static int charNumber = chars.length;

    private String path;
    private BinaryImage binaryImage;
    private TreeSet<ConnectedPixel> connectedPixels;
    private int averageCharacterPerDocument = 1500;

    public Document(String path) {
        this.path = path;
        this.binaryImage = new BinaryImage(path);
        this.connectedPixels = new TreeSet<ConnectedPixel>(new ConnectedPixelComparator());
    }

    public void detectCharacters(int radius, int margin, int minCharHeight, int minCharWidth, int maxCharSize) {
        for (ConnectedPixel cp : ConnectedPixel.getConnectedPixels(radius, margin, binaryImage)) {
            if (cp.getHeight() >= minCharHeight
                    && cp.getHeight() <= maxCharSize
                    && cp.getWidth() >= minCharWidth
                    && cp.getWidth() <= maxCharSize) {
                connectedPixels.add(cp);
            }
        }
    }

    public static int getCharIndex(char c) {
        for (int i = 0; i < charNumber; i++) {
            if (chars[i] == c) {
                return i;
            }
        }
        return -1;
    }

    public void detectStructure() {
        // TODO
    }

    public BufferedImage rasterizeBinaryImage(boolean writeToFile) {
        BufferedImage res = binaryImage.rasterize();
        if (writeToFile) {
            String[] initialFile = SerializerUtil.getPathBaseExtension(path);
            String newName = initialFile[0] + initialFile[1] + ".bin." + initialFile[2];
            ImgProcUtil.writeImage(newName, res, "jpeg");
        }
        return res;
    }

    public BufferedImage rasterizeDetectedCharacter(boolean writeToFile, int charSize) {
        BinaryImage bImg = new BinaryImage(binaryImage.getHeight(), binaryImage.getWidth());
        int[] backgroundColor = new int[]{64, 110, 156};
        int iBack = 0;
        for (ConnectedPixel cp : connectedPixels) {
            CharacterPixel characterPixel = cp.createCharaterPixel();
            if (charSize > 0) {
                characterPixel = characterPixel.scaleInto(charSize);
            }
//            characterPixel.writeOnImage(bImg);
            characterPixel.writeOnImage(bImg, backgroundColor[(iBack++) % backgroundColor.length]);
        }
        BufferedImage res = bImg.rasterize();
        if (writeToFile) {
            String[] initialFile = SerializerUtil.getPathBaseExtension(path);
            String newName = initialFile[0] + initialFile[1] + ".char." + initialFile[2];
            ImgProcUtil.writeImage(newName, res, "jpeg");
        }
        return res;
    }

    public void rasterizeInOrder(boolean writeToFile) {
        PriorityQueue<ConnectedPixel> pq = new PriorityQueue<ConnectedPixel>(
                averageCharacterPerDocument,
                new ConnectedPixelComparator()
        );
        int height = connectedPixels.size()
                / (binaryImage.getWidth() / (sizeCharacter + 2 * space))
                * (2 * sizeCharacter);
        BinaryImage bImg = new BinaryImage(Math.max(binaryImage.getHeight(), height), binaryImage.getWidth());
        int i = sizeCharacter * 2;
        int j = 125;
        int minColor = 200, maxColor = 250;
        int color = maxColor;
        int nChar = connectedPixels.size();
        for (ConnectedPixel cp : connectedPixels) {
            if (j > bImg.getWidth() - 125) {
                j = 125;
                i += 2 * sizeCharacter;
            }
            CharacterPixel chr = cp.createCharaterPixel(i, j);
            chr.scaleInto(sizeCharacter).writeOnImage(bImg, color);
//            chr.writeOnImage(bImg, color);
            color = color == minColor ? maxColor : color - 2;
            j += sizeCharacter + space;
        }
        System.out.println("Charnumber = " + nChar);
        BufferedImage img = bImg.rasterize();
        if (writeToFile) {
            String[] initialFile = SerializerUtil.getPathBaseExtension(path);
            String newName = initialFile[0] + initialFile[1] + ".orChar." + initialFile[2];
            ImgProcUtil.writeImage(newName, img, "jpeg");
        }
    }

    public Iterator<ConnectedPixel> iterateConnectedPixels() {
        return connectedPixels.iterator();
    }

    public TreeSet<ConnectedPixel> getConnectedPixels() {
        return this.connectedPixels;
    }

    public void setNetwork(BackPropagationNetwork network) {
    }

    public int getNumberOfDetectedCharacter() {
        return connectedPixels.size();
    }

    public String getPath() {
        return this.path;
    }

}

