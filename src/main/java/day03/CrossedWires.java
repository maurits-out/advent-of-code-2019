package day03;

import org.apache.commons.lang3.tuple.Pair;

import java.util.*;
import java.util.regex.Pattern;

import static java.lang.Integer.parseInt;
import static java.lang.Math.abs;
import static java.util.List.of;
import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.rangeClosed;

public class CrossedWires {

    private static final Map<Character, Pair<Integer, Integer>> DIRECTIONS = Map.of(
            'R', Pair.of(0, 1),
            'L', Pair.of(0, -1),
            'U', Pair.of(-1, 0),
            'D', Pair.of(1, 0));
    private static final Pair<Integer, Integer> CENTRAL_PORT = Pair.of(0, 0);

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
        for (var instruction : Pattern.compile(",").split(path)) {
            var currentPos = visited.getLast();
            var points = move(currentPos, instruction);
            visited.addAll(points);
        }
        return visited;
    }

    public List<Pair<Integer, Integer>> move(Pair<Integer, Integer> current, String instruction) {
        var direction = DIRECTIONS.get(instruction.charAt(0));
        var steps = parseInt(instruction.substring(1));
        return rangeClosed(1, steps)
                .mapToObj(s -> Pair.of(current.getLeft() + (s * direction.getLeft()), current.getRight() + (s * direction.getRight())))
                .collect(toList());
    }

    private int manhattanDistance(Pair<Integer, Integer> point) {
        return abs(point.getLeft()) + abs(point.getRight());
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
