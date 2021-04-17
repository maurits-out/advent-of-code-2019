package day13;

import java.util.function.BiPredicate;
import java.util.function.IntBinaryOperator;
import java.util.function.IntPredicate;

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
    private final IOHandler ioHandler;
    private int ip = 0;
    private int relativeBase = 0;

    IntcodeComputer(String program, IOHandler ioHandler) {
        this.memory = new Memory(program);
        this.ioHandler = ioHandler;
    }

    void setMemory(int address, int value) {
        memory.write(address, value);
    }

    void run() {
        var halted = false;
        while (!halted) {
            var opcode = memory.read(ip) % 100;
            switch (opcode) {
                case OPCODE_ADD -> {
                    var outcome = calc(ip, relativeBase, Integer::sum);
                    writeToMemory(ip, relativeBase, 3, outcome);
                    ip += 4;
                }
                case OPCODE_MULTIPLY -> {
                    var outcome = calc(ip, relativeBase, (n, m) -> n * m);
                    writeToMemory(ip, relativeBase, 3, outcome);
                    ip += 4;
                }
                case OPCODE_INPUT -> {
                    var input = ioHandler.getInput();
                    writeToMemory(ip, relativeBase, 1, input);
                    ip += 2;
                }
                case OPCODE_OUTPUT -> {
                    var value = getParameter(ip, relativeBase, 1);
                    ioHandler.output(value);
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
                    var outcome = compare(ip, relativeBase, Integer::equals);
                    writeToMemory(ip, relativeBase, 3, outcome);
                    ip += 4;
                }
                case OPCODE_ADJUST_RELATIVE_BASE -> {
                    relativeBase += getParameter(ip, relativeBase, 1);
                    ip += 2;
                }
                case OPCODE_HALT -> halted = true;
                default -> throw new IllegalStateException("Unknown opcode: " + opcode);
            }
        }
    }

    private int compare(int ip, int relativeBase, BiPredicate<Integer, Integer> condition) {
        var operand1 = getParameter(ip, relativeBase, 1);
        var operand2 = getParameter(ip, relativeBase, 2);
        return condition.test(operand1, operand2) ? 1 : 0;
    }

    private int jump(int ip, int relativeBase, IntPredicate jumpCondition) {
        var operand = getParameter(ip, relativeBase, 1);
        if (jumpCondition.test(operand)) {
            return getParameter(ip, relativeBase, 2);
        }
        return ip + 3;
    }

    private int calc(int ip, int relativeBase, IntBinaryOperator operator) {
        var operand1 = getParameter(ip, relativeBase, 1);
        var operand2 = getParameter(ip, relativeBase, 2);
        return operator.applyAsInt(operand1, operand2);
    }

    private int getParameter(int ip, int relativeBase, int parameterIndex) {
        var value = memory.read(ip + parameterIndex);
        var mode = parameterMode(ip, parameterIndex);
        return switch (mode) {
            case 0 -> memory.read(value);
            case 1 -> value;
            case 2 -> memory.read(relativeBase + value);
            default -> throw new IllegalStateException("Invalid parameter mode: " + mode);
        };
    }

    private void writeToMemory(int ip, int relativeBase, int parameterIndex, int value) {
        var address = memory.read(ip + parameterIndex);
        var mode = parameterMode(ip, parameterIndex);
        switch (mode) {
            case 0 -> memory.write(address, value);
            case 1 -> throw new IllegalStateException("Parameter in immediate mode!");
            case 2 -> memory.write(address + relativeBase, value);
            default -> throw new IllegalStateException("Invalid parameter mode: " + mode);
        }
    }

    private int parameterMode(int ip, int parameterIndex) {
        var mode = memory.read(ip) / 100;
        for (var i = parameterIndex; i > 1; i--) {
            mode /= 10;
        }
        return mode % 10;
    }
}
