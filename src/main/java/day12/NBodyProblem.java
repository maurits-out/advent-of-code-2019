package day12;

import static java.lang.Integer.signum;
import static java.lang.Math.abs;

public class NBodyProblem {

    public int part1(int[][] pos, int iterations) {
        var vel = createVelocityVectors(pos.length, pos[0].length);
        for (var i = 0; i < iterations; i++) {
            // apply gravity
            for (var m = 0; m < pos.length; m++) {
                for (var n = 0; n < pos.length; n++) {
                    for (var a = 0; a < pos[m].length; a++) {
                        vel[m][a] += signum(pos[n][a] - pos[m][a]);
                    }
                }
            }
            // apply velocity
            for (var m = 0; m < pos.length; m++) {
                for (var a = 0; a < pos[m].length; a++) {
                    pos[m][a] += vel[m][a];
                }
            }
        }

        return calcTotalEnergy(pos, vel);
    }

    private int[][] createVelocityVectors(int moons, int axes) {
        int[][] vel = new int[moons][];
        for (var m = 0; m < moons; m++) {
            vel[m] = new int[axes];
        }
        return vel;
    }

    private int calcTotalEnergy(int[][] positions, int[][] vel) {
        var total = 0;
        for (var m = 0; m < positions.length; m++) {
            var potential = 0;
            var kinetic = 0;
            for (var a = 0; a < positions[m].length; a++) {
                potential += abs(positions[m][a]);
                kinetic += abs(vel[m][a]);
            }
            total += potential * kinetic;
        }
        return total;
    }
}
