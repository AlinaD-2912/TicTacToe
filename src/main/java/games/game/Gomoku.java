package games.game;

import games.console.InteractionUtilisateur;
import games.console.View;
import games.game_engine.Board;
import games.players.ArtificialPlayer;
import games.players.HumanPlayer;

public class Gomoku extends Game {

    private int size = 15;
    private String name;
    private Board board;
    private HumanPlayer currentPlayer;
    private ArtificialPlayer currentArtificialPlayer;
    private View view;
    private InteractionUtilisateur interactionUtilisateur;

    public Gomoku() {
        super(15,15 );
        board = new Board(size, size);
        view = new View();
        interactionUtilisateur = new InteractionUtilisateur();
    }
    //●

    @Override
    public void play() {


    }

    @Override
    public void setX(int x) {
        this.size = x;
    }
    @Override
    public void setY(int y) {
        this.size = y;
    }
    @Override
    public void setName(String s) {
        this.name = s;
    }




}
