package day04;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Day 4: Secure Container")
class SecureContainerTest {

    private SecureContainer secureContainer;

    @BeforeEach
    void setUp() {
        secureContainer = new SecureContainer();
    }

    @Nested
    @DisplayName("Part 1")
    class Part1 {

        @Test
        void actualAnswer() {
            var answer = secureContainer.part1();
            assertEquals(1919, answer);
        }
    }

    @Nested
    @DisplayName("Part 2")
    class Part2 {

        @Test
        void actualAnswer() {
            var answer = secureContainer.part2();
            assertEquals(1291, answer);
        }
    }
}
