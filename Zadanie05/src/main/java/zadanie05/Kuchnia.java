package zadanie05;

import java.util.Map;
import java.util.Set;

/**
 * Interfejs kuchni.
 */
public interface Kuchnia {

    /**
     * Do spiżarni dodawany jest produkt w podanej liczbie gramów.
     *
     * @param produkt dodawany produkt
     * @param gram    ciężar produktu w gramach
     */
    void dodajDoSpizarni(Produkt produkt, int gram);

    /**
     * Na podstawie przepisu i stanu spiżarni wyliczana jest optymalna gramatura
     * składników tak, aby możliwe było przygotowanie potrawy. Jeśli w spiżarni brak
     * składników potrzebnych do przygotowania przepisu, zwracany jest pusty zbiór.
     *
     * @param przepis przepis do przygotowania
     * @return skład przepisu przeliczony według stanu spiżarni
     */
    Set<Skladnik> przeliczPrzepis(Przepis przepis);

    /**
     * Na podstawie przekazanego przepisu przygotowywana jest potrawa.
     * Jej przygotowanie obniża stan spiżarni.
     *
     * @param przepis przepis na potrawę
     * @return true - potrawę można było przygotować,
     *         false - stan spiżarni nie pozwala na przygotowanie potrawy
     */
    boolean wykonaj(Przepis przepis);

    /**
     * Aktualny stan spiżarni. Uwaga: raportowane są wyłącznie posiadane produkty.
     * Jeśli aktualna ilość posiadanego w spiżarni produktu wynosi 0, to nie jest on
     * raportowany.
     *
     * @return mapa, w której kluczem jest produkt, a wartością ciężar posiadanych
     *         zapasów danego produktu w gramach
     */
    Map<Produkt, Integer> stanSpizarni();
}
