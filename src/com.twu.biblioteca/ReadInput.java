//ReadInput takes in user input and returns it.
package com.twu.biblioteca;

import java.util.Scanner;

public class ReadInput {

    private Scanner scanner;

    ReadInput(Scanner scanner) {
        this.scanner = scanner;
    }

    public String read() {
        scanner = new Scanner(System.in);
        return scanner.nextLine();

    }
}