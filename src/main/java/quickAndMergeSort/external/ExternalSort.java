package quickAndMergeSort.external;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class ExternalSort {
    private final String pathA = "src/main/resources/v1-A.txt";
    private final String pathB = "src/main/resources/v1-B.txt";
    private RandomAccessFile readerA = new RandomAccessFile(pathA, "r");
    private RandomAccessFile readerB = new RandomAccessFile(pathB, "r");
    private String resultPath;
    private RandomAccessFile resultFileReader;
    private String current = pathA;

    public ExternalSort(String path) throws FileNotFoundException {
        this.resultFileReader =  new RandomAccessFile(path, "r");
        this.resultPath = path;
    }

    public void sort() throws IOException {
        split();

        if (readerA.length() == 0 || readerB.length() == 0) {
            return;
        }
        merge();
        sort();
    }

    public void split() throws IOException {
        SortUtils.cleanFiles(pathA, pathB);

        String number = resultFileReader.readLine();
        String previous = number;

        while (number != null) {
            if (Integer.parseInt(number) >= Integer.parseInt(previous)) {
                FileWriter.write(number, current);
                previous = number;
            } else {
                current = SortUtils.switchWriteSource(current, pathA, pathB);
                FileWriter.write(number, current);
                previous = number;
            }
            number = resultFileReader.readLine();
        }
        SortUtils.movePositionsToStart(resultFileReader);
    }

    public void merge() throws IOException {
        SortUtils.cleanFiles(resultPath);

        String numberA = readerA.readLine();
        String numberB = readerB.readLine();

        int A = Integer.parseInt(numberA);
        int B = Integer.parseInt(numberB);
        int previousA = A;
        int previousB = B;

        while (numberA != null && numberB != null) {

            if (A < B) {
                FileWriter.write(numberA, resultPath);
                previousA = A;
                numberA = readerA.readLine();
                if (numberA != null && (A = Integer.parseInt(numberA)) < previousA) {
                    FileWriter.write(numberB, resultPath);
                    previousB = B;
                    numberB = readerB.readLine();
                }
            } else {
                FileWriter.write(numberB, resultPath);
                previousB = B;
                numberB = readerB.readLine();
                if (numberB != null && (B = Integer.parseInt(numberB)) < previousB) {
                    FileWriter.write(numberA, resultPath);
                    previousA = A;
                    numberA = readerA.readLine();
                }
            }
        }

        SortUtils.writeLeftoversToCurrentFile(numberA, resultPath, readerA);
        SortUtils.writeLeftoversToCurrentFile(numberB, resultPath, readerB);
        SortUtils.movePositionsToStart(readerA, readerB);
    }

}
