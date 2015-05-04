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
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
    }

    public static void main(String[] args) {
        new EntryPoint();
    }

}
