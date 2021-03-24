package day08;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Arrays.copyOfRange;
import static java.util.Comparator.comparingLong;
import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.range;

public class SpaceImageFormat {

    private static final int IMAGE_WIDTH = 25;
    private static final int IMAGE_HEIGHT = 6;

    private final char[] input;

    public SpaceImageFormat(String input) {
        this.input = input.toCharArray();
    }

    long part1() {
        Layer layer = layers().min(comparingLong(l -> l.count('0'))).orElseThrow();
        return layer.count('1') * layer.count('2');
    }

    void part2() {
        Layer[] layers = layers().toArray(Layer[]::new);
        String result = range(0, IMAGE_HEIGHT * IMAGE_WIDTH).mapToObj(i -> color(layers, i)).map(c -> c == '1' ? "*" : " ").collect(Collectors.joining());
        range(0, result.length() / IMAGE_WIDTH)
                .mapToObj(i -> result.substring(i * IMAGE_WIDTH, (i + 1) * IMAGE_WIDTH))
                .forEach(System.out::println);
    }

    private char color(Layer[] layers, int index) {
        return Arrays.stream(layers).map(l -> l.getColor(index)).filter(c -> c != '2').findFirst().orElseThrow();
    }

    private Stream<Layer> layers() {
        var imageSize = IMAGE_WIDTH * IMAGE_HEIGHT;
        return range(0, input.length / imageSize)
                .mapToObj(i -> copyOfRange(input, i * imageSize, (i + 1) * imageSize))
                .map(Layer::new);
    }

    private static class Layer {

        private final char[] pixels;

        public Layer(char[] pixels) {
            this.pixels = pixels;
        }

        public long count(char color) {
            return range(0, pixels.length)
                    .mapToObj(i -> pixels[i])
                    .filter(c -> c.equals(color))
                    .count();
        }

        public char getColor(int i) {
            return pixels[i];
        }

    }


}
