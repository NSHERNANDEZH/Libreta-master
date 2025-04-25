import java.util.*;

public class ArbolBST {
        private final Comparator<String> comparator;
        private Nodo root;
        private int size;

        public ArbolBST(Comparator<String> comparator) {
            this.comparator = comparator;
            this.root = null;
            this.size = 0;
        }

        public void insert(int id, String dato) {
            root = insert(root, new Nodo(id, dato));
        }

        private Nodo insert(Nodo node, Nodo nuevo) {
            if (node == null) {
                size++;
                return nuevo;
            }

            int cmp = comparator.compare(nuevo.dato, node.dato);
            if (cmp < 0) {
                node.left = insert(node.left, nuevo);
            } else if (cmp > 0) {
                node.right = insert(node.right, nuevo);
            } else {
                // Si el dato es igual, podés decidir ignorarlo o permitir duplicados por id
                return node;
            }

            return node;
        }

        public List<Integer> recorridoPorNiveles() {
            List<Integer> resultado = new ArrayList<>();
            if (root == null) return resultado;

            Queue<Nodo> cola = new LinkedList<>();
            cola.add(root);

            while (!cola.isEmpty()) {
                Nodo actual = cola.poll();
                resultado.add(actual.id); // solo se guarda el ID

                if (actual.left != null) cola.add(actual.left);
                if (actual.right != null) cola.add(actual.right);
            }

            return resultado;
        }

        public int size() {
            return size;
        }

    public Nodo getRoot() {
        return root;
    }
}


