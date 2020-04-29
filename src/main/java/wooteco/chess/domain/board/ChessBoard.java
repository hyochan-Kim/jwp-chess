package wooteco.chess.domain.board;

import wooteco.chess.domain.coordinate.Coordinate;
import wooteco.chess.domain.coordinate.File;
import wooteco.chess.domain.coordinate.Rank;
import wooteco.chess.domain.observer.Observable;
import wooteco.chess.domain.observer.Publishable;
import wooteco.chess.domain.piece.Blank;
import wooteco.chess.domain.piece.Team;

import java.util.*;
import java.util.stream.Collectors;

public class ChessBoard implements Publishable {
    private final Map<Coordinate, Tile> chessBoard;
    private Observable observable;

    private ChessBoard(final Map<Coordinate, Tile> chessBoard) {
        this.chessBoard = chessBoard;
    }

    public static ChessBoard empty() {
        Map<Coordinate, Tile> emptyBoard = new HashMap<>();
        for (File file : File.values()) {
            putEmptyTileInFile(emptyBoard, file);
        }
        return new ChessBoard(emptyBoard);
    }

    private static void putEmptyTileInFile(Map<Coordinate, Tile> emptyBoard, File file) {
        for (Rank rank : Rank.values()) {
            Coordinate coordinate = Coordinate.of(file, rank);
            emptyBoard.put(coordinate, new Tile(coordinate, new Blank()));
        }
    }

    public Map<Coordinate, Tile> getChessBoard() {
        return Collections.unmodifiableMap(chessBoard);
    }

    public void move(Coordinate source, Coordinate target) {

        if (!canMove(source, target)) {
            throw new IllegalArgumentException("That piece can not move to target.");
        }

        Tile sourceTile = chessBoard.get(source);
        Tile targetTile = chessBoard.get(target);

        push(targetTile.replacePiece(sourceTile));
    }

    public boolean canMove(Coordinate source, Coordinate target) {
        Tile sourceTile = chessBoard.get(source);
        Tile targetTile = chessBoard.get(target);

        if (sourceTile.canNotReach(targetTile)) {
            return false;
        }

        Directions directions = sourceTile.findPath(targetTile);
        return !directions.hasObstacle(source, chessBoard::get);
    }

    public List<String> getMovableWay(Coordinate sourceCoordinate) {
        List<String> movableCoordinate = new ArrayList<>();
        for (File file : File.values()) {
            movableCoordinate.addAll(
                    Arrays.stream(Rank.values())
                            .filter(rank -> canMove(sourceCoordinate, Coordinate.of(file, rank)))
                            .map(rank -> file.getSymbol() + rank.getValue())
                            .collect(Collectors.toList()));
        }
        return movableCoordinate;
    }

    public double calculateScore(Team team) {
        return Score.calculateScore(team, chessBoard::get);
    }

    public void put(final Tile tile) {
        this.chessBoard.put(tile.getCoordinate(), tile);
    }

    public boolean isNotSameTeam(final Coordinate source, final Team currentTeam) {
        Tile tile = this.chessBoard.get(source);
        return !tile.isSameTeam(currentTeam);
    }

    @Override
    public void subscribe(final Observable observable) {
        this.observable = observable;
    }

    @Override
    public void push(final Object object) {
        observable.update(object);
    }
}