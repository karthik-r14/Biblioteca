package com.twu.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class TestLibrary {

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
    public void shouldListBooksInTheLibrary() {
        ArrayList<Book> books = new ArrayList<>();
        books.add(new Book("Five Point Someone", "Chetan Bhagat", 2004));
        books.add(new Book("Revolution 2020", "Chetan Bhagat", 2011));

        Library library = new Library(books, new ArrayList<Movie>(), new ArrayList<UserAccount>());
        library.displayBooks();

        assertEquals(String.format("%-40S%-40S%-40S", "FIVE POINT SOMEONE", "CHETAN BHAGAT", 2004) + "\n" + String.format("%-40S%-40S%-40S", "Revolution 2020", "CHETAN BHAGAT", 2011) + "\n", outputContent.toString());

    }

    @Test
    public void shouldCheckOutABookFromTheLibrary() {
        ArrayList<Book> books = new ArrayList<>();
        books.add(new Book("Five Point Someone", "Chetan Bhagat", 2004));
        books.add(new Book("One Night At the Call Center", "Chetan Bhagat", 2005));
        books.add(new Book("Revolution 2020", "Chetan Bhagat", 2011));

        Library library = new Library(books, new ArrayList<Movie>(), new ArrayList<UserAccount>());

        assertEquals("Thank you! Enjoy the book", library.checkoutABook("Five Point Someone", new UserAccount("123-4567", "qwerty", "USER", "kar", "kau@gmail.com", "9088444301")));
    }

    @Test
    public void shouldNotifyWhenABookIsNotAvailableInTheLibrary() {
        ArrayList<Book> books = new ArrayList<>();
        books.add(new Book("Five Point Someone", "Chetan Bhagat", 2004));
        books.add(new Book("One Night At the Call Center", "Chetan Bhagat", 2005));
        books.add(new Book("Revolution 2020", "Chetan Bhagat", 2011));

        Library library = new Library(books, new ArrayList<Movie>(), new ArrayList<UserAccount>());

        assertEquals("That book is not available", library.checkoutABook("2 States", new UserAccount("123-4567", "qwerty", "USER", "kar", "kau@gmail.com", "9088444301")));
    }

    @Test
    public void shouldSuccessfullyReturnABookBackToTheLibrary() {
        ArrayList<Book> books = new ArrayList<>();
        books.add(new Book("Five Point Someone", "Chetan Bhagat", 2004));
        books.add(new Book("One Night At the Call Center", "Chetan Bhagat", 2005));
        books.add(new Book("Revolution 2020", "Chetan Bhagat", 2011));

        Library library = new Library(books, new ArrayList<Movie>(), new ArrayList<UserAccount>());
        library.checkoutABook("Five Point Someone", new UserAccount("123-4567", "qwerty", "USER", "kar", "kau@gmail.com", "9088444301"));

        assertEquals("Thank you for returning the book", library.returnABook("Five Point Someone"));
    }

    @Test
    public void shouldNotifyOnUnsuccessfulReturnOfBookToTheLibrary() {
        ArrayList<Book> books = new ArrayList<>();
        books.add(new Book("Five Point Someone", "Chetan Bhagat", 2004));
        books.add(new Book("One Night At the Call Center", "Chetan Bhagat", 2005));
        books.add(new Book("Revolution 2020", "Chetan Bhagat", 2011));

        Library library = new Library(books, new ArrayList<Movie>(), new ArrayList<UserAccount>());
        library.checkoutABook("Five Point Someone", new UserAccount("123-4567", "qwerty", "USER", "kar", "kau@gmail.com", "9088444301"));

        assertEquals("That is not a valid book to return", library.returnABook("2 states"));
    }

    @Test
    public void shouldDisplayAllMovies() {
        ArrayList<String> menu = new ArrayList<>();

        ArrayList<Book> books = new ArrayList<>();
        ArrayList<Movie> movies = new ArrayList<>();
        movies.add(new Movie("The Boy in the Striped pyjamas", "Mark Herman", 2008, 7.8f));

        Library library = new Library(books, movies, new ArrayList<UserAccount>());
        library.displayMovie();

        assertEquals(String.format("%-40s%-25s%-25s%-25s", "THE BOY IN THE STRIPED PYJAMAS", "MARK HERMAN", 2008, 7.8) + "\n", outputContent.toString());
    }

    @Test
    public void shouldCheckOutAMovieFromTheLibrary() {
        ArrayList<Movie> movies = new ArrayList<>();
        movies.add(new Movie("The Boy in the Striped pyjamas", "Mark Herman", 2008, 7.8f));

        Library library = new Library(new ArrayList<Book>(), movies, new ArrayList<UserAccount>());

        assertEquals("Thank you! Enjoy the movie", library.checkoutAMovie("The Boy in the Striped pyjamas"));
    }

    @Test
    public void shouldNotCheckOutAMovieFromTheLibrary() {
        ArrayList<Movie> movies = new ArrayList<>();
        movies.add(new Movie("The Boy in the Striped pyjamas", "Mark Herman", 2008, 7.8f));

        Library library = new Library(new ArrayList<Book>(), movies, new ArrayList<UserAccount>());

        assertEquals("That movie is not available", library.checkoutAMovie("V for Vendatta"));
    }

    @Test
    public void shouldReturnUserAccountOnSuccessfulLogin() {

        ArrayList<UserAccount> userAccounts = new ArrayList<>();
        userAccounts.add(new UserAccount("123-456", "abcdef", "user", "kar", "kau@gmail.com", "9088444301"));
        userAccounts.add(new UserAccount("124-546", "abcdef", "user", "kar", "kau@gmail.com", "9088444301"));

        String userInput = "123-456\nabcdef";
        final ByteArrayInputStream inContent = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(inContent);

        Library library = new Library(new ArrayList<Book>(), new ArrayList<Movie>(), userAccounts);

        assertEquals("USER", library.login().getRole());
    }

    @Test
    public void shouldReturnNullOnUnsuccessfulLogin() {

        ArrayList<UserAccount> userAccounts = new ArrayList<>();
        userAccounts.add(new UserAccount("123-456", "abcdef", "user", "kar", "kau@gmail.com", "9088444301"));
        userAccounts.add(new UserAccount("124-546", "abcdef", "user", "kar", "kau@gmail.com", "9088444301"));

        String userInput = "123-456\nabcd";
        final ByteArrayInputStream inContent = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(inContent);

        Library library = new Library(new ArrayList<Book>(), new ArrayList<Movie>(), userAccounts);

        assertEquals("DEFAULT", library.login().getRole());
    }

    @Test
    public void shouldDisplayCheckedOutBookDetails() {

        ArrayList<Book> books = new ArrayList<>();
        books.add(new Book("Five Point Someone", "Chetan Bhagat", 2004));
        books.add(new Book("One Night At the Call Center", "Chetan Bhagat", 2005));
        books.add(new Book("Revolution 2020", "Chetan Bhagat", 2011));

        ArrayList<UserAccount> userAccounts = new ArrayList<>();
        userAccounts.add(new UserAccount("123-456", "abcdef", "user", "kar", "kau@gmail.com", "9088444301"));
        userAccounts.add(new UserAccount("124-546", "abcdef", "user", "kar", "kau@gmail.com", "9088444301"));

        Library library = new Library(books, new ArrayList<Movie>(), userAccounts);
        library.checkoutABook("Revolution 2020", userAccounts.get(0));
        library.displayCheckedOutBookDetails();

        assertEquals(String.format("%-40S%-40S%-40S", "REVOLUTION 2020", "CHETAN BHAGAT", "2011") + "\n" + "USER NAME :123-456\nNAME :KAR\nROLE :USER\n\n\n", outputContent.toString());
    }
}
