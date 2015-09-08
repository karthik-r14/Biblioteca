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


public class TestMainMenu {

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
    public void shouldDisplayListBooksInMainMenu() {
        MainMenu menu = new MainMenu();

        menu.addOptions("1.List Books");
        menu.displayMenu();

        assertEquals("1.List Books\n", outputContent.toString());
    }

    @Test
    public void shouldReadInputFromUser() {
        MainMenu menu = new MainMenu();
        ByteArrayInputStream inputStream = new ByteArrayInputStream("1".getBytes());

        System.setIn(inputStream);
        String input = menu.takeUserInput();
        System.setIn(System.in);

        assertEquals("1", input);
    }

    @Test
    public void shouldDisplayAllBooksInLibraryWhenUserInputsNumericOne() {

        MainMenu menu = new MainMenu();
        Library books = new Library();

        books.addABook(new Book("Five Point Someone", "Chetan Bhagat", 2004));
        books.addABook(new Book("One Night At the Call Center", "Chetan Bhagat", 2005));
        books.addABook(new Book("Revolution 2020", "Chetan Bhagat", 2011));
        menu.executeOption("1", books);

        assertEquals("FIVE POINT SOMEONE  CHETAN BHAGAT  2004\nONE NIGHT AT THE CALL CENTER  CHETAN BHAGAT  2005\nREVOLUTION 2020  CHETAN BHAGAT  2011\n", outputContent.toString());
    }

    @Test
    public void shouldNotifyWhenInvalidOptionIsChosen() {

        MainMenu menu = new MainMenu();
        Library books = new Library();

        books.addABook(new Book("Five Point Someone", "Chetan Bhagat", 2004));
        books.addABook(new Book("One Night At the Call Center", "Chetan Bhagat", 2005));
        books.addABook(new Book("Revolution 2020", "Chetan Bhagat", 2011));

        menu.executeOption("-1", books);
        assertEquals("SELECT A VALID OPTION\n", outputContent.toString());
    }

    @Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();

    @Test
    public void shouldValidateQuit() {
        MainMenu menu = new MainMenu();
        Library books = new Library();

        books.addABook(new Book("Five Point Someone", "Chetan Bhagat", 2004));
        books.addABook(new Book("One Night At the Call Center", "Chetan Bhagat", 2005));
        books.addABook(new Book("Revolution 2020", "Chetan Bhagat", 2011));
        menu.addOptions("List Books");
        menu.addOptions("Checkout a Book");
        menu.addOptions("Quit");

        exit.expectSystemExit();
        menu.executeOption("3", books);
    }

    @Test
    public void shouldValidateCheckOutABook() {
        String bookChoice = "One Night At the Call Center";
        final ByteArrayInputStream inContent = new ByteArrayInputStream(bookChoice.getBytes());
        System.setIn(inContent);

        MainMenu menu = new MainMenu();
        Library books = new Library();

        books.addABook(new Book("Five Point Someone", "Chetan Bhagat", 2004));
        books.addABook(new Book("One Night At the Call Center", "Chetan Bhagat", 2005));
        books.addABook(new Book("Revolution 2020", "Chetan Bhagat", 2011));
        menu.addOptions("List Books");
        menu.addOptions("Checkout a Book");
        menu.addOptions("Quit");

        menu.executeOption("2", books);
        assertEquals("ENTER BOOKNAME:\nThank you! Enjoy the book\n", outputContent.toString());
    }
}