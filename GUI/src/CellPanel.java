// File:    CellPanel.java
// Created: 25/04/2015

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author mahefa
 */
public class CellPanel implements Serializable {

    private TreeSet<Cell> cells;
    private Cell selectedCell = null;
    private FormatPanel panel;
    private String name = "";

    public CellPanel(FormatPanel panel) {
        cells = new TreeSet<Cell>(new CellComparator(0.01));
        cells.add(new Cell(0.0, 0.0, 1.0, 1.0));
        this.panel = panel;
        panel.revalidate();
        panel.repaint();
    }

    public void draw(Graphics2D g2d) {
        for (Cell cell : cells) {
            if (selectedCell != null && selectedCell.equals(cell)) {
                cell.select(panel, g2d);
            } else {
                cell.draw(panel, g2d);
            }
        }
    }

    public Cell getAtPosition(int realX, int realY) {
        double x = (1.0 * realX) / panel.getWidth();
        double y = (1.0 * realY) / panel.getHeight();
        for (Cell cell : cells) {
            if (cell.getX() <= x && x < cell.getX() + cell.getW()
                    && cell.getY() <= y && y < cell.getY() + cell.getH()) {
                return cell;
            }
        }
        return null;
    }

    public void splitCell(int nCol, int nRow, double wCol, double wRow, Cell cell) {
        if (cells.contains(cell)) {
            cells.remove(cell);
        } else {
            System.out.println("Weird, can't find selected cell");
        }
        cells.addAll(cell.divide(nCol, nRow, wCol, wRow));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Cell getSelectedCell() {
        return selectedCell;
    }

    public void setSelectedCell(Cell selectedCell) {
        this.selectedCell = selectedCell;
    }

    public String printCellsInOrder() {
        StringBuilder str = new StringBuilder();
        str.append("Cells in order:").append('\n');
        for (Cell cell : cells) {
            str.append(cell).append('\n');
        }
        return str.toString();
    }
}

class Cell implements Serializable {
    private double x = 0.0;
    private double y = 0.0;
    private double w = 0.0;
    private double h = 0.0;
    private String formattedContent = "";

    public Cell(double x, double y, double w, double h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Cell) {
            Cell other = (Cell) o;
            return doubleEqual(this.x, other.x)
                    && doubleEqual(this.y, other.y)
                    && doubleEqual(this.w, other.w)
                    && doubleEqual(this.h, other.h);
        } else {
            return false;
        }
    }

    private boolean doubleEqual(double a, double b) {
        double epsilon = 0.01;
        return Math.abs(a - b) < epsilon;
    }

    public ArrayList<Cell> divide(int nCol, int nRow, double wCol, double wRow) {
        System.out.println("Dividing : " + this + " value: " + nCol + ", " + nRow + ", " + wCol + ", " + wRow);
        ArrayList<Cell> res = new ArrayList<Cell>();
        double[] col = new double[nCol];
        double[] row = new double[nRow];
        if (doubleEqual(wCol, 0.0) || nCol != 2) {
            for (int i = 0; i < nCol; i++) {
                col[i] = x + i * w / nCol;
            }
        } else {
            col[0] = x;
            col[1] = x + w * wCol;
        }
        if (doubleEqual(wRow, 0.0) || nRow != 2) {
            for (int i = 0; i < nRow; i++) {
                row[i] = y + i * h / nRow;
            }
        } else {
            row[0] = y;
            row[1] = y + h * wRow;
        }
        for (int j = 0; j < nRow; j++) {
            for (int i = 0; i < nCol; i++) {
                res.add(new Cell(
                        col[i],
                        row[j],
                        (i == nCol - 1 ? x + w : col[i + 1]) - col[i],
                        (j == nRow - 1 ? y + h : row[j + 1]) - row[j]
                ));
            }
        }
        System.out.println("result");
        for (Cell re : res) {
            System.out.println(re);
        }
        return res;
    }

    public void draw(FormatPanel panel, Graphics2D g2d) {
        int height = panel.getHeight();
        int width = panel.getWidth();
        g2d.setColor(Color.BLACK);
        g2d.drawRect(
                (int) (x * width),
                (int) (y * height),
                (int) (w * width),
                (int) (h * height)
        );
//        panel.drawRect(
//                Color.BLACK,
//                (int) (x * width),
//                (int) (y * height),
//                (int) (w * width),
//                (int) (h * height)
//        );
    }

    public void select(FormatPanel panel, Graphics2D g2d) {
        int height = panel.getHeight();
        int width = panel.getWidth();
        g2d.setColor(Color.PINK);
        g2d.fillRect(
                (int) (x * width),
                (int) (y * height),
                (int) (w * width),
                (int) (h * height)
        );
//        panel.drawRect(
//                Color.RED,
//                (int) (x * width),
//                (int) (y * height),
//                (int) (w * width),
//                (int) (h * height)
//        );
    }

    public ArrayList<String> getVariables() {
        ArrayList<String> variables = new ArrayList<String>();
        Pattern pattern = Pattern.compile("(\\$[a-zA-Z_0-9]+)");
        Matcher matcher = pattern.matcher(formattedContent);
        while (matcher.find()) {
            variables.add(matcher.group(0));
        }
        return variables;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public String getFormattedContent() {
        return formattedContent;
    }

    public void setFormattedContent(String formattedContent) {
        this.formattedContent = formattedContent;
    }

    public double getW() {
        return w;
    }

    public double getH() {
        return h;
    }

    @Override
    public String toString() {
        return "{Cell: (" + x + ", " + y + "), (" + w + ", " + h + ")}";
    }
}

class CellComparator implements Comparator<Cell>, Serializable {

    double epsilon = 0.01;

    public CellComparator(double epsilon) {
        this.epsilon = epsilon;
    }

    @Override
    public int compare(Cell cell1, Cell cell2) {
        if (doubleEqual(cell1.getY(), cell2.getY())) {
            if (doubleEqual(cell1.getX(), cell2.getX())) {
                return 0;
            } else {
                return cell1.getX() - cell2.getX() < 0 ? -1 : 1;
            }
        } else {
            return cell1.getY() - cell2.getY() < 0 ? -1 : 1;
        }
    }

    private boolean doubleEqual(double a, double b) {
        return Math.abs(a - b) < epsilon;
    }
}