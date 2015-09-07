//ReadInput takes in user input and returns it.
package com.twu.biblioteca;

import java.util.Scanner;

public class ReadInput {

    String userInput;

    public String read() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter choice :");
        userInput = scanner.nextLine();
        return userInput;
    }
}