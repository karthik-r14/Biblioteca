//BibliotecaDemo is a demonstration of Biblioteca Application
package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.Scanner;

public class BibliotecaDemo {

    public static void main(String[] args) {

        MainMenu menu = new MainMenu(new ArrayList<String>());
        menu.addOptions("1.List Books");
        menu.addOptions("2.List Movies");
        menu.addOptions("3.Checkout a Movie");
        menu.addOptions("4.Login");
        menu.addOptions("E.Exit");

        ArrayList<Book> books = new ArrayList<>();
        books.add(new Book("The Bicentennial Man", "Issac Asimov", 1976));
        books.add(new Book("One Night At the Call Center", "Chetan Bhagat", 2005));
        books.add(new Book("Revolution 2020", "Chetan Bhagat", 2011));
        books.add(new Book("Pride and Prejudice", "Jane Austen", 1813));
        books.add(new Book("Da Vinci code", "DAN BROWN", 2003));
        books.add(new Book("Adventures of Sherlock Holmes", "Sir Arthur Conan Doyle", 1892));

        ArrayList<Movie> movie = new ArrayList<>();
        movie.add(new Movie("The Boy in the Striped pyjamas", "Mark Herman", 2008, 7.8f));
        movie.add(new Movie("V for Vendetta", "James Mcteigue", 2006, 8.2f));

        ArrayList<UserAccount> userAccounts = new ArrayList<>();
        userAccounts.add(new UserAccount("123-4567", "abcdef", "USER"));
        userAccounts.add(new UserAccount("123-1234","qwerty","USER"));
        userAccounts.add(new UserAccount("123-4567","zxcvb","USER"));
        userAccounts.add(new UserAccount("123-9876","asdff","USER"));


        Library library = new Library(books, movie, userAccounts);

        UserMenu userMenu = new UserMenu(new ArrayList<String>());
        userMenu.addOptions("1.List Books");
        userMenu.addOptions("2.Checkout a Book");
        userMenu.addOptions("3.Return a Book");
        userMenu.addOptions("4.List Movies");
        userMenu.addOptions("5.Checkout a Movie");
        userMenu.addOptions("6.User Details");
        userMenu.addOptions("7.Logout");

        BibliotecaApplication bibliotecaApplication = new BibliotecaApplication(menu, library, new WelcomeMessage("WELCOME TO BIBLIOTECA"), new ReadInput(new Scanner(System.in)), userMenu);

        bibliotecaApplication.start();
        UserAccount userAccount = new UserAccount(" ", " ", "DEFAULT");
        while (true) {
            userAccount = bibliotecaApplication.run(userAccount);
        }
    }
}