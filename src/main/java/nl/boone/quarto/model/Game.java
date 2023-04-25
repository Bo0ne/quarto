package nl.boone.quarto.model;

import nl.boone.quarto.exceptions.IllegalMoveException;
import nl.boone.quarto.model.players.Player;

import static nl.boone.quarto.model.Board.BOARD_SIZE;

public class Game {
    private final Board board;
    private final Player[] players;
    private int currentPlayer;
    private Piece pieceToPlay;

    public Game(Player player1, Player player2) {
        board = new Board();
        players = new Player[]{player1, player2};
        currentPlayer = 0;
    }

    public void tryMove(Move move) throws IllegalMoveException {
        int x = move.getX();
        int y = move.getY();
        Piece pieceToGive = move.getPieceToGive();

        if (x >= BOARD_SIZE || y >= BOARD_SIZE) {
            throw new IllegalMoveException("Move out of bounds");
        }
        if (board.getPiece(x, y) != null) {
            throw new IllegalMoveException("Field already occupied");
        }
        if (!board.getPiecesLeft().contains(pieceToGive)) {
            throw new IllegalMoveException("The piece you want to give was already used");
        }
    }

    public void makeMove(Move move) throws IllegalMoveException {
        tryMove(move);

        board.setField(move.getX(), move.getY(), pieceToPlay);
        pieceToPlay = move.getPieceToGive();
        currentPlayer = (currentPlayer + 1) % 2;
    }

    public boolean isGameOver() {
        return board.isFull() || board.hasWinner();
    }

    public Player getTurn() {
        return players[currentPlayer];
    }

    public Piece getPieceToPlay() {
        return pieceToPlay;
    }

    public Board getBoard() {
        return board;
    }

    public Player getWinner() {
        if (!board.hasWinner()) {
            return null;
        }
        return players[(currentPlayer + 1) % 2];
    }
}
