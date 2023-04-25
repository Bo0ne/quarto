package nl.boone.quarto.model;

public class Piece {
    private final boolean isSquare;
    private final boolean isHollow;
    private final boolean isWhite;
    private final boolean isTall;

    public Piece(boolean isSquare, boolean isHollow, boolean isWhite, boolean isTall) {
        this.isSquare = isSquare;
        this.isHollow = isHollow;
        this.isWhite = isWhite;
        this.isTall = isTall;
    }

    public boolean isSquare() {
        return isSquare;
    }

    public boolean isHollow() {
        return isHollow;
    }

    public boolean isWhite() {
        return isWhite;
    }

    public boolean isTall() {
        return isTall;
    }

    public String toString() {
        char square = isSquare ? 'S' : 'R';
        char hollow = isHollow ? 'H' : 'F';
        char white = isWhite ? 'W' : 'B';
        char tall = isTall ? 'T' : 's';
        return "" + square + hollow + white + tall;
    }
}
