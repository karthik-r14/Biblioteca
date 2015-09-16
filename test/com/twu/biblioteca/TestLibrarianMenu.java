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

    @Test
    public void shouldSuccessfullyReturnABookBack() {
        String bookName = "Five Point Someone";
        final ByteArrayInputStream inContent = new ByteArrayInputStream(bookName.getBytes());
        System.setIn(inContent);

        ArrayList<String> menu = new ArrayList<>();
        LibrarianMenu librarianMenu = new LibrarianMenu(new UserMenu(menu));


        ArrayList<Book> books = new ArrayList<>();
        books.add(new Book("Five Point Someone", "Chetan Bhagat", 2004));
        books.add(new Book("One Night At the Call Center", "Chetan Bhagat", 2005));
        books.add(new Book("Revolution 2020", "Chetan Bhagat", 2011));

        Library library = new Library(books, new ArrayList<Movie>());
        library.checkoutABook("Five Point Someone");
        librarianMenu.addOptions("List Books");
        librarianMenu.addOptions("Checkout a Book");
        librarianMenu.addOptions("Return a Book");

        librarianMenu.executeOption("3", library, new UserAccount("124-1234", "abc-defg", "user"));

        assertEquals("ENTER BOOK TO BE RETURNED:\nThank you for returning the book\n", outputContent.toString());
    }

    @Test
    public void shouldNotifyOnUnsuccessfulReturnOfBook() {
        String bookName = "2 States";
        final ByteArrayInputStream inContent = new ByteArrayInputStream(bookName.getBytes());
        System.setIn(inContent);

        ArrayList<String> menu = new ArrayList<>();
        LibrarianMenu librarianMenu = new LibrarianMenu(new UserMenu(menu));

        ArrayList<Book> books = new ArrayList<>();
        books.add(new Book("Five Point Someone", "Chetan Bhagat", 2004));
        books.add(new Book("One Night At the Call Center", "Chetan Bhagat", 2005));
        books.add(new Book("Revolution 2020", "Chetan Bhagat", 2011));

        Library library = new Library(books, new ArrayList<Movie>());
        library.checkoutABook("Five Point Someone");
        librarianMenu.addOptions("List Books");
        librarianMenu.addOptions("Checkout a Book");
        librarianMenu.addOptions("Return a Book");
        librarianMenu.executeOption("3", library, new UserAccount("124-1234", "abc-defg", "user"));

        assertEquals("ENTER BOOK TO BE RETURNED:\nThat is not a valid book to return\n", outputContent.toString());
    }

    @Test
    public void shouldDisplayAllMoviesWhenUserInputsFour() {
        ArrayList<String> menu = new ArrayList<>();
        LibrarianMenu librarianMenu = new LibrarianMenu(new UserMenu(menu));

        ArrayList<Book> books = new ArrayList<>();
        ArrayList<Movie> movies = new ArrayList<>();
        movies.add(new Movie("The Boy in the Striped pyjamas", "Mark Herman", 2008, 7.8f));

        Library library = new Library(books, movies);

        librarianMenu.executeOption("4", library, new UserAccount("124-1234", "abc-defg", "user"));
        assertEquals("---------------------------------------------------------------------------------------------------\n" + String.format("%-40S%-25S%-25S%-25S", "MOVIE", "DIRECTOR", "YEAR", "RATING") + "\n---------------------------------------------------------------------------------------------------\n" + String.format("%-40s%-25s%-25s%-25s", "THE BOY IN THE STRIPED PYJAMAS", "MARK HERMAN", 2008, 7.8) + "\n", outputContent.toString());
    }

    @Test
    public void shouldValidateCheckOutMovie() {
        String movieChoice = "The Boy in the striped pyjamas";
        final ByteArrayInputStream inContent = new ByteArrayInputStream(movieChoice.getBytes());
        System.setIn(inContent);

        ArrayList<String> menu = new ArrayList<>();
        LibrarianMenu librarianMenu = new LibrarianMenu(new UserMenu(menu));

        ArrayList<Movie> movies = new ArrayList<>();
        movies.add(new Movie("The Boy in the Striped pyjamas", "Mark Herman", 2008, 7.8f));

        Library library = new Library(new ArrayList<Book>(), movies);
        librarianMenu.addOptions("List Books");
        librarianMenu.addOptions("Checkout a movie");
        librarianMenu.executeOption("5", library, new UserAccount("124-1234", "abc-defg", "user") );

        assertEquals("ENTER MOVIE NAME:\nThank you! Enjoy the movie\n", outputContent.toString());
    }

    @Test
    public void shouldnotifyWhenMovieIsNotAvailable() {
        String bookName = "Happy Feet";
        final ByteArrayInputStream inContent = new ByteArrayInputStream(bookName.getBytes());
        System.setIn(inContent);

        ArrayList<String> menu = new ArrayList<>();
        LibrarianMenu librarianMenu = new LibrarianMenu(new UserMenu(menu));

        ArrayList<Movie> movies = new ArrayList<>();
        movies.add(new Movie("The Boy in the Striped pyjamas", "Mark Herman", 2008, 7.8f));

        Library library = new Library(new ArrayList<Book>(), movies);
        librarianMenu.addOptions("List Books");
        librarianMenu.addOptions("Checkout a movie");
        librarianMenu.executeOption("5", library, new UserAccount("karthik_r14", "abc-defg", "user"));

        assertEquals("ENTER MOVIE NAME:\nThat movie is not available\n", outputContent.toString());
    }

    @Test
    public void shouldDisplayUserDetails() {
        LibrarianMenu librarianMenu = new LibrarianMenu(new UserMenu(new ArrayList<String>()));
        librarianMenu.executeOption("6", new Library(new ArrayList<Book>(), new ArrayList<Movie>()), new UserAccount("124-1234", "abc-defg", "user"));

        assertEquals("USER NAME :124-1234\nROLE :USER\n", outputContent.toString());
    }
}