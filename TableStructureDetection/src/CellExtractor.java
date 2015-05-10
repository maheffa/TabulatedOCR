// File:    CellExtractor.java
// Created: 10/05/2015

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.TreeSet;

/**
 * @author mahefa
 */
public class CellExtractor {

    private int lineThickness;
    private BinaryImage image;
    private CellContainer cellContainer;

    public CellExtractor(BinaryImage img, int lineThickness) {
        this.lineThickness = lineThickness;
        this.image = img;
    }

    public TreeSet<CellImage> getCells() {
        cellContainer = new CellContainer(2 * lineThickness);
        boolean boo = true;
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth() && boo; x++) {
//                System.out.println("reading " + Integer.toHexString(image.getRGB(x, y)));
//                System.out.println("reading " + x + " , " + y);
                if (image.getPixel(x, y) == BinaryImage.WHITE) {
                    int fx = cellContainer.contained(x, y);
                    if (fx != x) {
                        x = fx;
                        continue;
                    }
                    CellImage cellImage = getCell(x, y);
                    if (cellImage != null) {
//                        System.out.println("Got white pixel on " + x + " + " + y);
                        if (!cellContainer.contains(cellImage)) {
                            cellContainer.add(cellImage);
                            System.out.println("Adding " + cellImage);
//                            boo = false;
//                            break;
                        }
                    }
                }
            }
        }
        return cellContainer.getCells();
    }

    private CellImage getCell(int x, int y) {
        int[][] coords = new int[4][2];
        coords[0][0] = x;
        coords[0][1] = y;
        CellImage cellImage = null;
        if (getTopRight(x + 1, y, coords)) {
            cellImage = new CellImage(x, y, coords[1][0] - x, coords[2][1] - y);
        }
        return cellImage;
    }

    private boolean getTopRight(int x, int y, int[][] coords) {
        for (int i = x; i < image.getWidth(); i++) {
//            System.out.println("right -> " + i + ", " + y);
            if (image.getPixel(i, y + 2 * lineThickness) == BinaryImage.WHITE) {
                if (getBottomRight(i, y, coords)) {
                    coords[1][0] = i;
                    coords[1][1] = y;
                    return true;
                }
            }
        }
        return false;
    }

    private boolean getBottomRight(int x, int y, int[][] coords) {
        for (int j = y; j < image.getHeight(); j++) {
//            System.out.println("down |v " + x + ", " + j);
            if (image.getPixel(x - 2 * lineThickness, j) == BinaryImage.WHITE) {
                if (getBottomLeft(x, j, coords)) {
                    coords[2][0] = x;
                    coords[2][1] = j;
                    return true;
                }
            }
        }
        return false;
    }

    private boolean getBottomLeft(int x, int y, int[][] coords) {
        for (int i = x; i >= 0; i--) {
//            System.out.println("left <- " + i + ", " + y);
            if (image.getPixel(i, y - 2 * lineThickness) == BinaryImage.WHITE) {
                if (getTopLeft(i, y, coords)) {
                    coords[3][0] = i;
                    coords[3][1] = y;
                    return true;
                }
            }
        }
        return false;
    }

    private boolean getTopLeft(int x, int y, int[][] coords) {
        for (int j = y; j >= 0; j--) {
//            System.out.println("up |^ " + x + ", " + j);
            if (image.getPixel(x + 2 * lineThickness, j) == BinaryImage.WHITE) {
                if (Math.abs(x - coords[0][0]) + Math.abs(j - coords[0][1]) <= 2 * lineThickness) {
                    return true;
                }
            }
        }
        return false;
    }

}
