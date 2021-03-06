//Biblioteca application launches the application
package com.twu.biblioteca;

public class BibliotecaController {

    private WelcomeMessage welcomeMessage;
    private MainMenu menu;
    private UserMenu userMenu;
    private LibrarianMenu librarianMenu;
    private Library library;
    private ReadInput userInput;

    public BibliotecaController(MainMenu menu, Library library, WelcomeMessage welcomeMessage, ReadInput input, UserMenu userMenu, LibrarianMenu librarianMenu) {
        this.welcomeMessage = welcomeMessage;
        this.menu = menu;
        this.userMenu = userMenu;
        this.library = library;
        this.librarianMenu = librarianMenu;
        userInput = input;
    }

    public UserAccount run(UserAccount userAccount) {
        switch (userAccount.getRole()) {

            case "DEFAULT":
                menu.displayMenu();
                display("Enter choice :");
                return menu.executeOption(userInput.read(), library);

            case "USER":
                userMenu.displayMenu("USER MENU");
                display("Enter choice :");
                return userMenu.executeOption(userInput.read(), library, userAccount);

            case "LIBRARIAN":
                librarianMenu.displayMenu("LIBRARIAN MENU");
                display("Enter choice :");
                return librarianMenu.executeOption(userInput.read(), library, userAccount);
        }
        return new UserAccount(" ", " ", "DEFAULT", " ", " ", " ");
    }

    public void start() {
        welcomeMessage.display();
    }

    public void display(String message) {
        System.out.println(message);
    }
}