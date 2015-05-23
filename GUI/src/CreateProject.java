import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.jgoodies.forms.factories.*;
import org.jdesktop.swingx.*;
import java.io.File;
import java.util.List;
/*
 * Created by JFormDesigner on Tue May 19 20:03:17 MSK 2015
 */



/**
 * @author Mahefa Manitrativo
 */
public class CreateProject extends JPanel {

    private OcrMainForm parent = null;
    private boolean update = false;
    private Project project = null;

    public CreateProject(OcrMainForm parent) {
        initComponents();
        this.parent = parent;
    }

    public CreateProject(OcrMainForm parent, Project project) {
        initComponents();
        update = true;
        this.parent = parent;
        this.project = project;
        this.txtProjectImage.setText(project.getInputFilePath());
        this.txtProjectName.setText(project.getName());
        this.butCreate.setText("Сохранить");
    }

    private void butChooseImageActionPerformed(ActionEvent e) {
        JFileChooser fc = new JFileChooser("Выбор рисунок");
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Рисунки", "jpg", "jpeg",
                "png", "PNG", "tiff", "TIFF", "JPG", "JPEG");
        fc.setFileFilter(filter);
        int returnVal = fc.showOpenDialog(CreateProject.this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            this.txtProjectImage.setText(file.getAbsolutePath());
        }
    }

    private void butCreateActionPerformed(ActionEvent e) {
        Project p = new Project();
        if (update) {
            p = project;
        }
        if (txtProjectName.getText().length() != 0) {
            p.setName(txtProjectName.getText());
        } else {
            JOptionPane.showMessageDialog(this, "Название проекта не задано");
        }
        if (txtProjectImage.getText().length() != 0) {
            p.setInputFilePath(txtProjectImage.getText());
        } else {
            JOptionPane.showMessageDialog(this, "Исходное изображение не задано");
        }

        if (update) {
            DBAccess.getDbAccess().updateEntry(p);
        } else {
            if (DBAccess.getDbAccess().getProjectByName(p.getName()) != null) {
                JOptionPane.showMessageDialog(this, "Проект с таким названием уже существует");
            } else {
                DBAccess.getDbAccess().addEntry(p);
            }
        }
        parent.updateProjectList();
        GUIUtil.close(this);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Mahefa Manitrativo
        panel1 = new JPanel();
        label5 = new JLabel();
        txtProjectName = new JTextField();
        label2 = new JLabel();
        txtProjectImage = new JTextField();
        butChooseImage = new JButton();
        panel4 = new JPanel();
        butCreate = new JButton();

        //======== this ========
        setBorder(Borders.DLU4);

        // JFormDesigner evaluation mark
        setBorder(new javax.swing.border.CompoundBorder(
            new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
                "JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
                javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
                java.awt.Color.red), getBorder())); addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

        setLayout(new VerticalLayout(10));

        //======== panel1 ========
        {
            panel1.setBorder(new CompoundBorder(
                new EtchedBorder(),
                Borders.DLU4));
            panel1.setLayout(new GridBagLayout());
            ((GridBagLayout)panel1.getLayout()).columnWidths = new int[] {0, 65, 0, 0, 0, 0};
            ((GridBagLayout)panel1.getLayout()).rowHeights = new int[] {0, 0, 0};
            ((GridBagLayout)panel1.getLayout()).columnWeights = new double[] {0.0, 1.0, 0.0, 0.0, 1.0, 1.0E-4};
            ((GridBagLayout)panel1.getLayout()).rowWeights = new double[] {1.0, 0.0, 1.0E-4};

            //---- label5 ----
            label5.setText("\u041d\u0430\u0437\u0432\u0430\u043d\u0438\u0435 \u043f\u0440\u043e\u0435\u043a\u0442\u0430:");
            panel1.add(label5, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.EAST, GridBagConstraints.VERTICAL,
                new Insets(0, 0, 5, 5), 0, 0));

            //---- txtProjectName ----
            txtProjectName.setPreferredSize(new Dimension(250, 22));
            panel1.add(txtProjectName, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));

            //---- label2 ----
            label2.setText("\u0418\u0441\u0445\u043e\u0434\u043d\u044b\u0439 \u0444\u0430\u0439\u043b:");
            panel1.add(label2, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
                GridBagConstraints.EAST, GridBagConstraints.VERTICAL,
                new Insets(0, 0, 0, 5), 0, 0));
            panel1.add(txtProjectImage, new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 5), 0, 0));

            //---- butChooseImage ----
            butChooseImage.setText("\u0412\u044b\u0431\u0440\u0430\u0442\u044c");
            butChooseImage.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    butChooseImageActionPerformed(e);
                }
            });
            panel1.add(butChooseImage, new GridBagConstraints(3, 1, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 5), 0, 0));
        }
        add(panel1);

        //======== panel4 ========
        {
            panel4.setLayout(new GridBagLayout());
            ((GridBagLayout)panel4.getLayout()).columnWidths = new int[] {0, 0};
            ((GridBagLayout)panel4.getLayout()).rowHeights = new int[] {0, 0};
            ((GridBagLayout)panel4.getLayout()).columnWeights = new double[] {1.0, 1.0E-4};
            ((GridBagLayout)panel4.getLayout()).rowWeights = new double[] {1.0, 1.0E-4};

            //---- butCreate ----
            butCreate.setText("\u0421\u043e\u0437\u0434\u0430\u0442\u044c");
            butCreate.setHorizontalAlignment(SwingConstants.RIGHT);
            butCreate.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    butCreateActionPerformed(e);
                }
            });
            panel4.add(butCreate, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.EAST, GridBagConstraints.VERTICAL,
                new Insets(0, 0, 0, 0), 0, 0));
        }
        add(panel4);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Mahefa Manitrativo
    private JPanel panel1;
    private JLabel label5;
    private JTextField txtProjectName;
    private JLabel label2;
    private JTextField txtProjectImage;
    private JButton butChooseImage;
    private JPanel panel4;
    private JButton butCreate;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
