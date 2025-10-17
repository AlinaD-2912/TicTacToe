/*
 * Name of the class: Connect4
 *
 * Description: this class is responsible for the logic of gaming process for the game Connect4,
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

public class Connect4 extends GameController implements Strategy {

    private int x = 6;
    private int y = 7;
    private String GAME_NAME = "Connect4";
    private Board board;
    private HumanPlayer currentPlayer;
    private View view;
    private UserInteraction interactionUtilisateur;

    /**
     * Constructor of Connect4
     * Initializes the board, view, user interaction, current player, and sets the game state to CONTINUING
     */
    public Connect4() {
        super(6,7 );
        board = new Board(x, y);
        view = new View();
        interactionUtilisateur = new UserInteraction();
        currentPlayer = new HumanPlayer("");
        setState(State.CONTINUING);
        setGAME_NAME(GAME_NAME);
    }


    /**
     * Checks if the game is over.
     * A game is over if a player has aligned 4 symbols or if it is a draw
     *
     * @return boolean true if the game ended, false if it is still continuing
     */
    @Override
    public boolean isOver() {
        int symbolsRequired = 4;
        Board.gameState result = board.gameState(symbolsRequired);

        switch (result) {
            case Draw -> {
                setState(State.DRAW);
                view.gameOverMessage(1);
                board.display();
                return true;
            }
            case RedCircle_Won, YellowCircle_Won -> {
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
     * Game engine for Connect4
     * Handles player turns, places discs in the correct position, and updates the board until the game is over
     */
    @Override
    public void play() {
        view.messageBeginningOfTheGameGomoku();
        currentPlayer = new HumanPlayer("\u001B[31m●\u001B[0m");
        board.setCurrentPlayer(currentPlayer);
        setState(State.CONTINUING);

        while (getState() == State.CONTINUING) {
            board.display();
            int[] move = board.getMoveFromPlayer(1);
            int[] finalMove = board.findPositionBelow(move);
            board.setOwner(finalMove[0], finalMove[1], currentPlayer);
            board.switchPlayers(5);

            isOver();
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
    public void setGAME_NAME(String s) {
        this.GAME_NAME = s;
    }

}
