package pyramidSorting;

import easySorting.BubbleSort;
import easySorting.InsertionSort;
import easySorting.ShellSort;

import java.util.Arrays;
import java.util.Random;
import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) {
        Integer[] array = new Integer[1000000];
        Random random = new Random();

        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(1000000);
        }

         HeapSort heapSort = new HeapSort();

         count(SelectionSort::sort, array, "SelectionSort::sort");
         count(heapSort::sort, array, "HeapSort::sort");
    }

    public static void count(Consumer<Integer[]> consumer, Integer[] array, String type) {
        Integer[] result = array.clone();
        long start = System.currentTimeMillis();
        consumer.accept(result);
        System.out.println("testSort: " + type + " " + (System.currentTimeMillis() - start));
     //   Arrays.stream(result).forEach(s -> System.out.print(s + " "));
     //   System.out.println();
    }
}
