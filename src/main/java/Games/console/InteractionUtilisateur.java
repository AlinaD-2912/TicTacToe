package Games.console;

import java.util.Scanner;

public class InteractionUtilisateur {

    private Scanner scanner;

    public InteractionUtilisateur(){
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
