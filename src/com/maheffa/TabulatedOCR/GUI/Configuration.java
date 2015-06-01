package com.maheffa.TabulatedOCR.GUI;

import com.jgoodies.forms.factories.Borders;
import com.maheffa.TabulatedOCR.DBManager.DBAccess;
import com.maheffa.TabulatedOCR.DBManager.Ocrconfig;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;
/*
 * Created by JFormDesigner on Tue May 19 22:56:23 MSK 2015
 */


/**
 * @author Mahefa Manitrativo
 */
public class Configuration extends JPanel {
    private List<Ocrconfig> listConfig = null;
    private DefaultComboBoxModel<String> comboBoxModel = null;
    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Mahefa Manitrativo
    private JTabbedPane tabbedPane1;
    private JPanel panel1;
    private JPanel panel4;
    private JLabel label2;
    private JTextField txtDPI;
    private JLabel label15;
    private JComboBox comboLang;
    private JLabel label19;
    private JCheckBox checkNoiseRemoveTesseract;
    private JLabel label21;
    private JCheckBox checkDictionary;
    private JPanel panel9;
    private JPanel panel12;
    private JLabel label3;
    private JLabel label4;
    private JPanel panel13;
    private JComboBox comboGrayscale;
    private JCheckBox checkBinary;
    private JPanel panel6;
    private JLabel label20;
    private JCheckBox checkDeskew;
    private JLabel label14;
    private JTextField txtMaxAngleTolerance;
    private JPanel panel3;
    private JPanel panel10;
    private JPanel panel14;
    private JLabel label5;
    private JLabel label6;
    private JPanel panel15;
    private JTextField txtRadius;
    private JTextField txtMargin;
    private JPanel panel11;
    private JPanel panel17;
    private JLabel label7;
    private JLabel label8;
    private JLabel label9;
    private JLabel label10;
    private JLabel label11;
    private JLabel label12;
    private JLabel label13;
    private JPanel panel18;
    private JTextField txtThreshold1;
    private JTextField txtThreshold2;
    private JTextField txtDistanceAccumulation;
    private JTextField txtAngleAccumulation;
    private JTextField txtAccumulationThreshold;
    private JTextField txtMinimumLineLength;
    private JTextField txtMaxGap;
    private JPanel panel5;
    private JLabel label16;
    private JTextField txtMaxLineDistance;
    private JLabel label17;
    private JTextField txtMaxLineGap;
    private JLabel label18;
    private JTextField txtThickness;
    private JPanel panel2;
    private JLabel label1;
    private JTextField txtConfigurationName;
    private JButton butSave;
    private JPanel panel7;
    private JButton butDelete;
    private JComboBox comboConfigList;
    public Configuration() {
        initComponents();

        // setting list config
        listConfig = DBAccess.getDbAccess().listConfiguration();
        comboBoxModel = new DefaultComboBoxModel<String>();
        for (Ocrconfig ocrconfig : listConfig) {
            comboBoxModel.addElement(ocrconfig.getName());
        }
        comboConfigList.setModel(comboBoxModel);
        comboConfigList.setSelectedIndex(0);

        // filling forms
        updateGUIConfig();
    }

    public void updateGUIConfig() {
        int selected = comboConfigList.getSelectedIndex();
        Ocrconfig conf = listConfig.get(selected);
        // setting name
        this.txtConfigurationName.setText(conf.getName());
        // setting DPI
        this.txtDPI.setText(String.valueOf(conf.getDpi()));
        // setting language
        ComboBoxModel<String> langModel = this.comboLang.getModel();
        for (int i = 0; i < langModel.getSize(); i++) {
            if ((langModel.getElementAt(i)).contentEquals(conf.getLanguage())) {
                this.comboLang.setSelectedIndex(i);
                break;
            }
        }
        //  setting grayscale method
        this.comboGrayscale.setSelectedIndex(conf.getGrayscale());
        // setting binary
        this.checkBinary.setSelected(conf.getBinarisation());
        // setting radius
        this.txtRadius.setText(String.valueOf(conf.getRadius()));
        // setting margin
        this.txtMargin.setText(String.valueOf(conf.getMargin()));
        // setting thrsld1
        this.txtThreshold1.setText(String.valueOf(conf.getThreshold1()));
        // setting thrsld2
        this.txtThreshold2.setText(String.valueOf(conf.getThreshold2()));
        // setting distance accumulation
        this.txtDistanceAccumulation.setText(String.valueOf(conf.getDistanceAccumulation()));
        // setting radius accumulation
        this.txtAngleAccumulation.setText(String.valueOf(conf.getAngleAccumulation()));
        // setting accumulation threshold
        this.txtAccumulationThreshold.setText(String.valueOf(conf.getThresholdAccumulation()));
        // setting minumal line length
        this.txtMinimumLineLength.setText(String.valueOf(conf.getMinLenght()));
        // setting max gap between lines
        this.txtMaxGap.setText(String.valueOf(conf.getMaxGap()));
        // setting max angle tolerance
        this.txtMaxAngleTolerance.setText(String.valueOf(conf.getTolerableSkewAngle()));
        // setting max distance between line
        this.txtMaxLineDistance.setText(String.valueOf(conf.getMaxLineDistance()));
        // setting max line gap
        this.txtMaxLineGap.setText(String.valueOf(conf.getMaxLineGap()));
        // setting line thickness
        this.txtThickness.setText(String.valueOf(conf.getLineThickness()));
        // setting deskew
        this.checkDeskew.setSelected(conf.getDeskew());
        // setting denoise
        this.checkNoiseRemoveTesseract.setSelected(conf.getDenoise());
        // setting user dictionary use
        this.checkDictionary.setSelected(conf.getUserDictionary());

        // saving current configuration
        DBAccess.setCurrentConfiguration(conf);
    }

    private void butSaveConfigActionPerformed(ActionEvent e) {
        Ocrconfig conf;
        boolean update;
        if (this.txtConfigurationName.getText().contentEquals((String)this.comboConfigList.getSelectedItem())) {
            conf = this.listConfig.get(this.comboConfigList.getSelectedIndex());
            update = true;
        } else {
            conf = new Ocrconfig();
            update = false;
        }
        // setting name
        conf.setName(this.txtConfigurationName.getText());
        // setting DPI
        conf.setDpi(Integer.valueOf(txtDPI.getText()));
        // setting language
        conf.setLanguage((String) comboLang.getSelectedItem());
        //  setting grayscale method
        conf.setGrayscale(comboGrayscale.getSelectedIndex());
        // setting binary
        conf.setBinarisation(checkBinary.isSelected());
        // setting radius
        conf.setRadius(Integer.valueOf(txtRadius.getText()));
        // setting margin
        conf.setMargin(Integer.valueOf(txtMargin.getText()));
        // setting thrsld1
        conf.setThreshold1(Integer.valueOf(txtThreshold1.getText()));
        // setting thrsld2
        conf.setThreshold2(Integer.valueOf(txtThreshold2.getText()));
        // setting distance accumulation
        conf.setDistanceAccumulation(Integer.valueOf(txtDistanceAccumulation.getText()));
        // setting radius accumulation
        conf.setAngleAccumulation(Double.valueOf(txtAngleAccumulation.getText()));
        // setting accumulation threshold
        conf.setThresholdAccumulation(Integer.valueOf(txtAccumulationThreshold.getText()));
        // setting minumal line length
        conf.setMinLenght(Integer.valueOf(txtMinimumLineLength.getText()));
        // setting max gap between lines
        conf.setMaxGap(Integer.valueOf(txtMaxGap.getText()));
        // setting max angle tolerance
        conf.setTolerableSkewAngle(Double.valueOf(txtMaxAngleTolerance.getText()));
        // setting max distance between line
        conf.setMaxLineDistance(Integer.valueOf(txtMaxLineDistance.getText()));
        // setting max line gap
        conf.setMaxLineGap(Integer.valueOf(txtMaxLineGap.getText()));
        // setting line thickness
        conf.setLineThickness(Integer.valueOf(txtThickness.getText()));
        // setting deskew
        conf.setDeskew(checkDeskew.isSelected());
        // setting denoise
        conf.setDenoise(checkNoiseRemoveTesseract.isSelected());
        // setting user dictionary use
        conf.setUserDictionary(checkDictionary.isSelected());

        DBAccess dbAccess = DBAccess.getDbAccess();
        if (update) {
            dbAccess.updateEntry(conf);
        } else {
            dbAccess.addEntry(conf);
        }
        DBAccess.setCurrentConfiguration(conf);
        GUIUtil.close(this);
    }

    private void comboConfigListItemStateChanged(ItemEvent e) {
        updateGUIConfig();
    }

    private void butDeleteActionPerformed(ActionEvent e) {
        Ocrconfig conf = listConfig.get(comboConfigList.getSelectedIndex());
        DBAccess.getDbAccess().deleteEntry(conf);
        comboBoxModel.removeElementAt(comboConfigList.getSelectedIndex());
        comboConfigList.setSelectedIndex(0);
        listConfig = DBAccess.getDbAccess().listConfiguration();
        updateGUIConfig();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Mahefa Manitrativo
        tabbedPane1 = new JTabbedPane();
        panel1 = new JPanel();
        panel4 = new JPanel();
        label2 = new JLabel();
        txtDPI = new JTextField();
        label15 = new JLabel();
        comboLang = new JComboBox();
        label19 = new JLabel();
        checkNoiseRemoveTesseract = new JCheckBox();
        label21 = new JLabel();
        checkDictionary = new JCheckBox();
        panel9 = new JPanel();
        panel12 = new JPanel();
        label3 = new JLabel();
        label4 = new JLabel();
        panel13 = new JPanel();
        comboGrayscale = new JComboBox();
        checkBinary = new JCheckBox();
        panel6 = new JPanel();
        label20 = new JLabel();
        checkDeskew = new JCheckBox();
        label14 = new JLabel();
        txtMaxAngleTolerance = new JTextField();
        panel3 = new JPanel();
        panel10 = new JPanel();
        panel14 = new JPanel();
        label5 = new JLabel();
        label6 = new JLabel();
        panel15 = new JPanel();
        txtRadius = new JTextField();
        txtMargin = new JTextField();
        panel11 = new JPanel();
        panel17 = new JPanel();
        label7 = new JLabel();
        label8 = new JLabel();
        label9 = new JLabel();
        label10 = new JLabel();
        label11 = new JLabel();
        label12 = new JLabel();
        label13 = new JLabel();
        panel18 = new JPanel();
        txtThreshold1 = new JTextField();
        txtThreshold2 = new JTextField();
        txtDistanceAccumulation = new JTextField();
        txtAngleAccumulation = new JTextField();
        txtAccumulationThreshold = new JTextField();
        txtMinimumLineLength = new JTextField();
        txtMaxGap = new JTextField();
        panel5 = new JPanel();
        label16 = new JLabel();
        txtMaxLineDistance = new JTextField();
        label17 = new JLabel();
        txtMaxLineGap = new JTextField();
        label18 = new JLabel();
        txtThickness = new JTextField();
        panel2 = new JPanel();
        label1 = new JLabel();
        txtConfigurationName = new JTextField();
        butSave = new JButton();
        panel7 = new JPanel();
        butDelete = new JButton();
        comboConfigList = new JComboBox();

        //======== this ========
        setBorder(Borders.DLU4);

        // JFormDesigner evaluation mark
        setBorder(new javax.swing.border.CompoundBorder(
                new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
                        "JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
                        javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
                        java.awt.Color.red), getBorder()));
        addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent e) {
                if ("border".equals(e.getPropertyName())) throw new RuntimeException();
            }
        });

        setLayout(new GridBagLayout());
        ((GridBagLayout)getLayout()).columnWidths = new int[] {0, 0, 0};
        ((GridBagLayout)getLayout()).rowHeights = new int[] {0, 0, 0, 0, 0};
        ((GridBagLayout)getLayout()).columnWeights = new double[] {0.0, 0.0, 1.0E-4};
        ((GridBagLayout)getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 1.0E-4};

        //======== tabbedPane1 ========
        {
            tabbedPane1.setBorder(Borders.DLU4);

            //======== panel1 ========
            {
                panel1.setBorder(Borders.DLU4);
                panel1.setLayout(new GridBagLayout());
                ((GridBagLayout)panel1.getLayout()).columnWidths = new int[] {0, 0, 0};
                ((GridBagLayout)panel1.getLayout()).rowHeights = new int[] {0, 0, 0, 0};
                ((GridBagLayout)panel1.getLayout()).columnWeights = new double[] {0.0, 0.0, 1.0E-4};
                ((GridBagLayout)panel1.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 1.0E-4};

                //======== panel4 ========
                {
                    panel4.setBorder(new CompoundBorder(
                        new TitledBorder(""),
                        Borders.DLU4));
                    panel4.setLayout(new GridBagLayout());
                    ((GridBagLayout)panel4.getLayout()).columnWidths = new int[] {0, 0, 0};
                    ((GridBagLayout)panel4.getLayout()).rowHeights = new int[] {0, 0, 0, 0, 0};
                    ((GridBagLayout)panel4.getLayout()).columnWeights = new double[] {0.0, 0.0, 1.0E-4};
                    ((GridBagLayout)panel4.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 1.0E-4};

                    //---- label2 ----
                    label2.setText("DPI:");
                    label2.setHorizontalAlignment(SwingConstants.TRAILING);
                    panel4.add(label2, new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 5, 5), 0, 0));

                    //---- txtDPI ----
                    txtDPI.setText("300");
                    txtDPI.setPreferredSize(new Dimension(80, 26));
                    txtDPI.setHorizontalAlignment(SwingConstants.TRAILING);
                    panel4.add(txtDPI, new GridBagConstraints(1, 0, 1, 1, 1.0, 0.0,
                        GridBagConstraints.WEST, GridBagConstraints.VERTICAL,
                        new Insets(0, 0, 5, 0), 0, 0));

                    //---- label15 ----
                    label15.setText("\u042f\u0437\u044b\u043a:");
                    label15.setHorizontalAlignment(SwingConstants.TRAILING);
                    panel4.add(label15, new GridBagConstraints(0, 1, 1, 1, 1.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 5, 5), 0, 0));

                    //---- comboLang ----
                    comboLang.setModel(new DefaultComboBoxModel(new String[] {
                        "eng",
                        "rus",
                        "fra"
                    }));
                    panel4.add(comboLang, new GridBagConstraints(1, 1, 1, 1, 1.0, 0.0,
                        GridBagConstraints.WEST, GridBagConstraints.VERTICAL,
                        new Insets(0, 0, 5, 0), 0, 0));

                    //---- label19 ----
                    label19.setText("\u0421\u0438\u043b\u044c\u043d\u043e\u0435 \u0443\u0441\u0442\u0440\u0430\u043d\u0435\u043d\u0438\u0435 \u0448\u0443\u043c\u044b:");
                    label19.setHorizontalAlignment(SwingConstants.TRAILING);
                    panel4.add(label19, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 5, 5), 0, 0));
                    panel4.add(checkNoiseRemoveTesseract, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 5, 0), 0, 0));

                    //---- label21 ----
                    label21.setText("\u0414\u043e\u043f\u043e\u043b\u044c\u043d\u0438\u0442\u0435\u043b\u044c\u043d\u0430\u044f \u0441\u043b\u043e\u0432\u0430\u0440\u044c:");
                    label21.setHorizontalAlignment(SwingConstants.TRAILING);
                    panel4.add(label21, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 0, 5), 0, 0));
                    panel4.add(checkDictionary, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 0, 0), 0, 0));
                }
                panel1.add(panel4, new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0,
                    GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                    new Insets(0, 0, 5, 5), 0, 0));

                //======== panel9 ========
                {
                    panel9.setBorder(new CompoundBorder(
                        new TitledBorder("\u041f\u0435\u0440\u043e\u0431\u0440\u0430\u0437\u043e\u0432\u0430\u043d\u0438\u0435"),
                        Borders.DLU4));
                    panel9.setLayout(new GridBagLayout());
                    ((GridBagLayout)panel9.getLayout()).columnWidths = new int[] {0, 0, 0};
                    ((GridBagLayout)panel9.getLayout()).rowHeights = new int[] {0, 0};
                    ((GridBagLayout)panel9.getLayout()).columnWeights = new double[] {0.0, 0.0, 1.0E-4};
                    ((GridBagLayout)panel9.getLayout()).rowWeights = new double[] {0.0, 1.0E-4};

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
                        label4.setText("\u0418\u0441\u043f\u043e\u043b\u044c\u0437\u043e\u0432\u0430\u0442\u044c \u043a\u043b\u0430\u0441\u0442\u0435\u0440\u0438\u0437\u0430\u0446\u0438\u0438");
                        panel12.add(label4, new GridBagConstraints(0, 1, 1, 1, 0.0, 1.0,
                            GridBagConstraints.EAST, GridBagConstraints.VERTICAL,
                            new Insets(0, 0, 0, 0), 0, 0));
                    }
                    panel9.add(panel12, new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0,
                        GridBagConstraints.EAST, GridBagConstraints.VERTICAL,
                        new Insets(0, 0, 0, 5), 0, 0));

                    //======== panel13 ========
                    {
                        panel13.setLayout(new GridBagLayout());
                        ((GridBagLayout)panel13.getLayout()).columnWidths = new int[] {0, 0};
                        ((GridBagLayout)panel13.getLayout()).rowHeights = new int[] {0, 0, 0};
                        ((GridBagLayout)panel13.getLayout()).columnWeights = new double[] {0.0, 1.0E-4};
                        ((GridBagLayout)panel13.getLayout()).rowWeights = new double[] {0.0, 0.0, 1.0E-4};

                        //---- comboGrayscale ----
                        comboGrayscale.setModel(new DefaultComboBoxModel(new String[] {
                            "Average",
                            "Luminosity",
                            "Lightness"
                        }));
                        comboGrayscale.setSelectedIndex(1);
                        panel13.add(comboGrayscale, new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0,
                            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                            new Insets(0, 0, 5, 0), 0, 0));

                        //---- checkBinary ----
                        checkBinary.setSelected(true);
                        panel13.add(checkBinary, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
                            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                            new Insets(0, 0, 0, 0), 0, 0));
                    }
                    panel9.add(panel13, new GridBagConstraints(1, 0, 1, 1, 1.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 0, 0), 0, 0));
                }
                panel1.add(panel9, new GridBagConstraints(0, 1, 1, 1, 1.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));

                //======== panel6 ========
                {
                    panel6.setBorder(new CompoundBorder(
                        new TitledBorder("\u041d\u0430\u043a\u043b\u043e\u043d"),
                        Borders.DLU4));
                    panel6.setLayout(new GridBagLayout());
                    ((GridBagLayout)panel6.getLayout()).columnWidths = new int[] {0, 0, 0};
                    ((GridBagLayout)panel6.getLayout()).rowHeights = new int[] {0, 0, 0, 0};
                    ((GridBagLayout)panel6.getLayout()).columnWeights = new double[] {0.0, 0.0, 1.0E-4};
                    ((GridBagLayout)panel6.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 1.0E-4};

                    //---- label20 ----
                    label20.setText("\u0418\u0441\u043f\u0440\u0430\u0432\u0438\u0442\u044c \u043d\u0430\u043a\u043b\u043e\u043d\u0430 \u0434\u043e\u043a\u0443\u043c\u0435\u043d\u0442\u0430:");
                    label20.setHorizontalAlignment(SwingConstants.TRAILING);
                    panel6.add(label20, new GridBagConstraints(0, 0, 1, 1, 0.3, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 5, 5), 0, 0));
                    panel6.add(checkDeskew, new GridBagConstraints(1, 0, 1, 1, 1.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 5, 0), 0, 0));

                    //---- label14 ----
                    label14.setText("\u041c\u0430\u043a\u0441\u0438\u043c\u0430\u043b\u044c\u043d\u043e\u0435 \u043d\u0430\u043a\u043b\u043e\u043d\u0435\u043d\u0438\u0435 \u043b\u0438\u043d\u0438\u0438 (\u0433\u0440\u0430\u0434):");
                    label14.setHorizontalAlignment(SwingConstants.TRAILING);
                    panel6.add(label14, new GridBagConstraints(0, 1, 1, 1, 0.3, 1.0,
                        GridBagConstraints.EAST, GridBagConstraints.VERTICAL,
                        new Insets(0, 0, 5, 5), 0, 0));

                    //---- txtMaxAngleTolerance ----
                    txtMaxAngleTolerance.setMinimumSize(new Dimension(50, 27));
                    txtMaxAngleTolerance.setPreferredSize(new Dimension(50, 27));
                    txtMaxAngleTolerance.setHorizontalAlignment(SwingConstants.TRAILING);
                    panel6.add(txtMaxAngleTolerance, new GridBagConstraints(1, 1, 1, 1, 1.0, 0.0,
                        GridBagConstraints.WEST, GridBagConstraints.VERTICAL,
                        new Insets(0, 0, 5, 0), 0, 0));
                }
                panel1.add(panel6, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 5), 0, 0));
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

                //======== panel10 ========
                {
                    panel10.setBorder(new CompoundBorder(
                        new TitledBorder("\u041e\u0431\u043d\u0430\u0440\u0443\u0436\u0435\u043d\u0438\u0435 \u0431\u0443\u043a\u0432"),
                        Borders.DLU4));
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

                        //---- txtRadius ----
                        txtRadius.setHorizontalAlignment(SwingConstants.TRAILING);
                        panel15.add(txtRadius, new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0,
                            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                            new Insets(0, 0, 5, 0), 0, 0));

                        //---- txtMargin ----
                        txtMargin.setHorizontalAlignment(SwingConstants.TRAILING);
                        panel15.add(txtMargin, new GridBagConstraints(0, 1, 1, 1, 1.0, 0.0,
                            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                            new Insets(0, 0, 0, 0), 0, 0));
                    }
                    panel10.add(panel15, new GridBagConstraints(1, 0, 1, 1, 1.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 0, 0), 0, 0));
                }
                panel3.add(panel10, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 10, 0), 0, 0));

                //======== panel11 ========
                {
                    panel11.setBorder(new CompoundBorder(
                        new TitledBorder("\u041e\u0431\u043d\u0430\u0440\u0443\u0436\u0435\u043d\u0438\u0435 \u043b\u0438\u043d\u0438\u0438"),
                        Borders.DLU4));
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
                        label13.setText("\u041f\u0440\u0435\u043e\u0431\u0440\u0430\u0437\u043e\u0432\u0430\u043d\u0438\u0435 \u0425\u0430\u0444\u0430 - \u041c\u0430\u043a\u0441\u0438\u043c\u0430\u043b\u044c\u043d\u043e\u0435 \n\u0440\u0430\u0441\u0441\u0442\u043e\u044f\u043d\u0438\u0435 \u043c\u0435\u0436\u0434\u0443 \u043b\u0438\u043d\u044f\u043c\u0438 (px):");
                        panel17.add(label13, new GridBagConstraints(0, 6, 1, 1, 0.0, 1.0,
                            GridBagConstraints.EAST, GridBagConstraints.VERTICAL,
                            new Insets(0, 0, 5, 0), 0, 0));
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

                        //---- txtThreshold1 ----
                        txtThreshold1.setMinimumSize(new Dimension(50, 27));
                        txtThreshold1.setPreferredSize(new Dimension(50, 27));
                        txtThreshold1.setHorizontalAlignment(SwingConstants.TRAILING);
                        panel18.add(txtThreshold1, new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0,
                            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                            new Insets(0, 0, 5, 0), 0, 0));

                        //---- txtThreshold2 ----
                        txtThreshold2.setMinimumSize(new Dimension(50, 27));
                        txtThreshold2.setPreferredSize(new Dimension(50, 27));
                        txtThreshold2.setHorizontalAlignment(SwingConstants.TRAILING);
                        panel18.add(txtThreshold2, new GridBagConstraints(0, 1, 1, 1, 1.0, 0.0,
                            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                            new Insets(0, 0, 5, 0), 0, 0));

                        //---- txtDistanceAccumulation ----
                        txtDistanceAccumulation.setMinimumSize(new Dimension(50, 27));
                        txtDistanceAccumulation.setPreferredSize(new Dimension(50, 27));
                        txtDistanceAccumulation.setHorizontalAlignment(SwingConstants.TRAILING);
                        panel18.add(txtDistanceAccumulation, new GridBagConstraints(0, 2, 1, 1, 1.0, 0.0,
                            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                            new Insets(0, 0, 5, 0), 0, 0));

                        //---- txtAngleAccumulation ----
                        txtAngleAccumulation.setMinimumSize(new Dimension(50, 27));
                        txtAngleAccumulation.setPreferredSize(new Dimension(50, 27));
                        txtAngleAccumulation.setText("0.12354569");
                        txtAngleAccumulation.setHorizontalAlignment(SwingConstants.TRAILING);
                        panel18.add(txtAngleAccumulation, new GridBagConstraints(0, 3, 1, 1, 1.0, 0.0,
                            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                            new Insets(0, 0, 5, 0), 0, 0));

                        //---- txtAccumulationThreshold ----
                        txtAccumulationThreshold.setMinimumSize(new Dimension(50, 27));
                        txtAccumulationThreshold.setPreferredSize(new Dimension(50, 27));
                        txtAccumulationThreshold.setHorizontalAlignment(SwingConstants.TRAILING);
                        panel18.add(txtAccumulationThreshold, new GridBagConstraints(0, 4, 1, 1, 1.0, 0.0,
                            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                            new Insets(0, 0, 5, 0), 0, 0));

                        //---- txtMinimumLineLength ----
                        txtMinimumLineLength.setMinimumSize(new Dimension(50, 27));
                        txtMinimumLineLength.setPreferredSize(new Dimension(50, 27));
                        txtMinimumLineLength.setHorizontalAlignment(SwingConstants.TRAILING);
                        panel18.add(txtMinimumLineLength, new GridBagConstraints(0, 5, 1, 1, 1.0, 0.0,
                            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                            new Insets(0, 0, 5, 0), 0, 0));

                        //---- txtMaxGap ----
                        txtMaxGap.setMinimumSize(new Dimension(50, 27));
                        txtMaxGap.setPreferredSize(new Dimension(50, 27));
                        txtMaxGap.setHorizontalAlignment(SwingConstants.TRAILING);
                        panel18.add(txtMaxGap, new GridBagConstraints(0, 6, 1, 1, 1.0, 0.0,
                            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                            new Insets(0, 0, 5, 0), 0, 0));
                    }
                    panel11.add(panel18, new GridBagConstraints(1, 0, 1, 1, 4.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 0, 0), 0, 0));
                }
                panel3.add(panel11, new GridBagConstraints(0, 1, 1, 1, 1.0, 1.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 10, 0), 0, 0));

                //======== panel5 ========
                {
                    panel5.setBorder(new CompoundBorder(
                        new TitledBorder("\u041e\u0431\u043d\u0430\u0440\u0443\u0436\u0435\u043d\u0438\u0435 \u0442\u0430\u0431\u043b\u0438\u0446"),
                        Borders.DLU4));
                    panel5.setLayout(new GridBagLayout());
                    ((GridBagLayout)panel5.getLayout()).columnWidths = new int[] {0, 0, 0};
                    ((GridBagLayout)panel5.getLayout()).rowHeights = new int[] {0, 0, 0, 0};
                    ((GridBagLayout)panel5.getLayout()).columnWeights = new double[] {0.0, 0.0, 1.0E-4};
                    ((GridBagLayout)panel5.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 1.0E-4};

                    //---- label16 ----
                    label16.setText("\u041c\u0430\u043a\u0441\u0438\u043c\u0430\u043b\u044c\u043d\u043e\u0435 \u0440\u0430\u0441\u0442\u043e\u044f\u043d\u0438\u0435 \u043c\u0435\u0436\u0434\u0443 \u043b\u0438\u043d\u0438\u044f\u043c\u0438 (px):");
                    label16.setHorizontalAlignment(SwingConstants.TRAILING);
                    panel5.add(label16, new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 5, 5), 0, 0));

                    //---- txtMaxLineDistance ----
                    txtMaxLineDistance.setHorizontalAlignment(SwingConstants.TRAILING);
                    panel5.add(txtMaxLineDistance, new GridBagConstraints(1, 0, 1, 1, 1.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 5, 0), 0, 0));

                    //---- label17 ----
                    label17.setText("\u041c\u0430\u043a\u0441\u0438\u043c\u0430\u043b\u044c\u043d\u043e\u0435 \u0440\u0430\u0441\u0442\u043e\u044f\u043d\u0438\u0435 \u043c\u0435\u0436\u0434\u0443 \u0441\u043c\u0435\u0436\u043d\u044b\u043c\u0438 \u043b\u0438\u043d\u0438\u044f\u043c\u0438 (px):");
                    label17.setHorizontalAlignment(SwingConstants.TRAILING);
                    panel5.add(label17, new GridBagConstraints(0, 1, 1, 1, 1.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 5, 5), 0, 0));

                    //---- txtMaxLineGap ----
                    txtMaxLineGap.setHorizontalAlignment(SwingConstants.TRAILING);
                    panel5.add(txtMaxLineGap, new GridBagConstraints(1, 1, 1, 1, 1.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 5, 0), 0, 0));

                    //---- label18 ----
                    label18.setText("\u0422\u043e\u043b\u0449\u0438\u043d\u0430 \u043b\u0438\u043d\u0438\u0438 (px):");
                    label18.setHorizontalAlignment(SwingConstants.TRAILING);
                    panel5.add(label18, new GridBagConstraints(0, 2, 1, 1, 1.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 0, 5), 0, 0));

                    //---- txtThickness ----
                    txtThickness.setHorizontalAlignment(SwingConstants.TRAILING);
                    panel5.add(txtThickness, new GridBagConstraints(1, 2, 1, 1, 1.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 0, 0), 0, 0));
                }
                panel3.add(panel5, new GridBagConstraints(0, 2, 1, 1, 0.0, 1.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 0), 0, 0));
            }
            tabbedPane1.addTab("\u041f\u0440\u0435\u043f\u0440\u043e\u0446\u0435\u0441\u0441\u043e\u0440", panel3);
        }
        add(tabbedPane1, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

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
            panel2.add(txtConfigurationName, new GridBagConstraints(1, 0, 1, 1, 2.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 5), 0, 0));

            //---- butSave ----
            butSave.setText("\u0421\u043e\u0445\u0440\u0430\u043d\u0438\u0442\u044c");
            butSave.setHorizontalAlignment(SwingConstants.TRAILING);
            butSave.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    butSaveConfigActionPerformed(e);
                }
            });
            panel2.add(butSave, new GridBagConstraints(11, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.EAST, GridBagConstraints.VERTICAL,
                new Insets(0, 0, 0, 0), 0, 0));
        }
        add(panel2, new GridBagConstraints(0, 2, 1, 1, 1.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //======== panel7 ========
        {
            panel7.setLayout(new GridBagLayout());
            ((GridBagLayout)panel7.getLayout()).columnWidths = new int[] {0, 0, 0};
            ((GridBagLayout)panel7.getLayout()).rowHeights = new int[] {0, 0};
            ((GridBagLayout)panel7.getLayout()).columnWeights = new double[] {0.0, 0.0, 1.0E-4};
            ((GridBagLayout)panel7.getLayout()).rowWeights = new double[] {0.0, 1.0E-4};

            //---- butDelete ----
            butDelete.setText("\u0423\u0434\u0430\u043b\u0438\u0442\u044c");
            butDelete.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    butDeleteActionPerformed(e);
                }
            });
            panel7.add(butDelete, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 5), 0, 0));

            //---- comboConfigList ----
            comboConfigList.addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    comboConfigListItemStateChanged(e);
                }
            });
            panel7.add(comboConfigList, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.EAST, GridBagConstraints.VERTICAL,
                new Insets(0, 0, 0, 0), 0, 0));
        }
        add(panel7, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
            GridBagConstraints.EAST, GridBagConstraints.VERTICAL,
            new Insets(0, 0, 5, 5), 0, 0));
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
