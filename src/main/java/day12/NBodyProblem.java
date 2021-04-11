package day12;

import java.util.function.Supplier;

import static java.lang.Integer.signum;
import static java.lang.Math.abs;
import static java.util.stream.IntStream.range;

public class NBodyProblem {

    public int part1(Supplier<int[][]> input, int iterations) {
        return simulate(input, new Part1(iterations));
    }

    public long part2(Supplier<int[][]> input) {
        return simulate(input, new Part2(input.get()));
    }

    private <T> T simulate(Supplier<int[][]> input, State<T> state) {
        var pos = input.get();
        var vel = range(0, pos.length).mapToObj(m -> new int[pos[0].length]).toArray(int[][]::new);

        while (!state.halt()) {
            // apply gravity
            for (var m = 0; m < pos.length; m++) {
                for (var n = 0; n < pos.length; n++) {
                    for (var a = 0; a < pos[m].length; a++) {
                        vel[m][a] += signum(pos[n][a] - pos[m][a]);
                    }
                }
            }
            // apply velocity
            for (var m = 0; m < pos.length; m++) {
                for (var a = 0; a < pos[m].length; a++) {
                    pos[m][a] += vel[m][a];
                }
            }
            state.update(pos, vel);
        }

        return state.getResult(pos, vel);
    }
}
