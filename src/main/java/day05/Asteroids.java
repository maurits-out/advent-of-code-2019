package day05;

public class Asteroids {

	private final String program;

	public Asteroids(String program) {
		this.program = program;
	}

	public int part1() {
		return run(1);
	}

	public int part2() {
		return run(5);
	}

	private int run(int input) {
		var computer = new IntcodeComputer(program, input);
		return computer.run();
	}
}
