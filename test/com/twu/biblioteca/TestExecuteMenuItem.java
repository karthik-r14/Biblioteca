package com.twu.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import org.junit.contrib.java.lang.system.ExpectedSystemExit;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

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
        Library books = new Library();

        books.addABook(new Book("Five Point Someone", "Chetan Bhagat", 2004));
        books.addABook(new Book("One Night At the Call Center", "Chetan Bhagat", 2005));
        books.addABook(new Book("Revolution 2020", "Chetan Bhagat", 2011));
        executeMenu.execute(books);

        assertEquals("FIVE POINT SOMEONE  CHETAN BHAGAT  2004\nONE NIGHT AT THE CALL CENTER  CHETAN BHAGAT  2005\nREVOLUTION 2020  CHETAN BHAGAT  2011\n", outputContent.toString());
    }

    @Test
    public void shouldNotifyWhenInvalidOptionIsChosen() {
        ExecuteMenuItem executeMenu = new ExecuteMenuItem("-1");
        Library books = new Library();

        books.addABook(new Book("Five Point Someone", "Chetan Bhagat", 2004));
        books.addABook(new Book("One Night At the Call Center", "Chetan Bhagat", 2005));
        books.addABook(new Book("Revolution 2020", "Chetan Bhagat", 2011));

        executeMenu.execute(books);
        assertEquals("SELECT A VALID OPTION\n", outputContent.toString());
    }

    @Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();

    @Test
    public void shouldValidateQuit() {
        ExecuteMenuItem executeMenu = new ExecuteMenuItem("3");
        Library books = new Library();

        books.addABook(new Book("Five Point Someone", "Chetan Bhagat", 2004));
        books.addABook(new Book("One Night At the Call Center", "Chetan Bhagat", 2005));
        books.addABook(new Book("Revolution 2020", "Chetan Bhagat", 2011));

        exit.expectSystemExit();
        executeMenu.execute(books);
    }

    @Test
    public void shouldValidateCheckoutBooks() {
        String bookName = "Revolution 2020";
        final ByteArrayInputStream inContent = new ByteArrayInputStream(bookName.getBytes());
        System.setIn(inContent);

        ExecuteMenuItem executeMenu = new ExecuteMenuItem("2");
        Library books = new Library();
        books.addABook(new Book("Five Point Someone", "Chetan Bhagat", 2004));
        books.addABook(new Book("One Night At the Call Center", "Chetan Bhagat", 2005));
        books.addABook(new Book("Revolution 2020", "Chetan Bhagat", 2011));

        executeMenu.execute(books);
        assertEquals("ENTER BOOKNAME:\nThank you! Enjoy the book\n", outputContent.toString() );
    }
}