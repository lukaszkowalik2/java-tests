package zadanie07;

/**
 * Interfejs lokalizatora
 */
public interface Locator {
    /**
     * Wyjątek zgłaszanny, gdy podana kolumna jest duża
     */
    class ColumnToHighException extends Exception {
        private static final long serialVersionUID = 1338640189615772849L;
    };

    /**
     * Wyjątek zgłaszany, gdy podana kolumna jest za mała
     */
    class ColumnToLowException extends Exception {
        private static final long serialVersionUID = -1149972781727632717L;
    }

    /**
     * Wyjątek zgłaszany, gdy podany wiersz jest za duży
     */
    class RowToHighException extends Exception {
        private static final long serialVersionUID = -7339887856127998936L;
    }

    /**
     * Wyjątek zgłaszany, gdy podany wiersz jest za mały
     */
    class RowToLowException extends Exception {
        private static final long serialVersionUID = -1932358281879377610L;
    }

    /**
     * Informacja o maksymalnym, poprawnym indeksie wiersza.
     *
     * @return maksymalna wartość jaką można przypisać do wiersza
     */
    int maxRow();

    /**
     * Informacja o minimalnym, poprawnym indeksie wiersza
     *
     * @return najmniejsza wartość jaką można przypisać do wiersza
     */
    int minRow();

    /**
     * Informacja o maksymalnym, poprawnym indeksie kolumny.
     *
     * @return maksymalna wartość jaką można przypisać do kolumny
     */
    int maxCol();

    /**
     * Informacja o minimalnym, poprawnym indeksie kolumny
     *
     * @return najmniejsza wartość jaką można przypisać do kolumny
     */
    int minCol();

    /**
     * Test, czy to podana pozycja jest tą poszukiwaną. W przypadku pudła zgłaszany jest wyjątek
     * informujący o powodzie porażki. Z uwagi na to, że można jednocześnie zgłosić tylko jeden
     * wyjątek, w przypadku jednoczesnego błędnego podania złej kolumny i złego wiersza zgłaszany
     * jest jeden z dwóch możliwych wyjątków. Wybór zgłaszanego wyjątku odbywa się losowo.
     * Znalezienie poszukiwanego miejsca można rozpoznać, bo wtedy metoda nie zgłosi żadnego
     * wyjątku.
     *
     *
     * @param position pozycja
     * @throws ColumnToHighException kolumna za duża
     * @throws ColumnToLowException  kolumna za mała
     * @throws RowToHighException    wiersz za duży
     * @throws RowToLowException    wiersz za mały
     */
    void here(Position2D position)
            throws ColumnToHighException, ColumnToLowException, RowToHighException, RowToLowException;
}