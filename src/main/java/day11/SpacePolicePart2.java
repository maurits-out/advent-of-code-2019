package day11;

import java.util.HashSet;
import java.util.IntSummaryStatistics;
import java.util.Set;
import java.util.function.ToIntFunction;

import static day11.Direction.NORTH;
import static java.lang.System.lineSeparator;
import static java.util.stream.Collectors.joining;
import static java.util.stream.IntStream.rangeClosed;

public class SpacePolicePart2 {

    private final IntcodeComputer computer;

    public SpacePolicePart2(String program) {
        computer = new IntcodeComputer(program);
    }

    String paint() {
        var blackPanels = run();
        return toGrid(blackPanels);
    }

    private Set<Position> run() {
        var blackPanels = new HashSet<Position>();
        var position = new Position(0, 0);
        var direction = NORTH;

        while (true) {
            computer.addInput(calculateInput(position, blackPanels));
            var outputs = computer.run();
            if (outputs.size() < 2) {
                break;
            }
            if (paintBlack(outputs.get(0))) {
                blackPanels.add(position);
            } else {
                blackPanels.remove(position);
            }
            direction = turn(direction, outputs.get(1));
            position = position.move(direction);
        }

        return blackPanels;
    }

    private String toGrid(Set<Position> blackPanels) {
        var rowStats = stats(blackPanels, Position::row);
        var columnStats = stats(blackPanels, Position::column);

        return rangeClosed(rowStats.getMin(), rowStats.getMax())
                .mapToObj(r -> rangeClosed(columnStats.getMin(), columnStats.getMax())
                        .mapToObj(c -> toChar(blackPanels, r, c))
                        .reduce(new StringBuilder(), StringBuilder::append, StringBuilder::append))
                .collect(joining(lineSeparator()));
    }

    private IntSummaryStatistics stats(Set<Position> positions, ToIntFunction<Position> function) {
        return positions.stream().mapToInt(function).summaryStatistics();
    }

    private char toChar(Set<Position> blackPanels, int r, int c) {
        return blackPanels.contains(new Position(r, c)) ? ' ' : '#';
    }

    private boolean paintBlack(Long paintValue) {
        return paintValue == 0;
    }

    private boolean turnLeft(Long output) {
        return output == 0;
    }

    private Direction turn(Direction direction, Long output) {
        return turnLeft(output) ? direction.turnLeft() : direction.turnRight();
    }

    private int calculateInput(Position position, Set<Position> blackPanels) {
        return blackPanels.contains(position) ? 0 : 1;
    }
}
