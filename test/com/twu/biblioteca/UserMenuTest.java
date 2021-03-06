package com.twu.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class UserMenuTest {

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

        UserMenu userMenu = new UserMenu(menu);
        userMenu.addOptions("List Books");
        userMenu.addOptions("Checkout movie");
        userMenu.displayMenu(" ");

        assertEquals("\n\n \nList Books\nCheckout movie\n", outputContent.toString());
    }

    @Test
    public void shouldDisplayAllBooksInLibraryWhenUserInputsNumericOne() {
        ArrayList<String> menu = new ArrayList<>();
        UserMenu userMenu = new UserMenu(menu);

        ArrayList<Book> books = new ArrayList<>();
        books.add(new Book("Five Point Someone", "Chetan Bhagat", 2004));

        Library library = new Library(books, new ArrayList<Movie>(), new ArrayList<UserAccount>());
        userMenu.executeOption("1", library, new UserAccount("124-1234", "abc-defg", "user", "karu", "kar@gmail.com", "9880443410"));

        assertEquals("------------------------------------------------------------------------------------\n" + String.format("%-40S%-40S%-40S", "TITLE", "AUTHOR", "YEAR") + "\n------------------------------------------------------------------------------------\n" + String.format("%-40S%-40S%-40S", "FIVE POINT SOMEONE", "CHETAN BHAGAT", 2004) + "\n", outputContent.toString());
    }

    @Test
    public void shouldNotifyWhenInvalidOptionIsChosen() {
        ArrayList<String> menu = new ArrayList<>();
        UserMenu userMenu = new UserMenu(menu);

        ArrayList<Book> books = new ArrayList<>();
        books.add(new Book("Five Point Someone", "Chetan Bhagat", 2004));
        books.add(new Book("One Night At the Call Center", "Chetan Bhagat", 2005));
        books.add(new Book("Revolution 2020", "Chetan Bhagat", 2011));

        Library library = new Library(books, new ArrayList<Movie>(), new ArrayList<UserAccount>());
        userMenu.executeOption("-1", library, new UserAccount("124-1234", "abc-defg", "user", "karu", "karu@gmail.com", "9880443410"));
        assertEquals("SELECT A VALID OPTION\n", outputContent.toString());
    }

    @Test
    public void shouldValidateCheckOutABook() {
        String bookChoice = "One Night At the Call Center";
        final ByteArrayInputStream inContent = new ByteArrayInputStream(bookChoice.getBytes());
        System.setIn(inContent);

        ArrayList<String> menu = new ArrayList<>();
        UserMenu userMenu = new UserMenu(menu);

        ArrayList<Book> books = new ArrayList<>();
        books.add(new Book("Five Point Someone", "Chetan Bhagat", 2004));
        books.add(new Book("One Night At the Call Center", "Chetan Bhagat", 2005));
        books.add(new Book("Revolution 2020", "Chetan Bhagat", 2011));

        Library library = new Library(books, new ArrayList<Movie>(), new ArrayList<UserAccount>());
        userMenu.addOptions("List Books");
        userMenu.addOptions("Checkout a Book");
        userMenu.executeOption("2", library, new UserAccount("124-1234", "abc-defg", "user", "karu", "karu@gmail.com", "9880443410"));

        assertEquals("ENTER BOOKNAME:\nThank you! Enjoy the book\n", outputContent.toString());
    }

    @Test
    public void shouldSuccessfullyReturnABookBack() {
        String bookName = "Five Point Someone";
        final ByteArrayInputStream inContent = new ByteArrayInputStream(bookName.getBytes());
        System.setIn(inContent);

        ArrayList<String> menu = new ArrayList<>();
        UserMenu userMenu = new UserMenu(menu);

        ArrayList<Book> books = new ArrayList<>();
        books.add(new Book("Five Point Someone", "Chetan Bhagat", 2004));
        books.add(new Book("One Night At the Call Center", "Chetan Bhagat", 2005));
        books.add(new Book("Revolution 2020", "Chetan Bhagat", 2011));

        Library library = new Library(books, new ArrayList<Movie>(), new ArrayList<UserAccount>());
        UserAccount userAccount = new UserAccount("123-4567", "qwerty", "USER", "karu", "karu@gmail.com", "9880443410");
        library.checkoutABook("Five Point Someone", userAccount);
        userMenu.addOptions("List Books");
        userMenu.addOptions("Checkout a Book");
        userMenu.addOptions("Return a Book");

        userMenu.executeOption("3", library, userAccount);

        assertEquals("ENTER BOOK TO BE RETURNED:\nThank you for returning the book\n", outputContent.toString());
    }

    @Test
    public void shouldNotifyOnUnsuccessfulReturnOfBook() {
        String bookName = "2 States";
        final ByteArrayInputStream inContent = new ByteArrayInputStream(bookName.getBytes());
        System.setIn(inContent);

        ArrayList<String> menu = new ArrayList<>();
        UserMenu userMenu = new UserMenu(menu);

        ArrayList<Book> books = new ArrayList<>();
        books.add(new Book("Five Point Someone", "Chetan Bhagat", 2004));
        books.add(new Book("One Night At the Call Center", "Chetan Bhagat", 2005));
        books.add(new Book("Revolution 2020", "Chetan Bhagat", 2011));

        Library library = new Library(books, new ArrayList<Movie>(), new ArrayList<UserAccount>());
        library.checkoutABook("Five Point Someone", new UserAccount("123-4567", "qwerty", "USER", "karu", "karu@gmail.com", "9880443410"));
        userMenu.addOptions("List Books");
        userMenu.addOptions("Checkout a Book");
        userMenu.addOptions("Return a Book");
        userMenu.executeOption("3", library, new UserAccount("124-1234", "abc-defg", "user", "karu", "karu@gmail.com", "9880443410"));

        assertEquals("ENTER BOOK TO BE RETURNED:\nThat is not a valid book to return\n", outputContent.toString());
    }

    @Test
    public void shouldDisplayAllMoviesWhenUserInputsFour() {
        ArrayList<String> menu = new ArrayList<>();
        UserMenu userMenu = new UserMenu(menu);

        ArrayList<Book> books = new ArrayList<>();
        ArrayList<Movie> movies = new ArrayList<>();
        movies.add(new Movie("The Boy in the Striped pyjamas", "Mark Herman", 2008, 7.8f));

        Library library = new Library(books, movies, new ArrayList<UserAccount>());

        userMenu.executeOption("4", library, new UserAccount("124-1234", "abc-defg", "user", "karu", "karu@gmail.com", "9880443410"));
        assertEquals("---------------------------------------------------------------------------------------------------\n" + String.format("%-40S%-25S%-25S%-25S", "MOVIE", "DIRECTOR", "YEAR", "RATING") + "\n---------------------------------------------------------------------------------------------------\n" + String.format("%-40s%-25s%-25s%-25s", "THE BOY IN THE STRIPED PYJAMAS", "MARK HERMAN", 2008, 7.8) + "\n", outputContent.toString());
    }

    @Test
    public void shouldValidateCheckOutMovie() {
        String movieChoice = "The Boy in the striped pyjamas";
        final ByteArrayInputStream inContent = new ByteArrayInputStream(movieChoice.getBytes());
        System.setIn(inContent);

        ArrayList<String> menu = new ArrayList<>();
        UserMenu userMenu = new UserMenu(menu);

        ArrayList<Movie> movies = new ArrayList<>();
        movies.add(new Movie("The Boy in the Striped pyjamas", "Mark Herman", 2008, 7.8f));

        Library library = new Library(new ArrayList<Book>(), movies, new ArrayList<UserAccount>());
        userMenu.addOptions("List Books");
        userMenu.addOptions("Checkout a movie");
        userMenu.executeOption("5", library, new UserAccount("124-1234", "abc-defg", "user", "karu", "karu@gmail.com", "9880443410"));

        assertEquals("ENTER MOVIE NAME:\nThank you! Enjoy the movie\n", outputContent.toString());
    }

    @Test
    public void shouldnotifyWhenMovieIsNotAvailable() {
        String bookName = "Happy Feet";
        final ByteArrayInputStream inContent = new ByteArrayInputStream(bookName.getBytes());
        System.setIn(inContent);

        ArrayList<String> menu = new ArrayList<>();
        UserMenu userMenu = new UserMenu(menu);

        ArrayList<Movie> movies = new ArrayList<>();
        movies.add(new Movie("The Boy in the Striped pyjamas", "Mark Herman", 2008, 7.8f));

        Library library = new Library(new ArrayList<Book>(), movies, new ArrayList<UserAccount>());
        userMenu.addOptions("List Books");
        userMenu.addOptions("Checkout a movie");
        userMenu.executeOption("5", library, new UserAccount("karthik_r14", "abc-defg", "user", "karu", "karu@gmail.com", "9880443410"));

        assertEquals("ENTER MOVIE NAME:\nThat movie is not available\n", outputContent.toString());
    }

    @Test
    public void shouldDisplayUserDetails() {
        UserMenu userMenu = new UserMenu(new ArrayList<String>());
        userMenu.executeOption("6", new Library(new ArrayList<Book>(), new ArrayList<Movie>(), new ArrayList<UserAccount>()), new UserAccount("124-1234", "abc-defg", "user", "karu", "karu@gmail.com", "9880443410"));

        assertEquals("USER NAME :124-1234\nNAME :KARU" + "\nEMAIL ADDRESS :karu@gmail.com" + "\nPHONE NUMBER :9880443410\nROLE :USER\n", outputContent.toString());
    }
}