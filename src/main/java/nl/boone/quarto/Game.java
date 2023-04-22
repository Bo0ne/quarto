package nl.boone.quarto;

import nl.boone.quarto.exceptions.IllegalMoveException;

import static nl.boone.quarto.Board.BOARD_SIZE;

public class Game {
    private final Board board;

    public Game() {
        board = new Board();
    }

    public void makeMove(Move move) throws IllegalMoveException {
        int x = move.getX();
        int y = move.getY();
        Piece piece = move.getPiece();

        if (x >= BOARD_SIZE || y >= BOARD_SIZE) {
            throw new IllegalMoveException("Move out of bounds");
        }
        if (board.getPiece(x, y) != null) {
            throw new IllegalMoveException("Field already occupied");
        }
        if (!board.getPiecesLeft().contains(piece)) {
            throw new IllegalMoveException("Piece already used");
        }

        board.setField(x, y, piece);
    }
}
