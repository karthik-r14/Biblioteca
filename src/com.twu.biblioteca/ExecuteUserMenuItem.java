//ExecuteUserMenuItem executes the desired usermenu item based on choice
package com.twu.biblioteca;

import java.util.Scanner;

public class ExecuteUserMenuItem {

    private String choice;

    public ExecuteUserMenuItem(String choice) {
        this.choice = choice;
    }

    public UserAccount execute(Library library, UserAccount userAccount) {
        ReadInput input = new ReadInput(new Scanner(System.in));

        switch (choice) {
            case "1":
                display("------------------------------------------------------------------------------------");
                display(String.format("%-40S%-40S%-40S", "TITLE", "AUTHOR", "YEAR"));
                display("------------------------------------------------------------------------------------");
                library.displayBooks();
                break;

            case "2":
                display("ENTER BOOKNAME:");
                display(library.checkoutABook(input.read(), userAccount));
                break;

            case "3":
                display("ENTER BOOK TO BE RETURNED:");
                display(library.returnABook(input.read()));
                break;

            case "4":
                display("---------------------------------------------------------------------------------------------------");
                display(String.format("%-40S%-25S%-25S%-25S", "MOVIE", "DIRECTOR", "YEAR", "RATING"));
                display("---------------------------------------------------------------------------------------------------");
                library.displayMovie();
                break;

            case "5":
                display("ENTER MOVIE NAME:");
                display(library.checkoutAMovie(input.read()));
                break;

            case "6":
                userAccount.displayInfo();
                break;

            case "7":
                return new UserAccount(" ", " ", "DEFAULT");

            default:
                display("SELECT A VALID OPTION");
        }
        return userAccount;
    }

    private void display(String message) {
        System.out.println(message);
    }
}