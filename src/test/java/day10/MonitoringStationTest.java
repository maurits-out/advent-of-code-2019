package day10;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static support.IO.readInputAsString;

@DisplayName("Day 10: Monitoring Station")
public class MonitoringStationTest {

    @Nested
    @DisplayName("Part 1")
    class Part1 {

        @Test
        void matchesExample() {
            String map = """
                    .#..##.###...#######
                    ##.############..##.
                    .#.######.########.#
                    .###.#######.####.#.
                    #####.##.#.##.###.##
                    ..#####..#.#########
                    ####################
                    #.####....###.#.#.##
                    ##.#################
                    #####.##.###..####..
                    ..######..##.#######
                    ####.##.####...##..#
                    .#####..#.######.###
                    ##...#.##########...
                    #.##########.#######
                    .####.#.###.###.#.##
                    ....##.##.###..#####
                    .#.#.###########.###
                    #.#.#.#####.####.###
                    ###.##.####.##.#..##
                    """;
            assertMaxDetectableAsteroids(210, map);
        }

        @Test
        void actualAnswer() {
            assertMaxDetectableAsteroids(278, readInputAsString("day10-input.txt"));
        }

        private void assertMaxDetectableAsteroids(int expected, String map) {
            MonitoringStation station = new MonitoringStation(map);
            assertEquals(expected, station.part1());
        }
    }
}
