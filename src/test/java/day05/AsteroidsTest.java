package day05;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static support.IO.readInputAsString;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("Day 5: Sunny with a Chance of Asteroids")
public class AsteroidsTest {

    private Asteroids asteroids;

    @BeforeEach
    void setUp() {
        asteroids = new Asteroids(readInputAsString("day05-input.txt"));
    }

    @Nested
    @DisplayName("Part 1")
    class Part1 {

        @Test
        void actualAnswer() {
            var answer = asteroids.part1();
            assertEquals(8332629, answer);
        }
    }

    @Nested
    @DisplayName("Part 2")
    class Part2 {

        @Test
        void actualAnswer() {
            var answer = asteroids.part2();
            assertEquals(8805067, answer);
        }
    }
}
