public class Arbol {

    private String tipo;
    private String color;
    private int altura;

    public Arbol(String tipo, String color, int altura) {
        this.tipo = tipo;
        this.color = color;
        this.altura = altura;
    }

    public void dibujar(int x, int y) {
        System.out.println("Arbol: " + tipo + " | Color: " + color + " | Altura: " + altura + " | Posicion: (" + x + "," + y + ")");
    }
}