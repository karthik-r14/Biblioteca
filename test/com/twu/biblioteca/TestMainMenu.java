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
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


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
        ArrayList<String> menu = new ArrayList<>();
        MainMenu mainMenu = new MainMenu(menu);
        mainMenu.addOptions("1.List Books");
        mainMenu.displayMenu();

        assertEquals("\n\nMAIN MENU\n1.List Books\n", outputContent.toString());
    }

    @Test
    public void shouldDisplayAllBooksInLibraryWhenUserInputsNumericOne() {
        ArrayList<String> menu = new ArrayList<>();
        MainMenu mainMenu = new MainMenu(menu);

        ArrayList<Book> books = new ArrayList<>();
        books.add(new Book("Five Point Someone", "Chetan Bhagat", 2004));

        Library library = new Library(books, new ArrayList<Movie>(), new ArrayList<UserAccount>());
        mainMenu.executeOption("1", library);

        assertEquals("------------------------------------------------------------------------------------\n" + String.format("%-40S%-40S%-40S", "TITLE", "AUTHOR", "YEAR") + "\n------------------------------------------------------------------------------------\n" + String.format("%-40S%-40S%-40S", "FIVE POINT SOMEONE", "CHETAN BHAGAT", 2004) + "\n", outputContent.toString());
    }

    @Test
    public void shouldNotifyWhenInvalidOptionIsChosen() {
        ArrayList<String> menu = new ArrayList<>();
        MainMenu mainMenu = new MainMenu(menu);

        ArrayList<Book> books = new ArrayList<>();
        books.add(new Book("Five Point Someone", "Chetan Bhagat", 2004));
        books.add(new Book("One Night At the Call Center", "Chetan Bhagat", 2005));
        books.add(new Book("Revolution 2020", "Chetan Bhagat", 2011));

        Library library = new Library(books, new ArrayList<Movie>(), new ArrayList<UserAccount>());
        mainMenu.executeOption("-1", library);
        assertEquals("SELECT A VALID OPTION\n", outputContent.toString());
    }

    @Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();

    @Test
    public void shouldValidateQuit() {
        ArrayList<String> menu = new ArrayList<>();
        MainMenu mainMenu = new MainMenu(menu);
        ArrayList<Book> books = new ArrayList<>();
        books.add(new Book("Five Point Someone", "Chetan Bhagat", 2004));
        books.add(new Book("One Night At the Call Center", "Chetan Bhagat", 2005));
        books.add(new Book("Revolution 2020", "Chetan Bhagat", 2011));

        Library library = new Library(books, new ArrayList<Movie>(), new ArrayList<UserAccount>());
        mainMenu.addOptions("List Books");
        mainMenu.addOptions("Checkout a Book");
        mainMenu.addOptions("EXIT");
        exit.expectSystemExit();
        mainMenu.executeOption("E", library);
    }

    @Test
    public void shouldDisplayAllMoviesWhenUserInputsTwo() {
        ArrayList<String> menu = new ArrayList<>();
        MainMenu mainMenu = new MainMenu(menu);

        ArrayList<Book> books = new ArrayList<>();
        ArrayList<Movie> movies = new ArrayList<>();
        movies.add(new Movie("The Boy in the Striped pyjamas", "Mark Herman", 2008, 7.8f));

        Library library = new Library(books, movies, new ArrayList<UserAccount>());

        mainMenu.executeOption("2", library);
        assertEquals("---------------------------------------------------------------------------------------------------\n" + String.format("%-40S%-25S%-25S%-25S", "MOVIE", "DIRECTOR", "YEAR", "RATING") + "\n---------------------------------------------------------------------------------------------------\n" + String.format("%-40s%-25s%-25s%-25s", "THE BOY IN THE STRIPED PYJAMAS", "MARK HERMAN", 2008, 7.8) + "\n", outputContent.toString());
    }

    @Test
    public void shouldValidateCheckOutMovie() {
        String movieChoice = "The Boy in the striped pyjamas";
        final ByteArrayInputStream inContent = new ByteArrayInputStream(movieChoice.getBytes());
        System.setIn(inContent);

        ArrayList<String> menu = new ArrayList<>();
        MainMenu mainMenu = new MainMenu(menu);

        ArrayList<Movie> movies = new ArrayList<>();
        movies.add(new Movie("The Boy in the Striped pyjamas", "Mark Herman", 2008, 7.8f));

        Library library = new Library(new ArrayList<Book>(), movies, new ArrayList<UserAccount>());
        mainMenu.addOptions("List Books");
        mainMenu.addOptions("Checkout a movie");
        mainMenu.addOptions("Quit");
        mainMenu.executeOption("3", library);

        assertEquals("ENTER MOVIE NAME:\nThank you! Enjoy the movie\n", outputContent.toString());
    }

    @Test
    public void shouldReturnUseruOnSuccesfulLogin() {

        ArrayList<UserAccount> userAccounts = new ArrayList<>();
        userAccounts.add(new UserAccount("123-456", "abcdef", "user", "karu", "karu@gmail.com", "9880431234"));
        userAccounts.add(new UserAccount("123-457", "asdfgh", "user", "kar", "kau@gmail.com", "9088444301"));

        MainMenu mainMenu = new MainMenu(new ArrayList<String>());
        Library library = new Library(new ArrayList<Book>(), new ArrayList<Movie>(), userAccounts);

        String userInput = "123-456\nabcdef";
        final ByteArrayInputStream inContent = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(inContent);

        mainMenu.addOptions("List Books");
        mainMenu.addOptions("Checkout a movie");
        mainMenu.addOptions("Login");

        assertEquals("USER", mainMenu.executeOption("4", library).getRole());
    }

    @Test
    public void shouldReturnDefaultUserOnUnsuccesfulLogin() {

        ArrayList<UserAccount> userAccounts = new ArrayList<>();
        userAccounts.add(new UserAccount("123-456", "abcdef", "user", "karu", "karu@gmail.com", "9880431234"));
        userAccounts.add(new UserAccount("123-457", "asdfgh", "user", "kar", "kau@gmail.com", "9088444301"));

        MainMenu mainMenu = new MainMenu(new ArrayList<String>());
        Library library = new Library(new ArrayList<Book>(), new ArrayList<Movie>(), userAccounts);

        String userInput = "123-456\nabcf";
        final ByteArrayInputStream inContent = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(inContent);

        mainMenu.addOptions("List Books");
        mainMenu.addOptions("Checkout a movie");
        mainMenu.addOptions("Login");

        assertEquals("DEFAULT", mainMenu.executeOption("4", library).getRole());
    }
}