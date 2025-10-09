package games.game;

public class Connect4 extends Game {

    private int x = 6;
    private int y = 7;
    private String name = "Connect4";

    public Connect4(int x, int y) {
        super(6, 7);
    }

    @Override
    public boolean isOver() {
        return false;
    }

    @Override
    public void setX(int x) {
        this.x = x;
    }
    @Override
    public void setY(int y) {
        this.y = y;
    }
    @Override
    public void setName(String s) {
        this.name = s;
    }

    @Override
    public void play() {

    }


}
