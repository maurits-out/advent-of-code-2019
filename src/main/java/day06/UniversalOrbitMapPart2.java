package day06;

import org.apache.commons.lang3.tuple.Pair;

import java.util.*;

import static java.util.List.of;

class UniversalOrbitMapPart2 {

    private static final String START_OBJECT = "YOU";
    private static final String DESTINATION_OBJECT = "SAN";

    private final Map<String, List<String>> adjacencyList;

    UniversalOrbitMapPart2(List<String> input) {
        adjacencyList = parse(input);
    }

    int part2() {
        var queue = new LinkedList<>(of(Pair.of(START_OBJECT, 0)));
        var visited = new HashSet<>(of(START_OBJECT));
        while (true) {
            var state = queue.remove();
            var object = state.getLeft();
            var transfers = state.getRight();
            if (object.equals(DESTINATION_OBJECT)) {
                return transfers - 2;
            }
            adjacencyList.get(object).stream().filter(p -> !visited.contains(p)).forEach(s -> {
                visited.add(s);
                queue.add(Pair.of(s, transfers + 1));
            });
        }
    }

    private Map<String, List<String>> parse(List<String> input) {
        var result = new HashMap<String, List<String>>();
        input.stream().map(s -> {
            var i = s.indexOf(')');
            return Pair.of(s.substring(0, i), s.substring(i + 1));
        }).forEach(p -> {
            result.computeIfAbsent(p.getLeft(), k -> new ArrayList<>()).add(p.getRight());
            result.computeIfAbsent(p.getRight(), k -> new ArrayList<>()).add(p.getLeft());
        });
        return result;
    }
}
