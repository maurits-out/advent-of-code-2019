package day11;

import java.util.HashSet;
import java.util.Set;

import static day11.Direction.NORTH;
import static java.util.stream.IntStream.rangeClosed;

public class SpacePolicePart2 {

    private final IntcodeComputer computer;

    public SpacePolicePart2(String program) {
        computer = new IntcodeComputer(program);
    }

    void paint() {
        var blackPanels = run();
        draw(blackPanels);
    }

    private Set<Position> run() {
        var blackPanels = new HashSet<Position>();
        var position = new Position(0, 0);
        var direction = NORTH;
        while (true) {
            computer.addInput(calculateInput(position, blackPanels));
            var outputs = computer.run(2);
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

    private void draw(Set<Position> blackPanels) {
        int rows = blackPanels.stream().mapToInt(Position::row).max().orElseThrow();
        int columns = blackPanels.stream().mapToInt(Position::column).max().orElseThrow();

        rangeClosed(0, rows).forEach(r -> {
            var row = new StringBuilder();
            rangeClosed(0, columns).forEach(c -> {
                if (blackPanels.contains(new Position(r, c))) {
                    row.append(' ');
                } else {
                    row.append('#');
                }
            });
            System.out.println(row);
        });
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
