package day12;

import static java.lang.Math.abs;

public record MyVector(int x, int y, int z) {

    static MyVector ZERO = new MyVector(0, 0, 0);

    int sumOfAbsoluteValues() {
        return abs(x) + abs(y) + abs(z);
    }
}
