package games.console;

public class View {

    public View(){

    }

    public void messageBeginningOfTheGame() {
        System.out.println("\u001B[33m ========= Welcome ! =========\u001B[0m");
        System.out.println("---------------------------------------");
        System.out.println("\u001B[33m Choose the game you would like to play\u001B[0m");
        System.out.println("\u001B[32m 1. TicTacToe\u001B[0m");
        System.out.println("\u001B[35m 2. Gomoku\u001B[0m");
        System.out.println("\u001B[36m 3. Puissance 4\u001B[0m");
        System.out.println("---------------------------------------");
        System.out.println("\u001B[33m So what do you prefer (1,2 or 3): \u001B[0m");
    }
    public void messageBeginningOfTheGameTicTacToe () {
        System.out.println("\u001B[32m======= Welcome to Tic-Tac-Toe! =======\u001B[0m");
        System.out.println("\u001B[32m---------------------------------------\u001B[0m");
        System.out.println("\u001B[32mNow choose what mode of game you prefer\u001B[0m");
        System.out.println("\u001B[33m 1. 2 human players \u001B[0m");
        System.out.println("\u001B[33m 2. player vs artificial player\u001B[0m");
        System.out.println("\u001B[33m 3. 2 artificial players\u001B[0m");
        System.out.println("\u001B[32m---------------------------------------\u001B[0m");
        System.out.println("\u001B[32mSo what do you prefer (1,2 or 3): \u001B[0m");
    }

    public void pickXCoordinate() {
        System.out.println("Pick the coordinate for x (0,1,2...): ");
    }
    public void pickYCoordinate() {
        System.out.println("Pick the coordinate for y (0,1,2...): ");
    }

    public void pickPlayerRepresentation() {
        System.out.println("Pick (X or O): ");
    }

    public void warnings (int nb) {
        String [] warnings = {"Invalid Input. Please try again.", "x out of range", "y out of range", "The cell is already occupied"};
        System.out.println(warnings[nb]);
    }

    public void gameOverMessage (int message) {
        System.out.println("\n----- GAME OVER -----");

        if(message == 1) {
            System.out.println("        DRAW          ");
        }
        if (message == 2) {
            System.out.println("    Player O Won          ");
        }
        if (message == 3) {
            System.out.println("    Player X Won          ");
        }

    }


}
