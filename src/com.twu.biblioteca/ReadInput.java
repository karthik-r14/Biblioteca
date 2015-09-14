//ReadInput takes in user input and returns it.
package com.twu.biblioteca;

import java.util.Scanner;

public class ReadInput {

    private Scanner scanner;

    ReadInput(Scanner scanner) {
        this.scanner = scanner;
    }

    public String read() {
        return scanner.nextLine();
    }
}