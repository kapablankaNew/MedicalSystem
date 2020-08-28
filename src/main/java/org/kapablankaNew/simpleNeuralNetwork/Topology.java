package org.kapablankaNew.simpleNeuralNetwork;

import java.util.*;

public class Topology {
    //number of inputs in neural network (in first layer)
    private final int inputCount;
    //number of outputs of neural network (ih last layer)
    private final int outputCount;
    //number of neurons in several hidden layer
    private final List<Integer> hiddenLayers;

    private final double learningRate;

    public Topology(int inputCount, int outputCount, int[] layers, double learningRate) {
        this.inputCount = inputCount;
        this.outputCount = outputCount;
        this.learningRate = learningRate;
        hiddenLayers = new ArrayList<Integer>();
        for (int i = 0; i < layers.length; i++){
            hiddenLayers.add(layers[i]);
        }
    }

    public Topology(int inputCount, int outputCount, int layer, double learningRate){
        this(inputCount, outputCount, new int[] {layer}, learningRate);
    }

    public Topology(int inputCount, int outputCount, double learningRate){
        this(inputCount, outputCount, null, learningRate);
    }

    public double getLearningRate() {
        return learningRate;
    }

    public int getInputCount() {
        return inputCount;
    }

    public int getOutputCount() {
        return outputCount;
    }

    public List<Integer> getHiddenLayers() {
        return hiddenLayers;
    }

    public int getCountOfNeuronsInLayer(int index) {
        return hiddenLayers.get(index);
    }
}
