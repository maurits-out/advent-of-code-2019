package day12;

import static java.util.stream.IntStream.of;
import static java.util.stream.IntStream.range;

class Part1 implements State<Integer> {

    private final int numIterations;
    private int iteration = 0;

    Part1(int numIterations) {
        this.numIterations = numIterations;
    }

    @Override
    public void update(int[][] pos, int[][] vel) {
        iteration++;
    }

    @Override
    public boolean halt() {
        return iteration == numIterations;
    }

    @Override
    public Integer getResult(int[][] pos, int[][] vel) {
        return range(0, pos.length)
                .map(m -> sumAbsoluteValues(pos[m]) * sumAbsoluteValues(vel[m]))
                .sum();
    }

    private int sumAbsoluteValues(int... values) {
        return of(values)
                .map(Math::abs)
                .sum();
    }
}
