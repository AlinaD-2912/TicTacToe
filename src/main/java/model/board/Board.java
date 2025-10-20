/*
 * Name of the class: Board
 *
 * Description: this class is responsible for all the processes happening on board, it displays board, get player's moves
 *              it provides player's representation, then assigns the representation of the current player to the cell
 *              that he chosen, it determines the game state and switches players. This class is a game engine, it has
 *              every needed method to build new game with reusable functions.
 *
 * Version: 2.0
 *
 * Date: 13/10/2025
 *
 * Copyright: moi
 */

package model.board;

import controller.UserInteraction;
import model.design_pattern.Strategy;
import model.player.ArtificialPlayer;
import model.player.HumanPlayer;
import model.player.Player;
import view.View;

public class Board {

    private int sizeX;
    private int sizeY;
    private int x;
    private int y;

    public enum gameState { Draw, Player_X_Won, Player_O_Won, Default, Player_WhiteCircle_Won, Player_EmptyCircle_Won, RedCircle_Won, YellowCircle_Won }

    private Cell[][] table;
    private HumanPlayer currentPlayer;
    private View view;
    private UserInteraction interactionUtilisateur;
    private ArtificialPlayer currentArtificialPlayer;
    private Rules rules;
    private Strategy currentGame;


    /**
     * Board initializer and constructor
     */
    public Board(int sizeX, int sizeY, Strategy currentGame) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.currentGame = currentGame;

        table = new Cell[sizeX][sizeY];
        for (int i = 0; i < sizeX; i++) {
            for (int j = 0; j < sizeY; j++) {
                table[i][j] = new Cell();
            }
        }
        currentArtificialPlayer = new ArtificialPlayer("X");
        view = new View();
        interactionUtilisateur = new UserInteraction();

    }

    /**
     * Helper function to print borders of board
     */
    public void displayTable(int size, boolean newLine) {
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


    /**
     * Prints board
     */
    public void display() {
        displayTable(sizeY, false);
        System.out.print("\n");
        for (int i = 0; i < sizeX; i++) {
            System.out.print("|");
            for (int j = 0; j < sizeY; j++) {
                System.out.print(" " + table[i][j].getRepresentation() + " ");
                if (j < sizeY + 1) System.out.print("|");
            }
            System.out.println();
            if (i < sizeX - 1) displayTable(sizeY, true);
        }
        displayTable(sizeY, true);
        System.out.print("\n");
    }

    /**
     * Gets player representation (X or O)
     */
    public Player getPlayerRepresentation(boolean isHuman) {
        // check user input
        if (isHuman) {
            while (true) {
                view.pickPlayerRepresentation();
                String userInput = interactionUtilisateur.userInputString();
                if (userInput.equals("X") || userInput.equals("O")) {
                    currentPlayer = new HumanPlayer(userInput);
                    break;
                } else {
                    view.warnings(0);
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


    /**
     * Cheks the coordinates chosen by player, and decides if he can move there
     */
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
            MinMax aiLogic = new MinMax(sizeX, sizeY, currentGame.getSymbolsAlignedRequired());
            Player aiPlayer = currentArtificialPlayer;
            Player opponent = currentPlayer;

            Coord bestMoveCoord = aiLogic.getBestMove(table, aiPlayer, opponent);
            return new int[]{bestMoveCoord.row(), bestMoveCoord.col()};
        }

        return null;
    }


    /**
     * Gomoku special function to find the lowest position of the cell
     */
    public int[] findPositionBelow(int[] position) {
        int column = position[1];

        // start from the bottom row
        for (int row = sizeX - 1; row >= 0; row--) {
            if (table[row][column].isEmpty()) {
                return new int[]{row, column};
            }
        }
        return new int[]{-1, -1};
    }

    /**
     * Sets the owner of the cell by x,y and chosen player representation
     */
    public void setOwner(int row, int col, Player player) {
        if (row < 0 || row >= sizeX) throw new IllegalArgumentException("Row out of bounds");
        if (col < 0 || col >= sizeY) throw new IllegalArgumentException("Column out of bounds");
        if (player == null) throw new IllegalArgumentException("Player cannot be null");
        table[row][col].setRepresentation(player.getRepresentation());
    }


    /**
     * Players switching
     */
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
        // switch players connect4
        if (gameMode == 5) {
            if (currentPlayer.getRepresentation().equals("\u001B[31m●\u001B[0m")) {
                currentPlayer.setRepresentation("\u001B[33m●\u001B[0m");
            }else {
                currentPlayer.setRepresentation("\u001B[31m●\u001B[0m");
            }
        }
    }


    /**
     * Returns current state of the game
     */
    public gameState gameState() {
        int symbolsAlignedRequired = currentGame.getSymbolsAlignedRequired();
        rules = new Rules();
        Cell result = rules.findAlignedCells(table, sizeX, sizeY, symbolsAlignedRequired);

        if (result != null && !result.isEmpty()) {
            String winner = result.getRepresentation();
            switch (winner) {
                case "X" -> { return gameState.Player_X_Won; }
                case "O" -> { return gameState.Player_O_Won; }
                case "●" -> { return gameState.Player_WhiteCircle_Won; }
                case "○" -> { return gameState.Player_EmptyCircle_Won; }
                case "\u001B[31m●\u001B[0m" -> { return gameState.RedCircle_Won; }
                case "\u001B[33m●\u001B[0m" -> { return gameState.YellowCircle_Won; }
            }
        }

        if (rules.isBoardFull(sizeX, sizeY, table)) {
            return gameState.Draw;
        }

        return gameState.Default;
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

}