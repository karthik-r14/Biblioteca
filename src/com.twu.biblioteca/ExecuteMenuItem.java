//ExecuteMenuItem executes the desired menu item based on choice
package com.twu.biblioteca;

public class ExecuteMenuItem {

    private String choice;

    public ExecuteMenuItem(String choice) {
        this.choice = choice;
    }

    public void execute(Library library) {
        ReadInput input = new ReadInput();

        switch (choice) {
            case "1":
                library.displayBooks();
                break;

            case "2":
                display(library.checkoutABook(input.read("ENTER BOOKNAME:")));
                break;

            case "3":
                display(library.returnABook(input.read("ENTER BOOK TO BE RETURNED:")));
                break;

            case "4":
                System.exit(0);

            default:
                display("SELECT A VALID OPTION");
        }
    }

    public void display(String message) {
        System.out.println(message);
    }
}