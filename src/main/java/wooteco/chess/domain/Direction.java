package wooteco.chess.domain;

import java.util.Arrays;
import java.util.function.BiPredicate;

import wooteco.chess.domain.position.Position;

public enum Direction {
	COL(Position::isSameCol),
	ROW(Position::isSameRow),
	POSITIVE_DIAGONAL(Position::isPositiveDiagonal),
	NEGATIVE_DIAGONAL(Position::isNegativeDiagonal),
	NON_LINEAR((p1, p2) -> false);

	private BiPredicate<Position, Position> filter;

	Direction(BiPredicate<Position, Position> filter) {
		this.filter = filter;
	}

	public static Direction of(Position source, Position target) {
		return Arrays.stream(values())
				.filter(direction -> direction.isMatch(source, target))
				.findFirst()
				.orElse(NON_LINEAR);
	}

	public boolean isMatch(Position source, Position target) {
		return filter.test(source, target);
	}
}
