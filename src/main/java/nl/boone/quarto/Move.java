package nl.boone.quarto;

public class Move {
    private final int x;
    private final int y;
    private final Piece piece;

    public Move(int x, int y, Piece piece) {
        this.x = x;
        this.y = y;
        this.piece = piece;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Piece getPiece() {
        return piece;
    }
}
