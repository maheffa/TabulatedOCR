package com.maheffa.TabulatedOCR.GUI;
// File:    ImagePanel.java
// Created: 23/05/2015

import com.maheffa.TabulatedOCR.ImageProcessing.ImgProcUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

/**
 * @author mahefa
 */
public class ImagePanel extends JPanel {

    private BufferedImage original;

    public ImagePanel() {
        super();
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    ImageViewer view = new ImageViewer(original);
                    GUIUtil.createFrameForPanel("Просмотр рисунка", view);
                }
            }
        });
    }

    public void setImage(String imagePath) {
        original = ImgProcUtil.readImage(imagePath);
        repaint();
    }

    public BufferedImage getImage() {
        return this.original;
    }

    public void setImage(BufferedImage image) {
        original = image;
        repaint();
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
