package games.game;

import games.console.UserInteraction;
import games.console.View;
import games.game_engine.Board;
import games.players.ArtificialPlayer;
import games.players.HumanPlayer;

public class Connect4 extends Game {

    private int x = 6;
    private int y = 7;
    private String name = "Connect4";
    private Board board;
    private HumanPlayer currentPlayer;
    private View view;
    private UserInteraction interactionUtilisateur;
    private TicTacToe ticTacToe;

    public Connect4() {
        super(6,7 );
        board = new Board(x, y);
        view = new View();
        interactionUtilisateur = new UserInteraction();
        currentPlayer = new HumanPlayer("");
    }

    @Override
    public boolean isOver() {
        int symbolsRequired = 4;
        if (board.gameState(symbolsRequired) == Board.gameState.Draw)
        {
            view.gameOverMessage(1);
            board.display();
            return true;
        }
        if (board.gameState(symbolsRequired) == Board.gameState.RedCircle_Won) {
            view.gameOverMessage(4);
            board.display();
            return true;
        }
        if (board.gameState(symbolsRequired) == Board.gameState.YellowCircle_Won) {
            view.gameOverMessage(5);
            board.display();
            return true;
        }
        return false;
    }

    @Override
    public void play() {
        view.messageBeginningOfTheGameGomoku();
        currentPlayer = new HumanPlayer("\u001B[31m●\u001B[0m");
        board.setCurrentPlayer(currentPlayer);
        while(!isOver()) {
            board.display();
            int[] move = board.getMoveFromPlayer(1);
            int[] finalMove = board.findPositionBelow(move);
            board.setOwner(finalMove[0], finalMove[1], currentPlayer);
            board.switchPlayers(5);
        }
    }


    @Override
    public void setX(int x) {
        this.x = x;
    }
    @Override
    public void setY(int y) {
        this.y = y;
    }
    @Override
    public void setName(String s) {
        this.name = s;
    }




}
