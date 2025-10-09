package games.game;

import games.game_engine.Board;
import games.players.ArtificialPlayer;
import games.players.HumanPlayer;
import games.console.InteractionUtilisateur;
import games.console.View;

public class TicTacToe extends Game {

    private int size = 3;
    private String name = "TicTacToe";
    private Board board;
    private HumanPlayer currentPlayer;
    private ArtificialPlayer currentArtificialPlayer;
    private View view;
    private InteractionUtilisateur interactionUtilisateur;

    public TicTacToe() {
        super(3,3 );
        board = new Board(size, size);
        view = new View();
        interactionUtilisateur = new InteractionUtilisateur();
    }


    // Game engine
    @Override
    public void play() {
        view.messageBeginningOfTheGameTicTacToe();
        int input = interactionUtilisateur.userInputInt();
        if (input == 1) {
            twoHumanPlayers();
        }
        if (input == 2) {
            humanVsArtificialPlayer();
        }
        if (input == 3) {
            artificialVsArtificial();
        }
        else {
            view.warnings(0);
        }
    }

    // Game over
    @Override
    public boolean isOver () {
        int symbolRequired = 3;
        if (board.gameState(symbolRequired) == Board.gameState.Draw)
        {
            view.gameOverMessage(1);
            board.display();
            return true;
        }
        if (board.gameState(symbolRequired) == Board.gameState.Player_O_Won) {
            view.gameOverMessage(2);
            board.display();
            return true;
        }
        if (board.gameState(symbolRequired) == Board.gameState.Player_X_Won) {
            view.gameOverMessage(3);
            board.display();
            return true;
        }
        return false;
    }

    // 2 human players mode
    public void twoHumanPlayers() {
        currentPlayer = (HumanPlayer) board.getPlayerRepresentation(true);
        while(!isOver()) {
            board.display();
            int[] move = board.getMoveFromPlayer(1);
            board.setOwner(move[0], move[1], currentPlayer);
            board.switchPlayers(1);
        }
    }

    // 1 human vs 1 artificial player
    public void humanVsArtificialPlayer() {
        currentPlayer = (HumanPlayer) board.getPlayerRepresentation(true);
        currentArtificialPlayer = (ArtificialPlayer) board.getPlayerRepresentation(false);

        while(!isOver()) {
            board.display();
            int[] movePlayer = board.getMoveFromPlayer(1);
            board.setOwner(movePlayer[0], movePlayer[1], currentPlayer);
            board.switchPlayers(2);
            int[] moveArtificialPlayer = board.getMoveFromPlayer(2);
            board.setOwner(moveArtificialPlayer[0], moveArtificialPlayer[1], currentArtificialPlayer);
            board.switchPlayers(2);

        }
    }

    public void artificialVsArtificial() {
        ArtificialPlayer ai1 = new ArtificialPlayer("X");
        ArtificialPlayer ai2 = new ArtificialPlayer("O");
        ArtificialPlayer currentArtificialPlayer = ai1;

        while (!isOver()) {
            board.display();

            int[] move = board.getMoveFromPlayer(2);
            board.setOwner(move[0], move[1], currentArtificialPlayer);
            board.switchPlayers(3);

            if (currentArtificialPlayer == ai1) {
                currentArtificialPlayer = ai2;
            } else {
                currentArtificialPlayer = ai1;
            }
        }
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
