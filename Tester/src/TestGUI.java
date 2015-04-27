// File:    TestGUI.java
// Created: 27/04/2015

import javax.swing.*;

/**
 * @author mahefa
 */
public class TestGUI {

    public TestGUI() {
    }

    public static void main(String[] args) {
        CreateFormatForm form = new CreateFormatForm();
        JFrame frame = new JFrame("Hey watsup");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(form);
        frame.pack();
        frame.setVisible(true);
    }
}
