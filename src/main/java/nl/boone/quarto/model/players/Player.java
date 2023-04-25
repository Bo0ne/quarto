package nl.boone.quarto.model.players;

import nl.boone.quarto.model.Game;
import nl.boone.quarto.model.Move;
import nl.boone.quarto.model.Piece;

public abstract class Player {
    private String name;
    public abstract Move determineMove(Game game);

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
