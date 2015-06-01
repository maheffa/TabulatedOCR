package com.maheffa.TabulatedOCR.TableStructureDetection;
// File:    com.maheffa.TabulatedOCR.TableStructureDetection.CellContainer.java
// Created: 10/05/2015

import com.maheffa.TabulatedOCR.ImageProcessing.ImgProcUtil;
import com.maheffa.TabulatedOCR.TextExtraction.Extractor;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.TreeSet;

/**
 * @author mahefa
 */
public class CellContainer {

    private CellImageComparator comparator;
    private TreeSet<CellImage> treeSet;
    private int lineThickness;

    private int lineIndex = 0;
    private ArrayList<CellImage> cellImages;


    public CellContainer(int lineThickness) {
        this.lineThickness = lineThickness;
        comparator = new CellImageComparator(lineThickness);
        treeSet = new TreeSet<CellImage>(comparator);
    }

    public void add(CellImage e) {
        // do not add cell with height or width less than double the line thickness
        if (e.getWidth() <= 2 * lineThickness || e.getHeight() <= 2 * lineThickness) return;
        System.out.println("Adding cell " + e);
        treeSet.add(e);
    }

    public boolean contains(CellImage e) {
        for (CellImage cellImage : treeSet) {
            if (comparator.compare(e, cellImage) == 0) {
                return true;
            }
        }
        return false;
    }

    public int contained(int x, int y) {
        for (CellImage cellImage : treeSet) {
            if (cellImage.getX() <= x && cellImage.getX() + cellImage.getWidth() > x
                    && cellImage.getY() <= y && cellImage.getY() + cellImage.getHeight() > y) {
                return cellImage.getX() + cellImage.getWidth();
            }
        }
        return x;
    }

    public TreeSet<CellImage> getCells() {
        return this.treeSet;
    }

    public void extractCellsToPath(BufferedImage original, String dirPath, String name) {
        File dir = new File(dirPath + File.separator + name);
        if (!dir.exists()) {
            dir.mkdir();
        }
        int i = 0;
        for (CellImage cellImage : treeSet) {
            BufferedImage img = cellImage.getFromImage(original);
            ImgProcUtil.writeImage(
                    dirPath + File.separator + name + File.separator + Integer.toString(i) + ".png",
                    img);
            i++;
        }
    }

    public ArrayList<String> extractLine(Extractor extractor, BufferedImage original) {
        System.out.println("Recognizing line: ");
        ArrayList<String> textLine = null;
        if (hasNextLine()) {
            ArrayList<CellImage> lineImage = getNextLine();
            textLine = new ArrayList<String>();
            for (CellImage cellImage : lineImage) {
                String txt;
                textLine.add(txt = extractor.extractText(cellImage.getFromImage(original)));
                System.out.println(txt);
            }
        }
        return textLine;
    }

    public ArrayList<String> getCellText(Extractor extractor) {
        ArrayList<String> texts = new ArrayList<String>();
        for (CellImage cellImage : treeSet) {
            texts.add(cellImage.getText(extractor));
        }
        return texts;
    }

    public void initLines() {
        this.lineIndex = 0;
        cellImages = new ArrayList<CellImage>();
        for (CellImage cellImage : treeSet) {
            cellImages.add(cellImage);
        }
    }

    public double percentage() {
        return 1.0 * lineIndex / treeSet.size();
    }

    public boolean hasNextLine() {
        return lineIndex < cellImages.size();
    }

    public ArrayList<CellImage> getNextLine() {
        ArrayList<CellImage> line = new ArrayList<CellImage>();
        int h = cellImages.get(lineIndex).getY();
        CellImage c;
        while (lineIndex < cellImages.size() && Math.abs((c = cellImages.get(lineIndex)).getY() - h) < lineThickness) {
            System.out.println("Getting cell " + lineIndex);
            line.add(c);
            lineIndex++;
        }
        return line;
    }

    public int getSize() {
        return treeSet.size();
    }
}

class CellImage {
    int minx = 0, miny = 0, maxx = 0, maxy = 0;
    BufferedImage image = null;

    public CellImage(int minx, int miny, int maxx, int maxy) {
        this.minx = minx;
        this.miny = miny;
        this.maxx = maxx;
        this.maxy = maxy;
    }

    public BufferedImage getFromImage(BufferedImage img) {
        return image = img.getSubimage(
                getX(),
                getY(),
                getWidth(),
                getHeight());
    }

    public BufferedImage getImage() {
        return this.image;
    }

    public String getText(Extractor textExtractor) {
        return textExtractor.extractText(this.image);
    }

    public int getX() {
        return minx;
    }

    public int getY() {
        return miny;
    }

    public int getWidth() {
        return maxx - minx;
    }

    public int getHeight() {
        return maxy - miny;
    }

    @Override
    public String toString() {
        return "{(" + getX() + ", " + getY() + "), width: " + getWidth() + ", height: " + getHeight() + "}";
    }
}

class CellImageComparator implements Comparator<CellImage> {
    private int threshold = 10;

    public CellImageComparator(int threshold) {
        this.threshold = threshold;
    }

    @Override
    public int compare(CellImage c1, CellImage c2) {
        if (Math.abs(c1.getY() - c2.getY()) < threshold) {
            if (Math.abs(c1.getX() - c2.getX()) < threshold) {
                return 0;
            } else {
                return c1.getX() - c2.getX();
            }
        } else {
            return c1.getY() - c2.getY();
        }
    }
}