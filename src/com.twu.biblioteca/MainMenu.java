//A main menu has a list of options and a funtionality for adding a new option ,displaying the menu.
package com.twu.biblioteca;

import java.util.ArrayList;

public class MainMenu {

    private ArrayList<String> menu;
    private ExecuteMenuItem item;

    MainMenu(ArrayList<String> menu) {
        this.menu = menu;
    }

    public void addOptions(String option) {
        menu.add(option);
    }

    public void displayMenu() {
        System.out.println("\n\nMAIN MENU");
        for (String option : menu) {
            System.out.println(option);
        }
    }

    public UserAccount executeOption(String choice, Library library) {
        item = new ExecuteMenuItem(choice);
        return item.execute(library);
    }
}