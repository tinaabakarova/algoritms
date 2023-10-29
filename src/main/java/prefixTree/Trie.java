package prefixTree;

class Trie {
    static final int ALPHABET_SIZE = 26;

    class Node
    {
        Node[] childNodes = new Node[ALPHABET_SIZE];

        boolean isEnd;

        String value;

        Node(){
            isEnd = false;
            value = null;
            for (int i = 0; i < ALPHABET_SIZE; i++)
                childNodes[i] = null;
        }
    };

    Node root;

    public Trie() {
        root = new Node();
    }

    public void insert(String key, String value) {
        int level;
        int length = key.length();
        int index;

        Node trie = root;

        for (level = 0; level < length; level++)
        {
            index = key.charAt(level) - 'a';
            if (trie.childNodes[index] == null)
                trie.childNodes[index] = new Node();

            trie = trie.childNodes[index];
        }

        trie.isEnd = true;
        trie.value = value;
    }

    public boolean search(String key) {
        int level;
        int length = key.length();
        int index;
        Node trie = root;

        for (level = 0; level < length; level++)
        {
            index = key.charAt(level) - 'a';

            if (trie.childNodes[index] == null)
                return false;

            trie = trie.childNodes[index];
        }

        return (trie.isEnd);
    }

    public String get(String key) {
        int level;
        int length = key.length();
        int index;
        Node trie = root;

        if (!search(key)) return null;
        else {
            for (level = 0; level < length; level++) {
                index = key.charAt(level) - 'a';

                if (trie.childNodes[index] == null)
                    return null;

                trie = trie.childNodes[index];
            }
            return trie.value;
        }
    }

    static boolean isEmpty(Node root)
    {
        for (int i = 0; i < ALPHABET_SIZE; i++)
            if (root.childNodes[i] != null)
                return false;
        return true;
    }

    public Node remove(Node root, String key, int depth)
    {
        if (root == null)
            return null;

        if (depth == key.length()) {

            if (root.isEnd) {
                root.isEnd = false;
                root.value = null;
            }

            if (isEmpty(root)) {
                root = null;
            }

            return root;
        }

        int index = key.charAt(depth) - 'a';
        root.childNodes[index] =
                remove(root.childNodes[index], key, depth + 1);

        if (isEmpty(root) && !root.isEnd){
            root = null;
        }

        return root;
    }
}
