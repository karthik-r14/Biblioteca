//BibliotecaDemo is a demonstration of Biblioteca Application
package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.Scanner;

public class BibliotecaApplication {

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
        movie.add(new Movie("Koi Mil Gaya", "Rakesh Roshan", 2003, 7.1f));
        movie.add(new Movie("Kaho Naa Pyaar hai", "Rakesh Roshan", 2000, 6.9f));
        movie.add(new Movie("Inception", "Christopher Nolan", 2010, 8.8f));
        movie.add(new Movie("Interstellar", "Christopher Nolan", 2014, 8.7f));

        ArrayList<UserAccount> userAccounts = new ArrayList<>();
        userAccounts.add(new UserAccount("100-1001", "abcdef", "USER", "Karu", "kar@gmail.com", "9871595922"));
        userAccounts.add(new UserAccount("100-1002", "qwerty", "USER", "Raghuram", "rag@gmail.com", "9902910898"));
        userAccounts.add(new UserAccount("100-1003", "zxcvb", "USER", "Pritesh", "pritu@gmail.com", "9845123498"));
        userAccounts.add(new UserAccount("100-1004", "asdff", "USER", "Kritika", "kriti@gmai.com ", "9844909821 "));
        userAccounts.add(new UserAccount("100-1005", "wiper", "LIBRARIAN", "Karthik", "karthik@gmail.com", "9880443410"));


        Library library = new Library(books, movie, userAccounts);

        UserMenu menu1 = new UserMenu(new ArrayList<String>());
        menu1.addOptions("1.List Books");
        menu1.addOptions("2.Checkout a Book");
        menu1.addOptions("3.Return a Book");
        menu1.addOptions("4.List Movies");
        menu1.addOptions("5.Checkout a Movie");
        menu1.addOptions("6.User Details");
        menu1.addOptions("7.Logout");

        UserMenu menu2 = new UserMenu(new ArrayList<String>());

        LibrarianMenu librarianMenu = new LibrarianMenu(menu2);
        librarianMenu.addOptions("1.List Books");
        librarianMenu.addOptions("2.Checkout a Book");
        librarianMenu.addOptions("3.Return a Book");
        librarianMenu.addOptions("4.List Movies");
        librarianMenu.addOptions("5.Checkout a Movie");
        librarianMenu.addOptions("6.User Details");
        librarianMenu.addOptions("7.Logout");
        librarianMenu.addOptions("8.Book Details");

        BibliotecaController bibliotecaController = new BibliotecaController(menu, library, new WelcomeMessage("WELCOME TO BIBLIOTECA"), new ReadInput(new Scanner(System.in)), menu1, librarianMenu);

        bibliotecaController.start();
        UserAccount userAccount = new UserAccount(" ", " ", "DEFAULT", " ", " ", " ");
        while (true) {
            userAccount = bibliotecaController.run(userAccount);
        }
    }
}