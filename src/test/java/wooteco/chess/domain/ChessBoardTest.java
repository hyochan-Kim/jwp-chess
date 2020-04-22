package wooteco.chess.domain;

import static org.assertj.core.api.Assertions.*;
import static wooteco.chess.domain.TestFixture.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import wooteco.chess.domain.piece.Piece;

class ChessBoardTest {
	@DisplayName("Bishop을 경로에 맞게 이동")
	@Test
	void moveBishop() {
		ChessBoard board = BISHOP_TEST_BOARD();
		Piece sourcePiece = board.findPieceBy(D4);

		board.move(D4, E5);

		assertThat(board.findPieceBy(E5)).isEqualTo(sourcePiece);
	}

	@DisplayName("Bishop을 이동시킬 때 경로가 막혀있으면 예외 발생")
	@Test
	void moveBishop_BlockPath_ExceptionThrown() {
		ChessBoard board = BISHOP_TEST_BOARD();
		assertThatThrownBy(() -> board.move(D4, B6))
				.isInstanceOf(IllegalArgumentException.class);
	}

	@DisplayName("Pawn을 경로에 맞게 이동")
	@Test
	void movePawn() {
		ChessBoard board = PAWN_TEST_BOARD();
		Piece sourcePiece = board.findPieceBy(D5);

		board.move(D5, D6);

		assertThat(board.findPieceBy(D6)).isEqualTo(sourcePiece);
	}

	@DisplayName("Pawn을 이동시킬 때 대각선 1칸 앞에 상대가 있으면 공격")
	@Test
	void attackPawn_InDiagonalEnemy() {
		ChessBoard board = ATTACK_PAWN_TEST_BOARD();
		Piece sourcePiece = board.findPieceBy(D4);

		board.move(D4, E5);

		assertThat(board.findPieceBy(E5)).isEqualTo(sourcePiece);
	}

	@DisplayName("findPieceBy에서 Position 자리에 기물이 없으면 예외 발생")
	@Test
	void findPieceBy() {
		ChessBoard board = PAWN_TEST_BOARD();
		assertThatThrownBy(() -> board.findPieceBy(D2)).isInstanceOf(IllegalArgumentException.class);
	}

	@DisplayName("Piece를 이동시킬 때 목적지에 같은 팀 기물이 있으면 예외 발생")
	@Test
	void movePawn_ExistTargetPosition_ExceptionThrown() {
		ChessBoard board = PAWN_TEST_BOARD();
		assertThatThrownBy(() -> board.move(D4, D5)).isInstanceOf(IllegalArgumentException.class);
	}
}
