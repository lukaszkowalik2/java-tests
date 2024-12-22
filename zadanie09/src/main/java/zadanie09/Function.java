package zadanie09;

/**
 * Interfejs funkcji dwóch argumentów.
 */
public interface Function {
    /**
     * Metoda zwraca wartość funkcji wyliczoną na podstawie wartości dwóch
     * parametrów row i col. Czas wyznaczania wartości funkcji nie będzie
     * stały. Może zależeć od argumentów wywołania, może być wręcz losowy.
     * Funkcja bezpieczna w programach wielowątkowych.
     *
     * @param row parametr pierwszy
     * @param col parametr drugi
     * @return wartość funkcji
     */
    double get(int row, int col);
}