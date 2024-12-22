package zadanie07;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LocatorTest {

    private Locator getLocator(int minCol, int maxCol, int minRow, int maxRow, Position2D correct) {
        return new Locator() {
            @Override
            public int maxRow() {
                return maxRow;
            }

            @Override
            public int minRow() {
                return minRow;
            }

            @Override
            public int maxCol() {
                return maxCol;
            }

            @Override
            public int minCol() {
                return minCol;
            }

            @Override
            public void here(Position2D position) throws ColumnToHighException, ColumnToLowException, RowToHighException, RowToLowException {
                if (position.col() < correct.col()) throw new ColumnToLowException();
                else if (position.col() > correct.col()) throw new ColumnToHighException();
                else if (position.row() < correct.row()) throw new RowToLowException();
                else if (position.row() > correct.row()) throw new RowToHighException();
            }
        };
    }

    @Test
    void testFindPositionWithinBounds() {
        Locator locator = getLocator(0, 10, 0, 10, new Position2D(5, 5));
        FastFinder ff = new FastFinder();
        Position2D result = ff.tryToFind(locator);
        assertEquals(5, result.col());
        assertEquals(5, result.row());
    }

    @Test
    void testFindPositionAtBoundary() {
        Locator locator = getLocator(0, 10, 0, 10, new Position2D(0, 0));
        FastFinder ff = new FastFinder();
        Position2D result = ff.tryToFind(locator);
        assertEquals(0, result.col());
        assertEquals(0, result.row());
    }

    @Test
    void testFindPositionAtUpperBoundary() {
        Locator locator = getLocator(0, 10, 0, 10, new Position2D(10, 10));
        FastFinder ff = new FastFinder();
        Position2D result = ff.tryToFind(locator);
        assertEquals(10, result.col());
        assertEquals(10, result.row());
    }

    @Test
    void testFindPositionOutsideBounds() {
        Locator locator = getLocator(-5000, 5000, -2000, 2000, new Position2D(-50, -100));
        FastFinder ff = new FastFinder();
        Position2D result = ff.tryToFind(locator);
        assertEquals(-50, result.col());
        assertEquals(-100, result.row());
    }

    @Test
    void testFindPositionWithinSmallBounds() {
        Locator locator = getLocator(0, 1, 0, 1, new Position2D(1, 1));
        FastFinder ff = new FastFinder();
        Position2D result = ff.tryToFind(locator);
        assertEquals(1, result.col());
        assertEquals(1, result.row());
    }
}
