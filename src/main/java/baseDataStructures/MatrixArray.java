package baseDataStructures;

import java.util.Arrays;

public class MatrixArray<T> implements IArray<T> {
    T[][] array;
    int sizeY;
    int sizeX;
    int delta = 10;

    public MatrixArray(int delta) {
        this.sizeY = 1;
        this.sizeX = 0;
        this.array = (T[][]) new Object[sizeY][delta];
    }

    @Override
    public int size() {
        return sizeX;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public void put(T item) {
        int indexY = sizeX / delta;
        int indexX = sizeX % delta;

        if (!isEmpty() && (size() == array.length * delta)) {
            resize();
        }
        array[indexY][indexX] = item;
        sizeX++;
    }

    @Override
    public T get(int index) {
        int indexY = index / delta;
        int indexX = index % delta;
        return (T) array[indexY][indexX];
    }

    @Override
    public void add(T item, int index) {
        int indexY = index / delta;
        int indexX = index % delta;

        if (size() == array.length * delta)
            resize();

        for (int i = size(); i >= index ; i--) {
            array[i / delta][i % delta] = array[(i - 1) / delta][(i - 1) % delta];
        }
        array[indexY][indexX] = item;

    }

    @Override
    public T remove(int index) {

        T deletedResult = get(index);
        for (int i = index; i < size(); i++) {
            array[i / delta][i % delta] = array[(i + 1) / delta][(i + 1) % delta];
        }
        array[size() / delta][size() % delta] = null;

        return deletedResult;
    }

    private void resize() {
        T[][] newArray = (T[][]) new Object[sizeY + 1][delta];
        if (!isEmpty()) {
            for (int i = 0; i < sizeY; i++) {
                System.arraycopy(array[i], 0, newArray[i], 0, delta);
            }
        }
        array = newArray;
        sizeY++;
    }

    @Override
    public void printAll() {
        Arrays.stream(array).forEach(array -> Arrays.stream(array).forEach(f -> System.out.print(f + " ")));
        System.out.println();
    }
}
