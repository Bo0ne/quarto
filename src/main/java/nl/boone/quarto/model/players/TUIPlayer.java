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

    @Override
    public Move determineMove(Game game) {
        Piece pieceToPlay = game.getPieceToPlay();
        int x = -1;
        int y = -1;
        Piece pieceToGive;
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                if (pieceToPlay != null) {
                    System.out.println("> " + getName() + " Where do you want to place the piece " + pieceToPlay + "? (row-column)");
                    while (true) {
                        try {
                            String xy = scanner.next(Pattern.compile("\\d-\\d"));
                            x = Integer.parseInt(xy.split("-")[0]);
                            y = Integer.parseInt(xy.split("-")[1]);
                            break;
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input! Input should be of the form 'row-column'");
                        }
                    }
                }
                System.out.println(game.getBoard().displayPiecesLeft());
                while (true) {
                    System.out.println("> " + getName() + " Which piece do you want to give to your opponent?");
                    int pieceIdx = scanner.nextInt();
                    try {
                        pieceToGive = game.getBoard().getPiecesLeft().get(pieceIdx);
                        break;
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Invalid input! Input should be a number between 0 and " + (game.getBoard().getPiecesLeft().size() - 1));
                    }
                }
                Move move = new Move(x, y, pieceToGive);
                game.tryMove(move);
                break;
            } catch (IllegalMoveException e) {
                System.out.println("Illegal move! " + e.getMessage());
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String x = scanner.next(Pattern.compile("\\d-\\d"));
            System.out.println(x);
        }
    }

}
