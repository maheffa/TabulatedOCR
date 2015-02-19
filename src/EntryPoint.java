// File:    EntryPoint.java
// Created: 19/02/2015

import org.opencv.core.Core;

import javax.naming.BinaryRefAddr;

/**
 * @author mahefa
 */
public class EntryPoint {

    public EntryPoint() {
    }


    public static void main(String[] args) {
        Tester t = new TestBinaryImage();
        t.runTest();
    }

}
