//BibliotecaDemo is a demonstration of Biblioteca Application
package com.twu.biblioteca;

import java.util.ArrayList;

public class BibliotecaDemo {

    public static void main(String[] args) {

        MainMenu menu = new MainMenu(new ArrayList<String>(), new ReadInput());
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

        BibliotecaApplication bibliotecaApplication = new BibliotecaApplication(menu, library, new WelcomeMessage("WELCOME TO BIBLIOTECA"));

        bibliotecaApplication.start();
        while (true) {
            bibliotecaApplication.run();
        }
    }
}