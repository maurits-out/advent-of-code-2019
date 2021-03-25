package day02;

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
        record Tuple(int noun, int verb) {}

        return rangeClosed(0, 99)
                .boxed().flatMap(noun -> rangeClosed(0, 99).mapToObj(verb -> new Tuple(noun, verb)))
                .filter(p -> runProgram(p.noun(), p.verb()) == 19690720)
                .findAny()
                .map(p -> p.noun() * 100 + p.verb())
                .orElseThrow();
    }

    private int runProgram(int noun, int verb) {
        IntcodeComputer computer = new IntcodeComputer(input);
        return computer.run(noun, verb);
    }
}
