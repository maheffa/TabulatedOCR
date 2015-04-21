// File:    BackPropagation.java
// Created: 06/04/2015

import org.encog.engine.network.activation.ActivationSigmoid;
import org.encog.mathutil.randomize.ConsistentRandomizer;
import org.encog.ml.data.MLData;
import org.encog.ml.data.MLDataSet;
import org.encog.ml.data.basic.BasicMLData;
import org.encog.ml.data.basic.BasicMLDataSet;
import org.encog.neural.networks.BasicNetwork;
import org.encog.neural.networks.layers.BasicLayer;
import org.encog.neural.networks.training.propagation.back.Backpropagation;

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

    public void addTraining(int[] inValue, int outValue) {
        double[] i = new double[inValue.length];
        double[] o = new double[outputSize];
        for (int j = 0; j < i.length; j++) {
            i[j] = inValue[j] == BinaryImage.BLACK ? 1.0 : 0.0;
        }
        o[outValue] = 1.0;
        MLData idata = new BasicMLData(i);
        MLData odata = new BasicMLData(o);
        trainingSet.add(idata, odata);
    }

    public void train(double learningRate, double momentum, double minError) {
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
            if (lastError == train.getError()) {
                log.warning("Error had converged to " + lastError + ". Now quitting");
                break;
            }  else {
                lastError = train.getError();
            }
        } while (train.getError() > minError);
        log.info("Epoch finished\n" +
                "Final error: " + train.getError() + "\n" +
                "Last delta: " + Arrays.toString(train.getLastDelta()));
    }

    public int recognize(int[] inValue) {
        double[] i = new double[inValue.length];
        for (int j = 0; j < i.length; j++) {
            i[j] = inValue[j] == 0 ? 0.0 : 1.0;
        }
        double[] outValue = network.compute(new BasicMLData(i)).getData();
        System.out.printf("Result : " + Arrays.toString(outValue));
        for (int j = 0; j < outValue.length; j++) {
            if (outValue[j] > 0.5) {
                return j;
            }
        }
        return -1;
    }



}
