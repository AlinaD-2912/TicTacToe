/*
 * Name of the class: View
 *
 * Description: this class is responsible for the appearance in the console, user interface
 *
 * Version: 1.0
 *
 * Date: 13/10/2025
 *
 * Copyright: moi
 */

package view;

public class View {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_VIOLET = "\u001B[34m";
    public static final String ANSI_ROSE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";


    /**
     * Default constructor
     */
    public View(){

    }


    /**
     * Displays the main menu at the beginning of the game
     */
    public void messageBeginningOfTheGame() {
        System.out.println(ANSI_YELLOW + "============= Welcome ! =============" + ANSI_RESET);
        System.out.println(ANSI_VIOLET + "---------------------------------------" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "Choose the game you would like to play" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "1. TicTacToe" + ANSI_RESET);
        System.out.println(ANSI_ROSE + "2. Gomoku" + ANSI_RESET);
        System.out.println(ANSI_CYAN + "3. Puissance 4" + ANSI_RESET);
        System.out.println(ANSI_VIOLET + "---------------------------------------" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "So what do you prefer (1,2 or 3): " + ANSI_RESET);
    }


    /**
     * Displays the menu for TicTacToe at the beginning of the game
     */
    public void messageBeginningOfTheGameTicTacToe () {
        System.out.println( ANSI_YELLOW + "======= Welcome to Tic-Tac-Toe! =======" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "---------------------------------------");
        System.out.println("Now choose what mode of game you prefer" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "1. 2 human players ");
        System.out.println("2. player vs artificial player");
        System.out.println("3. 2 artificial players" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "---------------------------------------");
        System.out.println("So what do you prefer (1,2 or 3): " + ANSI_RESET);
    }


    /**
     * Prompts the user to pick the X coordinate
     */
    public void pickXCoordinate() {
        System.out.println(ANSI_YELLOW + "Pick the coordinate for x (0,1,2...): " + ANSI_RESET);
    }


    /**
     * Prompts the user to pick the Y coordinate
     */
    public void pickYCoordinate() {
        System.out.println(ANSI_YELLOW + "Pick the coordinate for y (0,1,2...): " + ANSI_RESET);
    }


    /**
     * Prompts the user to pick a representation for their player
     */
    public void pickPlayerRepresentation() {
        System.out.println(ANSI_GREEN + "Pick (X or O): " + ANSI_RESET);
    }


    /**
     * Displays warnings based on input error code
     *
     * @param nb the index of the warning message
     */
    public void warnings (int nb) {
        String [] warnings = {"Invalid Input. Please try again.", "x out of range", "y out of range", "The cell is already occupied"};
        System.out.println(warnings[nb]);
    }


    /**
     * Displays the game over message according to the outcome
     *
     * @param message an integer representing the type of game over (draw or player win)
     */
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
        if (message == 4) {
            System.out.println("    Player ● Won          ");
        }
        if (message == 5) {
            System.out.println("    Player ○ Won          ");
        }

    }

    public void messageBeginningOfTheGameGomoku () {
        System.out.println(ANSI_ROSE + "======= Welcome to GOMOKU !!! =======" + ANSI_RESET);
        System.out.println(ANSI_VIOLET + "---------------------------------------" + ANSI_RESET);
    }

}
