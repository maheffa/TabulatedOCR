// File:    TestTableDetector.java
// Created: 04/05/2015

import java.io.File;

/**
 * @author mahefa
 */
public class TestTableDetector {

    public TestTableDetector() {
    }

    public static void main(String[] args) {
        File f = new File("Test/real");
        File[] files = f.listFiles();
        if (files != null) {
            for (File file : files) {
                String[] pbe = SerializerUtil.getPathBaseExtension(file.getAbsolutePath());
                if (pbe[1].contains("hough") || !pbe[1].contains("bin") || !pbe[2].contains("png")) continue;
                System.out.println("Working with " + file.getAbsolutePath());
                TableDetector td = new TableDetector(file.getAbsolutePath());
                td.detectLine(true);
            }
        }
    }

}
