package nl.boone.quarto.model.players;

import nl.boone.quarto.exceptions.IllegalMoveException;
import nl.boone.quarto.model.Game;
import nl.boone.quarto.model.Move;
import nl.boone.quarto.model.Piece;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class TUIPlayer extends Player {

    public TUIPlayer(String name) {
        super(name);
    }

    public String askCoordinates(Scanner scanner) {
        while (true) {
            //TODO Fix forever loop
            System.out.println("I AM WORKING FFS");
            try {
                return scanner.next(Pattern.compile("\\d-\\d"));
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Input should be of the form 'row-column'");
            }
        }
    }

    public Piece askPiece(Scanner scanner, Game game) {
        while (true) {
            System.out.println("> " + getName() + " Which piece do you want to give to your opponent?");
            int pieceIdx = scanner.nextInt();
            try {
                return game.getBoard().getPiecesLeft().get(pieceIdx);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("\u001B[31mInvalid input! Input should be a number between 0 and " + (game.getBoard().getPiecesLeft().size() - 1) + "\u001B[0m");
            }
        }
    }

    @Override
    public Move determineMove(Game game) {
        Piece pieceToPlay = game.getPieceToPlay();
        int x = 0;
        int y = 0;
        Piece pieceToGive;
        Scanner scanner = new Scanner(System.in);
        Move move;
        while (true) {
            try {
                if (pieceToPlay != null) {
                    System.out.println("> " + getName() + " Where do you want to place the piece " + pieceToPlay + "? (row-column)");
                    String xy = askCoordinates(scanner);
                    x = Integer.parseInt(xy.split("-")[0]);
                    y = Integer.parseInt(xy.split("-")[1]);
                }
                System.out.println(game.getBoard().displayPiecesLeft());
                pieceToGive = askPiece(scanner, game);
                move = new Move(x, y, pieceToGive);
                game.tryMove(move);
                break;
            } catch (IllegalMoveException e) {
                System.out.println("Illegal move! " + e.getMessage());
            }
        }
        return move;
    }

    public static void main(String[] args) throws IllegalMoveException {
     TUIPlayer player = new TUIPlayer("test");
     Game game = new Game(player, player);
     game.makeMove(new Move(0, 0, game.getBoard().getPiecesLeft().get(0)));
     System.out.println(player.determineMove(game));
    }
}
