package day12;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Day 12: The N-Body Problem1")
class NBodyProblemTest {

    @Nested
    @DisplayName("Part 1")
    class Part1 {

        @Test
        void matchesExample() {
            int[][] positions = {
                    {-1, 0, 2},
                    {2, -10, -7},
                    {4, -8, 8},
                    {3, 5, -1}
            };
            var problem = new NBodyProblem();
            assertEquals(179, problem.part1(positions, 10));
        }

        @Test
        void actualAnswer() {
            int[][] positions = {
                    {-17, 9, -5},
                    {-1, 7, 13},
                    {-19, 12, 5},
                    {-6, -6, -4}
            };
            var problem = new NBodyProblem();
            assertEquals(8742, problem.part1(positions, 1000));
        }
    }
}
