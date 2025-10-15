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

import java.util.EnumSet;

public abstract class GameController {

    private int x;
    private int y;
    private String name;
    private State currentState = State.CONTINUING;

    public GameController() {

    }

    public GameController(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public abstract boolean isOver ();
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
    public void setName(String name) {
        this.name = name;
    }
    public void setState(State newState) {
        this.currentState = newState;
    }

    public State getState() {
        return this.currentState;
    }
    public String getName() {
        return this.name;
    }

}
