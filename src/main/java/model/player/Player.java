/*
 * Name of the class: Artificial player
 *
 * Description: this class is the one other instances of Player inherit from, it contains all of the needed functions,
 *              that can be reused easily by it's child classes.
 *
 * Version: 1.0
 *
 * Date: 13/10/2025
 *
 * Copyright: moi
 */

package model.player;

public class Player {

    private String representation;

    public Player(String representation) {
        this.representation = representation;
    }

    public Player() {

    }

    public String setRepresentation(String representation) {
        this.representation = representation;
        return representation;
    }

    public String getRepresentation() {
        return this.representation;
    }
}

