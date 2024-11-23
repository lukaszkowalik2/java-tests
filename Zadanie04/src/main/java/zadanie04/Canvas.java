package zadanie04;

/**
 * Klasa reprezentuje płótno o stałej liczbie kolumn i wierszy. Kolor (jasność) piksela
 * na płótnie reprezentuje liczba całkowita. Położenia pikseli na płótnie zawierają się
 * od (0,0) do getMaxPosition włącznie. Wyjście poza poprawny obszar płótna powoduje błąd.
 */
public abstract class Canvas {
    /**
     * Pobranie jasności piksela znajdującego się na pozycji position.
     * @param position położenie piksela
     * @return jego jasność w zakresie od 0 do 255
     */
    abstract public int getBrightness(Position2D position);

    /**
     * Ustawienie jasności piksela na pozycji position na brightness.
     * @param position położenie piksela
     * @param brightness jasność do ustawienia od 0 do 255
     */
    abstract public void setBrightness(Position2D position, int brightness);

    /**
     * Położenie piksela o największej poprawnej liczbie kolumn i wierszy.
     * Można odczytać i zmienić położenie tego piksela.
     * @return maksymalne poprawne położenie piksela na płótnie
     */
    abstract public Position2D getMaxPosition();
}
