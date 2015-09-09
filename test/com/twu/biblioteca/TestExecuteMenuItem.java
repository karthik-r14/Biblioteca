package com.twu.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import org.junit.contrib.java.lang.system.ExpectedSystemExit;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class TestExecuteMenuItem {

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
    public void shouldDisplayAllBooksWhenUserInputsNumericOne() {
        ExecuteMenuItem executeMenu = new ExecuteMenuItem("1");
        ArrayList<Book> books = new ArrayList<>();
        books.add(new Book("Five Point Someone", "Chetan Bhagat", 2004));
        books.add(new Book("One Night At the Call Center", "Chetan Bhagat", 2005));
        books.add(new Book("Revolution 2020", "Chetan Bhagat", 2011));

        Library library = new Library(books);
        executeMenu.execute(library);

        assertEquals("FIVE POINT SOMEONE  CHETAN BHAGAT  2004\nONE NIGHT AT THE CALL CENTER  CHETAN BHAGAT  2005\nREVOLUTION 2020  CHETAN BHAGAT  2011\n", outputContent.toString());
    }

    @Test
    public void shouldNotifyWhenInvalidOptionIsChosen() {
        ExecuteMenuItem executeMenu = new ExecuteMenuItem("-1");
        ArrayList<Book> books = new ArrayList<>();
        books.add(new Book("Five Point Someone", "Chetan Bhagat", 2004));
        books.add(new Book("One Night At the Call Center", "Chetan Bhagat", 2005));
        books.add(new Book("Revolution 2020", "Chetan Bhagat", 2011));

        Library library = new Library(books);
        executeMenu.execute(library);

        assertEquals("SELECT A VALID OPTION\n", outputContent.toString());
    }

    @Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();

    @Test
    public void shouldValidateQuit() {
        ExecuteMenuItem executeMenu = new ExecuteMenuItem("3");
        ArrayList<Book> books = new ArrayList<>();
        books.add(new Book("Five Point Someone", "Chetan Bhagat", 2004));
        books.add(new Book("One Night At the Call Center", "Chetan Bhagat", 2005));
        books.add(new Book("Revolution 2020", "Chetan Bhagat", 2011));

        Library library = new Library(books);

        exit.expectSystemExit();
        executeMenu.execute(library);
    }

    @Test
    public void shouldValidateCheckoutBooks() {
        String bookName = "Revolution 2020";
        final ByteArrayInputStream inContent = new ByteArrayInputStream(bookName.getBytes());
        System.setIn(inContent);

        ExecuteMenuItem executeMenu = new ExecuteMenuItem("2");
        ArrayList<Book> books = new ArrayList<>();
        books.add(new Book("Five Point Someone", "Chetan Bhagat", 2004));
        books.add(new Book("One Night At the Call Center", "Chetan Bhagat", 2005));
        books.add(new Book("Revolution 2020", "Chetan Bhagat", 2011));

        Library library = new Library(books);
        executeMenu.execute(library);

        assertEquals("ENTER BOOKNAME:\nThank you! Enjoy the book\n", outputContent.toString());
    }

    @Test
    public void shouldNotifyWhenBookIsNotAvailable() {
        String bookName = "2 states";
        final ByteArrayInputStream inContent = new ByteArrayInputStream(bookName.getBytes());
        System.setIn(inContent);

        ExecuteMenuItem executeMenu = new ExecuteMenuItem("2");
        ArrayList<Book> books = new ArrayList<>();
        books.add(new Book("Five Point Someone", "Chetan Bhagat", 2004));
        books.add(new Book("One Night At the Call Center", "Chetan Bhagat", 2005));
        books.add(new Book("Revolution 2020", "Chetan Bhagat", 2011));

        Library library = new Library(books);
        executeMenu.execute(library);

        assertEquals("ENTER BOOKNAME:\nThat book is not available\n", outputContent.toString());
    }
}