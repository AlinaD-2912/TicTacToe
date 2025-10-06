import java.util.Scanner;
public class TicTacToe {

    private int size = 3;
    private int x;
    private int y;
    private Cell[][] table;
    private Player player;
    private Scanner scanner;

    public TicTacToe() {
        table = new Cell[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                table[i][j] = new Cell();
            }
        }
        scanner = new Scanner(System.in);

    }

    public void display() {
        System.out.println("--------------");
        for (int i = 0; i < size; i++) {
            System.out.print("|");
            for (int j = 0; j < size; j++) {
                System.out.print(table[i][j].getRepresentation());
                if (j < size + 1) System.out.print("|");
            }
            System.out.println();
            //System.out.print("|");

            if (i < size - 1) System.out.println("--------------");
        }
        System.out.println("--------------");
    }

    public int[] getMoveFromPlayer () {
        // check user input
        System.out.println("Pick (X or O): ");

        //loop on user input
        while (true) {
            String userInput = scanner.nextLine();
            if (userInput.equals("X") || userInput.equals("O")) {
                player = new Player(userInput);
                break;
            } else {
                System.out.println("Invalid Input. Please try again.");
                System.out.println("Pick (X or O): ");
            }
        }

        //loop on coordinates
        while (true) {
            System.out.println("Pick the coordinate for x (0,1,2): ");
            int x = scanner.nextInt();
            System.out.println("Pick the coordinate for y (0,1,2): ");
            int y = scanner.nextInt();

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

    public void setOwner(int row,  int col, Player player) {
        table[row][col].setRepresentation(player.getRepresentation());
    }


}
