// File:    TestMath.java
// Created: 16/04/2015

import org.apache.commons.math3.fraction.Fraction;

import java.util.Arrays;

/**
 * @author mahefa
 */
public class TestMath {

    public TestMath() {
    }

    public static int[] getClosestFraction(double val) {
        Fraction fraction = new Fraction(val);
        return new int[]{fraction.getNumerator(), fraction.getDenominator()};
    }

    public static void main(String[] args) {
        double k = Math.pow(10, 2);
        double v = Math.floor(Math.PI * k)/k;
        int[] rat = getClosestFraction(v);
        System.out.println(v);
        System.out.println(Arrays.toString(getClosestFraction(v)));
        System.out.println(((rat[0] * 1.0) / rat[1]));
    }

}
