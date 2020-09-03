import com.opencsv.CSVReader;
import lombok.SneakyThrows;
import org.junit.Test;
import org.kapablankaNew.simpleNeuralNetwork.DataSet;
import org.kapablankaNew.simpleNeuralNetwork.NeuralNetwork;
import org.kapablankaNew.simpleNeuralNetwork.Neuron;
import org.kapablankaNew.simpleNeuralNetwork.Topology;

import java.io.FileReader;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class NeuralNetworkTest {
    private NeuralNetwork neuralNetwork;

    @Test
    @SneakyThrows
    public void feedForwardTest() {
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

        dataSet.normalize();

        Topology topology = new Topology(13, 1, new int[]{10, 5, 2}, 0.001);
        NeuralNetwork neuralNetwork = new NeuralNetwork(topology);
        List<Neuron> result;

        neuralNetwork.learnBackPropagation(dataSet, 100000);

        double special = Math.pow(10, 2);

        for (int i = 0; i < 304; i++) {
            result = neuralNetwork.predict(dataSet.getInputSignals(i));
            double current = Math.round(result.get(0).getOutput() * special) / special;
            assertEquals("line" + String.valueOf(i), dataSet.getExpectedResult(i).get(0), current, 0.15);
        }
    }
}
