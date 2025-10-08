package Games.game_engine;

import Games.game_rules.Rules;
import Games.players.ArtificialPlayer;
import Games.players.HumanPlayer;
import Games.players.Player;
import Games.console.InteractionUtilisateur;
import Games.console.View;

import java.security.SecureRandom;
public class Board {

    private int size = 3;
    private int x = 3;
    private int y = 3;
    public enum gameState { Draw, Player_X_Won, Player_O_Won, Default }

    private Cell[][] table;
    private HumanPlayer currentPlayer;
    private View view;
    private InteractionUtilisateur interactionUtilisateur;
    private ArtificialPlayer currentArtificialPlayer;
    private Rules rules;
    private SecureRandom secureRandom = new SecureRandom();

    // Board initializer
    public Board() {
        table = new Cell[x][y];
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                table[i][j] = new Cell();
            }
        }
        currentArtificialPlayer = new ArtificialPlayer("X");
        view = new View();
        interactionUtilisateur = new InteractionUtilisateur();
    }

    public int getSize() {
        return this.size;
    }

    public Cell[][] getTable() {
        return this.table;
    }

    public void displayTire(int size, boolean newLine) {
        if (!newLine) {
            System.out.print("-");
            for (int i = 0; i < size; i++) {
                System.out.print("----");
            }
        }
        else  {
            System.out.print("-");
            for (int i = 0; i < size; i++) {
                System.out.print("----");

            }System.out.print("\n");
        }
    }

    // Display Board
    public void display() {
        displayTire(x, false);
        System.out.print("\n");
        for (int i = 0; i < x; i++) {
            System.out.print("|");
            for (int j = 0; j < y; j++) {
                System.out.print(" " + table[i][j].getRepresentation() + " ");
                if (j < y + 1) System.out.print("|");
            }
            System.out.println();
            if (i < x - 1) displayTire(x, true);
        }
        displayTire(x, true);
        System.out.print("\n");
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
//            currentArtificialPlayer.getRepresentation();
//            while (true) {
//                x = secureRandom.nextInt(size);
//                y = secureRandom.nextInt(size);
//                // if empty, choose another cell
//                if (table[x][y].getRepresentation().equals("X") ||
//                        table[x][y].getRepresentation().equals("O")) {
//                    continue;
//                }
//                return new int[]{x, y};
//            }
            MinMax aiLogic = new MinMax();
            Player aiPlayer = currentArtificialPlayer;
            Player opponent = currentPlayer;

            int[] bestMove = aiLogic.getBestMove(table, aiPlayer, opponent);
            return bestMove;

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
        rules =  new Rules();
        Cell result = rules.findAlignedCells(table, size, size);
        if (result != null && !result.isEmpty()) {
            String winner = result.getRepresentation();
            if (winner.equals("X")) {
                return gameState.Player_X_Won;
            } else if (winner.equals("O")) {
                return gameState.Player_O_Won;
            }
        }
        if (rules.isBoardFull(size, table) ) {
            return gameState.Draw;
        }

        return gameState.Default;
    }

}
