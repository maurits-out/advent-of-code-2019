package day11;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static support.IO.readInputAsString;

@DisplayName("Day 11: Space Police")
public class SpacePoliceTest {

    private final String input = readInputAsString("day11-input.txt");

    @Nested
    @DisplayName("Part 1")
    class Part1 {

        @Test
        void actualAnswer() {
            SpacePolicePart1 part1 = new SpacePolicePart1(input);
            assertEquals(2415, part1.countPaintedPanels());
        }
    }

    @Nested
    @DisplayName("Part 2")
    class Part2 {

        @Test
        void actualAnswer() {
            SpacePolicePart2 part2 = new SpacePolicePart2(input);
            part2.paint();
        }
    }
}
