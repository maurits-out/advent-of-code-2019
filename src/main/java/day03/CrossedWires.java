package day03;

import support.Pair;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.rangeClosed;

public class CrossedWires {

    private static final Map<Character, Pair<Integer, Integer>> DIRECTIONS = Map.of(
            'R', Pair.of(0, 1),
            'L', Pair.of(0, -1),
            'U', Pair.of(-1, 0),
            'D', Pair.of(1, 0));
    private static final Pair<Integer, Integer> CENTRAL_PORT = Pair.of(0, 0);

    private final String path1;
    private final String path2;

    public CrossedWires(String path1, String path2) {
        this.path1 = path1;
        this.path2 = path2;
    }

    public int part1() {
        return intersectionPoints(followInstructions(path1), followInstructions(path2))
                .stream()
                .mapToInt(this::manhattanDistance)
                .min()
                .orElseThrow();
    }

    private Set<Pair<Integer, Integer>> intersectionPoints(Set<Pair<Integer, Integer>> visited1, Set<Pair<Integer, Integer>> visited2) {
        Set<Pair<Integer, Integer>> intersectionPoints = new HashSet<>(visited1);
        intersectionPoints.retainAll(visited2);
        intersectionPoints.remove(CENTRAL_PORT);
        return intersectionPoints;
    }

    private Set<Pair<Integer, Integer>> followInstructions(String path) {
        var visited = new HashSet<Pair<Integer, Integer>>();
        var currentPos = CENTRAL_PORT;
        for (String instruction : Pattern.compile(",").split(path)) {
            List<Pair<Integer, Integer>> points = move(currentPos, instruction);
            visited.addAll(points);
            currentPos = points.get(points.size() - 1);
        }
        return visited;
    }

    public List<Pair<Integer, Integer>> move(Pair<Integer, Integer> current, String instruction) {
        var direction = DIRECTIONS.get(instruction.charAt(0));
        var steps = Integer.parseInt(instruction.substring(1));
        return rangeClosed(1, steps)
                .mapToObj(s -> Pair.of(current.first + (s * direction.first), current.second + (s * direction.second)))
                .collect(toList());
    }

    private int manhattanDistance(Pair<Integer, Integer> point) {
        return Math.abs(point.first) + Math.abs(point.second);
    }
}
