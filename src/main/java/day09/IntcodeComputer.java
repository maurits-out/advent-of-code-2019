package day09;

import java.util.LinkedList;
import java.util.function.*;

import static java.lang.String.join;

class IntcodeComputer {

    private static final int OPCODE_ADD = 1;
    private static final int OPCODE_MULTIPLY = 2;
    private static final int OPCODE_INPUT = 3;
    private static final int OPCODE_OUTPUT = 4;
    private static final int OPCODE_JUMP_IF_TRUE = 5;
    private static final int OPCODE_JUMP_IF_FALSE = 6;
    private static final int OPCODE_LESS_THAN = 7;
    private static final int OPCODE_EQUALS = 8;
    private static final int OPCODE_ADJUST_RELATIVE_BASE = 9;
    private static final int OPCODE_HALT = 99;

    private final Memory memory;
    private final int input;

    IntcodeComputer(String program, int input) {
        this.memory = new Memory(program);
        this.input = input;
    }

    String run() {
        var ip = 0L;
        var relativeBase = 0L;
        var output = new LinkedList<String>();

        loop:
        while (true) {
            var opcode = memory.read(ip) % 100;
            switch ((int) opcode) {
                case OPCODE_ADD -> {
                    var outcome = calc(ip, relativeBase, Long::sum);
                    writeToMemory(ip, relativeBase, 3, outcome);
                    ip += 4;
                }
                case OPCODE_MULTIPLY -> {
                    var outcome = calc(ip, relativeBase, (n, m) -> n * m);
                    writeToMemory(ip, relativeBase, 3, outcome);
                    ip += 4;
                }
                case OPCODE_INPUT -> {
                    writeToMemory(ip, relativeBase, 1, input);
                    ip += 2;
                }
                case OPCODE_OUTPUT -> {
                    var value = getParameter(ip, relativeBase, 1);
                    output.add(String.valueOf(value));
                    ip += 2;
                }
                case OPCODE_JUMP_IF_TRUE -> ip = jump(ip, relativeBase, n -> n != 0);
                case OPCODE_JUMP_IF_FALSE -> ip = jump(ip, relativeBase, n -> n == 0);
                case OPCODE_LESS_THAN -> {
                    var outcome = compare(ip, relativeBase, (n, m) -> n < m);
                    writeToMemory(ip, relativeBase, 3, outcome);
                    ip += 4;
                }
                case OPCODE_EQUALS -> {
                    var outcome = compare(ip, relativeBase, Long::equals);
                    writeToMemory(ip, relativeBase, 3, outcome);
                    ip += 4;
                }
                case OPCODE_ADJUST_RELATIVE_BASE -> {
                    relativeBase += getParameter(ip, relativeBase, 1);
                    ip += 2;
                }
                case OPCODE_HALT -> {
                    break loop;
                }
                default -> throw new IllegalStateException("Unknown opcode: " + opcode);
            }
        }
        return join(",", output);
    }

    private int compare(long ip, long relativeBase, BiPredicate<Long, Long> condition) {
        var operand1 = getParameter(ip, relativeBase, 1);
        var operand2 = getParameter(ip, relativeBase, 2);
        return condition.test(operand1, operand2) ? 1 : 0;
    }

    private long jump(long ip, long relativeBase, LongPredicate jumpCondition) {
        var operand = getParameter(ip, relativeBase, 1);
        if (jumpCondition.test(operand)) {
            return getParameter(ip, relativeBase, 2);
        }
        return ip + 3;
    }

    private long calc(long ip, long relativeBase, LongBinaryOperator operator) {
        var operand1 = getParameter(ip, relativeBase, 1);
        var operand2 = getParameter(ip, relativeBase, 2);
        return operator.applyAsLong(operand1, operand2);
    }

    private long getParameter(long ip, long relativeBase, int parameterIndex) {
        var value = memory.read(ip + parameterIndex);
        var mode = parameterMode(ip, parameterIndex);
        return switch (mode) {
            case 0 -> memory.read(value);
            case 1 -> value;
            case 2 -> memory.read(relativeBase + value);
            default -> throw new IllegalStateException("Invalid parameter mode: " + mode);
        };
    }

    private void writeToMemory(long ip, long relativeBase, int parameterIndex, long value) {
        var address = memory.read(ip + parameterIndex);
        var mode = parameterMode(ip, parameterIndex);
        switch (mode) {
            case 0 -> memory.write(address, value);
            case 1 -> throw new IllegalStateException("Parameter in immediate mode!");
            case 2 -> memory.write(address + relativeBase, value);
            default -> throw new IllegalStateException("Invalid parameter mode: " + mode);
        }
    }

    private int parameterMode(long ip, int parameterIndex) {
        var mode = memory.read(ip) / 100;
        for (var i = parameterIndex; i > 1; i--) {
            mode /= 10;
        }
        return (int) (mode % 10);
    }
}
