package wooteco.chess.domain.piece;

import wooteco.chess.domain.Side;
import wooteco.chess.domain.position.Position;

public class Bishop extends Piece {
	private static final String NAME = "b";
	private static final int SCORE = 3;

	public Bishop(Side side, Position position) {
		super(side, position);
		this.name = NAME;
		this.score = SCORE;
	}

	@Override
	public boolean isInPath(Position targetPosition) {
		return position.isInDiagonal(targetPosition);
	}
}
