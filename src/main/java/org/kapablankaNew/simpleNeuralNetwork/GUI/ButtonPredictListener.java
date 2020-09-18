package org.kapablankaNew.simpleNeuralNetwork.GUI;

import org.kapablankaNew.simpleNeuralNetwork.DataSetException;
import org.kapablankaNew.simpleNeuralNetwork.NeuralNetwork;
import org.kapablankaNew.simpleNeuralNetwork.NeuralNetworkException;
import org.kapablankaNew.simpleNeuralNetwork.Neuron;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ButtonPredictListener implements ActionListener {
    private NeuralNetwork neuralNetwork;
    private JFrame source;

    public ButtonPredictListener(NeuralNetwork neuralNetwork, JFrame source){
        this.neuralNetwork = neuralNetwork;
        this.source = source;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        List<Double> inputs = new ArrayList<>();
        try {
            List<Neuron> result = neuralNetwork.predict(inputs);
        } catch (NeuralNetworkException | DataSetException ea) {
            JOptionPane.showMessageDialog(source, ea.toString());
        }
    }
}
