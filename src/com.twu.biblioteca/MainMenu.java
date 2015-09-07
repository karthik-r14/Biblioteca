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
        item = new ExecuteMenuItem();
    }

    public void addOptions(String option) {
        menu.add(option);
    }

    public void displayMenu() {
        int id = 1;
        for (String option : menu) {
            System.out.println(id + "." + option);
            id++;
        }
    }

    public String takeUserInput() {
        return input.read();
    }

    public void executeOption(String choice, Object object) {
        item.execute(choice, object);
    }
}