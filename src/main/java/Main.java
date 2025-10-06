import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        TicTacToe ticTacToe = new TicTacToe();
        ticTacToe.display();
        Player player = new Player(" O ");
        int[] t = ticTacToe.getMoveFromPlayer();
        System.out.println(t[0] + " " + t[1]);
        ticTacToe.setOwner(t[0], t[1], player);
        ticTacToe.display();

        int[] t2 = ticTacToe.getMoveFromPlayer();
        ticTacToe.setOwner(t2[0], t2[1], player);
        ticTacToe.display();
    }
}
