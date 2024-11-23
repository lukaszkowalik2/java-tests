package zadanie05;

import java.util.Map;
import java.util.Set;

public class EKuchnia implements Kuchnia {

    @Override
    public void dodajDoSpizarni(Produkt produkt, int gram) {}

    @Override
    public Set<Skladnik> przeliczPrzepis(Przepis przepis) {}

    @Override
    public boolean wykonaj(Przepis przepis) {}

    @Override
    public Map<Produkt, Integer> stanSpizarni() {}
}
