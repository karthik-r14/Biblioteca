//A main menu has a list of options and a funtionality for adding a new option ,displaying the menu.
package com.twu.biblioteca;

import java.util.ArrayList;

public class MainMenu {

    private ArrayList<String> menu= new ArrayList<String>();

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
}
