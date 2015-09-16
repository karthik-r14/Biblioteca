//Usermenu has a menu which can be displayed, new options can be added to it.
package com.twu.biblioteca;

import java.util.ArrayList;

public class UserMenu {

    private ArrayList<String> menu;

    public UserMenu(ArrayList<String> menu) {
        this.menu = menu;
    }

    public void addOptions(String option) {
        menu.add(option);
    }

    public void displayMenu() {
        for (String option : menu) {
            System.out.println(option);
        }
    }
}
