// File:    TestForkJoin.java
// Created: 12/02/2015

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * @author mahefa
 */
class MyTask extends RecursiveTask<Long> {

    private int THRESHOLD;
    private int[] arr;
    private int lo, hi;

    public MyTask(int[] arr, int lo, int hi, int tr) {
        this.arr = arr;
        this.lo = lo;
        this.hi = hi;
        this.THRESHOLD = tr;
    }

    @Override
    protected Long compute() {
        if (lo >= hi) return 0L;
        if (hi - lo < THRESHOLD) {
            long t = System.currentTimeMillis();
            int[] s = new int[TestForkJoin.limThre];
            int[] k = Arrays.copyOf(s, s.length);
            Random r = new Random();
            for (int i = 0; i < s.length; i++) {
                s[i] = r.nextInt();
            }
            for (int i = 0; i < s.length; i++) {
                s[i] *= s[i];
            }
            for (int i = 1; i < s.length - 1; i++) {
                k[i] = s[i-1] * s[i+1] - s[i];
            }
            return System.currentTimeMillis() - t;
        } else {
            MyTask t = new MyTask(arr, lo, (hi + lo)/2, THRESHOLD);
            MyTask t2 = new MyTask(arr, (hi + lo) / 2 + 1, hi, THRESHOLD);
            t.fork();
            long v = t2.compute();
            return v + t.join();
        }

    }
}

public class TestForkJoin {

    static int limThre = 100000;
    public TestForkJoin() {
    }

    public static void main(String[] args) {
        
        int limArr = 1000000000;
        System.out.println("Threshold: ");
        for (int i = 100; i < limThre; i *= 10) {
            System.out.print(i + "\t");
        }
        System.out.println();

        for (int arrLength = 100; arrLength < limArr; arrLength *= 10) {
            System.out.print(arrLength + ":\t");
            int[] arr = new int[arrLength];
            Random r = new Random();
            for (int i = 0; i < arrLength; i++) {
                arr[i] = r.nextInt();
            }
            long t = System.currentTimeMillis();
            ForkJoinPool fjp = new ForkJoinPool();
            long tcal = fjp.invoke(new MyTask(arr, 0, arrLength, limThre));
            t = System.currentTimeMillis() - t;
            System.out.println(tcal + "/" + t + "(" + (t != 0 ? (tcal * 100) / t : "NA") + "%)\t");
        }

        long maxBytes = Runtime.getRuntime().maxMemory();
        System.out.println("Max memory: " + maxBytes / 1024 / 1024 + "M");
    }
}
