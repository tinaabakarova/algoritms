package pyramidSorting;

public class HeapSort {
    Integer[] arr;

    public void sort(Integer[] arr) {
        this.arr = arr;

        for (int root = arr.length / 2 - 1; root >= 0; root--) {
            heapify(root, arr.length);
        }

        for (int j = arr.length - 1; j > 0; j--) {
            swap(0, j);
            heapify(0, j);
        }
    }

    private void heapify(int root, int size) {
        int l = 2 * root + 1;
        int r = l + 1;
        int x = root;

        if (l < size && arr[l] > arr[x]) x = l;
        if (r < size && arr[r] > arr[x]) x = r;
        if (x == root) return;

        swap(x, root);
        heapify(x, size);
    }

    private void swap(int x, int root) {
        int temp = arr[root];
        arr[root] = arr[x];
        arr[x] = temp;
    }
}
