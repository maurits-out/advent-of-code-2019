package day13;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import static java.lang.Integer.parseInt;
import static java.util.stream.IntStream.range;

class Memory {

    private final Map<Integer, Integer> storage = new HashMap<>();

    public Memory(String program) {
        var values = Pattern.compile(",").split(program);
        range(0, values.length).forEach(i -> storage.put(i, parseInt(values[i])));
    }

    public int read(int address) {
        validateAddress(address);
        return storage.getOrDefault(address, 0);
    }

    public void write(int address, int value) {
        validateAddress(address);
        storage.put(address, value);
    }

    private void validateAddress(int address) {
        if (address < 0) {
            throw new IllegalArgumentException("invalid address: " + address);
        }
    }
}
