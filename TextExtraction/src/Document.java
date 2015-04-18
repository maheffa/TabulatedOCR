// File:    Document.java
// Created: 14/04/2015

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author mahefa
 */
public class Document {
    private String path;
    private BinaryImage binaryImage;
//    private ArrayList<ConnectedPixel> connectedPixels;
//    private ArrayList<CharacterPixel> characterPixels;
    private PriorityQueue<ConnectedPixel> priorityCP;
    private BackPropagationNetwork network;
    private int sizeCharacter = 30;
    private int averageCharacterPerDocument = 1500;
    private int pixelDefinition = sizeCharacter * sizeCharacter;
    private int charNumber = 64;
    private int space = 10;
    private int[] layers = new int[]{pixelDefinition, (pixelDefinition + charNumber) / 2, charNumber};

    public Document(String path) {
        this.path = path;
        this.binaryImage = new BinaryImage(path);
        this.network = new BackPropagationNetwork(layers);
        this.priorityCP = new PriorityQueue<ConnectedPixel>(
                averageCharacterPerDocument,
                new ConnectedPixelComparator());
    }

    public void detectCharacters(int radius, int margin, int minCharSize, int maxCharSize) {
//        connectedPixels = new ArrayList<ConnectedPixel>();
//        characterPixels = new ArrayList<CharacterPixel>();
        this.priorityCP = new PriorityQueue<ConnectedPixel>(
                averageCharacterPerDocument,
                new ConnectedPixelComparator());
        for (ConnectedPixel cp : ConnectedPixel.getConnectedPixels(radius, margin, binaryImage)) {
            if (cp.getHeight() >= minCharSize
                    && cp.getHeight() <= maxCharSize
                    /*&& cp.getWidth() >= minCharSize
                    && cp.getWidth() <= maxCharSize*/) {
//                connectedPixels.add(cp);
                priorityCP.add(cp);
//                CharacterPixel chp = cp.createCharaterPixel();
//                characterPixels.add(cp.createCharaterPixel());
//                System.out.println(Arrays.toString(cp.createCharaterPixel().getData()));
//                System.out.println(cp.size() + " / " + chp.getData().length);
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
        for (ConnectedPixel cp : priorityCP) {
            CharacterPixel characterPixel = cp.createCharaterPixel();
            if (charSize > 0) {
                characterPixel = characterPixel.scaleInto(charSize);
            }
//            characterPixel.writeOnImage(bImg);
            characterPixel.writeOnImage(bImg, backgroundColor[(iBack++) % backgroundColor.length]);
        }
        BufferedImage res = bImg.rasterize();
        if (writeToFile) {
            String[] initialFile = ImgProcUtil.getPathBaseExtension(path);
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
        int height = priorityCP.size()
                / (binaryImage.getWidth() / (sizeCharacter + 2 * space))
                * (sizeCharacter + space);
        BinaryImage bImg = new BinaryImage(Math.max(binaryImage.getHeight(), height), binaryImage.getWidth());
        int i = sizeCharacter * 2;
        int j = sizeCharacter * 2;
        int minColor = 60, maxColor = 160;
        int color = maxColor;
        while (this.priorityCP.size() > 0) {
            ConnectedPixel cp = priorityCP.poll();
            if (j > bImg.getWidth() - 2 * sizeCharacter) {
                j = sizeCharacter * 2;
                i += sizeCharacter + space;
            }
            CharacterPixel chr = cp.createCharaterPixel(i, j);
            chr.scaleInto(sizeCharacter).writeOnImage(bImg, color);
//            chr.writeOnImage(bImg, color);
            color = color == minColor ? maxColor : color - 2;
            j += sizeCharacter + space;
        }
        BufferedImage img = bImg.rasterize();
        if (writeToFile) {
            String[] initialFile = ImgProcUtil.getPathBaseExtension(path);
            String newName = initialFile[0] + initialFile[1] + ".orChar." + initialFile[2];
            ImgProcUtil.writeImage(newName, img, "jpeg");
        }
    }

    public void setNetwork(BackPropagationNetwork network) {

    }

    public String getPath() {
        return this.path;
    }

}

