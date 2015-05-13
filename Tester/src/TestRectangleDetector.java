// File:    TestRectangleDetector.java
// Created: 12/05/2015

import org.opencv.core.Core;
import org.opencv.core.Mat;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;

import static org.opencv.imgproc.Imgproc.*;

/**
 * @author mahefa
 */
public class TestRectangleDetector {

    public TestRectangleDetector() {
    }

    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        BufferedImage img = ImgProcUtil.readImage("Test/01.png");
        Mat mat = TableDetector.bufferedImageToMat(img);
        cvtColor(mat, mat, COLOR_BGR2GRAY);
        Canny(mat, mat, 100, 100);
        Mat lines = new Mat();
        HoughLinesP(mat, lines, 1, Math.PI / 180, 70, 30, 10);
        ArrayList<double[]> cornes = new ArrayList<double[]>();
        ArrayList<double[]> allLines = new ArrayList<double[]>();
        BufferedImage tmpImg = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
        Graphics2D gg = tmpImg.createGraphics();
        gg.setColor(Color.RED);
        gg.setStroke(new BasicStroke(4));
        for (int i = 0; i < lines.rows(); i++) {
            for (int j = 0; j < lines.cols(); j++) {
                double[] l = lines.get(i, j);
                allLines.add(lines.get(i, j));
                System.out.println("Line: " + Arrays.toString(lines.get(i, j)));
                gg.drawLine((int)l[0], (int)l[1], (int)l[2], (int)l[3]);
            }
        }
        ImgProcUtil.writeImage("Test/tmp.png", tmpImg);
        for (int i = 0; i < allLines.size(); i++) {
            for (int j = i + 1; j < allLines.size(); j++) {
                double[] corner = computeInterset(allLines.get(i), allLines.get(j));
                cornes.add(computeInterset(allLines.get(i), allLines.get(j)));
                System.out.println("Corners " + Arrays.toString(corner));
            }
        }
        Graphics2D g2d = img.createGraphics();
        g2d.setColor(Color.RED);
        for (double[] corne : cornes) {
            g2d.drawOval((int) corne[0], (int) corne[1], 10, 10);
        }

        ImgProcUtil.writeImage("Test/011.png", img);
    }

    public static double[] computeInterset(double[] a, double[] b) {
        double x1 = a[0], y1 = a[1], x2 = a[2], y2 = a[3];
        double x3 = b[0], y3 = b[1], x4 = b[2], y4 = b[3];
        double d;
        double[] res = new double[2];
        if ((d = ((x1-x2) * (y3-y4)) - ((y1-y2) * (x3-x4))) != 0) {
            res[0] = ((x1*y2 - y1*x2) * (x3-x4) - (x1-x2) * (x3*y4 - y3*x4)) / d;
            res[1] = ((x1*y2 - y1*x2) * (y3-y4) - (y1-y2) * (x3*y4 - y3*x4)) / d;
        }
        else {
            res = new double[]{-1, -1};
        }
        return res;
    }

}
