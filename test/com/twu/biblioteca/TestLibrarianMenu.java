package com.twu.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class TestLibrarianMenu {

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
    public void shouldDisplayUserMenu() {

        ArrayList<String> menu = new ArrayList<>();

        LibrarianMenu librarianMenu = new LibrarianMenu(new UserMenu(menu));
        librarianMenu.addOptions("List Books");
        librarianMenu.addOptions("List Movie");
        librarianMenu.displayMenu();

        assertEquals("List Books\nList Movie\n", outputContent.toString());
    }

    @Test
    public void shouldDisplayAllBooksInLibraryWhenUserInputsNumericOne() {
        ArrayList<String> menu = new ArrayList<>();
        LibrarianMenu librarianMenu = new LibrarianMenu(new UserMenu(menu));
        librarianMenu.addOptions("List Books");
        librarianMenu.addOptions("List Movie");

        ArrayList<Book> books = new ArrayList<>();
        books.add(new Book("Five Point Someone", "Chetan Bhagat", 2004));

        Library library = new Library(books, new ArrayList<Movie>());
        librarianMenu.executeOption("1", library, new UserAccount("124-1234", "abc-defg", "user"));

        assertEquals("------------------------------------------------------------------------------------\n" + String.format("%-40S%-40S%-40S", "TITLE", "AUTHOR", "YEAR") + "\n------------------------------------------------------------------------------------\n" + String.format("%-40S%-40S%-40S", "FIVE POINT SOMEONE", "CHETAN BHAGAT", 2004) + "\n", outputContent.toString());
    }

    @Test
    public void shouldNotifyWhenInvalidOptionIsChosen() {
        ArrayList<String> menu = new ArrayList<>();
        LibrarianMenu librarianMenu = new LibrarianMenu(new UserMenu(menu));
        librarianMenu.addOptions("List Books");

        ArrayList<Book> books = new ArrayList<>();
        books.add(new Book("Five Point Someone", "Chetan Bhagat", 2004));
        books.add(new Book("One Night At the Call Center", "Chetan Bhagat", 2005));
        books.add(new Book("Revolution 2020", "Chetan Bhagat", 2011));

        Library library = new Library(books, new ArrayList<Movie>());
        librarianMenu.executeOption("-1", library, new UserAccount("124-1234", "abc-defg", "user"));
        assertEquals("SELECT A VALID OPTION\n", outputContent.toString());
    }

    @Test
    public void shouldValidateCheckOutABook() {
        String bookChoice = "One Night At the Call Center";
        final ByteArrayInputStream inContent = new ByteArrayInputStream(bookChoice.getBytes());
        System.setIn(inContent);

        ArrayList<String> menu = new ArrayList<>();
        LibrarianMenu librarianMenu = new LibrarianMenu(new UserMenu(menu));
        librarianMenu.addOptions("List Books");

        ArrayList<Book> books = new ArrayList<>();
        books.add(new Book("Five Point Someone", "Chetan Bhagat", 2004));
        books.add(new Book("One Night At the Call Center", "Chetan Bhagat", 2005));
        books.add(new Book("Revolution 2020", "Chetan Bhagat", 2011));

        Library library = new Library(books, new ArrayList<Movie>());
        librarianMenu.addOptions("List Books");
        librarianMenu.addOptions("Checkout a Book");
        librarianMenu.executeOption("2", library, new UserAccount("124-1234", "abc-defg", "user"));

        assertEquals("ENTER BOOKNAME:\nThank you! Enjoy the book\n", outputContent.toString());
    }

    

}