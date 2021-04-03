package day11;

import java.util.HashSet;
import java.util.Set;

import static day11.Direction.NORTH;
import static java.util.Comparator.naturalOrder;

public class SpacePolicePart2 {

    private final IntcodeComputer computer;

    public SpacePolicePart2(String program) {
        computer = new IntcodeComputer(program);
    }

    void paint() {
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
        draw(blackPanels);
    }

    private void draw(Set<Position> blackPanels) {
        int rows = blackPanels.stream().map(Position::row).max(naturalOrder()).orElseThrow();
        int columns = blackPanels.stream().map(Position::column).max(naturalOrder()).orElseThrow();
        char[][] grid = new char[rows + 1][columns + 1];
        for (int r = 0; r <= rows; r++) {
            for (int c = 0; c <= columns; c++) {
                if (blackPanels.contains(new Position(r, c))) {
                    grid[r][c] = ' ';
                } else {
                    grid[r][c] = '#';
                }
            }
        }

        for (int r = 0; r <= rows; r++) {
            for (int c = 0; c <= columns; c++) {
                System.out.printf("%c", grid[r][c]);
            }
            System.out.println();
        }
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
