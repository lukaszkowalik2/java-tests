package zadanie09;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

class FastParallelCalculationsTest {

    private final ParallelCalculations calculations = new FastParallelCalculations();

    private static class SumFunction implements Function {
        @Override
        public double get(int row, int col) {
            return row + col;
        }
    }

    private static class MulFunction implements Function {
        @Override
        public double get(int row, int col) {
            return (double) row * col;
        }
    }

    private static class SlowFunction implements Function {
        @Override
        public double get(int row, int col) {
            try {
                Thread.sleep((long) (Math.random() * 10));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return row + col;
        }
    }

    @Test
    void testSumFunctionSingleThread() {
        int size = 3;
        int threads = 1;

        List<List<Double>> result = calculations.map(new SumFunction(), size, threads);

        double[][] expected = {
                {0.0, 1.0, 2.0},
                {1.0, 2.0, 3.0},
                {2.0, 3.0, 4.0}
        };

        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                assertEquals(expected[row][col], result.get(row).get(col),
                        "Incorrect value for row=" + row + ", col=" + col);
            }
        }
    }

    @Test
    void testMulFunctionTwoThreads() {
        int size = 3;
        int threads = 2;

        List<List<Double>> result = calculations.map(new MulFunction(), size, threads);

        double[][] expected = new double[size][size];
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                expected[row][col] = row * col;
            }
        }

        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                assertEquals(expected[row][col], result.get(row).get(col),
                        "Incorrect value for row=" + row + ", col=" + col);
            }
        }
    }

    @Test
    void testSlowFunctionMultiThread() {
        int size = 10;
        int threads = 4;

        long startTime = System.currentTimeMillis();

        List<List<Double>> result = calculations.map(new SlowFunction(), size, threads);

        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        System.out.println("Calculation time (SlowFunction) = " + duration + "ms");

        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                double expected = row + col;
                double actual = result.get(row).get(col);
                assertEquals(expected, actual,
                        "Incorrect value for row=" + row + ", col=" + col);
            }
        }
    }

    @Test
    void testZeroSize() {
        int size = 0;
        int threads = 1;

        List<List<Double>> result = calculations.map(new SumFunction(), size, threads);

        // Oczekujemy pustej listy
        assertTrue(result.isEmpty(), "Result for size=0 should be an empty list.");
    }

    @Test
    void testMoreThreadsThanSize() {
        int size = 3;
        int threads = 10;

        List<List<Double>> result = calculations.map(new MulFunction(), size, threads);

        double[][] expected = new double[size][size];
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                expected[row][col] = row * col;
            }
        }

        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                assertEquals(expected[row][col], result.get(row).get(col),
                        "Incorrect value for row=" + row + ", col=" + col);
            }
        }
    }

    @Test
    void testNonTrivialFunction() {
        int size = 5;
        int threads = 2;

        Function nonTrivialFunction = new Function() {
            @Override
            public double get(int row, int col) {
                return Math.pow(row, 2) + Math.sqrt(col);
            }
        };

        List<List<Double>> result = calculations.map(nonTrivialFunction, size, threads);

        double[][] expected = new double[size][size];
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                expected[row][col] = Math.pow(row, 2) + Math.sqrt(col);
            }
        }

        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                assertEquals(expected[row][col], result.get(row).get(col),
                        "Incorrect value for row=" + row + ", col=" + col);
            }
        }
    }

    @Test
    void testLargeSize() {
        int size = 100;
        int threads = 8;

        List<List<Double>> result = calculations.map(new SumFunction(), size, threads);

        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                double expected = row + col;
                assertEquals(expected, result.get(row).get(col),
                        "Incorrect value for row=" + row + ", col=" + col);
            }
        }
    }

    @Test
    void testOddThreads() {
        int size = 7;
        int threads = 3;

        List<List<Double>> result = calculations.map(new MulFunction(), size, threads);

        double[][] expected = new double[size][size];
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                expected[row][col] = row * col;
            }
        }

        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                assertEquals(expected[row][col], result.get(row).get(col),
                        "Incorrect value for row=" + row + ", col=" + col);
            }
        }
    }

}
