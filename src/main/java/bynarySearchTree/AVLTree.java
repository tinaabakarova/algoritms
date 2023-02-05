package bynarySearchTree;

public class AVLTree extends BinaryTree {

    private int height(Node N) {
        if (N == null)
            return 0;

        return N.getHeight();
    }

    private int max(int a, int b) {
        return Math.max(a, b);
    }

    private Node smallRightRotation(Node y) {
        Node x = y.getLeft();
        Node T2 = x.getRight();

        x.setRight(y);
        y.setLeft(T2);

        y.setHeight(max(height(y.getLeft()), height(y.getRight())) + 1);
        x.setHeight(max(height(x.getLeft()), height(x.getRight())) + 1);

        return x;
    }

    private Node smallLeftRotation(Node x) {
        Node y = x.getRight();
        Node T2 = y.getLeft();

        y.setLeft(x);
        x.setRight(T2);

        x.setHeight(max(height(x.getLeft()), height(x.getRight())) + 1);
        y.setHeight(max(height(y.getLeft()), height(y.getRight())) + 1);

        return y;
    }

    private Node bigLeftRotation(Node x) {
        x.setRight(smallRightRotation(x.getRight()));
        return smallLeftRotation(x);
    }

    private Node bigRightRotation(Node y) {
        y.setLeft(smallLeftRotation(y.getLeft()));
        return smallRightRotation(y);
    }

    private int getBalance(Node N) {
        if (N == null)
            return 0;

        return height(N.getLeft()) - height(N.getRight());
    }

    protected Node insert(Node node, int key) {

        if (node == null)
            return (new Node(key));

        if (key < node.getKey())
            node.setLeft(insert(node.getLeft(), key));
        else if (key > node.getKey())
            node.setRight(insert(node.getRight(), key));
        else
            return node;

        node.setHeight( 1 + max(height(node.getLeft()),
                height(node.getRight())));

        int balance = getBalance(node);

        if (balance > 1 && key < node.getLeft().getKey())
            return smallRightRotation(node);

        if (balance < -1 && key > node.getRight().getKey())
            return smallLeftRotation(node);

        if (balance > 1 && key > node.getLeft().getKey()) {
            return bigRightRotation(node);
        }

        if (balance < -1 && key < node.getRight().getKey()) {
            return bigLeftRotation(node);
        }

        return node;
    }

    private Node minValueNode(Node node)
    {
        Node current = node;

        while (current.getLeft() != null)
            current = current.getLeft();

        return current;
    }

    protected Node delete(Node root, int key)
    {
        if (root == null)
            return root;

        if (key < root.getKey())
            root.setLeft(delete(root.getLeft(), key));

        else if (key > root.getKey())
            root.setRight(delete(root.getRight(), key));

        else
        {

            if ((root.getLeft() == null) || (root.getRight() == null))
            {
                Node temp = null;
                if (temp == root.getLeft())
                    temp = root.getRight();
                else
                    temp = root.getLeft();

                if (temp == null)
                {
                    temp = root;
                    root = null;
                }
                else
                    root = temp;
            }
            else
            {

                Node temp = minValueNode(root.getRight());

                root.setKey(temp.getKey());

                root.setRight(delete(root.getRight(), temp.getKey()));
            }
        }

        if (root == null)
            return root;

        root.setHeight(max(height(root.getLeft()), height(root.getRight())) + 1);

        return reBalance(root);
    }

    Node reBalance(Node node){
        int balance = getBalance(node);

        if (balance > 1 && getBalance(node.getLeft()) >= 0)
            return smallRightRotation(node);

        if (balance > 1 && getBalance(node.getLeft()) < 0)
        {
            return bigRightRotation(node);
        }

        if (balance < -1 && getBalance(node.getRight()) <= 0)
            return smallLeftRotation(node);

        if (balance < -1 && getBalance(node.getRight()) > 0)
        {
            return bigLeftRotation(node);
        }

        return node;
    }
}
