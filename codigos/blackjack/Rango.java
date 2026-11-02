package blackjack;

// Rango de una carta con su valor en blackjack. Enum singleton que es un estado intrínseco compartido.
public enum Rango {
    AS(11),
    DOS(2),
    TRES(3),
    CUATRO(4),
    CINCO(5),
    SEIS(6),
    SIETE(7),
    OCHO(8),
    NUEVE(9),
    DIEZ(10),
    JOTA(10),
    REINA(10),
    REY(10);

    private final int valor;

    Rango(int valor) {
        this.valor=valor;
    }

    public int getValor() {
        return valor;
    }
}
