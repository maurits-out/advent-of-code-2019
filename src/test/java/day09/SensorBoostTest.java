package day09;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static support.IO.readInputAsString;

@DisplayName("Day 9: Sensor Boost")
public class SensorBoostTest {

    private final String input = readInputAsString("day09-input.txt");

    @Nested
    @DisplayName("Part 1")
    class Part1 {

        @Test
        void matchesExample() {
            assertPart1("109,1,204,-1,1001,100,1,100,1008,100,16,101,1006,101,0,99", "109,1,204,-1,1001,100,1,100,1008,100,16,101,1006,101,0,99"
            );
            assertPart1("1219070632396864", "1102,34915192,34915192,7,4,7,99,0");
            assertPart1("1125899906842624", "104,1125899906842624,99");
        }

        @Test
        void actualAnswer() {
            assertPart1("2494485073", input);
        }

        private void assertPart1(String expected, String input) {
            SensorBoost boost = new SensorBoost(input);
            assertEquals(expected, boost.part1());
        }
    }

    @Nested
    @DisplayName("Part 2")
    class Part2 {

        @Test
        void actualAnswer() {
            SensorBoost boost = new SensorBoost(input);
            assertEquals("44997", boost.part2());
        }
    }
}
