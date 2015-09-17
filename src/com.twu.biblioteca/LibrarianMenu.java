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

        return userMenu.executeOption(choice, library, userAccount);
    }
}