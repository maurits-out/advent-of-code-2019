package day06;

import java.util.*;

import static java.util.List.of;
import static support.Pair.pair;

public class UniversalOrbitMapPart2 {

    private static final String START_OBJECT = "YOU";
    private static final String DESTINATION_OBJECT = "SAN";

    private final Map<String, List<String>> adjacencyList;

    public UniversalOrbitMapPart2(List<String> input) {
        adjacencyList = parse(input);
    }

    public int part2() {
        var queue = new LinkedList<>(of(pair(START_OBJECT, 0)));
        var visited = new HashSet<>(of(START_OBJECT));
        while (true) {
            var state = queue.remove();
            var object = state.first;
            var transfers = state.second;
            if (object.equals(DESTINATION_OBJECT)) {
                return transfers - 2;
            }
            adjacencyList.get(object).stream().filter(p -> !visited.contains(p)).forEach(s -> {
                visited.add(s);
                queue.add(pair(s, transfers + 1));
            });
        }
    }

    private Map<String, List<String>> parse(List<String> input) {
        var result = new HashMap<String, List<String>>();
        input.stream().map(s -> {
            var i = s.indexOf(')');
            return pair(s.substring(0, i), s.substring(i + 1));
        }).forEach(p -> {
            result.computeIfAbsent(p.first, k -> new ArrayList<>()).add(p.second);
            result.computeIfAbsent(p.second, k -> new ArrayList<>()).add(p.first);
        });
        return result;
    }
}
