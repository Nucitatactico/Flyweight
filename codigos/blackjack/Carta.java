package blackjack;

import java.util.Objects;

// ConcreteFlyweight: inmutable, con todo su estado intrínseco (palo, rango, imagen).
public final class Carta {

    private final Palo palo;
    private final Rango rango;
    private final CartaImagen imagen;

    Carta(Palo palo, Rango rango) {
        this.palo   = palo;
        this.rango  = rango;
        this.imagen = new CartaImagen();
    }

    public Palo getPalo()        { return palo; }
    public Rango getRango()      { return rango; }
    public int getValor()        { return rango.getValor(); }
    public CartaImagen getImagen() { return imagen; }

    // Recibe estado extrínseco por parámetro: si la carta está boca arriba o no.
    public String describe(boolean bocaArriba) {
        return bocaArriba ? toString() : "[carta oculta]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Carta)) return false;
        Carta otra = (Carta) o;
        return palo == otra.palo && rango == otra.rango;
    }

    @Override
    public int hashCode() {
        return Objects.hash(palo, rango);
    }

    @Override
    public String toString() {
        return rango + " de " + palo;
    }
}
