/*
 * Name of the interface: Visitor
 *
 * Description: This interface defines the Visitor design pattern for different game states.
 *              Each method is called depending on the current state of the game.
 *
 * Version: 1.0
 *
 * Date: 16/10/2025
 *
 * Copyright: moi
 */

package model.design_pattern;

import controller.GameController;


public interface Visitor {
    /**
     * Called when the game is continuing
     *
     * @param game the game controller on which this state is applied
     */
    void visitContinuing(GameController game);

    /**
     * Called when a player has won the game
     *
     * @param game the game controller on which this state is applied
     */
    void visitPlayerWon(GameController game);

    /**
     * Called when the game ended in a draw
     *
     * @param game the game controller on which this state is applied
     */
    void visitDraw(GameController game);
}
