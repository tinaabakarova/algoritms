package bynarySearchTree;

public class BinarySearchTree extends BinaryTree {

    public BinarySearchTree(int key) {
        setRoot(new Node(key));
    }

    public BinarySearchTree() {
        setRoot(null);
    }

    protected Node insert(Node root, int key) {
        if (root == null) {
            root = new Node(key);
            return root;
        } else if (key < root.getKey())
            root.setLeft(insert(root.getLeft(), key));
        else if (key > root.getKey())
            root.setRight(insert(root.getRight(), key));

        return root;
    }

    protected Node delete(Node root, int key)
    {
        if (root == null)
            return null;

        if (key < root.getKey())
            root.setLeft(delete(root.getLeft(), key));
        else if (key > root.getKey())
            root.setRight(delete(root.getRight(), key));

        else {
            if (root.getLeft() == null)
                return root.getRight();
            else if (root.getRight() == null)
                return root.getLeft();

            root.setKey(findMin(root.getRight()));

            root.setRight(delete(root.getRight(), root.getKey()));
        }

        return root;
    }
}
