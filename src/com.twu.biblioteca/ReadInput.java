package com.twu.biblioteca;

import java.util.Scanner;

public class ReadInput {

    String userInput;

    public void read() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter choice :");
        userInput = scanner.nextLine();
    }

    @Override
    public String toString() {
        return userInput;
    }
}
