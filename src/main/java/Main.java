/*
 * Name of the class: Main
 *
 * Description: This class contains the entry point of the program
 *              It launches the GameLauncher to start the selected game
 *
 * Version: 1.0
 *
 * Date: 13/10/2025
 *
 * Copyright: moi
 */

import controller.GameLauncher;

public class Main {
    public static void main(String[] args) {
        GameLauncher gameLauncher = new GameLauncher();
        gameLauncher.start();
    }

}
