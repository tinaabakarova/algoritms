package linear;

public class CountingSort {
    public static void sort(int[] arr, int m) {
        int n = arr.length;

        int[] output = new int[n];

        int[] count = new int[m];
        for (int i = 0; i < m; ++i)
            count[i] = 0;

        for (int i = 0; i < n; ++i)
            ++count[arr[i]];

        for (int i = 1; i <= m-1; ++i)
            count[i] += count[i - 1];

        for (int i = n - 1; i >= 0; i--) {
            output[count[arr[i]] - 1] = arr[i];
            --count[arr[i]];
        }

        System.arraycopy(output, 0, arr, 0, n);
    }
}
