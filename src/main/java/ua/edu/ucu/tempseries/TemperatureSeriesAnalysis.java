package ua.edu.ucu.tempseries;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
//import java.lang.Math;
import java.util.List;

public class TemperatureSeriesAnalysis {

    private static final double MIN_POSSIBLE_TEMP = -273.5;
    private static final double DIFF_FOR_COMPARISON = .000001;

    private double[] tempSeries;

    public TemperatureSeriesAnalysis() {
        this.tempSeries = new double[]{};
    }

    public TemperatureSeriesAnalysis(double[] temperatureSeries) {
        for (double t: temperatureSeries) {
            if (t < MIN_POSSIBLE_TEMP) {
                throw new InputMismatchException();
            }
        }
        this.tempSeries = Arrays.copyOf(temperatureSeries,
                temperatureSeries.length);
    }

    public void check(double[] input) {
        if (input.length == 0) {
            throw new IllegalArgumentException();
        }
    }

    public double average() {
        check(tempSeries);
        double avgTemp = 0;
        for (double t: tempSeries) {
            avgTemp += t;
        }
        return avgTemp / tempSeries.length;
    }

    public double deviation() {
        check(tempSeries);
        double deviation = 0.0;
        double mean = average();
        for (double t: tempSeries) {
            deviation += (t - mean) * (t - mean);
        }
        return Math.sqrt(deviation/tempSeries.length);
    }

    public double min() {
        check(tempSeries);
        return findTempClosestToValue(Double.NEGATIVE_INFINITY);
    }

    public double max() {
        check(tempSeries);
        return findTempClosestToValue(Double.POSITIVE_INFINITY);
    }

    public double findTempClosestToZero() {
        check(tempSeries);
        return findTempClosestToValue(0);
    }

    public double findTempClosestToValue(double tempValue) {
        check(tempSeries);
        double minDiff = Double.POSITIVE_INFINITY;
        double closest = tempSeries[0];
        for (double t: tempSeries) {
            if (Math.abs(t - tempValue) < minDiff) {
                closest = t;
                minDiff = Math.abs(closest - tempValue);
            }
            if (t > 0 && Math.abs(t - tempValue
                    - minDiff) < DIFF_FOR_COMPARISON) {
                closest = t;
                minDiff = Math.abs(closest - tempValue);
            }
        }
        return closest;
    }

    public double[] findTempsLessThen(double tempValue) {
        check(tempSeries);
        List<Double> tempsLess = new ArrayList<>();
        for (double t: tempSeries) {
            if (t < tempValue) {
                tempsLess.add(t);
            }
        }
        return arrayToDouble(tempsLess);
    }

    public double[] findTempsGreaterThen(double tempValue) {
        check(tempSeries);
        List<Double> tempsGreater = new ArrayList<>();
        for (double t: tempSeries) {
            if (t > tempValue) {
                tempsGreater.add(t);
            }
        }
        return arrayToDouble(tempsGreater);
    }

    public double[] arrayToDouble(List<Double> input) {
        double[] result = new double[input.size()];
        for (int i = 0; i < input.size(); i++) {
            result[i] = input.get(i);
        }
        return result;
    }

    public TempSummaryStatistics summaryStatistics() {
        check(tempSeries);
        return new TempSummaryStatistics(average(), deviation(), min(), max());
    }

    public int numOfTemps() {
        int i = 0, num = 0;
        while ((i < tempSeries.length) && (tempSeries[i] != 0.0)) {
            num++;
            i++;
        }
        return num;
    }

    public int addTemps(double... temps) {
        int realLength = numOfTemps();
        if (realLength <= tempSeries.length + temps.length) {
            double[] newTempSeries = new double[2 * tempSeries.length];
            System.arraycopy(tempSeries, 0, newTempSeries,
                    0, tempSeries.length);
            System.arraycopy(temps, 0, newTempSeries,
                    tempSeries.length, temps.length);
            this.tempSeries = newTempSeries;
        }
        else {
            System.arraycopy(temps, 0, tempSeries, realLength, temps.length);
        }
        return realLength + temps.length;
        //return numOfTemps();
    }
}
