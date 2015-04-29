// File:    CellPanel.java
// Created: 25/04/2015

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author mahefa
 */
public class CellPanel implements Serializable {

    private CellPanel parent;
    private ArrayList<CellPanel> child;
    private int height = 0;
    private int width = 0;
    private int posX = 0;
    private int posY = 0;
    private String formattedContent = "";
    private Color color;
    private FormatPanel panel;
    private String name = "";

    public CellPanel() {
    }

    public CellPanel(int posX, int posY, int width, int height, CellPanel parent, FormatPanel panel) {
        this.posX = posX;
        this.posY = posY;
        this.height = height;
        this.width = width;
        child = new ArrayList<CellPanel>();
        this.parent = parent;
        this.panel = panel;
        draw();
    }

    public void draw(Graphics2D g2d) {
        g2d.setColor(Color.BLACK);
        g2d.drawRect(posX, posY, width, height);
        for (CellPanel c : child) {
            c.draw(g2d);
        }
    }

    public void draw() {
        panel.drawRect(Color.BLACK, posX, posY, width, height);
    }

    public void clear() {
//        panel.drawRect(Color.WHITE, posX, posY, width, height);
    }

    public void select() {
        panel.drawRect(Color.RED, posX, posY, width, height);
    }

    public boolean initialized() {
        return !(width == 0 && height == 0);
    }

    public void divide(int nCol, int nRow) {
        child.clear();
        int dWith = width / nCol;
        int dHeight = height / nRow;
        System.out.println("dividing " + this + " into " + nCol + ", " + nRow);
        for (int i = 0; i < nRow; i++) {
            for (int j = 0; j < nCol; j++) {
                System.out.print(i + ", " + j + " : ");
                CellPanel cp = new CellPanel(
                        posX + j * dWith,
                        posY + i * dHeight,
                        dWith,
                        dHeight,
                        this,
                        panel);
                cp.name = name;
                if (!child.contains(cp)) {
                    System.out.println(cp);
                    child.add(cp);
                }
            }
        }
    }

    public void merge() {
        if (child != null && child.size() > 0) {
            for (CellPanel cell : child) {
                cell.merge();
                cell.clear();
            }
        }
        child.clear();
    }

    public CellPanel getAtPosition(int x, int y) {
        CellPanel res = null;
        if (posX <= x && x <= posX + width
                && posY <= y && y <= posY + height) {
            if (child.size() > 0) {
                for (CellPanel cellPanel : child) {
                    if ((res = cellPanel.getAtPosition(x, y)) != null) {
                        return res;
                    }
                }
            } else {
                return this;
            }
        }
        return res;
    }

    public void setFormattedContent(String formattedContent) {
        this.formattedContent = formattedContent;
    }

    public String getFormattedContent() {
        return formattedContent;
    }

    public void removeChildren() {
        child.clear();
    }

    public CellPanel getParent() {
        return parent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<CellPanel> getChild() {
        return child;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof CellPanel) {
            CellPanel other = (CellPanel) o;
            return this.posX == other.posX && this.posY == other.posY &&
                    this.width == other.width && this.height == other.height;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "{cellpanel : (" + posX + ", " + posY + "), (" + width + ", " + height + ")}";
    }

}
