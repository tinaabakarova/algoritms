package hashTables;

public class Map {
    int capacity;
    int size;
    Node[] nodes;
    Node dummy;

    public Map() {
        this.capacity = 20;
        this.size = 0;
        this.nodes = new Node[this.capacity];
        this.dummy = new Node(-1, -1);
    }

    public int hashCode(int key) {
        return key % this.capacity;
    }

    public int getIndexWithQuadProbing(int value, int index) {
        return hashCode(value) + index * index;
    }

    public void resize() {
        Node[] temp = this.nodes;
        this.nodes = new Node[(int) (this.capacity * 1.5)];
        System.arraycopy(temp, 0, this.nodes, 0, temp.length);
    }

    public void insert(int key, int value) {
        Node temp = new Node(key, value);

        int index = hashCode(key);
        int count = 0;

        while (this.nodes[index] != null && this.nodes[index].key != key && this.nodes[index].key != -1) {
            index = getIndexWithQuadProbing(key, count);
            count++;
            index %= this.capacity;
        }

        if (this.nodes[index] == null || this.nodes[index].key == -1) {
            this.size++;
        }
        this.nodes[index] = temp;

        if (this.size > this.capacity / 2) resize();
    }

    public int delete(int key) {
        int index = hashCode(key);
        int count = 0;

        while (this.nodes[index] != null) {
            if (this.nodes[index].key == key) {
                Node temp = this.nodes[index];
                this.nodes[index] = this.dummy;
                this.size--;
                return temp.value;
            }

            if (count > this.capacity) {
                return -1;
            }

            index = getIndexWithQuadProbing(key, count);
            count++;
            index %= this.capacity;
        }
        return -1;
    }

    public int get(int key) {
        int index = hashCode(key);
        int count = 0;

        while (this.nodes[index] != null) {

            if (count > this.capacity) {
                return -1;
            }

            if (this.nodes[index].key == key) {
                return this.nodes[index].value;
            }
            index = getIndexWithQuadProbing(key, count);
            count++;
            index %= this.capacity;
        }

        return 0;
    }

    public int sizeofMap() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public void display() {
        for (int i = 0; i < this.capacity; i++) {
            if (this.nodes[i] != null && this.nodes[i].key != -1) {
                System.out.println("key = " + this.nodes[i].key + " value = " + this.nodes[i].value + " index = " + i);
            }
        }
    }
}
