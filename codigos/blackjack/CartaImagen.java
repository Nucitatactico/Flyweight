package blackjack;

// Simula la imagen de una carta aprox 20 KB. Es parte del estado intrínseco pesado.
public final class CartaImagen {

    public static final int TAMANO_BYTES = 20_000;

    private final byte[] pixeles;

    CartaImagen() {
        this.pixeles = new byte[TAMANO_BYTES];
    }

    public int tamanoBytes() {
        return pixeles.length;
    }
}
