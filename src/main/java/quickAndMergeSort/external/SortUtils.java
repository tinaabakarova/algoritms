package quickAndMergeSort.external;

import java.io.IOException;
import java.io.RandomAccessFile;

public class SortUtils {

    public static void cleanFiles(String... args) {
        for (String path: args) {
            FileWriter.cleanFile(path);
        }
    }

    public static String switchWriteSource(String current, String writeToA, String writeToB) {
        if (current.equals(writeToA)) {
            return writeToB;
        } else {
            return writeToA;
        }
    }

    public static void writeLeftoversToCurrentFile(String str, String currentPath, RandomAccessFile file) throws IOException {
        while (str != null) {
            FileWriter.write(str, currentPath);
            str = file.readLine();
        }
    }

    public static void movePositionsToStart(RandomAccessFile... args) throws IOException {
        for (RandomAccessFile file: args) {
            file.seek(0);
        }
    }

    public static void copyLeftoversToResultFile(String resultPath, RandomAccessFile... files) throws IOException {
        String str;

        for (RandomAccessFile file: files) {
            while ((str = file.readLine()) != null) {
                FileWriter.write(str, resultPath);
            }
        }
    }
}
