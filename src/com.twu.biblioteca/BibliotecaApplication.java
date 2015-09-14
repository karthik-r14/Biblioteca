//Biblioteca application launches the application
package com.twu.biblioteca;

public class BibliotecaApplication {

    private WelcomeMessage welcomeMessage;
    private MainMenu menu;
    private Library library;
    private ReadInput userInput;

    public BibliotecaApplication(MainMenu menu, Library library, WelcomeMessage welcomeMessage, ReadInput input) {
        this.welcomeMessage = welcomeMessage;
        this.menu = menu;
        this.library = library;
        userInput = input;
    }

    public void run() {
        menu.displayMenu();
        display("Enter choice :");
        menu.executeOption(userInput.read(), library);
    }

    public void start() {
        welcomeMessage.display();
    }

    public void display(String message) {
        System.out.println(message);
    }
}