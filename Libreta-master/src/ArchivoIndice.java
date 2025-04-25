import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ArchivoIndice {


    public static void guardarEstructuraConNulls(Nodo root, String campo, String tipoArbol) throws IOException {
        String nombreArchivo = campo + "-" + tipoArbol + "-completo.txt";
        PrintWriter writer = new PrintWriter(new FileWriter(nombreArchivo));
        Queue<Nodo> cola = new LinkedList<>();

        cola.add(root);

        while (!cola.isEmpty()) {
            Nodo actual = cola.poll();

            if (actual == null) {
                writer.println("null");
                continue;
            }

            writer.println(actual.id);
            cola.add(actual.left);
            cola.add(actual.right);
        }

        writer.close();
        System.out.println("Archivo completo generado: " + nombreArchivo);
    }
}