package easySorting;

public class InsertionSort {
    public static void sort(Integer[] array) {
        int n = array.length;
        for (int i = 1; i < n; ++i) {
            int key = array[i];
            int j = i - 1;

            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j = j - 1;
            }
            array[j + 1] = key;
        }
    }

    public static void optimizedSort(Integer[] array) {
        int n = array.length;
        int i, loc, j, selected;

        for (i = 1; i < n; ++i) {
            j = i - 1;
            selected = array[i];

            loc = binarySearch(array, selected, 0, j);

            while (j >= loc) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = selected;
        }
    }

    private static int binarySearch(Integer[] a, int item, int low, int high)
    {
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (item == a[mid])
                return mid + 1;
            else if (item > a[mid])
                low = mid + 1;
            else
                high = mid - 1;
        }

        return low;
    }
}
