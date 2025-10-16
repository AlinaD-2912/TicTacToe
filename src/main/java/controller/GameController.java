/*
 * Name of the class: Game
 *
 * Description: this class is the one other games inherit from, it is abstract and it's abstract functions have to
 *              be present in the classes that inherit it
 *
 * Version: 1.0
 *
 * Date: 13/10/2025
 *
 * Copyright: moi
 */

package controller;

import model.design_pattern.Visitor;
import view.View;

import java.util.EnumSet;

public abstract class GameController {

    private int x;
    private int y;
    private String name;
    private State currentState = State.CONTINUING;
    private View view;
    private Visitor visitor;

    /**
     * Default constructor
     */
    public GameController() {

    }

    /**
     * Constructor with board dimensions
     *
     * @param x width of the board
     * @param y height of the board
     */
    public GameController(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Checks if the game is over
     *
     * @return boolean true if the game ended, false otherwise
     */
    public abstract boolean isOver ();

    /**
     * Starts the game loop
     */
    public abstract void play();

    public int getX() {
        return this.x;
    }
    public int getY() {
        return this.y;
    }



    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
    public void setVisitor(Visitor visitor) {
        this.visitor = visitor;
    }

    public void setGAME_NAME(String name) {
        this.name = name;
    }
    public void setState(State state) {
        this.currentState = state;
        // Immediately react to state change
        if(visitor != null) {
            currentState.accept(visitor, this);
        }
    }

    public State getState() {
        return currentState;
    }
    public String getName() {
        return this.name;
    }
    public View getView() {
        return view;
    }

}
