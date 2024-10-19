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
            0, 1, 0, 1, //5
            1, 1, 1, 1, 1, //-15
            0, 0, // "+"
            0, 1, 1, 1, 1 // 15
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
            0, 1, 0, 1, //5
            1, 1, 1, 1, 1, //-15
            0, 1, // "-"
            0, 1, 1, 1, 1 // 15
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
            0, 1, 0, 1, //5
            1, 1, 1, 1, 1, //-15
            1, 0, // "*"
            0, 1, 1, 1, 1 // 15
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
            0, 1, 0, 1, //5
            1, 1, 1, 1, 1, //-15
            1, 1, // "/"
            0, 1, 1, 1, 1 // 15
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
            0, 1, 0, 1, //5
            1, 1, 1, 1, 1, //-15
            1, 1, // "/"
            0, 0, 0, 0, 0 // 0
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
            0, 1, 0, 1, //5
            1, 1, 1, 1, 1, //-15
            0, 0, // "+"
            0, 1, 1, 1, 1 // 15
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
            1, 1, 1, 1, //15
            1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, //-16383
            0, 0, // "+"
            1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, //-16383
        };

        for (int bit : firstBits) {
            zadanie.input(bit);
        }

        assertEquals(-16383 + -16383, zadanie.wynik());

        int[] secondBits = {
            1, 1, 1, 1, //15
            0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, //16383
            0, 1, // "-"
            1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, //-16382
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
            0, 0, 1, 0, //15
            0, 1, //1
            0, 1, // "-"
            1, 1, //-1
        };

        for (int bit : bits) {
            zadanie.input(bit);
        }

        assertEquals(2, zadanie.wynik());
    }

    @Test
    void testHandlingNegativeNumbers() {
        Zadanie01 zadanie = new Zadanie01();
        int[] bits = {
            0, 0, 1, 0, //15
            1, 0, // -0
            1, 0, // "*"
            1, 1, // -1
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
            0, 1, 0, 1, //5
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
        Zadanie01 zadanie = new Zadanie01();
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
        Zadanie01 zadanie = new Zadanie01();
        int[] input = {
            0, 0, 1, 1, //  3
            0, 0, 1, //  1
            1, 0, // "*"
            1, 1, 1 // "-3"
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
            0, 0, 1, 1, //3
            1, 1, 1, // -3
            1, 0, // "*"
            0, 0, 1 // 1
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
            0, 0, 1, 1, //  3
            1, 1, 1, //  -3
            1, 1, // "/"
            1, 0, 1 // -1
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
            0, 0, 1, 1, //3
            1, 1, 1, //-3
            1, 0, //"*"
            1, 0, 1 //-1
        };
        for (int bit : input) {
            zadanie.input(bit);
        }
        assertEquals(3, zadanie.wynik());
    }

    @Test
    void testInvalidLengthForA() {
        Zadanie01 zadanie = new Zadanie01();
        // Invalid input for part "a"
        int[] input = {
            0, 0, 1
        };
        for (int bit : input) {
            zadanie.input(bit);
        }
        assertEquals(-2_000_000_000, zadanie.wynik());
    }

    @Test
    void testInvalidLengthForB() {
        Zadanie01 zadanie = new Zadanie01();
        // Invalid input for part "b"
        int[] input = {
            1, 1, 1, 1,
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
        // Input sequence too long
        int[] input = {
            0, 0, 1, 1, //3
            1, 1, 1, //-3
            0, 0, //"+"
            1, 1, 1, //-3

            0, 0, 1, 0, //2
            1, 0, // -0
            0, 1, // "-"
            0 // will be 1
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
        // Incomplete input, insufficient data bits
        int[] input = {0, 0, 1, 1, 0, 1, 1, 1, 0, 1};
        for (int bit : input) {
            zadanie.input(bit);
        }
        assertEquals(-2_000_000_000, zadanie.wynik());
    }

    @Test
    void testNegativeNumberInFirstByte() {
        Zadanie01 zadanie = new Zadanie01();
        // Negative first number (-10)
        int[] input = {1, 0, 1, 0, 0, 1, 0, 0, 0, 1};
        for (int bit : input) {
            zadanie.input(bit);
        }
        assertEquals(-2_000_000_000, zadanie.wynik());
    }

    @Test
    void testMultipleRoundsWithNewData() {
        Zadanie01 zadanie = new Zadanie01();
        int[] input = {
            0, 0, 1, 1, //3
            0, 1, 1, //3
            0, 0, //"+"
            0, 1, 1, //3

            0, 0, 1, 1, //3
            1, 1, 1, //-3
            0, 0, //"+"
            0, 1, 1 //3
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
            0, 0, 0, 1, // 1
            0, // 0
            0, 0, // "+"
            1 // 0
        };

        for (int bit : input) {
            zadanie.input(bit);
        }

        assertEquals(-2_000_000_000, zadanie.wynik());
    }

    @Test
    void testTwoIndependentInstances() {
        Zadanie01 instance1 = new Zadanie01();
        Zadanie01 instance2 = new Zadanie01();

        int[] input1 = {0, 1, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0};
        for (int bit : input1) {
            instance1.input(bit);
        }

        int[] input2 = {0, 0, 1, 0, 0, 0, 0, 1};
        for (int bit : input2) {
            instance2.input(bit);
        }

        assertEquals(-1, instance1.wynik());
        assertEquals(-2_000_000_000, instance2.wynik());
    }
}
