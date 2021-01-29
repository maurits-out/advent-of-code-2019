package day02;

import static java.util.stream.IntStream.rangeClosed;
import static support.Pair.pair;

public class ProgramAlarm {

    private final String input;

    public ProgramAlarm(String input) {
        this.input = input;
    }

    public int part1() {
        return runProgram(12, 2);
    }

    public int part2() {
        return rangeClosed(0, 99)
                .boxed().flatMap(noun -> rangeClosed(0, 99).mapToObj(verb -> pair(noun, verb)))
                .filter(p -> runProgram(p.first, p.second) == 19690720)
                .findAny()
                .map(p -> p.first * 100 + p.second)
                .orElseThrow();
    }

    private int runProgram(int noun, int verb) {
        IntcodeComputer computer = new IntcodeComputer(input);
        return computer.run(noun, verb);
    }
}
