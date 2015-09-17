//ExecuteMenuItem executes the desired menu item based on choice
package com.twu.biblioteca;

import java.util.Scanner;

public class ExecuteMenuItem {

    private String choice;

    public ExecuteMenuItem(String choice) {
        this.choice = choice;
    }

    public void execute(Library library) {
        ReadInput input = new ReadInput(new Scanner(System.in));

        switch (choice) {
            case "1":
                display("------------------------------------------------------------------------------------");
                display(String.format("%-40S%-40S%-40S", "TITLE", "AUTHOR", "YEAR"));
                display("------------------------------------------------------------------------------------");
                library.displayBooks();
                break;

            case "2":
                display("---------------------------------------------------------------------------------------------------");
                display(String.format("%-40S%-25S%-25S%-25S", "MOVIE", "DIRECTOR", "YEAR", "RATING"));
                display("---------------------------------------------------------------------------------------------------");
                library.displayMovie();
                break;

            case "3":
                display("ENTER MOVIE NAME:");
                display(library.checkoutAMovie(input.read()));
                break;

            case "e":
            case "E":
                System.exit(0);

            default:
                display("SELECT A VALID OPTION");
        }
    }

    public void display(String message) {
        System.out.println(message);
    }
}