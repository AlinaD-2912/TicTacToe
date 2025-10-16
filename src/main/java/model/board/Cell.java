/*
 * Name of the class: Cell
 *
 * Description: this class is important as it is used to create the board for each game, and represent each cell of it
 *
 * Version: 2.0
 *
 * Date: 13/10/2025
 *
 * Copyright: moi
 */

package model.board;

import java.util.List;

public class Cell {

    private String representation;
    private List<int[]> coordinates;

    /**
     * Default constructor
     * Initializes the cell with an empty representation
     */
    public Cell() {
        this.representation = " ";
    }


    /**
     * Constructor with representation and coordinates.
     *
     * @param representation the symbol or content of the cell
     * @param coordinates the list of coordinates this cell represents
     */
    public Cell(String representation, List<int[]> coordinates) {
        this.representation = (representation == null ? " " : representation);;
        this.coordinates = coordinates;
    }

    /**
     * Checks if the cell is empty
     *
     * @return boolean true if the cell is empty, false otherwise
     */
    public boolean isEmpty() {
        return this.representation.equals(" ") ;
    }


    /**
     * Clears the cell by setting its representation to empty
     */
    public void clear() {
        this.representation = " ";
    }


    public String setRepresentation(String representation) {
        this.representation = representation;
        return representation;
    }

    public String getRepresentation() {
        return this.representation;
    }

    public List<int[]> getCoordinates() { return coordinates; }


}
