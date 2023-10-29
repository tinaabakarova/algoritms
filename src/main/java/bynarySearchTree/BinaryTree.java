package bynarySearchTree;

public abstract class BinaryTree {
    private Node root;

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public void insert(int key) {
        root = insert(root, key);
    }

    protected abstract Node insert(Node root, int key);

    public Node search(Node root, int key) {
        if (root == null || root.getKey() == key)
            return root;

        if (root.getKey() < key)
            return search(root.getRight(), key);

        return search(root.getLeft(), key);
    }

    public void delete(int key) { root = delete(root, key); }

    protected abstract Node delete(Node root, int key);

    protected int findMin(Node root)
    {
        int min = root.getKey();
        while (root.getLeft() != null) {
            min = root.getLeft().getKey();
            root = root.getLeft();
        }
        return min;
    }

    public void print() {
        print(root);
    }

    private void print(Node node)
    {
        if (node != null)
        {
            System.out.print(node.getKey() + " ");
            print(node.getLeft());
            print(node.getRight());
        }
    }
}
