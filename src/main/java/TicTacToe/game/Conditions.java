package TicTacToe.game;

public class Conditions {

    // Check the fullness of the board
    public boolean isBoardFull(int size, Cell[][] table) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (table[i][j].getRepresentation().equals(" ")) {
                    return false;
                }
            }
        }
        return true;
    }

    // Check if the 3 symbols are aligned
    public boolean is3SymbolsAlligned(int size, Cell[][] table) {
        // check rows
        for (int i = 0; i < size; i++) {
            // from left to right
            if (table[i][0].getRepresentation().equals(table[i][1].getRepresentation()) && table[i][1].getRepresentation().equals(table[i][2].getRepresentation()) && !table[i][0].isEmpty()) {
                return true;
            }
        }

        // check columns
        for (int j = 0; j < size; j++) {
            if (table[0][j].getRepresentation().equals(table[1][j].getRepresentation()) && table[1][j].getRepresentation().equals(table[2][j].getRepresentation()) && !table[0][j].isEmpty()) {
                return true;
            }
        }

        // check diagonally
        for (int j = 0; j < size; j++) {
            if (table[0][0].getRepresentation().equals(table[1][1].getRepresentation()) && table[1][1].getRepresentation().equals(table[2][2].getRepresentation()) && !table[0][0].isEmpty()) {
                return true;
            }
            if (table[0][2].getRepresentation().equals(table[1][1].getRepresentation()) && table[1][1].getRepresentation().equals(table[2][0].getRepresentation()) && !table[0][2].isEmpty()) {
                return true;
            }
        }
        return false;
    }

    // Check if the 3 symbols are aligned
    public String getWinner(int size, Cell[][] table) {
        // check rows
        for (int i = 0; i < size; i++) {
            // from left to right
            if (table[i][0].getRepresentation().equals(table[i][1].getRepresentation()) && table[i][1].getRepresentation().equals(table[i][2].getRepresentation()) && !table[i][0].isEmpty()) {
                return table[i][0].getRepresentation();
            }
        }

        // check columns
        for (int j = 0; j < size; j++) {
            if (table[0][j].getRepresentation().equals(table[1][j].getRepresentation()) && table[1][j].getRepresentation().equals(table[2][j].getRepresentation()) && !table[0][j].isEmpty()) {
                return table[0][j].getRepresentation();
            }
        }

        // check diagonally
        for (int j = 0; j < size; j++) {
            if (table[0][0].getRepresentation().equals(table[1][1].getRepresentation()) && table[1][1].getRepresentation().equals(table[2][2].getRepresentation()) && !table[0][0].isEmpty()) {
                return table[0][0].getRepresentation();
            }
            if (table[0][2].getRepresentation().equals(table[1][1].getRepresentation()) && table[1][1].getRepresentation().equals(table[2][0].getRepresentation()) && !table[0][2].isEmpty()) {
                return table[0][2].getRepresentation();
            }
        }
        return null;
    }


}

