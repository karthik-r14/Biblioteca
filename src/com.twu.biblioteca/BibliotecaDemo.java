//BibliotecaDemo is a demonstratoin of Biblioteca Application
package com.twu.biblioteca;

public class BibliotecaDemo {

    public static void main(String[] args) {

        boolean welcomeFlag = true;

        MainMenu menu = new MainMenu();
        menu.addOptions("1.List Books");
        menu.addOptions("2.Checkout a Book");
        menu.addOptions("3.Exit");

        Library library = new Library();
        library.addABook(new Book("The Bicentennial Man", "Issac Asimov", 1976));
        library.addABook(new Book("One Night At the Call Center", "Chetan Bhagat", 2005));
        library.addABook(new Book("Revolution 2020", "Chetan Bhagat", 2011));
        library.addABook(new Book("Pride and Prejudice", "Jane Austen", 1813));
        library.addABook(new Book("Da Vinci code", "DAN BROWN", 2003));
        library.addABook(new Book("Adventures of Sherlock Holmes", "Sir Arthur Conan Doyle", 1892));

        BibliotecaApplication bibliotecaApplication = new BibliotecaApplication(menu, library);

        while (true) {
            bibliotecaApplication.run(welcomeFlag);
            welcomeFlag = false;
        }
    }
}
