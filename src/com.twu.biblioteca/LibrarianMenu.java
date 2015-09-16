package com.twu.biblioteca;

public class LibrarianMenu {
    UserMenu userMenu;

    LibrarianMenu(UserMenu userMenu) {
        this.userMenu = userMenu;
    }

    public void addOptions(String option) {
         userMenu.addOptions(option);
    }

    public void displayMenu() {
        userMenu.displayMenu();
    }
}