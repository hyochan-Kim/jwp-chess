package wooteco.chess.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import wooteco.chess.domain.coordinate.Coordinate;
import wooteco.chess.domain.piece.Team;
import wooteco.chess.dto.MoveDto;
import wooteco.chess.dto.RoomDto;
import wooteco.chess.repository.MoveRepository;
import wooteco.chess.repository.MoveRepositoryImpl;
import wooteco.chess.repository.RoomRepository;
import wooteco.chess.repository.RoomRepositoryImpl;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ChessServiceTest {
    private ChessService chessService;
    private RoomRepository roomRepository;

    @BeforeEach
    void setup() {
        chessService = new ChessService();
        roomRepository = new RoomRepositoryImpl();
    }

    @AfterEach
    void tearDown() throws SQLException {
        MoveRepository moveRepository = new MoveRepositoryImpl();
        moveRepository.deleteAll();
        roomRepository.deleteAll();
    }

    @Test
    void move() throws SQLException {
        int roomId = (int) roomRepository.create(new RoomDto()).getObject();
        chessService.renew(roomId);
        MoveDto moveDto = new MoveDto(roomId, Coordinate.of("a2"), Coordinate.of("a4"));
        assertThat(chessService.move(moveDto).isSuccess()).isTrue();
    }

    @Test
    void getMovableWay() {
        chessService.renew(1);
        List<String> movableWay = Arrays.asList("a3", "c3");
        assertThat(chessService.getMovableWay(1, Team.WHITE, Coordinate.of("b1")).getObject())
                .isEqualTo(movableWay);
    }
}
