package wooteco.chess.piece;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import wooteco.chess.domain.coordinate.Vector;
import wooteco.chess.domain.piece.Blank;
import wooteco.chess.domain.piece.King;
import wooteco.chess.domain.piece.Piece;
import wooteco.chess.domain.piece.Team;

import static org.assertj.core.api.Assertions.assertThat;

class KingTest {

    @DisplayName("킹은 전방향으로 한칸만 움직일 수 있다.")
    @ParameterizedTest
    @CsvSource(value = {
            "0,1,true",
            "0,-1,true",
            "1,0,true",
            "1,-1,true",
            "1,1,true",
            "-1,0,true",
            "-1,1,true",
            "-1,-1,true",
            "0,2,false"})
    void canMove(int file, int rank, boolean expect) {
        Piece king = new King(Team.BLACK);
        assertThat(king.canMove(new Vector(file, rank), new Blank())).isEqualTo(expect);
    }
}