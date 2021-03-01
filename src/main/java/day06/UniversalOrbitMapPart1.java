package day06;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toMap;
import static support.Pair.pair;

class UniversalOrbitMapPart1 {

    private final Map<String, String> map;

    UniversalOrbitMapPart1(List<String> input) {
        this.map = parse(input);
    }

    int part1() {
        return map.keySet().stream().mapToInt(this::countOrbits).sum();
    }

    private int countOrbits(String object) {
        if (!map.containsKey(object)) {
            return 0;
        }
        return 1 + countOrbits(map.get(object));
    }

    private Map<String, String> parse(List<String> input) {
        return input
                .stream()
                .map(s -> {
                    var i = s.indexOf(')');
                    return pair(s.substring(i + 1), s.substring(0, i));
                })
                .collect(toMap(l -> l.first, l -> l.second));
    }
}
