package game;

import players.ArtificialPlayer;
import players.HumanPlayer;
import players.Player;
import console.InteractionUtilisateur;
import console.View;

import java.security.SecureRandom;
public class Board {

    private int size = 3;
    private int x;
    private int y;
    private Cell[][] table;
    private HumanPlayer currentPlayer;
    private View view;
    private InteractionUtilisateur interactionUtilisateur;

    private ArtificialPlayer currentArtificialPlayer;
    public enum gameState { Draw, Player_X_Won, Player_O_Won, Default }
    private Conditions conditions;
    private SecureRandom secureRandom = new SecureRandom();
    // Board initializer
    public Board() {
        table = new Cell[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                table[i][j] = new Cell();
            }
        }
        currentArtificialPlayer = new ArtificialPlayer("X");
        view = new View();
        interactionUtilisateur = new InteractionUtilisateur();

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
    public Player getPlayerRepresentation(boolean isHuman) {
        // check user input
        if (isHuman) {
            view.pickPlayerRepresentation();

            //loop on user input
            while (true) {
                String userInput = interactionUtilisateur.userInputString();
                if (userInput.equals("X") || userInput.equals("O")) {
                    currentPlayer = new HumanPlayer(userInput);
                    break;
                } else {
                    view.warnings(0);
                    view.pickPlayerRepresentation();
                }
            }
            return currentPlayer;
        }

        else {
            if (currentPlayer.getRepresentation().equals("X")) {
                currentArtificialPlayer.setRepresentation("O");
            }
            else {
                currentArtificialPlayer.setRepresentation("X");
            }return currentArtificialPlayer;

        }

    }

    // Chosen coordinates x, y
    public int[] getMoveFromPlayer (int gameMode) {

        if (gameMode == 1) {
            //loop on coordinates
            while (true) {
                view.pickXCoordinate();
                x = interactionUtilisateur.userInputInt();
                view.pickYCoordinate();
                y = interactionUtilisateur.userInputInt();

                //check x = row
                if (x < 0 || x >= size) {
                    view.warnings(1);
                    continue;
                }
                //check y = column
                if (y < 0 || y >= size) {
                    view.warnings(2);
                    continue;
                }
                //check if empty
                if (table[x][y].getRepresentation().equals("X") || table[x][y].getRepresentation().equals("O")) {
                    view.warnings(3);
                    continue;
                }
                return new int[]{x, y};
            }
        }

        if (gameMode == 2) {
            //loop on coordinates
            while (true) {
                x = secureRandom.nextInt(size);
                y = secureRandom.nextInt(size);

                // check if empty
                if (table[x][y].getRepresentation().equals("X") ||
                        table[x][y].getRepresentation().equals("O")) {
                    continue;
                }

                return new int[]{x, y};
            }
        }
        return null;

    }

    // Owner of the cell by x,y and chosen player representation
    public void setOwner(int row,  int col, Player player) {
        table[row][col].setRepresentation(player.getRepresentation());
    }

    // Players switching
    public void switchPlayers (int gameMode) {
        currentArtificialPlayer = new ArtificialPlayer(" ");

        if (gameMode == 1) {
            if (currentPlayer.getRepresentation().equals("X")) {
                currentPlayer.setRepresentation("O");
            } else {
                currentPlayer.setRepresentation("X");
            }
        }

        if (gameMode == 2) {
            if (currentPlayer.getRepresentation().equals("O")) {
                currentArtificialPlayer.setRepresentation("X");
            }
            if (currentArtificialPlayer.getRepresentation().equals("X")) {
                currentArtificialPlayer.setRepresentation("O");
            }
        }

        if (gameMode == 3) {
            if (currentArtificialPlayer.getRepresentation().equals("X")) {
                currentArtificialPlayer.setRepresentation("O");
            } else {
                currentArtificialPlayer.setRepresentation("X");
            }
        }

    }

    // Returns current state of the game
    public gameState gameState () {
        conditions =  new Conditions();

        if (conditions.isBoardFull(size, table) && !conditions.is3SymbolsAlligned(size, table)) {
            return gameState.Draw;
        }
        String winner = conditions.getWinner(size, table);
        if (conditions.is3SymbolsAlligned(size, table)) {
            if (winner == "X") {
                return gameState.Player_X_Won;
            }
            else if (winner == "O") {
                return gameState.Player_O_Won;
            }
        }
        return gameState.Default;
    }

}
