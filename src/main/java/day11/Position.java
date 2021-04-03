package day11;

record Position(int row, int column) {
    public Position move(Direction direction) {
        return switch (direction) {
            case EAST -> new Position(row, column + 1);
            case WEST -> new Position(row, column - 1);
            case NORTH -> new Position(row - 1, column);
            case SOUTH -> new Position(row + 1, column);
        };
    }
}
