// File:    TesterUtil.java
// Created: 19/04/2015

/**
 * @author mahefa
 */
public class Util {

    static long time;
    static long chrono;

    public Util() {
    }

    public static void startChrono() {
        time = System.currentTimeMillis();
    }

    public static void stopChrono() {
        chrono = System.currentTimeMillis() - time;
    }

    public static void outputChrono() {
        System.out.println("Time elapsed: " + (chrono / 1000) + "sec " + (chrono - 1000 * (chrono / 1000)) + "ms");
    }

}
