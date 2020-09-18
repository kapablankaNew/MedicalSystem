package org.kapablankaNew.simpleNeuralNetwork.GUI;

import com.jtattoo.plaf.acryl.AcrylLookAndFeel;
import org.kapablankaNew.simpleNeuralNetwork.NeuralNetwork;

import javax.swing.*;
import java.awt.*;

public class MainForm {
    NeuralNetwork neuralNetwork; // = NeuralNetwork.load("NeuralNetwork");
    JFrame frame;
    JButton predict;
    JComboBox comboBox;

    public MainForm() throws UnsupportedLookAndFeelException {
        InitializeComponent();
    }

    private void InitializeComponent() throws UnsupportedLookAndFeelException {
        UIManager.setLookAndFeel(new AcrylLookAndFeel());
        predict = new JButton("Predict");
        predict.addActionListener(new ButtonPredictListener(neuralNetwork, frame));

        comboBox = new JComboBox<>(new String[]{
                "Acryl", "Aero", "Aluminium", "Bernstein", "Fast", "HiFi", "McWin",
                "Mint", "Noire", "Smart", "Luna", "Texture"
        });

        frame = new JFrame("Neural network");
        frame.setSize(new Dimension(700, 700));
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());
        comboBox.addActionListener(new ComboBoxSkinsListener(frame));
        frame.add(comboBox);
        frame.setVisible(true);
    }
}
