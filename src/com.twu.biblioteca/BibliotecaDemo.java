package com.twu.biblioteca;

public class BibliotecaDemo {

    public static void main(String[] args) {

        boolean flag =true;

        MainMenu menu = new MainMenu();
        menu.addOptions("1.List Books");
        menu.addOptions("2.Exit");

        Library library = new Library();
        library.addABook(new Book("Five Point Someone", "Chetan Bhagat", 2004));
        library.addABook(new Book("One Night At the Call Center", "Chetan Bhagat", 2005));
        library.addABook(new Book("Revolution 2020", "Chetan Bhagat", 2011));
        library.addABook(new Book("Pride and Prejudice", "Jane Austen", 1813));
        library.addABook(new Book("Da Vinci code", "DAN BROWN", 2003));
        library.addABook(new Book("Adventures of Sherlock Holmes", "Sir Arthur Conan Doyle", 1892));

        BibliotecaApplication bibliotecaApplication = new BibliotecaApplication(menu ,library);

        while (true) {
            bibliotecaApplication.start(flag);
            flag =false;
        }
    }
}
