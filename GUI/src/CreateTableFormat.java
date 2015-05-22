import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import com.jgoodies.forms.factories.*;
/*
 * Created by JFormDesigner on Tue May 19 17:41:38 MSK 2015
 */



/**
 * @author Mahefa Manitrativo
 */
public class CreateTableFormat extends JPanel {
    public CreateTableFormat() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Mahefa Manitrativo
        panel4 = new JPanel();
        label6 = new JLabel();
        txtFormatName = new JTextField();
        panel1 = new JPanel();
        label1 = new JLabel();
        txtColumnNumber = new JTextField();
        label5 = new JLabel();
        checkBox1 = new JCheckBox();
        panel2 = new JPanel();
        label4 = new JLabel();
        comboColumn = new JComboBox();
        label2 = new JLabel();
        columnName = new JTextField();
        label3 = new JLabel();
        comboType = new JComboBox();
        butSaveColumn = new JButton();
        panel3 = new JPanel();
        butCancel = new JButton();
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

            //---- txtColumnNumber ----
            txtColumnNumber.setPreferredSize(new Dimension(50, 27));
            panel1.add(txtColumnNumber, new GridBagConstraints(1, 0, 1, 1, 1.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 0), 0, 0));

            //---- label5 ----
            label5.setText("\u0418\u0441\u043a\u043b\u044e\u0447\u0438\u0442\u044c \u043f\u0435\u0440\u0432\u0443\u044e \u0441\u0442\u0440\u043e\u043a\u0443");
            panel1.add(label5, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
                GridBagConstraints.EAST, GridBagConstraints.VERTICAL,
                new Insets(0, 0, 0, 5), 0, 0));

            //---- checkBox1 ----
            checkBox1.setSelected(true);
            panel1.add(checkBox1, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
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
            panel2.add(comboColumn, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 0), 0, 0));

            //---- label2 ----
            label2.setText("\u041d\u0430\u0437\u0432\u0430\u043d\u0438\u0435:");
            panel2.add(label2, new GridBagConstraints(0, 1, 1, 1, 1.0, 0.0,
                GridBagConstraints.EAST, GridBagConstraints.VERTICAL,
                new Insets(0, 0, 5, 5), 0, 0));
            panel2.add(columnName, new GridBagConstraints(1, 1, 1, 1, 1.0, 0.0,
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

            //---- butSaveColumn ----
            butSaveColumn.setText("\u0421\u043e\u0445\u0440\u0430\u043d\u0438\u0442\u044c");
            panel2.add(butSaveColumn, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0,
                GridBagConstraints.EAST, GridBagConstraints.VERTICAL,
                new Insets(0, 0, 0, 0), 0, 0));
        }
        add(panel2, new GridBagConstraints(0, 2, 1, 1, 1.0, 1.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 0), 0, 0));

        //======== panel3 ========
        {
            panel3.setLayout(new GridBagLayout());
            ((GridBagLayout)panel3.getLayout()).columnWidths = new int[] {0, 0, 0};
            ((GridBagLayout)panel3.getLayout()).rowHeights = new int[] {0, 0, 0};
            ((GridBagLayout)panel3.getLayout()).columnWeights = new double[] {0.0, 0.0, 1.0E-4};
            ((GridBagLayout)panel3.getLayout()).rowWeights = new double[] {0.0, 0.0, 1.0E-4};

            //---- butCancel ----
            butCancel.setText("\u041e\u0442\u043c\u0435\u043d\u0438\u0442\u044c");
            panel3.add(butCancel, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));

            //---- butCreate ----
            butCreate.setText("\u0421\u043e\u0437\u0434\u0430\u0442\u044c");
            panel3.add(butCreate, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
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
    private JTextField txtColumnNumber;
    private JLabel label5;
    private JCheckBox checkBox1;
    private JPanel panel2;
    private JLabel label4;
    private JComboBox comboColumn;
    private JLabel label2;
    private JTextField columnName;
    private JLabel label3;
    private JComboBox comboType;
    private JButton butSaveColumn;
    private JPanel panel3;
    private JButton butCancel;
    private JButton butCreate;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
