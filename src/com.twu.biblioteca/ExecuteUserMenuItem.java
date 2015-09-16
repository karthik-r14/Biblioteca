package com.twu.biblioteca;

public class ExecuteUserMenuItem {

    private String choice;

    public ExecuteUserMenuItem(String choice) {
        this.choice = choice;
    }

    public void execute(Library library) {

        switch (choice) {
            case "1":
                display("------------------------------------------------------------------------------------");
                display(String.format("%-40S%-40S%-40S", "TITLE", "AUTHOR", "YEAR"));
                display("------------------------------------------------------------------------------------");
                library.displayBooks();
                break;

            default:
                display("SELECT A VALID OPTION");
        }
    }

    private void display(String message) {
        System.out.println(message);
    }
}