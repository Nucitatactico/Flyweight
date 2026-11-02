import java.util.HashMap;
import java.util.Map;

public class ArbolFactory {

    private static Map<String, Arbol> arboles = new HashMap<>();

    public static Arbol obtenerArbol(String tipo, String color, int altura) {

        String clave = tipo + color + altura;

        if (!arboles.containsKey(clave)) {

            arboles.put(clave, new Arbol(tipo, color, altura));

            System.out.println("Creando nuevo tipo de arbol: " + tipo);

        } else {

            System.out.println("Reutilizando arbol: " + tipo);
        }

        return arboles.get(clave);
    }
}