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
import java.util.Scanner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestBibliotecaApplication {

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
    public void shouldDisplayTheListOfBooksWhenUserInputsOne() {
        String userChoice = "1";
        final ByteArrayInputStream inContent = new ByteArrayInputStream(userChoice.getBytes());
        System.setIn(inContent);

        ArrayList<String> menu = new ArrayList<>();
        ReadInput input = new ReadInput(new Scanner(System.in));
        MainMenu mainMenu = new MainMenu(menu);
        mainMenu.addOptions("1.List Book");
        mainMenu.addOptions("2.Exit");

        ArrayList<Book> books = new ArrayList<>();
        books.add(new Book("Da Vinci code", "DAN BROWN", 2003));
        books.add(new Book("Adventures of Sherlock Holmes", "Arthur Conan Doyle", 1892));

        Library library = new Library(books, new ArrayList<Movie>());
        WelcomeMessage welcomeMessage = new WelcomeMessage("WELCOME TO BIBLIOTECA");
        BibliotecaApplication biblioteca = new BibliotecaApplication(mainMenu, library, welcomeMessage, input);
        biblioteca.start();
        biblioteca.run();

        assertEquals("WELCOME TO BIBLIOTECA\n1.List Book\n2.Exit\nEnter choice :\n" + "------------------------------------------------------------------------------------\n" + String.format("%-40S%-40S%-40S", "TITLE", "AUTHOR", "YEAR") + "\n------------------------------------------------------------------------------------\n" + String.format("%-40S%-40S%-40S", "DA VINCI CODE", "DAN BROWN", 2003) + "\n" + String.format("%-40S%-40S%-40S", "ADVENTURES OF SHERLOCK HOLMES", "ARTHUR CONAN DOYLE", 1892) + "\n", outputContent.toString());
    }

    @Test
    public void shouldDisplayWelcomeMessageOnStart() {
        ArrayList<String> menu = new ArrayList<>();
        ReadInput input = new ReadInput(new Scanner(System.in));
        MainMenu mainMenu = new MainMenu(menu);
        mainMenu.addOptions("1.List Book");
        mainMenu.addOptions("2.Exit");

        ArrayList<Book> books = new ArrayList<>();
        books.add(new Book("Da Vinci code", "DAN BROWN", 2003));
        books.add(new Book("Adventures of Sherlock Holmes", "Arthur Conan Doyle", 1892));

        WelcomeMessage welcomeMessage = new WelcomeMessage("WELCOME TO BIBLIOTECA");
        Library library = new Library(books, new ArrayList<Movie>());
        BibliotecaApplication biblioteca = new BibliotecaApplication(mainMenu, library, welcomeMessage, input);
        biblioteca.start();

        assertEquals("WELCOME TO BIBLIOTECA\n", outputContent.toString());

    }

    @Test
    public void shouldDisplayAMessageWhenUserEntersInvalidOption() {
        String userChoice = "8";
        final ByteArrayInputStream inContent = new ByteArrayInputStream(userChoice.getBytes());
        System.setIn(inContent);

        ArrayList<String> menu = new ArrayList<>();
        ReadInput input = new ReadInput(new Scanner(System.in));
        MainMenu mainMenu = new MainMenu(menu);
        mainMenu.addOptions("1.List Book");
        mainMenu.addOptions("2.Exit");

        ArrayList<Book> books = new ArrayList<>();
        books.add(new Book("Da Vinci code", "DAN BROWN", 2003));
        books.add(new Book("Adventures of Sherlock Holmes", "Arthur Conan Doyle", 1892));

        WelcomeMessage welcomeMessage = new WelcomeMessage("WELCOME TO BIBLIOTECA");
        Library library = new Library(books, new ArrayList<Movie>());
        BibliotecaApplication biblioteca = new BibliotecaApplication(mainMenu, library, welcomeMessage, input);
        biblioteca.start();
        biblioteca.run();

        assertEquals("WELCOME TO BIBLIOTECA\n1.List Book\n2.Exit\nEnter choice :\nSELECT A VALID OPTION\n", outputContent.toString());
    }

    @Test
    public void shouldDisplayAMessageWhenUserEntersNonIntegerInputs() {
        String userChoice = "list";
        final ByteArrayInputStream inContent = new ByteArrayInputStream(userChoice.getBytes());
        System.setIn(inContent);

        ArrayList<String> menu = new ArrayList<>();
        ReadInput input = new ReadInput(new Scanner(System.in));
        MainMenu mainMenu = new MainMenu(menu);
        mainMenu.addOptions("1.List Book");
        mainMenu.addOptions("2.Exit");

        ArrayList<Book> books = new ArrayList<>();
        books.add(new Book("Da Vinci code", "DAN BROWN", 2003));
        books.add(new Book("Adventures of Sherlock Holmes", "Arthur Conan Doyle", 1892));

        WelcomeMessage welcomeMessage = new WelcomeMessage("WELCOME TO BIBLIOTECA");
        Library library = new Library(books, new ArrayList<Movie>());
        BibliotecaApplication biblioteca = new BibliotecaApplication(mainMenu, library, welcomeMessage, input);
        biblioteca.start();
        biblioteca.run();

        assertEquals("WELCOME TO BIBLIOTECA\n1.List Book\n2.Exit\nEnter choice :\nSELECT A VALID OPTION\n", outputContent.toString());
    }

    @Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();

    @Test
    public void shouldExitTheApplicationWhenUserSelectsExitOption() {
        String userChoice = "E";
        final ByteArrayInputStream inContent = new ByteArrayInputStream(userChoice.getBytes());
        System.setIn(inContent);

        ArrayList<String> menu = new ArrayList<>();
        ReadInput input = new ReadInput(new Scanner(System.in));
        MainMenu mainMenu = new MainMenu(menu);
        mainMenu.addOptions("1.List Book");
        mainMenu.addOptions("2.Checkout a Book");
        mainMenu.addOptions("3.Exit");

        ArrayList<Book> books = new ArrayList<>();
        books.add(new Book("Da Vinci code", "DAN BROWN", 2003));
        books.add(new Book("Adventures of Sherlock Holmes", "Arthur Conan Doyle", 1892));

        WelcomeMessage welcomeMessage = new WelcomeMessage("WELCOME TO BIBLIOTECA");
        Library library = new Library(books, new ArrayList<Movie>());

        BibliotecaApplication biblioteca = new BibliotecaApplication(mainMenu, library, welcomeMessage, input);

        exit.expectSystemExitWithStatus(0);
        biblioteca.start();
        biblioteca.run();
    }

    @Test
    public void shouldCheckoutABook() {
        String BookChoice = "Da Vinci code";
        final ByteArrayInputStream inContent = new ByteArrayInputStream(BookChoice.getBytes());
        System.setIn(inContent);

        ArrayList<String> menu = new ArrayList<>();

        MainMenu mainMenu = new MainMenu(menu);
        mainMenu.addOptions("1.List Book");
        mainMenu.addOptions("2.Checkout a Book");
        mainMenu.addOptions("3.Exit");

        ArrayList<Book> books = new ArrayList<>();
        books.add(new Book("Da Vinci code", "DAN BROWN", 2003));
        books.add(new Book("Adventures of Sherlock Holmes", "Arthur Conan Doyle", 1892));

        WelcomeMessage welcomeMessage = new WelcomeMessage("WELCOME TO BIBLIOTECA");
        Library library = new Library(books, new ArrayList<Movie>());
        ReadInput input = mock(ReadInput.class);
        when(input.read()).thenReturn("2");

        BibliotecaApplication biblioteca = new BibliotecaApplication(mainMenu, library, welcomeMessage, input);
        biblioteca.start();
        biblioteca.run();

        assertEquals("WELCOME TO BIBLIOTECA\n1.List Book\n2.Checkout a Book\n3.Exit\nEnter choice :\nENTER BOOKNAME:\nThank you! Enjoy the book\n", outputContent.toString());
    }

    @Test
    public void shouldNotifyWhenBookIsNotAvailable() {
        String BookChoice = "Revolution 2020";
        final ByteArrayInputStream inContent = new ByteArrayInputStream(BookChoice.getBytes());
        System.setIn(inContent);

        ArrayList<String> menu = new ArrayList<>();
        ReadInput input = mock(ReadInput.class);
        when(input.read()).thenReturn("2");

        MainMenu mainMenu = new MainMenu(menu);
        mainMenu.addOptions("1.List Book");
        mainMenu.addOptions("2.Checkout a Book");
        mainMenu.addOptions("3.Exit");

        ArrayList<Book> books = new ArrayList<>();
        books.add(new Book("Da Vinci code", "DAN BROWN", 2003));
        books.add(new Book("Adventures of Sherlock Holmes", "Arthur Conan Doyle", 1892));

        WelcomeMessage welcomeMessage = new WelcomeMessage("WELCOME TO BIBLIOTECA");
        Library library = new Library(books, new ArrayList<Movie>());

        BibliotecaApplication biblioteca = new BibliotecaApplication(mainMenu, library, welcomeMessage, input);
        biblioteca.start();
        biblioteca.run();

        assertEquals("WELCOME TO BIBLIOTECA\n1.List Book\n2.Checkout a Book\n3.Exit\nEnter choice :\nENTER BOOKNAME:\nThat book is not available\n", outputContent.toString());
    }

    @Test
    public void shouldSuccessfullyReturnABookBack() {
        String bookName = "Five Point Someone";
        final ByteArrayInputStream inContent = new ByteArrayInputStream(bookName.getBytes());
        System.setIn(inContent);

        ArrayList<String> menu = new ArrayList<>();
        MainMenu mainMenu = new MainMenu(menu);
        mainMenu.addOptions("1.List Books");
        mainMenu.addOptions("2.Checkout a Book");
        mainMenu.addOptions("3.Return a Book");
        mainMenu.addOptions("4.Quit");

        ArrayList<Book> books = new ArrayList<>();
        books.add(new Book("Five Point Someone", "Chetan Bhagat", 2004));
        books.add(new Book("One Night At the Call Center", "Chetan Bhagat", 2005));
        books.add(new Book("Revolution 2020", "Chetan Bhagat", 2011));

        WelcomeMessage welcomeMessage = new WelcomeMessage("WELCOME TO BIBLIOTECA");
        Library library = new Library(books, new ArrayList<Movie>());
        library.checkoutABook("Five Point Someone");
        ReadInput input = mock(ReadInput.class);
        when(input.read()).thenReturn("3");

        BibliotecaApplication biblioteca = new BibliotecaApplication(mainMenu, library, welcomeMessage, input);
        biblioteca.start();
        biblioteca.run();

        assertEquals("WELCOME TO BIBLIOTECA\n1.List Books\n2.Checkout a Book\n3.Return a Book\n4.Quit\nEnter choice :\nENTER BOOK TO BE RETURNED:\nThank you for returning the book\n", outputContent.toString());
    }

    @Test
    public void shouldPrintMessageWhenDisplayIsInvoked() {

        ArrayList<String> menu = new ArrayList<>();
        MainMenu mainMenu = new MainMenu(menu);
        ArrayList<Book> books = new ArrayList<>();
        Library library = new Library(books, new ArrayList<Movie>());
        WelcomeMessage welcomeMessage = new WelcomeMessage("WELCOME TO BIBLIOTECA");
        BibliotecaApplication bibliotecaApplication = new BibliotecaApplication(mainMenu, library, welcomeMessage, new ReadInput(new Scanner(System.in)));

        bibliotecaApplication.display("Enter choice :");

        assertEquals("Enter choice :\n", outputContent.toString());
    }

    @Test
    public void shouldDisplayTheListOfMoviesWhenUserInputsFour() {
        String userChoice = "4";
        final ByteArrayInputStream inContent = new ByteArrayInputStream(userChoice.getBytes());
        System.setIn(inContent);

        ArrayList<String> menu = new ArrayList<>();
        ReadInput input = new ReadInput(new Scanner(System.in));
        MainMenu mainMenu = new MainMenu(menu);
        mainMenu.addOptions("1.List Book");
        mainMenu.addOptions("2.List Movies");

        ArrayList<Movie> movies = new ArrayList<>();
        movies.add(new Movie("The Boy in the Striped pyjamas", "Mark Herman", 2008, 7.8f));

        Library library = new Library(new ArrayList<Book>(), movies);
        WelcomeMessage welcomeMessage = new WelcomeMessage("WELCOME TO BIBLIOTECA");
        BibliotecaApplication biblioteca = new BibliotecaApplication(mainMenu, library, welcomeMessage, input);
        biblioteca.start();
        biblioteca.run();

        assertEquals("WELCOME TO BIBLIOTECA\n1.List Book\n2.List Movies\nEnter choice :\n" + "---------------------------------------------------------------------------------------------------\n" + String.format("%-40S%-25S%-25S%-25S", "MOVIE", "DIRECTOR", "YEAR", "RATING") + "\n---------------------------------------------------------------------------------------------------" + "\n" + String.format("%-40S%-25S%-25S%-25S", "THE BOY IN THE STRIPED PYJAMAS", "MARK HERMAN", 2008, 7.8) + "\n", outputContent.toString());
    }

    @Test
    public void shouldCheckoutAMovie() {
        String BookChoice = "The boy in the striped pyjamas";
        final ByteArrayInputStream inContent = new ByteArrayInputStream(BookChoice.getBytes());
        System.setIn(inContent);

        ArrayList<String> menu = new ArrayList<>();

        MainMenu mainMenu = new MainMenu(menu);
        mainMenu.addOptions("1.List Book");
        mainMenu.addOptions("6.Checkout a Movie");
        mainMenu.addOptions("3.Exit");

        ArrayList<Movie> movies = new ArrayList<>();
        movies.add(new Movie("The Boy in the Striped pyjamas", "Mark Herman", 2008, 7.8f));

        WelcomeMessage welcomeMessage = new WelcomeMessage("WELCOME TO BIBLIOTECA");
        Library library = new Library(new ArrayList<Book>(), movies);
        ReadInput input = mock(ReadInput.class);
        when(input.read()).thenReturn("5");

        BibliotecaApplication biblioteca = new BibliotecaApplication(mainMenu, library, welcomeMessage, input);
        biblioteca.start();
        biblioteca.run();

        assertEquals("WELCOME TO BIBLIOTECA\n1.List Book\n6.Checkout a Movie\n3.Exit\nEnter choice :\nENTER MOVIE NAME:\nThank you! Enjoy the movie\n", outputContent.toString());
    }

    @Test
    public void shouldNotifyWhenMovieIsNotAvailable() {
        String BookChoice = "V for Vendetta";
        final ByteArrayInputStream inContent = new ByteArrayInputStream(BookChoice.getBytes());
        System.setIn(inContent);

        ArrayList<String> menu = new ArrayList<>();

        MainMenu mainMenu = new MainMenu(menu);
        mainMenu.addOptions("1.List Book");
        mainMenu.addOptions("6.Checkout a Movie");
        mainMenu.addOptions("3.Exit");

        ArrayList<Movie> movies = new ArrayList<>();
        movies.add(new Movie("The Boy in the Striped pyjamas", "Mark Herman", 2008, 7.8f));

        WelcomeMessage welcomeMessage = new WelcomeMessage("WELCOME TO BIBLIOTECA");
        Library library = new Library(new ArrayList<Book>(), movies);
        ReadInput input = mock(ReadInput.class);
        when(input.read()).thenReturn("5");

        BibliotecaApplication biblioteca = new BibliotecaApplication(mainMenu, library, welcomeMessage, input);
        biblioteca.start();
        biblioteca.run();

        assertEquals("WELCOME TO BIBLIOTECA\n1.List Book\n6.Checkout a Movie\n3.Exit\nEnter choice :\nENTER MOVIE NAME:\nThat movie is not available\n", outputContent.toString());
    }
}