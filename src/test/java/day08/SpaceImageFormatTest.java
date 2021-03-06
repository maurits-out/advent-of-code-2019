package day08;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static support.IO.readInputAsString;

@DisplayName("Day 8: Space Image Format")
public class SpaceImageFormatTest {

    @Nested
    @DisplayName("Part 1")
    class Part1 {

        @Test
        void actualAnswer() {
            var input = readInputAsString("day08-input.txt");
            var sif = new SpaceImageFormat(input);
            assertEquals(1950, sif.part1());
        }
    }

    @Nested
    @DisplayName("Part 2")
    class Part2 {

        @Test
        void actualAnswer() {
            var input = readInputAsString("day08-input.txt");
            var sif = new SpaceImageFormat(input);
            assertEquals("""
                    **** *  *  **  *  * *   \s
                    *    * *  *  * *  * *   \s
                    ***  **   *  * **** *   \s
                    *    * *  **** *  * *   \s
                    *    * *  *  * *  * *   \s
                    *    *  * *  * *  * ****\s""", sif.part2());
        }
    }
}
