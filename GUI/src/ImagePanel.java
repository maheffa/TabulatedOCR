// File:    ImagePanel.java
// Created: 23/05/2015

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @author mahefa
 */
public class ImagePanel extends JPanel {

    private BufferedImage original;

    public ImagePanel() {
        super();
    }

    public void setImage(String imagePath) {
        original = ImgProcUtil.readImage(imagePath);
        repaint();
    }

    public void setImage(BufferedImage image) {
        original = image;
        repaint();
    }

    public BufferedImage getImage() {
        return this.original;
    }

    public void clear() {
        original = null;
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (original != null) {
            int pWidth = this.getWidth();
            int pHeight = this.getHeight();
            int iWidth = original.getWidth();
            int iHeight = original.getHeight();
            double k = iWidth * 1.0 / pWidth;
            if (iHeight / k > pHeight) {
                k = iHeight * 1.0 / pHeight;
            }
            int sWidth = (int) (iWidth / k);
            int sHeight = (int) (iHeight / k);
            Image img = original.getScaledInstance(sWidth, sHeight, Image.SCALE_SMOOTH);
            int posX = (pWidth - sWidth) / 2;
            int posY = (pHeight - sHeight) / 2;
            g.drawImage(img, posX, posY, Color.WHITE, null);
        }
    }
}
