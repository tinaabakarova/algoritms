package easySorting;

public class ShellSort {
    private static int[] ciuraGaps = {701, 301, 132, 57, 23, 10, 4, 1};
    private static int[] threeSmoothGaps = {3888,3456,3072,2916,2592,2304,2187,2048,1944,1728,1536,1458,1296,1152,1024,
            972,864,768,729,648,576,512,486,432,384,324,288,256,243,216,192,162,144,128,108,
            96,81,72,64,54,48,36,32,27,24,18,16,12,9,8,6,4,3,2,1};

    public static void sort(Integer[] array) {
        int n = array.length;

        for (int gap = n/2; gap > 0; gap /= 2)
        {
            for (int i = gap; i < n; i += 1)
            {
                int temp = array[i];

                int j;
                for (j = i; j >= gap && array[j - gap] > temp; j -= gap)
                    array[j] = array[j - gap];

                array[j] = temp;
            }
        }
    }

    private static void optimizedSort(Integer[] array, int[] gaps) {
        int n = array.length;

        for (int gap:gaps) {
            for (int i = gap; i < n; i+=1) {
                int temp = array[i];

                int j;

                for (j = i; j - gap >= 0 && array[j - gap] > temp; j -= gap)
                {
                    array[j] = array[j - gap];
                }

                array[j] = temp;
            }
        }
    }

    public static void optimized3SmoothSort(Integer[] array) {
        optimizedSort(array, threeSmoothGaps);
    }

    public static void optimizedCiuraSort(Integer[] array) {
        optimizedSort(array, ciuraGaps);
    }
}
