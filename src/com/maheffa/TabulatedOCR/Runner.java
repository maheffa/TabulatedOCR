package com.maheffa.TabulatedOCR;
// File:    com.maheffa.TabulatedOCR.Runner.java
// Created: 23/05/2015

import com.maheffa.TabulatedOCR.DBManager.*;
import com.maheffa.TabulatedOCR.GUI.ImagePanel;
import com.maheffa.TabulatedOCR.GUI.OcrMainForm;
import com.maheffa.TabulatedOCR.ImageProcessing.BinaryImage;
import com.maheffa.TabulatedOCR.ImageProcessing.ImgProcUtil;
import com.maheffa.TabulatedOCR.TableStructureDetection.CellContainer;
import com.maheffa.TabulatedOCR.TableStructureDetection.CellExtractor;
import com.maheffa.TabulatedOCR.TableStructureDetection.TableDetector;
import com.maheffa.TabulatedOCR.TextExtraction.ConnectedPixel;
import com.maheffa.TabulatedOCR.TextExtraction.Extractor;
import com.maheffa.TabulatedOCR.TextExtraction.FuzzyTextMatcher;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.awt.image.BufferedImage;
import java.io.StringWriter;
import java.util.*;
import java.util.concurrent.ExecutionException;

/**
 * @author mahefa
 */
public class Runner implements Runnable {

    private OcrMainForm mainForm;
    private SwingWorker<HashMap<String, Object>, RunnerProgress> worker;

    public Runner(OcrMainForm form) {
        this.mainForm = form;
    }

    @Override
    public void run() {
        worker = new TOCRWorker(mainForm);
        worker.execute();
    }

    public void stop() {
        worker.cancel(true);
    }

}

class TOCRWorker extends SwingWorker<HashMap<String, Object>, RunnerProgress> {

    private OcrMainForm mainForm;

    TOCRWorker(OcrMainForm mainForm) {
        this.mainForm = mainForm;
    }

    @Override
    protected HashMap<String, Object> doInBackground() throws Exception {
        int nStep = 13;
        Ocrconfig conf = mainForm.getCurrentConfiguration();
        Format format = mainForm.getCurrentFormat();
        Project project = mainForm.getCurrentProject();
        String path = project.getInputFilePath();
        String[] pbe = ImgProcUtil.getPathBaseExtension(path);
        CellContainer cellContainer = null;

        mainForm.showProgress();
//                STATE_READING_IMAGE
        publish(RunnerProgress.createMessenger(0, "Reading image from " + path));
        BufferedImage originalImage = ImgProcUtil.readImage(path);
        publish(RunnerProgress.createImageShower(RunnerProgress.STATE_READING_IMAGE, originalImage));

//                STATE_GRAYSCALING
        publish(RunnerProgress.createMessenger((int) (100.0 / nStep), "Grayscaling ..."));
        BinaryImage binaryImage = new BinaryImage(originalImage);
        binaryImage.convertToGrayScale(conf.getGrayscale());
        publish(RunnerProgress.createImageShower(RunnerProgress.STATE_GRAYSCALING, binaryImage.rasterize()));

//                STATE_BINARISING
        BufferedImage workingImage = binaryImage.rasterize();
        publish(RunnerProgress.createMessenger((int) (100.0 * 2 / nStep), "Binarising ..."));
        binaryImage.binarize(conf.getBinarisation());
        publish(RunnerProgress.createImageShower(
                RunnerProgress.STATE_BINARISING,
                workingImage));

//                STATE_DETECTING_CONNECTED_PIXELS
        publish(RunnerProgress.createMessenger((int) (100.0 * 3 / nStep), "Detecting connected pixel ..."));
        ArrayList<ConnectedPixel> connectedPixels = ConnectedPixel.getConnectedPixels(
                conf.getRadius(),
                conf.getMargin(),
                binaryImage
        );
        publish(RunnerProgress.createMessenger(-1, "Done connected pixel ..."));

        TableDetector tableDetector = null;
        BufferedImage largeTable = null;
        if (format.getType().equalsIgnoreCase("TABLE")) {
//                STATE_GETTING_LARGEST_CONNECTED_PIXELS
            tableDetector = new TableDetector(workingImage);
            tableDetector.setConfiguration(conf);
            publish(RunnerProgress.createMessenger((int) (100.0 * 4 / nStep), "Getting largest connected pixel ..."));
            largeTable = tableDetector.getLargestTable(connectedPixels);
        }

//                STATE_APPLYING_HOUGH
        publish(RunnerProgress.createMessenger((int) (100.0 * 5 / nStep), "Applying Hough transform ..."));
        TableDetector tmpTableDetector = new TableDetector(workingImage);
        tmpTableDetector.setConfiguration(conf);
        BufferedImage tmpHough = tmpTableDetector.applyHoughLineProbabilistic();
        double skew = tmpTableDetector.getDocumentSkew();
        publish(RunnerProgress.createImageShower(RunnerProgress.STATE_APPLYING_HOUGH, tmpHough));

        if (conf.getDeskew()) {
            publish(RunnerProgress.createMessenger(-1, "Checking image skew"));
            if (skew < conf.getTolerableSkewAngle()) {
                publish(RunnerProgress.createMessenger(-1, "Document doesn't need deskewing"));
                System.out.println("Image doesn't need deskewing");
            } else {
//                STATE_DESKEWING_IMAGE
                System.out.println("Deskewing image");
                publish(RunnerProgress.createMessenger((int) (100.0 * 6 / nStep), "Deskewing image"));
                workingImage = tmpTableDetector.deskewDocument(workingImage);
                publish(RunnerProgress.createImageShower(RunnerProgress.STATE_DESKEWING_IMAGE, workingImage));
                binaryImage = new BinaryImage(workingImage, 100);

//                STATE_GRAYSCALING_2
//                STATE_BINARISING_2

            }
        }

        if (format.getType().equalsIgnoreCase("TABLE")) {
            BufferedImage houghTable = tableDetector.applyHoughLineProbabilistic(largeTable);
//            STATE_GETTING_LARGEST_TABLE
            publish(RunnerProgress.createMessenger((int) (100.0 * 9 / nStep), "Getting table"));
            connectedPixels = ConnectedPixel.getConnectedPixels(
                    conf.getRadius(),
                    conf.getMargin(),
                    binaryImage
            );
            largeTable = tableDetector.getLargestTable(connectedPixels);
            tableDetector = new TableDetector(largeTable);
            tableDetector.setConfiguration(conf);
            tableDetector.applyHoughLineProbabilistic();

//                STATE_GETTING_PERFECT_TABLE
            publish(RunnerProgress.createMessenger((int) (100.0 * 10 / nStep), "Getting perfect table"));
            BufferedImage perfectTable = tableDetector.getLineApproximation()
                    .draw(workingImage.getWidth(), workingImage.getHeight(), conf.getLineThickness());
            publish(RunnerProgress.createImageShower(RunnerProgress.STATE_GETTING_PERFECT_TABLE, perfectTable));

//                STATE_CHECKING_CELL
            publish(RunnerProgress.createMessenger((int) (100.0 * 11 / nStep), "Checking cells"));
            CellExtractor cellExtractor = new CellExtractor(
                    perfectTable,
                    tableDetector.getIntersetionPoints(),
                    conf.getLineThickness()
            );
            cellContainer = cellExtractor.getCellContainer();
            cellExtractor.drawOnImage(largeTable, cellContainer);
            publish(RunnerProgress.createImageShower(RunnerProgress.STATE_CHECKING_CELL, largeTable));
        }

//                STATE_RECOGNIZING_TEXT
        publish(RunnerProgress.createMessenger((int) (100.0 * 12 / nStep), "Recognizing text"));
        Extractor extractor = new Extractor();
        extractor.setApplyNoiseRemoval(conf.getDenoise());
        extractor.setUserDictionary(conf.getUserDictionary());
        extractor.setTreatCell(format.getType().equalsIgnoreCase("TABLE"));
        ArrayList<ArrayList<String>> cellText = null;
        String text = null;
        if (format.getType().equalsIgnoreCase("TABLE")) {
            BufferedImage bImg = binaryImage.rasterize();
            cellText = new ArrayList<ArrayList<String>>();
            cellContainer.initLines();
            while (cellContainer.hasNextLine()) {
                publish(RunnerProgress.createMessenger(
                        (int) (100.0 * (12 / nStep + cellContainer.percentage() / nStep)), "Recognizing text"));
                cellText.add(cellContainer.extractLine(extractor, bImg));
            }
        } else {
            text = extractor.extractText(binaryImage.rasterize());
        }
//                STATE_GETTING_VARIABLES
        publish(RunnerProgress.createMessenger((int) (100.0 * 13 / nStep), "Getting variables"));
        HashMap<String, Object> variables = new HashMap<String, Object>();
        if (format.getType().equalsIgnoreCase("TABLE")) {
            TableFormat tableFormat = format.getTableFormat();
            int columnCount = tableFormat.getColumnCount();
            ColumnCharacteristic[] columns = new ColumnCharacteristic[columnCount];
            for (int i = 0; i < columnCount; i++) {
                for (ColumnCharacteristic col : (Set<ColumnCharacteristic>) tableFormat.getColumnCharacteristics()) {
                    if (col.getPosition() == i) {
                        columns[i] = col;
                        variables.put(col.getName(), new ArrayList<String>());
                        break;
                    }
                }
            }
            int index = tableFormat.getReadFirstLine() ? 0 : 1;
            for (; index < cellText.size(); index++) {
                ArrayList<String> line = cellText.get(index);
                if (line.size() < columnCount) continue;
                for (int i = 0; i < columns.length; i++) {
                    ((ArrayList<String>)variables.get(columns[i].getName())).add(line.get(i));
                }
            }
        } else {
            HashMap<String, String> tmpVariable = FuzzyTextMatcher.matchWholeVariables(
                    text,
                    format.getTextFormat().getContent()
            );
            for (Map.Entry<String, String> entry : tmpVariable.entrySet()) {
                variables.put(entry.getKey(), entry.getValue());
            }
        }
        publish(RunnerProgress.createMessenger(100, "Done"));
        return variables;
    }

    @Override
    public void process(List<RunnerProgress> runnerProgress) {
        for (RunnerProgress progress : runnerProgress) {
            switch (progress.getState()) {
                case -1 :
                    mainForm.getLabelProgress().setText(progress.getMessage());
                    mainForm.getProgressbar().setValue(progress.getPercentage());
                    break;
                case RunnerProgress.STATE_READING_IMAGE:
                    ((ImagePanel) mainForm.getPanelImage()).setImage(progress.getImage());
                    mainForm.getTabbedPaneData().setSelectedIndex(0);
                    break;
                case RunnerProgress.STATE_BINARISING :
                    ((ImagePanel) mainForm.getPanelBinary()).setImage(progress.getImage());
                    mainForm.getTabbedPaneProgress().setSelectedIndex(0);
                    break;
                case RunnerProgress.STATE_APPLYING_HOUGH:
                    ((ImagePanel) mainForm.getPanelHough()).setImage(progress.getImage());
                    mainForm.getTabbedPaneProgress().setSelectedIndex(1);
                    break;
                case RunnerProgress.STATE_DESKEWING_IMAGE:
                    ((ImagePanel) mainForm.getPanelDeskew()).setImage(progress.getImage());
                    mainForm.getTabbedPaneProgress().setSelectedIndex(2);
                    break;
                case RunnerProgress.STATE_GETTING_PERFECT_TABLE:
                    ((ImagePanel) mainForm.getPanelTable()).setImage(progress.getImage());
                    mainForm.getTabbedPaneProgress().setSelectedIndex(3);
                case RunnerProgress.STATE_CHECKING_CELL:
                    ((ImagePanel) mainForm.getPanelCell()).setImage(progress.getImage());
                    mainForm.getTabbedPaneProgress().setSelectedIndex(4);
                    break;
            }
        }
    }

    @Override
    public void done() {
        try {
            HashMap<String, Object> result = get();
            Format format = mainForm.getCurrentFormat();

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("Data");
            Attr attr = doc.createAttribute("type");
            attr.setValue(format.getType());
            rootElement.setAttributeNode(attr);
            doc.appendChild(rootElement);

            if (format.getType().equalsIgnoreCase("TABLE")) {
                TableFormat tableFormat = format.getTableFormat();
                int columnCount = tableFormat.getColumnCount();
                ColumnCharacteristic[] columns = new ColumnCharacteristic[columnCount];
                ArrayList<ArrayList<String>> columnsContent = new ArrayList<ArrayList<String>>();
                for (int i = 0; i < columnCount; i++) {
                    for (ColumnCharacteristic col : (Set<ColumnCharacteristic>) tableFormat.getColumnCharacteristics()) {
                        if (col.getPosition() == i) {
                            columns[i] = col;
                            ArrayList<String> matchedList = (ArrayList<String>) result.get(col.getName());
                            columnsContent.add(matchedList);
                            break;
                        }
                    }
                }
                int nRow = columnsContent.get(0).size();
                for (int i = 0; i < nRow; i++) {
                    Element row = doc.createElement("row");
                    row.setAttribute("id", String.valueOf(i));
                    rootElement.appendChild(row);
                    for (int j = 0; j < columnCount; j++) {
                        ColumnCharacteristic c = columns[j];
                        Element col = doc.createElement(c.getName());
                        col.setAttribute("type", c.getType());
                        row.appendChild(col);
                        col.appendChild(doc.createTextNode(columnsContent.get(j).get(i)));
                    }
                }
            } else {
                for (Map.Entry<String, Object> entry : result.entrySet()) {
                    Element variable = doc.createElement(entry.getKey());
                    variable.appendChild(doc.createTextNode((String) entry.getValue()));
                    rootElement.appendChild(variable);
                }
            }

            String resultInXml = xmlDocToString(doc);
            mainForm.setResult(resultInXml);

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    private String xmlDocToString(Document doc) {
        DOMSource domSource = new DOMSource(doc);
        StringWriter writer = new StringWriter();
        StreamResult result = new StreamResult(writer);
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = null;
        try {
            transformer = tf.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "8");
            transformer.transform(domSource, result);
        } catch (TransformerException e) {
            e.printStackTrace();
        }
        return writer.toString();
    }
}
