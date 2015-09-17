//LibrarianMenu has a userMenu which can be displayed, executed, new options can be added to it.
package com.twu.biblioteca;

public class LibrarianMenu {
    private UserMenu userMenu;

    LibrarianMenu(UserMenu userMenu) {
        this.userMenu = userMenu;
    }

    public void addOptions(String option) {
        userMenu.addOptions(option);
    }

    public void displayMenu() {
        userMenu.displayMenu();
    }

    public UserAccount executeOption(String choice, Library library, UserAccount userAccount) {

        switch (choice) {
            case "8":
                System.out.println("------------------------------------------------------------------------------------");
                System.out.println(String.format("%-40S%-40S%-40S", "TITLE", "AUTHOR", "YEAR"));
                System.out.println("------------------------------------------------------------------------------------");
                library.displayCheckedOutBookDetails();
                return userAccount;

            default:
                return userMenu.executeOption(choice, library, userAccount);
        }
    }
}