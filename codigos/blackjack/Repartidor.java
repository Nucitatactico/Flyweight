package blackjack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Client: mantiene los mazos mezclados como referencias compartidas. Estado extrínseco: orden y posición.
public final class Repartidor {

    public static final int CARTAS_POR_MAZO = 52;

    private final List<Carta> cartas;

    public Repartidor(int numMazos) {
        List<Carta> temp = new ArrayList<>(numMazos * CARTAS_POR_MAZO);
        for (int m = 0; m < numMazos; m++) {
            for (Palo palo : Palo.values()) {
                for (Rango rango : Rango.values()) {
                    temp.add(CartaFactory.obtener(palo, rango));
                }
            }
        }
        Collections.shuffle(temp);
        this.cartas = temp;
    }

    public Carta repartir() {
        return cartas.remove(cartas.size() - 1);
    }

    public int cartasRestantes() {
        return cartas.size();
    }

    public List<Carta> instantanea() {
        return Collections.unmodifiableList(new ArrayList<>(cartas));
    }
}
