// File:    EntryPoint.java
// Created: 19/02/2015


import javax.swing.*;

/**
 * @author mahefa
 */
public class EntryPoint {
    private Parameters parameters;
    private OcrMainForm ocrMainForm;

    public EntryPoint() {
        parameters = Parameters.getInstance();
        ocrMainForm = new OcrMainForm();
        System.out.println("Parameters filepath " + parameters.getProjectPath());
        launchGUI();
    }

    private void launchGUI() {
        JFrame frame = ocrMainForm.getMainFrame();
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new EntryPoint();
    }

}
