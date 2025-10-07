import java.util.Scanner;
public class Board {

    private int size = 3;
    private int x;
    private int y;
    private Cell[][] table;
    private Player currentPlayer;
    private Scanner scanner;
    public enum gameState { Draw, Player_X_Won, Player_O_Won, Default }
    private Conditions conditions;

    // Board initializer
    public Board() {
        table = new Cell[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                table[i][j] = new Cell();
            }
        }
        scanner = new Scanner(System.in);

    }

    // Display Board
    public void display() {
        System.out.println("--------------");
        for (int i = 0; i < size; i++) {
            System.out.print("|");
            for (int j = 0; j < size; j++) {
                System.out.print(" " + table[i][j].getRepresentation() + " ");
                if (j < size + 1) System.out.print("|");
            }
            System.out.println();
            if (i < size - 1) System.out.println("--------------");
        }
        System.out.println("--------------");
    }

    // Player representation (X or O)
    public Player getPlayerRepresentation() {
        // check user input
        System.out.println("Pick (X or O): ");

        //loop on user input
        while (true) {
            String userInput = scanner.nextLine();
            if (userInput.equals("X") || userInput.equals("O")) {
                currentPlayer = new Player(userInput);
                break;
            } else {
                System.out.println("Invalid Input. Please try again.");
                System.out.println("Pick (X or O): ");
            }
        }
        return currentPlayer;
    }

    // Chosen coordinates x, y
    public int[] getMoveFromPlayer () {

        //loop on coordinates
        while (true) {
            System.out.println("Pick the coordinate for x (0,1,2): ");
            x = scanner.nextInt();
            System.out.println("Pick the coordinate for y (0,1,2): ");
            y = scanner.nextInt();

            //check x = row
            if (x < 0 || x >= size) {
                System.out.println("x out of range");
                continue;
            }
            //check y = column
            if (y < 0 || y >= size) {
                System.out.println("y out of range");
                continue;
            }
            //check if empty
            if (table[x][y].getRepresentation().equals("X") || table[x][y].getRepresentation().equals("O")) {
                System.out.println("The cell is already occupied");
                continue;
            }
            return new int[]{x, y};

        }
    }

    // Owner of the cell by x,y and chosen player representation
    public void setOwner(int row,  int col, Player player) {
        table[row][col].setRepresentation(player.getRepresentation());
    }

    // Players switching
    public void switchPlayers () {
        if (currentPlayer.getRepresentation().equals("X")) {
            currentPlayer.setRepresentation("O");
        } else {
            currentPlayer.setRepresentation("X");
        }

    }

    // Returns current state of the game
    public gameState gameState () {
        conditions =  new Conditions();

        if (conditions.isBoardFull(size, table) && !conditions.is3SymbolsAlligned(size, table)) {
            return gameState.Draw;
        }
        if (conditions.is3SymbolsAlligned(size, table)) {
            return gameState.Player_O_Won;
        }

        return gameState.Default;

    }


}
