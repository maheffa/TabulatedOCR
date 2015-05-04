import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import com.jgoodies.forms.factories.*;
/*
 * Created by JFormDesigner on Fri May 01 17:21:24 MSK 2015
 */



/**
 * @author Boubakar Tilojab
 */
public class Chooser extends JPanel {

    public static int FORMAT = 0, DATABASE = 1;
    private DefaultListModel<String> listModel = null;
    private int type = -1;
    private boolean deleteMode = false;

    public Chooser() {
        initComponents();
    }

    public Chooser(String[] list, int type, boolean deleteMode) {
        listModel = new DefaultListModel<String>();
        for (String el : list) {
            listModel.addElement(el);
        }
        this.type = type;
        this.deleteMode = deleteMode;
        initComponents();
        if (deleteMode) {
            butEdit.setText("Удалить");
        }
    }

    private void butCancelActionPerformed(ActionEvent e) {
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        frame.setVisible(false);
        frame.dispose();
    }

    private void butEditActionPerformed(ActionEvent e) {
        int index = jlist.getSelectedIndex();
        if (!deleteMode) {
            if (type == Chooser.FORMAT) {
                GUIUtil.createFrameForPanel(new CreateFormatForm(listModel.get(index)));
            } else if (type == Chooser.DATABASE) {

            }
            SwingUtilities.getWindowAncestor(this).dispose();
        } else {
            if (type == Chooser.FORMAT) {
                String name = listModel.get(index);
                Serializer.deleteFormat(name);
                listModel.remove(index);
            } else if (type == Chooser.DATABASE) {

            }
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Boubakar Tilojab
        scrollPane1 = new JScrollPane();
        jlist = new JList(listModel);
        butCancel = new JButton();
        butEdit = new JButton();

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
        ((GridBagLayout)getLayout()).rowHeights = new int[] {0, 0, 0};
        ((GridBagLayout)getLayout()).columnWeights = new double[] {1.0, 0.0, 1.0E-4};
        ((GridBagLayout)getLayout()).rowWeights = new double[] {0.0, 0.0, 1.0E-4};

        //======== scrollPane1 ========
        {

            //---- jlist ----
            jlist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            jlist.setSelectedIndex(0);
            scrollPane1.setViewportView(jlist);
        }
        add(scrollPane1, new GridBagConstraints(0, 0, 2, 1, 0.0, 50.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 6, 0), 0, 0));

        //---- butCancel ----
        butCancel.setText("\u041e\u0442\u043c\u0435\u043d\u0438\u0442\u044c");
        butCancel.setHorizontalAlignment(SwingConstants.RIGHT);
        butCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                butCancelActionPerformed(e);
            }
        });
        add(butCancel, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
            GridBagConstraints.EAST, GridBagConstraints.VERTICAL,
            new Insets(0, 0, 0, 10), 0, 0));

        //---- butEdit ----
        butEdit.setText("\u0420\u0435\u0434\u0430\u043a\u0442\u0438\u0440\u043e\u0432\u0430\u0442\u044c");
        butEdit.setHorizontalAlignment(SwingConstants.RIGHT);
        butEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                butEditActionPerformed(e);
            }
        });
        add(butEdit, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
            GridBagConstraints.EAST, GridBagConstraints.VERTICAL,
            new Insets(0, 0, 0, 0), 0, 0));
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Boubakar Tilojab
    private JScrollPane scrollPane1;
    private JList jlist;
    private JButton butCancel;
    private JButton butEdit;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
