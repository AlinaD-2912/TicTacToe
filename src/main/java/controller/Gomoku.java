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
import model.design_pattern.Strategy;
import model.player.HumanPlayer;
import view.View;

public class Gomoku extends GameController implements Strategy {

    private int BOARD_SIZE = 15;
    private String GAME_NAME;
    private Board board;
    private HumanPlayer currentPlayer;
    private View view;

    /**
     * Constructor of Gomoku
     * Initializes the board, view, current player, and sets the game state to CONTINUING
     */
    public Gomoku() {
        super(15,15 );
        board = new Board(BOARD_SIZE, BOARD_SIZE);
        view = new View();
        currentPlayer = new HumanPlayer("");
        setState(State.CONTINUING);
        setGAME_NAME(GAME_NAME);
    }

    /**
     * Checks if the game is over
     * A game is over if a player has aligned 5 symbols or if it is a draw
     *
     * @return boolean true if the game ended, false if it is still continuing
     */
    @Override
    public boolean isOver() {
        int symbolsRequired = 5;
        Board.gameState result = board.gameState(symbolsRequired);

        switch (result) {
            case Draw -> {
                setState(State.DRAW);
                view.gameOverMessage(1);
                board.display();
                return true;
            }
            case Player_WhiteCircle_Won, Player_EmptyCircle_Won -> {
                setState(State.PLAYER_WON);
                view.gameOverMessage(4);
                board.display();
                return true;
            }
            default -> {
                setState(State.CONTINUING);
                return false;
            }
        }
    }

    /**
     * Game engine for Gomoku
     * Handles player turns and updates the board until the game is over
     */
    @Override
    public void play() {
        view.messageBeginningOfTheGameGomoku();
        currentPlayer = new HumanPlayer("●");
        board.setCurrentPlayer(currentPlayer);
        setState(State.CONTINUING);

        while (getState() == State.CONTINUING) {
            board.display();
            int[] move = board.getMoveFromPlayer(1);
            board.setOwner(move[0], move[1], currentPlayer);
            board.switchPlayers(4);

            isOver();
        }
    }

    @Override
    public void setX(int x) {
        this.BOARD_SIZE = x;
    }
    @Override
    public void setY(int y) {
        this.BOARD_SIZE = y;
    }
    @Override
    public void setGAME_NAME(String s) {
        this.GAME_NAME = s;
    }




}
