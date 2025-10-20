/*
 * Name of the class: TicTacToe
 *
 * Description: this class is responsible for the logic and gaming process of TicTacToe,
 *              it determines in which way and order the board functions will be used, has 3 different modes of game,
 *              and inherits the same abstract functions from it's parent class
 *
 * Version: 3.0
 *
 * Date: 13/10/2025
 *
 * Copyright: moi
 */

package controller;

import model.board.Board;
import model.design_pattern.Strategy;
import model.design_pattern.Visitor;
import model.player.ArtificialPlayer;
import model.player.HumanPlayer;
import view.View;

public class TicTacToe extends GameController implements Visitor, Strategy {

    private int BOARD_SIZE = 3;
    private int symbolsAligned = 3;
    private String GAME_NAME = "TicTacToe";
    private int gameMode;


    // models
    private Board board;
    private HumanPlayer currentPlayer;
    private ArtificialPlayer currentArtificialPlayer;

    // view & input
    private View view;
    private UserInteraction userInteraction;

    public TicTacToe() {
        super(3, 3);
        board = new Board(BOARD_SIZE, BOARD_SIZE, this);
        view = new View();
        userInteraction = new UserInteraction();
        setGAME_NAME(GAME_NAME);

        super.setView(view);
        super.setVisitor(this);
    }


    /**
     * Game engine
     */
    @Override
    public void play() {
        view.messageBeginningOfTheGameTicTacToe();
        gameMode = userInteraction.userInputInt();
        if (gameMode == 1) {
            twoHumanPlayers();
        } else if (gameMode == 2) {
            humanVsArtificialPlayer();
        } else if (gameMode == 3) {
            artificialVsArtificial();
        } else {
            view.warnings(0);
        }
    }


    /**
     * Game over
     */
    @Override
    public boolean isOver() {

        Board.gameState result = board.gameState();

        switch (result) {
            case Draw -> {
                if (getState() != State.DRAW) {
                    setState(State.DRAW);
                }
            }
            case Player_O_Won, Player_X_Won -> {
                if (getState() != State.PLAYER_WON) {
                    setState(State.PLAYER_WON);
                }
            }
            default -> setState(State.CONTINUING);
        }

        return false;
    }


    /**
     * 2 human players mode
     */
    public void twoHumanPlayers() {
        currentPlayer = (HumanPlayer) board.getPlayerRepresentation(true);
        setState(State.CONTINUING);

        while (getState() == State.CONTINUING) {
            board.display();
            int[] move = board.getMoveFromPlayer(1);
            board.setOwner(move[0], move[1], currentPlayer);
            board.switchPlayers(1);

            if (isOver()) break;
        }
    }


    /**
     * 1 human vs 1 artificial player
     */
    public void humanVsArtificialPlayer() {
        currentPlayer = (HumanPlayer) board.getPlayerRepresentation(true);
        currentArtificialPlayer = (ArtificialPlayer) board.getPlayerRepresentation(false);
        setState(State.CONTINUING);

        while (getState() == State.CONTINUING) {
            board.display();
            int[] movePlayer = board.getMoveFromPlayer(1);
            board.setOwner(movePlayer[0], movePlayer[1], currentPlayer);
            board.switchPlayers(2);

            isOver();
            if (getState() != State.CONTINUING) break;

            int[] moveArtificialPlayer = board.getMoveFromPlayer(2);
            board.setOwner(moveArtificialPlayer[0], moveArtificialPlayer[1], currentArtificialPlayer);
            board.switchPlayers(2);

            if (isOver()) break;
        }
    }


    /**
     * artificial vs artificial player
     */
    public void artificialVsArtificial() {
        ArtificialPlayer ai1 = new ArtificialPlayer("X");
        ArtificialPlayer ai2 = new ArtificialPlayer("O");
        ArtificialPlayer currentArtificialPlayer = ai1;
        setState(State.CONTINUING);

        while (getState() == State.CONTINUING) {
            board.display();
            int[] move = board.getMoveFromPlayer(2);
            board.setOwner(move[0], move[1], currentArtificialPlayer);
            board.switchPlayers(3);

            if (currentArtificialPlayer == ai1) {
                currentArtificialPlayer = ai2;
            } else {
                currentArtificialPlayer = ai1;
            }

            if (isOver()) break;
        }
    }

    // setters
    @Override
    public void setX(int x) {
        this.BOARD_SIZE = x;
    }
    @Override
    public void setY(int y) {
        this.BOARD_SIZE = y;
    }


    // Visitor functions
    @Override
    public void visitContinuing(GameController game) {
    }
    @Override
    public void visitPlayerWon(GameController game) {
        TicTacToe ttt = (TicTacToe) game;
        ttt.board.display();

        Board.gameState result = ttt.board.gameState();
        if (result == Board.gameState.Player_X_Won) {
            game.getView().gameOverMessage(3);  // Player X Won
        } else if (result == Board.gameState.Player_O_Won) {
            game.getView().gameOverMessage(2);  // Player O Won
        }
    }
    @Override
    public void visitDraw(GameController game) {
        TicTacToe ttt = (TicTacToe) game;
        ttt.board.display();
        game.getView().gameOverMessage(1);
    }


    // Strategy function
    @Override
    public int getSymbolsAlignedRequired() {
        return symbolsAligned;
    }
}
