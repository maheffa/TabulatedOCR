// File:    EntryPoint.java
// Created: 19/02/2015


import javax.swing.*;

/**
 * @author mahefa
 */
public class EntryPoint {
    private Parameters parameter;
    private OcrMainForm ocrMainForm;

    public EntryPoint() {
        parameter = Parameters.getInstance();
        ocrMainForm = new OcrMainForm(parameter);
        System.out.println("Parameters filepath " + parameter.getProjectPath());
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
