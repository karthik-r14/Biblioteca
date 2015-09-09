package com.twu.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class LibraryTest {

    private final ByteArrayOutputStream outputContent = new ByteArrayOutputStream();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outputContent));
    }

    @After
    public void CleanUpStreams() {
        System.setOut(System.out);
    }

    @Test
    public void shouldListBooksInTheLibrary() {
        ArrayList<Book> books = new ArrayList<>();
        books.add(new Book("Five Point Someone", "Chetan Bhagat", 2004));
        books.add(new Book("One Night At the Call Center", "Chetan Bhagat", 2005));
        books.add(new Book("Revolution 2020", "Chetan Bhagat", 2011));

        Library library = new Library(books);
        library.displayBooks();

        assertEquals("FIVE POINT SOMEONE  CHETAN BHAGAT  2004\nONE NIGHT AT THE CALL CENTER  CHETAN BHAGAT  2005\nREVOLUTION 2020  CHETAN BHAGAT  2011\n", outputContent.toString());
    }

    @Test
    public void shouldCheckOutABookFromTheLibrary() {
        ArrayList<Book> books = new ArrayList<>();
        books.add(new Book("Five Point Someone", "Chetan Bhagat", 2004));
        books.add(new Book("One Night At the Call Center", "Chetan Bhagat", 2005));
        books.add(new Book("Revolution 2020", "Chetan Bhagat", 2011));

        Library library = new Library(books);

        assertEquals("Thank you! Enjoy the book", library.checkoutABook("Five Point Someone"));
    }

    @Test
    public void shouldNotifyWhenABookIsNotAvailableInTheLibrary() {
        ArrayList<Book> books = new ArrayList<>();
        books.add(new Book("Five Point Someone", "Chetan Bhagat", 2004));
        books.add(new Book("One Night At the Call Center", "Chetan Bhagat", 2005));
        books.add(new Book("Revolution 2020", "Chetan Bhagat", 2011));

        Library library = new Library(books);

        assertEquals("That book is not available", library.checkoutABook("2 States"));
    }

    @Test
    public void shouldReturnABookBackToTheLibrary() {
        ArrayList<Book> books = new ArrayList<>();
        books.add(new Book("Five Point Someone", "Chetan Bhagat", 2004));
        books.add(new Book("One Night At the Call Center", "Chetan Bhagat", 2005));
        books.add(new Book("Revolution 2020", "Chetan Bhagat", 2011));

        Library library = new Library(books);
        library.checkoutABook("Five Point Someone");

        assertEquals("Thank you for returning the book", library.returnABook("Five Point Someone"));
    }

}