public class Nodo {
    int id;             // ID del contacto
    String dato;        // Campo usado para ordenar (ej: nombre, apellido)
    Nodo left;
    Nodo right;
    int height;

    public Nodo(int id, String dato) {
        this.id = id;
        this.dato = dato;
        this.height = 1;
    }
}