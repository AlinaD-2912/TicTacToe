package Games.players;

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
