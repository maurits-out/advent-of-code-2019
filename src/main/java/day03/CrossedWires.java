package day03;

import support.Pair;

import java.util.*;
import java.util.regex.Pattern;

import static java.util.List.of;
import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.rangeClosed;
import static support.Pair.pair;

public class CrossedWires {

    private static final Map<Character, Pair<Integer, Integer>> DIRECTIONS = Map.of(
            'R', pair(0, 1),
            'L', pair(0, -1),
            'U', pair(-1, 0),
            'D', pair(1, 0));
    private static final Pair<Integer, Integer> CENTRAL_PORT = pair(0, 0);

    private final List<Pair<Integer, Integer>> path1;
    private final List<Pair<Integer, Integer>> path2;
    private final Set<Pair<Integer, Integer>> intersectionPoints;

    public CrossedWires(String path1, String path2) {
        this.path1 = followInstructions(path1);
        this.path2 = followInstructions(path2);
        this.intersectionPoints = intersectionPoints();
    }

    public int part1() {
        return intersectionPoints.stream().mapToInt(this::manhattanDistance).min().orElseThrow();
    }

    public int part2() {
        return intersectionPoints.stream().mapToInt(this::combinedSteps).min().orElseThrow();
    }

    private List<Pair<Integer, Integer>> followInstructions(String path) {
        var visited = new LinkedList<>(of(CENTRAL_PORT));
        for (String instruction : Pattern.compile(",").split(path)) {
            var currentPos = visited.getLast();
            List<Pair<Integer, Integer>> points = move(currentPos, instruction);
            visited.addAll(points);
        }
        return visited;
    }

    public List<Pair<Integer, Integer>> move(Pair<Integer, Integer> current, String instruction) {
        var direction = DIRECTIONS.get(instruction.charAt(0));
        var steps = Integer.parseInt(instruction.substring(1));
        return rangeClosed(1, steps)
                .mapToObj(s -> pair(current.first + (s * direction.first), current.second + (s * direction.second)))
                .collect(toList());
    }

    private int manhattanDistance(Pair<Integer, Integer> point) {
        return Math.abs(point.first) + Math.abs(point.second);
    }

    private Set<Pair<Integer, Integer>> intersectionPoints() {
        var intersectionPoints = new HashSet<>(path1);
        intersectionPoints.retainAll(new HashSet<>(path2));
        intersectionPoints.remove(CENTRAL_PORT);
        return intersectionPoints;
    }

    private int combinedSteps(Pair<Integer, Integer> point) {
        return path1.indexOf(point) + path2.indexOf(point);
    }
}
