package day13;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.signum;

class ScoreHandler implements IOHandler {

    private final List<Integer> buffer = new ArrayList<>(3);

    private int ballPosition;
    private int paddlePosition;
    private int score;

    @Override
    public int getInput() {
        return signum(ballPosition - paddlePosition);
    }

    @Override
    public void output(int value) {
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

    public int getScore() {
        return score;
    }
}
