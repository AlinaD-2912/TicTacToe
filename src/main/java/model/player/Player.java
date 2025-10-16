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

    /**
     * Constructor with representation
     * Initializes a player with a given symbol
     *
     * @param representation the symbol or token used to represent the player on the board
     */
    public Player(String representation) {
        this.representation = representation;
    }


    /**
     * Default constructor
     * Initializes a player without setting a representation
     */
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

