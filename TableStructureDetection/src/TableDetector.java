// File:    TableDetector.java
// Created: 04/05/2015

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.*;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;

import static org.opencv.imgproc.Imgproc.*;

import org.opencv.core.*;
import org.opencv.core.Point;
import org.opencv.imgcodecs.Imgcodecs;

import javax.imageio.ImageIO;


/**
 * @author mahefa
 */
public class TableDetector {

//    private IplImage iplImage = null;
    private BufferedImage binaryImage = null;
    private String filePath = "";
    private int threshold1 = 20;
    private int threshold2 = 60;
    private int aperatureSize = 3;
    private double distanceAccumulator = 1;
    private double angleAccumulator = Math.PI / 180;
    private int thresholdAccumulator = 40;
    private int minimumLineLenght = 50;
    private int maximumLineGap = 100;
//    private CvSeq detectedLines = null;
    private int minimumTableArea = 100 * 100;
    private double documentSkew = 0;
    private LineApproximation lineApproximation;


    public TableDetector(BufferedImage binaryImage) {
        this.binaryImage = binaryImage;
//        this.iplImage = IplImage.createFrom(binaryImage);
        lineApproximation = new LineApproximation(50);
    }

    public TableDetector(String filePath) {
        this(ImgProcUtil.readImage(filePath));
        this.filePath = filePath;
    }

    public BufferedImage detectLine(boolean writeIntoFile) {
        BufferedImage res = applyHoughLineProbabilistic();
        String filePath = this.filePath.equals("") ? "res.png" : this.filePath;
        if (writeIntoFile) {
            String[] pbe = SerializerUtil.getPathBaseExtension(filePath);
            ImgProcUtil.writeImage(pbe[0] + File.separator + pbe[1] + ".hough." + pbe[2], res);
        }
        return res;
    }

    public BufferedImage applyHoughLineProbabilistic() {
        return applyHoughLineProbabilistic(binaryImage);
    }

    public BufferedImage applyHoughLineProbabilistic(BufferedImage image) {
        double sumLenght = 0;
        double sumMult = 0;
        Mat src = bufferedImageToMat(image);
        Mat dst = new Mat();
        Mat cdst = new Mat();
        Mat lines = new Mat();
        Canny(src, dst, threshold1, threshold2);
        cvtColor(dst, cdst, COLOR_GRAY2BGR);
        HoughLinesP(dst, lines, distanceAccumulator, angleAccumulator, thresholdAccumulator,
                minimumLineLenght, maximumLineGap);
        System.out.println("Detected " + lines.rows() + " lines");
        for (int i = 0; i < lines.rows(); i++) {
            for (int j = 0; j < lines.cols(); j++) {
                double[] vec = lines.get(i, j);
                double x1 = vec[0],
                        y1 = vec[1],
                        x2 = vec[2],
                        y2 = vec[3];
                Point pt1 = new Point(x1, y1);
                Point pt2 = new Point(x2, y2);
                double angle = (180 / Math.PI * getAngle(pt1.x, pt1.y, pt2.x, pt2.y));
                double distance = getDistance(pt1.x, pt1.y, pt2.x, pt2.y);
                if (angle > 45) {
                    angle -= 90;
                } else if (angle < -45) {
                    angle += 90;
                }
//                System.out.println("\t " + pt1 + " - " + pt2 + "; angle: " + (int)angle + ", distance: " + (int)distance);
                if (angle > 10 || angle < -10) continue;
                sumLenght += distance * distance;
                sumMult += angle * distance * distance;
                line(cdst, pt1, pt2, new Scalar(255, 0, 0), 3);
                lineApproximation.add((int)x1, (int)y1, (int)x2, (int)y2);
            }
        }
        documentSkew = sumMult / sumLenght;
        System.out.println("Document skew : " + documentSkew);
        return matToBufferedImage(cdst);
    }

    public static BufferedImage matToBufferedImage(Mat mat) {
        MatOfByte mob = new MatOfByte();
        Imgcodecs.imencode(".png", mat, mob);
        byte[] byteArray = mob.toArray();
        BufferedImage bufImage = null;
        try {
            InputStream in = new ByteArrayInputStream(byteArray);
            bufImage = ImageIO.read(in);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bufImage;
    }

    public static Mat bufferedImageToMat(BufferedImage img) {
        byte[] data = ((DataBufferByte) img.getRaster().getDataBuffer()).getData();
        Mat mat = new Mat(
                img.getHeight(),
                img.getWidth(),
                img.getType() == BufferedImage.TYPE_BYTE_GRAY ? CvType.CV_8UC1 : CvType.CV_8UC3);
        mat.put(0, 0, data);
        return mat;
    }

    public ArrayList<Point> getIntersetionPoints() {
        ArrayList<Point> intersections = new ArrayList<Point>();
        ArrayList<Line> lines = lineApproximation.getLines();
        for (int i = 0; i < lines.size(); i++) {
            for (int j = i + 1; j < lines.size(); j++) {
                Point p = Line.getProlongedIntersetion(lines.get(i), lines.get(j));
                if (p.x > 0 && p.x < binaryImage.getWidth()
                        && p.y > 0 && p.y < binaryImage.getHeight()) {
                    intersections.add(p);
//                    System.out.println("Intersection at " + p);
                }
            }
        }
        System.out.println("Number of intersetion " + intersections.size());
        return intersections;
    }

    /*
    Detect close lines
    Write straight line with thickness around 10px or more
    run through straight line till you see black pixel below 10 px
    if you see, you got your top line of the cell, go down then left then up
    save the 4 corners coordinate
    continue where you left on straight line

     */

    public BufferedImage writeRGBTable(ArrayList<ConnectedPixel> connectedPixels) {
        int minTableHeight = 50;
        int minTableWidth = 100;
        BinaryImage binaryImage = new BinaryImage(
                this.binaryImage.getHeight(),
                this.binaryImage.getWidth()
        );
        for (ConnectedPixel connectedPixel : connectedPixels) {
            int h = connectedPixel.getHeight();
            int w = connectedPixel.getWidth();
            if (h > minTableHeight && w > minTableWidth && h * w > minimumTableArea) {
//                System.out.println("writing on: " + connectedPixel);
                connectedPixel.createCharaterPixel().writeOnImage(binaryImage);
            }
        }
        return binaryImage.rasterize();
    }

    public BufferedImage writeLargestTable(ArrayList<ConnectedPixel> connectedPixels) {
        int maxArea = Integer.MIN_VALUE;
        ConnectedPixel pixels = null;
        for (ConnectedPixel connectedPixel : connectedPixels) {
            if (connectedPixel.getHeight() * connectedPixel.getWidth() > maxArea) {
                maxArea = connectedPixel.getHeight() * connectedPixel.getWidth();
                pixels = connectedPixel;
            }
        }
        BinaryImage binaryImage = new BinaryImage(
                this.binaryImage.getHeight(),
                this.binaryImage.getWidth()
        );
        pixels.createCharaterPixel().writeOnImage(binaryImage);
        return binaryImage.rasterize();
    }

    public double getDocumentSkew() {
        return documentSkew == Double.NaN ? 0 : documentSkew;
    }

    public BufferedImage deskewDocument(BufferedImage img) {
        return rotate(img, -getDocumentSkew() * Math.PI / 180);
    }

    public BufferedImage deskewDocument() {
        binaryImage = rotate(-getDocumentSkew() * Math.PI / 180);
        return binaryImage;
    }

    public BufferedImage getImageWithoutCells(BufferedImage image, CellContainer cellContainer) {
        BufferedImage copy = deepCopy(image);
        Graphics2D g2d = copy.createGraphics();
        g2d.setColor(Color.WHITE);
        for (CellImage cellImage : cellContainer.getCells()) {
            g2d.drawRect(cellImage.getX() - 10, cellImage.getY() - 10, cellImage.getWidth() + 20, cellImage.getHeight() + 20);
        }
        return copy;
    }

    public static BufferedImage deepCopy(BufferedImage bi) {
        ColorModel cm = bi.getColorModel();
        boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
        WritableRaster raster = bi.copyData(null);
        return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
    }

    public BufferedImage rotate(double angle) {
        return rotate(binaryImage, angle);
    }

    public static BufferedImage rotate(BufferedImage img, double angle) {
        AffineTransform affine = new AffineTransform();
        affine.rotate(angle, img.getWidth() / 2, img.getHeight() / 2);
        AffineTransformOp op = new AffineTransformOp(affine, AffineTransformOp.TYPE_BICUBIC);
        BufferedImage res = op.filter(img, null);
        for (int i = 0; i < res.getWidth(); i++) {
            for (int j = 0; j < res.getHeight(); j++) {
                if (((res.getRGB(i, j) >> 24) & 0xFF) == 0) {
                    res.setRGB(i, j, Color.WHITE.getRGB());
                }
            }
        }
        return res;
    }

    public static double getAngle(double x0, double y0, double x1, double y1) {
        if (x0 > x1) {
            double x = x0, y = y0;
            x0 = x1;
            y0 = y1;
            x1 = x;
            y1 = y;
        } else if (x0 == x1) {
            return y1 > y0 ? Math.PI/2 : -Math.PI/2;
        }
        return Math.atan((1.0 * (y1 - y0)) / (1.0 * (x1 - x0)));
    }

    public static double getDistance(double x0, double y0, double x1, double y1) {
        return Math.sqrt((x1 - x0) * (x1 - x0) + (y1 - y0) * (y1 - y0));
    }

    public static double getDistance(Point A, Point B) {
        return getDistance(A.x, A.y, B.x, B.y);
    }

    public LineApproximation getLineApproximation() {
        return lineApproximation;
    }

    public double getImageSkew() {
        return 0;
    }

    public void setThreshold1(int threshold1) {
        this.threshold1 = threshold1;
    }

    public void setThreshold2(int threshold2) {
        this.threshold2 = threshold2;
    }

    public void setAperatureSize(int aperatureSize) {
        this.aperatureSize = aperatureSize;
    }

    public void setCannyParameter(int threshold1, int threshold2, int aperatureSize) {
        this.threshold1 = threshold1;
        this.threshold2 = threshold2;
        this.aperatureSize = aperatureSize;
    }

    public void setDistanceAccumulator(double distanceAccumulator) {
        this.distanceAccumulator = distanceAccumulator;
    }

    public void setAngleAccumulator(double angleAccumulator) {
        this.angleAccumulator = angleAccumulator;
    }

    public void setThresholdAccumulator(int thresholdAccumulator) {
        this.thresholdAccumulator = thresholdAccumulator;
    }

    public void setMinimumLineLenght(int minimumLineLenght) {
        this.minimumLineLenght = minimumLineLenght;
    }

    public void setMaximumLineGap(int maximumLineGap) {
        this.maximumLineGap = maximumLineGap;
    }

    public void setHoughLineParameter(double distanceAccumulator, double angleAccumulator,
                                      int thresholdAccumulator, int minimumLineLenght, int maximumLineGap) {
        this.distanceAccumulator = distanceAccumulator;
        this.angleAccumulator = angleAccumulator;
        this.thresholdAccumulator = thresholdAccumulator;
        this.minimumLineLenght = minimumLineLenght;
        this.maximumLineGap = maximumLineGap;
    }
}
