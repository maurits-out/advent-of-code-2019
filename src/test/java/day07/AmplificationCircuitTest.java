package day07;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static support.IO.readInputAsString;

@DisplayName("Day 7: Amplification Circuit")
public class AmplificationCircuitTest {

    @Nested
    @DisplayName("Part 1")
    class Part1 {

        @Test
        void matchesExample() {
            part1(43210, "3,15,3,16,1002,16,10,16,1,16,15,15,4,15,99,0,0");
        }

        @Test
        void actualAnswer() {
            part1(844468, readInputAsString("day07-input.txt"));
        }

        private void part1(int expected, String program) {
            var amplificationCircuit = new AmplificationCircuit(program);
            assertEquals(expected, amplificationCircuit.part1());
        }
    }

    @Nested
    @DisplayName("Part 2")
    class Part2 {

        @Test
        void matchesExample() {
            part2(139629729, "3,26,1001,26,-4,26,3,27,1002,27,2,27,1,27,26,27,4,27,1001,28,-1,28,1005,28,6,99,0,0,5");
        }

        @Test
        void actualAnswer() {
            part2(4215746, readInputAsString("day07-input.txt"));
        }

        private void part2(int expected, String program) {
            var amplificationCircuit = new AmplificationCircuit(program);
            assertEquals(expected, amplificationCircuit.part2());
        }
    }
}
