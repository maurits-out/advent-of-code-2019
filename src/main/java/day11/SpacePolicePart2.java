package day11;

import java.util.HashSet;
import java.util.Set;

import static day11.Direction.NORTH;
import static java.lang.System.lineSeparator;
import static java.util.stream.Collectors.joining;
import static java.util.stream.IntStream.rangeClosed;

public class SpacePolicePart2 {

    private final IntcodeComputer computer;

    public SpacePolicePart2(String program) {
        computer = new IntcodeComputer(program);
    }

    void paint() {
        var blackPanels = run();
        System.out.println(toGrid(blackPanels));
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
        var rowStats = blackPanels.stream().mapToInt(Position::row).summaryStatistics();
        var columnStats = blackPanels.stream().mapToInt(Position::column).summaryStatistics();

        return rangeClosed(rowStats.getMin(), rowStats.getMax())
                .mapToObj(r -> rangeClosed(columnStats.getMin(), columnStats.getMax())
                        .mapToObj(c -> toChar(blackPanels, r, c))
                        .reduce(new StringBuilder(), StringBuilder::append, StringBuilder::append))
                .collect(joining(lineSeparator()));
    }

    private char toChar(Set<Position> blackPanels, int r, int c) {
        if (blackPanels.contains(new Position(r, c))) {
            return ' ';
        }
        return '#';
    }

    private boolean paintBlack(Long paintValue) {
        return paintValue == 0;
    }

    private boolean turnLeft(Long output) {
        return output == 0;
    }

    private Direction turn(Direction direction, Long output) {
        if (turnLeft(output)) {
            return direction.turnLeft();
        }
        return direction.turnRight();
    }

    private int calculateInput(Position position, Set<Position> blackPanels) {
        if (blackPanels.contains(position)) {
            return 0;
        }
        return 1;
    }
}
