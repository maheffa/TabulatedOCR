// File:    CellContainer.java
// Created: 10/05/2015

import java.awt.image.BufferedImage;
import java.util.Comparator;
import java.util.TreeSet;

/**
 * @author mahefa
 */
public class CellContainer {

    private CellImageComparator comparator;
    private TreeSet<CellImage> treeSet;
    private int lineThickness;

    public CellContainer(int lineThickness) {
        this.lineThickness = lineThickness;
        comparator = new CellImageComparator(lineThickness);
        treeSet = new TreeSet<CellImage>(comparator);
    }

    public void add(CellImage e) {
        // do not add cell with height or width less than double the line thickness
        if (e.getWidth() <= 2 * lineThickness || e.getHeight() <= 2 * lineThickness) return;
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