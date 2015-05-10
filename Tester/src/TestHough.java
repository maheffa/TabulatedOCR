// File:    TestHough.java
// Created: 06/05/2015

import java.awt.image.BufferedImage;
import java.io.File;

/**
 * @author mahefa
 */
public class TestHough {

    public TestHough() {
    }

    public static void main(String[] args) {
        File f = new File("Test/hough");
        File[] files = f.listFiles();
        for (File file : files) {
            TableDetector tableDetector = new TableDetector(file.getAbsolutePath());
            String[] pbe = SerializerUtil.getPathBaseExtension(file.getAbsolutePath());
            System.out.println("Current: " + file.getAbsolutePath());
            // canny
//            tableDetector.setThreshold1(10);
//            tableDetector.setThreshold2(10);
//            tableDetector.setAperatureSize(10);
            // hough
//            tableDetector.setDistanceAccumulator(10);
//            tableDetector.setAngleAccumulator(10);
//            tableDetector.setThresholdAccumulator(10);
//            tableDetector.setMinimumLineLenght(10);
//            tableDetector.setMaximumLineGap(10);
            BufferedImage img = tableDetector.applyHoughLineProbabilistic();
            ImgProcUtil.writeImage(pbe[0] + File.separator + pbe[1] + ".table.png", img);
        }
    }
}
