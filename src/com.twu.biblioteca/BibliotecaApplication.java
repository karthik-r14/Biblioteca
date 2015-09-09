//Biblioteca application launches the application
package com.twu.biblioteca;

public class BibliotecaApplication {

    private WelcomeMessage welcomeMessage;
    private MainMenu menu;
    private Library library;

    public BibliotecaApplication(MainMenu menu, Library library, WelcomeMessage welcomeMessage) {
        this.welcomeMessage = welcomeMessage;
        this.menu = menu;
        this.library = library;
    }

    public void run() {
        menu.displayMenu();
        menu.executeOption(menu.takeUserInput(), library);
    }

    public void start() {
        welcomeMessage.display();
    }
}