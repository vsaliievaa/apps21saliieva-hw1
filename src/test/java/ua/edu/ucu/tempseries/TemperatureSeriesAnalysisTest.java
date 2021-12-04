package ua.edu.ucu.tempseries;

import static org.junit.Assert.*;
import org.junit.Test;

import java.util.Arrays;
import java.util.InputMismatchException;

public class TemperatureSeriesAnalysisTest {

    @Test(expected = InputMismatchException.class)
    public void testTemperatureSeriesAnalysisMinPossibleTemps() {
        double [] temperatureSeries = {90.3, -100.15, 150.0, -274.4};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testTemperatureSeriesWithDefaultConstructor() {
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis();

        seriesAnalysis.average();
    }

    @Test(expected = IllegalArgumentException.class)
    public void TestCheck() {
        double[] emptyArray = new double[]{};

        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(emptyArray);
        seriesAnalysis.check(emptyArray);
    }

    @Test
    public void testAverageWithOneElementArray() {
        // setup input data and expected result
        double[] temperatureSeries = {-1.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = -1.0;

        // call tested method
        double actualResult = seriesAnalysis.average();

        // compare expected result with actual result
        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAverageWithEmptyArray() {
        double[] temperatureSeries = {};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);

        // expect exception here
        seriesAnalysis.average();
    }

    @Test
    public void testAverage() {
        double[] temperatureSeries = {3.0, -5.0, 1.0, 5.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 1.0;

        double actualResult = seriesAnalysis.average();
        
        assertEquals(expResult, actualResult, 0.00001);        
    }

    @Test
    public void testFindClosestToZero() {
        // setup input data and expected result
        double[] temperatureSeries = {-0.2, 0.2, -0.2};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 0.2;

        // call tested method
        double actualResult = seriesAnalysis.findTempClosestToZero();

        // compare expected result with actual result
        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testDeviation() {
        double[] temperatureSeries = {5.0, 35.0, 46.0, 3.0, 11.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 17.29;

        double actualResult = seriesAnalysis.deviation();

        assertEquals(expResult, actualResult, 0.01);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeviationWithEmptyArgument() {
        double[] temperatureSeries = {};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);

        seriesAnalysis.deviation();
    }

    @Test
    public void testMin() {
        double[] temperatureSeries = {5.0, 35.0, 46.0, 3.0, 11.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 17.29;

        double actualResult = seriesAnalysis.deviation();

        assertEquals(expResult, actualResult, 0.01);
    }

    @Test
    public void testMax() {
        double[] temperatureSeries = {5.0, 35.0, 46.0, 3.0, 11.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 17.29;

        double actualResult = seriesAnalysis.deviation();

        assertEquals(expResult, actualResult, 0.01);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMaxWithEmptyArgument() {
        double[] temperatureSeries = {};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);

        seriesAnalysis.max();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMinWithEmptyArgument() {
        double[] temperatureSeries = {};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);

        seriesAnalysis.min();
    }

    @Test
    public void testMaxWithOneElement() {
        double[] temperatureSeries = {-3.3};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expectedValue = -3.3;

        double actualValue = seriesAnalysis.max();

        assertEquals(expectedValue, actualValue, 0);
    }

    @Test
    public void testMinWithOneElement() {
        double[] temperatureSeries = {19.8};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expectedValue = 19.8;

        double actualValue = seriesAnalysis.min();

        assertEquals(expectedValue, actualValue, 0);
    }

    @Test
    public void testFindTempClosestToValue() {
        double[] temperatureSeries = {13.76, -28.98, 16.34, -36.6};
        double value = 13.75;
        double expectedResult = 13.76;
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);

        double actualResult = seriesAnalysis.findTempClosestToValue(value);

        assertEquals(expectedResult, actualResult, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindClosestToZeroWithEmptyArgument() {
        double[] temperatureSeries = {};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);

        seriesAnalysis.findTempClosestToZero();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindClosestToValueWithEmptyArgument() {
        double[] temperatureSeries = {};
        double value = 17.55;
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);

        seriesAnalysis.findTempClosestToValue(value);

    }

    @Test
    public void testFindTempsLessThen() {
        double[] temperatureSeries = {161.1, 143.2, 226.3, 235.4, 244.5, 240.6, 269.7, 154.8, 194.8, 242.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double lessValue = 184.13;
        double[] expectedResult = {161.1, 143.2, 154.8};

        double[] actualResult = seriesAnalysis.findTempsLessThen(lessValue);

        assertEquals(Arrays.toString(expectedResult), Arrays.toString(actualResult));
    }

    @Test
    public void testFindTempsGreaterThen() {
        double[] temperatureSeries = {201.1, 216.2, 125.3, 183.4, 269.5, 131.6, 229.7, 228.8, 136.8, 215.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double greaterValue = 201.3;
        double[] expectedResult = {216.2, 269.5, 229.7, 228.8, 215.0};

        double[] actualResult = seriesAnalysis.findTempsGreaterThen(greaterValue);

        assertEquals(Arrays.toString(expectedResult), Arrays.toString(actualResult));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindTempsLessThenWithEmptyArgument() {
        double[] temperatureSeries = {};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double lessValue = 0.0;

        seriesAnalysis.findTempsLessThen(lessValue);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindTempsGreaterThenWithEmptyArgument() {
        double[] temperatureSeries = {};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double greaterValue = 0.0;

        seriesAnalysis.findTempsGreaterThen(greaterValue);
    }

    @Test
    public void testFindTempsLessThenWithNoAppropriateTemps() {
        double[] temperatureSeries = {161.1, 143.2, 235.4, 154.8, 194.8};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double lessValue = 0.0;
        double[] expectedResult = {};

        double[] actualResult = seriesAnalysis.findTempsLessThen(lessValue);

        assertEquals(Arrays.toString(expectedResult), Arrays.toString(actualResult));
    }

    @Test
    public void testFindTempsGreaterThenWithNoAppropriateTemps() {
        double[] temperatureSeries = {161.1, 143.2, 235.4, 154.8, 194.8};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double greaterValue = 273.5;
        double[] expectedResult = {};

        double[] actualResult = seriesAnalysis.findTempsGreaterThen(greaterValue);

        assertEquals(Arrays.toString(expectedResult), Arrays.toString(actualResult));
    }

    @Test
    public void TestNumOfTemps() {
        double[] temperatureSeries = {10.0, 20.0, 30.0, 40.0, 50.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        int expNumOfTemps = 5;

        int actualNumOfTemps = seriesAnalysis.numOfTemps();

        assertEquals(expNumOfTemps, actualNumOfTemps);
    }

    @Test
    public void TestNumOfTempsWithEmptyArray() {
        double[] temperatureSeries = {};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        int expNumOfTemps = 0;

        int actualNumOfTemps = seriesAnalysis.numOfTemps();

        assertEquals(expNumOfTemps, actualNumOfTemps);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSummaryStatisticsWithEmptySeries() {
        double[] temperatureSeries = new double[]{};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);

        seriesAnalysis.summaryStatistics();
    }

    @Test
    public void testSummaryStatistics() {
        double[] temperatureSeries = {12.2, 13.3, 14.4};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        TempSummaryStatistics stats = seriesAnalysis.summaryStatistics();

        assertNotNull(stats);
    }

    @Test
    public void testAddTemps() {
        double[] temperatureSeries = {34.0, -75.4, 80.0, 12.6};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double[] temps = {12.1, 13.1};
        int numOfAddedTemps = seriesAnalysis.addTemps(temps);

        assertEquals(6, numOfAddedTemps);
    }

    @Test
    public void testAddTempsWithAllocationOfNewSeries() {
        double[] temperatureSeries = {81.6, -51.0, 25.5, 62.3};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double[] temps = {12.3, 45.6};
        seriesAnalysis.addTemps(temps);

        double[] temp = {34.5};

        int numOfAddedTemps = seriesAnalysis.addTemps(temp);

        assertEquals(7, numOfAddedTemps);
    }
}
