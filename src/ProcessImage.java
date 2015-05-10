// File:    ProcessImage.java
// Created: 05/05/2015

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.Callable;

/**
 * @author mahefa
 */
public class ProcessImage {

    private BufferedImage document;
    private String imageDirectory;
    private String imageName;
    private String imageExtension;
    private ProjectConfiguration project = null;
    private BufferedImage binaryImage = null;
    private CallableBinarization callableBinarization = null;
    private CallableConnectedPixel callableConnectedPixel = null;
    private ArrayList<ConnectedPixel> connectedPixels = null;
    private int height = 0;
    private int width = 0;

    public ProcessImage(ProjectConfiguration project) {
        this.project = project;
        String[] pbe = SerializerUtil.getPathBaseExtension(project.getImagePath());
        imageDirectory = pbe[0];
        imageName = pbe[1];
        imageExtension = pbe[2];
    }

    public void process() throws Exception {
        document = ImgProcUtil.readImage(this.project.getImagePath());

        // I) run binarization
        callableBinarization = new CallableBinarization(document);
        binaryImage = callableBinarization.call();
        logImage(binaryImage, "bin");
        height = binaryImage.getHeight();
        width = binaryImage.getWidth();
        System.out.println("dimension " + height + " x " + width);

        // II) collect connectedPixels
        System.out.println("Getting connected pixels");
        callableConnectedPixel = new CallableConnectedPixel(3, 1, callableBinarization.getBinaryImage());
        connectedPixels = callableConnectedPixel.call();

        // III) detect table structure
        CallableTableDetector callableTableDetector = new CallableTableDetector(binaryImage);
        // 1) detect table
        System.out.println("Detecting tables");
        BufferedImage tableImage = callableTableDetector.getTableDetector().writeRGBTable(connectedPixels);
        logImage(tableImage, "table");
        // 2) process image to better table

        // 3) run hough
        System.out.println("Applying Hough Transform");
        CallableTableDetector hough = new CallableTableDetector(tableImage);
        tableImage = hough.call();
        logImage(tableImage, "hough");
        // 4) detect and rotate image
//        tableImage = hough.getTableDetector().deskewDocument();
//        logImage(tableImage, "deskew");
        // 5) deskew document
        System.out.println("Deskewing document");
        document = hough.getTableDetector().deskewDocument(document);
        logImage(document, "deskew");
        // 6) work with new deskewed document
        System.out.println("Binarizing deskewed document");
        callableBinarization = new CallableBinarization(document);
        binaryImage = callableBinarization.call();
        logImage(binaryImage, "bin1");
        // 7) Detect connected pixels;
        System.out.println("Detecting connected pixels");
        callableConnectedPixel = new CallableConnectedPixel(3, 1, callableBinarization.getBinaryImage());
        connectedPixels = callableConnectedPixel.call();
        // 8) Write largest table
        System.out.println("Writing largest table");
        callableTableDetector = new CallableTableDetector(binaryImage);
        tableImage = callableTableDetector.getTableDetector().writeLargestTable(connectedPixels);
        logImage(tableImage, "largeTable");
        // 9) Get perfecttable
        System.out.println("Getting perfect table");
        hough = new CallableTableDetector(tableImage);
        hough.call();
        hough.getTableDetector().getLineApproximation().draw(tableImage.getWidth(), tableImage.getHeight(), 20);
        logImage(tableImage, "perfectTable");
        // 6bis attempt to read text
        Extractor extractor = new Extractor("fra");
        logText(extractor.extractText(binaryImage), "0");
        callableConnectedPixel = new CallableConnectedPixel(3, 1, callableBinarization.getBinaryImage());
        connectedPixels = callableConnectedPixel.call();

    }


    private void logImage(BufferedImage img, String midName){
        ImgProcUtil.writeImage(
                imageDirectory + File.separator + imageName + "." + midName + ".png",
                img
        );
    }

    private void logText(String txt, String midName) {
        try {
            BufferedWriter bw = new BufferedWriter(
                    new FileWriter(
                            new File(
                                    imageDirectory + File.separator + imageName + "." + midName + ".txt"
                            )));
            bw.write(txt);
            bw.flush();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class CallableBinarization implements Callable<BufferedImage> {

    private String imagePath = null;
    private BinaryImage binaryImage = null;
    private BufferedImage bufferedImage = null;

    public CallableBinarization(String imagePath) {
        this.imagePath = imagePath;
    }

    public CallableBinarization(BufferedImage img) {
        this.bufferedImage = img;
    }

    @Override
    public BufferedImage call() throws Exception {
        if (bufferedImage == null) {
            return (binaryImage = new BinaryImage(imagePath)).rasterize();
        } else {
            return (binaryImage = new BinaryImage(bufferedImage)).rasterize();
        }
    }

    public BinaryImage getBinaryImage() {
        return this.binaryImage;
    }
}

class CallableConnectedPixel implements Callable<ArrayList<ConnectedPixel>> {

    private int margin = 0;
    private int radius = 0;
    private BinaryImage binaryImage = null;

    public CallableConnectedPixel(int radius, int margin, BinaryImage binaryImage) {
        this.margin = margin;
        this.radius = radius;
        this.binaryImage = binaryImage;
    }

    @Override
    public ArrayList<ConnectedPixel> call() throws Exception {
        return ConnectedPixel.getConnectedPixels(radius, margin, binaryImage);
    }
}

class CallableTableDetector implements Callable<BufferedImage> {

    private BufferedImage img;
    private TableDetector tableDetector;

    public CallableTableDetector(BufferedImage img) {
        this.img = img;
        this.tableDetector = new TableDetector(img);
    }

    @Override
    public BufferedImage call() throws Exception {
        return tableDetector.applyHoughLineProbabilistic();
    }

    public TableDetector getTableDetector() {
        return this.tableDetector;
    }
}