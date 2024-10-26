package zadanie02;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Zadanie02Test {
    Zadanie02 zadanie = new Zadanie02();

    @Test
    void testAddingSmallNumbers() {
        List<Integer> result = zadanie.code(4, 2, "+", 3);
        List<Integer> expected = Arrays.asList(
                0, 1, 0, 0, // 4
                0, 0, 1, 0, // 2
                0, 0,       // +
                0, 0, 1, 1  // 3
        );
        assertEquals(expected, result);
    }

    @Test
    void testAddingZeroAndPositive() {
        List<Integer> result = zadanie.code(4, 0, "+", 1);
        List<Integer> expected = Arrays.asList(
                0, 1, 0, 0, // 4
                0, 0, 0, 0, // 0
                0, 0,       // +
                0, 0, 0, 1  // 1
        );
        assertEquals(expected, result);
    }

    @Test
    void testAddingPositiveAndNegative() {
        List<Integer> result = zadanie.code(4, 2, "+", -1);
        List<Integer> expected = Arrays.asList(
                0, 1, 0, 0, // 4
                0, 0, 1, 0, // 2
                0, 0,       // +
                1, 0, 0, 1  // -1
        );
        assertEquals(expected, result);
    }

    @Test
    void testAddingNegativeAndPositive() {
        List<Integer> result = zadanie.code(4, -2, "+", 1);
        List<Integer> expected = Arrays.asList(
                0, 1, 0, 0, // 4
                1, 0, 1, 0, // -2
                0, 0,       // +
                0, 0, 0, 1  // 1
        );
        assertEquals(expected, result);
    }

    @Test
    void testAddingTwoNegatives() {
        List<Integer> result = zadanie.code(4, -2, "+", -3);
        List<Integer> expected = Arrays.asList(
                0, 1, 0, 0, // 4
                1, 0, 1, 0, // -2
                0, 0,       // +
                1, 0, 1, 1  // -3
        );
        assertEquals(expected, result);
    }

    @Test
    void testAddingOverflow() {
        List<Integer> result = zadanie.code(5, 15, "+", 1);
        List<Integer> expected = Arrays.asList(
                0, 1, 0, 1,     // 5
                0, 1, 1, 1, 1,  // 15
                0, 0,           // +
                0, 0, 0, 0, 1   // 1
        );
        assertEquals(expected, result);
    }

    @Test
    void testAddingLengthTooSmall() {
        List<Integer> result = zadanie.code(4, 15, "+", 1);
        List<Integer> expected = Collections.emptyList();
        assertEquals(expected, result);
    }

    @Test
    void testAddingResultingInZero() {
        List<Integer> result = zadanie.code(4, 3, "+", -3);
        List<Integer> expected = Arrays.asList(
                0, 1, 0, 0, // 4
                0, 0, 1, 1, // 3
                0, 0,       // +
                1, 0, 1, 1  // -3
        );
        assertEquals(expected, result);
    }

    @Test
    void testAddingZeroAndZero() {
        List<Integer> result = zadanie.code(4, 0, "+", 0);
        List<Integer> expected = Arrays.asList(
                0, 1, 0, 0, // 4
                0, 0, 0, 0, // 0
                0, 0,       // +
                0, 0, 0, 0  // 0
        );
        assertEquals(expected, result);
    }

    @Test
    void testAddingNegativeAndZero() {
        List<Integer> result = zadanie.code(4, -4, "+", 0);
        List<Integer> expected = Arrays.asList(
                0, 1, 0, 0, // 4
                1, 1, 0, 0, // -4
                0, 0,       // +
                0, 0, 0, 0  // 0
        );
        assertEquals(expected, result);
    }

    @Test
    void testAddingPositiveWithPadding() {
        List<Integer> result = zadanie.code(5, 1, "+", 1);
        List<Integer> expected = Arrays.asList(
                0, 1, 0, 1,  // 5
                0, 0, 0, 0, 1, // 1
                0, 0,          // +
                0, 0, 0, 0, 1  // 1
        );
        assertEquals(expected, result);
    }

    @Test
    void testAddingTwoLargeNumbersOutOfBounds() {
        List<Integer> result = zadanie.code(4, 100, "+", 200);
        List<Integer> expected = Collections.emptyList();
        assertEquals(expected, result);
    }

    @Test
    void testAddingNegativeOutOfBounds() {
        List<Integer> result = zadanie.code(4, -100, "+", -100);
        List<Integer> expected = Collections.emptyList();
        assertEquals(expected, result);
    }

    @Test
    void testAddingSmallNegativeAndPositive() {
        List<Integer> result = zadanie.code(4, -2, "+", 2);
        List<Integer> expected = Arrays.asList(
                0, 1, 0, 0, // 4
                1, 0, 1, 0, // -2
                0, 0,       // +
                0, 0, 1, 0  // 2
        );
        assertEquals(expected, result);
    }

    @Test
    void testAddingSmallNumbersResultZero() {
        List<Integer> result = zadanie.code(4, 1, "+", -1);
        List<Integer> expected = Arrays.asList(
                0, 1, 0, 0, // 4
                0, 0, 0, 1, // 1
                0, 0,       // +
                1, 0, 0, 1  // -1
        );
        assertEquals(expected, result);
    }

    @Test
    void testAddingMixedSignWithPadding() {
        List<Integer> result = zadanie.code(6, 3, "+", -3);
        List<Integer> expected = Arrays.asList(
                0, 1, 1, 0,         // 6
                0, 0, 0, 0, 1, 1,   // 3
                0, 0,               // +
                1, 0, 0, 0, 1, 1    // -3
        );
        assertEquals(expected, result);
    }

    @Test
    void testAddingLargePositiveExceedingBitLimit() {
        List<Integer> result = zadanie.code(4, 16, "+", 1);
        List<Integer> expected = Collections.emptyList();
        assertEquals(expected, result);
    }

    @Test
    void testAddingNegativeResultExceedingBitLimit() {
        List<Integer> result = zadanie.code(4, -8, "+", -1);
        List<Integer> expected = Collections.emptyList();
        assertEquals(expected, result);
    }

    @Test
    void testAddingNegativeResultWithinLimit() {
        List<Integer> result = zadanie.code(5, -4, "+", -3);
        List<Integer> expected = Arrays.asList(
                0, 1, 0, 1,     // 5
                1, 0, 1, 0, 0,  // -4
                0, 0,           // +
                1, 0, 0, 1, 1   // -3
        );
        assertEquals(expected, result);
    }

    @Test
    void testAddingZeroAndNegative() {
        List<Integer> result = zadanie.code(4, 0, "+", -2);
        List<Integer> expected = Arrays.asList(
                0, 1, 0, 0, // 4
                0, 0, 0, 0, // 0
                0, 0,       // +
                1, 0, 1, 0  // -2
        );
        assertEquals(expected, result);
    }

    @Test
    void testAddingWithLongerBitLength() {
        List<Integer> result = zadanie.code(8, 4, "+", 5);
        List<Integer> expected = Arrays.asList(
                1, 0, 0, 0, // 8
                0, 0, 0, 0, 0, 1, 0, 0, // 4
                0, 0,             // +
                0, 0, 0, 0, 0, 1, 0, 1  // 5
        );
        assertEquals(expected, result);
    }

    @Test
    void testSubtractingSmallNumbers() {
        List<Integer> result = zadanie.code(4, 5, "-", 3);
        List<Integer> expected = Arrays.asList(
                0, 1, 0, 0, // 4
                0, 1, 0, 1, // 5
                0, 1,       // -
                0, 0, 1, 1  // 3
        );
        assertEquals(expected, result);
    }

    @Test
    void testSubtractingZeroAndPositive() {
        List<Integer> result = zadanie.code(4, 0, "-", 2);
        List<Integer> expected = Arrays.asList(
                0, 1, 0, 0, // 4
                0, 0, 0, 0, // 0
                0, 1,       // -
                0, 0, 1, 0  // 2
        );
        assertEquals(expected, result);
    }

    @Test
    void testSubtractingPositiveAndNegative() {
        List<Integer> result = zadanie.code(4, 2, "-", -1);
        List<Integer> expected = Arrays.asList(
                0, 1, 0, 0, // 4
                0, 0, 1, 0, // 2
                0, 1,       // -
                1, 0, 0, 1  // -1
        );
        assertEquals(expected, result);
    }

    @Test
    void testSubtractingNegativeAndPositive() {
        List<Integer> result = zadanie.code(4, -2, "-", 1);
        List<Integer> expected = Arrays.asList(
                0, 1, 0, 0, // 4
                1, 0, 1, 0, // -2
                0, 1,       // -
                0, 0, 0, 1  // 1
        );
        assertEquals(expected, result);
    }

    @Test
    void testSubtractingTwoNegatives() {
        List<Integer> result = zadanie.code(4, -3, "-", -1);
        List<Integer> expected = Arrays.asList(
                0, 1, 0, 0, // 4
                1, 0, 1, 1, // -3
                0, 1,       // -
                1, 0, 0, 1  // -1
        );
        assertEquals(expected, result);
    }

    @Test
    void testSubtractingOverflow() {
        List<Integer> result = zadanie.code(4, 15, "-", -1);
        List<Integer> expected = Collections.emptyList();
        assertEquals(expected, result);
    }

    @Test
    void testSubtractingResultingInZero() {
        List<Integer> result = zadanie.code(4, 3, "-", 3);
        List<Integer> expected = Arrays.asList(
                0, 1, 0, 0, // 4
                0, 0, 1, 1, // 3
                0, 1,       // -
                0, 0, 1, 1  // 3
        );
        assertEquals(expected, result);
    }

    @Test
    void testSubtractingZeroAndZero() {
        List<Integer> result = zadanie.code(4, 0, "-", 0);
        List<Integer> expected = Arrays.asList(
                0, 1, 0, 0, // 4
                0, 0, 0, 0, // 0
                0, 1,       // -
                0, 0, 0, 0  // 0
        );
        assertEquals(expected, result);
    }

    @Test
    void testSubtractingNegativeAndZero() {
        List<Integer> result = zadanie.code(5, -4, "-", 0);
        List<Integer> expected = Arrays.asList(
                0, 1, 0, 1,     // 5
                1, 0, 1, 0, 0,  // -4
                0, 1,           // -
                0, 0, 0, 0, 0   // 0
        );
        assertEquals(expected, result);
    }

    @Test
    void testSubtractingPositiveWithPadding() {
        List<Integer> result = zadanie.code(5, 5, "-", 1);
        List<Integer> expected = Arrays.asList(
                0, 1, 0, 1,  // 5
                0, 0, 1, 0, 1, // 5
                0, 1,          // -
                0, 0, 0, 0, 1  // 1
        );
        assertEquals(expected, result);
    }

    @Test
    void testSubtractingLargeNumbersOutOfBounds() {
        List<Integer> result = zadanie.code(4, 100, "-", 200);
        List<Integer> expected = Collections.emptyList();
        assertEquals(expected, result);
    }

    @Test
    void testSubtractingNegativeOutOfBounds() {
        List<Integer> result = zadanie.code(4, -100, "-", -100);
        List<Integer> expected = Collections.emptyList();
        assertEquals(expected, result);
    }

    @Test
    void testSubtractingWithLengthMismatch() {
        List<Integer> result = zadanie.code(2, 3, "-", 1);
        List<Integer> expected = Collections.emptyList();
        assertEquals(expected, result);
    }

    @Test
    void testSubtractingSmallNegativeAndPositive() {
        List<Integer> result = zadanie.code(4, -2, "-", 1);
        List<Integer> expected = Arrays.asList(
                0, 1, 0, 0, // 4
                1, 0, 1, 0, // -2
                0, 1,       // -
                0, 0, 0, 1  // 1
        );
        assertEquals(expected, result);
    }

    @Test
    void testSubtractingMixedSignWithPadding() {
        List<Integer> result = zadanie.code(6, 3, "-", -3);
        List<Integer> expected = Arrays.asList(
                0, 1, 1, 0,         // 6
                0, 0, 0, 0, 1, 1,   // 3
                0, 1,               // -
                1, 0, 0, 0, 1, 1    // -3
        );
        assertEquals(expected, result);
    }

    @Test
    void testSubtractingPositiveExceedingBitLimit() {
        List<Integer> result = zadanie.code(4, 16, "-", 1);
        List<Integer> expected = Collections.emptyList();
        assertEquals(expected, result);
    }

    @Test
    void testSubtractingNegativeResultExceedingBitLimit() {
        List<Integer> result = zadanie.code(4, -8, "-", 1);
        List<Integer> expected = Collections.emptyList();
        assertEquals(expected, result);
    }

    @Test
    void testSubtractingNegativeResultWithinLimit() {
        List<Integer> result = zadanie.code(4, -3, "-", -1);
        List<Integer> expected = Arrays.asList(
                0, 1, 0, 0, // 4
                1, 0, 1, 1, // -3
                0, 1,       // -
                1, 0, 0, 1  // -1
        );
        assertEquals(expected, result);
    }

    @Test
    void testSubtractingZeroAndNegative() {
        List<Integer> result = zadanie.code(4, 0, "-", -2);
        List<Integer> expected = Arrays.asList(
                0, 1, 0, 0, // 4
                0, 0, 0, 0, // 0
                0, 1,       // -
                1, 0, 1, 0  // -2
        );
        assertEquals(expected, result);
    }

    @Test
    void testSubtractingWithLongerBitLength() {
        List<Integer> result = zadanie.code(8, 9, "-", 5);
        List<Integer> expected = Arrays.asList(
                1, 0, 0, 0,                     // 8
                0, 0, 0, 0, 1 ,0 ,0, 1,         // 9
                0, 1,                           // -
                0, 0, 0, 0, 0, 1, 0, 1          // 5
        );
        assertEquals(expected, result);
    }

    @Test
    void testMultiplyingSmallNumbers() {
        List<Integer> result = zadanie.code(4, 2, "*", 3);
        List<Integer> expected = Arrays.asList(
                0, 1, 0, 0, // 4
                0, 0, 1, 0, // 2
                1, 0,       // *
                0, 0, 1, 1  // 3
        );
        assertEquals(expected, result);
    }

    @Test
    void testMultiplyingZeroAndPositive() {
        List<Integer> result = zadanie.code(4, 0, "*", 2);
        List<Integer> expected = Arrays.asList(
                0, 1, 0, 0, // 4
                0, 0, 0, 0, // 0
                1, 0,       // *
                0, 0, 1, 0  // 2
        );
        assertEquals(expected, result);
    }

    @Test
    void testMultiplyingPositiveAndNegative() {
        List<Integer> result = zadanie.code(4, 2, "*", -1);
        List<Integer> expected = Arrays.asList(
                0, 1, 0, 0, // 4
                0, 0, 1, 0, // 2
                1, 0,       // *
                1, 0, 0, 1  // -1
        );
        assertEquals(expected, result);
    }

    @Test
    void testMultiplyingNegativeAndPositive() {
        List<Integer> result = zadanie.code(4, -2, "*", 1);
        List<Integer> expected = Arrays.asList(
                0, 1, 0, 0, // 4
                1, 0, 1, 0, // -2
                1, 0,       // *
                0, 0, 0, 1  // 1
        );
        assertEquals(expected, result);
    }

    @Test
    void testMultiplyingTwoNegatives() {
        List<Integer> result = zadanie.code(4, -3, "*", -1);
        List<Integer> expected = Arrays.asList(
                0, 1, 0, 0, // 4
                1, 0, 1, 1, // -3
                1, 0,       // *
                1, 0, 0, 1  // -1
        );
        assertEquals(expected, result);
    }

    @Test
    void testMultiplyingResultingInOverflow() {
        List<Integer> result = zadanie.code(4, 15, "*", 2);
        List<Integer> expected = Collections.emptyList();
        assertEquals(expected, result);
    }

    @Test
    void testMultiplyingResultingInZero() {
        List<Integer> result = zadanie.code(4, 0, "*", 3);
        List<Integer> expected = Arrays.asList(
                0, 1, 0, 0, // 4
                0, 0, 0, 0, // 0
                1, 0,       // *
                0, 0, 1, 1  // 3
        );
        assertEquals(expected, result);
    }

    @Test
    void testMultiplyingOneAndZero() {
        List<Integer> result = zadanie.code(4, 1, "*", 0);
        List<Integer> expected = Arrays.asList(
                0, 1, 0, 0, // 4
                0, 0, 0, 1, // 1
                1, 0,       // *
                0, 0, 0, 0  // 0
        );
        assertEquals(expected, result);
    }

    @Test
    void testMultiplyingNegativeAndZero() {
        List<Integer> result = zadanie.code(4, -2, "*", 0);
        List<Integer> expected = Arrays.asList(
                0, 1, 0, 0, // 4
                1, 0, 1, 0, // -2
                1, 0,       // *
                0, 0, 0, 0  // 0
        );
        assertEquals(expected, result);
    }

    @Test
    void testMultiplyingPositiveWithPadding() {
        List<Integer> result = zadanie.code(5, 2, "*", 2);
        List<Integer> expected = Arrays.asList(
                0, 1, 0, 1,  // 5
                0, 0, 0, 1, 0, // 2
                1, 0,          // *
                0, 0, 0, 1, 0  // 2
        );
        assertEquals(expected, result);
    }

    @Test
    void testMultiplyingLargeNumbersOutOfBounds() {
        List<Integer> result = zadanie.code(4, 100, "*", 200);
        List<Integer> expected = Collections.emptyList();
        assertEquals(expected, result);
    }

    @Test
    void testMultiplyingNegativeLargeOutOfBounds() {
        List<Integer> result = zadanie.code(4, -50, "*", -4);
        List<Integer> expected = Collections.emptyList();
        assertEquals(expected, result);
    }

    @Test
    void testMultiplyingNegativeAndPositiveWithinLimit() {
        List<Integer> result = zadanie.code(4, -2, "*", 2);
        List<Integer> expected = Arrays.asList(
                0, 1, 0, 0, // 4
                1, 0, 1, 0, // -2
                1, 0,       // *
                0, 0, 1, 0  // 2
        );
        assertEquals(expected, result);
    }

    @Test
    void testMultiplyingMixedSignWithPadding() {
        List<Integer> result = zadanie.code(6, 3, "*", -1);
        List<Integer> expected = Arrays.asList(
                0, 1, 1, 0,         // 6
                0, 0, 0, 0, 1, 1,   // 3
                1, 0,               // *
                1, 0, 0, 0, 0, 1    // -1
        );
        assertEquals(expected, result);
    }

    @Test
    void testMultiplyingNegativeResultWithinLimit() {
        List<Integer> result = zadanie.code(4, -3, "*", 1);
        List<Integer> expected = Arrays.asList(
                0, 1, 0, 0, // 4
                1, 0, 1, 1, // -3
                1, 0,       // *
                0, 0, 0, 1  // 1
        );
        assertEquals(expected, result);
    }

    @Test
    void testMultiplyingZeroAndNegative() {
        List<Integer> result = zadanie.code(4, 0, "*", -2);
        List<Integer> expected = Arrays.asList(
                0, 1, 0, 0, // 4
                0, 0, 0, 0, // 0
                1, 0,       // *
                1, 0, 1, 0  // -2
        );
        assertEquals(expected, result);
    }

    @Test
    void testMultiplyingWithLongerBitLength() {
        List<Integer> result = zadanie.code(8, 4, "*", 2);
        List<Integer> expected = Arrays.asList(
                1, 0, 0, 0,             // 8
                0, 0, 0, 0, 0, 1, 0, 0, // 4
                1, 0,                   // *
                0, 0, 0, 0, 0, 0, 1, 0  // 2
        );
        assertEquals(expected, result);
    }

    @Test
    void testDividingSmallNumbers() {
        List<Integer> result = zadanie.code(4, 6, "/", 3);
        List<Integer> expected = Arrays.asList(
                0, 1, 0, 0, // 4
                0, 1, 1, 0, // 6
                1, 1,       // /
                0, 0, 1, 1  // 3
        );
        assertEquals(expected, result);
    }

    @Test
    void testDividingZeroAndPositive() {
        List<Integer> result = zadanie.code(4, 0, "/", 2);
        List<Integer> expected = Arrays.asList(
                0, 1, 0, 0, // 4
                0, 0, 0, 0, // 0
                1, 1,       // /
                0, 0, 1, 0  // 2
        );
        assertEquals(expected, result);
    }

    @Test
    void testDividingPositiveAndNegative() {
        List<Integer> result = zadanie.code(5, 8, "/", -2);
        List<Integer> expected = Arrays.asList(
                0, 1, 0, 1,     // 5
                0, 1, 0, 0, 0,  // 8
                1, 1,           // /
                1, 0, 0, 1, 0   // -2
        );
        assertEquals(expected, result);
    }

    @Test
    void testDividingNegativeAndPositive() {
        List<Integer> result = zadanie.code(5, -8, "/", 2);
        List<Integer> expected = Arrays.asList(
                0, 1, 0, 1,     // 5
                1, 1, 0, 0, 0,  // -8
                1, 1,           // /
                0, 0, 0, 1, 0   // 2
        );
        assertEquals(expected, result);
    }

    @Test
    void testDividingTwoNegatives() {
        List<Integer> result = zadanie.code(4, -6, "/", -3);
        List<Integer> expected = Arrays.asList(
                0, 1, 0, 0, // 4
                1, 1, 1, 0, // -6
                1, 1,       // /
                1, 0, 1, 1  // -3
        );
        assertEquals(expected, result);
    }

    @Test
    void testDividingResultingInOne() {
        List<Integer> result = zadanie.code(4, 7, "/", 7);
        List<Integer> expected = Arrays.asList(
                0, 1, 0, 0, // 4
                0, 1, 1, 1, // 7
                1, 1,       // /
                0, 1, 1, 1  // 7
        );
        assertEquals(expected, result);
    }

    @Test
    void testDividingResultingInZero() {
        List<Integer> result = zadanie.code(5, 1, "/", 8);
        List<Integer> expected = Arrays.asList(
                0, 1, 0, 1,     // 5
                0, 0, 0, 0, 1,  // 1
                1, 1,           // /
                0, 1, 0, 0, 0   // 8
        );
        assertEquals(expected, result);
    }

    @Test
    void testDividingPositiveByOne() {
        List<Integer> result = zadanie.code(4, 5, "/", 1);
        List<Integer> expected = Arrays.asList(
                0, 1, 0, 0, // 4
                0, 1, 0, 1, // 5
                1, 1,       // /
                0, 0, 0, 1  // 1
        );
        assertEquals(expected, result);
    }

    @Test
    void testDividingPositiveWithPadding() {
        List<Integer> result = zadanie.code(5, 10, "/", 2);
        List<Integer> expected = Arrays.asList(
                0, 1, 0, 1,     // 5
                0, 1, 0, 1, 0,  // 10
                1, 1,           // /
                0, 0, 0, 1, 0   // 2
        );
        assertEquals(expected, result);
    }

    @Test
    void testDividingLargeNumbersOutOfBounds() {
        List<Integer> result = zadanie.code(4, 100, "/", 2);
        List<Integer> expected = Collections.emptyList();
        assertEquals(expected, result);
    }

    @Test
    void testDividingNegativeLargeOutOfBounds() {
        List<Integer> result = zadanie.code(4, -50, "/", -2);
        List<Integer> expected = Collections.emptyList(); // Exceeds 4-bit range
        assertEquals(expected, result);
    }

    @Test
    void testDividingWithLengthMismatch() {
        List<Integer> result = zadanie.code(3, 4, "/", 2);
        List<Integer> expected = Collections.emptyList(); // Length mismatch
        assertEquals(expected, result);
    }

    @Test
    void testDividingNegativeAndPositiveWithinLimit() {
        List<Integer> result = zadanie.code(4, -4, "/", 2);
        List<Integer> expected = Arrays.asList(
                0, 1, 0, 0, // 4
                1, 1, 0, 0, // -4
                1, 1,       // /
                0, 0, 1, 0  // 2
        );
        assertEquals(expected, result);
    }

    @Test
    void testDividingMixedSignWithPadding() {
        List<Integer> result = zadanie.code(6, 12, "/", -4);
        List<Integer> expected = Arrays.asList(
                0, 1, 1, 0,    // 6
                0, 0, 1, 1, 0, 0, // 12
                1, 1,           // /
                1, 0, 0, 1, 0, 0 // -4
        );
        assertEquals(expected, result);
    }

    @Test
    void testDividingPositiveExceedingBitLimit() {
        List<Integer> result = zadanie.code(4, 16, "/", 1);
        List<Integer> expected = Collections.emptyList(); // Exceeds 4-bit range
        assertEquals(expected, result);
    }

    @Test
    void testDividingNegativeResultExceedingBitLimit() {
        List<Integer> result = zadanie.code(4, -8, "/", 1);
        List<Integer> expected = Collections.emptyList(); // Exceeds 4-bit range
        assertEquals(expected, result);
    }

    @Test
    void testDividingNegativeResultWithinLimit() {
        List<Integer> result = zadanie.code(5, -9, "/", 3);
        List<Integer> expected = Arrays.asList(
                0, 1, 0, 1,     // 5
                1, 1, 0, 0, 1,  // -9
                1, 1,           // /
                0, 0,  0, 1, 1  // 3
        );
        assertEquals(expected, result);
    }

    @Test
    void testDividingZeroAndNegative() {
        List<Integer> result = zadanie.code(4, 0, "/", -2);
        List<Integer> expected = Arrays.asList(
                0, 1, 0, 0, // 4
                0, 0, 0, 0, // 0
                1, 1,       // /
                1, 0, 1, 0  // -2
        );
        assertEquals(expected, result);
    }

    @Test
    void testDividingWithLongerBitLength() {
        List<Integer> result = zadanie.code(8, 16, "/", 2);
        List<Integer> expected = Arrays.asList(
                1, 0, 0, 0,             // 8
                0, 0, 0, 1, 0, 0, 0, 0, // 16
                1, 1,                   // /
                0, 0, 0, 0, 0, 0, 1, 0  // 2
        );
        assertEquals(expected, result);
    }

    @Test
    void testDividingByZero() {
        List<Integer> result = zadanie.code(4, 5, "/", 0);
        List<Integer> expected = Arrays.asList(
                0, 1, 0, 0, // 4
                0, 1, 0, 1, // 5
                1, 1,       // /
                0, 0, 0, 0  // 0
        );
        assertEquals(expected, result);
    }

    @Test
    void testAddingMaxLength() {
        List<Integer> result = zadanie.code(15, 16383, "+", -16383);
        List<Integer> expected = Arrays.asList(
                1, 1, 1, 1, // 15
                0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 ,1 ,1 ,1, 1, // 16383
                0, 0,       // +
                1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 ,1 ,1 ,1, 1 // -16383
        );
        assertEquals(expected, result);
    }

    @Test
    void testSubMaxLength() {
        List<Integer> result = zadanie.code(15, 16383, "-", -16383);
        List<Integer> expected = Arrays.asList(
                1, 1, 1, 1, // 15
                0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 ,1 ,1 ,1, 1, // 16383
                0, 1,       // -
                1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 ,1 ,1 ,1, 1 // -16383
        );
        assertEquals(expected, result);
    }

    @Test
    void testMultiMaxLength() {
        List<Integer> result = zadanie.code(15, 16383, "*", -16383);
        List<Integer> expected = Arrays.asList(
                1, 1, 1, 1, // 15
                0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 ,1 ,1 ,1, 1, // 16383
                1, 0,       // *
                1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 ,1 ,1 ,1, 1 // -16383
        );
        assertEquals(expected, result);
    }

    @Test
    void testDiviMaxLength() {
        List<Integer> result = zadanie.code(15, 16383, "/", -16383);
        List<Integer> expected = Arrays.asList(
                1, 1, 1, 1, // 15
                0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 ,1 ,1 ,1, 1, // 16383
                1, 1,       // /
                1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 ,1 ,1 ,1, 1 // -16383
        );
        assertEquals(expected, result);
    }

}
