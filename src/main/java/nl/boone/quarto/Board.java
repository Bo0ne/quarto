package nl.boone.quarto;

import nl.boone.quarto.exceptions.IllegalMoveException;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private Piece[][] fields;
    private List<Piece> piecesLeft;

    private static final int BOARD_SIZE = 4;

    public Board() {
        fields = new Piece[BOARD_SIZE][BOARD_SIZE];
        piecesLeft = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                for (int k = 0; k < 2; k++) {
                    for (int l = 0; l < 2; l++) {
                        piecesLeft.add(new Piece(i == 1, j == 1, k == 1, l == 1));
                    }
                }
            }
        }
    }

    public void makeMove(Move move) throws IllegalMoveException {
        int x = move.getX();
        int y = move.getY();
        Piece piece = move.getPiece();

        if (x >= BOARD_SIZE || y >= BOARD_SIZE) {
            throw new IllegalMoveException("Move out of bounds");
        };
        if (fields[x][y] != null) {
            throw new IllegalMoveException("Field already occupied");
        };
        if (!piecesLeft.contains(piece)) {
            throw new IllegalMoveException("Piece already used");
        };

        fields[x][y] = piece;
        piecesLeft.remove(piece);
    }

    public Piece getPiece(int x, int y) {
        return fields[x][y];
    }

    public List<Piece> getPiecesLeft() {
        return piecesLeft;
    }

    public boolean isFull() {
        return piecesLeft.isEmpty();
    }

    public boolean hasWinner() {
        //TODO Implement
        return false;
    }

    public String toString() {
        String result = "---------------------\n";
        for (int i = 0; i < 4; i++) {
            result += "|";
            for (int j = 0; j < 4; j++) {
                result += fields[i][j] + "|";
            }
            result += "\n---------------------\n";
        }
        return result;
    }


    public static void main(String[] args) throws IllegalMoveException {
        Board board = new Board();
        board.makeMove(new Move(0, 0, board.getPiecesLeft().get(0)));
        board.makeMove(new Move(3, 1, board.getPiecesLeft().get(0)));
        board.makeMove(new Move(2, 1, board.getPiece(3, 1)));
        System.out.println(board);
    }
}
