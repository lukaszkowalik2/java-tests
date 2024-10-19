package zadanie01;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Zadanie01Test {

    private Zadanie01 zadanie;

    @BeforeEach
    void setUp() {
        zadanie = new Zadanie01();
    }

    @Test
    void testInitialError() {
        assertEquals(-2_000_000_000, zadanie.wynik());
    }

    @Test
    void testAddition() {
        // 0101 11111 00 01110 => (-15 + 14) = -1
        int[] bits = {
                0, 1, 0, 1,
                1, 1, 1, 1, 1,
                0, 0,
                0, 1, 1, 1, 0
        };

        for (int bit : bits) {
            zadanie.input(bit);
        }

        assertEquals(-1, zadanie.wynik());
    }

    @Test
    void testSubtraction() {
        // 0101 11111 01 01110 => (-15 - 14) = -29
        int[] bits = {
                0, 1, 0, 1,
                1, 1, 1, 1, 1,
                0, 1,
                0, 1, 1, 1, 0
        };

        for (int bit : bits) {
            zadanie.input(bit);
        }

        assertEquals(-29, zadanie.wynik());
    }

    @Test
    void testMultiplication() {
        // 0101 00011 10 00011 => (3 * 3) = 9
        int[] bits = {
                0, 1, 0, 1,
                0, 0, 0, 1, 1,
                1, 0,
                0, 0, 0, 1, 1
        };

        for (int bit : bits) {
            zadanie.input(bit);
        }

        assertEquals(9, zadanie.wynik());
    }

    @Test
    void testDivision() {
        // 0110 00100 11 00010 => (4 / 2) = 2
        int[] bits = {
                0, 1, 0, 1,
                0, 0, 1, 0, 0,
                1, 1,
                0, 0, 0, 1, 0
        };

        for (int bit : bits) {
            zadanie.input(bit);
        }

        assertEquals(2, zadanie.wynik());
    }

    @Test
    void testErrorForDivisionByZero() {
        // 0110 00100 11 00000 => (4 / 0) => BLAD
        int[] bits = {
                0, 1, 1, 0,
                0, 0, 1, 0, 0,
                1, 1,
                0, 0, 0, 0, 0
        };

        for (int bit : bits) {
            zadanie.input(bit);
        }

        assertEquals(-2_000_000_000, zadanie.wynik());
    }

    @Test
    void testResultCanOnlyBeFetchedOnce() {
        // 0101 11111 01 01110 => (-15 - 14) = -29
        int[] bits = {
                0, 1, 0, 1,
                1, 1, 1, 1, 1,
                0, 1,
                0, 1, 1, 1, 0
        };

        for (int bit : bits) {
            zadanie.input(bit);
        }

        assertEquals(-29, zadanie.wynik());
        assertEquals(-2_000_000_000, zadanie.wynik());
    }

    @Test
    void testResultOverwrittenByNewInput() {
        int[] firstBits = {
                0, 1, 0, 1,
                1, 1, 1, 1, 1,
                0, 0,
                0, 1, 1, 1, 0
        };

        for (int bit : firstBits) {
            zadanie.input(bit);
        }

        assertEquals(-1, zadanie.wynik());

        int[] secondBits = {
                0, 0, 1, 0,
                0, 0,
                0, 0,
                0, 1
        };

        for (int bit : secondBits) {
            zadanie.input(bit);
        }

        // New result should be 1, old result is overwritten
        assertEquals(1, zadanie.wynik());
    }

    @Test
    void testHandlingSmallNumbers() {
        // 0010 001 00 001 => (1 + 1) = 2
        int[] bits = {
                0, 0, 1, 1,
                0, 0, 1,
                0, 0,
                0, 0, 1
        };

        for (int bit : bits) {
            zadanie.input(bit);
        }

        assertEquals(2, zadanie.wynik());
    }

    @Test
    void testHandlingNegativeNumbers() {
        //  0 - -1
        int[] bits = {
                0, 0, 1, 1,
                1, 0, 0,
                0, 1,
                1, 0, 1
        };

        for (int bit : bits) {
            zadanie.input(bit);
        }

        assertEquals(1, zadanie.wynik());
    }
    @Test
    void testTwoSequencesBackToBack() {
        int[] firstSequence = {
                0, 1, 0, 1,
                1, 1, 1, 1, 1,
                0, 0,
                0, 1, 1, 1, 0
        };

        for (int bit : firstSequence) {
            zadanie.input(bit);
        }

        assertEquals(-1, zadanie.wynik());

        int[] secondSequence = {
                0, 0, 1, 0,
                0, 0,
                0, 0,
                0, 1
        };

        for (int bit : secondSequence) {
            zadanie.input(bit);
        }

        assertEquals(1, zadanie.wynik());
    }

    @Test
    void testNotEnoughBits() {
        int[] partialSequence = {
                0, 1, 0, 1,
                1, 1, 1, 1, 1,
                0, 0
        };

        for (int bit : partialSequence) {
            zadanie.input(bit);
        }
        assertEquals(-2_000_000_000, zadanie.wynik());
    }

    @Test
    void testTooManyBits() {
        // 0101 11111 00 01110 => (-15 + 14) = -1
        int[] bits = {
                0, 1, 0, 1,
                1, 1, 1, 1, 1,
                0, 0,
                0, 1, 1, 1, 0,
                1, 1, 1
        };

        for (int bit : bits) {
            zadanie.input(bit);
        }

        assertEquals(-1, zadanie.wynik());
    }

    @Test
    void testIncompleteSecondSequence() {
        // 0101 11111 00 01110 => (-15 + 14) = -1
        int[] firstSequence = {
                0, 1, 0, 1,
                1, 1, 1, 1, 1,
                0, 0,
                0, 1, 1, 1, 0
        };

        int[] incompleteSecondSequence = {
                0, 0, 1, 0,
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
        // 3 + 3 = 6
        int[] input = {0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 0, 1, 1};
        for (int bit : input) {
            zadanie.input(bit);
        }
        assertEquals(6, zadanie.wynik());
    }

    @Test
    void testAdditionOfSmallNumbers() {
        // 1 + 1 = 2
        int[] input = {0, 0, 1, 0, 0, 1, 0, 0, 0, 1};
        for (int bit : input) {
            zadanie.input(bit);
        }
        assertEquals(2, zadanie.wynik());
    }

    @Test
    void testMultiplicationOfNegativeNumbers() {
        // -3 * 3 = -9
        int[] input = {0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1};
        for (int bit : input) {
            zadanie.input(bit);
        }
        assertEquals(-9, zadanie.wynik());
    }

    @Test
    void testDivisionOfNegativeAndPositiveNumbers() {
        // -3 / 3 = -1
        int[] input = {0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1};
        for (int bit : input) {
            zadanie.input(bit);
        }
        assertEquals(-1, zadanie.wynik());
    }

    @Test
    void testSubtractionOfNegativeAndPositiveNumbers() {
        // -3 - 3 = -6
        int[] input = {0, 0, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1};
        for (int bit : input) {
            zadanie.input(bit);
        }
        assertEquals(-6, zadanie.wynik());
    }

    @Test
    void testInvalidLengthForA() {
        // Invalid input for part "a"
        int[] input = {0, 0, 1};
        for (int bit : input) {
            zadanie.input(bit);
        }
        assertEquals(-2_000_000_000, zadanie.wynik());
    }

    @Test
    void testInvalidLengthForB() {
        // Invalid input for part "b"
        int[] input = {1, 1, 1, 1, 0, 0, 0};
        for (int bit : input) {
            zadanie.input(bit);
        }
        assertEquals(-2_000_000_000, zadanie.wynik());
    }

    @Test
    void testTooLongInput() {
        // Input sequence too long
        int[] input = {0, 0, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 0, 0, 1, 0, 1, 0, 0, 1, 0};
        for (int bit : input) {
            zadanie.input(bit);
        }
        assertEquals(0, zadanie.wynik());
    }

    @Test
    void testAdditionOfSmallNumbersAfterMultipleRounds() {
        // 1 + 1 = 2
        int[] input = {0, 0, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 0, 1};
        for (int bit : input) {
            zadanie.input(bit);
        }
        assertEquals(2, zadanie.wynik());
    }

    @Test
    void testIncompleteData() {
        // Incomplete input, insufficient data bits
        int[] input = {0, 0, 1, 1, 0, 1, 1, 1, 0, 1};
        for (int bit : input) {
            zadanie.input(bit);
        }
        assertEquals(-2_000_000_000, zadanie.wynik());
    }

    @Test
    void testNegativeNumberInFirstByte() {
        // Negative first number (-10)
        int[] input = {1, 0, 1, 0, 0, 1, 0, 0, 0, 1};
        for (int bit : input) {
            zadanie.input(bit);
        }
        assertEquals(-2_000_000_000, zadanie.wynik());
    }

    @Test
    void testMultipleRoundsWithNewData() {
        // First: 3 + 3 = 6, then -3 + 3 = 0
        int[] input = {0, 0, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1};
        for (int bit : input) {
            zadanie.input(bit);
        }
        assertEquals(0, zadanie.wynik());
    }

    @Test
    void testMultipleRoundsWithTwoInputs() {
        // First: 3 + 3 = 6, then -3 - 3 = -6
        int[] input = {0, 0, 1, 1, 0, 1, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 1, 0, 1, 1};
        for (int bit : input) {
            zadanie.input(bit);
        }
        assertEquals(-6, zadanie.wynik());
    }
}
