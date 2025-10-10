package games.game_engine;

import games.console.UserInteraction;
import games.console.View;
import games.game.Connect4;
import games.game.Gomoku;
import games.game.TicTacToe;

public class GameLauncher {

    public void start() {
        View view = new View();
        UserInteraction interaction = new UserInteraction();

        view.messageBeginningOfTheGame();
        int input = interaction.userInputInt();

        switch (input) {
            case 1 -> new TicTacToe().play();
            case 2 -> new Gomoku().play();
            case 3 -> new Connect4().play();
            default -> view.warnings(0);
        }


    }

}
