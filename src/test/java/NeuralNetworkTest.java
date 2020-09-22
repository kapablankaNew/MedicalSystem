import com.opencsv.CSVReader;
import lombok.SneakyThrows;
import org.junit.Test;
import org.kapablankaNew.simpleNeuralNetwork.DataSet;
import org.kapablankaNew.simpleNeuralNetwork.NeuralNetwork;
import org.kapablankaNew.simpleNeuralNetwork.Neuron;
import org.kapablankaNew.simpleNeuralNetwork.Topology;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class NeuralNetworkTest {
    private NeuralNetwork neuralNetwork;

    @Test
    @SneakyThrows
    public void feedForwardTest() {
        CSVReader reader = new CSVReader(new FileReader("./src/main/resources/heart_failure_clinical_records_dataset.csv"));
        List<String[]> lines = reader.readAll();
        DataSet dataSet = new DataSet(lines.get(1).length - 2, 1);
        DataSet testDataSet = new DataSet(lines.get(1).length - 2, 1);

        int ch = 0;
        for (String[] line : lines) {
            if (ch == 0) {
                ch = 1;
            } else {
                List<Double> inputs = new ArrayList<>();
                List<Double> inputsTest = new ArrayList<>();
                List<Double> outputs = new ArrayList<>();
                List<Double> outputsTest = new ArrayList<>();
                for (int i = 0; i < line.length - 2; i++) {
                    inputs.add(Double.parseDouble(line[i]));
                    inputsTest.add(Double.parseDouble(line[i]));
                }
                outputs.add(Double.parseDouble(line[line.length - 1]));
                outputsTest.add(Double.parseDouble(line[line.length - 1]));
                dataSet.addData(inputs, outputs);
                testDataSet.addData(inputsTest, outputsTest);
            }
        }

        System.out.println("Learning start!");
        Topology topology = new Topology(11, 1, new int[]{15}, 0.01);
        NeuralNetwork neuralNetwork = new NeuralNetwork(topology);
        List<Neuron> result;

        neuralNetwork.learnBackPropagation(dataSet, 30000);

        System.out.println("Learning finish!");
        double special = Math.pow(10, 2);

        for (int i = 0; i < 299; i++) {
            result = neuralNetwork.predict(testDataSet.getInputSignals(i));
            double current = Math.round(result.get(0).getResult() * special) / special;
            double expected = testDataSet.getExpectedResult(i).get(0);
            if (Math.abs(current - expected) > 0.4) {
                System.out.println("Good result: " + expected
                        + ", current result: " + current);
            }
        }
        neuralNetwork.save("NeuralNetwork");
    }
}
