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
import model.player.HumanPlayer;
import view.View;

public class Connect4 extends GameController {

    private int x = 6;
    private int y = 7;
    private String GAME_NAME = "Connect4";
    private Board board;
    private HumanPlayer currentPlayer;
    private View view;
    private UserInteraction interactionUtilisateur;

    public Connect4() {
        super(6,7 );
        board = new Board(x, y);
        view = new View();
        interactionUtilisateur = new UserInteraction();
        currentPlayer = new HumanPlayer("");
        setState(State.CONTINUING);
        setGAME_NAME(GAME_NAME);
    }

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
