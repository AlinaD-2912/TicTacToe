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
