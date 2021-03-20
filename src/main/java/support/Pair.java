package support;

import java.util.Objects;

public class Pair<T, U> {
    public final T first;
    public final U second;

    private Pair(T first, U second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        var pair = (Pair<?, ?>) o;
        return first.equals(pair.first) && second.equals(pair.second);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }

    @Override
    public String toString() {
        return "(" + first + ", " + second + ")";
    }

    public static <T, U> Pair<T, U> pair(T first, U second) {
        return new Pair<>(first, second);
    }
}
