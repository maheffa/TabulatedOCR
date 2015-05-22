import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.List;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import javax.swing.*;
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

    private void createUIComponents() {
        // TODO: add custom component creation code here
    }

    private void menuCreateFormatActionPerformed(ActionEvent e) {
        GUIUtil.createFrameForPanel("Создание табличного формата", new CreateTableFormat());
    }

    private void menuQuitActionPerformed(ActionEvent e) {
        close();
    }

    private void menuConfigActionPerformed(ActionEvent e) {
        GUIUtil.createFrameForPanel("Параметры конфигурации", new Configuration());
    }

    private void menuCreateTextFormatActionPerformed(ActionEvent e) {
        GUIUtil.createFrameForPanel("Создание текстового формата", new CreateTextFormat());
    }

    private void menuLaunchActionPerformed(ActionEvent e) {
        // TODO: implement launching process
    }

    private void menuRelaunchActionPerformed(ActionEvent e) {
        // TODO: implement relaunching process
    }

    private void menuStopActionPerformed(ActionEvent e) {
        // TODO: implement process stoping
    }

    private void menuAboutActionPerformed(ActionEvent e) {
        GUIUtil.createFrameForPanel("О прилжении", new About());
    }

    private void butAddProjectActionPerformed(ActionEvent e) {
        GUIUtil.createFrameForPanel("Создать проект", new CreateProject(this));
    }

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Mahefa Manitrativo
        mainFrame = new JFrame();
        menuBar1 = new JMenuBar();
        menu1 = new JMenu();
        menuQuit = new JMenuItem();
        menu3 = new JMenu();
        menuConfig = new JMenuItem();
        menuCreateTableFormat = new JMenuItem();
        menuCreateTextFormat = new JMenuItem();
        menu5 = new JMenu();
        menuLaunch = new JMenuItem();
        menuStop = new JMenuItem();
        menuRelaunch = new JMenuItem();
        menu4 = new JMenu();
        menuAbout = new JMenuItem();
        splitPane2 = new JSplitPane();
        panel1 = new JPanel();
        splitPane4 = new JSplitPane();
        panel14 = new JPanel();
        scrollPane5 = new JScrollPane();
        listProject = new JList();
        panel5 = new JPanel();
        butEditProject = new JButton();
        butAddProject = new JButton();
        butDeleteProject = new JButton();
        scrollPane2 = new JScrollPane();
        label3 = new JLabel();
        splitPane3 = new JSplitPane();
        panel3 = new JPanel();
        scrollPane3 = new JScrollPane();
        panelResult = new JTabbedPane();
        panelImage = new JPanel();
        panel12 = new JPanel();
        panel13 = new JPanel();
        button1 = new JButton();
        scrollPane4 = new JScrollPane();
        tabbedPane2 = new JTabbedPane();
        panelBinary = new JPanel();
        panelHough = new JPanel();
        panelDeskew = new JPanel();
        panelTable = new JPanel();
        panelCell = new JPanel();
        label4 = new JLabel();
        progressBar1 = new JProgressBar();
        panel2 = new JPanel();
        panel4 = new JPanel();
        scrollPane6 = new JScrollPane();
        list1 = new JList();
        butEditFormat = new JButton();
        butDeleteFormat = new JButton();
        scrollPane1 = new JScrollPane();
        label2 = new JLabel();

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
                    menu1.addSeparator();
                    menu1.addSeparator();

                    //---- menuQuit ----
                    menuQuit.setText("\u0412\u044b\u0439\u0442\u0438");
                    menuQuit.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            menuQuitActionPerformed(e);
                        }
                    });
                    menu1.add(menuQuit);
                }
                menuBar1.add(menu1);

                //======== menu3 ========
                {
                    menu3.setText("\u041a\u043e\u043d\u0444\u0438\u0433\u0443\u0440\u0430\u0446\u0438\u044f");

                    //---- menuConfig ----
                    menuConfig.setText("\u041f\u0430\u0440\u0430\u043c\u0435\u0442\u0440\u044b \u0440\u0430\u0441\u043f\u043e\u0437\u043d\u0430\u0432\u043e\u043d\u0438\u044f");
                    menuConfig.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            menuConfigActionPerformed(e);
                        }
                    });
                    menu3.add(menuConfig);
                    menu3.addSeparator();

                    //---- menuCreateTableFormat ----
                    menuCreateTableFormat.setText("\u0421\u043e\u0437\u0430\u0434\u0430\u0442\u044c \u0442\u0430\u0431\u043b\u0438\u0447\u043d\u044b\u0439 \u0444\u043e\u0440\u043c\u0430\u0442");
                    menuCreateTableFormat.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            menuCreateFormatActionPerformed(e);
                        }
                    });
                    menu3.add(menuCreateTableFormat);

                    //---- menuCreateTextFormat ----
                    menuCreateTextFormat.setText("\u0421\u043e\u0437\u0434\u0430\u0442\u044c \u0442\u0435\u043a\u0441\u0442\u043e\u0432\u043e\u0439 \u0444\u043e\u0440\u043c\u0430\u0442");
                    menuCreateTextFormat.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            menuCreateTextFormatActionPerformed(e);
                        }
                    });
                    menu3.add(menuCreateTextFormat);
                }
                menuBar1.add(menu3);

                //======== menu5 ========
                {
                    menu5.setText("\u0420\u0430\u0441\u043f\u043e\u0437\u043d\u043e\u0432\u0430\u043d\u0438\u0435");

                    //---- menuLaunch ----
                    menuLaunch.setText("\u0417\u0430\u043f\u0443\u0441\u0442\u0438\u0442\u044c");
                    menuLaunch.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            menuLaunchActionPerformed(e);
                        }
                    });
                    menu5.add(menuLaunch);

                    //---- menuStop ----
                    menuStop.setText("\u041e\u0441\u0442\u0430\u043d\u043e\u0432\u0438\u0442\u044c");
                    menuStop.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            menuStopActionPerformed(e);
                        }
                    });
                    menu5.add(menuStop);

                    //---- menuRelaunch ----
                    menuRelaunch.setText("\u041f\u0435\u0440\u0435\u0437\u0430\u043f\u0443\u0441\u0442\u044c");
                    menuRelaunch.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            menuRelaunchActionPerformed(e);
                        }
                    });
                    menu5.add(menuRelaunch);
                }
                menuBar1.add(menu5);

                //======== menu4 ========
                {
                    menu4.setText("\u041f\u043e\u043c\u043e\u0449\u044c");

                    //---- menuAbout ----
                    menuAbout.setText("\u041e \u043f\u0440\u0438\u043b\u043e\u0436\u0435\u043d\u0438\u0438");
                    menuAbout.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            menuAboutActionPerformed(e);
                        }
                    });
                    menu4.add(menuAbout);
                }
                menuBar1.add(menu4);
            }
            mainFrame.setJMenuBar(menuBar1);

            //======== splitPane2 ========
            {

                //======== panel1 ========
                {
                    panel1.setPreferredSize(new Dimension(250, 449));
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
                        splitPane4.setResizeWeight(0.5);

                        //======== panel14 ========
                        {
                            panel14.setBorder(Borders.DLU4);
                            panel14.setPreferredSize(new Dimension(169, 400));
                            panel14.setLayout(new GridBagLayout());
                            ((GridBagLayout)panel14.getLayout()).columnWidths = new int[] {71, 0};
                            ((GridBagLayout)panel14.getLayout()).rowHeights = new int[] {0, 0, 0};
                            ((GridBagLayout)panel14.getLayout()).columnWeights = new double[] {0.0, 1.0E-4};
                            ((GridBagLayout)panel14.getLayout()).rowWeights = new double[] {0.0, 0.0, 1.0E-4};

                            //======== scrollPane5 ========
                            {
                                scrollPane5.setViewportView(listProject);
                            }
                            panel14.add(scrollPane5, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0,
                                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                                new Insets(0, 0, 5, 0), 0, 0));

                            //======== panel5 ========
                            {
                                panel5.setLayout(new GridBagLayout());
                                ((GridBagLayout)panel5.getLayout()).columnWidths = new int[] {0, 0, 0, 0};
                                ((GridBagLayout)panel5.getLayout()).rowHeights = new int[] {0, 0, 0, 0};
                                ((GridBagLayout)panel5.getLayout()).columnWeights = new double[] {0.0, 0.0, 0.0, 1.0E-4};
                                ((GridBagLayout)panel5.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 1.0E-4};

                                //---- butEditProject ----
                                butEditProject.setIcon(null);
                                butEditProject.setPreferredSize(new Dimension(35, 25));
                                panel5.add(butEditProject, new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0,
                                    GridBagConstraints.EAST, GridBagConstraints.VERTICAL,
                                    new Insets(0, 0, 5, 5), 0, 0));

                                //---- butAddProject ----
                                butAddProject.setPreferredSize(new Dimension(35, 25));
                                butAddProject.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        butAddProjectActionPerformed(e);
                                    }
                                });
                                panel5.add(butAddProject, new GridBagConstraints(1, 0, 1, 1, 1.0, 0.0,
                                    GridBagConstraints.EAST, GridBagConstraints.VERTICAL,
                                    new Insets(0, 0, 5, 5), 0, 0));

                                //---- butDeleteProject ----
                                butDeleteProject.setPreferredSize(new Dimension(35, 25));
                                panel5.add(butDeleteProject, new GridBagConstraints(2, 0, 1, 1, 1.0, 0.0,
                                    GridBagConstraints.EAST, GridBagConstraints.VERTICAL,
                                    new Insets(0, 0, 5, 0), 0, 0));
                            }
                            panel14.add(panel5, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
                                GridBagConstraints.EAST, GridBagConstraints.VERTICAL,
                                new Insets(0, 0, 0, 0), 0, 0));
                        }
                        splitPane4.setTopComponent(panel14);

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

                            //======== panelResult ========
                            {

                                //======== panelImage ========
                                {
                                    panelImage.setLayout(new GridBagLayout());
                                    ((GridBagLayout)panelImage.getLayout()).columnWidths = new int[] {0, 0};
                                    ((GridBagLayout)panelImage.getLayout()).rowHeights = new int[] {0, 0};
                                    ((GridBagLayout)panelImage.getLayout()).columnWeights = new double[] {0.0, 1.0E-4};
                                    ((GridBagLayout)panelImage.getLayout()).rowWeights = new double[] {0.0, 1.0E-4};
                                }
                                panelResult.addTab("\u0418\u0441\u0445\u043e\u0434\u043d\u043e\u0435", panelImage);

                                //======== panel12 ========
                                {
                                    panel12.setLayout(new GridBagLayout());
                                    ((GridBagLayout)panel12.getLayout()).columnWidths = new int[] {0, 0};
                                    ((GridBagLayout)panel12.getLayout()).rowHeights = new int[] {0, 0, 0};
                                    ((GridBagLayout)panel12.getLayout()).columnWeights = new double[] {0.0, 1.0E-4};
                                    ((GridBagLayout)panel12.getLayout()).rowWeights = new double[] {0.0, 0.0, 1.0E-4};

                                    //======== panel13 ========
                                    {
                                        panel13.setLayout(new GridBagLayout());
                                        ((GridBagLayout)panel13.getLayout()).columnWidths = new int[] {0, 0};
                                        ((GridBagLayout)panel13.getLayout()).rowHeights = new int[] {0, 0};
                                        ((GridBagLayout)panel13.getLayout()).columnWeights = new double[] {0.0, 1.0E-4};
                                        ((GridBagLayout)panel13.getLayout()).rowWeights = new double[] {0.0, 1.0E-4};
                                    }
                                    panel12.add(panel13, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0,
                                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                                        new Insets(0, 0, 5, 0), 0, 0));

                                    //---- button1 ----
                                    button1.setText("\u042d\u043a\u0441\u043f\u043e\u0440\u0442\u0438\u0440\u043e\u0432\u0430\u0442\u044c");
                                    panel12.add(button1, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
                                        GridBagConstraints.EAST, GridBagConstraints.VERTICAL,
                                        new Insets(0, 0, 0, 0), 0, 0));
                                }
                                panelResult.addTab("\u0420\u0435\u0437\u0443\u043b\u044c\u0442\u0430\u0442", panel12);
                            }
                            scrollPane3.setViewportView(panelResult);
                        }
                        panel3.add(scrollPane3, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0,
                            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                            new Insets(0, 0, 0, 0), 0, 0));

                        //======== scrollPane4 ========
                        {
                            scrollPane4.setPreferredSize(new Dimension(0, 300));
                            scrollPane4.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
                            scrollPane4.setBorder(Borders.DLU4);

                            //======== tabbedPane2 ========
                            {

                                //======== panelBinary ========
                                {
                                    panelBinary.setLayout(new GridBagLayout());
                                    ((GridBagLayout)panelBinary.getLayout()).columnWidths = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
                                    ((GridBagLayout)panelBinary.getLayout()).rowHeights = new int[] {0, 0, 0, 0, 0, 0, 0};
                                    ((GridBagLayout)panelBinary.getLayout()).columnWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};
                                    ((GridBagLayout)panelBinary.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};
                                }
                                tabbedPane2.addTab("\u0411\u0438\u043d\u0430\u0440\u043d\u043e\u0435", panelBinary);

                                //======== panelHough ========
                                {
                                    panelHough.setLayout(new GridBagLayout());
                                    ((GridBagLayout)panelHough.getLayout()).columnWidths = new int[] {0, 0};
                                    ((GridBagLayout)panelHough.getLayout()).rowHeights = new int[] {0, 0};
                                    ((GridBagLayout)panelHough.getLayout()).columnWeights = new double[] {0.0, 1.0E-4};
                                    ((GridBagLayout)panelHough.getLayout()).rowWeights = new double[] {0.0, 1.0E-4};
                                }
                                tabbedPane2.addTab("\u0425\u0430\u0444", panelHough);

                                //======== panelDeskew ========
                                {
                                    panelDeskew.setLayout(new GridBagLayout());
                                    ((GridBagLayout)panelDeskew.getLayout()).columnWidths = new int[] {0, 0};
                                    ((GridBagLayout)panelDeskew.getLayout()).rowHeights = new int[] {0, 0};
                                    ((GridBagLayout)panelDeskew.getLayout()).columnWeights = new double[] {0.0, 1.0E-4};
                                    ((GridBagLayout)panelDeskew.getLayout()).rowWeights = new double[] {0.0, 1.0E-4};
                                }
                                tabbedPane2.addTab("\u0423\u0441\u0442\u0440\u0430\u043d\u0451\u043d\u043d\u043e\u0435", panelDeskew);

                                //======== panelTable ========
                                {
                                    panelTable.setLayout(new GridBagLayout());
                                    ((GridBagLayout)panelTable.getLayout()).columnWidths = new int[] {0, 0};
                                    ((GridBagLayout)panelTable.getLayout()).rowHeights = new int[] {0, 0};
                                    ((GridBagLayout)panelTable.getLayout()).columnWeights = new double[] {0.0, 1.0E-4};
                                    ((GridBagLayout)panelTable.getLayout()).rowWeights = new double[] {0.0, 1.0E-4};
                                }
                                tabbedPane2.addTab("\u0422\u0430\u0431\u043b\u0438\u0446\u0430", panelTable);

                                //======== panelCell ========
                                {
                                    panelCell.setLayout(new GridBagLayout());
                                    ((GridBagLayout)panelCell.getLayout()).columnWidths = new int[] {0, 0};
                                    ((GridBagLayout)panelCell.getLayout()).rowHeights = new int[] {0, 0};
                                    ((GridBagLayout)panelCell.getLayout()).columnWeights = new double[] {0.0, 1.0E-4};
                                    ((GridBagLayout)panelCell.getLayout()).rowWeights = new double[] {0.0, 1.0E-4};
                                }
                                tabbedPane2.addTab("\u041a\u043b\u0435\u0442\u043a\u0438", panelCell);
                            }
                            scrollPane4.setViewportView(tabbedPane2);
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

                        //======== panel4 ========
                        {
                            panel4.setBorder(Borders.DLU4);
                            panel4.setLayout(new GridBagLayout());
                            ((GridBagLayout)panel4.getLayout()).columnWidths = new int[] {0, 0, 0};
                            ((GridBagLayout)panel4.getLayout()).rowHeights = new int[] {0, 0, 0, 0};
                            ((GridBagLayout)panel4.getLayout()).columnWeights = new double[] {0.0, 0.0, 1.0E-4};
                            ((GridBagLayout)panel4.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 1.0E-4};

                            //======== scrollPane6 ========
                            {
                                scrollPane6.setViewportView(list1);
                            }
                            panel4.add(scrollPane6, new GridBagConstraints(0, 0, 2, 1, 1.0, 1.0,
                                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                                new Insets(0, 0, 5, 0), 0, 0));

                            //---- butEditFormat ----
                            butEditFormat.setHorizontalAlignment(SwingConstants.LEFT);
                            butEditFormat.setPreferredSize(new Dimension(35, 25));
                            panel4.add(butEditFormat, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
                                GridBagConstraints.EAST, GridBagConstraints.VERTICAL,
                                new Insets(0, 0, 5, 5), 0, 0));

                            //---- butDeleteFormat ----
                            butDeleteFormat.setHorizontalAlignment(SwingConstants.RIGHT);
                            butDeleteFormat.setPreferredSize(new Dimension(35, 25));
                            panel4.add(butDeleteFormat, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
                                GridBagConstraints.EAST, GridBagConstraints.VERTICAL,
                                new Insets(0, 0, 5, 0), 0, 0));
                        }
                        panel2.add(panel4, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0,
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
                        panel2.add(scrollPane1, new GridBagConstraints(0, 1, 1, 1, 0.0, 1.0,
                            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                            new Insets(0, 0, 0, 0), 0, 0));
                    }
                    splitPane3.setRightComponent(panel2);
                }
                splitPane2.setRightComponent(splitPane3);
            }
            mainFrameContentPane.add(splitPane2);
            mainFrame.setSize(855, 720);
            mainFrame.setLocationRelativeTo(mainFrame.getOwner());
        }
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Mahefa Manitrativo
    private JFrame mainFrame;
    private JMenuBar menuBar1;
    private JMenu menu1;
    private JMenuItem menuQuit;
    private JMenu menu3;
    private JMenuItem menuConfig;
    private JMenuItem menuCreateTableFormat;
    private JMenuItem menuCreateTextFormat;
    private JMenu menu5;
    private JMenuItem menuLaunch;
    private JMenuItem menuStop;
    private JMenuItem menuRelaunch;
    private JMenu menu4;
    private JMenuItem menuAbout;
    private JSplitPane splitPane2;
    private JPanel panel1;
    private JSplitPane splitPane4;
    private JPanel panel14;
    private JScrollPane scrollPane5;
    private JList listProject;
    private JPanel panel5;
    private JButton butEditProject;
    private JButton butAddProject;
    private JButton butDeleteProject;
    private JScrollPane scrollPane2;
    private JLabel label3;
    private JSplitPane splitPane3;
    private JPanel panel3;
    private JScrollPane scrollPane3;
    private JTabbedPane panelResult;
    private JPanel panelImage;
    private JPanel panel12;
    private JPanel panel13;
    private JButton button1;
    private JScrollPane scrollPane4;
    private JTabbedPane tabbedPane2;
    private JPanel panelBinary;
    private JPanel panelHough;
    private JPanel panelDeskew;
    private JPanel panelTable;
    private JPanel panelCell;
    private JLabel label4;
    private JProgressBar progressBar1;
    private JPanel panel2;
    private JPanel panel4;
    private JScrollPane scrollPane6;
    private JList list1;
    private JButton butEditFormat;
    private JButton butDeleteFormat;
    private JScrollPane scrollPane1;
    private JLabel label2;
	// JFormDesigner - End of variables declaration  //GEN-END:variables

    private Parameters paramater;
    private DefaultListModel<String> formatListModel = null;
    private DefaultListModel<String> projectListModel = null;
    private List<Format> formatList = null;
    private List<Project> projectList = null;
    private DBAccess dbAccess;

    public OcrMainForm(Parameters paramater) {
        super();
        this.paramater = paramater;
        initComponents();
        // launch GUI
        dbAccess = DBAccess.getDbAccess();
        // setting button icons
        GUIUtil.setButtonIcon(this.butEditProject, "icons/edit.png");
        GUIUtil.setButtonIcon(this.butAddProject, "icons/add.png");
        GUIUtil.setButtonIcon(this.butDeleteProject, "icons/delete.png");
        GUIUtil.setButtonIcon(this.butEditFormat, "icons/edit.png");
        GUIUtil.setButtonIcon(this.butDeleteFormat, "icons/delete.png");
        // updating lists
        projectListModel = new DefaultListModel<String>();
        formatListModel = new DefaultListModel<String>();
        updateProjectList();
        updateFormatList();
    }

    public void close() {
        dbAccess.close();
        this.getMainFrame().setVisible(false);
        this.getMainFrame().dispose();
        System.exit(0);
    }

    public JFrame getMainFrame() {
        return this.mainFrame;
    }

    public void drawImage(BufferedImage img) {
        GUIUtil.drawImageOnPanel(this.panelImage, img);
    }

    public void updateProjectList() {
        projectList = dbAccess.listProject();
        projectListModel.clear();
        for (Project project : projectList) {
            projectListModel.addElement(project.getName());
        }
    }

    public void updateFormatList() {
        formatList = dbAccess.listFormat();
        formatListModel.clear();
        for (Format format : formatList) {
            formatListModel.addElement(format.getName());
        }
    }
}
