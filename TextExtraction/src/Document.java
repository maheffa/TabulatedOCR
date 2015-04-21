// File:    Document.java
// Created: 14/04/2015

import java.awt.image.BufferedImage;
import java.util.PriorityQueue;

/**
 * @author mahefa
 */
public class Document {
    public static int sizeCharacter = 50;
    public static int pixelDefinition = sizeCharacter * sizeCharacter;
    public static int charNumber = 64;
    public static int space = 10;

    private String path;
    private BinaryImage binaryImage;
//    private ArrayList<ConnectedPixel> connectedPixels;
//    private ArrayList<CharacterPixel> characterPixels;
    private PriorityQueue<ConnectedPixel> priorityCP;
    private int averageCharacterPerDocument = 1500;

    public Document(String path) {
        this.path = path;
        this.binaryImage = new BinaryImage(path);
        this.priorityCP = new PriorityQueue<ConnectedPixel>(
                averageCharacterPerDocument,
                new ConnectedPixelComparator());
    }

    public void detectCharacters(int radius, int margin, int minCharHeight, int minCharWidth, int maxCharSize) {
//        connectedPixels = new ArrayList<ConnectedPixel>();
//        characterPixels = new ArrayList<CharacterPixel>();
        this.priorityCP = new PriorityQueue<ConnectedPixel>(
                averageCharacterPerDocument,
                new ConnectedPixelComparator());
        for (ConnectedPixel cp : ConnectedPixel.getConnectedPixels(radius, margin, binaryImage)) {
            if (cp.getHeight() >= minCharHeight
                    && cp.getHeight() <= maxCharSize
                    && cp.getWidth() >= minCharWidth
                    && cp.getWidth() <= maxCharSize) {
//                connectedPixels.add(cp);
                priorityCP.add(cp);
//                CharacterPixel chp = cp.createCharaterPixel();
//                characterPixels.add(cp.createCharaterPixel());
//                System.out.println(Arrays.toString(cp.createCharaterPixel().getData()));
//                System.out.println(cp.size() + " / " + chp.getData().length);
            }
        }
    }

    public int getNumberOfDetectedCharacter() {
        return priorityCP.size();
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
                * (2 * sizeCharacter);
        BinaryImage bImg = new BinaryImage(Math.max(binaryImage.getHeight(), height), binaryImage.getWidth());
        int i = sizeCharacter * 2;
        int j = 125;
        int minColor = 200, maxColor = 250;
        int color = maxColor;
        int nChar = priorityCP.size();
        while (this.priorityCP.size() > 0) {
            ConnectedPixel cp = priorityCP.poll();
            pq.add(cp);
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
            String[] initialFile = ImgProcUtil.getPathBaseExtension(path);
            String newName = initialFile[0] + initialFile[1] + ".orChar." + initialFile[2];
            ImgProcUtil.writeImage(newName, img, "jpeg");
        }

        this.priorityCP = pq;
    }

    public void setNetwork(BackPropagationNetwork network) {

    }

    public String getPath() {
        return this.path;
    }

}

