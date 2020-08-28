package org.kapablankaNew.simpleNeuralNetwork;

import java.util.*;

public class DataSet {
    private final List<List<Double>> inputSignals;

    private final List<List<Double>> expectedResults;

    private final int outputCount;

    private final int inputCount;

    private final List<Double> avr;

    private final List<Double> standardDeviation;

    private final List<Double> max;

    private final List<Double> min;

    public DataSet(int inputCount, int outputCount){
        inputSignals = new ArrayList<>();
        expectedResults = new ArrayList<>();
        this.inputCount = inputCount;
        this.outputCount = outputCount;
        avr = new ArrayList<>();
        standardDeviation = new ArrayList<>();
        min = new ArrayList<>();
        max = new ArrayList<>();
    }

    public List<Double> getInputSignals(int index){
        return inputSignals.get(index);
    }

    public List<Double> getExpectedResult(int index){
        return expectedResults.get(index);
    }

    public int getOutputCount() {
        return outputCount;
    }

    public int getInputCount() {
        return inputCount;
    }

    public int getSize(){
        return inputSignals.size();
    }

    public void addData(List<Double> inputs, List<Double> results){
        inputSignals.add(inputs);
        expectedResults.add(results);
    }

    public void normalize(){
        int n = inputSignals.size();
        avr.clear();
        standardDeviation.clear();
        for (int i = 0; i < inputCount; i++){
            double sum = 0;
            for (List<Double> entry : inputSignals) {
                sum += entry.get(i);
            }
            avr.add(sum / n);
            sum = 0;
            for (List<Double> entry : inputSignals) {
                sum += Math.pow(entry.get(i) - avr.get(i), 2);
            }
            standardDeviation.add(Math.sqrt(sum / n));
            for (List<Double> entry : inputSignals) {
                entry.set(i, (entry.get(i) - avr.get(i)) / standardDeviation.get(i));
            }
        }
    }

    public void normalizeEntry(List<Double> inputSignals){
        for (int i = 0; i < inputCount; i++){
            inputSignals.set(i, (inputSignals.get(i) - avr.get(i)) / standardDeviation.get(i));
        }
    }

    public void scale(){
        max.clear();
        min.clear();
        for (int i = 0; i < inputCount; i++){
            double maximum, minimum;
            maximum = minimum = inputSignals.get(0).get(i);
            for (List<Double> entry : inputSignals){
                double item = entry.get(i);
                if (item > maximum){
                    maximum = item;
                }
                if (item < minimum){
                    minimum = item;
                }
            }
            max.add(maximum);
            min.add(minimum);
            for (List<Double> entry : inputSignals){
                entry.set(i, (entry.get(i) - min.get(i)) / (max.get(i) - min.get(i)));
            }
        }
    }

    public void scaleEntry(List<Double> inputSignals){
        for (int i = 0; i < inputCount; i++){
            inputSignals.set(i, (inputSignals.get(i) - min.get(i)) / (max.get(i) - min.get(i)));
        }
    }
}
