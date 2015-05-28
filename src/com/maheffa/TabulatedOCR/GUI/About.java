package com.maheffa.TabulatedOCR.GUI;

import com.jgoodies.forms.factories.Borders;

import javax.swing.*;
import java.awt.*;
/*
 * Created by JFormDesigner on Tue May 19 19:36:31 MSK 2015
 */


/**
 * @author Mahefa Manitrativo
 */
public class About extends JPanel {
    public About() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Mahefa Manitrativo
        label1 = new JLabel();
        textArea2 = new JTextArea();
        label2 = new JLabel();
        textField2 = new JTextField();
        label3 = new JLabel();
        textArea1 = new JTextArea();

        //======== this ========
        setBorder(Borders.DLU14);

        // JFormDesigner evaluation mark
        setBorder(new javax.swing.border.CompoundBorder(
            new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
                "JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
                javax.swing.border.TitledBorder.BOTTOM, new Font("Dialog", Font.BOLD, 12),
                Color.red), getBorder())); addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

        setLayout(new GridBagLayout());
        ((GridBagLayout)getLayout()).columnWidths = new int[] {0, 0, 0};
        ((GridBagLayout)getLayout()).rowHeights = new int[] {0, 0, 0, 0, 0, 0};
        ((GridBagLayout)getLayout()).columnWeights = new double[] {0.0, 0.0, 1.0E-4};
        ((GridBagLayout)getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};

        //---- label1 ----
        label1.setText("\u0410\u0432\u0442\u043e\u0440:");
        label1.setHorizontalAlignment(SwingConstants.TRAILING);
        add(label1, new GridBagConstraints(0, 0, 1, 2, 1.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 10, 10), 0, 0));

        //---- textArea2 ----
        textArea2.setText("\u0421\u0442\u0443\u0434\u0435\u043d\u0442: \u041c\u0430\u043d\u0438\u0442\u0440\u0430\u0440\u0438\u0432\u0443 \u0410\u0434\u0430\u043c\u0430 \u041c\u0430\u044d\u0444\u0430\n\u0413\u0440\u0443\u043f\u043f\u0430: \u041f\u0412-51\n\u0412\u0423\u0417: \u0411\u0413\u0422\u0423 \u0438\u043c. \u0429\u0443\u0445\u043e\u0432\u0430\n\u0418\u043d\u0441\u0442\u0438\u0442\u0443\u0442: \u0418\u0418\u0422\u0423\u0421\n\u041a\u0430\u0444\u0435\u0434\u0440\u0430: \u041f\u041e\u0412\u0422\u0438\u0410\u0421");
        textArea2.setEditable(false);
        add(textArea2, new GridBagConstraints(1, 0, 1, 2, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 10, 0), 0, 0));

        //---- label2 ----
        label2.setText("\u0412\u0435\u0440\u0441\u0438\u044f:");
        label2.setHorizontalAlignment(SwingConstants.TRAILING);
        add(label2, new GridBagConstraints(0, 2, 1, 1, 1.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 10, 10), 0, 0));

        //---- textField2 ----
        textField2.setEditable(false);
        textField2.setText("1.0.0");
        add(textField2, new GridBagConstraints(1, 2, 1, 1, 1.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 10, 0), 0, 0));

        //---- label3 ----
        label3.setText("\u041a\u0440\u0430\u0442\u043a\u043e \u043e \u043f\u0440\u0438\u043b\u043e\u0436\u0435\u043d\u0438\u0438:");
        label3.setHorizontalAlignment(SwingConstants.TRAILING);
        add(label3, new GridBagConstraints(0, 3, 1, 2, 1.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 0, 10), 0, 0));

        //---- textArea1 ----
        textArea1.setEditable(false);
        textArea1.setText("\u041f\u0440\u0438\u043b\u043e\u0436\u0435\u043d\u0438\u0435 \u0434\u043b\u044f \u0430\u0444\u0442\u043e\u043c\u0430\u0442\u0438\u0447\u0435\u0441\u043a\u043e\u0433\u043e \n\u0440\u0430\u0441\u043f\u043e\u0437\u043d\u043e\u0432\u0430\u043d\u0438\u044f \u0442\u0435\u043a\u0441\u0442\u043e\u0432, \u0438 \u0438\u0441\u043a\u043b\u044e\u0447\u0435\u043d\u0438\u044f\n\u0438\u0437 \u043d\u0438\u0445 \u043a\u043b\u044e\u0447\u0435\u0432\u044b\u0435 \u0438\u043d\u0444\u043e\u0440\u043c\u0430\u0446\u0438\u0438");
        add(textArea1, new GridBagConstraints(1, 3, 1, 2, 1.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 0, 0), 0, 0));
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Mahefa Manitrativo
    private JLabel label1;
    private JTextArea textArea2;
    private JLabel label2;
    private JTextField textField2;
    private JLabel label3;
    private JTextArea textArea1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
