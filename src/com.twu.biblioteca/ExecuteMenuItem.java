//ExecuteMenuItem executes the desired menu item
package com.twu.biblioteca;

public class ExecuteMenuItem {

    public void execute(String choice, Object object) {

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
