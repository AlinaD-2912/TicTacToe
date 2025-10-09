package games.game_engine;

import games.game_rules.MinMax;
import games.game_rules.Rules;
import games.players.ArtificialPlayer;
import games.players.HumanPlayer;
import games.players.Player;
import games.console.InteractionUtilisateur;
import games.console.View;

public class Board {

    private int sizeX;
    private int sizeY;
    private int x;
    private int y;
    public enum gameState { Draw, Player_X_Won, Player_O_Won, Default, Player_WhiteCircle_Won, Player_EmptyCircle_Won }

    private Cell[][] table;
    private HumanPlayer currentPlayer;
    private View view;
    private InteractionUtilisateur interactionUtilisateur;
    private ArtificialPlayer currentArtificialPlayer;
    private Rules rules;

    // Board initializer
    public Board(int sizeX, int sizeY) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        table = new Cell[sizeX][sizeY];
        for (int i = 0; i < sizeX; i++) {
            for (int j = 0; j < sizeY; j++) {
                table[i][j] = new Cell();
            }
        }
        currentArtificialPlayer = new ArtificialPlayer("X");
        view = new View();
        interactionUtilisateur = new InteractionUtilisateur();
    }

    public int getSizeX() {
        return this.sizeX;
    }

    public int getSizeY() {
        return this.sizeY;
    }

    public Cell[][] getTable() {
        return this.table;
    }

    public void setCurrentPlayer(HumanPlayer currentPlayer) {
        this.currentPlayer = currentPlayer;
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
        displayTire(sizeX, false);
        System.out.print("\n");
        for (int i = 0; i < sizeX; i++) {
            System.out.print("|");
            for (int j = 0; j < sizeY; j++) {
                System.out.print(" " + table[i][j].getRepresentation() + " ");
                if (j < sizeY + 1) System.out.print("|");
            }
            System.out.println();
            if (i < sizeX - 1) displayTire(sizeX, true);
        }
        displayTire(sizeX, true);
        System.out.print("\n");
    }

    // Player representation (X or O)
    public Player getPlayerRepresentation(boolean isHuman) {
        // check user input
        if (isHuman) {
            view.pickPlayerRepresentation();
            //loop on user input
            while (true) {
                view.pickPlayerRepresentation();
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
                if (x < 0 || x >= sizeX) {
                    view.warnings(1);
                    continue;
                }
                //check y = column
                if (y < 0 || y >= sizeY) {
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
        // switch 2 human players
        if (gameMode == 1) {
            if (currentPlayer.getRepresentation().equals("X")) {
                currentPlayer.setRepresentation("O");
            } else {
                currentPlayer.setRepresentation("X");
            }
        }
        // switch human and artificial players
        if (gameMode == 2) {
            if (currentPlayer.getRepresentation().equals("O")) {
                currentArtificialPlayer.setRepresentation("X");
            }
            if (currentArtificialPlayer.getRepresentation().equals("X")) {
                currentArtificialPlayer.setRepresentation("O");
            }
        }
        // switch 2 artificial players
        if (gameMode == 3) {
            if (currentArtificialPlayer.getRepresentation().equals("X")) {
                currentArtificialPlayer.setRepresentation("O");
            } else {
                currentArtificialPlayer.setRepresentation("X");
            }
        }
        // switch players gomoku
        if (gameMode == 4) {
            if (currentPlayer.getRepresentation().equals("●")) {
                currentPlayer.setRepresentation("○");
            }else {
                currentPlayer.setRepresentation("●");
            }
        }
    }

    // Returns current state of the game
    public gameState gameState (int symbolsAlignedRequired) {
        rules =  new Rules();
        Cell result = rules.findAlignedCells(table, sizeX, sizeY, symbolsAlignedRequired);
        if (result != null && !result.isEmpty()) {
            String winner = result.getRepresentation();
            if (winner.equals("X")) {
                return gameState.Player_X_Won;
            } else if (winner.equals("O")) {
                return gameState.Player_O_Won;
            }else if (winner.equals("●")) {
                return gameState.Player_WhiteCircle_Won;
            }else if (winner.equals("○")) {
                return gameState.Player_EmptyCircle_Won;
            }
        }
        if (rules.isBoardFull(sizeX, table) ) {
            return gameState.Draw;
        }
        if (symbolsAlignedRequired <= 0) {
            throw new IllegalArgumentException("symbolsAlignedRequired must be >= 1");
        }

        return gameState.Default;
    }

}
