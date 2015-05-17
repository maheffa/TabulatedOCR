// File:    ProcessImage.java
// Created: 05/05/2015

import javax.management.openmbean.TabularData;
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
    }

    public void process() throws Exception {
        document = ImgProcUtil.readImage(this.project.getImagePath());

        // PRE-PROCESSING
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
        // if document skew is too much (>1 degree)
        if (Math.abs(hough.getTableDetector().getDocumentSkew()) > 1.0) {
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
        }
        // 8) Write largest table
        System.out.println("Writing largest table");
        callableTableDetector = new CallableTableDetector(binaryImage);
        tableImage = callableTableDetector.getTableDetector().writeLargestTable(connectedPixels);
        logImage(tableImage, "largeTable");
        // 9) Get perfecttable
        int lineThickness = 8;
        System.out.println("Getting perfect table");
        hough = new CallableTableDetector(tableImage);
        hough.call();
        tableImage = hough.getTableDetector().getLineApproximation()
                .draw(tableImage.getWidth(), tableImage.getHeight(), lineThickness);
        logImage(tableImage, "perfectTable");
        // 10) Check cell
        System.out.println("Checking cells");
        CellExtractor cellExtractor = new CellExtractor(tableImage, hough.getTableDetector().getIntersetionPoints(), lineThickness);
//        CellExtractor cellExtractor = new CellExtractor(new BinaryImage(tableImage, 100), lineThickness);
//        cellExtractor.getCells(hough.getTableDetector().getLineApproximation().getGroupLines());
        CellContainer cellContainer = cellExtractor.getCellContainer();
        System.out.println("Number of cells: " + cellContainer.getSize());
        cellExtractor.drawOnImage(tableImage, cellContainer);
        logImage(tableImage, "coloredCell");

        // IV Extract information

//        Extractor extractor = new Extractor("eng");
        Extractor extractor = new Extractor("rus");
        cellContainer.extractCellsToPath(binaryImage, imageDirectory + File.separator + "txt", imageName);
        int cellCounter = 0;
        for (String s : cellContainer.getCellText(extractor)) {
            System.out.println("Cell " + cellCounter++ + " contains:");
            System.out.println(s);
        }
        BufferedImage imgWithoutCell = hough.getTableDetector().getImageWithoutCells(binaryImage, cellContainer);
        logImage(imgWithoutCell, "nocell");
        logText(extractor.extractText(imgWithoutCell), "nocell");
//        logText(extractor.extractText(binaryImage), "0");
//        callableConnectedPixel = new CallableConnectedPixel(3, 1, callableBinarization.getBinaryImage());
//        connectedPixels = callableConnectedPixel.call();

    }


    private void logImage(BufferedImage img, String midName){
        File f = new File(imageDirectory + File.separator + midName);
        if (!f.exists()) {
            f.mkdir();
        }
        ImgProcUtil.writeImage(
                imageDirectory + File.separator + midName + File.separator + imageName + "." + midName + ".png",
                img
        );
    }

    private void logText(String txt, String midName) {
        try {
            File f = new File(imageDirectory + File.separator + "txt");
            if (!f.exists()) {
                f.mkdir();
            }
            BufferedWriter bw = new BufferedWriter(
                    new FileWriter(
                            new File(imageDirectory + File.separator + "txt" + File.separator + imageName + ".txt")));
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