// File:    TestKMeans.java
// Created: 15/04/2015

import java.util.Random;

/**
 * @author mahefa
 */
public class TestKMeans {

    public TestKMeans() {
    }

    public static void main(String[] args) {
        int size = 100;
        int[] data = new int[size];
        Random r = new Random();
        KMeanCluster clust = new KMeanCluster();
        for (int i = 0; i < size; i++) {
            data[i] = r.nextInt(size * 3);
        }

        for (int i : data) {
            clust.add(i);
        }

        int[] means = new int[]{10, 30};

        System.out.println("initial cluster: " + clust);
        KMeanCluster[] clusts = clust.cluster(2, 100);
        System.out.println("Clustered");
        for (KMeanCluster cl: clusts) {
            System.out.println(cl);
        }
    }

}
