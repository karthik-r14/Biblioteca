package com.twu.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class TestBookList {

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
    public void shouldListofBookInTheLibrary() {
        BookList books = new BookList();

        books.addABook(new Book("Five Point Someone", "Chetan Bhagat", 2004));
        books.addABook(new Book("One Night At the Call Center", "Chetan Bhagat", 2005));
        books.addABook(new Book("Revolution 2020", "Chetan Bhagat", 2011));
        books.displayBooks();

        assertEquals("FIVE POINT SOMEONE  CHETAN BHAGAT  2004\nONE NIGHT AT THE CALL CENTER  CHETAN BHAGAT  2005\nREVOLUTION 2020  CHETAN BHAGAT  2011\n", outputContent.toString());
    }
}
