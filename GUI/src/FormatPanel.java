// File:    FormatPanel.java
// Created: 27/04/2015

import javax.swing.*;
import java.awt.*;

/**
 * @author mahefa
 */
public class FormatPanel extends JPanel {

    private CellPanel root = null;
    private Color color;
    private int tx, ty, tWidth, tHeight;
    private JFrame frame = null;

    public FormatPanel() {
        super();
    }

    public void initCell() {
        root = new CellPanel(0, 0, this.getWidth(), this.getHeight(), null, this);
        root.draw();
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public void closeFrame() {
        if (frame != null) {
            frame.setVisible(false);
            frame.dispose();
        }
    }

    public void drawRect(Color color, int x, int y, int width, int height) {
        this.color = color;
        this.tx = x;
        this.ty = y;
        this.tWidth = width;
        this.tHeight = height;
        this.revalidate();
        this.repaint();
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D g2d = (Graphics2D) graphics;
        g2d.setStroke(new BasicStroke(5));
        root.draw(g2d);
        g2d.setColor(color);
        g2d.drawRect(tx, ty, tWidth, tHeight);
    }

    public CellPanel getCellPanel() {
        return root;
    }
}
