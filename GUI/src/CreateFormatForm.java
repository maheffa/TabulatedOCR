import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import com.jgoodies.forms.factories.*;
import org.jdesktop.swingx.*;
/*
 * Created by JFormDesigner on Sun Apr 26 14:14:13 MSK 2015
 */

public class CreateFormatForm extends JPanel {
    private FormatPanel formatPanel = null;
    private CellPanel cellPanel = null;
    private Cell selectedCell = null;
    private boolean editMode = false;

    public CreateFormatForm() {
        initComponents();
        formatPanel = (FormatPanel) panelFormat;
        cellPanel = formatPanel.getCellPanel();
    }

    public CreateFormatForm(String name) {
        initComponents();
        cellPanel = Serializer.loadFormat(name);
        formatPanel.setCellPanel(cellPanel);
        txtFormatName.setText(cellPanel.getName());
        txtFormatName.setEditable(false);
        editMode = true;
    }

    private void butCancelActionPerformed(ActionEvent e) {
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        frame.dispose();
    }

    private void panelFormatMouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        CellPanel cellPanel = formatPanel.getCellPanel();
//        System.out.println("root " + root);
        System.out.println("Checking on position " + x + " , " + y);
        selectedCell = cellPanel.getAtPosition(x, y);
        cellPanel.setSelectedCell(selectedCell);
        System.out.println("selected: " + selectedCell);
        if(selectedCell != null) {
            txtFormat.setText(selectedCell.getFormattedContent());
        }
//        cellPanel.draw();
        formatPanel.revalidate();
        formatPanel.repaint();
    }

    private void butSplitActionPerformed(ActionEvent e) {
        if (selectedCell != null) {
            int splitCol = (Integer) this.spinCol.getValue();
            int splitRow = (Integer) this.spinRow.getValue();
            double colWeight = txtColumnWeight.getText().equals("") ? 0.0 : Double.valueOf(txtColumnWeight.getText());
            double rowWeight = txtRowWeight.getText().equals("") ? 0.0 : Double.valueOf(txtRowWeight.getText());

//            selectedCell.divide(splitCol, splitRow, colWeight, rowWeight);
            cellPanel.splitCell(splitCol, splitRow, colWeight, rowWeight, selectedCell);
//            selectedCell.draw(formatPanel);
            formatPanel.revalidate();
            formatPanel.repaint();
        }
    }

    private void butSaveFormatActionPerformed(ActionEvent e) {
        if (selectedCell != null) {
            selectedCell.setFormattedContent(txtFormat.getText());
        }
    }

    private void butSaveMouseClicked(MouseEvent e) {
        Parameters param = Parameters.getInstance();
        String path = param.getProjectPath();
        if (txtFormatName.getText().length() == 0) {
            JOptionPane.showMessageDialog(this, "Не указанно название формата");
            JOptionPane.showMessageDialog(this, "Не указанно название формата", "Название формата", JOptionPane.ERROR_MESSAGE);
        } else {
            String formatName = txtFormatName.getText();
            File projectDir = param.getProjectDir();
            if (!editMode) {
                File[] listFile = projectDir.listFiles();
                if (listFile != null) {
                    for (File f : listFile) {
                        String name = SerializerUtil.getPathBaseExtension(f.getPath())[1];
                        if (name.equals(formatName)) {
                            String[] options = new String[]{"Переиммновать", "Записать"};
                            int choice = JOptionPane.showOptionDialog(this,
                                    "Формат с таким именем уже существует",
                                    "Название формата",
                                    JOptionPane.DEFAULT_OPTION,
                                    JOptionPane.QUESTION_MESSAGE,
                                    null,
                                    options,
                                    options[0]);
                            if (choice == 0) {
                                return;
                            } else {
                                break;
                            }
                        }
                    }
                }
            }
            Serializer.saveFormat(formatPanel.getCellPanel(), formatName);
            (SwingUtilities.getWindowAncestor(this)).dispose();
        }
    }

    private void butSaveActionPerformed(ActionEvent e) {
        // TODO add your code here
    }

    private void spinColStateChanged(ChangeEvent e) {
        int v = (Integer) spinCol.getValue();
        if (v == 2) {
            txtColumnWeight.setEditable(true);
        } else {
            txtColumnWeight.setEditable(false);
        }
    }

    private void spinRowStateChanged(ChangeEvent e) {
        int v = (Integer) spinRow.getValue();
        if (v == 2) {
            txtRowWeight.setEditable(true);
        } else {
            txtRowWeight.setEditable(false);
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Boubakar Tilojab
        panelFormat = new FormatPanel();
        panel2 = new JPanel();
        panel4 = new JPanel();
        txtColumnWeight = new JTextField();
        spinCol = new JSpinner();
        label1 = new JLabel();
        label4 = new JLabel();
        spinRow = new JSpinner();
        label2 = new JLabel();
        txtRowWeight = new JTextField();
        label5 = new JLabel();
        butSplit = new JButton();
        panel5 = new JPanel();
        scrollPane1 = new JScrollPane();
        txtFormat = new JTextArea();
        butSaveFormat = new JButton();
        panel3 = new JPanel();
        label3 = new JLabel();
        txtFormatName = new JTextField();
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
            panel2.setPreferredSize(new Dimension(644, 140));
            panel2.setLayout(new HorizontalLayout());

            //======== panel4 ========
            {
                panel4.setBorder(new TitledBorder(null, "\u041a\u043b\u0435\u0442\u043a\u0438", TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION,
                    new Font("Dialog", Font.BOLD, 14)));
                panel4.setPreferredSize(new Dimension(280, 100));
                panel4.setLayout(new GridBagLayout());
                ((GridBagLayout)panel4.getLayout()).columnWidths = new int[] {56, 69, 40, 50, 0};
                ((GridBagLayout)panel4.getLayout()).rowHeights = new int[] {15, 14, 0, 0};
                ((GridBagLayout)panel4.getLayout()).columnWeights = new double[] {0.0, 0.0, 1.0, 1.0, 1.0E-4};
                ((GridBagLayout)panel4.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 1.0E-4};
                panel4.add(txtColumnWeight, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                    new Insets(0, 0, 3, 3), 0, 0));

                //---- spinCol ----
                spinCol.setModel(new SpinnerNumberModel(1, 1, null, 1));
                spinCol.addChangeListener(new ChangeListener() {
                    @Override
                    public void stateChanged(ChangeEvent e) {
                        spinColStateChanged(e);
                    }
                });
                panel4.add(spinCol, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.NONE,
                    new Insets(0, 0, 3, 3), 0, 0));

                //---- label1 ----
                label1.setText("Columns");
                panel4.add(label1, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 3, 3), 0, 0));

                //---- label4 ----
                label4.setText("Column weight");
                panel4.add(label4, new GridBagConstraints(3, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 3, 0), 0, 0));

                //---- spinRow ----
                spinRow.setModel(new SpinnerNumberModel(1, 1, null, 1));
                spinRow.addChangeListener(new ChangeListener() {
                    @Override
                    public void stateChanged(ChangeEvent e) {
                        spinRowStateChanged(e);
                    }
                });
                panel4.add(spinRow, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.NONE,
                    new Insets(0, 0, 3, 3), 0, 0));

                //---- label2 ----
                label2.setText("Rows");
                panel4.add(label2, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 3, 3), 0, 0));
                panel4.add(txtRowWeight, new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                    new Insets(0, 0, 3, 3), 0, 0));

                //---- label5 ----
                label5.setText("Row weight");
                panel4.add(label5, new GridBagConstraints(3, 1, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 3, 0), 0, 0));

                //---- butSplit ----
                butSplit.setText("Split");
                butSplit.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        butSplitActionPerformed(e);
                    }
                });
                panel4.add(butSplit, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 3), 0, 0));
            }
            panel2.add(panel4);

            //======== panel5 ========
            {
                panel5.setBorder(new TitledBorder(null, "\u0424\u043e\u0440\u043c\u0430\u0442", TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION,
                    new Font("Dialog", Font.BOLD, 14)));
                panel5.setPreferredSize(new Dimension(350, 124));
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
                new Insets(0, 0, 0, 4), 0, 0));
            panel3.add(txtFormatName, new GridBagConstraints(1, 0, 1, 1, 10.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 4), 0, 0));
            panel3.add(hSpacer1, new GridBagConstraints(2, 0, 1, 1, 2.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 4), 0, 0));

            //---- butCancel ----
            butCancel.setText("\u041e\u0442\u043c\u0435\u043d\u0438\u0442\u044c");
            butCancel.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    butCancelActionPerformed(e);
                }
            });
            panel3.add(butCancel, new GridBagConstraints(3, 0, 1, 1, 1.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 4), 0, 0));

            //---- butSave ----
            butSave.setText("\u0421\u043e\u0445\u0440\u0430\u043d\u0438\u0442\u044c");
            butSave.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    butSaveActionPerformed(e);
                }
            });
            butSave.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    butSaveMouseClicked(e);
                }
            });
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
    private JTextField txtColumnWeight;
    private JSpinner spinCol;
    private JLabel label1;
    private JLabel label4;
    private JSpinner spinRow;
    private JLabel label2;
    private JTextField txtRowWeight;
    private JLabel label5;
    private JButton butSplit;
    private JPanel panel5;
    private JScrollPane scrollPane1;
    private JTextArea txtFormat;
    private JButton butSaveFormat;
    private JPanel panel3;
    private JLabel label3;
    private JTextField txtFormatName;
    private JPanel hSpacer1;
    private JButton butCancel;
    private JButton butSave;
    // JFormDesigner - End of variables declaration  //GEN-END:variables


}
