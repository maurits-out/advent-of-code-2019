package day11;

import java.util.HashSet;
import java.util.Set;

import static day11.Direction.NORTH;

public class SpacePolicePart1 {

    private final IntcodeComputer computer;

    public SpacePolicePart1(String program) {
        computer = new IntcodeComputer(program);
    }

    int countPaintedPanels() {
        var whitePanels = new HashSet<Position>();
        var painted = new HashSet<Position>();
        var position = new Position(0, 0);
        var direction = NORTH;

        while (true) {
            computer.addInput(calculateInput(position, whitePanels));
            var outputs = computer.run();
            if (outputs.size() < 2) {
                break;
            }
            if (paintBlack(outputs.get(0))) {
                if (whitePanels.contains(position)) {
                    whitePanels.remove(position);
                    painted.add(position);
                }
            } else if (!whitePanels.contains(position)) {
                whitePanels.add(position);
                painted.add(position);
            }
            direction = turn(direction, outputs.get(1));
            position = position.move(direction);
        }

        return painted.size();
    }

    private boolean paintBlack(Long paintValue) {
        return paintValue == 0;
    }

    private Direction turn(Direction direction, Long output) {
        if (turnLeft(output)) {
            return direction.turnLeft();
        }
        return direction.turnRight();
    }

    private boolean turnLeft(Long output) {
        return output == 0;
    }

    private int calculateInput(Position position, Set<Position> whitePanels) {
        if (whitePanels.contains(position)) {
            return 1;
        }
        return 0;
    }
}
