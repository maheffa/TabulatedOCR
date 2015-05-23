import java.awt.*;
import java.awt.event.*;
import java.util.HashSet;
import java.util.Set;
import javax.swing.*;
import javax.swing.border.*;
import com.jgoodies.forms.factories.*;
/*
 * Created by JFormDesigner on Tue May 19 18:15:13 MSK 2015
 */



/**
 * @author Mahefa Manitrativo
 */
public class CreateTextFormat extends JPanel {
    private OcrMainForm parent;
    private boolean update = false;
    private Format format;
    private TextFormat textFormat;

    public CreateTextFormat(OcrMainForm parent) {
        initComponents();
        this.parent = parent;
        format = new Format();
        textFormat = new TextFormat();
        Set set = new HashSet();
        set.add(textFormat);
        format.setTextFormats(set);
    }

    public CreateTextFormat(OcrMainForm parent, Format format) {
        this(parent);
        update = true;
        this.format = format;
        // setting GUI
        this.txtFormatName.setText(format.getName());
        for (TextFormat textFormat : (Set<TextFormat>) format.getTextFormats()) {
            this.textFormat = textFormat;
        }
        this.txtFormatContent.setText(textFormat.getContent());
    }

    private void butSaveActionPerformed(ActionEvent e) {
        // error checking
        if (txtFormatName.getText().length() == 0) {
            JOptionPane.showMessageDialog(this, "Название формата не задано");
        }
        if (!update && DBAccess.getDbAccess().getFormatByName(format.getName()) != null) {
            JOptionPane.showMessageDialog(this, "Формат с таким названием уже существует");
        }

        // setting values
        format.setName(txtFormatName.getText());
        format.setType("TEXT");
        textFormat.setContent(txtFormatContent.getText());
        textFormat.setFormat(format);

        // inserting to database
        DBAccess access = DBAccess.getDbAccess();
        if (update) {
            access.updateEntry(format);
        } else {
            access.addEntry(format);
        }

        parent.updateFormatList();
        GUIUtil.close(this);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Mahefa Manitrativo
        panel1 = new JPanel();
        label2 = new JLabel();
        txtFormatName = new JTextField();
        panel2 = new JPanel();
        label1 = new JLabel();
        scrollPane1 = new JScrollPane();
        txtFormatContent = new JEditorPane();
        panel3 = new JPanel();
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
        ((GridBagLayout)getLayout()).columnWidths = new int[] {0, 0, 0};
        ((GridBagLayout)getLayout()).rowHeights = new int[] {0, 0, 0, 0};
        ((GridBagLayout)getLayout()).columnWeights = new double[] {0.0, 0.0, 1.0E-4};
        ((GridBagLayout)getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 1.0E-4};

        //======== panel1 ========
        {
            panel1.setBorder(new CompoundBorder(
                new EtchedBorder(),
                Borders.DLU4));
            panel1.setLayout(new GridBagLayout());
            ((GridBagLayout)panel1.getLayout()).columnWidths = new int[] {0, 0, 0};
            ((GridBagLayout)panel1.getLayout()).rowHeights = new int[] {0, 0, 0, 0};
            ((GridBagLayout)panel1.getLayout()).columnWeights = new double[] {0.0, 0.0, 1.0E-4};
            ((GridBagLayout)panel1.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 1.0E-4};

            //---- label2 ----
            label2.setText("\u041d\u0430\u0437\u0432\u0430\u043d\u0438\u0435:");
            panel1.add(label2, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));
            panel1.add(txtFormatName, new GridBagConstraints(1, 0, 1, 1, 1.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 0), 0, 0));
        }
        add(panel1, new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //======== panel2 ========
        {
            panel2.setBorder(new CompoundBorder(
                new EtchedBorder(),
                Borders.DLU4));
            panel2.setLayout(new GridBagLayout());
            ((GridBagLayout)panel2.getLayout()).columnWidths = new int[] {0, 0, 0};
            ((GridBagLayout)panel2.getLayout()).rowHeights = new int[] {0, 0, 0, 0};
            ((GridBagLayout)panel2.getLayout()).columnWeights = new double[] {0.0, 0.0, 1.0E-4};
            ((GridBagLayout)panel2.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 1.0E-4};

            //---- label1 ----
            label1.setText("\u0422\u0435\u043a\u0441\u0442\u043e\u0432\u043e\u0439 \u0444\u043e\u0440\u043c\u0430\u0442 (\u043e\u0431\u0440\u0430\u0437\u0435\u0446) \u0434\u043e\u043a\u0443\u043c\u0435\u043d\u0442\u0430:");
            panel2.add(label1, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));

            //======== scrollPane1 ========
            {

                //---- txtFormatContent ----
                txtFormatContent.setPreferredSize(new Dimension(309, 300));
                scrollPane1.setViewportView(txtFormatContent);
            }
            panel2.add(scrollPane1, new GridBagConstraints(0, 1, 1, 1, 1.0, 1.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));
        }
        add(panel2, new GridBagConstraints(0, 1, 1, 1, 1.0, 1.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //======== panel3 ========
        {
            panel3.setLayout(new GridBagLayout());
            ((GridBagLayout)panel3.getLayout()).columnWidths = new int[] {0, 0, 0};
            ((GridBagLayout)panel3.getLayout()).rowHeights = new int[] {0, 0, 0, 0};
            ((GridBagLayout)panel3.getLayout()).columnWeights = new double[] {0.0, 0.0, 1.0E-4};
            ((GridBagLayout)panel3.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 1.0E-4};

            //---- butSave ----
            butSave.setText("\u0421\u043e\u0445\u0440\u0430\u043d\u0438\u0442\u044c");
            butSave.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    butSaveActionPerformed(e);
                }
            });
            panel3.add(butSave, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 0), 0, 0));
        }
        add(panel3, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
            GridBagConstraints.EAST, GridBagConstraints.VERTICAL,
            new Insets(0, 0, 0, 5), 0, 0));
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Mahefa Manitrativo
    private JPanel panel1;
    private JLabel label2;
    private JTextField txtFormatName;
    private JPanel panel2;
    private JLabel label1;
    private JScrollPane scrollPane1;
    private JEditorPane txtFormatContent;
    private JPanel panel3;
    private JButton butSave;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
