package linear;

import java.util.Collections;
import java.util.Vector;

public class BucketSort {
    static void bucketSort(int[] arr, int n)
    {
        if (n <= 0)
            return;

        Vector<Integer>[] buckets = new Vector[n];

        for (int i = 0; i < n; i++) {
            buckets[i] = new Vector<Integer>();
        }

        for (int i = 0; i < n; i++) {
            int idx = arr[i];
            buckets[idx].add(arr[i]);
        }

        for (int i = 0; i < n; i++) {
            Collections.sort(buckets[i]);
        }

        int index = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < buckets[i].size(); j++) {
                arr[index++] = buckets[i].get(j);
            }
        }
    }

}
