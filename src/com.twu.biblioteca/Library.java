//A Library comprises of books which can be displayed, checkedout.Also a checkedout book can be returned
package com.twu.biblioteca;

import java.util.ArrayList;

public class Library {

    private ArrayList<Book> listOfBooks;
    private ArrayList<Book> checkedOutBooks;
    private ArrayList<Movie> listOfMovies;

    public Library(ArrayList<Book> listOfBooks, ArrayList<Movie> listOfMovies) {
        this.listOfBooks = listOfBooks;
        this.listOfMovies = listOfMovies;
        checkedOutBooks = new ArrayList<>();
    }

    public String checkoutABook(String bookName) {
        for (Book book : listOfBooks) {
            if (book.compareWithBookName(bookName)) {
                checkedOutBooks.add(book);
                listOfBooks.remove(book);
                return "Thank you! Enjoy the book";
            }
        }
        return "That book is not available";
    }

    public void displayBooks() {
        for (Book book : listOfBooks) {
            book.displayBook();
        }
    }

    public void displayMovie() {
        for (Movie movie : listOfMovies) {
            movie.displayMovie();
        }
    }

    public String returnABook(String bookName) {
        for (Book book : checkedOutBooks) {
            if (book.compareWithBookName(bookName)) {
                listOfBooks.add(book);
                checkedOutBooks.remove(book);
                return "Thank you for returning the book";
            }
        }
        return "That is not a valid book to return";
    }
}