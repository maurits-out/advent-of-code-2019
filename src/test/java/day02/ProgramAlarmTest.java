package day02;

import day01.RocketEquation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Day 2: 1202 Program Alarm")
public class ProgramAlarmTest {

    private ProgramAlarm programAlarm;

    @BeforeEach
    void setUp() {
        programAlarm = new ProgramAlarm(readInput());
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

    private String readInput() {
        var url = getClass().getResource("/day02-input.txt");
        try {
            var file = Path.of(url.toURI());
            return Files.readString(file).trim();
        } catch (URISyntaxException | IOException e) {
            throw new RuntimeException("Error in reading input: " + e.getMessage(), e);
        }
    }
}
