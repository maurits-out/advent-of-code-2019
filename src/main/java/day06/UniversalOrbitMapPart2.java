package day06;

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
        record State(String object, int transfers) {}

        var queue = new LinkedList<>(of(new State(START_OBJECT, 0)));
        var visited = new HashSet<>(of(START_OBJECT));
        while (true) {
            var state = queue.remove();
            if (state.object().equals(DESTINATION_OBJECT)) {
                return state.transfers() - 2;
            }
            adjacencyList.get(state.object()).stream().filter(p -> !visited.contains(p)).forEach(s -> {
                visited.add(s);
                queue.add(new State(s, state.transfers() + 1));
            });
        }
    }

    private Map<String, List<String>> parse(List<String> input) {
        record Orbit(String object1, String object2) {}

        var result = new HashMap<String, List<String>>();
        input.stream().map(s -> {
            var i = s.indexOf(')');
            return new Orbit(s.substring(0, i), s.substring(i + 1));
        }).forEach(p -> {
            result.computeIfAbsent(p.object1(), k -> new ArrayList<>()).add(p.object2());
            result.computeIfAbsent(p.object2(), k -> new ArrayList<>()).add(p.object1());
        });
        return result;
    }
}
