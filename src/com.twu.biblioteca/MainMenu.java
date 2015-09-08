//A main menu has a list of options and a funtionality for adding a new option ,displaying the menu.
package com.twu.biblioteca;

import java.util.ArrayList;

public class MainMenu {

    private ArrayList<String> menu;
    private ReadInput input;
    private ExecuteMenuItem item;

    MainMenu() {
        menu = new ArrayList<>();
        input = new ReadInput();
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
        return input.read();
    }

    public void executeOption(String choice, Object object) {
        item = new ExecuteMenuItem(choice);
        item.execute(object);
    }
}