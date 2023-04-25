package nl.boone.quarto.model;

public class Move {
    private final int x;
    private final int y;
    private final Piece pieceToGive;

    public Move(int x, int y, Piece pieceToGive) {
        this.x = x;
        this.y = y;
        this.pieceToGive = pieceToGive;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Piece getPieceToGive() {
        return pieceToGive;
    }

    @Override
    public String toString() {
        return "Move{" +
                "x=" + x +
                ", y=" + y +
                ", pieceToGive=" + pieceToGive +
                '}';
    }
}
