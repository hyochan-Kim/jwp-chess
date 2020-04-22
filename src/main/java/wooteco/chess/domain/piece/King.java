package wooteco.chess.domain.piece;

import wooteco.chess.domain.Side;
import wooteco.chess.domain.position.Position;

public class King extends Piece {
	private static final int KING_DISTANCE = 1;
	private static final String NAME = "k";
	private static final double SCORE = 0;

	public King(Side side, Position position) {
		super(side, position);
		this.name = NAME;
		this.score = SCORE;
	}

	@Override
	public boolean isInPath(Position targetPosition) {
		return this.position.isInDistance(KING_DISTANCE, targetPosition);
	}
}
