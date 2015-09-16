//Usermenu has a menu which can be displayed, new options can be added to it.
package com.twu.biblioteca;

import java.util.ArrayList;

public class UserMenu extends MainMenu {

    private ArrayList<String> menu;

    public UserMenu(ArrayList<String> menu) {
        super(menu);
        this.menu = menu;
    }
}
