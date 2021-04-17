package day13;

import java.util.NoSuchElementException;

class BlockCounter implements IOHandler {

    private int elementCount = 0;
    private int blockCount = 0;

    @Override
    public long getInput() {
        throw new NoSuchElementException("No input available");
    }

    @Override
    public void output(long value) {
        if (elementCount == 2) {
            if (value == 2) {
                blockCount++;
            }
            elementCount = 0;
        } else {
            elementCount++;
        }
    }

    public int getBlockCount() {
        return blockCount;
    }
}
