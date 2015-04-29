// File:    TestGUI.java
// Created: 27/04/2015

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.io.File;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.regex.Pattern;

/**
 * @author mahefa
 */
public class TestGUI {

    public TestGUI() {
    }

//    public static void testOcrMainForm() {
//        OcrMainForm ocr = new OcrMainForm();
//        JFrame frame = ocr.getMainFrame();
//        frame.pack();
//        frame.setVisible(true);
//    }

    public static void testCreateFormatForm() {
        CreateFormatForm form = new CreateFormatForm();
        JFrame frame = new JFrame("Hey watsup");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(form);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {

//        testOcrMainForm();
        String p = "/home/mahefa/TabulatedOCR/";
        String[] folders = p.split(Pattern.quote(File.separator));
        System.out.printf(Arrays.toString(folders));
        DefaultMutableTreeNode root = new DefaultMutableTreeNode(new File("/"));
        int index = 1;
        DefaultMutableTreeNode current = root;
        while (index < folders.length) {
            open(current, 2);
            Enumeration<DefaultMutableTreeNode> enumeration = current.children();
            DefaultMutableTreeNode satisfactory = null;
            while (enumeration.hasMoreElements() && satisfactory == null) {
                DefaultMutableTreeNode tmpDm = enumeration.nextElement();
                if (((File) tmpDm.getUserObject()).getName().equals(folders[index])) {
                    satisfactory = tmpDm;
                    index++;
                    current = satisfactory;
                }
            }
        }
//        System.out.println("Explored");
        Enumeration<DefaultMutableTreeNode> en = root.breadthFirstEnumeration();
        while (en.hasMoreElements()) {
            System.out.println(((File) en.nextElement().getUserObject()).getName());
        }
    }

    public static void open(DefaultMutableTreeNode folder, int depth) {
        File f = (File) folder.getUserObject();
        if (f.isDirectory() && depth > 0) {
            File[] files = f.listFiles();
            if (files != null)
                for (File ff : files) {
                    DefaultMutableTreeNode df = new DefaultMutableTreeNode(ff);
                    open(df, depth - 1);
                    folder.add(df);
                }
        }
    }
}
