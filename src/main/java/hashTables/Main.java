package hashTables;

public class Main {
    public static void main(String[] args) {
        Map h = new Map();
        h.insert(1, 1);
        h.insert(2, 2);
        h.insert(2, 3);
        h.insert(22, 3);
        h.insert(42, 4);
        h.insert(62, 4);

        h.delete(22);
        h.delete(42);
        h.delete(62);
        h.display();
        System.out.println(h.sizeofMap());
        System.out.println(h.delete(2));
        System.out.println(h.sizeofMap());
        System.out.println(h.isEmpty());
        System.out.println(h.get(2));
    }
}
