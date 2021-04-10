package day12;

import static java.lang.Integer.signum;
import static java.lang.Math.abs;

public class NBodyProblem {

    public int part1(int[][] positions, int iterations) {
        var moons = positions.length;
        var axes = positions[0].length;

        var vel = createVelocityVectors(moons, axes);
        for (var i = 0; i < iterations; i++) {
            // apply gravity
            for (var m = 0; m < moons; m++) {
                for (var n = 0; n < moons; n++) {
                    for (var a = 0; a < axes; a++) {
                        vel[m][a] += signum(positions[n][a] - positions[m][a]);
                    }
                }
            }
            // apply velocity
            for (var m = 0; m < moons; m++) {
                for (var a = 0; a < axes; a++) {
                    positions[m][a] += vel[m][a];
                }
            }
        }

        return calcTotalEnergy(positions, moons, axes, vel);
    }

    private int[][] createVelocityVectors(int numMoons, int numAxes) {
        int[][] vel = new int[numMoons][];
        for (var m = 0; m < numMoons; m++) {
            vel[m] = new int[numAxes];
        }
        return vel;
    }

    private int calcTotalEnergy(int[][] positions, int numMoons, int numAxes, int[][] vel) {
        var total = 0;
        for (var m = 0; m < numMoons; m++) {
            var potential = 0;
            var kinetic = 0;
            for (var a = 0; a < numAxes; a++) {
                potential += abs(positions[m][a]);
                kinetic += abs(vel[m][a]);
            }
            total += potential * kinetic;
        }
        return total;
    }
}
