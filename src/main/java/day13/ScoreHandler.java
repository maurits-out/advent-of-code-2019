package day13;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Long.signum;

class ScoreHandler implements IOHandler {

    private final List<Long> buffer = new ArrayList<>(3);

    private long ballPosition;
    private long paddlePosition;
    private long score = 0;

    @Override
    public long getInput() {
        return signum(ballPosition - paddlePosition);
    }

    @Override
    public void output(long value) {
        buffer.add(value);
        if (buffer.size() == 3) {
            handleOutputTriple();
            buffer.clear();
        }
    }

    private void handleOutputTriple() {
        var tileId = buffer.get(2);
        var x = buffer.get(0);

        if (x == -1) {
            score = tileId;
        } else if (tileId == 3) {
            paddlePosition = x;
        } else if (tileId == 4) {
            ballPosition = x;
        }
    }

    public long getScore() {
        return score;
    }
}
