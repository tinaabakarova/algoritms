package quickAndMergeSort;

import quickAndMergeSort.external.ExternalCountingSort;
import quickAndMergeSort.external.ExternalSort;
import quickAndMergeSort.external.ExternalSortV2;
import quickAndMergeSort.external.FileGenerator;

import java.io.IOException;
import java.util.Random;
import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) throws IOException {
        String path = "src/main/resources/file.txt";

        int[] array = new int[10000000];
        Random random = new Random();

        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(10);
        }

        Consumer<int[]> consumerQuick = (int[] arr) -> {
            QuickSort quickSort = new QuickSort(arr);
            quickSort.sort();
        };

        Consumer<int[]> consumerMerge = (int[] arr) -> {
            MergeSort mergeSort = new MergeSort(arr);
            mergeSort.sort();
        };

        int max = 10;

        Consumer<int[]> externalCounting = (int[] arr) -> {
            ExternalCountingSort externalCountingSort = null;
            try {
                externalCountingSort = new ExternalCountingSort(path);
                externalCountingSort.sort(max);
            } catch (IOException e) {
                e.printStackTrace();
            }
        };

        Consumer<int[]> external = (int[] arr) -> {
            ExternalSort externalSort = null;
            try {
                externalSort = new ExternalSort(path);
                externalSort.sort();
            } catch (IOException e) {
                e.printStackTrace();
            }
        };

        Consumer<int[]> externalV2 = (int[] arr) -> {
            ExternalSortV2 externalSort = null;
            try {
                externalSort = new ExternalSortV2(path);
                externalSort.sort();
            } catch (IOException e) {
                e.printStackTrace();
            }
        };

        count(consumerQuick, array, "quickSort.sort()");
        count(consumerMerge, array, "mergeSort.sort()");

        FileGenerator.generate(1, max, 100000, path);

        count(externalCounting, new int[0], "externalCounting.sort()");
        count(external, new int[0], "external.sort()");
        count(externalV2, new int[0], "externalV2.sort()");

    }

    public static void count(Consumer<int[]> consumer, int[] array, String type) {
        int[] result = array.clone();
        long start = System.currentTimeMillis();
        consumer.accept(result);
        System.out.println("testSort: " + type + " " + (System.currentTimeMillis() - start));
        //       Arrays.stream(result).forEach(s -> System.out.print(s + " "));
        System.out.println();
    }
}
