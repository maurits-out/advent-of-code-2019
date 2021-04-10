package day12;

import java.util.List;
import java.util.Set;
import java.util.function.Function;

import static day12.MyVector.ZERO;
import static java.lang.Integer.signum;
import static java.util.stream.Collectors.toUnmodifiableSet;

public class NBodyProblem {

    private final List<MyVector> initialPositions;
    private final int numIterations;

    public NBodyProblem(List<MyVector> initialPositions, int numIterations) {
        this.initialPositions = initialPositions;
        this.numIterations = numIterations;
    }

    record Moon(MyVector pos, MyVector vel) {

        int totalEnergy() {
            return pos.sumOfAbsoluteValues() * vel.sumOfAbsoluteValues();
        }
    }

    public int part1() {
        Set<Moon> moons = initialState();
        for (int i = 0; i < numIterations; i++) {
            moons = step(moons);
        }
        return moons.stream().mapToInt(Moon::totalEnergy).sum();
    }

    private Set<Moon> initialState() {
        return initialPositions.stream().map(pos -> new Moon(pos, ZERO)).collect(toUnmodifiableSet());
    }

    private Set<Moon> step(Set<Moon> allMoons) {
        Function<Moon, Moon> applyGravity = moon -> applyGravity(moon, allMoons);
        return allMoons
                .stream()
                .map(applyGravity.andThen(this::applyVelocity))
                .collect(toUnmodifiableSet());
    }

    private Moon applyVelocity(Moon moon) {
        MyVector vel = moon.vel();
        MyVector currentPos = moon.pos();
        MyVector newPos = new MyVector(
                calculateNewPosition(currentPos.x(), vel.x()),
                calculateNewPosition(currentPos.y(), vel.y()),
                calculateNewPosition(currentPos.z(), vel.z())
        );
        return new Moon(newPos, vel);
    }

    private int calculateNewPosition(int position, int velocity) {
        return position + velocity;
    }

    private Moon applyGravity(Moon moonToUpdate, Set<Moon> allMoons) {
        return allMoons.stream().reduce(moonToUpdate, this::applyGravity, this::applyGravity);
    }

    private Moon applyGravity(Moon moon, Moon other) {
        MyVector currentPos = moon.pos();
        MyVector currentVel = moon.vel();
        MyVector otherPos = other.pos();
        MyVector newVel = new MyVector(
                calculateNewVelocity(currentVel.x(), currentPos.x(), otherPos.x()),
                calculateNewVelocity(currentVel.y(), currentPos.y(), otherPos.y()),
                calculateNewVelocity(currentVel.z(), currentPos.z(), otherPos.z())
        );
        return new Moon(currentPos, newVel);
    }

    private int calculateNewVelocity(int velocity, int position, int positionOfOtherMoon) {
        return velocity + signum(positionOfOtherMoon - position);
    }
}
