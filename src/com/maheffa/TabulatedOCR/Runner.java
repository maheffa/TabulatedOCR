package com.maheffa.TabulatedOCR;
// File:    com.maheffa.TabulatedOCR.Runner.java
// Created: 23/05/2015

import com.maheffa.TabulatedOCR.GUI.OcrMainForm;

import javax.swing.*;
import java.util.HashMap;

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

