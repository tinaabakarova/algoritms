package easySorting;

import java.util.Random;
import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) {
        Integer[] array = new Integer[1000];
        Random random = new Random();

        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(1000);
        }

         count(BubbleSort::sort, array, "BubbleSort::sort");
         count(BubbleSort::optimizedSort, array, "BubbleSort::optimizedSort");
         count(InsertionSort::sort, array, "InsertionSort::sort");
         count(InsertionSort::optimizedSort, array, "InsertionSort::optimizedSort");
         count(ShellSort::sort, array, "ShellSort::sort");
         count(ShellSort::optimizedCiuraSort, array, "ShellSort::optimizedCiuraSort");
         count(ShellSort::optimized3SmoothSort, array, "ShellSort::optimized3SmoothSort");
    }

    public static void count(Consumer<Integer[]> consumer, Integer[] array, String type) {
        Integer[] result = array.clone();
        long start = System.currentTimeMillis();
        consumer.accept(result);
        System.out.println("testSort: " + type + " " + (System.currentTimeMillis() - start));
        //Arrays.stream(array).forEach(s -> System.out.print(s + " "));
        //System.out.println();
    }
}
