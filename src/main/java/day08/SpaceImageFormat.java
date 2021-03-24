package day08;

import static java.util.Comparator.comparingLong;
import static java.util.stream.Collectors.joining;
import static java.util.stream.IntStream.range;

public class SpaceImageFormat {

    private static final int IMAGE_WIDTH = 25;
    private static final int IMAGE_HEIGHT = 6;
    private static final int IMAGE_SIZE = IMAGE_WIDTH * IMAGE_HEIGHT;
    private static final char BLACK = '0';
    private static final char WHITE = '1';
    private static final char TRANSPARENT = '2';

    private final char[] input;

    public SpaceImageFormat(String input) {
        this.input = input.toCharArray();
    }

    long part1() {
        int layer = range(0, input.length / IMAGE_SIZE)
                .boxed()
                .min(comparingLong(l -> countColor(l, BLACK)))
                .orElseThrow();
        return countColor(layer, WHITE) * countColor(layer, TRANSPARENT);
    }

    void part2() {
        String image = range(0, IMAGE_SIZE)
                .mapToObj(this::color)
                .map(c -> c == WHITE ? "*" : " ")
                .collect(joining());
        range(0, image.length() / IMAGE_WIDTH)
                .mapToObj(row -> image.substring(row * IMAGE_WIDTH, (row + 1) * IMAGE_WIDTH))
                .forEach(System.out::println);
    }

    private char color(int pixel) {
        return range(0, input.length / IMAGE_SIZE)
                .mapToObj(l -> input[(l * IMAGE_SIZE) + pixel])
                .filter(c -> c != TRANSPARENT)
                .findFirst().orElseThrow();
    }

    private long countColor(int layer, char color) {
        return range(layer * IMAGE_SIZE, (layer + 1) * IMAGE_SIZE).filter(i -> input[i] == color).count();
    }
}
