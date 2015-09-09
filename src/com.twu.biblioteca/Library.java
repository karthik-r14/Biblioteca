//A Library comprises of books which can be displayed and a funtionality for adding a book to the list.
package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.zip.CheckedInputStream;

public class Library {

    private ArrayList<Book> listOfBooks;
    private ArrayList<Book> checkedOutBooks;

    public Library(ArrayList<Book> listOfBooks) {
        this.listOfBooks = listOfBooks;
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

    public String returnABook(String bookName) {
        for(Book book : checkedOutBooks) {
            if(book.compareWithBookName(bookName)) {
                listOfBooks.add(book);
                checkedOutBooks.remove(book);
                return "Thank you for returning the book";
            }
        }
        return " ";
    }
}