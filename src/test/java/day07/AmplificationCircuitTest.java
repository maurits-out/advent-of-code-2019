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
}
