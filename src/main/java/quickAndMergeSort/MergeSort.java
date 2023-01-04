package quickAndMergeSort;

public class MergeSort {
    int[] arr;

    public MergeSort(int[] arr) {
        this.arr = arr;
    }

    public void sort() {
        sort(0, arr.length - 1);
    }

    private void sort(int l, int r) {
        if(l >= r) return;

        int m = (l + r) / 2;
        sort(l, m);
        sort(m +1, r);
        merge(l, m, r);
    }

    private void merge(int l, int m, int r) {
        int[] temp = new int[r - l + 1];

        int a = l;
        int b = m + 1;
        int c = 0;

        while (a <= m && b <= r) {
            if (arr[a] < arr[b])
                temp[c++] = arr[a++];
            else
                temp[c++] = arr[b++];
        }

        while (a <= m) {
           temp[c++] = arr[a++];
        }
        while (b <= r) {
            temp[c++] = arr[b++];
        }

        for (int i = l; i <= r; i++) {
            arr[i] = temp[i - l];
        }

    }
}
