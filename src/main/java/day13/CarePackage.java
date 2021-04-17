package day13;

class CarePackage {

    private final String program;

    public CarePackage(String program) {
        this.program = program;
    }

    int part1() {
        var counter = new BlockCounter();
        var computer = new IntcodeComputer(program, counter);
        computer.run();
        return counter.getBlockCount();
    }

    long part2() {
        var handler = new ScoreHandler();
        var computer = new IntcodeComputer(program, handler);
        computer.setMemory(0, 2);
        computer.run();
        return handler.getScore();
    }
}
