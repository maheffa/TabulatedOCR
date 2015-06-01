/*
 * Created by JFormDesigner on Thu May 28 16:12:12 MSK 2015
 */

package com.maheffa.TabulatedOCR.GUI;

import com.jgoodies.forms.factories.Borders;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

/**
 * @author Mahefa Manitrativo
 */
public class ImageViewer extends JPanel {
    private BufferedImage image;
    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Mahefa Manitrativo
    private JPanel panel1;
    private JButton butZoomIn;
    private JButton butZoomOut;
    private JScrollPane scrollPane1;
    private JPanel imagePanel;

    public ImageViewer(BufferedImage image) {
        this.image = image;
        initComponents();
        this.scrollPane1.addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent mouseWheelEvent) {
                int notches = mouseWheelEvent.getWheelRotation();
                if (notches < 0) {
                    for (int i = 0; i < -notches; i++) {
                        ((ZoomPanel) imagePanel).zoomIn();
                    }
                } else {
                    for (int i = 0; i < notches; i++) {
                        ((ZoomPanel) imagePanel).zoomOut();
                    }
                }
            }
        });
    }

    private void butZoomInActionPerformed(ActionEvent e) {
        ((ZoomPanel) imagePanel).zoomIn();
    }

    private void butZoomOutActionPerformed(ActionEvent e) {
        ((ZoomPanel) imagePanel).zoomOut();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Mahefa Manitrativo
        panel1 = new JPanel();
        butZoomIn = new JButton();
        butZoomOut = new JButton();
        scrollPane1 = new JScrollPane();
        imagePanel = new ZoomPanel(this.image);

        //======== this ========
        setBorder(Borders.DLU7);

        // JFormDesigner evaluation mark
        setBorder(new javax.swing.border.CompoundBorder(
                new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
                        "JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
                        javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
                        java.awt.Color.red), getBorder()));
        addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent e) {
                if ("border".equals(e.getPropertyName())) throw new RuntimeException();
            }
        });

        setLayout(new GridBagLayout());
        ((GridBagLayout) getLayout()).columnWidths = new int[]{0, 0};
        ((GridBagLayout) getLayout()).rowHeights = new int[]{0, 0, 0};
        ((GridBagLayout) getLayout()).columnWeights = new double[]{0.0, 1.0E-4};
        ((GridBagLayout) getLayout()).rowWeights = new double[]{0.0, 0.0, 1.0E-4};

        //======== panel1 ========
        {
            panel1.setLayout(new GridBagLayout());
            ((GridBagLayout) panel1.getLayout()).columnWidths = new int[]{0, 0, 0};
            ((GridBagLayout) panel1.getLayout()).rowHeights = new int[]{0, 0};
            ((GridBagLayout) panel1.getLayout()).columnWeights = new double[]{0.0, 0.0, 1.0E-4};
            ((GridBagLayout) panel1.getLayout()).rowWeights = new double[]{0.0, 1.0E-4};

            //---- butZoomIn ----
            butZoomIn.setText("Zoom In");
            butZoomIn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    butZoomInActionPerformed(e);
                }
            });
            panel1.add(butZoomIn, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 5), 0, 0));

            //---- butZoomOut ----
            butZoomOut.setText("Zoom out");
            butZoomOut.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    butZoomOutActionPerformed(e);
                }
            });
            panel1.add(butZoomOut, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 0), 0, 0));
        }
        add(panel1, new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0,
                GridBagConstraints.EAST, GridBagConstraints.VERTICAL,
                new Insets(0, 0, 5, 0), 0, 0));

        //======== scrollPane1 ========
        {
            scrollPane1.setPreferredSize(new Dimension(500, 500));

            //======== imagePanel ========
            {
                imagePanel.setLayout(new GridBagLayout());
                ((GridBagLayout) imagePanel.getLayout()).columnWidths = new int[]{0, 0};
                ((GridBagLayout) imagePanel.getLayout()).rowHeights = new int[]{0, 0};
                ((GridBagLayout) imagePanel.getLayout()).columnWeights = new double[]{0.0, 1.0E-4};
                ((GridBagLayout) imagePanel.getLayout()).rowWeights = new double[]{0.0, 1.0E-4};
            }
            scrollPane1.setViewportView(imagePanel);
        }
        add(scrollPane1, new GridBagConstraints(0, 1, 1, 1, 1.0, 1.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0), 0, 0));
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }
    // JFormDesigner - End of variables declaration  //GEN-END:variables


}

class ZoomPanel extends JPanel {
    private BufferedImage image;
    private double zoom = 1.0;
    private double dZoom = 0.1;

    public ZoomPanel(BufferedImage image) {
        this.image = image;
    }

    public void zoomIn() {
        zoom += dZoom;
        revalidate();
        repaint();
    }

    public void zoomOut() {
        zoom -= dZoom;
        revalidate();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        int w = getWidth();
        int h = getHeight();
        int imageWidth = image.getWidth();
        int imageHeight = image.getHeight();
        double x = (w - zoom * imageWidth) / 2;
        double y = (h - zoom * imageHeight) / 2;
        AffineTransform at = AffineTransform.getTranslateInstance(x, y);
        at.scale(zoom, zoom);
        g2.drawRenderedImage(image, at);
    }

    public Dimension getPreferredSize() {
        if (image != null) {
            int w = (int) (zoom * image.getWidth());
            int h = (int) (zoom * image.getHeight());
            return new Dimension(w, h);
        } else {
            return super.getPreferredSize();
        }
    }
}