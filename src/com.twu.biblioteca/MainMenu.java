//A main menu has a list of options and a funtionality for adding a new option ,displaying the menu.
package com.twu.biblioteca;

import java.util.ArrayList;

public class MainMenu {

    private ArrayList<String> menu;
    private ReadInput input;
    private ExecuteMenuItem item;

    MainMenu(ArrayList<String> menu, ReadInput input) {
        this.menu = menu;
        this.input = input;
        //menu = new ArrayList<>();
        //input = new ReadInput();
    }

    public void addOptions(String option) {
        menu.add(option);
    }

    public void displayMenu() {
        for (String option : menu) {
            System.out.println(option);
        }
    }

    public String takeUserInput() {
        return input.read("Enter choice :");
    }

    public void executeOption(String choice, Library books) {
        item = new ExecuteMenuItem(choice);
        item.execute(books);
    }
}