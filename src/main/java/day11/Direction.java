package day11;

enum Direction {
    NORTH {
        @Override
        Direction turnLeft() {
            return WEST;
        }

        @Override
        Direction turnRight() {
            return EAST;
        }
    },
    SOUTH {
        @Override
        Direction turnLeft() {
            return EAST;
        }

        @Override
        Direction turnRight() {
            return WEST;
        }
    },
    WEST {
        @Override
        Direction turnLeft() {
            return SOUTH;
        }

        @Override
        Direction turnRight() {
            return NORTH;
        }
    },
    EAST {
        @Override
        Direction turnLeft() {
            return NORTH;
        }

        @Override
        Direction turnRight() {
            return SOUTH;
        }
    };

    abstract Direction turnLeft();

    abstract Direction turnRight();
}
