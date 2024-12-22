package zadanie09;

import java.util.List;

/**
 * Interfejs programu współbieżnie wyznaczającego wartości przekazanej funkcji.
 */
public interface ParallelCalculations {
    /**
     * Metoda wyznacza wartość funkcji function w (size)x(size) punktach. Funkcję
     * należy wyliczyć dla wszystkich par agrumentów z zakresu od 0 do size-1
     * uzywając w celu przypieszenia obliczeń threads wątków. Metoda zwraca
     * wynik w postaci listy list wartości. Jeśli referencja do listy list to
     * foo a bar to referencja do funkcji oraz:
     * <pre>
     * double v1 = foo.get(row).get(col);
     * double v2 = bar( row, col );
     * </pre>
     * to wartości <code>v1</code> i <code>v2</code> są identyczne.
     *
     * @param function funkcja dwóch argumentów do stablicowania
     * @param size liczba wierszy/kolumn
     * @param threads liczba wątków, których należy użyć w obliczeniach
     * @return lista list wartości.
     */
    List<List<Double>> map(Function function, int size, int threads);
}