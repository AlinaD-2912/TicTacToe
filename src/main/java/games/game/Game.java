package games.game;

import games.console.InteractionUtilisateur;
import games.console.View;


public abstract class Game {

    private int x;
    private int y;
    private String name;

    public Game() {
    }

    public Game(int x, int y) {
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

    public String getName() {
        return this.name;
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

}
