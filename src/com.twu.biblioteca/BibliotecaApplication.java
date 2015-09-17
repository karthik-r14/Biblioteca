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

    public String run(String menuType) {
        switch (menuType) {

            case "MAINMENU":
                menu.displayMenu();
                display("Enter choice :");
                return menu.executeOption(userInput.read(), library);

            case "USER MENU":
                return "USERMENU";

            case "LIBRARIAN":
                return "LIBRARIANMENU";
        }
        return "MAINMENU";
    }

    public void start() {
        welcomeMessage.display();
    }

    public void display(String message) {
        System.out.println(message);
    }
}