package zadanie05;

import java.util.Set;

/**
 * Interfejs przepisu kulinarnego.
 */
public interface Przepis {

    /**
     * Metoda dostarczająca informację o nazwie potrawy.
     *
     * @return nazwa potrawy, która powstanie z użycia przepisu
     */
    default String naCo() {
        return "Przepis na potrawę";
    }

    /**
     * Składniki przepisu.
     *
     * @return zbiór składników przepisu
     */
    Set<Skladnik> sklad();
}
