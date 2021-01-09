package day03;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import support.IO;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static support.IO.readInputAsLines;

@DisplayName("Day 3: Crossed Wires")
public class CrossedWiresTest {

    @Nested
    @DisplayName("Part 1")
    class Part1 {

        @Test
        void matchesExample() {
            var path1 = "R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51";
            var path2 = "U98,R91,D20,R16,D67,R40,U7,R15,U6,R7";
            CrossedWires crossedWires = new CrossedWires(path1, path2);
            assertEquals(135, crossedWires.part1());
        }

        @Test
        void actualAnswer() {
            var paths = readInputAsLines("day03-input.txt");
            CrossedWires crossedWires = new CrossedWires(paths.get(0), paths.get(1));
            assertEquals(3247, crossedWires.part1());
        }
    }
}
