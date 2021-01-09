package day03;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static support.IO.readInputAsLines;

@DisplayName("Day 3: Crossed Wires")
public class CrossedWiresTest {

    private final List<String> example = List.of(
            "R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51",
            "U98,R91,D20,R16,D67,R40,U7,R15,U6,R7");
    private final List<String> input = readInputAsLines("day03-input.txt");

    @Nested
    @DisplayName("Part 1")
    class Part1 {

        @Test
        void matchesExample() {
            var crossedWires = new CrossedWires(example.get(0), example.get(1));
            assertEquals(135, crossedWires.part1());
        }

        @Test
        void actualAnswer() {
            var crossedWires = new CrossedWires(input.get(0), input.get(1));
            assertEquals(3247, crossedWires.part1());
        }
    }

    @Nested
    @DisplayName("Part 2")
    class Part2 {

        @Test
        void matchesExample() {
            var crossedWires = new CrossedWires(example.get(0), example.get(1));
            assertEquals(410, crossedWires.part2());
        }

        @Test
        void actualAnswer() {
            var crossedWires = new CrossedWires(input.get(0), input.get(1));
            assertEquals(48054, crossedWires.part2());
        }
    }
}
