package blackjack;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

// FlyweightFactory: única fuente de instancias de Carta. Cachea las 52 cartas para siempre.
public final class CartaFactory {

    private static final Map<Palo, Map<Rango, Carta>> CACHE = construirCache();

    private CartaFactory() {
        throw new AssertionError("Clase utilitaria: no instanciar");
    }

    private static Map<Palo, Map<Rango, Carta>> construirCache() {
        Map<Palo, Map<Rango, Carta>> cache = new EnumMap<>(Palo.class);
        for (Palo palo : Palo.values()) {
            Map<Rango, Carta> porRango = new EnumMap<>(Rango.class);

            for (Rango rango : Rango.values())
                porRango.put(rango, new Carta(palo, rango));
   
            cache.put(palo, Collections.unmodifiableMap(porRango));
        }
        return Collections.unmodifiableMap(cache);
    }

    public static Carta obtener(Palo palo, Rango rango) {
        return CACHE.get(palo).get(rango);
    }

    public static int tamanoCache() {
        return Palo.values().length * Rango.values().length;
    }
}
