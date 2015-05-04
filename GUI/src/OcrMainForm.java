import java.io.IOException;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.regex.Pattern;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeCellRenderer;

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

    private void menuEditFormatActionPerformed(ActionEvent e) {
        Chooser chooser = new Chooser(
                Serializer.listFormat(),
                Chooser.FORMAT,
                false
        );
        GUIUtil.createFrameForPanel(chooser);
    }

    private void menuDeleteFormatActionPerformed(ActionEvent e) {
        Chooser chooser = new Chooser(
                Serializer.listFormat(),
                Chooser.FORMAT,
                true
        );
        GUIUtil.createFrameForPanel(chooser);
    }

    private void listFormatValueChanged(ListSelectionEvent e) {
        int index = listFormat.getSelectedIndex();
        String name = formatNameListModel.get(index);
        CellPanel cp = Serializer.loadFormat(name);

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
        menuEditFormat = new JMenuItem();
        menuDeleteFormat = new JMenuItem();
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
        splitPane2 = new JSplitPane();
        panel1 = new JPanel();
        splitPane4 = new JSplitPane();
        screllTree = new JScrollPane();
        projectTreeView = new JTree(openDownToPath(paramater.getProjectPath()));
        scrollPane2 = new JScrollPane();
        label3 = new JLabel();
        splitPane3 = new JSplitPane();
        panel3 = new JPanel();
        scrollPane3 = new JScrollPane();
        panel8 = new JPanel();
        scrollPane4 = new JScrollPane();
        formattedTextField1 = new JFormattedTextField();
        label4 = new JLabel();
        progressBar1 = new JProgressBar();
        panel2 = new JPanel();
        scrollFormat = new JScrollPane();
        panel4 = new JPanel();
        listFormat = new JList(formatNameListModel = getFormatList());
        scrollPane1 = new JScrollPane();
        label2 = new JLabel();
        scrollDatabase = new JScrollPane();
        listDatabase = new JList();
        scrollPane5 = new JScrollPane();
        label5 = new JLabel();

        //======== mainFrame ========
        {
            mainFrame.setMinimumSize(new Dimension(300, 500));
            Container mainFrameContentPane = mainFrame.getContentPane();
            mainFrameContentPane.setLayout(new GridLayout());

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

                    //---- menuEditFormat ----
                    menuEditFormat.setText("\u0420\u0435\u0434\u0430\u043a\u0442\u0438\u0440\u043e\u0432\u0430\u0442\u044c \u0444\u043e\u0440\u043c\u0430\u0442");
                    menuEditFormat.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            menuEditFormatActionPerformed(e);
                        }
                    });
                    menu3.add(menuEditFormat);

                    //---- menuDeleteFormat ----
                    menuDeleteFormat.setText("\u0423\u0434\u0430\u043b\u0438\u0442\u044c \u0444\u043e\u0440\u043c\u0430\u0442\u044c");
                    menuDeleteFormat.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            menuDeleteFormatActionPerformed(e);
                        }
                    });
                    menu3.add(menuDeleteFormat);
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

            //======== splitPane2 ========
            {

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

                    panel1.setLayout(new GridLayout());

                    //======== splitPane4 ========
                    {
                        splitPane4.setOrientation(JSplitPane.VERTICAL_SPLIT);
                        splitPane4.setResizeWeight(0.7);

                        //======== screllTree ========
                        {
                            screllTree.setBorder(new TitledBorder("\u041f\u0440\u043e\u0435\u043a\u0442"));
                            screllTree.setMinimumSize(new Dimension(150, 350));
                            screllTree.setPreferredSize(new Dimension(150, 500));
                            screllTree.setViewportView(projectTreeView);
                        }
                        splitPane4.setTopComponent(screllTree);

                        //======== scrollPane2 ========
                        {
                            scrollPane2.setBorder(Borders.DLU4);

                            //---- label3 ----
                            label3.setText("<html>  <b>\u041d\u0430\u0437\u0432\u0430\u043d\u0438\u0435</b>:<br/>  \u0418\u0441\u0445\u043e\u0434\u043d\u044b\u0439:<br/>  <i>\u0420\u0430\u0437\u043c\u0435\u0440</i>:<br/> </html>");
                            label3.setVerticalAlignment(SwingConstants.TOP);
                            scrollPane2.setViewportView(label3);
                        }
                        splitPane4.setBottomComponent(scrollPane2);
                    }
                    panel1.add(splitPane4);
                }
                splitPane2.setLeftComponent(panel1);

                //======== splitPane3 ========
                {
                    splitPane3.setResizeWeight(0.8);

                    //======== panel3 ========
                    {
                        panel3.setBackground(new Color(153, 153, 153));
                        panel3.setBorder(Borders.DLU2);
                        panel3.setMinimumSize(new Dimension(300, 79));
                        panel3.setPreferredSize(new Dimension(300, 300));
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
                    splitPane3.setLeftComponent(panel3);

                    //======== panel2 ========
                    {
                        panel2.setPreferredSize(new Dimension(150, 440));
                        panel2.setBorder(Borders.DLU2);
                        panel2.setMinimumSize(new Dimension(150, 450));
                        panel2.setMaximumSize(new Dimension(300, 2147483647));
                        panel2.setLayout(new GridBagLayout());
                        ((GridBagLayout)panel2.getLayout()).columnWeights = new double[] {1.0};

                        //======== scrollFormat ========
                        {
                            scrollFormat.setBorder(new TitledBorder("\u0424\u043e\u0440\u043c\u0430\u0442\u044b"));
                            scrollFormat.setMinimumSize(new Dimension(31, 40));
                            scrollFormat.setMaximumSize(new Dimension(300, 32767));

                            //======== panel4 ========
                            {
                                panel4.setLayout(new BorderLayout());

                                //---- listFormat ----
                                listFormat.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                                listFormat.setCursor(null);
                                listFormat.addListSelectionListener(new ListSelectionListener() {
                                    @Override
                                    public void valueChanged(ListSelectionEvent e) {
                                        listFormatValueChanged(e);
                                    }
                                });
                                panel4.add(listFormat, BorderLayout.CENTER);
                            }
                            scrollFormat.setViewportView(panel4);
                        }
                        panel2.add(scrollFormat, new GridBagConstraints(0, 0, 1, 1, 0.0, 5.0,
                            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                            new Insets(0, 0, 0, 0), 0, 0));

                        //======== scrollPane1 ========
                        {
                            scrollPane1.setBorder(Borders.DLU4);
                            scrollPane1.setMinimumSize(new Dimension(35, 50));
                            scrollPane1.setPreferredSize(new Dimension(156, 100));
                            scrollPane1.setMaximumSize(new Dimension(300, 32767));

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
                            scrollDatabase.setMaximumSize(new Dimension(300, 32767));
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
                    }
                    splitPane3.setRightComponent(panel2);
                }
                splitPane2.setRightComponent(splitPane3);
            }
            mainFrameContentPane.add(splitPane2);
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
    private JMenuItem menuEditFormat;
    private JMenuItem menuDeleteFormat;
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
    private JSplitPane splitPane2;
    private JPanel panel1;
    private JSplitPane splitPane4;
    private JScrollPane screllTree;
    private JTree projectTreeView;
    private JScrollPane scrollPane2;
    private JLabel label3;
    private JSplitPane splitPane3;
    private JPanel panel3;
    private JScrollPane scrollPane3;
    private JPanel panel8;
    private JScrollPane scrollPane4;
    private JFormattedTextField formattedTextField1;
    private JLabel label4;
    private JProgressBar progressBar1;
    private JPanel panel2;
    private JScrollPane scrollFormat;
    private JPanel panel4;
    private JList listFormat;
    private JScrollPane scrollPane1;
    private JLabel label2;
    private JScrollPane scrollDatabase;
    private JList listDatabase;
    private JScrollPane scrollPane5;
    private JLabel label5;
	// JFormDesigner - End of variables declaration  //GEN-END:variables

    private Parameters paramater;
    private DefaultListModel<String> formatNameListModel = null;

    public OcrMainForm(Parameters paramater) {
        super();
        this.paramater = paramater;
        initComponents();
        projectTreeView.setCellRenderer(new TreeCellRenderer() {
            @Override
            public Component getTreeCellRendererComponent(JTree jTree,
                                                          Object o,
                                                          boolean selected,
                                                          boolean expanded,
                                                          boolean leaf, int i,
                                                          boolean hasFocus) {
                if (o instanceof DefaultMutableTreeNode) {
                    DefaultMutableTreeNode object = (DefaultMutableTreeNode) o;
                    if (object.getUserObject() instanceof File) {
                        File f = (File) object.getUserObject();
                        JLabel label = new JLabel(f.getName());
                        if (f.isDirectory()) {
                            label.setIcon(UIManager.getIcon("FileView.directoryIcon"));
                        } else {
                            label.setIcon(UIManager.getIcon("FileView.fileIcon"));
                        }
                        return label;
                    }
                }
                return null;
            }
        });

        // launch GUI
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.pack();
        mainFrame.setVisible(true);
    }

    public JFrame getMainFrame() {
        return this.mainFrame;
    }

    private DefaultMutableTreeNode openDownToPath(String path) {
        String[] folders = path.split(Pattern.quote(File.separator));
        System.out.printf(Arrays.toString(folders));
        DefaultMutableTreeNode root = new DefaultMutableTreeNode(new File("/"));
        int index = 1;
        DefaultMutableTreeNode current = root;
        while (index < folders.length) {
            open(current, 1);
            Enumeration<DefaultMutableTreeNode> enumeration = current.children();
            DefaultMutableTreeNode satisfactory = null;
            while (enumeration.hasMoreElements() && satisfactory == null) {
                DefaultMutableTreeNode tmpDm = enumeration.nextElement();
                if (((File) tmpDm.getUserObject()).getName().equals(folders[index])) {
                    satisfactory = tmpDm;
                    current = satisfactory;
                }
            }
            index++;
        }
        return root;
    }

    private void open(DefaultMutableTreeNode folder, int depth) {
        File f = (File) folder.getUserObject();
//        System.out.println("Opening " + f.getPath());
        if (f.isDirectory() && depth > 0) {
            File[] files = f.listFiles();
            ArrayList<File> ds = new ArrayList<File>();
            ArrayList<File> fs = new ArrayList<File>();
            if (files != null) {
                for (File ff : files) {
                    if (ff.isHidden()) continue;
                    if (ff.isDirectory() && ff.listFiles() != null) {
                        ds.add(ff);
                    } else {
                        if (Serializer.isProjectFile(ff.getName())) {
                            fs.add(ff);
                        }
                    }
                }
                Collections.sort(ds);
                Collections.sort(fs);
                for (File ff : ds) {
                    DefaultMutableTreeNode df = new DefaultMutableTreeNode(ff);
                    if (hasAsChildren(folder, df)) continue;
                    open(df, depth - 1);
                    folder.add(df);
                }
                for (File ff : fs) {
                    DefaultMutableTreeNode df = new DefaultMutableTreeNode(ff);
                    if (hasAsChildren(folder, df)) continue;
                    folder.add(df);
                }
            }
        }
    }

    private boolean hasAsChildren(DefaultMutableTreeNode parent, DefaultMutableTreeNode element) {
        Enumeration<DefaultMutableTreeNode> children = parent.children();
        while (children.hasMoreElements()) {
            DefaultMutableTreeNode child = children.nextElement();
            File f1 = (File) child.getUserObject();
            File f2 = (File) element.getUserObject();
            try {
                if (f1.getCanonicalPath().equals(f2.getCanonicalPath())) {
                    return true;
                }
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }

    private DefaultListModel getFormatList() {
        DefaultListModel<String> listModel = new DefaultListModel<String>();
        for (String format : Serializer.listFormat()) {
            listModel.addElement(format);
        }
        return listModel;
    }

}
