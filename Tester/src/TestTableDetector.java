// File:    TestTableDetector.java
// Created: 04/05/2015

import org.opencv.core.Core;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @author mahefa
 */
public class TestTableDetector {

    public TestTableDetector() {
    }

    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
//        File f = new File("Test/real");
//        File[] files = f.listFiles();
//        if (files != null) {
//            for (File file : files) {
//                String[] pbe = SerializerUtil.getPathBaseExtension(file.getAbsolutePath());
//                if (pbe[1].contains("hough") || !pbe[1].contains("bin") || !pbe[2].contains("png")) continue;
//                System.out.println("Working with " + file.getAbsolutePath());
//                TableDetector td = new TableDetector(file.getAbsolutePath());
//                td.detectLine(true);
//            }
//        }
        BufferedImage img = ImgProcUtil.readImage("Test/real_rus/bank0.jpg");
        TableDetector detector = new TableDetector(img);
        detector.applyHoughLineProbabilistic();
        BufferedImage img0 = detector.getLineApproximation().draw(img.getWidth(), img.getHeight(), 5);
        CellExtractor cellExtractor = new CellExtractor(img0, detector.getIntersetionPoints(), 5);
        CellContainer cellContainer = cellExtractor.getCellContainer();
        Color[] colors = new Color[]{Color.BLUE, Color.GREEN, Color.RED};
        int count = 0;
        Graphics2D g2d = img0.createGraphics();
        System.out.println("Cell count " + cellContainer.getCells().size());
        for (CellImage cellImage : cellContainer.getCells()) {
            g2d.setColor(colors[count++ % colors.length]);
            g2d.fillRect(cellImage.getX(), cellImage.getY(), cellImage.getWidth(), cellImage.getHeight());
        }
        ImgProcUtil.writeImage("Test/real_rus/bank0.cell.png", img0);
    }


}
