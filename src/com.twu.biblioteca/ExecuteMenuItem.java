//ExecuteMenuItem executes the desired menu item based on choice
package com.twu.biblioteca;

public class ExecuteMenuItem {

    private String choice;

    public ExecuteMenuItem(String choice) {
        this.choice = choice;
    }

    public void execute(Library books) {

        switch (choice) {
            case "1":
                books.displayBooks();
                break;

            case "2":
                ReadInput input = new ReadInput();
                display(books.checkoutABook(input.read("ENTER BOOKNAME:")));
                break;

            case "3":
                System.exit(0);

            default:
                display("SELECT A VALID OPTION");
        }
    }

    public void display(String message) {
        System.out.println(message);
    }
}