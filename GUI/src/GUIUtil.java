// File:    GUIUtil.java
// Created: 02/05/2015

import javax.swing.*;

/**
 * @author mahefa
 */
public class GUIUtil {

    public GUIUtil() {
    }

    public static JFrame createFrameForPanel(JPanel panel) {
        JFrame frame = new JFrame();
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
        return frame;
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
}
