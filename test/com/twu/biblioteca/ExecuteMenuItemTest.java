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

public class ExecuteMenuItemTest {

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
        books.add(new Book("Revolution 2020", "Chetan Bhagat", 2011));

        Library library = new Library(books, new ArrayList<Movie>(), new ArrayList<UserAccount>());
        executeMenu.execute(library);

        assertEquals("------------------------------------------------------------------------------------\n" + String.format("%-40S%-40S%-40S", "TITLE", "AUTHOR", "YEAR") + "\n------------------------------------------------------------------------------------\n" + String.format("%-40S%-40S%-40S", "FIVE POINT SOMEONE", "CHETAN BHAGAT", 2004) + "\n" + String.format("%-40S%-40S%-40S", "REVOLUTION 2020", "CHETAN BHAGAT", 2011) + "\n", outputContent.toString());
    }

    @Test
    public void shouldNotifyWhenInvalidOptionIsChosen() {
        ExecuteMenuItem executeMenu = new ExecuteMenuItem("-1");
        ArrayList<Book> books = new ArrayList<>();
        books.add(new Book("Five Point Someone", "Chetan Bhagat", 2004));
        books.add(new Book("One Night At the Call Center", "Chetan Bhagat", 2005));
        books.add(new Book("Revolution 2020", "Chetan Bhagat", 2011));

        Library library = new Library(books, new ArrayList<Movie>(), new ArrayList<UserAccount>());
        executeMenu.execute(library);

        assertEquals("SELECT A VALID OPTION\n", outputContent.toString());
    }

    @Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();

    @Test
    public void shouldValidateQuit() {
        ExecuteMenuItem executeMenu = new ExecuteMenuItem("E");
        ArrayList<Book> books = new ArrayList<>();
        books.add(new Book("Five Point Someone", "Chetan Bhagat", 2004));
        books.add(new Book("One Night At the Call Center", "Chetan Bhagat", 2005));
        books.add(new Book("Revolution 2020", "Chetan Bhagat", 2011));

        Library library = new Library(books, new ArrayList<Movie>(), new ArrayList<UserAccount>());

        exit.expectSystemExit();
        executeMenu.execute(library);
    }

    @Test
    public void shouldDisplayAllMoviesWhenInputIsTwo() {

        ExecuteMenuItem executeMenu = new ExecuteMenuItem("2");
        ArrayList<Movie> movies = new ArrayList<>();
        movies.add(new Movie("The Boy in the Striped pyjamas", "Mark Herman", 2008, 7.8f));

        Library library = new Library(new ArrayList<Book>(), movies, new ArrayList<UserAccount>());
        executeMenu.execute(library);

        assertEquals("---------------------------------------------------------------------------------------------------\n" + String.format("%-40S%-25S%-25S%-25S", "MOVIE", "DIRECTOR", "YEAR", "RATING") + "\n---------------------------------------------------------------------------------------------------\n" + String.format("%-40S%-25S%-25S%-25S", "The Boy in the Striped pyjamas", "Mark Herman", 2008, 7.8) + "\n", outputContent.toString());
    }

    @Test
    public void shouldCheckoutAMovieWhenInputIsThree() {
        String bookName = "The Boy in the striped pyjamas";
        final ByteArrayInputStream inContent = new ByteArrayInputStream(bookName.getBytes());
        System.setIn(inContent);

        ExecuteMenuItem executeMenu = new ExecuteMenuItem("3");
        ArrayList<Movie> movies = new ArrayList<>();
        movies.add(new Movie("The Boy in the Striped pyjamas", "Mark Herman", 2008, 7.8f));

        Library library = new Library(new ArrayList<Book>(), movies, new ArrayList<UserAccount>());
        executeMenu.execute(library);

        assertEquals("ENTER MOVIE NAME:\nThank you! Enjoy the movie\n", outputContent.toString());
    }

    @Test
    public void shouldReturnUseronSuccessfulLogin() {

        ExecuteMenuItem executeMenu = new ExecuteMenuItem("4");

        ArrayList<UserAccount> userAccounts = new ArrayList<>();
        userAccounts.add(new UserAccount("123-456", "abcdef", "user", "Sachin", "sachin@gmail.com", "9901089765"));
        userAccounts.add(new UserAccount("124-546", "abcdef", "user", "Kaushal", "kaushal@gmail.com", "7760989810"));

        Library library = new Library(new ArrayList<Book>(), new ArrayList<Movie>(), userAccounts);

        String userInput = "123-456\nabcdef";
        final ByteArrayInputStream inContent = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(inContent);

        assertEquals("USER", executeMenu.execute(library).getRole());
    }

    @Test
    public void shouldReturnDefaultUsernUnsuccessfulLogin() {

        ExecuteMenuItem executeMenu = new ExecuteMenuItem("4");

        ArrayList<UserAccount> userAccounts = new ArrayList<>();
        userAccounts.add(new UserAccount("123-456", "abcdef", "user", "karthik", "kar@gmail.com ", "9880443410"));
        userAccounts.add(new UserAccount("124-546", "abcdef", "user", "Dheeraj", "dheeru@gmail.com ", "9880443410"));

        Library library = new Library(new ArrayList<Book>(), new ArrayList<Movie>(), userAccounts);

        String userInput = "123-456\nabcf";
        final ByteArrayInputStream inContent = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(inContent);

        assertEquals("DEFAULT", executeMenu.execute(library).getRole());
    }
}