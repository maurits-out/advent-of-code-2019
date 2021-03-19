package day07;

import java.util.List;

import static java.util.Comparator.naturalOrder;
import static java.util.List.of;
import static org.apache.commons.collections4.CollectionUtils.permutations;

public class AmplificationCircuit {

    private final String program;

    public AmplificationCircuit(String program) {
        this.program = program;
    }

    public int part1() {
        return permutations(of(0, 1, 2, 3, 4))
                .stream()
                .map(this::amplify)
                .max(naturalOrder())
                .orElseThrow();
    }

    private Integer amplify(List<Integer> phaseSettingSequence) {
        var output = 0;
        for (Integer phaseSetting : phaseSettingSequence) {
            IntcodeComputer computer = new IntcodeComputer(program);
            output = computer.run(phaseSetting, output);
        }
        return output;
    }
}
