package com.maheffa.TabulatedOCR.TableStructureDetection;// File:    com.maheffa.TabulatedOCR.TableStructureDetection.CellExtractor.java
// Created: 10/05/2015

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeSet;

import com.maheffa.TabulatedOCR.ImageProcessing.BinaryImage;
import org.opencv.core.Point;

/**
 * @author mahefa
 */
/*
Important NOTE: com.maheffa.TabulatedOCR.ImageProcessing.BinaryImage setPixel and getPixel have the wrong parameters (height, width) instead
of (width, height). They are used in lots of places so I don't want to refactorize for now (too little
time). This code will just follow the order of parameter set in com.maheffa.TabulatedOCR.ImageProcessing.BinaryImage's code
 */
public class CellExtractor {

    private int lineThickness;
    private BinaryImage binaryImage;
    private CellContainer cellContainer;
    private int minHeight = 50, minWidth = 50;
    private ArrayList<Point> points;
    private BufferedImage bufferedImage;

    public CellExtractor(BufferedImage img, ArrayList<Point> points, int lineThickness) {
        this.bufferedImage = img;
        this.points = points;
        this.lineThickness = lineThickness;
    }

    public CellExtractor(BinaryImage img, int lineThickness) {
        this.lineThickness = lineThickness;
        this.binaryImage = img;
    }

    public ArrayList<Point> getPointsInDirection(Point p, int direction) {
        /*
        d - direction: 0 - up, 1 - right, 2 - bottom, 3 - left
         */
        ArrayList<Point> result = new ArrayList<Point>();
        int d = direction;
        for (Point p0 : points) {
            if (d == 0 || d == 2) {
                if (Math.abs(p.x - p0.x) < lineThickness) {
                    if (d == 0 && p.y - p0.y > 0) {
                        result.add(p0);
                    } else if (d == 2 && p0.y - p.y > 0) {
                        result.add(p0);
                    }
                }
            } else {
                if (Math.abs(p.y - p0.y) < lineThickness) {
                    if (d == 1 && p0.x - p.x > 0) {
                        result.add(p0);
                    } else if (d == 3 && p.x - p0.x > 0) {
                        result.add(p0);
                    }
                }
            }
        }
        Collections.sort(result, new PointComparator());
        return result;
    }

    public boolean isLinked(Point A, Point B) {
//        boolean link = true;
//        for (int x = (int) Math.min(A.x, B.x); x < (int) Math.max(A.x, B.x) && link; x++) {
//            for (int y = (int) Math.min(A.y, B.y); y < (int) Math.max(A.y, B.y) && link; y++) {
//                int c = bufferedImage.getRGB(x, y) & 0xFFFFFF;
//                link = c == 0xFFFFFF;
//            }
//        }
        return (bufferedImage.getRGB((int)((A.x + B.x) / 2), (int)((A.y + B.y) / 2)) & 0xFFFFFF) == 0xFFFFFF ;
    }

    public CellContainer getCellContainer() {
        cellContainer = new CellContainer(lineThickness);
        for (Point p : points) {
            Point[] pts = new Point[4];
            pts[0] = p;
//            System.out.println("Checking on point " + p);
            if (getTopRight(p, pts)) {
                cellContainer.add(new CellImage((int) p.x, (int) p.y, (int) pts[2].x, (int) pts[2].y));
            }
//            System.out.println();
        }
        return cellContainer;
    }

    public void drawOnImage(BufferedImage img, CellContainer cellContainer) {
        Color[] colors = new Color[]{Color.BLUE, Color.GREEN, Color.RED};
        int count = 0;
        Graphics2D g2d = img.createGraphics();
//        System.out.println("Cell count " + cellContainer.getCells().size());
        for (CellImage cellImage : cellContainer.getCells()) {
            g2d.setColor(colors[count++ % colors.length]);
            g2d.fillRect(cellImage.getX(), cellImage.getY(), cellImage.getWidth(), cellImage.getHeight());
        }
    }

    public boolean getTopRight(Point p, Point[] pts) {
        ArrayList<Point> ps = getPointsInDirection(p, 1);
        Point[] aps = new Point[ps.size()];
        ps.toArray(aps);
//        System.out.println("Going right : " + Arrays.toString(aps));
        for (int i = 0; i < ps.size(); i++) {
            Point p0 = ps.get(i);
//            System.out.println("top right: " + p0);
            if (isLinked(p, p0) && getBottomRight(p0, pts)) {
                pts[1] = p0;
                return true;
            }
        }
        return false;
    }

    public boolean getBottomRight(Point p, Point[] pts) {
        ArrayList<Point> ps = getPointsInDirection(p, 2);
        Point[] aps = new Point[ps.size()];
        ps.toArray(aps);
//        System.out.println("Going down : " + Arrays.toString(aps));
        for (int i = 0; i < ps.size(); i++) {
            Point p0 = ps.get(i);
//            System.out.println("bottom right: " + p0);
            if (isLinked(p, p0) && getBottomLeft(p0, pts)) {
                pts[2] = p0;
                return true;
            }
        }
        return false;
    }

    public boolean getBottomLeft(Point p, Point[] pts){
        ArrayList<Point> ps = getPointsInDirection(p, 3);
        Point[] aps = new Point[ps.size()];
        ps.toArray(aps);
//        System.out.println("Going left : " + Arrays.toString(aps));
        for (int i = ps.size() - 1; i >= 0; i--) {
            Point p0 = ps.get(i);
//            System.out.println("bottom left: " + p0);
            if (isLinked(p, p0) && getTopLeft(p0, pts)) {
                pts[3] = p0;
                return true;
            }
        }
        return false;
    }

    public boolean getTopLeft(Point p, Point[] pts) {
        ArrayList<Point> ps = getPointsInDirection(p, 0);
        Point[] aps = new Point[ps.size()];
        ps.toArray(aps);
//        System.out.println("Going up : " + Arrays.toString(aps));
        for (Point point : ps) {
            if (point.x == pts[0].x && point.y == pts[0].y && isLinked(point, pts[0])) {
                return true;
            }
        }
        return false;
    }

//    public TreeSet<com.maheffa.TabulatedOCR.TableStructureDetection.CellImage> getCells() {
//        int[] colors = {com.maheffa.TabulatedOCR.ImageProcessing.BinaryImage.BLUE, com.maheffa.TabulatedOCR.ImageProcessing.BinaryImage.GREEN, com.maheffa.TabulatedOCR.ImageProcessing.BinaryImage.RED};
//        cellContainer = new com.maheffa.TabulatedOCR.TableStructureDetection.CellContainer(lineThickness);
//        int cellCounter = 0;
//        boolean boo = true;
//        for (int y = 0; y < binaryImage.getHeight(); y++) {
//            for (int x = 0; x < binaryImage.getWidth() && boo; x++) {
////                System.out.println("reading " + Integer.toHexString(binaryImage.getRGB(x, y)));
////                System.out.println("reading " + x + " , " + y);
//                if (binaryImage.getPixel(y, x) == com.maheffa.TabulatedOCR.ImageProcessing.BinaryImage.WHITE) {
////                    int fx = cellContainer.contained(x, y);
////                    if (fx != x) {
////                        x = fx - 1;
////                        continue;
////                    }
////                    System.out.println("Starting on " + x + " + " + y);
//                    com.maheffa.TabulatedOCR.TableStructureDetection.CellImage cellImage = getCell(x, y);
//                    if (cellImage != null) {
//                        if (!cellContainer.contains(cellImage)) {
//                            cellContainer.add(cellImage);
//                            System.out.println("Adding " + cellImage);
////                            boo = false;
////                            break;
//                            System.out.println("drawing");
//                            binaryImage.fillRect(
//                                    cellImage.getX(),
//                                    cellImage.getY(),
//                                    cellImage.getWidth(),
//                                    cellImage.getHeight(),
//                                    colors[cellCounter % colors.length]);
//                            cellCounter++;
//                        }
//                    } else {
////                        System.out.println("failed at " + x + " " + y);
//                    }
//                } else {
////                    if (binaryImage.getPixel(y, x) == com.maheffa.TabulatedOCR.ImageProcessing.BinaryImage.BLUE) {
////                        while (x < binaryImage.getWidth() && binaryImage.getPixel(y, x) == com.maheffa.TabulatedOCR.ImageProcessing.BinaryImage.BLUE) {
////                            x++;
////                        }
////                        x--;
////                    }
//                }
//            }
//        }
//        return cellContainer.getCells();
//    }

    public TreeSet<CellImage> getCells(ArrayList<GroupLines> groupLines) {
        int[] colors = {BinaryImage.BLUE, BinaryImage.GREEN, BinaryImage.RED};
        cellContainer = new CellContainer(lineThickness);
        int cellCounter = 0;
        for (GroupLines groupLine : groupLines) {
            Line line = groupLine.getAverageLine();
            int x = line.getMinX();
            int y = line.getMinY();
            if (binaryImage.getPixel(y, x) == BinaryImage.WHITE) {
                CellImage cellImage = getCell(x, y);
                if (cellImage != null) {
                    if (!cellContainer.contains(cellImage)) {
                        cellContainer.add(cellImage);
                        System.out.println("Adding " + cellImage);
//                            boo = false;
//                            break;
                        System.out.println("drawing");
                        binaryImage.fillRect(
                                cellImage.getX(),
                                cellImage.getY(),
                                cellImage.getWidth(),
                                cellImage.getHeight(),
                                colors[cellCounter % colors.length]);
                        cellCounter++;
                    }
                }
            }
        }
        return cellContainer.getCells();
    }
    public BinaryImage getBinaryImage() {
        return this.binaryImage;
    }

    private CellImage getCell(int x, int y) {
        int[][] coords = new int[4][2];
        coords[0][0] = x;
        coords[0][1] = y;
        CellImage cellImage = null;
        if (getTopRight(x + 1, y, coords)) {
            if (coords[1][0] - x > minWidth && coords[2][1] - y > minHeight) {
                cellImage = new CellImage(x, y, coords[1][0], coords[2][1]);
            }
        }
        return cellImage;
    }

    private boolean getTopRight(int x, int y, int[][] coords) {
        for (int i = x; i < binaryImage.getWidth() && binaryImage.getPixel(y, i) == BinaryImage.WHITE; i++) {
            if (i % 10 == 0 && y % 10 == 0) {

//                System.out.println("right -> " + i + ", " + y);
            }
//            binaryImage.fillRect(i, y, 1, 1, com.maheffa.TabulatedOCR.ImageProcessing.BinaryImage.RED);
            if (y + lineThickness >= binaryImage.getHeight()) return false;
            if (binaryImage.getPixel(y + lineThickness, i) == BinaryImage.WHITE) {
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
        for (int j = y; j < binaryImage.getHeight() && binaryImage.getPixel(j, x) == BinaryImage.WHITE; j++) {
//            if (x % 10 == 0 && j % 10 == 0) System.out.println("down |v " + x + ", " + j);
//            binaryImage.fillRect(x, j, 1, 1, com.maheffa.TabulatedOCR.ImageProcessing.BinaryImage.RED);
            if (x - lineThickness < 0) return false;
            if (binaryImage.getPixel(j, x - lineThickness) == BinaryImage.WHITE) {
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
        for (int i = x; i >= 0 && binaryImage.getPixel(y, i) == BinaryImage.WHITE; i--) {
//            if (i % 10 == 0 && y % 10 == 0) System.out.println("left <- " + i + ", " + y);
//            binaryImage.fillRect(i, y, 1, 1, com.maheffa.TabulatedOCR.ImageProcessing.BinaryImage.RED);
            if (y - lineThickness < 0) return false;
            if (binaryImage.getPixel(y - lineThickness, i) == BinaryImage.WHITE) {
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
        for (int j = y; j >= 0 && binaryImage.getPixel(j, x) == BinaryImage.WHITE; j--) {
//            if (x % 10 == 0 && j % 10 == 0) System.out.println("up |^ " + x + ", " + j);
//            binaryImage.fillRect(x, j, 1, 1, com.maheffa.TabulatedOCR.ImageProcessing.BinaryImage.RED);
//            if (binaryImage.getPixel(j, x + lineThickness) == com.maheffa.TabulatedOCR.ImageProcessing.BinaryImage.WHITE) {
//                if (Math.abs(x - coords[0][0]) + Math.abs(j - coords[0][1]) <= lineThickness) {
            if (x == coords[0][0] && j == coords[0][1]) {
                    return true;
                }
//            }
        }
        return false;
    }

}
