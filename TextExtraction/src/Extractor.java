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

    public Extractor() {
        tesseract = new Tesseract();
    }

    public void setLanguage(String lang) {
        tesseract.setLanguage(lang);
    }

    public void setTreatCell(boolean val) {
        if (val) {
            tesseract.setPageSegMode(7);
        } else {
            tesseract.setPageSegMode(3);
        }
    }

    public void setUserDictionary(boolean val) {
        if (val) {
            tesseract.setTessVariable("user_words_suffix", "user-words");
        } else {
            tesseract.setTessVariable("user_words_suffix", "");
        }
    }

    public void setApplyNoiseRemoval(boolean val) {
        if (val) {
            tesseract.setTessVariable("textord_heavy_nr", "1");
        } else {
            tesseract.setTessVariable("textord_heavy_nr", "0");
        }
    }

    public String extractText(BufferedImage img) {
        try {
            return FuzzyTextMatcher.clean(tesseract.doOCR(img));
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
