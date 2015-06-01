package com.maheffa.TabulatedOCR;
// File:    com.maheffa.TabulatedOCR.RunnerProgress.java
// Created: 23/05/2015

import java.awt.image.BufferedImage;

/**
 * @author mahefa
 */
public class RunnerProgress {

    public static final int STATE_READING_IMAGE = 0;
    public static final int STATE_GRAYSCALING = 1;
    public static final int STATE_BINARISING = 2;
    public static final int STATE_DETECTING_CONNECTED_PIXELS = 3;
    public static final int STATE_GETTING_LARGEST_CONNECTED_PIXELS = 4;
    public static final int STATE_APPLYING_HOUGH = 5;
    public static final int STATE_DESKEWING_IMAGE = 6;
    public static final int STATE_GRAYSCALING_2 = 7;
    public static final int STATE_BINARISING_2 = 8;
    public static final int STATE_GETTING_LARGEST_TABLE = 9;
    public static final int STATE_GETTING_PERFECT_TABLE = 10;
    public static final int STATE_CHECKING_CELL = 11;
    public static final int STATE_RECOGNIZING_TEXT = 12;
    public static final int STATE_GETTING_VARIABLES = 13;

    private int percentage = -1;
    private String message = null;
    private int state = -1;
    private BufferedImage image = null;

    private RunnerProgress() {
    }

    public static RunnerProgress createMessenger(int percentage, String message) {
        RunnerProgress runnerProgress = new RunnerProgress();
        runnerProgress.percentage = percentage;
        runnerProgress.message = message;
        return runnerProgress;
    }

    public static RunnerProgress createImageShower(int state, BufferedImage img) {
        RunnerProgress runnerProgress = new RunnerProgress();
        runnerProgress.state = state;
        runnerProgress.image = img;
        return runnerProgress;
    }

    public int getPercentage() {
        return percentage;
    }

    public String getMessage() {
        return message;
    }

    public int getState() {
        return state;
    }

    public BufferedImage getImage() {
        return image;
    }
}
