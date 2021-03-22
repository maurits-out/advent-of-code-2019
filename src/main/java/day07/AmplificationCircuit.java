package day07;

import java.util.List;
import java.util.function.Function;

import static java.util.Comparator.naturalOrder;
import static java.util.List.of;
import static java.util.stream.Collectors.toList;
import static org.apache.commons.collections4.CollectionUtils.permutations;

public class AmplificationCircuit {

    private final String program;

    public AmplificationCircuit(String program) {
        this.program = program;
    }

    public int part1() {
        return findLargestOutputSignal(of(0, 1, 2, 3, 4), sequence -> amplify(sequence, false));
    }

    public int part2() {
        return findLargestOutputSignal(of(5, 6, 7, 8, 9), sequence -> amplify(sequence, true));
    }

    private Integer findLargestOutputSignal(List<Integer> phaseSettings, Function<List<Integer>, Integer> amplify) {
        return permutations(phaseSettings)
                .stream()
                .map(amplify)
                .max(naturalOrder())
                .orElseThrow();
    }

    private Integer amplify(List<Integer> phaseSettingSequence, boolean feedbackLoop) {
        List<Amplifier> amplifiers = phaseSettingSequence
                .stream()
                .map(phaseSetting -> new Amplifier(program, phaseSetting))
                .collect(toList());
        for (int i = 1; i < phaseSettingSequence.size(); i++) {
            amplifiers.get(i - 1).setNext(amplifiers.get(i));
        }
        if (feedbackLoop) {
            amplifiers.get(phaseSettingSequence.size() - 1).setNext(amplifiers.get(0));
        }
        return amplifiers.get(0).amplify(0);
    }
}
