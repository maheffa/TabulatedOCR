// File:    BackPropagation.java
// Created: 06/04/2015

import org.encog.engine.network.activation.ActivationSigmoid;
import org.encog.mathutil.randomize.ConsistentRandomizer;
import org.encog.ml.data.MLData;
import org.encog.ml.data.MLDataPair;
import org.encog.ml.data.MLDataSet;
import org.encog.ml.data.basic.BasicMLData;
import org.encog.ml.data.basic.BasicMLDataSet;
import org.encog.neural.networks.BasicNetwork;
import org.encog.neural.networks.layers.BasicLayer;
import org.encog.neural.networks.training.propagation.back.Backpropagation;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author mahefa
 */
public class BackPropagationNetwork {

    private int[] layers;
    private BasicNetwork network;
    private MLDataSet trainingSet;
    private int outputSize;

    public BackPropagationNetwork(int[] layers) {
        this.outputSize = layers[layers.length - 1];
        this.layers = layers;
        network = new BasicNetwork();
        network.addLayer(new BasicLayer(null, true, layers[0]));
        for (int i = 0; i < layers.length - 1; i++) {
            network.addLayer(new BasicLayer(new ActivationSigmoid(), true, layers[i + 1]));
        }
        network.getStructure().finalizeStructure();
        network.reset();
        new ConsistentRandomizer(-1,1,500).randomize(network);
        trainingSet = new BasicMLDataSet();
    }

    public BackPropagationNetwork(BackPropagationNetwork copy, boolean keepTraining) {
        this.outputSize = copy.outputSize;
        this.layers = copy.layers;
        this.network = copy.network;
        if (keepTraining) {
            this.trainingSet = copy.trainingSet;
        } else {
            this.trainingSet = new BasicMLDataSet();
        }
    }

    public void addTraining(double[] inValue, int outValue) {
        double[] o = new double[outputSize];
        o[outValue] = 1.0;
        MLData idata = new BasicMLData(inValue);
        MLData odata = new BasicMLData(o);
        trainingSet.add(idata, odata);
    }

    public void train(double learningRate, double momentum, double minError) {
        int maxNEpoch = 10000;
        Logger log = Logger.getAnonymousLogger();
        Backpropagation train = new Backpropagation(network, trainingSet, learningRate, momentum);
        train.fixFlatSpot(false);
        int epoch = 0;
        double lastError = 1;
        log.info("Starting learning");
        do {
            train.iteration();
            epoch++;
            log.log(Level.INFO, "Epoch " + epoch + ", error = " + train.getError());
//            if (Math.abs(lastError - train.getError()) < Math.pow(10, -9)) {
//                log.warning("Error had converged to " + lastError + ". Now quitting");
//                break;
//            }  else {
//                lastError = train.getError();
//            }
        } while (train.getError() > minError && epoch < maxNEpoch);
        log.info("Epoch finished " + epoch + "\n" +
                "Final error: " + train.getError() + "\n" );
//                "Last delta: " + Arrays.toString(train.getLastDelta()));
    }

    public void checkLeraningSuccess() {
        System.out.println("Checking learning result");
        int count = 0, success = 0;
        while (count < trainingSet.size()) {
            MLDataPair dp = trainingSet.get(count);
            double[] inArr = dp.getInputArray();
            double[] outArr = dp.getIdealArray();
            double[] output = new double[outArr.length];
            network.compute(inArr, output);
            int imax = 0;
            double max = -1;
            for (int i = 0; i < outArr.length; i++) {
                if (outArr[i] > max) {
                    max = outArr[i];
                    imax = i;
                }
            }
            int imax0 = 0;
            double max0 = -1;
            for (int i = 0; i < output.length; i++) {
                if (output[i] > max0) {
                    max0 = output[i];
                    imax0 = i;
                }
            }
            System.out.println("expected: " + Arrays.toString(outArr));
            System.out.println("result: " + Arrays.toString(output));
//            if (Arrays.equals(output, outArr)) {
//                success++;
//            }
            if (imax0 == imax) {
                success++;
            }
            count++;
        }
        System.out.println("Success rate : " + ((success * 100) / count) + "% (" + success + "/" + count + ")");
    }

    public int recognize(double[] inValue) {
        int nTopMax = 5;
        double[] valTopMax = new double[nTopMax];
        int[] indexTopMax = new int[nTopMax];
        Arrays.fill(valTopMax, -(Double.MAX_VALUE - 1));

        double[] outValue = network.compute(new BasicMLData(inValue)).getData();
        System.out.printf("Result : " + Arrays.toString(outValue));
        for (int i = 0; i < outValue.length; i++) {
            double v = outValue[i];
            int iv = i;
            int j = 0;
            while (j < nTopMax && valTopMax[j] > v){
                j++;
            }
            if (j == nTopMax) {
                continue;
            } else {
                double tmp = 0;
                int iTmp;
                while (j < nTopMax) {
                    tmp = valTopMax[j];
                    valTopMax[j] = v;
                    v = tmp;
                    iTmp = indexTopMax[j];
                    indexTopMax[j] = iv;
                    iv = iTmp;
                    j++;
                }
            }
//            if (outValue[i] > 0.5) {
//                return i;
//            }
        }
//        System.out.println("Results: valtopMax " + Arrays.toString(valTopMax) + ", indextopmax " + Arrays.toString(indexTopMax));
        return indexTopMax[0];
    }



}
