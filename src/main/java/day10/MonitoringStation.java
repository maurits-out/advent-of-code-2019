package day10;

import java.util.Set;
import java.util.stream.Stream;

import static java.lang.Math.*;
import static java.util.Comparator.naturalOrder;
import static java.util.stream.Collectors.*;
import static java.util.stream.IntStream.range;

public class MonitoringStation {

    private static final double EPSILON = 0.00001;

    private final Set<Asteroid> asteroids;

    private static record Asteroid(int x, int y) {}

    public MonitoringStation(String map) {
        asteroids = locateAsteroids(map);
    }

    public int part1() {
        record Pair(Asteroid first, Asteroid second) {}

        return asteroids
                .stream()
                .flatMap(first -> asteroids.stream().map(second -> new Pair(first, second)))
                .filter(pair -> areInDirectLineOfSight(pair.first, pair.second))
                .collect(groupingBy(Pair::first, toSet()))
                .values()
                .stream()
                .map(Set::size)
                .max(naturalOrder()).orElseThrow();
    }

    private boolean areInDirectLineOfSight(Asteroid first, Asteroid second) {
        return !first.equals(second) &&
                asteroids
                        .stream()
                        .filter(a -> !(a.equals(first) || a.equals(second)))
                        .noneMatch(a -> isInLineOfSight(first, second, a));
    }

    private boolean isInLineOfSight(Asteroid from, Asteroid to, Asteroid asteroid) {
        return abs(distance(from, asteroid) + distance(asteroid, to) - distance(from, to)) < EPSILON;
    }

    private double distance(Asteroid from, Asteroid to) {
        return sqrt(pow(to.y - from.y, 2) + pow(to.x - from.x, 2));
    }

    private Set<Asteroid> locateAsteroids(String map) {
        String[] rows = map.lines().toArray(String[]::new);
        return range(0, rows.length)
                .boxed()
                .flatMap(y -> locateAsteroids(rows[y], y))
                .collect(toUnmodifiableSet());
    }

    private Stream<Asteroid> locateAsteroids(String row, int y) {
        return range(0, row.length())
                .filter(x -> row.charAt(x) == '#')
                .mapToObj(x -> new Asteroid(x, y));
    }
}
