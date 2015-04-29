import java.awt.*;
import javax.swing.*;
import com.jgoodies.forms.factories.*;
import org.jdesktop.swingx.*;
/*
 * Created by JFormDesigner on Wed Apr 29 18:46:48 MSK 2015
 */



/**
 * @author Boubakar Tilojab
 */
public class something extends JPanel {
    public something() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Boubakar Tilojab
        splitPane1 = new JSplitPane();
        panel2 = new JPanel();
        panel1 = new JPanel();
        label1 = new JLabel();
        button1 = new JButton();
        button2 = new JButton();
        label2 = new JLabel();
        scrollPane2 = new JScrollPane();
        label3 = new JLabel();

        //======== this ========
        setPreferredSize(new Dimension(300, 300));

        // JFormDesigner evaluation mark
        setBorder(new javax.swing.border.CompoundBorder(
            new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
                "JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
                javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
                java.awt.Color.red), getBorder())); addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

        setLayout(new GridLayout());

        //======== splitPane1 ========
        {
            splitPane1.setOrientation(JSplitPane.VERTICAL_SPLIT);
            splitPane1.setResizeWeight(0.5);

            //======== panel2 ========
            {
                panel2.setLayout(new FlowLayout());

                //======== panel1 ========
                {
                    panel1.setLayout(new GridLayout());

                    //---- label1 ----
                    label1.setText("text");
                    panel1.add(label1);

                    //---- button1 ----
                    button1.setText("text");
                    panel1.add(button1);
                }
                panel2.add(panel1);

                //---- button2 ----
                button2.setText("text");
                panel2.add(button2);

                //---- label2 ----
                label2.setText("text");
                panel2.add(label2);

                //======== scrollPane2 ========
                {
                    scrollPane2.setBorder(Borders.DLU4);

                    //---- label3 ----
                    label3.setText("<html>  <b>\u041d\u0430\u0437\u0432\u0430\u043d\u0438\u0435</b>:<br/>  \u0418\u0441\u0445\u043e\u0434\u043d\u044b\u0439:<br/>  <i>\u0420\u0430\u0437\u043c\u0435\u0440</i>:<br/> </html>");
                    label3.setVerticalAlignment(SwingConstants.TOP);
                    scrollPane2.setViewportView(label3);
                }
                panel2.add(scrollPane2);
            }
            splitPane1.setTopComponent(panel2);
        }
        add(splitPane1);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Boubakar Tilojab
    private JSplitPane splitPane1;
    private JPanel panel2;
    private JPanel panel1;
    private JLabel label1;
    private JButton button1;
    private JButton button2;
    private JLabel label2;
    private JScrollPane scrollPane2;
    private JLabel label3;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
