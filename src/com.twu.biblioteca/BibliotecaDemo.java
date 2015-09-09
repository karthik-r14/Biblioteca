//BibliotecaDemo is a demonstration of Biblioteca Application
package com.twu.biblioteca;

import java.util.ArrayList;

public class BibliotecaDemo {

    public static void main(String[] args) {

        MainMenu menu = new MainMenu(new ArrayList<String>(), new ReadInput());
        menu.addOptions("1.List Books");
        menu.addOptions("2.Checkout a Book");
        menu.addOptions("3.Return a Book");
        menu.addOptions("4.Exit");

        ArrayList<Book> books = new ArrayList<>();
        books.add(new Book("The Bicentennial Man", "Issac Asimov", 1976));
        books.add(new Book("One Night At the Call Center", "Chetan Bhagat", 2005));
        books.add(new Book("Revolution 2020", "Chetan Bhagat", 2011));
        books.add(new Book("Pride and Prejudice", "Jane Austen", 1813));
        books.add(new Book("Da Vinci code", "DAN BROWN", 2003));
        books.add(new Book("Adventures of Sherlock Holmes", "Sir Arthur Conan Doyle", 1892));

        Library library = new Library(books);

        BibliotecaApplication bibliotecaApplication = new BibliotecaApplication(menu, library, new WelcomeMessage("WELCOME TO BIBLIOTECA"));

        bibliotecaApplication.start();
        while (true) {
            bibliotecaApplication.run();
        }
    }
}