package zadanie08;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ParallelFinderTest {

    private ParallelFinder finder;
    private ThreadsFactory threadsFactory;

    @BeforeEach
    void setUp() {
        finder = new ParallelFinder();
        threadsFactory = new TestThreadsFactory();
        finder.setThreadsFactory(threadsFactory);
    }

    @Test
    void testEmptyArray() {
        Array emptyArray = new TestArray(new int[0]);
        finder.setArray(emptyArray);

        List<Integer> result = finder.start(5);
        assertTrue(result.isEmpty(), "Result should be empty.");
    }

    @Test
    void testSingleElementFound() {
        Array singleArray = new TestArray(new int[]{42});
        finder.setArray(singleArray);

        List<Integer> result = finder.start(42);
        assertEquals(1, result.size(), "Should find one occurrence.");
        assertEquals(0, result.get(0), "Should be at index 0.");

        assertEquals(0, singleArray.get(0), "Index 0 should now be 0.");
    }

    @Test
    void testSingleElementNotFound() {
        Array singleArray = new TestArray(new int[]{42});
        finder.setArray(singleArray);

        List<Integer> result = finder.start(999);
        assertTrue(result.isEmpty(), "Should not find any occurrences.");
        assertEquals(42, singleArray.get(0));
    }

    @Test
    void testMultipleOccurrences() {
        int[] data = {3, 4, 3, 7, 4, 8};
        Array arr = new TestArray(data);
        finder.setArray(arr);

        List<Integer> result = finder.start(4);

        assertEquals(2, result.size(), "Should find 2 occurrences of the value 4.");
        assertTrue(result.contains(1), "Should contain index 1.");
        assertTrue(result.contains(4), "Should contain index 4.");

        assertEquals(0, arr.get(1), "Index 1 should now be 0.");
        assertEquals(0, arr.get(4), "Index 4 should now be 0.");

        assertEquals(3, arr.get(0));
        assertEquals(3, arr.get(2));
        assertEquals(7, arr.get(3));
        assertEquals(8, arr.get(5));
    }

    @Test
    void testValueOnEdges() {
        int[] data = {9, 2, 2, 9, 12, 3, 42, 3, 1, 3, 9, 3, 9};
        Array arr = new TestArray(data);
        finder.setArray(arr);

        List<Integer> result = finder.start(9);

        assertEquals(4, result.size(), "Should find 4 occurrences.");
        assertTrue(result.contains(0));
        assertTrue(result.contains(3));

        assertEquals(0, arr.get(0));
        assertEquals(2, arr.get(1));
        assertEquals(2, arr.get(2));
        assertEquals(0, arr.get(3));
    }
}
