package games.game_engine;

import java.util.List;

public class Cell {

    private String representation;
    private List<int[]> coordinates;

    public Cell() {
        this.representation = " ";
    }

    public Cell(String representation, List<int[]> coordinates) {
        this.representation = (representation == null ? " " : representation);;
        this.coordinates = coordinates;
    }

    public String setRepresentation(String representation) {
        this.representation = representation;
        return representation;
    }

    public String getRepresentation() {
        return this.representation;
    }

    public List<int[]> getCoordinates() { return coordinates; }

    public boolean isEmpty() {
        return this.representation.equals(" ") ;
    }

    public void clear() {
        this.representation = " ";
    }

}
