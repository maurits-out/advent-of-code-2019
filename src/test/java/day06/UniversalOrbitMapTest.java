package day06;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static support.IO.readInputAsLines;

@DisplayName("Day 6: Universal Orbit Map")
class UniversalOrbitMapTest {

    @Nested
    @DisplayName("Part 1")
    class Part1 {

        @Test
        void matchesExample() {
            var example = List.of("COM)B", "B)C", "C)D", "D)E", "E)F", "B)G", "G)H", "D)I", "E)J", "J)K", "K)L");
            var universalOrbitMap = new UniversalOrbitMapPart1(example);
            assertEquals(42, universalOrbitMap.part1());
        }

        @Test
        void actualAnswer() {
            var input = readInputAsLines("day06-input.txt");
            var universalOrbitMap = new UniversalOrbitMapPart1(input);
            assertEquals(301100, universalOrbitMap.part1());
        }
    }

    @Nested
    @DisplayName("Part 2")
    class Part2 {

        @Test
        void matchesExample() {
            var example = List.of("COM)B", "B)C", "C)D", "D)E", "E)F", "B)G", "G)H", "D)I", "E)J", "J)K", "K)L", "K)YOU", "I)SAN");
            var universalOrbitMap = new UniversalOrbitMapPart2(example);
            assertEquals(4, universalOrbitMap.part2());
        }

        @Test
        void actualAnswer() {
            var input = readInputAsLines("day06-input.txt");
            var universalOrbitMap = new UniversalOrbitMapPart2(input);
            assertEquals(547, universalOrbitMap.part2());
        }
    }
}
