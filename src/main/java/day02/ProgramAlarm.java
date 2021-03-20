package day02;

import org.apache.commons.lang3.tuple.Pair;

import static java.util.stream.IntStream.rangeClosed;

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
                .boxed().flatMap(noun -> rangeClosed(0, 99).mapToObj(verb -> Pair.of(noun, verb)))
                .filter(p -> runProgram(p.getLeft(), p.getRight()) == 19690720)
                .findAny()
                .map(p -> p.getLeft() * 100 + p.getRight())
                .orElseThrow();
    }

    private int runProgram(int noun, int verb) {
        IntcodeComputer computer = new IntcodeComputer(input);
        return computer.run(noun, verb);
    }
}
