// File:    TestProcessing.java
// Created: 06/05/2015

import org.opencv.core.Core;

import java.io.File;

/**
 * @author mahefa
 */
public class TestProcessing {

    public TestProcessing() {
    }

    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        ProjectConfiguration proj = new ProjectConfiguration("Testing");
        File dir = new File("Test/fake_rus");
        File[] files = dir.listFiles();
        for (File file : files) {
            String[] pbe = SerializerUtil.getPathBaseExtension(file.getAbsolutePath());
            if (pbe[1].contains(".") || !ImgProcUtil.isAcceptableImage(file)) {
                continue;
            }
            proj.setImagePath(file.getAbsolutePath());
            System.out.println(proj);
            ProcessImage processImage = new ProcessImage(proj);
            try {
                processImage.process();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.gc();
        }
    }

}
