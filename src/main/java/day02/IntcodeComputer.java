package day02;

import java.util.Map;
import java.util.function.BinaryOperator;
import java.util.regex.Pattern;

class IntcodeComputer {

    private static final Map<Integer, BinaryOperator<Integer>> OPERATORS =
            Map.of(1, Integer::sum, 2, (x, y) -> x * y);

    private final String program;
    private int[] memory;

    IntcodeComputer(String program) {
        this.program = program;
    }

    int run(int noun, int verb) {
        init(noun, verb);
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

    private void init(int noun, int verb) {
        memory = Pattern.compile(",").splitAsStream(program).mapToInt(Integer::valueOf).toArray();
        memory[1] = noun;
        memory[2] = verb;
    }
}
