package day13;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import static java.lang.Long.parseLong;
import static java.util.stream.IntStream.range;

class Memory {

    private final Map<Long, Long> storage = new HashMap<>();

    public Memory(String program) {
        var values = Pattern.compile(",").split(program);
        range(0, values.length).forEach(i -> storage.put((long) i, parseLong(values[i])));
    }

    public long read(long address) {
        validateAddress(address);
        return storage.getOrDefault(address, 0L);
    }

    public void write(long address, long value) {
        validateAddress(address);
        storage.put(address, value);
    }

    private void validateAddress(long address) {
        if (address < 0) {
            throw new IllegalArgumentException("invalid address: " + address);
        }
    }
}
