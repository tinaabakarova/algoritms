package baseDataStructures;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        Integer count = 250000;
        Integer item = 100000;
        Integer index = 555;
        
        IArray<Integer> single = new SingleArray<>();
        IArray<Integer> vector = new VectorArray<>();
        IArray<Integer> factor = new FactorArray<>();
        IArray<Integer> matrix = new MatrixArray<>(10);
        List<Integer> list = new ArrayListWrap<>();

        testPut(single, count);
        testPut(vector, count);
        testPut(factor, count);
        testPut(matrix, count);
        testPut(list, count);
       
        countTime(single, item, index);
        countTime(vector, item, index);
        countTime(factor, item, index);
        countTime(matrix, item, index);
        countTime(list, item, index);
    }

    private static void testPut(IArray<Integer> array, int total) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < total; i++) {
            array.put(i);
        }

        System.out.println(array + " testPut:putCount " + (System.currentTimeMillis() - start));
    }

    private static void testPut(List<Integer> array, int total) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < total; i++) {
            array.add(i);
        }

        System.out.println(array + " testPut:putCount " + (System.currentTimeMillis() - start));
    }

    private static void countTime(IArray<Integer> array, Integer item, Integer index) {
        long start = System.currentTimeMillis();
        array.add(item, index);
        System.out.println(array + " testPut:add " + (System.currentTimeMillis() - start));
        start = System.currentTimeMillis();
        array.remove(index);
        System.out.println(array + " testPut:remove  " + (System.currentTimeMillis() - start));
        start = System.currentTimeMillis();
        array.put(item);
        System.out.println(array + " testPut:put " + (System.currentTimeMillis() - start));
        start = System.currentTimeMillis();
        array.get(index);
        System.out.println(array + " testPut:get " + (System.currentTimeMillis() - start));
    }

    private static void countTime(List<Integer> array, Integer item, Integer index) {
        long start = System.currentTimeMillis();
        array.add(item, index);
        System.out.println(array + " testPut:add " + (System.currentTimeMillis() - start));
        start = System.currentTimeMillis();
        array.remove(index);
        System.out.println(array + " testPut:remove  " + (System.currentTimeMillis() - start));
        start = System.currentTimeMillis();
        array.add(item);
        System.out.println(array + " testPut:put " + (System.currentTimeMillis() - start));
        start = System.currentTimeMillis();
        array.get(index);
        System.out.println(array + " testPut:get " + (System.currentTimeMillis() - start));
    }
}
