package day09;

public class SensorBoost {

    private final String program;

    public SensorBoost(String program) {
        this.program = program;
    }

    public String part1() {
        return execute(1);
    }

    public String part2() {
        return execute(2);
    }

    private String execute(int input) {
        IntcodeComputer computer = new IntcodeComputer(program, input);
        return computer.run();
    }
}
