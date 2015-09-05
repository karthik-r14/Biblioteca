//A BookList comprises of books which can be displayed and a funtionality for adding a book to the list.
package com.twu.biblioteca;

import java.util.ArrayList;

public class BookList {

    ArrayList<Book> ListOfBooks = new ArrayList<Book>();

    public void addABook(Book book) {
        ListOfBooks.add(book);
    }

    public void displayBooks() {
        for(Book book:ListOfBooks){
            book.displayBook();
        }
    }
}
