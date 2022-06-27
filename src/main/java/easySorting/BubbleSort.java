package easySorting;

public class BubbleSort {
    public static void sort(Integer[] array) {
        for (int i = 0; i < array.length; i++)
            for (int j = 0; j < array.length - 1; j++)
                if (array[j] > array[j + 1]) {
                    int z = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = z;
                }

    }

    public static void optimizedSort(Integer[] array) {
        boolean flag;
        for (int i = 0; i < array.length; i++) {
            flag = false;
            for (int j = 0; j < array.length - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int z = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = z;
                    flag = true;
                }
            }
            if (!flag) break;
        }
    }
}
