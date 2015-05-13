// File:    LineApproximation.java
// Created: 10/05/2015

import org.opencv.core.Point;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;

/**
 * @author mahefa
 */
public class LineApproximation {

    private int maxDistanceGap = 50;
    private int maxGap = 20;
    private ArrayList<GroupLines> groups;
    private ArrayList<Line> lines;
    private int distance;

    public LineApproximation(int distance) {
        groups = new ArrayList<GroupLines>();
        this.distance = distance;
    }

    public void add(int x1, int y1, int x2, int y2) {
        Line l = new Line(x1, y1, x2, y2);
        boolean fit = false;
        for (GroupLines group : groups) {
            if (fit) break;
            if (group.isClose(l)) {
                group.add(l);
                fit = true;
            }
        }
        if (!fit) {
            GroupLines group = new GroupLines(distance);
            group.add(l);
            groups.add(group);
        }
    }

    public ArrayList<Line> mergeGroupLines() {
        MergeLines mergeLines = new MergeLines(maxDistanceGap, maxGap);
        ArrayList<Line> lines = new ArrayList<Line>();
        for (GroupLines group : groups) {
            lines.add(group.getAverageLine());
        }
        return this.lines = mergeLines.mergeAll(lines);
    }

    public BufferedImage draw(int width, int height, int thickness) {
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
        Graphics2D g2d = img.createGraphics();
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, width, height);
        g2d.setColor(Color.WHITE);
        BasicStroke stroke = new BasicStroke(thickness);
        g2d.setStroke(stroke);
        for (Line l : mergeGroupLines()) {
            g2d.drawLine(l.getMinX(), l.getMinY(), l.getMaxX(), l.getMaxY());
        }
        return img;
    }

    public ArrayList<GroupLines> getGroupLines() {
        return this.groups;
    }

    public ArrayList<Line> getLines() {
        if (lines == null) {
            mergeGroupLines();
        }
        return lines;
    }
}

class MergeLines {
    // line should be either vertical or horizontal
    private int maxDistanceGap;
    private int maxGap;

    public MergeLines (int maxDistanceGap, int maxGap) {
        this.maxDistanceGap = maxDistanceGap;
        this.maxGap = maxGap;
    }

    private boolean isHorizontal(Line line) {
        return line.getMinY() == line.getMaxY();
    }

    public Line merge(Line l1, Line l2) {
        Line l = null;
        if (isHorizontal(l1)) {
            if (isHorizontal(l2)) {
                if (Math.abs(l1.getMaxY() - l2.getMaxY()) < maxGap) {
                    if (l1.getMaxX() + maxDistanceGap > l2.getMinX()
                            && l1.getMinX() < l2.getMaxX() + maxDistanceGap) {
                        l = new Line(
                                Math.min(l1.getMinX(), l2.getMinX()),
                                (l1.getMinY() + l2.getMinY()) / 2,
                                Math.max(l1.getMaxX(), l2.getMaxX()),
                                (l1.getMinY() + l2.getMinY()) / 2
                        );
                    }
                }
            }
        } else {
            if (!isHorizontal(l2)) {
                if (Math.abs(l1.getMaxX() - l2.getMaxX()) < maxGap) {
                    if (l1.getMaxY() + maxDistanceGap > l2.getMinY()
                        && l1.getMinY() < l2.getMaxY() + maxDistanceGap) {
                        l = new Line(
                                (l1.getMinX() + l2.getMinX()) / 2,
                                Math.min(l1.getMinY(), l2.getMinY()),
                                (l1.getMinX() + l2.getMinX()) / 2,
                                Math.max(l1.getMaxY(), l2.getMaxY())
                        );
                    }
                }
            }
        }
        return l;
    }

    public ArrayList<Line> mergeAll(ArrayList<Line> setlines) {
        int initialCount = setlines.size();
        ArrayList<Line> lines = new ArrayList<Line>();
        for (Line setline : setlines) {
            lines.add(setline);
        }
        boolean merged = true;
        while (merged) {
            merged = false;
            for (int i = 0; i < lines.size() && !merged; i++) {
                Line l1 = lines.get(i);
                for (int j = i + 1; j < lines.size() && !merged; j++) {
                    Line l2 = lines.get(j);
                    Line l = merge(l1, l2);
                    if (l != null) {
                        merged = true;
//                        System.out.println("Merging " + l1 + "\twith " + l2 + "\t-> " + l);
                        lines.remove(i);
                        lines.remove(j - 1);
                        lines.add(l);
                    }
                }
            }
        }
        System.out.println("Merged lines from " + initialCount + " down to " + lines.size());
        return lines;
    }
}

class GroupLines {

    private ArrayList<Line> lines;
    private int distance;

    public GroupLines(int distance) {
        lines = new ArrayList<Line>();
        this.distance = distance;
    }

    public boolean isClose(Line l) {
        for (Line line : lines) {
            if (line.isClose(l, distance)) {
                return true;
            }
        }
        return false;
    }

    public void add(Line l) {
        lines.add(l);
    }

    public Line getAverageLine() {
        int minLineLength = 30;
        int x1 = 0, x2 = 0, y1 = 0, y2 = 0;
        Line res = null;
        for (Line line : lines) {
            x1 += line.getMinX();
            y1 += line.getMinY();
            x2 += line.getMaxX();
            y2 += line.getMaxY();
        }
        x1 /= lines.size();
        x2 /= lines.size();
        y1 /= lines.size();
        y2 /= lines.size();
        // if line is horizontal then get a straight horizontal line
        if (Math.abs(y1 - y2) < minLineLength) {
            res = new Line(x1, (y1 + y2) / 2, x2, (y1 + y2) / 2);
        } else {
            // otherwise a straight vertical line
            res = new Line((x1 + x2) / 2, y1, (x1 + x2) / 2, y2);
        }
        return res;
    }
}

class Line {

    private Point A, B;

    public Line(Point A, Point B) {
        this.A = A;
        this.B = B;
    }

    public Line(int x1, int y1, int x2, int y2) {
        this.A = new Point(x1, y1);
        this.B = new Point(x2, y2);
    }

    public boolean isClose(Line other, int dist) {
        return (TableDetector.getDistance(this.A, other.A) < dist
                && TableDetector.getDistance(this.B, other.B) < dist)
                || (TableDetector.getDistance(this.A, other.B) < dist
                && TableDetector.getDistance(this.B, other.A) < dist);
    }

    public static Point getProlongedIntersetion(Line l1, Line l2) {
        double x1 = l1.getA().x, y1 = l1.getA().y, x2 = l1.getB().x, y2 = l1.getB().y;
        double x3 = l2.getA().x, y3 = l2.getA().y, x4 = l2.getB().x, y4 = l2.getB().y;
        double d;
        double[] res = new double[2];
        if ((d = ((x1-x2) * (y3-y4)) - ((y1-y2) * (x3-x4))) != 0) {
            res[0] = ((x1*y2 - y1*x2) * (x3-x4) - (x1-x2) * (x3*y4 - y3*x4)) / d;
            res[1] = ((x1*y2 - y1*x2) * (y3-y4) - (y1-y2) * (x3*y4 - y3*x4)) / d;
        }
        else {
            res = new double[]{-1, -1};
        }
        return new Point(res[0], res[1]);
    }

    public Point getA() {
        return A;
    }

    public Point getB() {
        return B;
    }

    public int getMinX() {
        return (int)Math.min(A.x, B.x);
    }

    public int getMaxX() {
        return (int) Math.max(A.x, B.x);
    }

    public int getMinY() {
        return (int) Math.min(A.y, B.y);
    }

    public int getMaxY() {
        return (int) Math.max(A.y, B.y);
    }

    @Override
    public String toString() {
        return A.toString() + " - " + B.toString();
    }
}

class PointComparator implements Comparator<Point> {

    @Override
    public int compare(Point p1, Point p2) {
        if (p1.y == p2.y) {
            return (int) (p1.x - p2.x);
        } else {
            return (int) (p1.y - p2.y);
        }
    }
}