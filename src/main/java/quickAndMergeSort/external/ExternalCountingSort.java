package quickAndMergeSort.external;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class ExternalCountingSort {
    private final String path = "src/main/resources/externalCountingSort/";
    private String resultPath;
    private RandomAccessFile resultFileReader;

    public ExternalCountingSort(String path) throws FileNotFoundException {
        this.resultFileReader = new RandomAccessFile(path, "r");
        this.resultPath = path;
    }

    public void sort(int t) throws IOException {
        RandomAccessFile[] files = new RandomAccessFile[t];

        for (int i = 1; i <= t; i++) {
            files[i - 1] = createFile(i);
        }

        String s;
        while ((s = resultFileReader.readLine()) != null) {
            files[Integer.parseInt(s) - 1].writeBytes(s + "\n");
        }

        SortUtils.movePositionsToStart(files);


        SortUtils.cleanFiles(path + "result.txt");
        RandomAccessFile result = new RandomAccessFile(path + "result.txt", "rw");
        for (RandomAccessFile ra : files) {
            while ((s = ra.readLine()) != null)
                result.writeBytes(s + "\n");
        }

        for (int i = 1; i <= t; i++) {
            FileWriter.cleanFile(path + i + ".txt");
        }
    }

    private RandomAccessFile createFile(int n) throws FileNotFoundException {
        return new RandomAccessFile(path + n + ".txt", "rw");
    }
}
