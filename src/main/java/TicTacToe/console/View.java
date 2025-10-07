package TicTacToe.console;

public class View {

    public View(){

    }

    public void messageBeginningOfTheGame () {
        System.out.println("======= Welcome to Tic-Tac-Toe! =======");
        System.out.println("---------------------------------------");
        System.out.println("Now choose what mode of game you prefer");
        System.out.println("1. 2 human players ");
        System.out.println("2. player vs artificial player");
        System.out.println("3. 2 artificial players");
        System.out.println("---------------------------------------");
        System.out.println("So what do you prefer (1,2 or 3): ");
    }

    public void pickXCoordinate() {
        System.out.println("Pick the coordinate for x (0,1,2): ");
    }
    public void pickYCoordinate() {
        System.out.println("Pick the coordinate for y (0,1,2): ");
    }

    public void pickPlayerRepresentation() {
        System.out.println("Pick (X or O): ");
    }

    public void warnings (int nb) {
        String [] warnings = {"Invalid Input. Please try again.", "x out of range", "y out of range", "The cell is already occupied"};
        System.out.println(warnings[nb]);
    }

    public void gameOverMessage (int message) {
        System.out.println("----- GAME OVER -----");

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
