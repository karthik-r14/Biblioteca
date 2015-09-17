//Biblioteca application launches the application
package com.twu.biblioteca;

public class BibliotecaApplication {

    private WelcomeMessage welcomeMessage;
    private MainMenu menu;
    private UserMenu userMenu;
    private Library library;
    private ReadInput userInput;

    public BibliotecaApplication(MainMenu menu, Library library, WelcomeMessage welcomeMessage, ReadInput input, UserMenu userMenu) {
        this.welcomeMessage = welcomeMessage;
        this.menu = menu;
        this.userMenu = userMenu;
        this.library = library;
        userInput = input;
    }

    public UserAccount run(UserAccount userAccount) {
        switch (userAccount.getRole()) {

            case "DEFAULT":
                menu.displayMenu();
                display("Enter choice :");
                return menu.executeOption(userInput.read(), library);

            case "USER":
                userMenu.displayMenu();
                display("Enter choice :");
                return userMenu.executeOption(userInput.read(), library, userAccount);

            case "LIBRARIAN":
        }
        return new UserAccount(" ", " ", "DEFAULT");
    }

    public void start() {
        welcomeMessage.display();
    }

    public void display(String message) {
        System.out.println(message);
    }
}