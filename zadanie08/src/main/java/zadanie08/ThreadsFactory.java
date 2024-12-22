package zadanie08;

/**
 * Interfejs fabryki wątków. Każda z metod dostarcza
 * tylko jednego wątku. Każdą z metod wolno wywołać tylko jeden raz.
 */
public interface ThreadsFactory {
    /**
     * Metoda zwraca obiekt-wątek w stanie NEW (nieuruchomiony). Wątek
     * przeznaczony wyłącznie do odczytu danych rozpoczynając od pozycji 0.
     *
     * @param run kod do wykonania w wątku
     * @return wątek, który przekazany kod wykona
     */
    Thread leftReadOnlyThread(Runnable run);

    /**
     * Metoda zwraca obiekt-wątek w stanie NEW (nieuruchomiony). Wątek przeznaczony
     * wyłącznie do odczytu danych rozpoczynając od maksymalnej pozycji w tablicy.
     *
     * @param run kod do wykonania w wątku
     * @return wątek, który przekazany kod wykona
     */
    Thread rightReadOnlyThread(Runnable run);

    /**
     * Wątek, który ma prawa do zapisu.
     *
     * @param run kod do realizacji w wątku
     * @return wątek, w którym kod zostanie wykonany
     */
    Thread writeOnlyThread(Runnable run);
}