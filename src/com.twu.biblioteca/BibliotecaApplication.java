package com.twu.biblioteca;

public class BibliotecaApplication {

    private WelcomeMessage welcomeMessage;
    private MainMenu menu;
    private Library library;

    public BibliotecaApplication(MainMenu menu, Library library) {
        welcomeMessage = new WelcomeMessage("WELCOME TO BIBLIOTECA");
        this.menu = menu;
        this.library = library;
    }

    public void start(boolean flag) {
        if(flag) {
            welcomeMessage.display();
        }
        menu.displayMenu();
        menu.executeOption(menu.takeUserInput(), library);
    }
}