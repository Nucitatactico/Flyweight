public class Main {
    public static void main(String[] args) {

        Arbol arbol1 = ArbolFactory.obtenerArbol("Pino", "Verde", 45);
        Arbol arbol2 = ArbolFactory.obtenerArbol("Pino", "Verde", 45);
        Arbol arbol3 = ArbolFactory.obtenerArbol("Roble", "Cafe", 30);
        Arbol arbol4 = ArbolFactory.obtenerArbol("Roble", "Cafe", 30);
        Arbol arbol5 = ArbolFactory.obtenerArbol("Pino", "Verde", 45);

        arbol1.dibujar(10, 20);
        arbol2.dibujar(30, 40);
        arbol3.dibujar(50, 60);
        arbol4.dibujar(70, 80);
        arbol5.dibujar(90, 100);
    }
}