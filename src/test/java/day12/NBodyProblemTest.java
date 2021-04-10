package day12;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Day 12: The N-Body Problem1")
class NBodyProblemTest {

    @Nested
    @DisplayName("Part 1")
    class Part1 {

        @Test
        void matchesExample() {
            var moons = List.of(
                    new MyVector(-1, 0, 2),
                    new MyVector(2, -10, -7),
                    new MyVector(4, -8, 8),
                    new MyVector(3, 5, -1));
            var problem = new NBodyProblem(moons, 10);
            assertEquals(179, problem.part1());
        }

        @Test
        void actualAnswer() {
            var moons = List.of(
                    new MyVector(-17, 9, -5),
                    new MyVector(-1, 7, 13),
                    new MyVector(-19, 12, 5),
                    new MyVector(-6, -6, -4));
            var problem = new NBodyProblem(moons, 1000);
            assertEquals(8742, problem.part1());
        }
    }
}
