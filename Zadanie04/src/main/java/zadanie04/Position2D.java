package zadanie04;

/**
 * Klasa reprezentuje położenie piksela na płótnie. Położenie jest niezmienne.
 */
public class Position2D {
    private final int col;
    private final int row;

    public Position2D(int col, int row) {
        this.col = col;
        this.row = row;
    }

    @Override
    public String toString() {
        return "Position2D [col=" + col + ", row=" + row + "]";
    }

    /**
     * Kolumna, w której znajduje się piksel.
     *
     * @return numer kolumny
     */
    public int getCol() {
        return col;
    }

    /**
     * Wiersz, w którym znajduje się piksel.
     *
     * @return numer wiersza
     */
    public int getRow() {
        return row;
    }
}
