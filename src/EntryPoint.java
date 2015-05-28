// File:    EntryPoint.java
// Created: 19/02/2015


import org.opencv.core.Core;

import javax.swing.*;

/**
 * @author mahefa
 */
public class EntryPoint {
    private OcrMainForm ocrMainForm;

    public EntryPoint(OcrMainForm ocrMainForm) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        this.ocrMainForm = ocrMainForm;
        launchGUI();
    }

    private void launchGUI() {
        final JFrame frame = ocrMainForm.getMainFrame();
        frame.pack();
        frame.setVisible(true);
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                if (JOptionPane.showConfirmDialog(frame,
                        "Вы действительно хотели закрыть?", "Закрыть приложение",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
                    System.exit(0);
                }
            }
        });
        frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
    }

    public static void main(String[] args) {
//        javax.swing.plaf.nimbus.NimbusLo/okAndFeel
//        try {
//            jUIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
//        try {
//            UIManager.setLookAndFeel(new GTKLookAndFeel());
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (UnsupportedLookAndFeelException e) {
//            e.printStackTrace();
//        }
        new EntryPoint(new OcrMainForm());
    }

}
