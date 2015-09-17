package com.twu.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class TestExecuteUserMenuItem {

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
        ExecuteUserMenuItem executeMenu = new ExecuteUserMenuItem("1");
        ArrayList<Book> books = new ArrayList<>();
        books.add(new Book("Five Point Someone", "Chetan Bhagat", 2004));
        books.add(new Book("Revolution 2020", "Chetan Bhagat", 2011));

        Library library = new Library(books, new ArrayList<Movie>(), new ArrayList<UserAccount>());
        executeMenu.execute(library, new UserAccount("karthik_r14", "abc-defg", "user", "karthik", "kar@gmail.com ", "9880443410"));

        assertEquals("------------------------------------------------------------------------------------\n" + String.format("%-40S%-40S%-40S", "TITLE", "AUTHOR", "YEAR") + "\n------------------------------------------------------------------------------------\n" + String.format("%-40S%-40S%-40S", "FIVE POINT SOMEONE", "CHETAN BHAGAT", 2004) + "\n" + String.format("%-40S%-40S%-40S", "REVOLUTION 2020", "CHETAN BHAGAT", 2011) + "\n", outputContent.toString());
    }

    @Test
    public void shouldValidateCheckoutBooks() {
        String bookName = "Revolution 2020";
        ByteArrayInputStream inContent = new ByteArrayInputStream(bookName.getBytes());
        System.setIn(inContent);

        ExecuteUserMenuItem executeMenu = new ExecuteUserMenuItem("2");
        ArrayList<Book> books = new ArrayList<>();
        books.add(new Book("Five Point Someone", "Chetan Bhagat", 2004));
        books.add(new Book("One Night At the Call Center", "Chetan Bhagat", 2005));
        books.add(new Book("Revolution 2020", "Chetan Bhagat", 2011));

        Library library = new Library(books, new ArrayList<Movie>(), new ArrayList<UserAccount>());
        executeMenu.execute(library, new UserAccount("karthik_r14", "abc-defg", "user", "karthik", "kar@gmail.com ", "9880443410"));

        assertEquals("ENTER BOOKNAME:\nThank you! Enjoy the book\n", outputContent.toString());
    }

    @Test
    public void shouldNotifyWhenBookIsNotAvailable() {
        String bookName = "2 states";
        final ByteArrayInputStream inContent = new ByteArrayInputStream(bookName.getBytes());
        System.setIn(inContent);

        ExecuteUserMenuItem executeMenu = new ExecuteUserMenuItem("2");
        ArrayList<Book> books = new ArrayList<>();
        books.add(new Book("Five Point Someone", "Chetan Bhagat", 2004));
        books.add(new Book("One Night At the Call Center", "Chetan Bhagat", 2005));
        books.add(new Book("Revolution 2020", "Chetan Bhagat", 2011));

        Library library = new Library(books, new ArrayList<Movie>(), new ArrayList<UserAccount>());
        executeMenu.execute(library, new UserAccount("karthik_r14", "abc-defg", "user", "karthik", "kar@gmail.com ", "9880443410"));

        assertEquals("ENTER BOOKNAME:\nThat book is not available\n", outputContent.toString());
    }

    @Test
    public void shouldSuccessfullyReturnABookBack() {
        String bookName = "Five Point Someone";
        final ByteArrayInputStream inContent = new ByteArrayInputStream(bookName.getBytes());
        System.setIn(inContent);

        ExecuteUserMenuItem executeMenu = new ExecuteUserMenuItem("3");

        ArrayList<Book> books = new ArrayList<>();
        books.add(new Book("Five Point Someone", "Chetan Bhagat", 2004));
        books.add(new Book("One Night At the Call Center", "Chetan Bhagat", 2005));
        books.add(new Book("Revolution 2020", "Chetan Bhagat", 2011));

        Library library = new Library(books, new ArrayList<Movie>(), new ArrayList<UserAccount>());
        library.checkoutABook("Five Point Someone", new UserAccount("123-4567", "qwerty", "USER", "Sujith", "sujith032@gmail.com", "9535883552"));
        executeMenu.execute(library, new UserAccount("karthik_r14", "abc-defg", "user", "karthik", "kar@gmail.com ", "9880443410"));

        assertEquals("ENTER BOOK TO BE RETURNED:\nThank you for returning the book\n", outputContent.toString());
    }

    @Test
    public void shouldNotifyOnUnsuccessfulReturnOfBook() {
        String bookName = "2 States";
        final ByteArrayInputStream inContent = new ByteArrayInputStream(bookName.getBytes());
        System.setIn(inContent);

        ExecuteUserMenuItem executeMenu = new ExecuteUserMenuItem("3");

        ArrayList<Book> books = new ArrayList<>();
        books.add(new Book("Five Point Someone", "Chetan Bhagat", 2004));
        books.add(new Book("One Night At the Call Center", "Chetan Bhagat", 2005));
        books.add(new Book("Revolution 2020", "Chetan Bhagat", 2011));

        Library library = new Library(books, new ArrayList<Movie>(), new ArrayList<UserAccount>());
        library.checkoutABook("Five Point Someone", new UserAccount("123-4567", "qwerty", "USER", "sujith", "sujith032@gmail.com", "9535883552"));
        executeMenu.execute(library, new UserAccount("karthik_r14", "abc-defg", "user", "karthik", "kar@gmail.com ", "9880443410"));

        assertEquals("ENTER BOOK TO BE RETURNED:\nThat is not a valid book to return\n", outputContent.toString());
    }

    @Test
    public void shouldDisplayAllMoviesWhenInputsNumericFour() {

        ExecuteUserMenuItem executeMenu = new ExecuteUserMenuItem("4");
        ArrayList<Movie> movies = new ArrayList<>();
        movies.add(new Movie("The Boy in the Striped pyjamas", "Mark Herman", 2008, 7.8f));

        Library library = new Library(new ArrayList<Book>(), movies, new ArrayList<UserAccount>());
        executeMenu.execute(library, new UserAccount("karthik_r14", "abc-defg", "user", "karthik", "kar@gmail.com ", "9880443410"));

        assertEquals("---------------------------------------------------------------------------------------------------\n" + String.format("%-40S%-25S%-25S%-25S", "MOVIE", "DIRECTOR", "YEAR", "RATING") + "\n---------------------------------------------------------------------------------------------------\n" + String.format("%-40S%-25S%-25S%-25S", "The Boy in the Striped pyjamas", "Mark Herman", 2008, 7.8) + "\n", outputContent.toString());
    }

    @Test
    public void shouldCheckoutAMovieWhenInputIsFIVE() {
        String bookName = "The Boy in the striped pyjamas";
        final ByteArrayInputStream inContent = new ByteArrayInputStream(bookName.getBytes());
        System.setIn(inContent);

        ExecuteUserMenuItem executeMenu = new ExecuteUserMenuItem("5");
        ArrayList<Movie> movies = new ArrayList<>();
        movies.add(new Movie("The Boy in the Striped pyjamas", "Mark Herman", 2008, 7.8f));

        Library library = new Library(new ArrayList<Book>(), movies, new ArrayList<UserAccount>());
        executeMenu.execute(library, new UserAccount("karthik_r14", "abc-defg", "user", "karthik", "kar@gmail.com ", "9880443410"));

        assertEquals("ENTER MOVIE NAME:\nThank you! Enjoy the movie\n", outputContent.toString());
    }

    @Test
    public void shouldnotifyWhenMovieIsNotAvailable() {
        String bookName = "Happy Feet";
        final ByteArrayInputStream inContent = new ByteArrayInputStream(bookName.getBytes());
        System.setIn(inContent);

        ExecuteUserMenuItem executeMenu = new ExecuteUserMenuItem("5");
        ArrayList<Movie> movies = new ArrayList<>();
        movies.add(new Movie("The Boy in the Striped pyjamas", "Mark Herman", 2008, 7.8f));

        Library library = new Library(new ArrayList<Book>(), movies, new ArrayList<UserAccount>());

        executeMenu.execute(library, new UserAccount("karthik_r14", "abc-defg", "user", "karthik", "kar@gmail.com ", "9880443410"));

        assertEquals("ENTER MOVIE NAME:\nThat movie is not available\n", outputContent.toString());
    }

    @Test
    public void shouldDisplayUserDetails() {
        ExecuteUserMenuItem executeMenu = new ExecuteUserMenuItem("6");
        executeMenu.execute(new Library(new ArrayList<Book>(), new ArrayList<Movie>(), new ArrayList<UserAccount>()), new UserAccount("124-1234", "abc-defg", "user", "kar", "kau@gmail.com", "9088444301"));

        assertEquals("USER NAME :124-1234\nNAME :KAR\n" + "EMAIL ADDRESS :kau@gmail.com\n" + "PHONE NUMBER :9088444301\nROLE :USER\n", outputContent.toString());
    }

    @Test
    public void shouldValidateLogOut() {
        ExecuteUserMenuItem executeMenu = new ExecuteUserMenuItem("7");
        UserAccount userAccount = executeMenu.execute(new Library(new ArrayList<Book>(), new ArrayList<Movie>(), new ArrayList<UserAccount>()), new UserAccount("124-1234", "abc-defg", "user", "kar", "kau@gmail.com", "9088444301"));

        assertEquals("DEFAULT", userAccount.getRole());
    }
}