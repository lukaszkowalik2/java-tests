package zadanie04;

import java.util.List;

/**
 * Klasa abstrakcyjna narzędzia do wypełniania płótna kolorem.
 */
public abstract class Fill {
    /**
     * Zlecenie wypełnienia obszaru kolorem. Płótno, na którym należy wykonać
     * operacje, lista sąsiadów, pozycja startowa, od której rozpoczyna się
     * wypełnianie oraz docelowa jasność podawane są przez użytkownika w wywołaniu
     * metody.
     *
     * @param canvas     płótno
     * @param neighbours lista pozycji sąsiednich pikseli liczona względem piksela o
     *                   położeniu (0,0). Położenia pikseli na tej liście mogą
     *                   zawierać liczby ujemne.
     * @param start      położenie piksela, od którego rozpoczyna się proces
     *                   wypełniania kolorem
     * @param brightness tym "kolorem" wypełniany jest obszar
     */
    public abstract void fill(Canvas canvas, List<Position2D> neighbours, Position2D start, int brightness);
}
