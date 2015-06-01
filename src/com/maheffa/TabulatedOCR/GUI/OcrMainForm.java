package com.maheffa.TabulatedOCR.GUI;

import com.jgoodies.forms.factories.Borders;
import com.maheffa.TabulatedOCR.DBManager.DBAccess;
import com.maheffa.TabulatedOCR.DBManager.Format;
import com.maheffa.TabulatedOCR.DBManager.Ocrconfig;
import com.maheffa.TabulatedOCR.DBManager.Project;
import com.maheffa.TabulatedOCR.ImageProcessing.ImgProcUtil;
import com.maheffa.TabulatedOCR.Runner;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.List;
/*
 * Created by JFormDesigner on Sun Apr 26 16:23:28 MSK 2015
 */


/**
 * @author Boubakar Tilojab
 */
public class OcrMainForm  {

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
    private JButton butAddProject;
    private JButton butEditProject;
    private JButton butDeleteProject;
    private JScrollPane scrollPane2;
    private JLabel labelInfoProject;
    private JSplitPane splitPane3;
    private JPanel panel3;
    private JScrollPane scrollPane3;
    private JTabbedPane tabbedPaneData;
    private JPanel panelImage;
    private JPanel panel12;
    private JPanel panelResult;
    private JScrollPane scrollPane7;
    private JTextArea txtResult;
    private JButton button1;
    private JScrollPane scrollPane4;
    private JTabbedPane tabbedPaneProgress;
    private JPanel panelBinary;
    private JPanel panelHough;
    private JPanel panelDeskew;
    private JPanel panelTable;
    private JPanel panelCell;
    private JLabel labelProgress;
    private JProgressBar progressbar;
    private JPanel panel2;
    private JPanel panel4;
    private JScrollPane scrollPane6;
    private JList listFormat;
    private JButton butEditFormat;
    private JButton butDeleteFormat;
    private JScrollPane scrollPane1;
    private JLabel labelInfoFormat;
    private DefaultListModel<String> formatListModel = null;
    private DefaultListModel<String> projectListModel = null;
    private List<Format> formatList = null;
    private List<Project> projectList = null;
    private DBAccess dbAccess;
    private HashMap<String, BufferedImage> cacheImage = null;
    private Runner runner;

    public OcrMainForm() {
        super();
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
        // create memory cache
        cacheImage = new HashMap<String, BufferedImage>();
    }

    private void menuCreateFormatActionPerformed(ActionEvent e) {
        GUIUtil.createFrameForPanel("Создание табличного формата", new CreateTableFormat(this));
    }

    private void menuQuitActionPerformed(ActionEvent e) {
        close();
    }

    private void menuConfigActionPerformed(ActionEvent e) {
        GUIUtil.createFrameForPanel("Параметры конфигурации", new Configuration());
    }

    private void menuCreateTextFormatActionPerformed(ActionEvent e) {
        GUIUtil.createFrameForPanel("Создание текстового формата", new CreateTextFormat(this));
    }

    private void menuLaunchActionPerformed(ActionEvent e) {
        if (listFormat.getSelectedIndex() < 0) {
            JOptionPane.showMessageDialog(this.mainFrame, "Формат не указан");
        } else if (listProject.getSelectedIndex() < 0) {
            JOptionPane.showMessageDialog(this.mainFrame, "Проект не указан");
        } else {
            runner = new Runner(this);
            SwingUtilities.invokeLater(runner);
        }
    }

    private void menuRelaunchActionPerformed(ActionEvent e) {
        // TODO: implement relaunching process
    }

    private void menuStopActionPerformed(ActionEvent e) {
        runner.stop();
    }

    private void menuAboutActionPerformed(ActionEvent e) {
        GUIUtil.createFrameForPanel("О прилжении", new About());
    }

    private void butAddProjectActionPerformed(ActionEvent e) {
        GUIUtil.createFrameForPanel("Создать проект", new CreateProject(this));
    }

    private void butEditProjectActionPerformed(ActionEvent e) {
        Project p = projectList.get(listProject.getSelectedIndex());
        GUIUtil.createFrameForPanel("Изменить проект", new CreateProject(this, p));
    }

    private void butDeleteProjectActionPerformed(ActionEvent e) {
        Project p = projectList.get(listProject.getSelectedIndex());
        dbAccess.deleteEntry(p);
        updateProjectList();
    }

    private void butEditFormatActionPerformed(ActionEvent e) {
        Format f = DBAccess.getDbAccess().getFormatByName((String) listFormat.getSelectedValue());
        if (f.getType().equalsIgnoreCase("table")) {
            GUIUtil.createFrameForPanel("Изменить формат", new CreateTableFormat(this, f));
        } else {
            GUIUtil.createFrameForPanel("Изменить формат", new CreateTextFormat(this, f));
        }
    }

    public JPanel getPanelBinary() {
        return panelBinary;
    }

    public JPanel getPanelCell() {
        return panelCell;
    }

    public JPanel getPanelDeskew() {
        return panelDeskew;
    }

    public JPanel getPanelHough() {
        return panelHough;
    }

    public JPanel getPanelImage() {
        return panelImage;
    }

    public JPanel getPanelTable() {
        return panelTable;
    }

    public JProgressBar getProgressbar() {
        return progressbar;
    }
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    public JLabel getLabelProgress() {
        return labelProgress;
    }

    public JPanel getPanelResult() {
        return panelResult;
    }

    public JTabbedPane getTabbedPaneData() {
        return tabbedPaneData;
    }

    public JTabbedPane getTabbedPaneProgress() {
        return tabbedPaneProgress;
    }

    private void createUIComponents() {
    }

    private void listProjectValueChanged(ListSelectionEvent e) {
        if (listProject.getSelectedIndex() < 0) {
            labelInfoProject.setText("");
            ((ImagePanel) this.panelImage).clear();
        } else {
            Project project = projectList.get(listProject.getSelectedIndex());
            StringBuilder str = new StringBuilder();
            str.append(project.toStringHTML());
            str.append(ImgProcUtil.getImageInfoHTML(((ImagePanel) panelImage).getImage()));
            labelInfoProject.setText("<html>" + project.toStringHTML() + "</html>");
            if (!cacheImage.containsKey(project.getInputFilePath())) {
                cacheImage.put(project.getInputFilePath(), ImgProcUtil.readImage(project.getInputFilePath()));
            }
            ((ImagePanel) this.panelImage).setImage(cacheImage.get(project.getInputFilePath()));
        }
    }

    private void listFormatValueChanged(ListSelectionEvent e) {
        if (listProject.getSelectedIndex() < 0) {
            labelInfoFormat.setText("");
        } else {
            Format format = formatList.get(listFormat.getSelectedIndex());
            labelInfoFormat.setText("<html>" + format.toStringHTML() + "</html>");
        }
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
        butAddProject = new JButton();
        butEditProject = new JButton();
        butDeleteProject = new JButton();
        scrollPane2 = new JScrollPane();
        labelInfoProject = new JLabel();
        splitPane3 = new JSplitPane();
        panel3 = new JPanel();
        scrollPane3 = new JScrollPane();
        tabbedPaneData = new JTabbedPane();
        panelImage = new ImagePanel();
        panel12 = new JPanel();
        panelResult = new ImagePanel();
        scrollPane7 = new JScrollPane();
        txtResult = new JTextArea();
        button1 = new JButton();
        scrollPane4 = new JScrollPane();
        tabbedPaneProgress = new JTabbedPane();
        panelBinary = new ImagePanel();
        panelHough = new ImagePanel();
        panelDeskew = new ImagePanel();
        panelTable = new ImagePanel();
        panelCell = new ImagePanel();
        labelProgress = new JLabel();
        progressbar = new JProgressBar();
        panel2 = new JPanel();
        panel4 = new JPanel();
        scrollPane6 = new JScrollPane();
        listFormat = new JList();
        butEditFormat = new JButton();
        butDeleteFormat = new JButton();
        scrollPane1 = new JScrollPane();
        labelInfoFormat = new JLabel();

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
                    menu1.setIcon(null);
                    menu1.addSeparator();
                    menu1.addSeparator();

                    //---- menuQuit ----
                    menuQuit.setText("\u0412\u044b\u0439\u0442\u0438");
                    menuQuit.setIcon(new ImageIcon("/home/mahefa/Workspace/Java/Tabulated OCR/icons/exit_small.png"));
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
                    menu3.setIcon(null);

                    //---- menuConfig ----
                    menuConfig.setText("\u041f\u0430\u0440\u0430\u043c\u0435\u0442\u0440\u044b \u0440\u0430\u0441\u043f\u043e\u0437\u043d\u0430\u0432\u043e\u043d\u0438\u044f");
                    menuConfig.setIcon(new ImageIcon("/home/mahefa/Workspace/Java/Tabulated OCR/icons/setting_small.png"));
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
                    menuCreateTableFormat.setIcon(new ImageIcon("/home/mahefa/Workspace/Java/Tabulated OCR/icons/add_small.png"));
                    menuCreateTableFormat.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            menuCreateFormatActionPerformed(e);
                        }
                    });
                    menu3.add(menuCreateTableFormat);

                    //---- menuCreateTextFormat ----
                    menuCreateTextFormat.setText("\u0421\u043e\u0437\u0434\u0430\u0442\u044c \u0442\u0435\u043a\u0441\u0442\u043e\u0432\u043e\u0439 \u0444\u043e\u0440\u043c\u0430\u0442");
                    menuCreateTextFormat.setIcon(new ImageIcon("/home/mahefa/Workspace/Java/Tabulated OCR/icons/add_small.png"));
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
                    menuLaunch.setIcon(new ImageIcon("/home/mahefa/Workspace/Java/Tabulated OCR/icons/play_small.png"));
                    menuLaunch.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            menuLaunchActionPerformed(e);
                        }
                    });
                    menu5.add(menuLaunch);

                    //---- menuStop ----
                    menuStop.setText("\u041e\u0441\u0442\u0430\u043d\u043e\u0432\u0438\u0442\u044c");
                    menuStop.setIcon(new ImageIcon("/home/mahefa/Workspace/Java/Tabulated OCR/icons/stop_small.png"));
                    menuStop.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            menuStopActionPerformed(e);
                        }
                    });
                    menu5.add(menuStop);

                    //---- menuRelaunch ----
                    menuRelaunch.setText("\u041f\u0435\u0440\u0435\u0437\u0430\u043f\u0443\u0441\u0442\u044c");
                    menuRelaunch.setIcon(new ImageIcon("/home/mahefa/Workspace/Java/Tabulated OCR/icons/replay_small.png"));
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
                    panel1.setBorder(new TitledBorder("\u041f\u0440\u043e\u0435\u043a\u0442\u044b"));
                    panel1.setMinimumSize(new Dimension(150, 447));

                    // JFormDesigner evaluation mark
                    panel1.setBorder(new javax.swing.border.CompoundBorder(
                            new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
                                    "JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
                                    javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
                                    java.awt.Color.red), panel1.getBorder()));
                    panel1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
                        public void propertyChange(java.beans.PropertyChangeEvent e) {
                            if ("border".equals(e.getPropertyName())) throw new RuntimeException();
                        }
                    });

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
                            ((GridBagLayout) panel14.getLayout()).columnWidths = new int[]{71, 0};
                            ((GridBagLayout) panel14.getLayout()).rowHeights = new int[]{0, 0, 0};
                            ((GridBagLayout) panel14.getLayout()).columnWeights = new double[]{0.0, 1.0E-4};
                            ((GridBagLayout) panel14.getLayout()).rowWeights = new double[]{0.0, 0.0, 1.0E-4};

                            //======== scrollPane5 ========
                            {

                                //---- listProject ----
                                listProject.addListSelectionListener(new ListSelectionListener() {
                                    @Override
                                    public void valueChanged(ListSelectionEvent e) {
                                        listProjectValueChanged(e);
                                    }
                                });
                                scrollPane5.setViewportView(listProject);
                            }
                            panel14.add(scrollPane5, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0,
                                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                                    new Insets(0, 0, 5, 0), 0, 0));

                            //======== panel5 ========
                            {
                                panel5.setLayout(new GridBagLayout());
                                ((GridBagLayout) panel5.getLayout()).columnWidths = new int[]{0, 0, 0, 0};
                                ((GridBagLayout) panel5.getLayout()).rowHeights = new int[]{0, 0, 0, 0};
                                ((GridBagLayout) panel5.getLayout()).columnWeights = new double[]{0.0, 0.0, 0.0, 1.0E-4};
                                ((GridBagLayout) panel5.getLayout()).rowWeights = new double[]{0.0, 0.0, 0.0, 1.0E-4};

                                //---- butAddProject ----
                                butAddProject.setPreferredSize(new Dimension(35, 25));
                                butAddProject.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        butAddProjectActionPerformed(e);
                                    }
                                });
                                panel5.add(butAddProject, new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0,
                                        GridBagConstraints.EAST, GridBagConstraints.VERTICAL,
                                        new Insets(0, 0, 5, 5), 0, 0));

                                //---- butEditProject ----
                                butEditProject.setIcon(null);
                                butEditProject.setPreferredSize(new Dimension(35, 25));
                                butEditProject.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        butEditProjectActionPerformed(e);
                                    }
                                });
                                panel5.add(butEditProject, new GridBagConstraints(1, 0, 1, 1, 1.0, 0.0,
                                        GridBagConstraints.EAST, GridBagConstraints.VERTICAL,
                                        new Insets(0, 0, 5, 5), 0, 0));

                                //---- butDeleteProject ----
                                butDeleteProject.setPreferredSize(new Dimension(35, 25));
                                butDeleteProject.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        butDeleteProjectActionPerformed(e);
                                    }
                                });
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
                            scrollPane2.setViewportView(labelInfoProject);
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
                        ((GridBagLayout) panel3.getLayout()).columnWidths = new int[]{0, 0};
                        ((GridBagLayout) panel3.getLayout()).rowHeights = new int[]{0, 0, 0, 0, 0};
                        ((GridBagLayout) panel3.getLayout()).columnWeights = new double[]{1.0, 1.0E-4};
                        ((GridBagLayout) panel3.getLayout()).rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0E-4};

                        //======== scrollPane3 ========
                        {
                            scrollPane3.setPreferredSize(new Dimension(0, 300));
                            scrollPane3.setBackground(new Color(153, 153, 153));
                            scrollPane3.setOpaque(false);
                            scrollPane3.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
                            scrollPane3.setBorder(Borders.DLU4);

                            //======== tabbedPaneData ========
                            {

                                //======== panelImage ========
                                {
                                    panelImage.setLayout(new GridBagLayout());
                                    ((GridBagLayout) panelImage.getLayout()).columnWidths = new int[]{0, 0};
                                    ((GridBagLayout) panelImage.getLayout()).rowHeights = new int[]{0, 0};
                                    ((GridBagLayout) panelImage.getLayout()).columnWeights = new double[]{0.0, 1.0E-4};
                                    ((GridBagLayout) panelImage.getLayout()).rowWeights = new double[]{0.0, 1.0E-4};
                                }
                                tabbedPaneData.addTab("\u0418\u0441\u0445\u043e\u0434\u043d\u043e\u0435", panelImage);

                                //======== panel12 ========
                                {
                                    panel12.setLayout(new GridBagLayout());
                                    ((GridBagLayout) panel12.getLayout()).columnWidths = new int[]{0, 0};
                                    ((GridBagLayout) panel12.getLayout()).rowHeights = new int[]{0, 0, 0};
                                    ((GridBagLayout) panel12.getLayout()).columnWeights = new double[]{0.0, 1.0E-4};
                                    ((GridBagLayout) panel12.getLayout()).rowWeights = new double[]{0.0, 0.0, 1.0E-4};

                                    //======== panelResult ========
                                    {
                                        panelResult.setLayout(new GridBagLayout());
                                        ((GridBagLayout) panelResult.getLayout()).columnWidths = new int[]{0, 0};
                                        ((GridBagLayout) panelResult.getLayout()).rowHeights = new int[]{0, 0};
                                        ((GridBagLayout) panelResult.getLayout()).columnWeights = new double[]{0.0, 1.0E-4};
                                        ((GridBagLayout) panelResult.getLayout()).rowWeights = new double[]{0.0, 1.0E-4};

                                        //======== scrollPane7 ========
                                        {
                                            scrollPane7.setViewportView(txtResult);
                                        }
                                        panelResult.add(scrollPane7, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0,
                                                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                                                new Insets(0, 0, 0, 0), 0, 0));
                                    }
                                    panel12.add(panelResult, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0,
                                            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                                            new Insets(0, 0, 5, 0), 0, 0));

                                    //---- button1 ----
                                    button1.setText("\u042d\u043a\u0441\u043f\u043e\u0440\u0442\u0438\u0440\u043e\u0432\u0430\u0442\u044c");
                                    panel12.add(button1, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
                                            GridBagConstraints.EAST, GridBagConstraints.VERTICAL,
                                            new Insets(0, 0, 0, 0), 0, 0));
                                }
                                tabbedPaneData.addTab("\u0420\u0435\u0437\u0443\u043b\u044c\u0442\u0430\u0442", panel12);
                            }
                            scrollPane3.setViewportView(tabbedPaneData);
                        }
                        panel3.add(scrollPane3, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0,
                                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                                new Insets(0, 0, 0, 0), 0, 0));

                        //======== scrollPane4 ========
                        {
                            scrollPane4.setPreferredSize(new Dimension(0, 300));
                            scrollPane4.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
                            scrollPane4.setBorder(Borders.DLU4);

                            //======== tabbedPaneProgress ========
                            {

                                //======== panelBinary ========
                                {
                                    panelBinary.setLayout(new GridBagLayout());
                                    ((GridBagLayout) panelBinary.getLayout()).columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
                                    ((GridBagLayout) panelBinary.getLayout()).rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
                                    ((GridBagLayout) panelBinary.getLayout()).columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};
                                    ((GridBagLayout) panelBinary.getLayout()).rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};
                                }
                                tabbedPaneProgress.addTab("\u0411\u0438\u043d\u0430\u0440\u043d\u043e\u0435", panelBinary);

                                //======== panelHough ========
                                {
                                    panelHough.setLayout(new GridBagLayout());
                                    ((GridBagLayout) panelHough.getLayout()).columnWidths = new int[]{0, 0};
                                    ((GridBagLayout) panelHough.getLayout()).rowHeights = new int[]{0, 0};
                                    ((GridBagLayout) panelHough.getLayout()).columnWeights = new double[]{0.0, 1.0E-4};
                                    ((GridBagLayout) panelHough.getLayout()).rowWeights = new double[]{0.0, 1.0E-4};
                                }
                                tabbedPaneProgress.addTab("\u0425\u0430\u0444", panelHough);

                                //======== panelDeskew ========
                                {
                                    panelDeskew.setLayout(new GridBagLayout());
                                    ((GridBagLayout) panelDeskew.getLayout()).columnWidths = new int[]{0, 0};
                                    ((GridBagLayout) panelDeskew.getLayout()).rowHeights = new int[]{0, 0};
                                    ((GridBagLayout) panelDeskew.getLayout()).columnWeights = new double[]{0.0, 1.0E-4};
                                    ((GridBagLayout) panelDeskew.getLayout()).rowWeights = new double[]{0.0, 1.0E-4};
                                }
                                tabbedPaneProgress.addTab("\u0423\u0441\u0442\u0440\u0430\u043d\u0451\u043d\u043d\u043e\u0435", panelDeskew);

                                //======== panelTable ========
                                {
                                    panelTable.setLayout(new GridBagLayout());
                                    ((GridBagLayout) panelTable.getLayout()).columnWidths = new int[]{0, 0};
                                    ((GridBagLayout) panelTable.getLayout()).rowHeights = new int[]{0, 0};
                                    ((GridBagLayout) panelTable.getLayout()).columnWeights = new double[]{0.0, 1.0E-4};
                                    ((GridBagLayout) panelTable.getLayout()).rowWeights = new double[]{0.0, 1.0E-4};
                                }
                                tabbedPaneProgress.addTab("\u0422\u0430\u0431\u043b\u0438\u0446\u0430", panelTable);

                                //======== panelCell ========
                                {
                                    panelCell.setLayout(new GridBagLayout());
                                    ((GridBagLayout) panelCell.getLayout()).columnWidths = new int[]{0, 0};
                                    ((GridBagLayout) panelCell.getLayout()).rowHeights = new int[]{0, 0};
                                    ((GridBagLayout) panelCell.getLayout()).columnWeights = new double[]{0.0, 1.0E-4};
                                    ((GridBagLayout) panelCell.getLayout()).rowWeights = new double[]{0.0, 1.0E-4};
                                }
                                tabbedPaneProgress.addTab("\u041a\u043b\u0435\u0442\u043a\u0438", panelCell);
                            }
                            scrollPane4.setViewportView(tabbedPaneProgress);
                        }
                        panel3.add(scrollPane4, new GridBagConstraints(0, 1, 1, 1, 1.0, 1.0,
                                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                                new Insets(0, 0, 0, 0), 0, 0));

                        //---- labelProgress ----
                        labelProgress.setText("<html><i>progress ...</i></html>");
                        labelProgress.setVisible(false);
                        panel3.add(labelProgress, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
                                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                                new Insets(0, 0, 0, 0), 0, 0));

                        //---- progressbar ----
                        progressbar.setVisible(false);
                        panel3.add(progressbar, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0,
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
                        ((GridBagLayout) panel2.getLayout()).columnWeights = new double[]{1.0};

                        //======== panel4 ========
                        {
                            panel4.setBorder(new TitledBorder("\u0424\u043e\u0440\u043c\u0430\u0442\u044b"));
                            panel4.setLayout(new GridBagLayout());
                            ((GridBagLayout) panel4.getLayout()).columnWidths = new int[]{0, 0, 0};
                            ((GridBagLayout) panel4.getLayout()).rowHeights = new int[]{0, 0, 0};
                            ((GridBagLayout) panel4.getLayout()).columnWeights = new double[]{0.0, 0.0, 1.0E-4};
                            ((GridBagLayout) panel4.getLayout()).rowWeights = new double[]{0.0, 0.0, 1.0E-4};

                            //======== scrollPane6 ========
                            {

                                //---- listFormat ----
                                listFormat.addListSelectionListener(new ListSelectionListener() {
                                    @Override
                                    public void valueChanged(ListSelectionEvent e) {
                                        listFormatValueChanged(e);
                                    }
                                });
                                scrollPane6.setViewportView(listFormat);
                            }
                            panel4.add(scrollPane6, new GridBagConstraints(0, 0, 2, 1, 1.0, 1.0,
                                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                                    new Insets(0, 0, 5, 0), 0, 0));

                            //---- butEditFormat ----
                            butEditFormat.setHorizontalAlignment(SwingConstants.LEFT);
                            butEditFormat.setPreferredSize(new Dimension(35, 25));
                            butEditFormat.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    butEditFormatActionPerformed(e);
                                }
                            });
                            panel4.add(butEditFormat, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
                                    GridBagConstraints.EAST, GridBagConstraints.VERTICAL,
                                    new Insets(0, 0, 0, 5), 0, 0));

                            //---- butDeleteFormat ----
                            butDeleteFormat.setHorizontalAlignment(SwingConstants.RIGHT);
                            butDeleteFormat.setPreferredSize(new Dimension(35, 25));
                            panel4.add(butDeleteFormat, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
                                    GridBagConstraints.EAST, GridBagConstraints.VERTICAL,
                                    new Insets(0, 0, 0, 0), 0, 0));
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

                            //---- labelInfoFormat ----
                            labelInfoFormat.setText("<html> <b>\u041d\u0430\u0437\u0432\u0430\u043d\u0438\u0435<b>:   <br/> <i>\u041a\u043e\u043b\u0438\u0447\u0435\u0441\u0442\u0432\u043e \u043a\u043b\u0435\u0442\u043e\u043a</i>:  <br/> <i>\u0421\u043f\u0438\u0441\u043e\u043a \u043f\u0435\u0440\u0435\u043c\u0435\u043d\u043d\u044b\u0445</i>: <br/> </html>");
                            labelInfoFormat.setVerticalAlignment(SwingConstants.TOP);
                            scrollPane1.setViewportView(labelInfoFormat);
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
        listProject.setModel(projectListModel);
    }

    public void updateFormatList() {
        formatList = dbAccess.listFormat();
        formatListModel.clear();
        for (Format format : formatList) {
            formatListModel.addElement(format.getName());
        }
        listFormat.setModel(formatListModel);
    }

    public void showProgress() {
        this.labelProgress.setVisible(true);
        this.progressbar.setVisible(true);
    }

    public void hideProgress() {
        this.labelProgress.setVisible(false);
        this.progressbar.setVisible(false);
    }

    public void setResult(String result) {
        this.txtResult.setText(result);
    }

    public Project getCurrentProject() {
        return projectList.get(listProject.getSelectedIndex());
    }

    public Format getCurrentFormat() {
        return formatList.get(listFormat.getSelectedIndex());
    }

    public Ocrconfig getCurrentConfiguration() {
        return DBAccess.getCurrentConfiguration();
    }
}
