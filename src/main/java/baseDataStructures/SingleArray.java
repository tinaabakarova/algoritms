package baseDataStructures;

import java.util.Arrays;

public class SingleArray<T> implements IArray<T>{
    private T[] array;

    public SingleArray() {
        this.array = (T[])new Object[0];
    }

    @Override
    public int size() {
        return array.length;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public void put(T item) {
        resize();
        array[size()-1] = item;
    }

    @Override
    public T get(int index) {
        return array[index];
    }

    @Override
    public void add(T item, int index) {
        T[] newArray =  (T[])new Object[size() + 1];
        System.arraycopy(array, 0, newArray, 0, size() - index);
        newArray[index] = item;
        System.arraycopy(array, index, newArray, index + 1, size() - index);
        array = newArray;
    }

    @Override
    public T remove(int index) {
        T[] newArray =  (T[])new Object[size() - 1];
        System.arraycopy(array, 0, newArray, 0, size() - index - 1);
        System.arraycopy(array, index + 1, newArray, index, size() - index - 1) ;
        T deletedResult = array[index];
        array = newArray;
        return deletedResult;
    }

    private void resize() {
        T[] newArray =  (T[])new Object[size() + 1];
        System.arraycopy(array, 0, newArray, 0, size());
        array = newArray;
    }

    @Override
    public void printAll() {
        Arrays.stream(array).forEach(f -> System.out.print(f + " "));
        System.out.println();
    }
}
