package quickAndMergeSort.external;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;

public class ExternalSortV2 {
    private final String pathA = "src/main/resources/v1-A.txt";
    private final String pathB = "src/main/resources/v1-B.txt";
    private final String pathC = "src/main/resources/v1-C.txt";
    private final String pathD = "src/main/resources/v1-D.txt";

    private String resultPath;
    private BufferedReader brResult;
    private String current;
    private RandomAccessFile raA = new RandomAccessFile(pathA, "r");
    private RandomAccessFile raB = new RandomAccessFile(pathB, "r");
    private RandomAccessFile raC = new RandomAccessFile(pathC, "r");
    private RandomAccessFile raD = new RandomAccessFile(pathD, "r");
    private RandomAccessFile getFromA;
    private RandomAccessFile getFromB;
    private String writeToA;
    private String writeToB;

    public ExternalSortV2(String resultPath) throws FileNotFoundException {
        this.brResult = new BufferedReader(new InputStreamReader(new FileInputStream(resultPath)));
        this.resultPath = resultPath;
    }

    public void sort() throws IOException {
        init();
        splitFiles();
        mergeFiles();
    }

    private void splitFiles() throws IOException {
        String value;

        while ((value = brResult.readLine()) != null) {
            FileWriter.write(value, current);
            current = SortUtils.switchWriteSource(current, pathA, pathB);
        }

        writeToA = pathC;
        writeToB = pathD;
    }

    private void mergeFiles() throws IOException {
        String stringFromFileA;
        String stringFromFileB;
        int step = 1;

        // do until one of source files will contains all sorted values
        while (getFromA.length() != 0 && getFromB.length() != 0) {
            stringFromFileA = getFromA.readLine();
            stringFromFileB = getFromB.readLine();

            // go through both source files
            while (stringFromFileA != null && stringFromFileB != null) {
                current = SortUtils.switchWriteSource(current, writeToA, writeToB);

                int i = 0;
                int j = 0;

                // go through step
                while (stringFromFileA != null && stringFromFileB != null && i < step && j < step) {
                    if (Integer.parseInt(stringFromFileA) < Integer.parseInt(stringFromFileB)) {
                        FileWriter.write(stringFromFileA, current);
                        stringFromFileA = getFromA.readLine();
                        i++;
                    } else if (Integer.parseInt(stringFromFileB) < Integer.parseInt(stringFromFileA)
                            || Integer.parseInt(stringFromFileB) == Integer.parseInt(stringFromFileA)) {
                        FileWriter.write(stringFromFileB, current);
                        stringFromFileB = getFromB.readLine();
                        j++;
                    }
                }

                while (stringFromFileA != null && i < step) {
                    FileWriter.write(stringFromFileA, current);
                    stringFromFileA = getFromA.readLine();
                    i++;
                }

                while (stringFromFileB != null && j < step) {
                    FileWriter.write(stringFromFileB, current);
                    stringFromFileB = getFromB.readLine();
                    j++;
                }
            }

            current = SortUtils.switchWriteSource(current, writeToA, writeToB);

            SortUtils.writeLeftoversToCurrentFile(stringFromFileA, current, getFromA);
            SortUtils.writeLeftoversToCurrentFile(stringFromFileB, current, getFromB);

            if (writeToA.equals(pathA) || writeToA.equals(pathB)) {
                SortUtils.cleanFiles(pathC, pathD);
                switchSources(pathC, pathD, raA, raB);
            } else if (writeToA.equals(pathC) || writeToA.equals(pathD)) {
                SortUtils.cleanFiles(pathA, pathB);
                switchSources(pathA, pathB, raC, raD);
            }

            SortUtils.movePositionsToStart(raA, raB, raC, raD);

            step = step * 2;
        }

        SortUtils.movePositionsToStart(raA, raB, raC, raD);
        SortUtils.cleanFiles(resultPath);
        SortUtils.copyLeftoversToResultFile(resultPath, raA, raB, raC, raD);
    }

    private void switchSources(String writeToA, String writeToB, RandomAccessFile getFromA, RandomAccessFile getFromB) {
        this.writeToA = writeToA;
        this.writeToB = writeToB;
        this.getFromA = getFromA;
        this.getFromB = getFromB;
    }

    private void init() {
        getFromA = raA;
        getFromB = raB;
        writeToA = pathA;
        writeToB = pathB;
        current = writeToA;

        SortUtils.cleanFiles(pathA, pathB, pathC, pathD);
    }
}


