//ReadInput takes in user input and returns it.
package com.twu.biblioteca;

import java.util.Scanner;

public class ReadInput {

    private String userInput;

    public String read(String message) {
        Scanner scanner = new Scanner(System.in);
        display(message);
        userInput = scanner.nextLine();
        return userInput;
    }

    public void display(String message) {
        System.out.println(message);
    }
}