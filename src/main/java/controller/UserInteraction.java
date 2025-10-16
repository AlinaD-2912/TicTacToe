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

package controller;

import java.util.Scanner;


public class UserInteraction {

    private Scanner scanner;

    public UserInteraction(){
        scanner = new Scanner(System.in);
    }

    /**
     *  Reads an integer from user input
     *
     *  @return the integer entered by the user
     */
    public int userInputInt() {
        int input = scanner.nextInt();
        return input;
    }


    /**
     *  Reads a string from user input
     *
     *  @return the string entered by the user
     */
    public String userInputString() {
        String input = scanner.nextLine();
        return input;
    }
}
