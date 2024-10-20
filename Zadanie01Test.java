package zadanie01;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Zadanie01Test {

    @Test
    void testInitialError() {
        Zadanie01 zadanie = new Zadanie01();
        assertEquals(-2_000_000_000, zadanie.wynik());
    }

    @Test
    void testAddition() {
        Zadanie01 zadanie = new Zadanie01();
        int result = -15 + 15;
        int[] bits = {
                0, 1, 0, 1,     // 5
                1, 1, 1, 1, 1,  // -15
                0, 0,           // "+"
                0, 1, 1, 1, 1   // 15
        };

        for (int bit : bits) {
            zadanie.input(bit);
        }
        assertEquals(result, zadanie.wynik());
    }

    @Test
    void testSubtraction() {
        Zadanie01 zadanie = new Zadanie01();
        int result = -15 - 15;
        int[] bits = {
                0, 1, 0, 1,     // 5
                1, 1, 1, 1, 1,  // -15
                0, 1,           // "-"
                0, 1, 1, 1, 1   // 15
        };

        for (int bit : bits) {
            zadanie.input(bit);
        }
        assertEquals(result, zadanie.wynik());
    }

    @Test
    void testMultiplication() {
        Zadanie01 zadanie = new Zadanie01();
        int result = -15 * 15;
        int[] bits = {
                0, 1, 0, 1,     // 5
                1, 1, 1, 1, 1,  // -15
                1, 0,           // "*"
                0, 1, 1, 1, 1   // 15
        };

        for (int bit : bits) {
            zadanie.input(bit);
        }
        assertEquals(result, zadanie.wynik());
    }

    @Test
    void testDivision() {
        Zadanie01 zadanie = new Zadanie01();
        int result = -15 / 15;
        int[] bits = {
                0, 1, 0, 1,     // 5
                1, 1, 1, 1, 1,  // -15
                1, 1,           // "/"
                0, 1, 1, 1, 1   // 15
        };

        for (int bit : bits) {
            zadanie.input(bit);
        }
        assertEquals(result, zadanie.wynik());
    }

    @Test
    void testErrorForDivisionByZero() {
        Zadanie01 zadanie = new Zadanie01();
        int[] bits = {
                0, 1, 0, 1,     // 5
                1, 1, 1, 1, 1,  // -15
                1, 1,           // "/"
                0, 0, 0, 0, 0   // 0
        };

        for (int bit : bits) {
            zadanie.input(bit);
        }

        assertEquals(-2_000_000_000, zadanie.wynik());
    }

    @Test
    void testResultCanOnlyBeFetchedOnce() {
        Zadanie01 zadanie = new Zadanie01();
        int result = -15 + 15;
        int[] bits = {
                0, 1, 0, 1,     // 5
                1, 1, 1, 1, 1,  // -15
                0, 0,           // "+"
                0, 1, 1, 1, 1   // 15
        };

        for (int bit : bits) {
            zadanie.input(bit);
        }

        assertEquals(result, zadanie.wynik());
        assertEquals(-2_000_000_000, zadanie.wynik());
    }

    @Test
    void testResultOverwrittenByNewInput() {
        Zadanie01 zadanie = new Zadanie01();

        int[] firstBits = {
                1, 1, 1, 1,     // 15
                1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,  // -16383
                0, 0,           // "+"
                1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1   // -16383
        };

        for (int bit : firstBits) {
            zadanie.input(bit);
        }

        assertEquals(-16383 + -16383, zadanie.wynik());

        int[] secondBits = {
                1, 1, 1, 1,     // 15
                0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,  // 16383
                0, 1,           // "-"
                1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0   // -16382
        };

        for (int bit : secondBits) {
            zadanie.input(bit);
        }

        assertEquals(16383 + 16382, zadanie.wynik());
    }

    @Test
    void testHandlingSmallNumbers() {
        Zadanie01 zadanie = new Zadanie01();
        int[] bits = {
                0, 0, 1, 0,     // 2
                0, 1,           // 1
                0, 1,           // "-"
                1, 1            // -1
        };

        for (int bit : bits) {
            zadanie.input(bit);
        }

        assertEquals(1 - (-1), zadanie.wynik());
    }


    @Test
    void testHandlingNegativeNumbers() {
        Zadanie01 zadanie = new Zadanie01();
        int[] bits = {
                0, 0, 1, 0,  // 2
                1, 0,        // -0
                1, 0,        // "*"
                1, 1         // -1
        };

        for (int bit : bits) {
            zadanie.input(bit);
        }

        assertEquals(0, zadanie.wynik());
    }

    @Test
    void testNotEnoughBits() {
        Zadanie01 zadanie = new Zadanie01();
        int[] partialSequence = {
                0, 1, 0, 1,  // 5
                1, 1, 1, 1, 1, // -15
                0, 0
        };

        for (int bit : partialSequence) {
            zadanie.input(bit);
        }
        assertEquals(-2_000_000_000, zadanie.wynik());
    }

    @Test
    void testTooManyBits() {
        Zadanie01 zadanie = new Zadanie01();
        int[] bits = {
                0, 1, 0, 1,     // 5
                1, 1, 1, 1, 1,  // -15
                0, 0,           // "+"
                0, 1, 1, 1, 0,  // 14
                1, 1, 1
        };

        for (int bit : bits) {
            zadanie.input(bit);
        }

        assertEquals(-1, zadanie.wynik());
    }

    @Test
    void testIncompleteSecondSequence() {
        Zadanie01 zadanie = new Zadanie01();
        int[] firstSequence = {
                0, 1, 0, 1,     // 5
                1, 1, 1, 1, 1,  // -15
                0, 0,           // "+"
                0, 1, 1, 1, 0   // 14
        };

        int[] incompleteSecondSequence = {
                0, 0, 1, 0,     // 2
                0, 0
        };

        for (int bit : firstSequence) {
            zadanie.input(bit);
        }

        assertEquals(-1, zadanie.wynik());

        for (int bit : incompleteSecondSequence) {
            zadanie.input(bit);
        }

        assertEquals(-2_000_000_000, zadanie.wynik());
    }

    @Test
    void testAdditionOfPositiveNumbers() {
        Zadanie01 zadanie = new Zadanie01();
        int[] input = {
                0, 0, 1, 1,  // 3
                0, 0, 1,     // 1
                1, 0,        // "*"
                1, 1, 1      // -3
        };

        for (int bit : input) {
            zadanie.input(bit);
        }

        assertEquals(-3, zadanie.wynik());
    }

    @Test
    void testMultiplicationOfNegativeNumbers() {
        Zadanie01 zadanie = new Zadanie01();
        int[] input = {
                0, 0, 1, 1,  // 3
                1, 1, 1,     // -3
                1, 0,        // "*"
                0, 0, 1      // 1
        };

        for (int bit : input) {
            zadanie.input(bit);
        }

        assertEquals(-3, zadanie.wynik());
    }


    @Test
    void testDivisionOfNegativeAndPositiveNumbers() {
        Zadanie01 zadanie = new Zadanie01();
        int[] input = {
                0, 0, 1, 1,  // 3
                1, 1, 1,     // -3
                1, 1,        // "/"
                1, 0, 1      // -1
        };
        for (int bit : input) {
            zadanie.input(bit);
        }
        assertEquals(3, zadanie.wynik());
    }

    @Test
    void testSubtractionOfNegativeAndPositiveNumbers() {
        Zadanie01 zadanie = new Zadanie01();
        int[] input = {
                0, 0, 1, 1,  // 3
                1, 1, 1,     // -3
                1, 0,        // "*"
                1, 0, 1      // -1
        };
        for (int bit : input) {
            zadanie.input(bit);
        }
        assertEquals(3, zadanie.wynik());
    }

    @Test
    void testInvalidLengthForA() {
        Zadanie01 zadanie = new Zadanie01();
        int[] input = {
                0, 0, 1  // Invalid input for part "a"
        };
        for (int bit : input) {
            zadanie.input(bit);
        }
        assertEquals(-2_000_000_000, zadanie.wynik());
    }

    @Test
    void testInvalidLengthForB() {
        Zadanie01 zadanie = new Zadanie01();
        int[] input = {
                1, 1, 1, 1,  // Invalid input for part "b"
                0, 0, 0
        };
        for (int bit : input) {
            zadanie.input(bit);
        }
        assertEquals(-2_000_000_000, zadanie.wynik());
    }

    @Test
    void testTooLongInput() {
        Zadanie01 zadanie = new Zadanie01();
        int[] input = {
                0, 0, 1, 1,  // 3
                1, 1, 1,     // -3
                0, 0,        // "+"
                1, 1, 1,     // -3
                0, 0, 1, 0,  // 2
                1, 0,        // -0
                0, 1,        // "-"
                0            // 1
        };
        for (int bit : input) {
            zadanie.input(bit);
        }
        assertEquals(-6, zadanie.wynik());
        zadanie.input(1);
        assertEquals(-1, zadanie.wynik());
    }

    @Test
    void testIncompleteData() {
        Zadanie01 zadanie = new Zadanie01();
        int[] input = {
                0, 0, 1, 1,  // Incomplete input
                0, 1, 1, 1,
                0, 1
        };
        for (int bit : input) {
            zadanie.input(bit);
        }
        assertEquals(-2_000_000_000, zadanie.wynik());
    }


    @Test
    void testNegativeNumberInFirstByte() {
        Zadanie01 zadanie = new Zadanie01();
        // Negative first number (-10)
        int[] input = {
                1, 0, 1, 0,  // -10 (długość części "a" nieprawidłowa)
                0, 1, 0, 0,  // reszta danych
                0, 1
        };
        for (int bit : input) {
            zadanie.input(bit);
        }
        assertEquals(-2_000_000_000, zadanie.wynik());
    }

    @Test
    void testMultipleRoundsWithNewData() {
        Zadanie01 zadanie = new Zadanie01();
        int[] input = {
                0, 0, 1, 1,  // 3
                0, 1, 1,     // 3
                0, 0,        // "+"
                0, 1, 1,     // 3

                0, 0, 1, 1,  // 3
                1, 1, 1,     // -3
                0, 0,        // "+"
                0, 1, 1      // 3
        };
        for (int bit : input) {
            zadanie.input(bit);
        }
        assertEquals(0, zadanie.wynik());
    }

    @Test
    void testAdditionWithLength1Operands() {
        Zadanie01 zadanie = new Zadanie01();
        int[] input = {
                0, 0, 0, 1,  // 1
                0,           // 0
                0, 0,        // "+"
                1            // 0
        };

        for (int bit : input) {
            zadanie.input(bit);
        }

        assertEquals(-2_000_000_000, zadanie.wynik());  // Oczekiwany błąd z powodu zbyt krótkich danych
    }

    @Test
    void testTwoIndependentInstances() {
        Zadanie01 instance1 = new Zadanie01();
        Zadanie01 instance2 = new Zadanie01();

        int[] input1 = {
                0, 1, 0, 1,  // 5
                1, 1, 1, 1, 1,  // -15
                0, 0,        // "+"
                0, 1, 1, 1, 0  // 14
        };
        for (int bit : input1) {
            instance1.input(bit);
        }

        int[] input2 = {
                0, 0, 1, 0,  // 2
                0, 0,        // +
                0, 0, 0,    // 1
        };
        for (int bit : input2) {
            instance2.input(bit);
        }

        assertEquals(-1, instance1.wynik());
        assertEquals(-2_000_000_000, instance2.wynik());
    }

    @Test
    void testResultNotReadyYet() {
        Zadanie01 zadanie = new Zadanie01();
        zadanie.input(0);
        zadanie.input(1);
        assertEquals(-2_000_000_000, zadanie.wynik());
    }

    @Test
    void testResultFetchedThenError() {
        Zadanie01 zadanie = new Zadanie01();
        int[] firstSequence = {
                0, 1, 0, 1,  // 5
                1, 1, 1, 1, 1,  // -15
                0, 0,        // "+"
                0, 1, 1, 1, 0  // 14
        };
        for (int bit : firstSequence) {
            zadanie.input(bit);
        }

        assertEquals(-1, zadanie.wynik());
        assertEquals(-2_000_000_000, zadanie.wynik());
    }

    @Test
    void testResultNotFetchedYetCanBeFetched() {
        Zadanie01 zadanie = new Zadanie01();
        int[] firstSequence = {
                0, 1, 0, 1,  // 5
                1, 1, 1, 1, 1,  // -15
                0, 0,        // "+"
                0, 1, 1, 1, 0  // 14
        };
        for (int bit : firstSequence) {
            zadanie.input(bit);
        }

        assertEquals(-1, zadanie.wynik());
    }

    @Test
    void testNewDataAfterResultFetchedError() {
        Zadanie01 zadanie = new Zadanie01();
        int[] firstSequence = {
                0, 1, 0, 1,  // 5
                1, 1, 1, 1, 1,  // -15
                0, 0,        // "+"
                0, 1, 1, 1, 0  // 14
        };
        for (int bit : firstSequence) {
            zadanie.input(bit);
        }

        assertEquals(-1, zadanie.wynik());

        zadanie.input(0);
        zadanie.input(1);

        assertEquals(-2_000_000_000, zadanie.wynik());
    }

    @Test
    void testNewDataButPreviousResultNotFetchedYet() {
        Zadanie01 zadanie = new Zadanie01();
        int[] firstSequence = {
                0, 1, 0, 1,  // 5
                1, 1, 1, 1, 1,  // -15
                0, 0,        // "+"
                0, 1, 1, 1, 0  // 14
        };
        for (int bit : firstSequence) {
            zadanie.input(bit);
        }

        zadanie.input(0);
        zadanie.input(1);

        assertEquals(-1, zadanie.wynik());
    }

    @Test
    void testNewDataOverridesPreviousResult() {
        Zadanie01 zadanie = new Zadanie01();
        int[] firstSequence = {
                0, 1, 0, 1,  // 5
                1, 1, 1, 1, 1,  // -15
                0, 0,        // "+"
                0, 1, 1, 1, 0  // 14
        };
        for (int bit : firstSequence) {
            zadanie.input(bit);
        }

        zadanie.input(0);
        zadanie.input(1);
        zadanie.input(1);
        zadanie.input(0);

        int[] newSequence = {
                0, 0, 1, 0,  // 2
                0, 0,        // "+"
                0, 0, 0, 1   // 1
        };
        for (int bit : newSequence) {
            zadanie.input(bit);
        }

        assertEquals(-1, zadanie.wynik());
        assertEquals(-2_000_000_000, zadanie.wynik());
    }

    @Test
    void testScenario1_Example() {
        Zadanie01 zadanie = new Zadanie01();

        int[] firstSequence = {
                0, 1, 0, 1,  // 5
                1, 1, 1, 1, 1,  // -15
                0, 0,        // "+"
                0, 1, 1, 1,   // 14
        };

        for (int bit : firstSequence) {
            zadanie.input(bit);
        }

        assertEquals(-2_000_000_000, zadanie.wynik());
        zadanie.input(0);
        assertEquals(-1, zadanie.wynik());

        int[] secondSequence = {
                0, 1, 0, 0,  // 2
                0, 0, 0, 0,  // 0
                0, 0,        // "+"
                0, 0, 0, 1   // 1
        };

        for (int bit : secondSequence) {
            zadanie.input(bit);
        }

        assertEquals(1, zadanie.wynik());
    }

    @Test
    void testScenario2_Example() {
        Zadanie01 zadanie = new Zadanie01();

        int[] firstSequence = {
                0, 1, 0, 1,  // 5
                1, 1, 1, 1, 1,  // -15
                0, 0,        // "+"
                0, 1, 1, 1,  // 14
        };
        for (int bit : firstSequence) {
            zadanie.input(bit);
        }

        assertEquals(-2_000_000_000, zadanie.wynik());
        zadanie.input(0);
        assertEquals(-1, zadanie.wynik());

        assertEquals(-2_000_000_000, zadanie.wynik());

        int[] secondSequence = {
                0, 0, 1, 0,  // 2
                0, 0,        // 0
                0, 0,        // "+"
                0, 1,        // 1
        };
        for (int bit : secondSequence) {
            zadanie.input(bit);
        }

        assertEquals(1, zadanie.wynik());
    }

    @Test
    void testScenario3_Example() {
        Zadanie01 zadanie = new Zadanie01();

        int[] firstSequence = {
                0, 1, 0, 1,  // 5
                1, 1, 1, 1, 1,  // -15
                0, 0,        // "+"
                0, 1, 1, 1, 0  // 14
        };
        for (int bit : firstSequence) {
            zadanie.input(bit);
        }

        assertEquals(-1, zadanie.wynik());

        int[] secondSequencePartial = {
                0, 0, 1, 0,  // 2
                0, 0         // 0
        };
        for (int bit : secondSequencePartial) {
            zadanie.input(bit);
        }

        assertEquals(-2_000_000_000, zadanie.wynik());

        zadanie.input(0);
        zadanie.input(0);  // "+"
        zadanie.input(0);
        zadanie.input(1);  // 1

        assertEquals(1, zadanie.wynik());
    }

    @Test
    void testAdditionOfTwoPositiveNumbers() {
        Zadanie01 zadanie = new Zadanie01();
        int[] input = {
                0, 0, 1, 1,  // 3
                0, 1, 0,     // 2
                0, 0,        // "+"
                0, 1, 1      // 3
        };
        for (int bit : input) {
            zadanie.input(bit);
        }
        assertEquals(5, zadanie.wynik());
    }

    @Test
    void testAdditionOfPositiveAndNegativeNumbers() {
        Zadanie01 zadanie = new Zadanie01();
        int[] input = {
                0, 0, 1, 1,  // 3
                1, 1, 1,     // -3
                0, 0,        // "+"
                0, 1, 0      // 2
        };
        for (int bit : input) {
            zadanie.input(bit);
        }
        assertEquals(-1, zadanie.wynik());
    }

    @Test
    void testAdditionOfTwoNegativeNumbers() {
        Zadanie01 zadanie = new Zadanie01();
        int[] input = {
                0, 0, 1, 1,  // 3
                1, 1, 1,     // -3
                0, 0,        // "+"
                1, 1, 1      // -3
        };
        for (int bit : input) {
            zadanie.input(bit);
        }
        assertEquals(-6, zadanie.wynik());
    }

    @Test
    void testAdditionOfZeroAndPositiveNumber() {
        Zadanie01 zadanie = new Zadanie01();
        int[] input = {
                0, 0, 1, 0,  // 1
                0, 0,        // 0
                0, 0,        // "+"
                1, 1         // -1
        };
        for (int bit : input) {
            zadanie.input(bit);
        }
        assertEquals(-1, zadanie.wynik());
    }

    @Test
    void testAdditionOfPositiveNumberAndZero() {
        Zadanie01 zadanie = new Zadanie01();
        int[] input = {
                0, 0, 1, 1,  // 1
                0, 1, 0,     // 2
                0, 0,        // "+"
                0, 0, 0            // 0
        };
        for (int bit : input) {
            zadanie.input(bit);
        }
        assertEquals(2, zadanie.wynik());
    }

    @Test
    void testAdditionOfNegativeNumberAndZero() {
        Zadanie01 zadanie = new Zadanie01();
        int[] input = {
                0, 0, 1, 1,  // 1
                1, 1, 1,     // -3
                0, 0,        // "+"
                0, 0, 0            // 0
        };
        for (int bit : input) {
            zadanie.input(bit);
        }
        assertEquals(-3, zadanie.wynik());
    }

    @Test
    void testAdditionOfPositiveNumberAndNegativeNumber() {
        Zadanie01 zadanie = new Zadanie01();
        int[] input = {
                0, 0, 1, 1,  // 3
                0, 1, 0,     // 2
                0, 0,        // "+"
                1, 1, 1      // -3
        };
        for (int bit : input) {
            zadanie.input(bit);
        }
        assertEquals(-1, zadanie.wynik());
    }

    @Test
    void testAdditionOfNegativeNumberAndPositiveNumber() {
        Zadanie01 zadanie = new Zadanie01();
        int[] input = {
                0, 0, 1, 1,  // 3
                1, 1, 1,     // -3
                0, 0,        // "+"
                0, 1, 0      // 2
        };
        for (int bit : input) {
            zadanie.input(bit);
        }
        assertEquals(-1, zadanie.wynik());
    }

    @Test
    void testAdditionOfTwoZeros() {
        Zadanie01 zadanie = new Zadanie01();
        int[] input = {
                0, 0, 1, 0,  // 2
                0, 0,        // 0
                0, 0,        // "+"
                0, 0         // 0
        };
        for (int bit : input) {
            zadanie.input(bit);
        }
        assertEquals(0, zadanie.wynik());
    }

    @Test
    void testAdditionOfMaxPositiveAndMaxPositive() {
        Zadanie01 zadanie = new Zadanie01();
        int[] input = {
                1, 0, 0, 1,  // 9
                0, 0, 1, 1, 1, 1, 1, 1, 1,  // 127
                0, 0,        // "+"
                0, 0, 1, 1, 1, 1, 1, 1, 1   // 127
        };
        for (int bit : input) {
            zadanie.input(bit);
        }
        assertEquals(254, zadanie.wynik());
    }


    @Test
    void testAdditionOfMaxPositiveAndMaxNegative() {
        Zadanie01 zadanie = new Zadanie01();
        int[] input = {
                1, 0, 0, 1,  // 15
                0, 0, 1, 1, 1, 1, 1, 1, 1,  // 127
                0, 0,        // "+"
                1, 0, 1, 1, 1, 1, 1, 1, 1   // -127
        };
        for (int bit : input) {
            zadanie.input(bit);
        }
        assertEquals(0, zadanie.wynik());
    }

    @Test
    void testAdditionOfMinNegativeAndMinNegative() {
        Zadanie01 zadanie = new Zadanie01();
        int[] input = {
                1, 0, 0, 1,  // 9
                1, 0, 1, 1, 1, 1, 1, 1, 1,  // -127
                0, 0,        // "+"
                1, 0, 1, 1, 1, 1, 1, 1, 1   // -127
        };
        for (int bit : input) {
            zadanie.input(bit);
        }
        assertEquals(-254, zadanie.wynik());
    }

    @Test
    void testAdditionOfMinNegativeAndMaxPositive() {
        Zadanie01 zadanie = new Zadanie01();
        int[] input = {
                1, 0, 0, 1,  // 15
                1, 0, 1, 1, 1, 1, 1, 1, 1,  // -127
                0, 0,        // "+"
                0, 0, 1, 1, 1, 1, 1, 1, 1   // 127
        };
        for (int bit : input) {
            zadanie.input(bit);
        }
        assertEquals(0, zadanie.wynik());
    }

    @Test
    void testAdditionOfSmallPositiveAndSmallNegative() {
        Zadanie01 zadanie = new Zadanie01();
        int[] input = {
                0, 0, 1, 1,  // 1
                0, 0, 1,        // 1
                0, 0,        // "+"
                1, 0, 1         // -1
        };
        for (int bit : input) {
            zadanie.input(bit);
        }
        assertEquals(0, zadanie.wynik());
    }

    @Test
    void testSubtractionOfTwoPositiveNumbers() {
        Zadanie01 zadanie = new Zadanie01();
        int[] input = {
                0, 0, 1, 1,  // 3
                0, 1, 0,     // 2
                0, 1,        // "-"
                0, 1, 1      // 3
        };
        for (int bit : input) {
            zadanie.input(bit);
        }
        assertEquals(-1, zadanie.wynik());
    }

    @Test
    void testSubtractionOfPositiveAndNegativeNumbers() {
        Zadanie01 zadanie = new Zadanie01();
        int[] input = {
                0, 0, 1, 1,  // 3
                1, 1, 1,     // -3
                0, 1,        // "-"
                0, 1, 0      // 2
        };
        for (int bit : input) {
            zadanie.input(bit);
        }
        assertEquals(-5, zadanie.wynik());
    }

    @Test
    void testSubtractionOfTwoNegativeNumbers() {
        Zadanie01 zadanie = new Zadanie01();
        int[] input = {
                0, 0, 1, 1,  // 3
                1, 1, 1,     // -3
                0, 1,        // "-"
                1, 1, 1      // -3
        };
        for (int bit : input) {
            zadanie.input(bit);
        }
        assertEquals(0, zadanie.wynik());
    }

    @Test
    void testSubtractionOfZeroAndPositiveNumber() {
        Zadanie01 zadanie = new Zadanie01();
        int[] input = {
                0, 0, 0, 1,  // 1
                0,           // 0
                0, 1,        // "-"
                0, 1, 0      // 2
        };
        for (int bit : input) {
            zadanie.input(bit);
        }
        assertEquals(-2_000_000_000, zadanie.wynik());
    }

    @Test
    void testSubtractionOfZeroAndNegativeNumber() {
        Zadanie01 zadanie = new Zadanie01();
        int[] input = {
                0, 0, 0, 1,  // 1
                0,           // 0
                0, 1,        // "-"
                1, 1, 1      // -3
        };
        for (int bit : input) {
            zadanie.input(bit);
        }
        assertEquals(-2_000_000_000, zadanie.wynik());
    }

    @Test
    void testSubtractionOfPositiveNumberAndZero() {
        Zadanie01 zadanie = new Zadanie01();
        int[] input = {
                0, 0, 0, 1,  // 1
                0, 1, 0,     // 2
                0, 1,        // "-"
                0            // 0
        };
        for (int bit : input) {
            zadanie.input(bit);
        }
        assertEquals(-2_000_000_000, zadanie.wynik());
    }

    @Test
    void testSubtractionOfNegativeNumberAndZero() {
        Zadanie01 zadanie = new Zadanie01();
        int[] input = {
                0, 0, 0, 1,  // 1
                1, 1, 1,     // -3
                0, 1,        // "-"
                0            // 0
        };
        for (int bit : input) {
            zadanie.input(bit);
        }
        assertEquals(-2_000_000_000, zadanie.wynik());
    }

    @Test
    void testSubtractionOfPositiveNumberAndNegativeNumber() {
        Zadanie01 zadanie = new Zadanie01();
        int[] input = {
                0, 0, 1, 1,  // 3
                0, 1, 0,     // 2
                0, 1,        // "-"
                1, 1, 1      // -3
        };
        for (int bit : input) {
            zadanie.input(bit);
        }
        assertEquals(5, zadanie.wynik());
    }

    @Test
    void testSubtractionOfNegativeNumberAndPositiveNumber() {
        Zadanie01 zadanie = new Zadanie01();
        int[] input = {
                0, 0, 1, 1,  // 3
                1, 1, 1,     // -3
                0, 1,        // "-"
                0, 1, 0      // 2
        };
        for (int bit : input) {
            zadanie.input(bit);
        }
        assertEquals(-5, zadanie.wynik());
    }

    @Test
    void testSubtractionOfTwoZeros() {
        Zadanie01 zadanie = new Zadanie01();
        int[] input = {
                0, 0, 0, 1,  // 1
                0,           // 0
                0, 1,        // "-"
                0            // 0
        };
        for (int bit : input) {
            zadanie.input(bit);
        }
        assertEquals(-2_000_000_000, zadanie.wynik());
    }

    @Test
    void testSubtractionOfMaxPositiveAndMaxPositive() {
        Zadanie01 zadanie = new Zadanie01();
        int[] input = {
                1, 0, 0, 1,  // 8
                0, 1, 1, 1, 1, 1, 1, 1, 1,  // 255
                0, 1,        // "-"
                0, 1, 1, 1, 1, 1, 1, 1, 1   // 255
        };
        for (int bit : input) {
            zadanie.input(bit);
        }
        assertEquals(0, zadanie.wynik());
    }

    @Test
    void testSubtractionOfMaxPositiveAndMaxNegative() {
        Zadanie01 zadanie = new Zadanie01();
        int[] input = {
                1, 0, 0, 1,  // 9
                0, 1, 1, 1, 1, 1, 1, 1, 1,  // 255
                0, 1,        // "-"
                1, 1, 1, 1, 1, 1, 1, 1, 1   // -255
        };
        for (int bit : input) {
            zadanie.input(bit);
        }
        assertEquals(510, zadanie.wynik());
    }

    @Test
    void testSubtractionOfMinNegativeAndMinNegative() {
        Zadanie01 zadanie = new Zadanie01();
        int[] input = {
                1, 0, 0, 1,  // 15
                1, 0, 1, 1, 1, 1, 1, 1, 1,  // -127
                0, 1,        // "-"
                1, 0, 1, 1, 1, 1, 1, 1, 1   // -127
        };
        for (int bit : input) {
            zadanie.input(bit);
        }
        assertEquals(0, zadanie.wynik());
    }

    @Test
    void testSubtractionOfMinNegativeAndMaxPositive() {
        Zadanie01 zadanie = new Zadanie01();
        int[] input = {
                1, 0, 0, 1,  // 15
                1, 0, 1, 1, 1, 1, 1, 1, 1,  // -127
                0, 1,        // "-"
                0, 0, 1, 1, 1, 1, 1, 1, 1   // 127
        };
        for (int bit : input) {
            zadanie.input(bit);
        }
        assertEquals(-254, zadanie.wynik());
    }

    @Test
    void testSubtractionOfSmallPositiveAndSmallNegative() {
        Zadanie01 zadanie = new Zadanie01();
        int[] input = {
                0, 0, 1, 1,  // 1
                0, 0, 1,        // 1
                0, 1,        // "-"
                1, 0, 1         // -1
        };
        for (int bit : input) {
            zadanie.input(bit);
        }
        assertEquals(2, zadanie.wynik());
    }

    @Test
    void testMultiplicationOfTwoPositiveNumbers() {
        Zadanie01 zadanie = new Zadanie01();
        int[] input = {
                0, 0, 1, 1,  // 3
                0, 1, 0,     // 2
                1, 0,        // "*"
                0, 1, 1      // 3
        };
        for (int bit : input) {
            zadanie.input(bit);
        }
        assertEquals(6, zadanie.wynik());
    }

    @Test
    void testMultiplicationOfPositiveAndNegativeNumbers() {
        Zadanie01 zadanie = new Zadanie01();
        int[] input = {
                0, 0, 1, 1,  // 3
                1, 1, 1,     // -3
                1, 0,        // "*"
                0, 1, 0      // 2
        };
        for (int bit : input) {
            zadanie.input(bit);
        }
        assertEquals(-6, zadanie.wynik());
    }

    @Test
    void testMultiplicationOfTwoNegativeNumbers() {
        Zadanie01 zadanie = new Zadanie01();
        int[] input = {
                0, 0, 1, 1,  // 3
                1, 1, 1,     // -3
                1, 0,        // "*"
                1, 1, 1      // -3
        };
        for (int bit : input) {
            zadanie.input(bit);
        }
        assertEquals(9, zadanie.wynik());
    }

    @Test
    void testMultiplicationOfZeroAndPositiveNumber() {
        Zadanie01 zadanie = new Zadanie01();
        int[] input = {
                0, 0, 1, 1,  // 1
                0, 0, 0,          // 0
                1, 0,        // "*"
                0, 1, 0      // 2
        };
        for (int bit : input) {
            zadanie.input(bit);
        }
        assertEquals(0, zadanie.wynik());
    }

    @Test
    void testMultiplicationOfZeroAndNegativeNumber() {
        Zadanie01 zadanie = new Zadanie01();
        int[] input = {
                0, 0, 1, 1,  // 1
                0, 0, 0,          // 0
                1, 0,        // "*"
                1, 1, 1      // -3
        };
        for (int bit : input) {
            zadanie.input(bit);
        }
        assertEquals(0, zadanie.wynik());
    }

    @Test
    void testMultiplicationOfPositiveNumberAndZero() {
        Zadanie01 zadanie = new Zadanie01();
        int[] input = {
                0, 0, 1, 1,  // 1
                0, 1, 0,     // 2
                1, 0,        // "*"
                0, 0, 0            // 0
        };
        for (int bit : input) {
            zadanie.input(bit);
        }
        assertEquals(0, zadanie.wynik());
    }

    @Test
    void testMultiplicationOfNegativeNumberAndZero() {
        Zadanie01 zadanie = new Zadanie01();
        int[] input = {
                0, 0, 1, 1,  // 1
                1, 1, 1,     // -3
                1, 0,        // "*"
                0, 0, 0            // 0
        };
        for (int bit : input) {
            zadanie.input(bit);
        }
        assertEquals(0, zadanie.wynik());
    }

    @Test
    void testMultiplicationOfPositiveNumberAndNegativeNumber() {
        Zadanie01 zadanie = new Zadanie01();
        int[] input = {
                0, 0, 1, 1,  // 3
                0, 1, 0,     // 2
                1, 0,        // "*"
                1, 1, 1      // -3
        };
        for (int bit : input) {
            zadanie.input(bit);
        }
        assertEquals(-6, zadanie.wynik());
    }

    @Test
    void testMultiplicationOfNegativeNumberAndPositiveNumber() {
        Zadanie01 zadanie = new Zadanie01();
        int[] input = {
                0, 0, 1, 1,  // 3
                1, 1, 1,     // -3
                1, 0,        // "*"
                0, 1, 0      // 2
        };
        for (int bit : input) {
            zadanie.input(bit);
        }
        assertEquals(-6, zadanie.wynik());
    }

    @Test
    void testMultiplicationOfTwoZeros() {
        Zadanie01 zadanie = new Zadanie01();
        int[] input = {
                0, 0, 1, 1,  // 1
                0, 0, 0,          // 0
                1, 0,        // "*"
                0, 0, 0            // 0
        };
        for (int bit : input) {
            zadanie.input(bit);
        }
        assertEquals(0, zadanie.wynik());
    }

    @Test
    void testMultiplicationOfMaxPositiveAndMaxPositive() {
        Zadanie01 zadanie = new Zadanie01();
        int[] input = {
                1, 1, 1, 1,  // 15
                0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                1, 0,        // "*"
                0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
        };
        for (int bit : input) {
            zadanie.input(bit);
        }
        assertEquals(268402689, zadanie.wynik());
    }

    @Test
    void testMultiplicationOfMaxPositiveAndMaxNegative() {
        Zadanie01 zadanie = new Zadanie01();
        int[] input = {
                1, 1, 1, 1,  // 15
                0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                1, 0,        // "*"
                1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
        };
        for (int bit : input) {
            zadanie.input(bit);
        }
        assertEquals(-268402689, zadanie.wynik());
    }

    @Test
    void testMultiplicationOfMinNegativeAndMinNegative() {
        Zadanie01 zadanie = new Zadanie01();
        int[] input = {
                1, 1, 1, 1,  // 15
                1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                1, 0,        // "*"
                1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
        };
        for (int bit : input) {
            zadanie.input(bit);
        }
        assertEquals(268402689, zadanie.wynik());
    }

    @Test
    void testMultiplicationOfMinNegativeAndMaxPositive() {
        Zadanie01 zadanie = new Zadanie01();
        int[] input = {
                1, 1, 1, 1,  // 15
                1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                1, 0,        // "*"
                0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
        };
        for (int bit : input) {
            zadanie.input(bit);
        }
        assertEquals(-268402689, zadanie.wynik());
    }

    @Test
    void testMultiplicationOfSmallPositiveAndSmallNegative() {
        Zadanie01 zadanie = new Zadanie01();
        int[] input = {
                0, 0, 1, 1,  // 1
                0, 0, 1,        // 1
                1, 0,        // "*"
                1, 0, 1        // -1
        };
        for (int bit : input) {
            zadanie.input(bit);
        }
        assertEquals(-1, zadanie.wynik());
    }

    @Test
    void testDivisionOfTwoPositiveNumbers() {
        Zadanie01 zadanie = new Zadanie01();
        int[] input = {
                0, 0, 1, 1,  // 3
                0, 1, 0,     // 2
                1, 1,        // "/"
                0, 1, 1      // 3
        };
        for (int bit : input) {
            zadanie.input(bit);
        }
        assertEquals(0, zadanie.wynik());
    }

    @Test
    void testDivisionOfPositiveAndNegativeNumbers() {
        Zadanie01 zadanie = new Zadanie01();
        int[] input = {
                0, 0, 1, 1,  // 3
                1, 1, 1,     // -3
                1, 1,        // "/"
                0, 1, 0      // 2
        };
        for (int bit : input) {
            zadanie.input(bit);
        }
        assertEquals(-1, zadanie.wynik());
    }

    @Test
    void testDivisionOfTwoNegativeNumbers() {
        Zadanie01 zadanie = new Zadanie01();
        int[] input = {
                0, 0, 1, 1,  // 3
                1, 1, 1,     // -3
                1, 1,        // "/"
                1, 1, 1      // -3
        };
        for (int bit : input) {
            zadanie.input(bit);
        }
        assertEquals(1, zadanie.wynik());
    }

    @Test
    void testDivisionOfZeroAndPositiveNumber() {
        Zadanie01 zadanie = new Zadanie01();
        int[] input = {
                0, 0, 1, 1,  // 1
                0, 0, 0,          // 0
                1, 1,        // "/"
                0, 1, 0      // 2
        };
        for (int bit : input) {
            zadanie.input(bit);
        }
        assertEquals(0, zadanie.wynik());
    }

    @Test
    void testDivisionOfZeroAndNegativeNumber() {
        Zadanie01 zadanie = new Zadanie01();
        int[] input = {
                0, 0, 1, 1,  // 1
                0, 0, 0,         // 0
                1, 1,        // "/"
                1, 1, 1      // -3
        };
        for (int bit : input) {
            zadanie.input(bit);
        }
        assertEquals(0, zadanie.wynik());
    }

    @Test
    void testDivisionOfPositiveNumberAndZero() {
        Zadanie01 zadanie = new Zadanie01();
        int[] input = {
                0, 0, 0, 1,  // 1
                0, 1, 0,     // 2
                1, 1,        // "/"
                0            // 0
        };
        for (int bit : input) {
            zadanie.input(bit);
        }
        assertEquals(-2_000_000_000, zadanie.wynik());  // Division by zero should return error
    }

    @Test
    void testDivisionOfNegativeNumberAndZero() {
        Zadanie01 zadanie = new Zadanie01();
        int[] input = {
                0, 0, 0, 1,  // 1
                1, 1, 1,     // -3
                1, 1,        // "/"
                0            // 0
        };
        for (int bit : input) {
            zadanie.input(bit);
        }
        assertEquals(-2_000_000_000, zadanie.wynik());  // Division by zero should return error
    }

    @Test
    void testDivisionOfPositiveNumberAndNegativeNumber() {
        Zadanie01 zadanie = new Zadanie01();
        int[] input = {
                0, 0, 1, 1,  // 3
                0, 1, 0,     // 2
                1, 1,        // "/"
                1, 1, 1      // -3
        };
        for (int bit : input) {
            zadanie.input(bit);
        }
        assertEquals(0, zadanie.wynik());
    }

    @Test
    void testDivisionOfNegativeNumberAndPositiveNumber() {
        Zadanie01 zadanie = new Zadanie01();
        int[] input = {
                0, 0, 1, 1,  // 3
                1, 1, 1,     // -3
                1, 1,        // "/"
                0, 1, 0      // 2
        };
        for (int bit : input) {
            zadanie.input(bit);
        }
        assertEquals(-1, zadanie.wynik());
    }

    @Test
    void testDivisionOfTwoZeros() {
        Zadanie01 zadanie = new Zadanie01();
        int[] input = {
                0, 0, 0, 1,  // 1
                0,           // 0
                1, 1,        // "/"
                0            // 0
        };
        for (int bit : input) {
            zadanie.input(bit);
        }
        assertEquals(-2_000_000_000, zadanie.wynik());  // Division by zero should return error
    }

    @Test
    void testDivisionOfMaxPositiveAndMaxPositive() {
        Zadanie01 zadanie = new Zadanie01();
        int[] input = {
                1, 1, 1, 1,  // 15
                0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                1, 1,        // "/"
                0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
        };
        for (int bit : input) {
            zadanie.input(bit);
        }
        assertEquals(1, zadanie.wynik());
    }

    @Test
    void testDivisionOfMaxPositiveAndMaxNegative() {
        Zadanie01 zadanie = new Zadanie01();
        int[] input = {
                1, 1, 1, 1,  // 15
                0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                1, 1,        // "/"
                1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
        };
        for (int bit : input) {
            zadanie.input(bit);
        }
        assertEquals(-1, zadanie.wynik());
    }

    @Test
    void testDivisionOfMinNegativeAndMinNegative() {
        Zadanie01 zadanie = new Zadanie01();
        int[] input = {
                1, 1, 1, 1,  // 15
                1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                1, 1,        // "/"
                1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
        };
        for (int bit : input) {
            zadanie.input(bit);
        }
        assertEquals(1, zadanie.wynik());
    }

    @Test
    void testDivisionOfMinNegativeAndMaxPositive() {
        Zadanie01 zadanie = new Zadanie01();
        int[] input = {
                1, 1, 1, 1,  // 15
                1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                1, 1,        // "/"
                0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
        };
        for (int bit : input) {
            zadanie.input(bit);
        }
        assertEquals(-1, zadanie.wynik());
    }

    @Test
    void testDivisionOfSmallPositiveAndSmallNegative() {
        Zadanie01 zadanie = new Zadanie01();
        int[] input = {
                0, 0, 1, 1,  // 1
                0, 0, 1,        // 1
                1, 1,        // "/"
                1, 0, 1         // -1
        };
        for (int bit : input) {
            zadanie.input(bit);
        }
        assertEquals(-1, zadanie.wynik());
    }

    @Test
    void testAllOperationsOnSingleInstance() {
        Zadanie01 zadanie = new Zadanie01();

        int[] addition = {
                0, 0, 1, 1,  // 2
                0, 1, 0,     // 2
                0, 0,        // "+"
                0, 1, 1      // 3
        };
        for (int bit : addition) {
            zadanie.input(bit);
        }
        assertEquals(5, zadanie.wynik());

        int[] subtraction = {
                0, 0, 1, 1,  // 2
                0, 1, 1,     // 3
                0, 1,        // "-"
                0, 1, 0      // 2
        };
        for (int bit : subtraction) {
            zadanie.input(bit);
        }
        assertEquals(1, zadanie.wynik());

        int[] multiplication = {
                0, 0, 1, 1,  // 2
                0, 1, 0,     // 2
                1, 0,        // "*"
                0, 1, 0      // 2
        };
        for (int bit : multiplication) {
            zadanie.input(bit);
        }
        assertEquals(4, zadanie.wynik());

        int[] division = {
                0, 0, 1, 1,  // 2
                0, 1, 1,    // 6
                1, 1,        // "/"
                0, 1, 1      // 3
        };
        for (int bit : division) {
            zadanie.input(bit);
        }
        assertEquals(1, zadanie.wynik());

        assertEquals(-2_000_000_000, zadanie.wynik());
        assertEquals(-2_000_000_000, zadanie.wynik());
    }
}
