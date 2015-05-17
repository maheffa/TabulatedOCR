// File:    TextExtractor.java
// Created: 07/05/2015

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

/**
 * @author mahefa
 */
public class Extractor {

    Tesseract tesseract = null;

    public Extractor(String language) {
        tesseract = new Tesseract();
        tesseract.setLanguage(language);
    }

    public String extractText(BufferedImage img) {
        try {
            return tesseract.doOCR(img);
        } catch (TesseractException e) {
            e.printStackTrace();
            return "";
        }
    }

    public HashMap<String, String> extractInformations(BufferedImage img, String fmt) {
        return FuzzyTextMatcher.matchVariables(extractText(img), fmt);
    }

    public static String showInformation(HashMap<String, String> map) {
        StringBuilder str = new StringBuilder();
        str.append("Variables:\n");
        for (Map.Entry<String, String> entry : map.entrySet()) {
            str.append("\t " + entry.getKey() + ": " + entry.getValue() + "\n");
        }
        return str.toString();
    }
}
