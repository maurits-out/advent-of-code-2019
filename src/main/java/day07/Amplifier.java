package day07;

public class Amplifier {

    private final IntcodeComputer computer;
    private Amplifier next;

    public Amplifier(String program, int phaseSetting) {
        this.computer = new IntcodeComputer(program, phaseSetting);
    }

    public void setNext(Amplifier next) {
        this.next = next;
    }

    public Integer amplify(int input) {
        computer.run(input);
        if (next == null || next.computer.isHalted()) {
            return computer.getOutput();
        }
        return next.amplify(computer.getOutput());
    }
}
