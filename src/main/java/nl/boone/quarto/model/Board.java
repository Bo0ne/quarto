package nl.boone.quarto.model;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private final Piece[][] fields;
    private final List<Piece> piecesLeft;

    public static final int BOARD_SIZE = 4;

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

    public void reset() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (fields[i][j] == null) continue;
                //Bring back the pieces
                piecesLeft.add(fields[i][j]);
                //Set fields to null
                fields[i][j] = null;
            }
        }


    }

    public void setField(int x, int y, Piece piece) {
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
        if (checkDiagonal(0) || checkDiagonal(1)) {
            return true;
        }
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (checkColumn(i) || checkRow(i)) {
                return true;
            }
        }
        return false;
    }

    private boolean checkColumn(int column) {
        List<Piece> pieces = new ArrayList<>();
        Piece piece;
        for (int i = 0; i < BOARD_SIZE; i++) {
            piece = fields[i][column];
            if (piece == null) return false;
            pieces.add(piece);
        }
        return checkLine(pieces);
    }

    private boolean checkRow(int row) {
        List<Piece> pieces = new ArrayList<>();
        Piece piece;
        for (int i = 0; i < BOARD_SIZE; i++) {
            piece = fields[row][i];
            if (piece == null) return false;
            pieces.add(piece);
        }
        return checkLine(pieces);
    }

    private boolean checkDiagonal(int diagonal) {
        List<Piece> pieces = new ArrayList<>();
        Piece piece;
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (diagonal == 0) {
                piece = fields[i][i];
                if (piece == null) return false;
                pieces.add(piece);
            } else {
                piece = fields[i][BOARD_SIZE - i - 1];
                if (piece == null) return false;
                pieces.add(piece);
            }
        }
        return checkLine(pieces);
    }

    private boolean checkLine(List<Piece> pieces) {
        if (pieces.size() != BOARD_SIZE) {
            return false;
        }
        Piece first = pieces.get(0);
        boolean shape = true;
        boolean color = true;
        boolean size = true;
        boolean height = true;

        for (Piece piece : pieces) {
            shape = shape && piece.isSquare() == first.isSquare();
            color = color && piece.isWhite() == first.isWhite();
            size = size && piece.isHollow() == first.isHollow();
            height = height && piece.isTall() == first.isTall();
        }

        return shape || color || size || height;
    }

    public String displayPiecesLeft() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < piecesLeft.size(); i++) {
            result.append(i).append(". ").append(piecesLeft.get(i)).append(" ");
        }
        return result.toString();
    }

    public String toString() {
        StringBuilder result = new StringBuilder("-------------------------------------\n");
        for (int i = 0; i < 4; i++) {
            result.append('|');
            for (int j = 0; j < 4; j++) {
                result.append(i).append('-').append(j).append(':').append(fields[i][j]).append('|');
            }
            result.append("\n-------------------------------------\n");
        }
        return result.toString();
    }


    public static void main(String[] args) {
        Board board = new Board();
        board.setField(2, 0, board.getPiecesLeft().get(0));
        board.setField(2, 1, board.getPiecesLeft().get(0));
        board.setField(2, 2, board.getPiecesLeft().get(0));
        board.setField(2, 3, board.getPiecesLeft().get(0));
        System.out.println(board + "\n" + board.getPiecesLeft());
        board.reset();
        System.out.println(board + "\n" + board.getPiecesLeft());
    }
}
