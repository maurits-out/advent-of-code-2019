package day01;

import java.util.List;

public class RocketEquation {

    private final List<Integer> input;

    public RocketEquation(List<Integer> input) {
        this.input = input;
    }

    public int part1() {
        return input.stream().mapToInt(this::massToFuel).sum();
    }

    public int part2() {
        return input.stream()
                .mapToInt(mass -> {
                    var fuel = massToFuel(mass);
                    return fuel + fuelRequirement(fuel);
                })
                .sum();
    }

    private int massToFuel(int mass) {
        return (mass / 3) - 2;
    }

    private int fuelRequirement(int fuel) {
        var requirement = massToFuel(fuel);
        if (requirement <= 0) {
            return 0;
        }
        return requirement + fuelRequirement(requirement);
    }
}
