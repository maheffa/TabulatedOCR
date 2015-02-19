// File:    ProcessorFunction.java
// Created: 19/02/2015

/**
 * @author mahefa
 */
public interface ProcessorFunction {
    /**
     * process the result value at [index / width, index % width] using src image matrix with size height x width;
     * @param i
     * @param src
     * @param height
     * @param width
     */
    public byte processPoint(int index, byte[] src, int height, int width);
}
