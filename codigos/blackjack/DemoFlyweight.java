package blackjack;

import java.util.HashMap;
import java.util.Map;

// Demo del patrón Flyweight con un blackjack de varios mazos.
public final class DemoFlyweight {

    //Variables que ejemplifican
    private static final int MAZOS_POR_MESA   = 8;
    private static final int MESAS_ESCALADO   = 200;
    private static final double BYTES_POR_MB  = 1024.0 * 1024.0;

    public static void main(String[] args) {
        System.out.printf("Aplicacion de Flyweight en un juego de blackjack \n(%d MAZOS)\n\n", MAZOS_POR_MESA);
        identidad();
        cache();
        repartidor();
        referencias();
        jugarMano();
        escalado();
    }

    private static void identidad() {
        System.out.println("\nDemostracion de identidad en memoria");
        Carta a1 = CartaFactory.obtener(Palo.PICAS, Rango.AS);
        Carta a2 = CartaFactory.obtener(Palo.PICAS, Rango.AS);
        System.out.printf("a1 == a2 ? %b\n", a1 == a2);
        System.out.printf("identityHashCode(a1) = %d\n", System.identityHashCode(a1));
        System.out.printf("identityHashCode(a2) = %d\n", System.identityHashCode(a2));
        System.out.printf("describe(true)  = %s\n", a1.describe(true));
        System.out.printf("describe(false) = %s\n", a1.describe(false));
    }

    private static void cache() {
        System.out.println("\nDemostracion del cache");
        System.out.printf("Instancias en cache: %d\n", CartaFactory.tamanoCache());
    }

    private static void repartidor() {
        System.out.printf("\nuso de un cliente que es repartidor de %d mazos\n", MAZOS_POR_MESA);
        Repartidor repartidor = new Repartidor(MAZOS_POR_MESA);
        int referencias = repartidor.cartasRestantes();
        int objetos     = CartaFactory.tamanoCache();
        long ahorro     = calcularAhorroBytes(referencias);

        System.out.printf("Referencias en el repartidor: %d\n", referencias);
        System.out.printf("Objetos Carta totales:        %d\n", objetos);
        System.out.printf("Ahorro estimado:              %,d B (%.2f MB)\n",ahorro, ahorro / BYTES_POR_MB);
    }

    private static void referencias() {
        System.out.println("\nReferencias unicas y totales");
        Repartidor repartidor = new Repartidor(MAZOS_POR_MESA);
        Map<Integer, Integer> refs = new HashMap<>();
        for (Carta c : repartidor.instantanea())
            refs.merge(System.identityHashCode(c), 1, Integer::sum);
     
        System.out.printf("Identidades únicas (objetos): %d\n", refs.size());
        System.out.printf("Referencias totales:          %d\n", repartidor.cartasRestantes());
    }

    //Metodo que simula como es una iteracion del juego
    private static void jugarMano() {
	System.out.println("\nejemplo de iteracion del juego");
        System.out.println("\nRepartir mano");
        Repartidor repartidor = new Repartidor(MAZOS_POR_MESA);
        Mano jugador = new Mano();
        jugador.recibir(repartidor.repartir(), true);
        jugador.recibir(repartidor.repartir(), false);
        System.out.printf("Mano: %s → total: %d\n", jugador, jugador.total());
    }

    private static void escalado() {
        int usosEscalados = MESAS_ESCALADO * MAZOS_POR_MESA * Repartidor.CARTAS_POR_MAZO;
        long sinFlyweight = (long) usosEscalados * CartaImagen.TAMANO_BYTES;
        long conFlyweight = (long) CartaFactory.tamanoCache() * CartaImagen.TAMANO_BYTES;
        long ahorro       = sinFlyweight - conFlyweight;

        System.out.println("\nEscalado imaginario para este modelo de diseño");
        System.out.printf("Mesas × mazos × cartas = %d × %d × %d = %,d usos\n",
                MESAS_ESCALADO, MAZOS_POR_MESA, Repartidor.CARTAS_POR_MAZO, usosEscalados);
        System.out.printf("Sin Flyweight: %,d objetos × %,d B = %,d B (%.2f MB)\n",
                usosEscalados, CartaImagen.TAMANO_BYTES, sinFlyweight, sinFlyweight / BYTES_POR_MB);
        System.out.printf("Con Flyweight: %,d objetos × %,d B = %,d B (%.2f MB)\n",
                CartaFactory.tamanoCache(), CartaImagen.TAMANO_BYTES, conFlyweight, conFlyweight / BYTES_POR_MB);
        System.out.printf("Ahorro:        %,d B (%.2f MB)\n",
                ahorro, ahorro / BYTES_POR_MB);
    }

    private static long calcularAhorroBytes(int referencias) {
        long sinFlyweight = (long) referencias * CartaImagen.TAMANO_BYTES;
        long conFlyweight = (long) CartaFactory.tamanoCache() * CartaImagen.TAMANO_BYTES;
        return sinFlyweight - conFlyweight;
    }
}
