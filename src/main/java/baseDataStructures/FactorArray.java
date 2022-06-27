package baseDataStructures;

import java.util.Arrays;

public class FactorArray<T> implements IArray<T> {
    T[] array;
    int size;

    public FactorArray() {
        this.array = (T[])new Object[10];
        this.size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public void put(T item) {
        if (size() == array.length)
            resize();
        array[size] = item;
        size++;
    }

    @Override
    public T get(int index) {
        return array[index];
    }

    @Override
    public void add(T item, int index) {
        if (size() == array.length)
            resize();

        T[] newArray =  (T[])new Object[array.length];
        System.arraycopy(array, 0, newArray, 0, size() - index);
        newArray[index] = item;
        System.arraycopy(array, index, newArray, index + 1, size() - index);
        array = newArray;
        size++;
    }

    @Override
    public T remove(int index) {
        T[] newArray =  (T[])new Object[array.length];
        System.arraycopy(array, 0, newArray, 0, size() - index - 1);
        System.arraycopy(array, index + 1, newArray, index, size() - index - 1) ;
        T deletedResult = array[index];
        array = newArray;
        return deletedResult;
    }

    private void resize() {
        T[] newArray =  (T[])new Object[size() * 2 + 1];
        if (!isEmpty())
            System.arraycopy(array, 0, newArray, 0, size());
        array = newArray;
    }


    @Override
    public void printAll() {
        Arrays.stream(array).forEach(f -> System.out.print(f + " "));
        System.out.println();
    }
}
