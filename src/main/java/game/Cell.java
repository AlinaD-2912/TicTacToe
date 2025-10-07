package game;

public class Cell {

    private String representation;

    public Cell() {
        this.representation = " ";
    }

    public String setRepresentation(String representation) {
        this.representation = representation;
        return representation;
    }

    public String getRepresentation() {
        return this.representation;
    }

    public boolean isEmpty() {
        return this.representation.equals(" ");
    }
}
