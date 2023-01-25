package quickAndMergeSort.external;

import java.util.Random;

public class FileGenerator {
    public static void generate(int min, int max, int n, String path) {
        FileWriter.deleteIfExists(path);

        for (int i = 0; i < n; i++) {
            FileWriter.write(String.valueOf((int)Math.floor(Math.random()*(max-min+1)+min)), path);
        }
    }
}
