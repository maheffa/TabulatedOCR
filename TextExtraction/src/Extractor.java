// File:    TextExtractor.java
// Created: 07/05/2015

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import java.awt.image.BufferedImage;
import java.util.HashMap;

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

}
