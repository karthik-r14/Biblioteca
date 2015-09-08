//A Library comprises of books which can be displayed and a funtionality for adding a book to the list.
package com.twu.biblioteca;

import java.util.ArrayList;

public class Library {

    private ArrayList<Book> listOfBooks;
    private ArrayList<Book> checkedOutBooks;

    public Library() {
        listOfBooks = new ArrayList<>();
        checkedOutBooks = new ArrayList<>();
    }

    public void addABook(Book book) {
        listOfBooks.add(book);
    }

    public String checkoutABook(String bookName) {

        for (Book book : listOfBooks) {
            if (book.compareWithBookName(bookName)) {
                checkedOutBooks.add(book);
                listOfBooks.remove(book);
                return "Thank you! Enjoy the book";
            }
        }
        return " ";
    }

    public void displayBooks() {
        for (Book book : listOfBooks) {
            book.displayBook();
        }
    }


}
