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

    public void executeOption(String choice, Library library, UserAccount userAccount) {
        userMenu.executeOption(choice, library, userAccount);
    }
}