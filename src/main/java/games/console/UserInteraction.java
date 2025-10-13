/*
 * Name of the class: UserInteraction
 *
 * Description: this class is responsible for extracting user input from System.out.print, uses Scanner
 *
 * Version: 1.0
 *
 * Date: 13/10/2025
 *
 * Copyright: moi
 */

package games.console;

import java.util.Scanner;


public class UserInteraction {

    private Scanner scanner;

    public UserInteraction(){
        scanner = new Scanner(System.in);
    }
 
    public int userInputInt() {
        int input = scanner.nextInt();
        return input;
    }

    public String userInputString() {
        String input = scanner.nextLine();
        return input;
    }
}
