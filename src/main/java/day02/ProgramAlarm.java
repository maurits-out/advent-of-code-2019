package day02;

import java.util.Map;
import java.util.function.BinaryOperator;
import java.util.regex.Pattern;

import static java.util.stream.IntStream.rangeClosed;
import static support.Pair.pair;

public class ProgramAlarm {

    private static final Map<Integer, BinaryOperator<Integer>> OPERATORS = Map.of(1, Integer::sum, 2, (x, y) -> x * y);

    private final String input;

    public ProgramAlarm(String input) {
        this.input = input;
    }

    public int part1() {
        return runProgram(12, 2);
    }

    public int part2() {
        return rangeClosed(0, 99).boxed().flatMap(noun -> rangeClosed(0, 99).mapToObj(verb -> pair(noun, verb)))
                .filter(p -> runProgram(p.first, p.second) == 19690720)
                .findAny().map(p -> p.first * 100 + p.second)
                .orElseThrow();
    }

    private int runProgram(int noun, int verb) {
        var memory = createInitialState(noun, verb);
        var ip = 0;
        while (memory[ip] != 99) {
            applyFunction(memory, ip, OPERATORS.get(memory[ip]));
            ip += 4;
        }
        return memory[0];
    }

    private void applyFunction(int[] memory, int ip, BinaryOperator<Integer> function) {
        var par1 = memory[ip + 1];
        var par2 = memory[ip + 2];
        var par3 = memory[ip + 3];
        memory[par3] = function.apply(memory[par1], memory[par2]);
    }

    private int[] createInitialState(int noun, int verb) {
        var program = Pattern.compile(",").splitAsStream(input).mapToInt(Integer::valueOf).toArray();
        program[1] = noun;
        program[2] = verb;
        return program;
    }
}
