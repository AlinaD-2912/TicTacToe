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

import view.View;

public class GameLauncher {

    public void start() {
        View view = new View();
        UserInteraction interaction = new UserInteraction();

        view.messageBeginningOfTheGame();
        int input = interaction.userInputInt();

        switch (input) {
            case 1 -> new TicTacToeController().play();
            case 2 -> new GomokuController().play();
            case 3 -> new Connect4Controller().play();
            default -> view.warnings(0);
        }
    }
}
