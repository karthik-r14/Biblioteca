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

        Library library = new Library(books, new ArrayList<Movie>(), new ArrayList<UserAccount>());
        WelcomeMessage welcomeMessage = new WelcomeMessage("WELCOME TO BIBLIOTECA");
        BibliotecaApplication biblioteca = new BibliotecaApplication(mainMenu, library, welcomeMessage, input, new UserMenu(new ArrayList<String>()), new LibrarianMenu(new UserMenu(new ArrayList<String>())));
        biblioteca.start();
        biblioteca.run(new UserAccount("", " ", "DEFAULT", " ", " ", " "));

        assertEquals("WELCOME TO BIBLIOTECA\n\n\n\n1.List Book\n2.Exit\nEnter choice :\n" + "------------------------------------------------------------------------------------\n" + String.format("%-40S%-40S%-40S", "TITLE", "AUTHOR", "YEAR") + "\n------------------------------------------------------------------------------------\n" + String.format("%-40S%-40S%-40S", "DA VINCI CODE", "DAN BROWN", 2003) + "\n" + String.format("%-40S%-40S%-40S", "ADVENTURES OF SHERLOCK HOLMES", "ARTHUR CONAN DOYLE", 1892) + "\n", outputContent.toString());
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
        Library library = new Library(books, new ArrayList<Movie>(), new ArrayList<UserAccount>());
        BibliotecaApplication biblioteca = new BibliotecaApplication(mainMenu, library, welcomeMessage, input, new UserMenu(new ArrayList<String>()), new LibrarianMenu(new UserMenu(new ArrayList<String>())));
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
        Library library = new Library(books, new ArrayList<Movie>(), new ArrayList<UserAccount>());
        BibliotecaApplication biblioteca = new BibliotecaApplication(mainMenu, library, welcomeMessage, input, new UserMenu(new ArrayList<String>()), new LibrarianMenu(new UserMenu(new ArrayList<String>())));
        biblioteca.start();
        biblioteca.run(new UserAccount("", " ", "DEFAULT", " ", " ", " "));

        assertEquals("WELCOME TO BIBLIOTECA\n\n\n\n1.List Book\n2.Exit\nEnter choice :\nSELECT A VALID OPTION\n", outputContent.toString());
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
        Library library = new Library(books, new ArrayList<Movie>(), new ArrayList<UserAccount>());
        BibliotecaApplication biblioteca = new BibliotecaApplication(mainMenu, library, welcomeMessage, input, new UserMenu(new ArrayList<String>()), new LibrarianMenu(new UserMenu(new ArrayList<String>())));
        biblioteca.start();
        biblioteca.run(new UserAccount("", " ", "DEFAULT", " ", " ", " "));

        assertEquals("WELCOME TO BIBLIOTECA\n\n\n\n1.List Book\n2.Exit\nEnter choice :\nSELECT A VALID OPTION\n", outputContent.toString());
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
        Library library = new Library(books, new ArrayList<Movie>(), new ArrayList<UserAccount>());

        BibliotecaApplication biblioteca = new BibliotecaApplication(mainMenu, library, welcomeMessage, input, new UserMenu(new ArrayList<String>()), new LibrarianMenu(new UserMenu(new ArrayList<String>())));

        exit.expectSystemExitWithStatus(0);
        biblioteca.start();
        biblioteca.run(new UserAccount("", " ", "DEFAULT", " ", " ", " "));
    }

    @Test
    public void shouldPrintMessageWhenDisplayIsInvoked() {

        ArrayList<String> menu = new ArrayList<>();
        MainMenu mainMenu = new MainMenu(menu);
        ArrayList<Book> books = new ArrayList<>();
        Library library = new Library(books, new ArrayList<Movie>(), new ArrayList<UserAccount>());
        WelcomeMessage welcomeMessage = new WelcomeMessage("WELCOME TO BIBLIOTECA");
        BibliotecaApplication bibliotecaApplication = new BibliotecaApplication(mainMenu, library, welcomeMessage, new ReadInput(new Scanner(System.in)), new UserMenu(new ArrayList<String>()), new LibrarianMenu(new UserMenu(new ArrayList<String>())));

        bibliotecaApplication.display("Enter choice :");

        assertEquals("Enter choice :\n", outputContent.toString());
    }

    @Test
    public void shouldDisplayTheListOfMoviesWhenUserInputsTwo() {
        String userChoice = "2";
        final ByteArrayInputStream inContent = new ByteArrayInputStream(userChoice.getBytes());
        System.setIn(inContent);

        ArrayList<String> menu = new ArrayList<>();
        ReadInput input = new ReadInput(new Scanner(System.in));
        MainMenu mainMenu = new MainMenu(menu);
        mainMenu.addOptions("1.List Book");
        mainMenu.addOptions("2.List Movies");

        ArrayList<Movie> movies = new ArrayList<>();
        movies.add(new Movie("The Boy in the Striped pyjamas", "Mark Herman", 2008, 7.8f));

        Library library = new Library(new ArrayList<Book>(), movies, new ArrayList<UserAccount>());
        WelcomeMessage welcomeMessage = new WelcomeMessage("WELCOME TO BIBLIOTECA");
        BibliotecaApplication biblioteca = new BibliotecaApplication(mainMenu, library, welcomeMessage, input, new UserMenu(new ArrayList<String>()), new LibrarianMenu(new UserMenu(new ArrayList<String>())));
        biblioteca.start();
        biblioteca.run(new UserAccount("", " ", "DEFAULT", " ", " ", " "));

        assertEquals("WELCOME TO BIBLIOTECA\n\n\n\n1.List Book\n2.List Movies\nEnter choice :\n" + "---------------------------------------------------------------------------------------------------\n" + String.format("%-40S%-25S%-25S%-25S", "MOVIE", "DIRECTOR", "YEAR", "RATING") + "\n---------------------------------------------------------------------------------------------------" + "\n" + String.format("%-40S%-25S%-25S%-25S", "THE BOY IN THE STRIPED PYJAMAS", "MARK HERMAN", 2008, 7.8) + "\n", outputContent.toString());
    }

    @Test
    public void shouldCheckoutAMovie() {
        String BookChoice = "The boy in the striped pyjamas";
        final ByteArrayInputStream inContent = new ByteArrayInputStream(BookChoice.getBytes());
        System.setIn(inContent);

        ArrayList<String> menu = new ArrayList<>();

        MainMenu mainMenu = new MainMenu(menu);
        mainMenu.addOptions("1.List Book");
        mainMenu.addOptions("3.Checkout a Movie");
        mainMenu.addOptions("E.Exit");

        ArrayList<Movie> movies = new ArrayList<>();
        movies.add(new Movie("The Boy in the Striped pyjamas", "Mark Herman", 2008, 7.8f));

        WelcomeMessage welcomeMessage = new WelcomeMessage("WELCOME TO BIBLIOTECA");
        Library library = new Library(new ArrayList<Book>(), movies, new ArrayList<UserAccount>());
        ReadInput input = mock(ReadInput.class);
        when(input.read()).thenReturn("3");

        BibliotecaApplication biblioteca = new BibliotecaApplication(mainMenu, library, welcomeMessage, input, new UserMenu(new ArrayList<String>()), new LibrarianMenu(new UserMenu(new ArrayList<String>())));
        biblioteca.start();
        biblioteca.run(new UserAccount("", " ", "DEFAULT", " ", " ", " "));

        assertEquals("WELCOME TO BIBLIOTECA\n\n\n\n1.List Book\n3.Checkout a Movie\nE.Exit\nEnter choice :\nENTER MOVIE NAME:\nThank you! Enjoy the movie\n", outputContent.toString());
    }

    @Test
    public void shouldNotifyWhenMovieIsNotAvailable() {
        String BookChoice = "V for Vendetta";
        final ByteArrayInputStream inContent = new ByteArrayInputStream(BookChoice.getBytes());
        System.setIn(inContent);

        ArrayList<String> menu = new ArrayList<>();

        MainMenu mainMenu = new MainMenu(menu);
        mainMenu.addOptions("1.List Book");
        mainMenu.addOptions("5.Checkout a Movie");
        mainMenu.addOptions("E.Exit");

        ArrayList<Movie> movies = new ArrayList<>();
        movies.add(new Movie("The Boy in the Striped pyjamas", "Mark Herman", 2008, 7.8f));

        WelcomeMessage welcomeMessage = new WelcomeMessage("WELCOME TO BIBLIOTECA");
        Library library = new Library(new ArrayList<Book>(), movies, new ArrayList<UserAccount>());
        ReadInput input = mock(ReadInput.class);
        when(input.read()).thenReturn("3");

        BibliotecaApplication biblioteca = new BibliotecaApplication(mainMenu, library, welcomeMessage, input, new UserMenu(new ArrayList<String>()), new LibrarianMenu(new UserMenu(new ArrayList<String>())));
        biblioteca.start();
        biblioteca.run(new UserAccount("", " ", "DEFAULT", " ", " ", " "));

        assertEquals("WELCOME TO BIBLIOTECA\n\n\n\n1.List Book\n5.Checkout a Movie\nE.Exit\nEnter choice :\nENTER MOVIE NAME:\nThat movie is not available\n", outputContent.toString());
    }

    @Test
    public void shouldReturnUserOnSuccesfulLogin() {

        ArrayList<UserAccount> userAccounts = new ArrayList<>();
        userAccounts.add(new UserAccount("123-456", "abcdef", "user", "Sachin", "sachin@gmail.com", "9901089765"));
        userAccounts.add(new UserAccount("123-457", "asdfgh", "user", "Kaushal", "kaushal@gmail.com", "7760108980"));

        MainMenu mainMenu = new MainMenu(new ArrayList<String>());
        Library library = new Library(new ArrayList<Book>(), new ArrayList<Movie>(), userAccounts);

        WelcomeMessage welcomeMessage = new WelcomeMessage("WELCOME TO BIBLIOTECA");
        ReadInput input = mock(ReadInput.class);
        when(input.read()).thenReturn("4");

        String userInput = "123-456\nabcdef";
        final ByteArrayInputStream inContent = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(inContent);

        mainMenu.addOptions("List Books");
        mainMenu.addOptions("Checkout a movie");
        mainMenu.addOptions("Login");

        BibliotecaApplication biblioteca = new BibliotecaApplication(mainMenu, library, welcomeMessage, input, new UserMenu(new ArrayList<String>()), new LibrarianMenu(new UserMenu(new ArrayList<String>())));
        biblioteca.start();
        assertEquals("USER", biblioteca.run(new UserAccount("", " ", "DEFAULT", " ", " ", " ")).getRole());
    }

    @Test
    public void shouldReturnDefaultUserOnUnsuccesfulLogin() {

        ArrayList<UserAccount> userAccounts = new ArrayList<>();
        userAccounts.add(new UserAccount("123-456", "abcdef", "user", "Sachin", "sachin@gmail.com", "9901089765"));
        userAccounts.add(new UserAccount("123-457", "asdfgh", "user", "Kaushal", "kaushal@gmail.com", "7760108980"));

        MainMenu mainMenu = new MainMenu(new ArrayList<String>());
        Library library = new Library(new ArrayList<Book>(), new ArrayList<Movie>(), userAccounts);

        String userInput = "123-456\nabcf";
        final ByteArrayInputStream inContent = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(inContent);

        WelcomeMessage welcomeMessage = new WelcomeMessage("WELCOME TO BIBLIOTECA");
        ReadInput input = mock(ReadInput.class);
        when(input.read()).thenReturn("4");

        mainMenu.addOptions("List Books");
        mainMenu.addOptions("Checkout a movie");
        mainMenu.addOptions("Login");

        BibliotecaApplication biblioteca = new BibliotecaApplication(mainMenu, library, welcomeMessage, input, new UserMenu(new ArrayList<String>()), new LibrarianMenu(new UserMenu(new ArrayList<String>())));
        biblioteca.start();

        assertEquals("DEFAULT", biblioteca.run(new UserAccount("", " ", "DEFAULT", " ", " ", " ")).getRole());
    }

    @Test
    public void shouldReturnLibrarianOnSuccesfulLogin() {

        ArrayList<UserAccount> userAccounts = new ArrayList<>();
        userAccounts.add(new UserAccount("123-456", "abcdef", "LIBRARIAN", "Sachin", "sachin@gmail.com", "9901089765"));
        userAccounts.add(new UserAccount("123-457", "asdfgh", "user", "Kaushal", "kaushal@gmail.com", "7760108980"));

        MainMenu mainMenu = new MainMenu(new ArrayList<String>());
        Library library = new Library(new ArrayList<Book>(), new ArrayList<Movie>(), userAccounts);

        WelcomeMessage welcomeMessage = new WelcomeMessage("WELCOME TO BIBLIOTECA");
        ReadInput input = mock(ReadInput.class);
        when(input.read()).thenReturn("4");

        String userInput = "123-456\nabcdef";
        final ByteArrayInputStream inContent = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(inContent);

        mainMenu.addOptions("List Books");
        mainMenu.addOptions("Checkout a movie");
        mainMenu.addOptions("Login");

        BibliotecaApplication biblioteca = new BibliotecaApplication(mainMenu, library, welcomeMessage, input, new UserMenu(new ArrayList<String>()), new LibrarianMenu(new UserMenu(new ArrayList<String>())));
        biblioteca.start();
        assertEquals("LIBRARIAN", biblioteca.run(new UserAccount("", " ", "DEFAULT", " ", " ", " ")).getRole());
    }
}