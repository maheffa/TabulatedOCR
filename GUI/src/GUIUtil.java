// File:    GUIUtil.java
// Created: 02/05/2015

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author mahefa
 */
public class GUIUtil {

    public GUIUtil() {
    }

    public static void close(JPanel panel) {
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(panel);
        frame.setVisible(false);
        frame.dispose();
    }

    public static JFrame createFrameForPanel(String title, JPanel panel) {
        JFrame frame = new JFrame(title);
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        return frame;
    }

    public static void setButtonIcon(JButton btn, String iconPath) {
        Image icon;
        try {
            icon = ImageIO.read(new File(iconPath));
            Image scaled = icon.getScaledInstance(15, 15, java.awt.Image.SCALE_SMOOTH);
            btn.setIcon(new ImageIcon(scaled));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String createHtmlFormattedText(String[] propreties, String[] values) {
        StringBuilder str = new StringBuilder();
        str.append("<html>");
        for (int i = 0; i < propreties.length; i++) {
            str.append("<b>").append(propreties[i]).append(": ").append("</b>");
            str.append(values[i]).append("<br/>");
        }
        str.append("</html>");
        return str.toString();
    }

    public static void drawImageOnPanel(JPanel pane, BufferedImage img) {
        Graphics g = pane.getGraphics();
        g.drawImage(img, 0, 0, pane.getWidth(), pane.getHeight(), Color.WHITE, null);
    }
}
