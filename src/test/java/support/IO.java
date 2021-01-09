package support;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Function;

public class IO {

    public static List<String> readInputAsLines(String fileName) {
        return readInput(fileName, path -> {
            try {
                return Files.readAllLines(path);
            } catch (IOException e) {
                throw new RuntimeException("Error in reading input: " + e.getMessage(), e);
            }
        });
    }

    public static String readInputAsString(String fileName) {
        return readInput(fileName, path -> {
            try {
                return Files.readString(path).trim();
            } catch (IOException e) {
                throw new RuntimeException("Error in reading input: " + e.getMessage(), e);
            }
        });
    }

    private static <T> T readInput(String fileName, Function<Path, T> function) {
        var url = IO.class.getResource("/" + fileName);
        try {
            var file = Path.of(url.toURI());
            return function.apply(file);
        } catch (URISyntaxException e) {
            throw new RuntimeException("Error in reading input: " + e.getMessage(), e);
        }
    }
}
