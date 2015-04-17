// File:    Cluster.java
// Created: 15/04/2015

import java.util.*;

/**
 * @author mahefa
 */
public class KMeanCluster{
    private ArrayList<Integer> data;
    private long sum;
    private int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;

    public KMeanCluster() {
        data = new ArrayList<Integer>();
        sum = 0;
    }

    public void add(int val) {
        sum += val;
        data.add(val);
        if (val < min) {
            min = val;
        }
        if (val > max) {
            max = val;
        }
    }

    public int getMean() {
        return (int)(sum / data.size());
    }

    public int size() {
        return data.size();
    }

    public int get(int i) {
        return data.get(i);
    }

    public ArrayList<Integer> getData() {
        return this.data;
    }

    public KMeanCluster[] cluster(int numCluster, int maxIterations) {
        int iterations = 0;
        KMeanCluster[] clusters0 = new KMeanCluster[numCluster];
        KMeanCluster[] clusters1;
        int[] means = new int[numCluster];
        for (int i = 0; i < numCluster; i++) {
            means[i] = min + ((max - min) * i) / numCluster;
            clusters0[i] = new KMeanCluster();
        }

        for (int i = 0; i < size(); i++) {
            int index = closestMean(get(i), means);
            clusters0[index].add(get(i));
        }

        do {
//            System.out.println("*** iteration " + iterations + " ***");
//            for (int i = 0; i < numCluster; i++) {
//                System.out.println(clusters0[i]);
//            }
            for (int i = 0; i < numCluster; i++) {
                means[i] = clusters0[i].getMean();
            }
            clusters1 = new KMeanCluster[numCluster];
            for (int i = 0; i < numCluster; i++) {
                clusters1[i] = new KMeanCluster();
            }
            for (int i = 0; i < size(); i++) {
                int index = closestMean(get(i), means);
                clusters1[index].add(get(i));
            }
            boolean equals = true;
            for (int i = 0; i < numCluster && equals; i++) {
                if (clusters0[i].size() != clusters1[i].size()) {
                    equals = false;
                }
            }
            if (equals) {
                break;
            } else {
                clusters0 = clusters1;
                iterations++;
            }
        } while (iterations < maxIterations);
        System.out.println("Number of iterations: " + iterations);
        return clusters0;
    }

    public static int closestMean(int val, int[] means) {
        int min = Integer.MAX_VALUE;
        int imin = -1;
        for (int i = 0; i < means.length; i++) {
            if (Math.abs(val - means[i]) < min) {
                imin = i;
                min = Math.abs(val - means[i]);
            }
        }
        return imin;
    }

    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("Cluster {").append('\n');
        str.append('\t').append("min: ").append(min).append('\n');
        str.append('\t').append("max: ").append(max).append('\n');
        str.append('\t').append("mean: ").append(getMean()).append('\n');
        str.append('\t').append("size: ").append(size()).append('\n');
        str.append('\t').append("data:");
        for (int e : data) {
            str.append(' ').append(e).append(',');
        }
        return "Cluster: {" + str.toString() + "}";
    }

}
