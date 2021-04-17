package day13;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static support.IO.readInputAsString;

@DisplayName("Day 13: Care Package")
public class CarePackageTest {

    private final String input = readInputAsString("day13-input.txt");

    @Nested
    @DisplayName("Part 1")
    class Part1 {

        @Test
        void actualAnswer() {
            var carePackage = new CarePackage(input);
            assertEquals(258, carePackage.part1());
        }
    }

    @Nested
    @DisplayName("Part 2")
    class Part2 {

        @Test
        void actualAnswer() {
            var carePackage = new CarePackage(input);
            assertEquals(12765, carePackage.part2());
        }
    }
}
