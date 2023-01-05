package quickAndMergeSort.external;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class FileWriter {

    public static void write(String i, String path) {
        try {
            Files.write(Paths.get(path), (i + "\n").getBytes(),
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND,
                    StandardOpenOption.WRITE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deleteIfExists(String path) {
        try {
            Files.deleteIfExists(Path.of(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void cleanFile(String path) {
        try {
            Files.write(Path.of(path), "".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
