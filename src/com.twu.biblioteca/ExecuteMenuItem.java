//ExecuteMenuItem executes the desired menu item based on choice
package com.twu.biblioteca;

public class ExecuteMenuItem {

    private String choice;
    public ExecuteMenuItem(String choice) {
        this.choice =choice;
    }

    public void execute(Object object) {

        switch (choice) {
            case "1":
                Library tempBooks;
                tempBooks = (Library) object;
                tempBooks.displayBooks();
                break;

            case "2":
                System.exit(0);

            default:
                System.out.println("SELECT A VALID OPTION");
        }
    }
}
