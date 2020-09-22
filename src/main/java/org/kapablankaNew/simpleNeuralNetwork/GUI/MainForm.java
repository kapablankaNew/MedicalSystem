package org.kapablankaNew.simpleNeuralNetwork.GUI;

import com.jtattoo.plaf.acryl.AcrylLookAndFeel;
import org.kapablankaNew.simpleNeuralNetwork.DataSetException;
import org.kapablankaNew.simpleNeuralNetwork.NeuralNetwork;
import org.kapablankaNew.simpleNeuralNetwork.NeuralNetworkException;
import org.kapablankaNew.simpleNeuralNetwork.Neuron;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainForm {
    NeuralNetwork neuralNetwork = NeuralNetwork.load("NeuralNetwork");
    JFrame frame;
    JButton predict;
    JComboBox<String> comboBox;
    JLabel labelAge;
    MyJTextField fieldAge;
    JLabel labelPhosphokinase;
    MyJTextField fieldPhosphokinase;
    JLabel labelEjection;
    MyJTextField fieldEjection;
    JLabel labelPlatelets;
    MyJTextField fieldPlatelets;
    JLabel labelSerumCreatinine;
    MyJTextField fieldSerumCreatinine;
    JLabel labelSerumSodium;
    MyJTextField fieldSerumSodium;
    JLabel labelDiabetes;
    JComboBox<String> comboBoxDiabetes;
    JLabel labelHypertension;
    JComboBox<String> comboBoxHypertension;
    JLabel labelGender;
    JComboBox<String> comboBoxGender;
    JLabel labelAnaemia;
    JComboBox<String> comboBoxAnaemia;
    JLabel labelSmoking;
    JComboBox<String> comboBoxSmoking;
    JLabel labelResult;
    JTextField fieldResult;


    public MainForm() throws UnsupportedLookAndFeelException, IOException, ClassNotFoundException {
        InitializeComponent();
    }

    private void InitializeComponent() throws UnsupportedLookAndFeelException {
        UIManager.setLookAndFeel(new AcrylLookAndFeel());

        frame = new JFrame("Neural network");
        frame.setSize(670, 400);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel contentPanel = new JPanel();
        contentPanel.setSize(new Dimension(650, 375));
        contentPanel.setLayout(new BorderLayout(20, 20));

        predict = new JButton("Predict");
        predict.setSize(200, 15);
        predict.setPreferredSize(new Dimension(100, 15));
        predict.setFont(new Font("Verdana", Font.BOLD, 14));
        predict.addActionListener(new ButtonPredictListener());

        comboBox = new JComboBox<>(new String[]{
                "Acryl", "Aero", "Aluminium", "Bernstein", "Fast", "HiFi", "McWin",
                "Mint", "Noire", "Smart", "Luna", "Texture"
        });
        comboBox.addActionListener(new ComboBoxSkinsListener(frame));
        comboBox.setSize(100, 15);
        comboBox.setPreferredSize(new Dimension(100, 15));

        JPanel panelLAF = new JPanel();
        panelLAF.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        panelLAF.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        panelLAF.add(comboBox);

        fieldAge = new MyJTextField(300, 15, "Enter the age", frame);
        labelAge = new MyJLabel(300, 15, "Age");

        JPanel panelAge = new JPanel();
        panelAge.setLayout(new BoxLayout(panelAge, BoxLayout.Y_AXIS));
        panelAge.add(labelAge);
        panelAge.add(fieldAge);
        panelAge.setPreferredSize(new Dimension(310, 40));
        panelAge.setSize(new Dimension(310, 40));

        fieldPhosphokinase = new MyJTextField(300, 15, "Enter the level of creatinine phosphokinase", frame);
        labelPhosphokinase = new MyJLabel(300, 15, "Creatinine phosphokinase");

        JPanel panelPhosphokinase = new JPanel();
        panelPhosphokinase.setLayout(new BoxLayout(panelPhosphokinase, BoxLayout.Y_AXIS));
        panelPhosphokinase.add(labelPhosphokinase);
        panelPhosphokinase.add(fieldPhosphokinase);
        panelPhosphokinase.setPreferredSize(new Dimension(310, 40));
        panelPhosphokinase.setSize(new Dimension(310, 40));

        fieldEjection = new MyJTextField(300, 15, "Enter the ejection fraction", frame);
        labelEjection = new MyJLabel(300, 15, "Ejection fraction");

        JPanel panelEjection = new JPanel();
        panelEjection.setLayout(new BoxLayout(panelEjection, BoxLayout.Y_AXIS));
        panelEjection.add(labelEjection);
        panelEjection.add(fieldEjection);
        panelEjection.setPreferredSize(new Dimension(310, 40));
        panelEjection.setSize(new Dimension(310, 40));

        fieldPlatelets = new MyJTextField(300, 15, "Enter the Level of platelets in " +
                "blood (kiloplatelets/mL)", frame);
        labelPlatelets = new MyJLabel(300, 15, "Level of platelets in blood (kiloplatelets/mL)");

        JPanel panelPlatelets = new JPanel();
        panelPlatelets.setLayout(new BoxLayout(panelPlatelets, BoxLayout.Y_AXIS));
        panelPlatelets.add(labelPlatelets);
        panelPlatelets.add(fieldPlatelets);
        panelPlatelets.setPreferredSize(new Dimension(310, 40));
        panelPlatelets.setSize(new Dimension(310, 40));

        fieldSerumCreatinine = new MyJTextField(300, 15, "Enter the level of serum creatinine in blood" +
                " (µmol/l)", frame);
        labelSerumCreatinine = new MyJLabel(300, 15, "Level of serum creatinine in blood (µmol/l)");

        JPanel panelSerumCreatinine = new JPanel();
        panelSerumCreatinine.setLayout(new BoxLayout(panelSerumCreatinine, BoxLayout.Y_AXIS));
        panelSerumCreatinine.add(labelSerumCreatinine);
        panelSerumCreatinine.add(fieldSerumCreatinine);
        panelSerumCreatinine.setPreferredSize(new Dimension(310, 40));
        panelSerumCreatinine.setSize(new Dimension(310, 40));

        fieldSerumSodium = new MyJTextField(300, 15, "Enter the level of serum sodium in blood" +
                " (mmol/l)", frame);
        labelSerumSodium = new MyJLabel(300, 15, "Level of serum sodium in blood (mmol/l)");

        JPanel panelSerumSodium = new JPanel();
        panelSerumSodium.setLayout(new BoxLayout(panelSerumSodium, BoxLayout.Y_AXIS));
        panelSerumSodium.add(labelSerumSodium);
        panelSerumSodium.add(fieldSerumSodium);
        panelSerumSodium.setPreferredSize(new Dimension(310, 40));
        panelSerumSodium.setSize(new Dimension(310, 40));

        labelDiabetes = new MyJLabel(300, 15, "Have patient diabetes?");
        comboBoxDiabetes = new JComboBox<>(new String[]{"Yes", "No"});

        JPanel panelDiabetes = new JPanel();
        panelDiabetes.setLayout(new BoxLayout(panelDiabetes, BoxLayout.Y_AXIS));
        panelDiabetes.add(labelDiabetes);
        panelDiabetes.add(comboBoxDiabetes);
        panelDiabetes.setPreferredSize(new Dimension(310, 40));
        panelDiabetes.setSize(new Dimension(310, 40));

        labelHypertension = new MyJLabel(300, 15, "Have patient hypertension?");
        comboBoxHypertension = new JComboBox<>(new String[]{"Yes", "No"});

        JPanel panelHypertension = new JPanel();
        panelHypertension.setLayout(new BoxLayout(panelHypertension, BoxLayout.Y_AXIS));
        panelHypertension.add(labelHypertension);
        panelHypertension.add(comboBoxHypertension);
        panelHypertension.setPreferredSize(new Dimension(310, 40));
        panelHypertension.setSize(new Dimension(310, 40));

        labelGender = new MyJLabel(300, 15, "Gender of patient");
        comboBoxGender = new JComboBox<>(new String[]{"Male", "Female"});

        JPanel panelGender = new JPanel();
        panelGender.setLayout(new BoxLayout(panelGender, BoxLayout.Y_AXIS));
        panelGender.add(labelGender);
        panelGender.add(comboBoxGender);
        panelGender.setPreferredSize(new Dimension(310, 40));
        panelGender.setSize(new Dimension(310, 40));

        labelAnaemia = new MyJLabel(300, 15, "Have patient anaemia?");
        comboBoxAnaemia = new JComboBox<>(new String[]{"Yes", "No"});

        JPanel panelAnaemia = new JPanel();
        panelAnaemia.setLayout(new BoxLayout(panelAnaemia, BoxLayout.Y_AXIS));
        panelAnaemia.add(labelAnaemia);
        panelAnaemia.add(comboBoxAnaemia);
        panelAnaemia.setPreferredSize(new Dimension(310, 40));
        panelAnaemia.setSize(new Dimension(310, 40));

        labelSmoking = new MyJLabel(300, 15, "Does the patient smoke?");
        comboBoxSmoking = new JComboBox<>(new String[]{"Yes", "No"});

        JPanel panelSmoking = new JPanel();
        panelSmoking.setLayout(new BoxLayout(panelSmoking, BoxLayout.Y_AXIS));
        panelSmoking.add(labelSmoking);
        panelSmoking.add(comboBoxSmoking);
        panelSmoking.setPreferredSize(new Dimension(310, 40));
        panelSmoking.setSize(new Dimension(310, 40));

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 2, 10, 10));
        panel.add(panelAge);
        panel.add(panelPhosphokinase);
        panel.add(panelEjection);
        panel.add(panelPlatelets);
        panel.add(panelSerumCreatinine);
        panel.add(panelSerumSodium);
        panel.add(panelDiabetes);
        panel.add(panelHypertension);
        panel.add(panelAnaemia);
        panel.add(panelSmoking);
        panel.add(panelGender);

        labelResult = new MyJLabel(300, 15, "Result: the chance of a heart failure in " +
                "the patient:");
        fieldResult = new JTextField();
        fieldResult.setSize(300, 15);
        fieldResult.setFont(new Font("Verdana", Font.PLAIN, 12));
        fieldResult.setEditable(false);

        JPanel panelResult = new JPanel();
        panelResult.setLayout(new BoxLayout(panelResult, BoxLayout.Y_AXIS));
        panelResult.add(labelResult);
        panelResult.add(fieldResult);
        panelResult.setPreferredSize(new Dimension(310, 40));
        panelResult.setSize(new Dimension(310, 40));

        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        contentPanel.add(panelLAF, BorderLayout.EAST);
        contentPanel.add(predict, BorderLayout.WEST);
        contentPanel.add(panel, BorderLayout.NORTH);
        contentPanel.add(panelResult, BorderLayout.CENTER);


        frame.add(contentPanel);
        frame.setVisible(true);
    }

    class ButtonPredictListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            List<Double> inputs = new ArrayList<>();
            try {
                double age = Double.parseDouble(fieldAge.getText());
                double anaemia = comboBoxAnaemia.getSelectedItem() == "Yes" ? 1 : 0;
                double phosphokinase = Double.parseDouble(fieldPhosphokinase.getText());
                double diabetes = comboBoxDiabetes.getSelectedItem() == "Yes" ? 1 : 0;
                double ejection = Double.parseDouble(fieldEjection.getText());
                double hypertension = comboBoxHypertension.getSelectedItem() == "Yes" ? 1 : 0;
                double platelets = Double.parseDouble(fieldPlatelets.getText());
                double creatinine = Double.parseDouble(fieldSerumCreatinine.getText());
                double sodium = Double.parseDouble(fieldSerumSodium.getText());
                double gender = comboBoxGender.getSelectedItem() == "Male" ? 1 : 0;
                double smoking = comboBoxSmoking.getSelectedItem() == "Yes" ? 1 : 0;
                if (age > 120 || age < 0){
                    throw new IllegalArgumentException("Age should be from 0 to 120 years!");
                }
                if (phosphokinase < 20 || phosphokinase > 10_000){
                    throw new IllegalArgumentException("Level of creatinine phosphokinase should be from 20 " +
                            "to 10 000!");
                }
                if (ejection < 0 || ejection > 100){
                    throw new IllegalArgumentException("Ejection fraction should be from 0 to 100!");
                }
                if (platelets < 25 || platelets > 1000){
                    throw new IllegalArgumentException("Level of platelets in blood should be " +
                            "from 25 to 1000 kiloplatelets/mL");
                }
                if (creatinine < 25|| creatinine > 1000){
                    throw new IllegalArgumentException("Level of serum creatinine in blood should be from " +
                            "25 to 1000 µmol/l");
                }
                if (sodium < 80 || sodium > 220){
                    throw new IllegalArgumentException("Level of serum sodium in blood should be from " +
                            "80 to 220 mmol/l");
                }
                inputs.add(age);
                inputs.add(anaemia);
                inputs.add(phosphokinase);
                inputs.add(diabetes);
                inputs.add(ejection);
                inputs.add(hypertension);
                inputs.add(platelets*1000);
                inputs.add(creatinine/100.0);
                inputs.add(sodium);
                inputs.add(gender);
                inputs.add(smoking);
                List<Neuron> result = neuralNetwork.predict(inputs);
                double chance = result.get(0).getResult();
                chance = Math.round(chance * Math.pow(10, 6))/Math.pow(10, 4);
                fieldResult.setText(chance + " %");
            } catch (NeuralNetworkException | DataSetException ea) {
                JOptionPane.showMessageDialog(frame, ea.getMessage());
            } catch (Exception ea) {
                JOptionPane.showMessageDialog(frame, "Attention! Error! " + ea.getMessage());
            }
        }
    }
}
