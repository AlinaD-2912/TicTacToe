import java.util.Scanner;
public class TicTacToe {

    private Board board;
    private Player currentPlayer;

    // Game engine
    public void play() {

        board = new Board();
        while(!isOver()) {
            // display
            // getMoveFromPlayer = x,y
            // getPlayerRepresentation = userInput (X, O)
            // setOwner - in parameters send getMoveFromPlayer and getPlayerRepresentation
            // switchPlayers - switches players

            board.display();
            currentPlayer = board.getPlayerRepresentation();
            int[] move = board.getMoveFromPlayer();
            board.setOwner(move[0], move[1], currentPlayer);
            board.switchPlayers();

        }

    }

    // Game over
    public boolean isOver () {
        if (board.gameState() == Board.gameState.Draw)
        {
            System.out.println("----- GAME OVER -----");
            System.out.println("        DRAW          ");
            board.display();
            return true;
        }
        if (board.gameState() == Board.gameState.Player_O_Won) {
            System.out.println("----- GAME OVER -----");
            System.out.println("    Player O Won          ");
            board.display();
            return true;
        }
        return false;

    }




}
