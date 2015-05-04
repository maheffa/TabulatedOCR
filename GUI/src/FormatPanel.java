// File:    FormatPanel.java
// Created: 27/04/2015

import javax.swing.*;
import java.awt.*;

/**
 * @author mahefa
 */
public class FormatPanel extends JPanel {

    private CellPanel cellPanel = null;

    public FormatPanel() {
        super();
    }

    public void initCell() {
        cellPanel = new CellPanel(this);
        this.revalidate();
        this.repaint();
    }

    public void setCellPanel(CellPanel cellPanel) {
        this.cellPanel = cellPanel;
    }

//    public void drawRect(Color color, int x, int y, int width, int height) {
//        this.color = color;
//        this.tx = x;
//        this.ty = y;
//        this.tWidth = width;
//        this.tHeight = height;
//        this.revalidate();
//        this.repaint();
//    }
//
    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D g2d = (Graphics2D) graphics;
        g2d.setStroke(new BasicStroke(2));
        cellPanel.draw(g2d);
//        g2d.setColor(color);
//        g2d.drawRect(tx, ty, tWidth, tHeight);
    }

    public CellPanel getCellPanel() {
        return cellPanel;
    }
}
