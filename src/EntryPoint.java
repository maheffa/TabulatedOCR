// File:    EntryPoint.java
// Created: 19/02/2015


import com.jgoodies.looks.plastic.Plastic3DLookAndFeel;
import com.jgoodies.looks.plastic.PlasticXPLookAndFeel;
import com.sun.java.swing.plaf.gtk.GTKLookAndFeel;
import com.sun.java.swing.plaf.motif.MotifLookAndFeel;
import com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel;
import com.sun.java.swing.plaf.windows.WindowsLookAndFeel;

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
        ocrMainForm.drawImage(ImgProcUtil.readImage("Test/bra1.png"));
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
        try {
            UIManager.setLookAndFeel(new GTKLookAndFeel());
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        new EntryPoint();
    }

}
