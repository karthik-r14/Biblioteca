//Usermenu has a menu which can be displayed, executed, new options can be added to it.
package com.twu.biblioteca;

import java.util.ArrayList;

public class UserMenu {

    private ArrayList<String> menu;
    private ExecuteUserMenuItem item;


    public UserMenu(ArrayList<String> menu) {
        this.menu = menu;
    }

    public void addOptions(String option) {
        menu.add(option);
    }

    public void displayMenu(String menuType) {
        System.out.println("\n\n" + menuType);
        for (String option : menu) {
            System.out.println(option);
        }
    }

    public UserAccount executeOption(String choice, Library library, UserAccount userAccount) {
        item = new ExecuteUserMenuItem(choice);
        return item.execute(library, userAccount);
    }
}
