package org.kapablankaNew.simpleNeuralNetwork;

import java.util.*;

public class Layer {
    //list of neurons in this layer
    private final List<Neuron> neurons;

    private final NeuronType layerType;

    public Layer(List<Neuron> neurons, NeuronType type) {
        this.neurons = neurons;
        layerType = type;
    }

    public NeuronType getLayerType() {
        return layerType;
    }

    public Layer(List<Neuron> neurons) {
        this(neurons, NeuronType.Normal);
    }

    public List<Neuron> getNeurons() {
        return neurons;
    }

    public Neuron getNeuron(int index){
        return neurons.get(index);
    }

    public int getCount() {
        return neurons.size();
    }

    //method for combining the output signals of all neurons in this layer into a single list
    public List<Double> getOutputSignals(){
        List<Double> signals = new ArrayList<>();
        for(Neuron neuron : neurons){
            signals.add(neuron.getResult());
        }
        return signals;
    }


}
