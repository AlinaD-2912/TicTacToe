/*
 * Name of the class: GameController
 *
 * Description: This abstract class is the base for all games.
 *              Other game classes inherit from it and must implement its abstract functions.
 *
 * Version: 1.0
 *
 * Date: 15/10/2025
 *
 * Copyright: moi
 */

package controller;

import model.design_pattern.Visitor;
import view.View;

public enum State implements Visitor {
    /**
     * Game is still ongoing
     */
    CONTINUING,

    /**
     * A player has won the game
     */
    PLAYER_WON,

    /**
     * The game ended in a draw
     */
    DRAW;

    public void accept(Visitor visitor, GameController game) {
        switch (this) {
            case CONTINUING -> visitor.visitContinuing(game);
            case PLAYER_WON -> visitor.visitPlayerWon(game);
            case DRAW -> visitor.visitDraw(game);
        }
    }

    @Override
    public void visitContinuing(GameController game) {

    }

    @Override
    public void visitPlayerWon(GameController game) {

    }

    @Override
    public void visitDraw(GameController game) {

    }
}