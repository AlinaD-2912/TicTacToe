/*
 * Name of the class: Gomoku
 *
 * Description: this class is responsible for the logic and gaming process of Gomoku,
 *              it determines in which way the board functions will be used, and has its own conditions for isOver
 *
 * Version: 1.0
 *
 * Date: 13/10/2025
 *
 * Copyright: moi
 */

package controller;
import model.board.Board;
import model.player.HumanPlayer;
import view.View;

public class GomokuController extends GameController {

    private int size = 15;
    private String name;
    private Board board;
    private HumanPlayer currentPlayer;
    private View view;
    private TicTacToeController ticTacToe;

    public GomokuController() {
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
