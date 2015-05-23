import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;

import com.jgoodies.forms.factories.*;
/*
 * Created by JFormDesigner on Tue May 19 17:41:38 MSK 2015
 */



/**
 * @author Mahefa Manitrativo
 */
public class CreateTableFormat extends JPanel {

    private OcrMainForm parent = null;
    private boolean update = false, initializing = false;
    private ColumnCharacteristic[] columnCharacteristics;
    private TableFormat tableFormat;
    private Format format;

    public CreateTableFormat(OcrMainForm parent) {
        initComponents();
        this.parent = parent;
        format = new Format();
        tableFormat = new TableFormat();
        Set<TableFormat> set = new HashSet<TableFormat>();
        set.add(tableFormat);
        format.setTableFormats(set);
    }

    public CreateTableFormat(OcrMainForm parent, Format format) {
        this(parent);
        update = true;
        initializing = true;
        this.format = format;
        for (TableFormat tableFormat : (Set<TableFormat>)format.getTableFormats()) {
            this.tableFormat = tableFormat;
        }
        columnCharacteristics = new ColumnCharacteristic[tableFormat.getColumnCharacteristics().size()];
        int index = 0;
        for (ColumnCharacteristic characteristic : (Set<ColumnCharacteristic>) tableFormat.getColumnCharacteristics()) {
            columnCharacteristics[index++] = characteristic;
        }
        System.out.println("ColumnCharacteristic array: " + Arrays.toString(columnCharacteristics));
        // setting GUIs
        this.butCreate.setText("Изменить");
        this.txtFormatName.setText(format.getName());
        int n = columnCharacteristics.length;
        this.spinnerColumnNumber.setValue(n);
        this.checkBox1.setSelected(tableFormat.getReadFirstLine());
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
        for (int i = 0; i < columnCharacteristics.length; i++) {
            ColumnCharacteristic c = columnCharacteristics[i];
            model.addElement(c.getName());
        }
        comboColumn.setModel(model);
        comboColumn.setSelectedIndex(0);
        this.txtColumnName.setText(columnCharacteristics[0].getName());
        this.comboType.setSelectedItem(columnCharacteristics[0].getType());
        initializing = false;
    }

    private void columnNamePropertyChange(PropertyChangeEvent e) {

    }

    private void spinnerColumnNumberStateChanged(ChangeEvent e) {
        if (initializing) return;
        // GUI
        int nColumn = (Integer) spinnerColumnNumber.getValue();
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
        for (int i = 0; i < nColumn; i++) {
            model.addElement("column" + Integer.toString(i));
        }
        comboColumn.setModel(model);
        // datas
        columnCharacteristics = new ColumnCharacteristic[nColumn];
    }

    private void butOKActionPerformed(ActionEvent e) {
        DefaultComboBoxModel m = (DefaultComboBoxModel) comboColumn.getModel();
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        int index = comboColumn.getSelectedIndex();
        for (int i = 0; i < m.getSize(); i++) {
            if (i == comboColumn.getSelectedIndex()) {
                model.addElement(txtColumnName.getText());
                columnCharacteristics[i] = new ColumnCharacteristic(
                        tableFormat,
                        i,
                        txtColumnName.getText(),
                        (String) comboType.getSelectedItem());
            } else {
                model.addElement(m.getElementAt(i));
            }
        }
        comboColumn.setModel(model);
        comboColumn.setSelectedIndex(index);
    }

    private void butCreateActionPerformed(ActionEvent e) {
        // error checking
        if (txtFormatName.getText().length() == 0) {
            JOptionPane.showMessageDialog(this, "Название формата не задано");
        }
        if (!update && DBAccess.getDbAccess().getFormatByName(format.getName()) != null) {
            JOptionPane.showMessageDialog(this, "Проект с таким названием уже существует");
        }

        // setting values
        format.setName(txtFormatName.getText());
        format.setType("TABLE");

        tableFormat.setColumnCount(columnCharacteristics.length);
        tableFormat.setFormat(format);
        Set set = new HashSet();
        for (ColumnCharacteristic characteristic : columnCharacteristics) {
            set.add(characteristic);
        }
        Set oldSet = tableFormat.getColumnCharacteristics();
        tableFormat.setColumnCharacteristics(set);
        tableFormat.setReadFirstLine(checkBox1.isSelected());

        // inserting to database
        DBAccess access = DBAccess.getDbAccess();
        if (update) {
            access.updateEntry(format);
//            access.updateEntry(tableFormat);
            if (oldSet != null)
                for (Object oldColumn : oldSet) {
                    access.deleteEntry(oldColumn);
                }
//            for (Object column : tableFormat.getColumnCharacteristics()) {
//                access.addEntry(column);
//            }
        } else {
            access.addEntry(format);
//            access.addEntry(tableFormat);
//            for (Object column : tableFormat.getColumnCharacteristics()) {
//                access.addEntry(column);
//            }
        }

        parent.updateFormatList();
        GUIUtil.close(this);
    }

    private void comboColumnActionPerformed(ActionEvent e) {
        int index = comboColumn.getSelectedIndex();
        ColumnCharacteristic c = columnCharacteristics[index];
        if (c != null) {
            txtColumnName.setText(c.getName());
            comboType.setSelectedItem(c.getType());
        } else {
            txtColumnName.setText((String) comboColumn.getSelectedItem());
            comboType.setSelectedIndex(0);
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Mahefa Manitrativo
        panel4 = new JPanel();
        label6 = new JLabel();
        txtFormatName = new JTextField();
        panel1 = new JPanel();
        label1 = new JLabel();
        spinnerColumnNumber = new JSpinner();
        label5 = new JLabel();
        checkBox1 = new JCheckBox();
        panel2 = new JPanel();
        label4 = new JLabel();
        comboColumn = new JComboBox();
        label2 = new JLabel();
        txtColumnName = new JTextField();
        label3 = new JLabel();
        comboType = new JComboBox();
        butOK = new JButton();
        panel3 = new JPanel();
        butCreate = new JButton();

        //======== this ========
        setBorder(Borders.DLU4);
        setPreferredSize(new Dimension(450, 380));

        // JFormDesigner evaluation mark
        setBorder(new javax.swing.border.CompoundBorder(
            new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
                "JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
                javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
                java.awt.Color.red), getBorder())); addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

        setLayout(new GridBagLayout());
        ((GridBagLayout)getLayout()).columnWidths = new int[] {0, 0};
        ((GridBagLayout)getLayout()).rowHeights = new int[] {0, 0, 0, 0, 0};
        ((GridBagLayout)getLayout()).columnWeights = new double[] {0.0, 1.0E-4};
        ((GridBagLayout)getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 1.0E-4};

        //======== panel4 ========
        {
            panel4.setBorder(new CompoundBorder(
                new EtchedBorder(),
                Borders.DLU4));
            panel4.setLayout(new GridBagLayout());
            ((GridBagLayout)panel4.getLayout()).columnWidths = new int[] {0, 0, 0};
            ((GridBagLayout)panel4.getLayout()).rowHeights = new int[] {0, 0};
            ((GridBagLayout)panel4.getLayout()).columnWeights = new double[] {0.0, 0.0, 1.0E-4};
            ((GridBagLayout)panel4.getLayout()).rowWeights = new double[] {0.0, 1.0E-4};

            //---- label6 ----
            label6.setText("\u041d\u0430\u0437\u0432\u0430\u043d\u0438\u0435:");
            panel4.add(label6, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 5), 0, 0));
            panel4.add(txtFormatName, new GridBagConstraints(1, 0, 1, 1, 1.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0), 0, 0));
        }
        add(panel4, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 0), 0, 0));

        //======== panel1 ========
        {
            panel1.setBorder(new CompoundBorder(
                new EtchedBorder(),
                Borders.DLU4));
            panel1.setPreferredSize(new Dimension(350, 45));
            panel1.setLayout(new GridBagLayout());
            ((GridBagLayout)panel1.getLayout()).columnWidths = new int[] {0, 0, 0};
            ((GridBagLayout)panel1.getLayout()).rowHeights = new int[] {0, 0, 0};
            ((GridBagLayout)panel1.getLayout()).columnWeights = new double[] {0.0, 0.0, 1.0E-4};
            ((GridBagLayout)panel1.getLayout()).rowWeights = new double[] {0.0, 0.0, 1.0E-4};

            //---- label1 ----
            label1.setText("\u041a\u043e\u043b\u0438\u0447\u0435\u0441\u0442\u0432\u043e \u0441\u0442\u043e\u043b\u044c\u0431\u0446\u0435\u0432:");
            panel1.add(label1, new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0,
                GridBagConstraints.EAST, GridBagConstraints.VERTICAL,
                new Insets(0, 0, 5, 5), 0, 0));

            //---- spinnerColumnNumber ----
            spinnerColumnNumber.setModel(new SpinnerNumberModel(1, 1, null, 1));
            spinnerColumnNumber.setPreferredSize(new Dimension(40, 20));
            spinnerColumnNumber.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    spinnerColumnNumberStateChanged(e);
                }
            });
            panel1.add(spinnerColumnNumber, new GridBagConstraints(1, 0, 1, 1, 1.0, 0.0,
                GridBagConstraints.WEST, GridBagConstraints.VERTICAL,
                new Insets(0, 0, 5, 0), 0, 0));

            //---- label5 ----
            label5.setText("\u0418\u0441\u043a\u043b\u044e\u0447\u0438\u0442\u044c \u043f\u0435\u0440\u0432\u0443\u044e \u0441\u0442\u0440\u043e\u043a\u0443");
            panel1.add(label5, new GridBagConstraints(0, 1, 1, 1, 1.0, 0.0,
                GridBagConstraints.EAST, GridBagConstraints.VERTICAL,
                new Insets(0, 0, 0, 5), 0, 0));

            //---- checkBox1 ----
            checkBox1.setSelected(true);
            panel1.add(checkBox1, new GridBagConstraints(1, 1, 1, 1, 1.0, 0.0,
                GridBagConstraints.WEST, GridBagConstraints.VERTICAL,
                new Insets(0, 0, 0, 0), 0, 0));
        }
        add(panel1, new GridBagConstraints(0, 1, 1, 1, 1.0, 1.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 0), 0, 0));

        //======== panel2 ========
        {
            panel2.setBorder(new CompoundBorder(
                new TitledBorder(new EtchedBorder(), "\u0425\u0430\u0440\u0430\u043a\u0442\u0435\u0440\u0438\u0441\u0442\u0438\u043a\u0430 \u0441\u0442\u043e\u044c\u0431\u0446\u044b", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION),
                Borders.DLU4));
            panel2.setLayout(new GridBagLayout());
            ((GridBagLayout)panel2.getLayout()).columnWidths = new int[] {0, 0, 0};
            ((GridBagLayout)panel2.getLayout()).rowHeights = new int[] {0, 0, 0, 0, 0};
            ((GridBagLayout)panel2.getLayout()).columnWeights = new double[] {0.0, 0.0, 1.0E-4};
            ((GridBagLayout)panel2.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 1.0E-4};

            //---- label4 ----
            label4.setText("\u0421\u0442\u043e\u043b\u044c\u0431\u0446\u0430:");
            panel2.add(label4, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.EAST, GridBagConstraints.VERTICAL,
                new Insets(0, 0, 5, 5), 0, 0));

            //---- comboColumn ----
            comboColumn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    comboColumnActionPerformed(e);
                }
            });
            panel2.add(comboColumn, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 0), 0, 0));

            //---- label2 ----
            label2.setText("\u041d\u0430\u0437\u0432\u0430\u043d\u0438\u0435:");
            panel2.add(label2, new GridBagConstraints(0, 1, 1, 1, 1.0, 0.0,
                GridBagConstraints.EAST, GridBagConstraints.VERTICAL,
                new Insets(0, 0, 5, 5), 0, 0));

            //---- txtColumnName ----
            txtColumnName.addPropertyChangeListener(new PropertyChangeListener() {
                @Override
                public void propertyChange(PropertyChangeEvent e) {
                    columnNamePropertyChange(e);
                }
            });
            panel2.add(txtColumnName, new GridBagConstraints(1, 1, 1, 1, 1.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 0), 0, 0));

            //---- label3 ----
            label3.setText("\u0422\u0438\u043f \u0437\u043d\u0430\u0447\u0435\u043d\u0438\u0438:");
            panel2.add(label3, new GridBagConstraints(0, 2, 1, 1, 1.0, 0.0,
                GridBagConstraints.EAST, GridBagConstraints.VERTICAL,
                new Insets(0, 0, 5, 5), 0, 0));

            //---- comboType ----
            comboType.setModel(new DefaultComboBoxModel(new String[] {
                "TEXT",
                "INT",
                "DOUBLE"
            }));
            panel2.add(comboType, new GridBagConstraints(1, 2, 1, 1, 1.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 0), 0, 0));

            //---- butOK ----
            butOK.setText("\u041e\u041a");
            butOK.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    butOKActionPerformed(e);
                }
            });
            panel2.add(butOK, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0,
                GridBagConstraints.EAST, GridBagConstraints.VERTICAL,
                new Insets(0, 0, 0, 0), 0, 0));
        }
        add(panel2, new GridBagConstraints(0, 2, 1, 1, 1.0, 1.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 0), 0, 0));

        //======== panel3 ========
        {
            panel3.setLayout(new GridBagLayout());
            ((GridBagLayout)panel3.getLayout()).columnWidths = new int[] {0, 0};
            ((GridBagLayout)panel3.getLayout()).rowHeights = new int[] {0, 0, 0};
            ((GridBagLayout)panel3.getLayout()).columnWeights = new double[] {0.0, 1.0E-4};
            ((GridBagLayout)panel3.getLayout()).rowWeights = new double[] {0.0, 0.0, 1.0E-4};

            //---- butCreate ----
            butCreate.setText("\u0421\u043e\u0437\u0434\u0430\u0442\u044c");
            butCreate.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    butCreateActionPerformed(e);
                }
            });
            panel3.add(butCreate, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 0), 0, 0));
        }
        add(panel3, new GridBagConstraints(0, 3, 1, 1, 0.0, 1.0,
            GridBagConstraints.SOUTHEAST, GridBagConstraints.NONE,
            new Insets(0, 0, 0, 0), 0, 0));
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Mahefa Manitrativo
    private JPanel panel4;
    private JLabel label6;
    private JTextField txtFormatName;
    private JPanel panel1;
    private JLabel label1;
    private JSpinner spinnerColumnNumber;
    private JLabel label5;
    private JCheckBox checkBox1;
    private JPanel panel2;
    private JLabel label4;
    private JComboBox comboColumn;
    private JLabel label2;
    private JTextField txtColumnName;
    private JLabel label3;
    private JComboBox comboType;
    private JButton butOK;
    private JPanel panel3;
    private JButton butCreate;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
