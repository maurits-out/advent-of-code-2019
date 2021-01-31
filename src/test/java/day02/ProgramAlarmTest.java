package day02;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static support.IO.readInputAsString;

@DisplayName("Day 2: 1202 Program Alarm")
public class ProgramAlarmTest {

    private ProgramAlarm programAlarm;

    @BeforeEach
    void setUp() {
        programAlarm = new ProgramAlarm(readInputAsString("day02-input.txt"));
    }

    @Nested
    @DisplayName("Part 1")
    class Part1 {

        @Test
        void actualAnswer() {
            var answer = programAlarm.part1();
            assertEquals(6087827, answer);
        }
    }

    @Nested
    @DisplayName("Part 2")
    class Part2 {

        @Test
        void actualAnswer() {
            var answer = programAlarm.part2();
            assertEquals(5379, answer);
        }
    }
}
