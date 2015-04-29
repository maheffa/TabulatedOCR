import java.util.Collections;
import java.util.Vector;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.tree.DefaultMutableTreeNode;

import com.jgoodies.forms.factories.*;
/*
 * Created by JFormDesigner on Sun Apr 26 16:23:28 MSK 2015
 */



/**
 * @author Boubakar Tilojab
 */
public class OcrMainForm  {

    private void menuCreateFormatActionPerformed(ActionEvent e) {
        JFrame frame = new JFrame();
        frame.add(new CreateFormatForm());
        frame.pack();
        frame.setVisible(true);
    }

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Boubakar Tilojab
        mainFrame = new JFrame();
        menuBar1 = new JMenuBar();
        menu1 = new JMenu();
        menuItem1 = new JMenuItem();
        menuItem2 = new JMenuItem();
        menuItem3 = new JMenuItem();
        menuItem4 = new JMenuItem();
        menuItem5 = new JMenuItem();
        menu2 = new JMenu();
        menuItem6 = new JMenuItem();
        menuItem7 = new JMenuItem();
        menuItem8 = new JMenuItem();
        menuItem9 = new JMenuItem();
        menu3 = new JMenu();
        menuItem10 = new JMenuItem();
        menuCreateFormat = new JMenuItem();
        menuItem12 = new JMenuItem();
        menuItem13 = new JMenuItem();
        menuItem14 = new JMenuItem();
        menuItem15 = new JMenuItem();
        menuItem16 = new JMenuItem();
        menuItem21 = new JMenuItem();
        menu5 = new JMenu();
        menuItem18 = new JMenuItem();
        menuItem19 = new JMenuItem();
        menuItem20 = new JMenuItem();
        menu4 = new JMenu();
        menuItem17 = new JMenuItem();
        panel1 = new JPanel();
        screllTree = new JScrollPane();
        treeView = new JTree(addNodes(null, new File("/")));
        scrollPane2 = new JScrollPane();
        label3 = new JLabel();
        panel3 = new JPanel();
        scrollPane3 = new JScrollPane();
        panel8 = new JPanel();
        scrollPane4 = new JScrollPane();
        formattedTextField1 = new JFormattedTextField();
        label4 = new JLabel();
        progressBar1 = new JProgressBar();
        panel2 = new JPanel();
        scrollFormat = new JScrollPane();
        listFormat = new JList();
        scrollPane1 = new JScrollPane();
        label2 = new JLabel();
        scrollDatabase = new JScrollPane();
        listDatabase = new JList();
        scrollPane5 = new JScrollPane();
        label5 = new JLabel();
        panel9 = new JPanel();

        //======== mainFrame ========
        {
            mainFrame.setMinimumSize(new Dimension(300, 500));
            Container mainFrameContentPane = mainFrame.getContentPane();
            mainFrameContentPane.setLayout(new GridBagLayout());
            ((GridBagLayout)mainFrameContentPane.getLayout()).columnWidths = new int[] {0, 0, 0, 0};
            ((GridBagLayout)mainFrameContentPane.getLayout()).rowHeights = new int[] {0, 0};
            ((GridBagLayout)mainFrameContentPane.getLayout()).columnWeights = new double[] {0.0, 0.0, 0.0, 1.0E-4};
            ((GridBagLayout)mainFrameContentPane.getLayout()).rowWeights = new double[] {1.0, 1.0E-4};

            //======== menuBar1 ========
            {

                //======== menu1 ========
                {
                    menu1.setText("\u0424\u0430\u0439\u043b");

                    //---- menuItem1 ----
                    menuItem1.setText("\u041e\u0442\u043a\u044b\u0442\u044c \u043f\u0440\u043e\u0435\u043a\u0442");
                    menuItem1.setIcon(UIManager.getIcon("Tree.openIcon"));
                    menu1.add(menuItem1);

                    //---- menuItem2 ----
                    menuItem2.setText("\u0421\u043e\u0445\u0440\u0430\u043d\u0438\u0442\u044c \u043f\u0440\u043e\u0435\u043a\u0442");
                    menuItem2.setIcon(UIManager.getIcon("FileView.floppyDriveIcon"));
                    menu1.add(menuItem2);

                    //---- menuItem3 ----
                    menuItem3.setText("\u0421\u043e\u0445\u0440\u0430\u043d\u0438\u0442\u044c \u043a\u0430\u043a");
                    menuItem3.setIcon(UIManager.getIcon("FileView.floppyDriveIcon"));
                    menu1.add(menuItem3);

                    //---- menuItem4 ----
                    menuItem4.setText("\u0417\u0430\u043a\u0440\u044b\u0442\u044c \u043f\u0440\u043e\u0435\u043a\u0442");
                    menu1.add(menuItem4);
                    menu1.addSeparator();

                    //---- menuItem5 ----
                    menuItem5.setText("\u0421\u043e\u0437\u0434\u0430\u0442\u044c \u043f\u0440\u043e\u0435\u043a\u0442");
                    menuItem5.setIcon(UIManager.getIcon("FileView.directoryIcon"));
                    menu1.add(menuItem5);
                    menu1.addSeparator();

                    //======== menu2 ========
                    {
                        menu2.setText("\u042d\u043a\u0441\u043f\u043e\u0440\u0442\u0438\u0440\u043e\u0432\u0430\u0442\u044c");

                        //---- menuItem6 ----
                        menuItem6.setText("CSS");
                        menu2.add(menuItem6);

                        //---- menuItem7 ----
                        menuItem7.setText("\u0411\u0430\u0437\u0430 \u0434\u0430\u043d\u043d\u044b\u0445");
                        menu2.add(menuItem7);

                        //---- menuItem8 ----
                        menuItem8.setText("CSV");
                        menu2.add(menuItem8);
                    }
                    menu1.add(menu2);
                    menu1.addSeparator();

                    //---- menuItem9 ----
                    menuItem9.setText("\u0412\u044b\u0439\u0442\u0438");
                    menu1.add(menuItem9);
                }
                menuBar1.add(menu1);

                //======== menu3 ========
                {
                    menu3.setText("\u041a\u043e\u043d\u0444\u0438\u0433\u0443\u0440\u0430\u0446\u0438\u044f");

                    //---- menuItem10 ----
                    menuItem10.setText("\u041f\u0430\u0440\u0430\u043c\u0435\u0442\u0440\u044b \u0440\u0430\u0441\u043f\u043e\u0437\u043d\u0430\u0432\u043e\u043d\u0438\u044f");
                    menu3.add(menuItem10);
                    menu3.addSeparator();

                    //---- menuCreateFormat ----
                    menuCreateFormat.setText("\u0421\u043e\u0437\u0430\u0434\u0430\u0442\u044c \u0444\u043e\u0440\u043c\u0430\u0442");
                    menuCreateFormat.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            menuCreateFormatActionPerformed(e);
                        }
                    });
                    menu3.add(menuCreateFormat);

                    //---- menuItem12 ----
                    menuItem12.setText("\u0420\u0435\u0434\u0430\u043a\u0442\u0438\u0440\u043e\u0432\u0430\u0442\u044c \u0444\u043e\u0440\u043c\u0430\u0442");
                    menu3.add(menuItem12);

                    //---- menuItem13 ----
                    menuItem13.setText("\u0423\u0434\u0430\u043b\u0438\u0442\u044c \u0444\u043e\u0440\u043c\u0430\u0442\u044c");
                    menu3.add(menuItem13);
                    menu3.addSeparator();

                    //---- menuItem14 ----
                    menuItem14.setText("\u0421\u043e\u0437\u0434\u0430\u0442\u044c \u0411\u0414");
                    menu3.add(menuItem14);

                    //---- menuItem15 ----
                    menuItem15.setText("\u0420\u0435\u0434\u0430\u043a\u0442\u0438\u0440\u043e\u0432\u0430\u0442\u044c \u0411\u0414");
                    menu3.add(menuItem15);

                    //---- menuItem16 ----
                    menuItem16.setText("\u0423\u0434\u0430\u043b\u0438\u0442\u044c \u0411\u0414");
                    menu3.add(menuItem16);
                    menu3.addSeparator();

                    //---- menuItem21 ----
                    menuItem21.setText("\u0421\u0432\u044f\u0437\u044b\u0432\u0430\u0442\u044c");
                    menu3.add(menuItem21);
                }
                menuBar1.add(menu3);

                //======== menu5 ========
                {
                    menu5.setText("\u0420\u0430\u0441\u043f\u043e\u0437\u043d\u043e\u0432\u0430\u043d\u0438\u0435");

                    //---- menuItem18 ----
                    menuItem18.setText("\u0417\u0430\u043f\u0443\u0441\u0442\u0438\u0442\u044c");
                    menu5.add(menuItem18);

                    //---- menuItem19 ----
                    menuItem19.setText("\u041e\u0441\u0442\u0430\u043d\u043e\u0432\u0438\u0442\u044c");
                    menu5.add(menuItem19);

                    //---- menuItem20 ----
                    menuItem20.setText("\u041f\u0435\u0440\u0435\u0437\u0430\u043f\u0443\u0441\u0442\u044c");
                    menu5.add(menuItem20);
                }
                menuBar1.add(menu5);

                //======== menu4 ========
                {
                    menu4.setText("\u041f\u043e\u043c\u043e\u0449\u044c");

                    //---- menuItem17 ----
                    menuItem17.setText("\u041e \u043f\u0440\u0438\u043b\u043e\u0436\u0435\u043d\u0438\u0438");
                    menu4.add(menuItem17);
                }
                menuBar1.add(menu4);
            }
            mainFrame.setJMenuBar(menuBar1);

            //======== panel1 ========
            {
                panel1.setPreferredSize(new Dimension(200, 449));
                panel1.setBorder(Borders.DLU2);
                panel1.setMinimumSize(new Dimension(150, 447));

                // JFormDesigner evaluation mark
                panel1.setBorder(new javax.swing.border.CompoundBorder(
                    new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
                        "JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
                        javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
                        java.awt.Color.red), panel1.getBorder())); panel1.addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

                panel1.setLayout(new GridBagLayout());
                ((GridBagLayout)panel1.getLayout()).columnWidths = new int[] {0, 0};
                ((GridBagLayout)panel1.getLayout()).rowHeights = new int[] {0, 0, 0};
                ((GridBagLayout)panel1.getLayout()).columnWeights = new double[] {1.0, 1.0E-4};
                ((GridBagLayout)panel1.getLayout()).rowWeights = new double[] {0.0, 0.0, 1.0E-4};

                //======== screllTree ========
                {
                    screllTree.setBorder(new TitledBorder("\u041f\u0440\u043e\u0435\u043a\u0442"));
                    screllTree.setMinimumSize(new Dimension(50, 50));
                    screllTree.setPreferredSize(new Dimension(150, 382));
                    screllTree.setViewportView(treeView);
                }
                panel1.add(screllTree, new GridBagConstraints(0, 0, 1, 1, 0.0, 5.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 0), 0, 0));

                //======== scrollPane2 ========
                {
                    scrollPane2.setBorder(Borders.DLU4);

                    //---- label3 ----
                    label3.setText("<html>  <b>\u041d\u0430\u0437\u0432\u0430\u043d\u0438\u0435</b>:<br/>  \u0418\u0441\u0445\u043e\u0434\u043d\u044b\u0439:<br/>  <i>\u0420\u0430\u0437\u043c\u0435\u0440</i>:<br/> </html>");
                    label3.setVerticalAlignment(SwingConstants.TOP);
                    scrollPane2.setViewportView(label3);
                }
                panel1.add(scrollPane2, new GridBagConstraints(0, 1, 1, 1, 0.0, 3.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 0), 0, 0));
            }
            mainFrameContentPane.add(panel1, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0), 0, 0));

            //======== panel3 ========
            {
                panel3.setBackground(new Color(153, 153, 153));
                panel3.setBorder(Borders.DLU2);
                panel3.setMinimumSize(new Dimension(200, 79));
                panel3.setLayout(new GridBagLayout());
                ((GridBagLayout)panel3.getLayout()).columnWidths = new int[] {0, 0};
                ((GridBagLayout)panel3.getLayout()).rowHeights = new int[] {0, 0, 0, 0, 0};
                ((GridBagLayout)panel3.getLayout()).columnWeights = new double[] {1.0, 1.0E-4};
                ((GridBagLayout)panel3.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 1.0E-4};

                //======== scrollPane3 ========
                {
                    scrollPane3.setPreferredSize(new Dimension(0, 300));
                    scrollPane3.setBackground(new Color(153, 153, 153));
                    scrollPane3.setOpaque(false);
                    scrollPane3.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
                    scrollPane3.setBorder(Borders.DLU4);

                    //======== panel8 ========
                    {
                        panel8.setBackground(new Color(153, 153, 153));
                        panel8.setLayout(new GridBagLayout());
                        ((GridBagLayout)panel8.getLayout()).columnWidths = new int[] {0, 0};
                        ((GridBagLayout)panel8.getLayout()).rowHeights = new int[] {0, 0};
                        ((GridBagLayout)panel8.getLayout()).columnWeights = new double[] {0.0, 1.0E-4};
                        ((GridBagLayout)panel8.getLayout()).rowWeights = new double[] {0.0, 1.0E-4};
                    }
                    scrollPane3.setViewportView(panel8);
                }
                panel3.add(scrollPane3, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 0), 0, 0));

                //======== scrollPane4 ========
                {
                    scrollPane4.setPreferredSize(new Dimension(0, 300));
                    scrollPane4.setBackground(new Color(153, 153, 153));
                    scrollPane4.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
                    scrollPane4.setBorder(Borders.DLU4);
                    scrollPane4.setViewportView(formattedTextField1);
                }
                panel3.add(scrollPane4, new GridBagConstraints(0, 1, 1, 1, 1.0, 1.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 0), 0, 0));

                //---- label4 ----
                label4.setText("<html><i>progress ...</i></html>");
                panel3.add(label4, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 0), 0, 0));
                panel3.add(progressBar1, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 0), 0, 0));
            }
            mainFrameContentPane.add(panel3, new GridBagConstraints(1, 0, 1, 1, 5.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0), 0, 0));

            //======== panel2 ========
            {
                panel2.setPreferredSize(new Dimension(250, 440));
                panel2.setBorder(Borders.DLU2);
                panel2.setMinimumSize(new Dimension(150, 450));
                panel2.setLayout(new GridBagLayout());
                ((GridBagLayout)panel2.getLayout()).columnWidths = new int[] {0, 0};
                ((GridBagLayout)panel2.getLayout()).rowHeights = new int[] {0, 0, 0, 0, 0, 0};
                ((GridBagLayout)panel2.getLayout()).columnWeights = new double[] {1.0, 1.0E-4};
                ((GridBagLayout)panel2.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};

                //======== scrollFormat ========
                {
                    scrollFormat.setBorder(new TitledBorder("\u0424\u043e\u0440\u043c\u0430\u0442\u044b"));
                    scrollFormat.setMinimumSize(new Dimension(31, 40));
                    scrollFormat.setViewportView(listFormat);
                }
                panel2.add(scrollFormat, new GridBagConstraints(0, 0, 1, 1, 0.0, 5.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 0), 0, 0));

                //======== scrollPane1 ========
                {
                    scrollPane1.setBorder(Borders.DLU4);
                    scrollPane1.setMinimumSize(new Dimension(35, 50));
                    scrollPane1.setPreferredSize(new Dimension(156, 100));

                    //---- label2 ----
                    label2.setText("<html> <b>\u041d\u0430\u0437\u0432\u0430\u043d\u0438\u0435<b>:   <br/> <i>\u041a\u043e\u043b\u0438\u0447\u0435\u0441\u0442\u0432\u043e \u043a\u043b\u0435\u0442\u043e\u043a</i>:  <br/> <i>\u0421\u043f\u0438\u0441\u043e\u043a \u043f\u0435\u0440\u0435\u043c\u0435\u043d\u043d\u044b\u0445</i>: <br/> </html>");
                    label2.setVerticalAlignment(SwingConstants.TOP);
                    scrollPane1.setViewportView(label2);
                }
                panel2.add(scrollPane1, new GridBagConstraints(0, 1, 1, 1, 0.0, 2.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 0), 0, 0));

                //======== scrollDatabase ========
                {
                    scrollDatabase.setBorder(new TitledBorder("\u0411\u0414"));
                    scrollDatabase.setMinimumSize(new Dimension(31, 40));
                    scrollDatabase.setViewportView(listDatabase);
                }
                panel2.add(scrollDatabase, new GridBagConstraints(0, 2, 1, 1, 0.0, 5.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 0), 0, 0));

                //======== scrollPane5 ========
                {
                    scrollPane5.setBorder(Borders.DLU4);
                    scrollPane5.setPreferredSize(new Dimension(109, 100));
                    scrollPane5.setMinimumSize(new Dimension(0, 50));

                    //---- label5 ----
                    label5.setText("<html> <b>\u0411\u0430\u0437\u0430 \u0434\u0430\u043d\u043d\u044b\u0445</b>: <br/> <b>\u0422\u0430\u0431\u043b\u0438\u0446\u0430</b>: <br/> <b>\u0421\u0442\u043e\u043b\u0431\u0446\u044b</b>: <br/> </html>");
                    label5.setVerticalAlignment(SwingConstants.TOP);
                    scrollPane5.setViewportView(label5);
                }
                panel2.add(scrollPane5, new GridBagConstraints(0, 3, 1, 1, 0.0, 2.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 0), 0, 0));

                //======== panel9 ========
                {
                    panel9.setLayout(new GridBagLayout());
                    ((GridBagLayout)panel9.getLayout()).columnWidths = new int[] {0, 0};
                    ((GridBagLayout)panel9.getLayout()).rowHeights = new int[] {0, 0};
                    ((GridBagLayout)panel9.getLayout()).columnWeights = new double[] {0.0, 1.0E-4};
                    ((GridBagLayout)panel9.getLayout()).rowWeights = new double[] {0.0, 1.0E-4};
                }
                panel2.add(panel9, new GridBagConstraints(0, 4, 1, 1, 0.0, 0.5,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 0), 0, 0));
            }
            mainFrameContentPane.add(panel2, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0), 0, 0));
            mainFrame.setSize(755, 550);
            mainFrame.setLocationRelativeTo(mainFrame.getOwner());
        }
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Boubakar Tilojab
    private JFrame mainFrame;
    private JMenuBar menuBar1;
    private JMenu menu1;
    private JMenuItem menuItem1;
    private JMenuItem menuItem2;
    private JMenuItem menuItem3;
    private JMenuItem menuItem4;
    private JMenuItem menuItem5;
    private JMenu menu2;
    private JMenuItem menuItem6;
    private JMenuItem menuItem7;
    private JMenuItem menuItem8;
    private JMenuItem menuItem9;
    private JMenu menu3;
    private JMenuItem menuItem10;
    private JMenuItem menuCreateFormat;
    private JMenuItem menuItem12;
    private JMenuItem menuItem13;
    private JMenuItem menuItem14;
    private JMenuItem menuItem15;
    private JMenuItem menuItem16;
    private JMenuItem menuItem21;
    private JMenu menu5;
    private JMenuItem menuItem18;
    private JMenuItem menuItem19;
    private JMenuItem menuItem20;
    private JMenu menu4;
    private JMenuItem menuItem17;
    private JPanel panel1;
    private JScrollPane screllTree;
    private JTree treeView;
    private JScrollPane scrollPane2;
    private JLabel label3;
    private JPanel panel3;
    private JScrollPane scrollPane3;
    private JPanel panel8;
    private JScrollPane scrollPane4;
    private JFormattedTextField formattedTextField1;
    private JLabel label4;
    private JProgressBar progressBar1;
    private JPanel panel2;
    private JScrollPane scrollFormat;
    private JList listFormat;
    private JScrollPane scrollPane1;
    private JLabel label2;
    private JScrollPane scrollDatabase;
    private JList listDatabase;
    private JScrollPane scrollPane5;
    private JLabel label5;
    private JPanel panel9;
	// JFormDesigner - End of variables declaration  //GEN-END:variables

    public OcrMainForm() {
        super();
        initComponents();
    }

    public JFrame getMainFrame() {
        return this.mainFrame;
    }

    public void setProjectPathTree(String path) {

    }

    private DefaultMutableTreeNode addNodes(DefaultMutableTreeNode curTop, File dir) {
//        System.out.println("Current directory : " + dir.getPath());
        String curPath = dir.getPath();
        DefaultMutableTreeNode curDir = new DefaultMutableTreeNode(curPath);
        if (curTop != null) { // should only be null at root
            curTop.add(curDir);
        }
        Vector ol = new Vector();
        String[] tmp = dir.list();
        for (int i = 0; tmp != null && i < tmp.length; i++)
            ol.addElement(tmp[i]);
        Collections.sort(ol, String.CASE_INSENSITIVE_ORDER);
        File f;
        Vector files = new Vector();
        // Make two passes, one for Dirs and one for Files. This is #1.
        for (int i = 0; i < ol.size(); i++) {
            String thisObject = (String) ol.elementAt(i);
            String newPath;
            if (curPath.equals("."))
                newPath = thisObject;
            else
                newPath = curPath + File.separator + thisObject;
            if ((f = new File(newPath)).isDirectory())
                addNodes(curDir, f);
            else
                files.addElement(thisObject);
        }
        // Pass two: for files.
        for (int fnum = 0; fnum < files.size(); fnum++)
            curDir.add(new DefaultMutableTreeNode(files.elementAt(fnum)));
        return curDir;
    }
}
