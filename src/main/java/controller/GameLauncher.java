/*
 * Name of the class: GameLauncher
 *
 * Description: launching the game based on user input its sole purpose
 *
 * Version: 2.0
 *
 * Date: 13/10/2025
 *
 * Copyright: moi
 */

package controller;

import model.design_pattern.Strategy;
import view.ErrorCode;
import view.View;

public class GameLauncher {

    /**
     * Starts the game launcher
     * Displays the menu, reads user input, and launches the corresponding game
     */
    public void start() {
        View view = new View();
        UserInteraction interaction = new UserInteraction();

        view.messageBeginningOfTheGame();
        int input = interaction.userInputInt();

        Strategy selectedGame;

        switch (input) {
            case 1:
                selectedGame = new TicTacToe();
                break;
            case 2:
                selectedGame = new Gomoku();
                break;
            case 3:
                selectedGame = new Connect4();
                break;
            default:
                view.warnings(ErrorCode.InvalidInput);
                return;
        }
        selectedGame.play();

    }
}
