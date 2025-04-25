import java.util.*;

public class ArbolAVL {

    private final Comparator<String> comparator;
    private Nodo root;
    private int size;

    public ArbolAVL(Comparator<String> comparator) {
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
            return node;
        }

        return balance(node);
    }

    public Nodo getRoot() {
        return root;
    }

    private Nodo balance(Nodo node) {
        updateHeight(node);

        int balanceFactor = getBalanceFactor(node);
        if (balanceFactor > 1) {
            if (getBalanceFactor(node.left) < 0) {
                node.left = rotateLeft(node.left);
            }
            return rotateRight(node);
        } else if (balanceFactor < -1) {
            if (getBalanceFactor(node.right) > 0) {
                node.right = rotateRight(node.right);
            }
            return rotateLeft(node);
        }

        return node;
    }

    private void updateHeight(Nodo node) {
        node.height = 1 + Math.max(height(node.left), height(node.right));
    }

    private int getBalanceFactor(Nodo node) {
        return (node == null) ? 0 : height(node.left) - height(node.right);
    }

    private int height(Nodo node) {
        return (node == null) ? 0 : node.height;
    }

    private Nodo rotateRight(Nodo y) {
        Nodo x = y.left;
        Nodo T2 = x.right;

        x.right = y;
        y.left = T2;

        updateHeight(y);
        updateHeight(x);

        return x;
    }

    private Nodo rotateLeft(Nodo x) {
        Nodo y = x.right;
        Nodo T2 = y.left;

        y.left = x;
        x.right = T2;

        updateHeight(x);
        updateHeight(y);

        return y;
    }

    // Recorrido por niveles (para generar .txt)
    public List<Integer> recorridoPorNiveles() {
        List<Integer> resultado = new ArrayList<>();
        if (root == null) return resultado;

        Queue<Nodo> cola = new LinkedList<>();
        cola.add(root);

        while (!cola.isEmpty()) {
            Nodo actual = cola.poll();
            resultado.add((Integer) actual.id); // Solo guardás el ID

            if (actual.left != null) cola.add(actual.left);
            if (actual.right != null) cola.add(actual.right);
        }

        return resultado;
    }

    public int size() {
        return size;
    }
}
