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
        try {
            UIManager.setLookAndFeel("com.jtattoo.plaf.aero.AeroLookAndFeel");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        new EntryPoint();
    }

}
