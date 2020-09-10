package org.kapablankaNew.simpleNeuralNetwork;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException, TopologyException, NeuralNetworkException, CsvException, DataSetException {
        Test();
        PictureConverter converter = new PictureConverter(500, 500);
        File file = new File("test.png");
        BufferedImage image = ImageIO.read(file);

        List<Double> testing = converter.convertImageToListOfSignals(image);
        System.out.println("Converting is fine!");
        image = converter.getImageFromSignals(testing);
        file = new File("new_image.png");
        ImageIO.write(image, "png", file);
        System.out.println("All is fine!");
    }

    private static void Test() throws IOException, CsvException, DataSetException, TopologyException, NeuralNetworkException {
        CSVReader reader = new CSVReader(new FileReader("./src/heart.csv"));
        List<String[]> lines = reader.readAll();

        DataSet dataSet = new DataSet(lines.get(1).length - 1, 1);
        DataSet dataSet1 = new DataSet(lines.get(1).length - 1, 1);

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
                List<Double> inputs1 = new LinkedList<>(inputs);
                List<Double> outputs1 = new LinkedList<>(outputs);
                dataSet.addData(inputs, outputs);
                dataSet1.addData(inputs1, outputs1);
            }
        }

        System.out.println(dataSet.getInputSignals(5) == dataSet1.getInputSignals(5));
        Topology topology = new Topology(13, 1, new int[]{16, 16, 8, 4, 2}, 0.01);
        NeuralNetwork neuralNetwork = new NeuralNetwork(topology);
        List<Neuron> result;

//        for (int i = 0; i < 303; i++) {
//            result = neuralNetwork.predict(dataSet.getInputSignals(i));
//            for (Neuron neuron : result) {
//                System.out.println("Good result: " +
//                        dataSet.getExpectedResult(i).get(0) +
//                        ", current result: " +
//                        neuron.toString());
//            }
//        }

        System.out.println("Learn");
        neuralNetwork.learnBackPropagation(dataSet, 5);

        double special = Math.pow(10, 4);
        for (int i = 0; i < 303; i++) {
            result = neuralNetwork.predict(dataSet1.getInputSignals(i));
            for (Neuron neuron : result) {
                System.out.println("Line: " +
                        i + " Good result: " +
                        dataSet1.getExpectedResult(i).get(0) +
                        ", current result: " +
                        Math.round(neuron.getOutput() * special) / special);
            }
        }
    }

    private static void createCompleteDataset(DataSet dataSet) throws DataSetException {
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

