import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import com.jgoodies.forms.factories.*;
/*
 * Created by JFormDesigner on Thu May 14 09:38:20 MSK 2015
 */



/**
 * @author Mahefa Manitrativo
 */
public class ConfigurationParameter extends JPanel {
    public ConfigurationParameter() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Mahefa Manitrativo
        tabbedPane1 = new JTabbedPane();
        panel1 = new JPanel();
        panel5 = new JPanel();
        label2 = new JLabel();
        panel6 = new JPanel();
        textField1 = new JTextField();
        panel3 = new JPanel();
        panel9 = new JPanel();
        panel12 = new JPanel();
        label3 = new JLabel();
        label4 = new JLabel();
        panel13 = new JPanel();
        comboBox1 = new JComboBox();
        checkBox1 = new JCheckBox();
        panel10 = new JPanel();
        panel14 = new JPanel();
        label5 = new JLabel();
        label6 = new JLabel();
        panel15 = new JPanel();
        textField2 = new JTextField();
        textField3 = new JTextField();
        panel11 = new JPanel();
        panel17 = new JPanel();
        label7 = new JLabel();
        label8 = new JLabel();
        label9 = new JLabel();
        label10 = new JLabel();
        label11 = new JLabel();
        label12 = new JLabel();
        label13 = new JLabel();
        label14 = new JLabel();
        panel18 = new JPanel();
        textField4 = new JTextField();
        textField5 = new JTextField();
        textField6 = new JTextField();
        textField7 = new JTextField();
        textField8 = new JTextField();
        textField9 = new JTextField();
        textField10 = new JTextField();
        textField11 = new JTextField();
        panel4 = new JPanel();
        panel2 = new JPanel();
        label1 = new JLabel();
        txtConfigurationName = new JTextField();
        butSave = new JButton();

        //======== this ========
        setBorder(Borders.DLU4);

        // JFormDesigner evaluation mark
        setBorder(new javax.swing.border.CompoundBorder(
            new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
                "JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
                javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
                java.awt.Color.red), getBorder())); addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

        setLayout(new GridBagLayout());
        ((GridBagLayout)getLayout()).columnWidths = new int[] {0, 0};
        ((GridBagLayout)getLayout()).rowHeights = new int[] {0, 0, 0};
        ((GridBagLayout)getLayout()).columnWeights = new double[] {1.0, 1.0E-4};
        ((GridBagLayout)getLayout()).rowWeights = new double[] {1.0, 0.0, 1.0E-4};

        //======== tabbedPane1 ========
        {
            tabbedPane1.setBorder(new EmptyBorder(5, 5, 5, 5));

            //======== panel1 ========
            {
                panel1.setBorder(null);
                panel1.setLayout(new GridBagLayout());
                ((GridBagLayout)panel1.getLayout()).columnWidths = new int[] {0, 0, 0};
                ((GridBagLayout)panel1.getLayout()).rowHeights = new int[] {0, 0};
                ((GridBagLayout)panel1.getLayout()).columnWeights = new double[] {0.0, 0.0, 1.0E-4};
                ((GridBagLayout)panel1.getLayout()).rowWeights = new double[] {0.0, 1.0E-4};

                //======== panel5 ========
                {
                    panel5.setBorder(Borders.DLU2);
                    panel5.setLayout(new GridBagLayout());
                    ((GridBagLayout)panel5.getLayout()).columnWidths = new int[] {0, 0};
                    ((GridBagLayout)panel5.getLayout()).rowHeights = new int[] {0, 0, 0, 0};
                    ((GridBagLayout)panel5.getLayout()).columnWeights = new double[] {0.0, 1.0E-4};
                    ((GridBagLayout)panel5.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 1.0E-4};

                    //---- label2 ----
                    label2.setText("DPI:");
                    label2.setHorizontalAlignment(SwingConstants.CENTER);
                    panel5.add(label2, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 5, 0), 0, 0));
                }
                panel1.add(panel5, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0,
                    GridBagConstraints.EAST, GridBagConstraints.NONE,
                    new Insets(0, 0, 0, 5), 0, 0));

                //======== panel6 ========
                {
                    panel6.setBorder(Borders.DLU2);
                    panel6.setLayout(new GridBagLayout());
                    ((GridBagLayout)panel6.getLayout()).columnWidths = new int[] {0, 0};
                    ((GridBagLayout)panel6.getLayout()).rowHeights = new int[] {0, 0, 0, 0};
                    ((GridBagLayout)panel6.getLayout()).columnWeights = new double[] {0.0, 1.0E-4};
                    ((GridBagLayout)panel6.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 1.0E-4};
                    panel6.add(textField1, new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 5, 0), 0, 0));
                }
                panel1.add(panel6, new GridBagConstraints(1, 0, 1, 1, 1.0, 1.0,
                    GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                    new Insets(0, 0, 0, 0), 0, 0));
            }
            tabbedPane1.addTab("\u041e\u0431\u0449\u0435\u0435", panel1);

            //======== panel3 ========
            {
                panel3.setBorder(Borders.DLU4);
                panel3.setLayout(new GridBagLayout());
                ((GridBagLayout)panel3.getLayout()).columnWidths = new int[] {0, 0};
                ((GridBagLayout)panel3.getLayout()).rowHeights = new int[] {0, 0, 0, 0};
                ((GridBagLayout)panel3.getLayout()).columnWeights = new double[] {0.0, 1.0E-4};
                ((GridBagLayout)panel3.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 1.0E-4};

                //======== panel9 ========
                {
                    panel9.setBorder(new CompoundBorder(
                        new TitledBorder("\u0411\u0438\u043d\u0430\u0440\u0438\u0437\u0430\u0446\u0438\u044f"),
                        Borders.DLU2));
                    panel9.setLayout(new GridBagLayout());
                    ((GridBagLayout)panel9.getLayout()).columnWidths = new int[] {0, 0, 0};
                    ((GridBagLayout)panel9.getLayout()).rowHeights = new int[] {0, 0, 0};
                    ((GridBagLayout)panel9.getLayout()).columnWeights = new double[] {0.0, 0.0, 1.0E-4};
                    ((GridBagLayout)panel9.getLayout()).rowWeights = new double[] {0.0, 0.0, 1.0E-4};

                    //======== panel12 ========
                    {
                        panel12.setLayout(new GridBagLayout());
                        ((GridBagLayout)panel12.getLayout()).columnWidths = new int[] {0, 0};
                        ((GridBagLayout)panel12.getLayout()).rowHeights = new int[] {0, 0, 0};
                        ((GridBagLayout)panel12.getLayout()).columnWeights = new double[] {0.0, 1.0E-4};
                        ((GridBagLayout)panel12.getLayout()).rowWeights = new double[] {0.0, 0.0, 1.0E-4};

                        //---- label3 ----
                        label3.setText("\u041c\u0435\u0442\u043e\u0434 \u0434\u043b\u044f \u043f\u0440\u0435\u043e\u0431\u0440\u0430\u0437\u043e\u0432\u0430\u043d\u0438\u044f \u0432 \u0447\u0451\u0440\u043d\u043e-\u0431\u0435\u043b\u043e\u0435");
                        panel12.add(label3, new GridBagConstraints(0, 0, 1, 1, 0.0, 1.0,
                            GridBagConstraints.EAST, GridBagConstraints.VERTICAL,
                            new Insets(0, 0, 5, 0), 0, 0));

                        //---- label4 ----
                        label4.setText("\u041f\u0440\u0435\u043e\u0431\u0440\u0430\u0437\u043e\u0432\u0430\u0442\u044c \u0438\u0437\u043e\u0431\u0440\u0430\u0436\u0435\u043d\u0438\u0435 \u0432 \u0431\u0438\u043d\u0430\u0440\u043d\u043e\u0435");
                        panel12.add(label4, new GridBagConstraints(0, 1, 1, 1, 0.0, 1.0,
                            GridBagConstraints.EAST, GridBagConstraints.VERTICAL,
                            new Insets(0, 0, 0, 0), 0, 0));
                    }
                    panel9.add(panel12, new GridBagConstraints(0, 1, 1, 1, 1.0, 0.0,
                        GridBagConstraints.EAST, GridBagConstraints.VERTICAL,
                        new Insets(0, 0, 0, 5), 0, 0));

                    //======== panel13 ========
                    {
                        panel13.setLayout(new GridBagLayout());
                        ((GridBagLayout)panel13.getLayout()).columnWidths = new int[] {0, 0};
                        ((GridBagLayout)panel13.getLayout()).rowHeights = new int[] {0, 0, 0};
                        ((GridBagLayout)panel13.getLayout()).columnWeights = new double[] {0.0, 1.0E-4};
                        ((GridBagLayout)panel13.getLayout()).rowWeights = new double[] {0.0, 0.0, 1.0E-4};

                        //---- comboBox1 ----
                        comboBox1.setModel(new DefaultComboBoxModel(new String[] {
                            "Average",
                            "Luminosity",
                            "Lightness"
                        }));
                        comboBox1.setSelectedIndex(1);
                        panel13.add(comboBox1, new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0,
                            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                            new Insets(0, 0, 5, 0), 0, 0));

                        //---- checkBox1 ----
                        checkBox1.setSelected(true);
                        panel13.add(checkBox1, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
                            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                            new Insets(0, 0, 0, 0), 0, 0));
                    }
                    panel9.add(panel13, new GridBagConstraints(1, 1, 1, 1, 1.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 0, 0), 0, 0));
                }
                panel3.add(panel9, new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 10, 0), 0, 0));

                //======== panel10 ========
                {
                    panel10.setBorder(new CompoundBorder(
                        new TitledBorder("\u0421\u043c\u0435\u0436\u043d\u043e\u0441\u0442\u044c \u043f\u0438\u043a\u0441\u0435\u043b\u0435\u0439"),
                        Borders.DLU2));
                    panel10.setLayout(new GridBagLayout());
                    ((GridBagLayout)panel10.getLayout()).columnWidths = new int[] {0, 0, 0};
                    ((GridBagLayout)panel10.getLayout()).rowHeights = new int[] {0, 0};
                    ((GridBagLayout)panel10.getLayout()).columnWeights = new double[] {0.0, 0.0, 1.0E-4};
                    ((GridBagLayout)panel10.getLayout()).rowWeights = new double[] {0.0, 1.0E-4};

                    //======== panel14 ========
                    {
                        panel14.setLayout(new GridBagLayout());
                        ((GridBagLayout)panel14.getLayout()).columnWidths = new int[] {0, 0};
                        ((GridBagLayout)panel14.getLayout()).rowHeights = new int[] {0, 0, 0};
                        ((GridBagLayout)panel14.getLayout()).columnWeights = new double[] {0.0, 1.0E-4};
                        ((GridBagLayout)panel14.getLayout()).rowWeights = new double[] {0.0, 0.0, 1.0E-4};

                        //---- label5 ----
                        label5.setText("\u0420\u0430\u0434\u0438\u0443\u0441 \u0441\u043c\u0435\u0436\u043d\u043e\u0441\u0442\u0438 (px):");
                        panel14.add(label5, new GridBagConstraints(0, 0, 1, 1, 0.0, 1.0,
                            GridBagConstraints.EAST, GridBagConstraints.VERTICAL,
                            new Insets(0, 0, 5, 0), 0, 0));

                        //---- label6 ----
                        label6.setText("\u041c\u0438\u043d\u0438\u043c\u0430\u043b\u044c\u043d\u043e\u0435 \u0440\u0430\u0441\u0442\u043e\u044f\u043d\u0438\u0435 \u043c\u0435\u0436\u0434\u0443 \u0434\u0432\u0443\u043c\u044f \u0431\u0443\u043a\u0432\u0430\u043c\u0438 (px):");
                        panel14.add(label6, new GridBagConstraints(0, 1, 1, 1, 0.0, 1.0,
                            GridBagConstraints.EAST, GridBagConstraints.VERTICAL,
                            new Insets(0, 0, 0, 0), 0, 0));
                    }
                    panel10.add(panel14, new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0,
                        GridBagConstraints.EAST, GridBagConstraints.VERTICAL,
                        new Insets(0, 0, 0, 5), 0, 0));

                    //======== panel15 ========
                    {
                        panel15.setLayout(new GridBagLayout());
                        ((GridBagLayout)panel15.getLayout()).columnWidths = new int[] {0, 0};
                        ((GridBagLayout)panel15.getLayout()).rowHeights = new int[] {0, 0, 0};
                        ((GridBagLayout)panel15.getLayout()).columnWeights = new double[] {0.0, 1.0E-4};
                        ((GridBagLayout)panel15.getLayout()).rowWeights = new double[] {0.0, 0.0, 1.0E-4};
                        panel15.add(textField2, new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0,
                            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                            new Insets(0, 0, 5, 0), 0, 0));
                        panel15.add(textField3, new GridBagConstraints(0, 1, 1, 1, 1.0, 0.0,
                            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                            new Insets(0, 0, 0, 0), 0, 0));
                    }
                    panel10.add(panel15, new GridBagConstraints(1, 0, 1, 1, 1.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 0, 0), 0, 0));
                }
                panel3.add(panel10, new GridBagConstraints(0, 1, 1, 1, 1.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 10, 0), 0, 0));

                //======== panel11 ========
                {
                    panel11.setBorder(new CompoundBorder(
                        new TitledBorder("\u041d\u0430\u0445\u043e\u0436\u0434\u0435\u043d\u0438\u0435 \u043b\u0438\u043d\u0438\u0438"),
                        Borders.DLU2));
                    panel11.setLayout(new GridBagLayout());
                    ((GridBagLayout)panel11.getLayout()).columnWidths = new int[] {0, 0, 0};
                    ((GridBagLayout)panel11.getLayout()).rowHeights = new int[] {0, 0};
                    ((GridBagLayout)panel11.getLayout()).columnWeights = new double[] {0.0, 0.0, 1.0E-4};
                    ((GridBagLayout)panel11.getLayout()).rowWeights = new double[] {0.0, 1.0E-4};

                    //======== panel17 ========
                    {
                        panel17.setLayout(new GridBagLayout());
                        ((GridBagLayout)panel17.getLayout()).columnWidths = new int[] {0, 0};
                        ((GridBagLayout)panel17.getLayout()).rowHeights = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0};
                        ((GridBagLayout)panel17.getLayout()).columnWeights = new double[] {0.0, 1.0E-4};
                        ((GridBagLayout)panel17.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};

                        //---- label7 ----
                        label7.setText("\u041f\u0440\u0435\u043e\u0431\u0440\u0430\u0437\u043e\u0432\u0430\u043d\u0438\u0435 Canny - \u041f\u0435\u0440\u0432\u044b\u0439 \u043f\u043e\u0440\u043e\u0433 (px):");
                        panel17.add(label7, new GridBagConstraints(0, 0, 1, 1, 0.0, 1.0,
                            GridBagConstraints.EAST, GridBagConstraints.VERTICAL,
                            new Insets(0, 0, 5, 0), 0, 0));

                        //---- label8 ----
                        label8.setText("\u041f\u0440\u0435\u043e\u0431\u0440\u0430\u0437\u043e\u0432\u0430\u043d\u0438\u0435 Canny - \u0412\u0442\u043e\u0440\u043e\u0439 \u043f\u043e\u0440\u043e\u0433 (px):");
                        panel17.add(label8, new GridBagConstraints(0, 1, 1, 1, 0.0, 1.0,
                            GridBagConstraints.EAST, GridBagConstraints.VERTICAL,
                            new Insets(0, 0, 5, 0), 0, 0));

                        //---- label9 ----
                        label9.setText("\u041f\u0440\u0435\u043e\u0431\u0440\u0430\u0437\u043e\u0432\u0430\u043d\u0438\u0435 \u0425\u0430\u0444\u0430 - \u041f\u0440\u0438\u0440\u0430\u0449\u0435\u043d\u0438\u0435 \u0440\u0430\u0441\u0442\u043e\u044f\u043d\u0438\u044f (px):");
                        panel17.add(label9, new GridBagConstraints(0, 2, 1, 1, 0.0, 1.0,
                            GridBagConstraints.EAST, GridBagConstraints.VERTICAL,
                            new Insets(0, 0, 5, 0), 0, 0));

                        //---- label10 ----
                        label10.setText("\u041f\u0440\u0435\u043e\u0431\u0440\u0430\u0437\u043e\u0432\u0430\u043d\u0438\u0435 \u0425\u0430\u0444\u0430 - \u041f\u0440\u0438\u0440\u0430\u0449\u0435\u043d\u0438\u0435 \u0443\u0433\u043b\u0430 (\u0433\u0440\u0430\u0434):");
                        panel17.add(label10, new GridBagConstraints(0, 3, 1, 1, 0.0, 1.0,
                            GridBagConstraints.EAST, GridBagConstraints.VERTICAL,
                            new Insets(0, 0, 5, 0), 0, 0));

                        //---- label11 ----
                        label11.setText("\u041f\u0440\u0435\u043e\u0431\u0440\u0430\u0437\u043e\u0432\u0430\u043d\u0438\u0435 \u0425\u0430\u0444\u0430 - \u041f\u043e\u0440\u043e\u0433 \u043f\u0440\u0438\u0440\u0430\u0449\u0435\u043d\u0438\u0438: ");
                        panel17.add(label11, new GridBagConstraints(0, 4, 1, 1, 0.0, 1.0,
                            GridBagConstraints.EAST, GridBagConstraints.VERTICAL,
                            new Insets(0, 0, 5, 0), 0, 0));

                        //---- label12 ----
                        label12.setText("\u041f\u0440\u0435\u043e\u0431\u0440\u0430\u0437\u043e\u0432\u0430\u043d\u0438\u0435 \u0425\u0430\u0444\u0430 - \u041c\u0438\u043d\u0438\u043c\u0430\u043b\u044c\u043d\u0430\u044f \u0434\u043b\u0438\u043d\u0430 \u043b\u0438\u043d\u0438\u0438 (px):");
                        panel17.add(label12, new GridBagConstraints(0, 5, 1, 1, 0.0, 1.0,
                            GridBagConstraints.EAST, GridBagConstraints.VERTICAL,
                            new Insets(0, 0, 5, 0), 0, 0));

                        //---- label13 ----
                        label13.setText("\u041f\u0440\u0435\u043e\u0431\u0440\u0430\u0437\u043e\u0432\u0430\u043d\u0438\u0435 \u0425\u0430\u0444\u0430 - \u041c\u0430\u043a\u0441\u0438\u043c\u0430\u043b\u044c\u043d\u043e\u0435 \u0440\u0430\u0441\u0441\u0442\u043e\u044f\u043d\u0438\u0435 \u043c\u0435\u0436\u0434\u0443 \u043b\u0438\u043d\u044f\u043c\u0438 (px):");
                        panel17.add(label13, new GridBagConstraints(0, 6, 1, 1, 0.0, 1.0,
                            GridBagConstraints.EAST, GridBagConstraints.VERTICAL,
                            new Insets(0, 0, 5, 0), 0, 0));

                        //---- label14 ----
                        label14.setText("\u041c\u0430\u043a\u0441\u0438\u043c\u0430\u043b\u044c\u043d\u043e\u0435 \u043d\u0430\u043a\u043b\u043e\u043d\u0435\u043d\u0438\u0435 \u043b\u0438\u043d\u0438\u0438 (\u0433\u0440\u0430\u0434):");
                        panel17.add(label14, new GridBagConstraints(0, 7, 1, 1, 0.0, 1.0,
                            GridBagConstraints.EAST, GridBagConstraints.VERTICAL,
                            new Insets(0, 0, 0, 0), 0, 0));
                    }
                    panel11.add(panel17, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                        GridBagConstraints.EAST, GridBagConstraints.VERTICAL,
                        new Insets(0, 0, 0, 5), 0, 0));

                    //======== panel18 ========
                    {
                        panel18.setLayout(new GridBagLayout());
                        ((GridBagLayout)panel18.getLayout()).columnWidths = new int[] {0, 0};
                        ((GridBagLayout)panel18.getLayout()).rowHeights = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0};
                        ((GridBagLayout)panel18.getLayout()).columnWeights = new double[] {0.0, 1.0E-4};
                        ((GridBagLayout)panel18.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};

                        //---- textField4 ----
                        textField4.setMinimumSize(new Dimension(50, 27));
                        textField4.setPreferredSize(new Dimension(50, 27));
                        panel18.add(textField4, new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0,
                            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                            new Insets(0, 0, 5, 0), 0, 0));

                        //---- textField5 ----
                        textField5.setMinimumSize(new Dimension(50, 27));
                        textField5.setPreferredSize(new Dimension(50, 27));
                        panel18.add(textField5, new GridBagConstraints(0, 1, 1, 1, 1.0, 0.0,
                            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                            new Insets(0, 0, 5, 0), 0, 0));

                        //---- textField6 ----
                        textField6.setMinimumSize(new Dimension(50, 27));
                        textField6.setPreferredSize(new Dimension(50, 27));
                        panel18.add(textField6, new GridBagConstraints(0, 2, 1, 1, 1.0, 0.0,
                            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                            new Insets(0, 0, 5, 0), 0, 0));

                        //---- textField7 ----
                        textField7.setMinimumSize(new Dimension(50, 27));
                        textField7.setPreferredSize(new Dimension(50, 27));
                        panel18.add(textField7, new GridBagConstraints(0, 3, 1, 1, 1.0, 0.0,
                            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                            new Insets(0, 0, 5, 0), 0, 0));

                        //---- textField8 ----
                        textField8.setMinimumSize(new Dimension(50, 27));
                        textField8.setPreferredSize(new Dimension(50, 27));
                        panel18.add(textField8, new GridBagConstraints(0, 4, 1, 1, 1.0, 0.0,
                            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                            new Insets(0, 0, 5, 0), 0, 0));

                        //---- textField9 ----
                        textField9.setMinimumSize(new Dimension(50, 27));
                        textField9.setPreferredSize(new Dimension(50, 27));
                        panel18.add(textField9, new GridBagConstraints(0, 5, 1, 1, 1.0, 0.0,
                            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                            new Insets(0, 0, 5, 0), 0, 0));

                        //---- textField10 ----
                        textField10.setMinimumSize(new Dimension(50, 27));
                        textField10.setPreferredSize(new Dimension(50, 27));
                        panel18.add(textField10, new GridBagConstraints(0, 6, 1, 1, 1.0, 0.0,
                            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                            new Insets(0, 0, 5, 0), 0, 0));

                        //---- textField11 ----
                        textField11.setMinimumSize(new Dimension(50, 27));
                        textField11.setPreferredSize(new Dimension(50, 27));
                        panel18.add(textField11, new GridBagConstraints(0, 7, 1, 1, 1.0, 0.0,
                            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                            new Insets(0, 0, 0, 0), 0, 0));
                    }
                    panel11.add(panel18, new GridBagConstraints(1, 0, 1, 1, 1.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 0, 0), 0, 0));
                }
                panel3.add(panel11, new GridBagConstraints(0, 2, 1, 1, 1.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 0), 0, 0));
            }
            tabbedPane1.addTab("\u041f\u0440\u0435\u043f\u0440\u043e\u0446\u0435\u0441\u0441\u043e\u0440", panel3);

            //======== panel4 ========
            {
                panel4.setLayout(new GridBagLayout());
                ((GridBagLayout)panel4.getLayout()).columnWidths = new int[] {0, 0, 0};
                ((GridBagLayout)panel4.getLayout()).rowHeights = new int[] {0, 0, 0, 0};
                ((GridBagLayout)panel4.getLayout()).columnWeights = new double[] {0.0, 0.0, 1.0E-4};
                ((GridBagLayout)panel4.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 1.0E-4};
            }
            tabbedPane1.addTab("\u0420\u0430\u0441\u043f\u043e\u0437\u043d\u043e\u0432\u0430\u043d\u0438\u0435", panel4);
        }
        add(tabbedPane1, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 0, 0), 0, 0));

        //======== panel2 ========
        {
            panel2.setBorder(Borders.DLU2);
            panel2.setLayout(new GridBagLayout());
            ((GridBagLayout)panel2.getLayout()).columnWidths = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            ((GridBagLayout)panel2.getLayout()).rowHeights = new int[] {0, 0};
            ((GridBagLayout)panel2.getLayout()).columnWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};
            ((GridBagLayout)panel2.getLayout()).rowWeights = new double[] {0.0, 1.0E-4};

            //---- label1 ----
            label1.setText("\u041d\u0430\u0437\u0432\u0430\u043d\u0438\u0435:");
            panel2.add(label1, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 5), 0, 0));

            //---- txtConfigurationName ----
            txtConfigurationName.setMinimumSize(new Dimension(150, 27));
            txtConfigurationName.setPreferredSize(new Dimension(200, 27));
            panel2.add(txtConfigurationName, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 5), 0, 0));

            //---- butSave ----
            butSave.setText("\u0421\u043e\u0445\u0440\u0430\u043d\u0438\u0442\u044c");
            butSave.setHorizontalAlignment(SwingConstants.TRAILING);
            panel2.add(butSave, new GridBagConstraints(11, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.EAST, GridBagConstraints.VERTICAL,
                new Insets(0, 0, 0, 0), 0, 0));
        }
        add(panel2, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 0, 0), 0, 0));
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Mahefa Manitrativo
    private JTabbedPane tabbedPane1;
    private JPanel panel1;
    private JPanel panel5;
    private JLabel label2;
    private JPanel panel6;
    private JTextField textField1;
    private JPanel panel3;
    private JPanel panel9;
    private JPanel panel12;
    private JLabel label3;
    private JLabel label4;
    private JPanel panel13;
    private JComboBox comboBox1;
    private JCheckBox checkBox1;
    private JPanel panel10;
    private JPanel panel14;
    private JLabel label5;
    private JLabel label6;
    private JPanel panel15;
    private JTextField textField2;
    private JTextField textField3;
    private JPanel panel11;
    private JPanel panel17;
    private JLabel label7;
    private JLabel label8;
    private JLabel label9;
    private JLabel label10;
    private JLabel label11;
    private JLabel label12;
    private JLabel label13;
    private JLabel label14;
    private JPanel panel18;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JTextField textField7;
    private JTextField textField8;
    private JTextField textField9;
    private JTextField textField10;
    private JTextField textField11;
    private JPanel panel4;
    private JPanel panel2;
    private JLabel label1;
    private JTextField txtConfigurationName;
    private JButton butSave;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
