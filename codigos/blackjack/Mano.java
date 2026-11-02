package blackjack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Client: mantiene el estado extrínseco por jugador (dueño y boca arriba). Guarda referencias a flyweights.
public final class Mano {

    public static final class CartaEnMano {
        private final Carta carta;
        private final boolean bocaArriba;

        CartaEnMano(Carta carta, boolean bocaArriba) {
            this.carta = carta;
            this.bocaArriba = bocaArriba;
        }

        public Carta getCarta()         { return carta; }
        public boolean estaBocaArriba() { return bocaArriba; }

        @Override
        public String toString() {
            return bocaArriba ? carta.toString() : "[oculta]";
        }
    }

    private final List<CartaEnMano> cartas = new ArrayList<>();

    public void recibir(Carta carta, boolean bocaArriba) {
        cartas.add(new CartaEnMano(carta, bocaArriba));
    }

    public int total() {
        int suma = 0;
        for (CartaEnMano c : cartas) {
            suma += c.getCarta().getValor();
        }
        return suma;
    }

    public List<CartaEnMano> cartas() {
        return Collections.unmodifiableList(cartas);
    }

    @Override
    public String toString() {
        return cartas.toString();
    }
}
