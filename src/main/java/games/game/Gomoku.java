package games.game;

import games.console.UserInteraction;
import games.console.View;
import games.game_engine.Board;
import games.players.ArtificialPlayer;
import games.players.HumanPlayer;

public class Gomoku extends Game {

    private int size = 15;
    private String name;
    private Board board;
    private HumanPlayer currentPlayer;
    private View view;
    private TicTacToe ticTacToe;

    public Gomoku() {
        super(15,15 );
        board = new Board(size, size);
        view = new View();
        currentPlayer = new HumanPlayer("");
    }

    @Override
    public boolean isOver() {
        int symbolsRequired = 5;
        if (board.gameState(symbolsRequired) == Board.gameState.Draw)
        {
            view.gameOverMessage(1);
            board.display();
            return true;
        }
        if (board.gameState(symbolsRequired) == Board.gameState.Player_WhiteCircle_Won) {
            view.gameOverMessage(4);
            board.display();
            return true;
        }
        if (board.gameState(symbolsRequired) == Board.gameState.Player_EmptyCircle_Won) {
            view.gameOverMessage(5);
            board.display();
            return true;
        }
        return false;
    }


    @Override
    public void play() {
        view.messageBeginningOfTheGameGomoku();
        currentPlayer = new HumanPlayer("●");
        board.setCurrentPlayer(currentPlayer);
        while(!isOver()) {
            board.display();
            int[] move = board.getMoveFromPlayer(1);
            board.setOwner(move[0], move[1], currentPlayer);
            board.switchPlayers(4);
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
