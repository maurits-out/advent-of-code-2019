package day07;

import java.util.function.BiPredicate;
import java.util.function.IntBinaryOperator;
import java.util.function.IntPredicate;
import java.util.regex.Pattern;

public class IntcodeComputer {

    private static final Pattern SEPARATOR = Pattern.compile(",");

    private static final int OPCODE_ADD = 1;
    private static final int OPCODE_MULTIPLY = 2;
    private static final int OPCODE_INPUT = 3;
    private static final int OPCODE_OUTPUT = 4;
    private static final int OPCODE_JUMP_IF_TRUE = 5;
    private static final int OPCODE_JUMP_IF_FALSE = 6;
    private static final int OPCODE_LESS_THAN = 7;
    private static final int OPCODE_EQUALS = 8;

    private final int[] memory;
    private int output;

    public IntcodeComputer(String program) {
        this.memory = SEPARATOR.splitAsStream(program).mapToInt(Integer::valueOf).toArray();
    }

    public Integer run(int... inputs) {
        var ip = 0;
        var inputIndex = 0;
        while (memory[ip] != 99) {
            var opcode = memory[ip] % 100;
            switch (opcode) {
                case OPCODE_ADD -> {
                    memory[memory[ip + 3]] = calc(ip, Integer::sum);
                    ip += 4;
                }
                case OPCODE_MULTIPLY -> {
                    memory[memory[ip + 3]] = calc(ip, (n, m) -> n * m);
                    ip += 4;
                }
                case OPCODE_INPUT -> {
                    memory[memory[ip + 1]] = inputs[inputIndex++];
                    ip += 2;
                }
                case OPCODE_OUTPUT -> {
                    output = memory[memory[ip + 1]];
                    ip += 2;
                }
                case OPCODE_JUMP_IF_TRUE -> ip = jump(ip, n -> n != 0);
                case OPCODE_JUMP_IF_FALSE -> ip = jump(ip, n -> n == 0);
                case OPCODE_LESS_THAN -> {
                    memory[memory[ip + 3]] = compare(ip, (n, m) -> n < m);
                    ip += 4;
                }
                case OPCODE_EQUALS -> {
                    memory[memory[ip + 3]] = compare(ip, Integer::equals);
                    ip += 4;
                }
                default -> throw new IllegalStateException("Unknown opcode: " + opcode);
            }
        }
        return output;
    }

    private int compare(int ip, BiPredicate<Integer, Integer> condition) {
        return condition.test(parameter(ip, 1), parameter(ip, 2)) ? 1 : 0;
    }

    private int jump(int ip, IntPredicate jumpCondition) {
        return jumpCondition.test(parameter(ip, 1)) ? parameter(ip, 2) : ip + 3;
    }

    private int calc(int ip, IntBinaryOperator operator) {
        return operator.applyAsInt(parameter(ip, 1), parameter(ip, 2));
    }

    private int parameter(int ip, int parameterIndex) {
        var m = memory[ip + parameterIndex];
        return parameterMode(ip, parameterIndex) == 0 ? memory[m] : m;
    }

    private int parameterMode(int ip, int parameterIndex) {
        var mode = memory[ip] / 100;
        for (var i = parameterIndex; i > 1; i--) {
            mode /= 10;
        }
        return mode % 10;
    }
}
