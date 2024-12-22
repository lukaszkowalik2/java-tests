package zadanie08;

import java.util.List;

/**
 * Interfejs poszukiwacza
 */
public interface Finder {
    /**
     * Ustawia dostęp do fabryki wątków
     *
     * @param factory fabryka wątków
     */
    void setThreadsFactory(ThreadsFactory factory);

    /**
     * Ustawia dostęp do tablicy, którą należy przeszukać
     *
     * @param array tablica do przeszukania
     */
    void setArray(Array array);

    /**
     * Uruchamia poszukiwania w tablicy wartości value.
     *
     * @param value wartość poszukiwana w tablicy
     * @return lista położeń, w których znaleziono value
     */
    List<Integer> start(int value);
}