package day12;

import static java.lang.Math.abs;
import static java.util.stream.IntStream.of;
import static java.util.stream.IntStream.range;

class Part2 implements State<Long> {

    private final int[][] initial;
    private final int[] iterations;
    private final boolean[] found;

    Part2(int[][] initialPositions) {
        this.initial = initialPositions;
        this.iterations = new int[initialPositions[0].length];
        this.found = new boolean[initialPositions[0].length];
    }

    @Override
    public void update(int[][] pos, int[][] vel) {
        range(0, pos[0].length)
                .filter(a -> !found[a])
                .forEach(a -> {
                    iterations[a]++;
                    found[a] = range(0, pos.length)
                            .allMatch(m -> pos[m][a] == initial[m][a] && vel[m][a] == 0);
                });
    }

    @Override
    public boolean halt() {
        return range(0, found.length)
                .allMatch(i -> found[i]);
    }

    @Override
    public Long getResult(int[][] pos, int[][] vel) {
        return of(iterations)
                .mapToLong(i -> i)
                .reduce(this::lcd)
                .orElseThrow();
    }

    private long lcd(long a, long b) {
        return abs(a * b) / gcd(a, b);
    }

    private long gcd(long a, long b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }
}
