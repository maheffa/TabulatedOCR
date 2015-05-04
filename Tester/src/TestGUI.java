// File:    TestGUI.java
// Created: 27/04/2015

import javax.swing.*;

/**
 * @author mahefa
 */
public class TestGUI {

    public TestGUI() {
    }

//    public static void testOcrMainForm() {
//        OcrMainForm ocr = new OcrMainForm();
//        JFrame frame = ocr.getMainFrame();
//        frame.pack();
//        frame.setVisible(true);
//    }

    public static void testJPanel(JPanel panel) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }

    public static void launchApp() {
        OcrMainForm ocr = new OcrMainForm(Parameters.getInstance());
    }

    public static void main(String[] args) {

//        launchApp();
        testJPanel(new CreateFormatForm("Bomba"));
    }


}
