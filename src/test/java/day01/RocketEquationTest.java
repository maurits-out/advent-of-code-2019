package day01;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import support.IO;

import java.util.List;

import static java.nio.file.Files.readAllLines;
import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Day 1: The Tyranny of the Rocket Equation")
class RocketEquationTest {

    private RocketEquation rocketEquation;

    @BeforeEach
    void setUp() {
        rocketEquation = new RocketEquation(readInput());
    }

    @Nested
    @DisplayName("Part 1")
    class Part1 {

        @Test
        void actualAnswer() {
            var answer = rocketEquation.part1();
            assertEquals(3270717, answer);
        }
    }

    @Nested
    @DisplayName("Part 2")
    class Part2 {

        @Test
        void actualAnswer() {
            var answer = rocketEquation.part2();
            assertEquals(4903193, answer);
        }
    }

    private List<Integer> readInput() {
        return IO.readInputAsLines("day01-input.txt").stream().map(Integer::valueOf).collect(toList());
    }
}
