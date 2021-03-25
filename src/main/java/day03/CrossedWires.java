package day03;

import java.util.*;
import java.util.regex.Pattern;

import static java.lang.Integer.parseInt;
import static java.lang.Math.abs;
import static java.util.List.of;
import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.rangeClosed;

public class CrossedWires {

    private record Location(int row, int column) {}

    private static final Map<Character, Location> DIRECTIONS = Map.of(
            'R', new Location(0, 1),
            'L', new Location(0, -1),
            'U', new Location(-1, 0),
            'D', new Location(1, 0));
    private static final Location CENTRAL_PORT = new Location(0, 0);

    private final List<Location> path1;
    private final List<Location> path2;
    private final Set<Location> intersectionPoints;

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

    private List<Location> followInstructions(String path) {
        var visited = new LinkedList<>(of(CENTRAL_PORT));
        for (var instruction : Pattern.compile(",").split(path)) {
            var currentPos = visited.getLast();
            var points = move(currentPos, instruction);
            visited.addAll(points);
        }
        return visited;
    }

    public List<Location> move(Location current, String instruction) {
        var direction = DIRECTIONS.get(instruction.charAt(0));
        var steps = parseInt(instruction.substring(1));
        return rangeClosed(1, steps)
                .mapToObj(s -> new Location(current.row() + (s * direction.row()), current.column() + (s * direction.column())))
                .collect(toList());
    }

    private int manhattanDistance(Location point) {
        return abs(point.row()) + abs(point.column());
    }

    private Set<Location> intersectionPoints() {
        var intersectionPoints = new HashSet<>(path1);
        intersectionPoints.retainAll(new HashSet<>(path2));
        intersectionPoints.remove(CENTRAL_PORT);
        return intersectionPoints;
    }

    private int combinedSteps(Location point) {
        return path1.indexOf(point) + path2.indexOf(point);
    }
}
