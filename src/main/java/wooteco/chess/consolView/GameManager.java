package wooteco.chess.consolView;

import wooteco.chess.domain.Chess;
import wooteco.chess.domain.board.BoardGenerator;
import wooteco.chess.domain.coordinate.Coordinate;

import java.util.Objects;

public class GameManager {
    public static final int SOURCE_INDEX = 1;
    public static final int TARGET_INDEX = 2;
    public static final String DELIMITER = " ";

    private Chess chess;
    private boolean isNotEnd;

    public GameManager() {
        isNotEnd = true;
    }

    public void runGame() {
        OutputView.showAllCommand();
        while (isNotEnd) {
            action();
        }
    }

    public void action() {
        String command = InputView.inputCommand();
        if (Command.isStart(command)) {
            start();
        }
        if (Command.isEnd(command)) {
            end();
        }
        if (Objects.isNull(chess)) {
            return;
        }
        if (Command.isMove(command)) {
            move(command);
        }
        if (Command.isStatus(command)) {
            status();
        }
        OutputView.showChessBoard(this.chess.getChessBoard());
    }

    private void start() {
        this.chess = new Chess(BoardGenerator.create());
    }

    private void end() {
        isNotEnd = false;
    }

    private void move(String command) {
        String source = command.split(DELIMITER)[SOURCE_INDEX];
        String target = command.split(DELIMITER)[TARGET_INDEX];
        chess.move(Coordinate.of(source), Coordinate.of(target));
        isNotEnd = chess.isKingAlive();
    }

    private void status() {
        OutputView.showScore(this.chess.getCurrentTeam(),
                this.chess.calculateCurrentTeamScore());
    }
}
