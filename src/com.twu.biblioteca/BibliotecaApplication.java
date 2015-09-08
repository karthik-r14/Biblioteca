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

    public void run(boolean welcomeFlag) {
        if(welcomeFlag) {
            welcomeMessage.display();
        }
        menu.displayMenu();
        menu.executeOption(menu.takeUserInput(), library);
    }
}