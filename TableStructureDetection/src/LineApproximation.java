// File:    LineApproximation.java
// Created: 10/05/2015

import org.opencv.core.Point;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * @author mahefa
 */
public class LineApproximation {

    ArrayList<GroupLines> groups;
    int distance;

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

    public BufferedImage draw(int width, int height, int thickness) {
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
        Graphics2D g2d = img.createGraphics();
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, width, height);
        g2d.setColor(Color.WHITE);
        BasicStroke stroke = new BasicStroke(thickness);
        g2d.setStroke(stroke);
        for (GroupLines group : groups) {
            Line l = group.getAverageLine();
            g2d.drawLine(l.getMinX(), l.getMinY(), l.getMaxX(), l.getMaxY());
        }
        return img;
    }

    public ArrayList<GroupLines> getGroupLines() {
        return this.groups;
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
