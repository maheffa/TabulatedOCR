package com.maheffa.TabulatedOCR.ImageProcessing;
// File:    com.maheffa.TabulatedOCR.ImageProcessing.ProcessorFunction.java
// Created: 19/02/2015

/**
 * @author mahefa
 */
public interface ProcessorFunction {
    public int processPoint(int index, int[] src, int height, int width, int area);
}
