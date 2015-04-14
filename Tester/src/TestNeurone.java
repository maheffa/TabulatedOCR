// File:    TestNeurone.java
// Created: 14/04/2015

/**
 * @author mahefa
 */
public class TestNeurone {

    public TestNeurone() {
    }

    public static void main(String[] args) {
        BackPropagationNetwork bp = new BackPropagationNetwork(new int[]{9, 5, 3});
        int[][] trainData = new int[][] {
                {1,1,1,0,0,0,0,0,0},
                {0,1,1,0,0,0,0,0,0},
                {0,0,0,1,1,1,0,0,0},
                {0,0,0,0,0,0,1,1,1},
                {0,0,0,0,0,0,0,1,1},
        };
        int[] real = new int[]{0, 0, 1, 2, 2};
        for (int i = 0; i < real.length; i++) {
            bp.addTraining(trainData[i], real[i]);
        }
        bp.train(0.8, 0.5, 0.001);
        System.out.println(bp.recognize(new int[]{0,0,0,1,0,0,0,1,1}));
    }

}
