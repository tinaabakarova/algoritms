package linear;

import java.io.IOException;
import java.util.Random;
import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) throws IOException {

        int[] array = new int[1000000000];
        Random random = new Random();

        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(65536);

        }

        Consumer<int[]> consumerBucket = (int[] arr) -> {
            BucketSort.bucketSort(arr, 65536);
        };

        Consumer<int[]> consumerCounting = (int[] arr) -> {
            CountingSort.sort(arr, 65536);
        };

        Consumer<int[]> consumerRadix = (int[] arr) -> {
            RadixSort.radixsort(arr, 65536);
        };

        count(consumerBucket, array, "BucketSort.sort()");
        count(consumerCounting, array, "CountingSort.sort()");
        count(consumerRadix, array, "RadixSort.sort()");

    }

    public static void count(Consumer<int[]> consumer, int[] array, String type) {
        int[] result = array.clone();
        long start = System.currentTimeMillis();
        consumer.accept(result);
        System.out.println("testSort: " + type + " " + (System.currentTimeMillis() - start));
       // Arrays.stream(result).forEach(s -> System.out.print(s + " "));
        System.out.println();
    }

}

