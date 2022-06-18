package baseDataStructures;

public interface IArray<T> {
    int size();
    boolean isEmpty();
    void put(T item);
    T get(int index);
    void add(T item, int index);
    T remove(int index);
    void printAll();
}
