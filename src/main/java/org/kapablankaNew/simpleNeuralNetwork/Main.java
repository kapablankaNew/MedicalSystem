package org.kapablankaNew.simpleNeuralNetwork;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException, CsvException {
        PictureConverter converter = new PictureConverter();
        List<Double> testing = converter.convert("test.png");
        System.out.println("Converting is fine!");
        converter.save("new_image.png", testing);
        System.out.println("All is fine!");
    }

    private static void Test() throws IOException, CsvException {
        CSVReader reader = new CSVReader(new FileReader("./src/heart.csv"));
        List<String[]> lines = reader.readAll();

        DataSet dataSet = new DataSet(lines.get(1).length - 1, 1);

        int ch = 0;
        for (String[] line : lines) {
            if (ch == 0) {
                ch = 1;
            } else {
                List<Double> inputs = new LinkedList<>();
                List<Double> outputs = new LinkedList<>();
                for (int i = 0; i < line.length - 1; i++) {
                    inputs.add(Double.parseDouble(line[i]));
                }
                outputs.add(Double.parseDouble(line[line.length - 1]));
                dataSet.addData(inputs, outputs);
            }
        }

        dataSet.scale();

        Topology topology = new Topology(13, 1, new int[]{16, 16, 8, 4, 2}, 0.01);
        NeuralNetwork neuralNetwork = new NeuralNetwork(topology);
        List<Neuron> result;

        for (int i = 0; i < 303; i++){
            result = neuralNetwork.predict(dataSet.getInputSignals(i));
            for (Neuron neuron : result){
                System.out.println("Good result: " +
                        dataSet.getExpectedResult(i).get(0) +
                        ", current result: " +
                        neuron.toString());
            }
        }

        System.out.println("Learn");
        neuralNetwork.learnBackPropagation(dataSet, 50000);

        double special = Math.pow(10, 4);
        for (int i = 0; i < 303; i++){
            result = neuralNetwork.predict(dataSet.getInputSignals(i));
            for (Neuron neuron : result){
                System.out.println("Line: " +
                        String.valueOf(i) +
                        " Good result: " +
                        dataSet.getExpectedResult(i).get(0) +
                        ", current result: " +
                        Math.round(neuron.getOutput()*special)/special);
            }
        }
    }

    private static void createCompleteDataset(DataSet dataSet){
        List<Double> inputs = Arrays.asList(0.0, 0.0, 0.0, 0.0);
        List<Double> outputs = Collections.singletonList(0.0);
        dataSet.addData(inputs, outputs);

        inputs = Arrays.asList(0.0, 0.0, 0.0, 1.0);
        outputs = Collections.singletonList(0.0);
        dataSet.addData(inputs, outputs);

        inputs = Arrays.asList(0.0, 0.0, 1.0, 0.0);
        outputs = Collections.singletonList(1.0);
        dataSet.addData(inputs, outputs);

        inputs = Arrays.asList(0.0, 0.0, 1.0, 1.0);
        outputs = Collections.singletonList(0.0);
        dataSet.addData(inputs, outputs);

        inputs = Arrays.asList(0.0, 1.0, 0.0, 0.0);
        outputs = Collections.singletonList(0.0);
        dataSet.addData(inputs, outputs);

        inputs = Arrays.asList(0.0, 1.0, 0.0, 1.0);
        outputs = Collections.singletonList(0.0);
        dataSet.addData(inputs, outputs);

        inputs = Arrays.asList(0.0, 1.0, 1.0, 0.0);
        outputs = Collections.singletonList(1.0);
        dataSet.addData(inputs, outputs);

        inputs = Arrays.asList(0.0, 1.0, 1.0, 1.0);
        outputs = Collections.singletonList(0.0);
        dataSet.addData(inputs, outputs);

        inputs = Arrays.asList(1.0, 0.0, 0.0, 0.0);
        outputs = Collections.singletonList(1.0);
        dataSet.addData(inputs, outputs);

        inputs = Arrays.asList(1.0, 0.0, 0.0, 1.0);
        outputs = Collections.singletonList(1.0);
        dataSet.addData(inputs, outputs);

        inputs = Arrays.asList(1.0, 0.0, 1.0, 0.0);
        outputs = Collections.singletonList(1.0);
        dataSet.addData(inputs, outputs);

        inputs = Arrays.asList(1.0, 0.0, 1.0, 1.0);
        outputs = Collections.singletonList(1.0);
        dataSet.addData(inputs, outputs);

        inputs = Arrays.asList(1.0, 1.0, 0.0, 0.0);
        outputs = Collections.singletonList(1.0);
        dataSet.addData(inputs, outputs);

        inputs = Arrays.asList(1.0, 1.0, 0.0, 1.0);
        outputs = Collections.singletonList(0.0);
        dataSet.addData(inputs, outputs);

        inputs = Arrays.asList(1.0, 1.0, 1.0, 0.0);
        outputs = Collections.singletonList(1.0);
        dataSet.addData(inputs, outputs);

        inputs = Arrays.asList(1.0, 1.0, 1.0, 1.0);
        outputs = Collections.singletonList(1.0);
        dataSet.addData(inputs, outputs);
    }
}

