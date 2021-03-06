package wooteco.chess.piece;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import wooteco.chess.domain.coordinate.Vector;
import wooteco.chess.domain.piece.Blank;
import wooteco.chess.domain.piece.NotMovedPawn;
import wooteco.chess.domain.piece.Piece;
import wooteco.chess.domain.piece.Team;

import static org.assertj.core.api.Assertions.assertThat;

class NotMovedPawnTest {
    @DisplayName("2칸 움직일 수 있는지 확인")
    @ParameterizedTest
    @CsvSource(value = {"0,2,WHITE,true", "2,1,WHITE,false"})
    void canMove(int fileVariation, int rankVariation, Team team, boolean expect) {
        Piece piece = new NotMovedPawn(team);
        assertThat(piece.canMove(new Vector(fileVariation, rankVariation), new Blank())).isEqualTo(expect);
    }
}