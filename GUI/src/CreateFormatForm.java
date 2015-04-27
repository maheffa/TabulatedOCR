import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import com.jgoodies.forms.factories.*;
import org.jdesktop.swingx.*;
/*
 * Created by JFormDesigner on Sun Apr 26 14:14:13 MSK 2015
 */

public class CreateFormatForm extends JPanel {
    private FormatPanel formatPanel = null;
    private CellPanel selectedCellPanel = null;

    public CreateFormatForm() {
        initComponents();
    }

    private void butCancelActionPerformed(ActionEvent e) {

    }

    private void panelFormatMouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        CellPanel root = formatPanel.getCellPanel();
        if (!root.initialized()) {
            formatPanel.initCell();
            root = formatPanel.getCellPanel();
        }
//        System.out.println("root " + root);
//        System.out.println("Checking on position " + x + " , " + y);
        selectedCellPanel = root.getAtPosition(x, y);
//        System.out.println("selected: " + selectedCellPanel);
        selectedCellPanel.select();
        txtFormat.setText(selectedCellPanel.getFormattedContent());
    }

    private void butSplitActionPerformed(ActionEvent e) {
        if (selectedCellPanel != null) {
            int splitCol = (Integer) this.spinCol.getValue();
            int splitRow = (Integer) this.spinRow.getValue();
            selectedCellPanel.divide(splitCol, splitRow);
            selectedCellPanel.draw();
        }
    }

    private void butMergeActionPerformed(ActionEvent e) {
        if (selectedCellPanel != null) {
            CellPanel parent = selectedCellPanel.getParent();
            if (parent != null) {
                parent.removeChildren();
                parent.select();
            }
        }
    }

    private void butSaveFormatActionPerformed(ActionEvent e) {
        if (selectedCellPanel != null) {
            selectedCellPanel.setFormattedContent(txtFormat.getText());
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Boubakar Tilojab
        panelFormat = new FormatPanel();
        panel2 = new JPanel();
        panel4 = new JPanel();
        spinCol = new JSpinner();
        label1 = new JLabel();
        spinRow = new JSpinner();
        label2 = new JLabel();
        butSplit = new JButton();
        butMerge = new JButton();
        panel5 = new JPanel();
        scrollPane1 = new JScrollPane();
        txtFormat = new JTextArea();
        butSaveFormat = new JButton();
        panel3 = new JPanel();
        label3 = new JLabel();
        textField1 = new JTextField();
        hSpacer1 = new JPanel(null);
        butCancel = new JButton();
        butSave = new JButton();

        //======== this ========
        setBorder(Borders.DIALOG);

        // JFormDesigner evaluation mark
        setBorder(new javax.swing.border.CompoundBorder(
            new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
                "JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
                javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
                java.awt.Color.red), getBorder())); addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

        setLayout(new VerticalLayout());

        //======== panelFormat ========
        {
            panelFormat.setPreferredSize(new Dimension(500, 300));
            panelFormat.setAlignmentX(50.0F);
            panelFormat.setAlignmentY(50.0F);
            panelFormat.setBackground(Color.white);
            panelFormat.setBorder(new EtchedBorder());
            panelFormat.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    panelFormatMouseClicked(e);
                    panelFormatMouseClicked(e);
                }
            });
            panelFormat.setLayout(new FlowLayout());
            formatPanel = (FormatPanel) panelFormat;
            formatPanel.initCell();
        }
        add(panelFormat);

        //======== panel2 ========
        {
            panel2.setBorder(Borders.DLU4);
            panel2.setLayout(new HorizontalLayout());

            //======== panel4 ========
            {
                panel4.setBorder(new TitledBorder(null, "\u041a\u043b\u0435\u0442\u043a\u0438", TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION,
                    new Font("Dialog", Font.BOLD, 14)));
                panel4.setLayout(new GridBagLayout());
                ((GridBagLayout)panel4.getLayout()).columnWidths = new int[] {0, 5, 0, 5, 0};
                ((GridBagLayout)panel4.getLayout()).rowHeights = new int[] {0, 5, 0, 5, 0, 0};
                ((GridBagLayout)panel4.getLayout()).columnWeights = new double[] {0.0, 0.0, 0.0, 0.0, 1.0E-4};
                ((GridBagLayout)panel4.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};
                panel4.add(spinCol, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 0), 0, 0));

                //---- label1 ----
                label1.setText("Columns");
                panel4.add(label1, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 0), 0, 0));
                panel4.add(spinRow, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 0), 0, 0));

                //---- label2 ----
                label2.setText("Rows");
                panel4.add(label2, new GridBagConstraints(2, 2, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 0), 0, 0));

                //---- butSplit ----
                butSplit.setText("Split");
                butSplit.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        butSplitActionPerformed(e);
                        butSplitActionPerformed(e);
                    }
                });
                panel4.add(butSplit, new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 0), 0, 0));

                //---- butMerge ----
                butMerge.setText("Merge");
                butMerge.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        butMergeActionPerformed(e);
                    }
                });
                panel4.add(butMerge, new GridBagConstraints(2, 4, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 0), 0, 0));
            }
            panel2.add(panel4);

            //======== panel5 ========
            {
                panel5.setBorder(new TitledBorder(null, "\u0424\u043e\u0440\u043c\u0430\u0442", TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION,
                    new Font("Dialog", Font.BOLD, 14)));
                panel5.setPreferredSize(new Dimension(450, 124));
                panel5.setLayout(new GridBagLayout());
                ((GridBagLayout)panel5.getLayout()).columnWidths = new int[] {0, 0};
                ((GridBagLayout)panel5.getLayout()).rowHeights = new int[] {0, 0, 0};
                ((GridBagLayout)panel5.getLayout()).columnWeights = new double[] {0.0, 1.0E-4};
                ((GridBagLayout)panel5.getLayout()).rowWeights = new double[] {0.0, 0.0, 1.0E-4};

                //======== scrollPane1 ========
                {
                    scrollPane1.setPreferredSize(new Dimension(250, 100));
                    scrollPane1.setViewportView(txtFormat);
                }
                panel5.add(scrollPane1, new GridBagConstraints(0, 0, 1, 1, 3.0, 1.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0));

                //---- butSaveFormat ----
                butSaveFormat.setText("\u0421\u043e\u0445\u0440\u0430\u043d\u0438\u0442\u044c \u0424\u043e\u0440\u043c\u0430\u0442");
                butSaveFormat.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        butSaveFormatActionPerformed(e);
                    }
                });
                panel5.add(butSaveFormat, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
                    GridBagConstraints.EAST, GridBagConstraints.VERTICAL,
                    new Insets(0, 0, 0, 0), 0, 0));
            }
            panel2.add(panel5);
        }
        add(panel2);

        //======== panel3 ========
        {
            panel3.setLayout(new GridBagLayout());
            ((GridBagLayout)panel3.getLayout()).columnWidths = new int[] {0, 0, 0, 0, 0, 0};
            ((GridBagLayout)panel3.getLayout()).rowHeights = new int[] {0, 0};
            ((GridBagLayout)panel3.getLayout()).columnWeights = new double[] {1.0, 1.0, 0.0, 1.0, 1.0, 1.0E-4};
            ((GridBagLayout)panel3.getLayout()).rowWeights = new double[] {1.0, 1.0E-4};

            //---- label3 ----
            label3.setText("\u041d\u0430\u0437\u0432\u0430\u043d\u0438\u0435: ");
            panel3.add(label3, new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0,
                GridBagConstraints.EAST, GridBagConstraints.VERTICAL,
                new Insets(0, 0, 0, 0), 0, 0));
            panel3.add(textField1, new GridBagConstraints(1, 0, 1, 1, 10.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0), 0, 0));
            panel3.add(hSpacer1, new GridBagConstraints(2, 0, 1, 1, 2.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0), 0, 0));

            //---- butCancel ----
            butCancel.setText("\u041e\u0442\u043c\u0435\u043d\u0438\u0442\u044c");
            panel3.add(butCancel, new GridBagConstraints(3, 0, 1, 1, 1.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0), 0, 0));

            //---- butSave ----
            butSave.setText("\u0421\u043e\u0445\u0440\u0430\u043d\u0438\u0442\u044c");
            panel3.add(butSave, new GridBagConstraints(4, 0, 1, 1, 1.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0), 0, 0));
        }
        add(panel3);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Boubakar Tilojab
    private JPanel panelFormat;
    private JPanel panel2;
    private JPanel panel4;
    private JSpinner spinCol;
    private JLabel label1;
    private JSpinner spinRow;
    private JLabel label2;
    private JButton butSplit;
    private JButton butMerge;
    private JPanel panel5;
    private JScrollPane scrollPane1;
    private JTextArea txtFormat;
    private JButton butSaveFormat;
    private JPanel panel3;
    private JLabel label3;
    private JTextField textField1;
    private JPanel hSpacer1;
    private JButton butCancel;
    private JButton butSave;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
