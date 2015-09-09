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

public class BibliotecaApplicationTest {

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
        ReadInput input = new ReadInput();
        MainMenu mainMenu = new MainMenu(menu, input);
        mainMenu.addOptions("1.List Book");
        mainMenu.addOptions("2.Exit");

        ArrayList<Book> books = new ArrayList<>();
        books.add(new Book("Da Vinci code", "DAN BROWN", 2003));
        books.add(new Book("Adventures of Sherlock Holmes", "Arthur Conan Doyle", 1892));

        Library library = new Library(books);
        WelcomeMessage welcomeMessage = new WelcomeMessage("WELCOME TO BIBLIOTECA");
        BibliotecaApplication biblioteca = new BibliotecaApplication(mainMenu, library, welcomeMessage);
        biblioteca.start();
        biblioteca.run();

        assertEquals("WELCOME TO BIBLIOTECA\n1.List Book\n2.Exit\nEnter choice :\nDA VINCI CODE  DAN BROWN  2003\nADVENTURES OF SHERLOCK HOLMES  ARTHUR CONAN DOYLE  1892\n", outputContent.toString());
    }

    @Test
    public void shouldDisplayWelcomeMessageOnStart() {
        ArrayList<String> menu = new ArrayList<>();
        ReadInput input = new ReadInput();
        MainMenu mainMenu = new MainMenu(menu, input);
        mainMenu.addOptions("1.List Book");
        mainMenu.addOptions("2.Exit");

        ArrayList<Book> books = new ArrayList<>();
        books.add(new Book("Da Vinci code", "DAN BROWN", 2003));
        books.add(new Book("Adventures of Sherlock Holmes", "Arthur Conan Doyle", 1892));

        WelcomeMessage welcomeMessage = new WelcomeMessage("WELCOME TO BIBLIOTECA");
        Library library = new Library(books);
        BibliotecaApplication biblioteca = new BibliotecaApplication(mainMenu, library, welcomeMessage);
        biblioteca.start();

        assertEquals("WELCOME TO BIBLIOTECA\n", outputContent.toString());

    }

    @Test
    public void shouldDisplayAMessageWhenUserEntersInvalidOption() {
        String userChoice = "8";
        final ByteArrayInputStream inContent = new ByteArrayInputStream(userChoice.getBytes());
        System.setIn(inContent);

        ArrayList<String> menu = new ArrayList<>();
        ReadInput input = new ReadInput();
        MainMenu mainMenu = new MainMenu(menu, input);
        mainMenu.addOptions("1.List Book");
        mainMenu.addOptions("2.Exit");

        ArrayList<Book> books = new ArrayList<>();
        books.add(new Book("Da Vinci code", "DAN BROWN", 2003));
        books.add(new Book("Adventures of Sherlock Holmes", "Arthur Conan Doyle", 1892));

        WelcomeMessage welcomeMessage = new WelcomeMessage("WELCOME TO BIBLIOTECA");
        Library library = new Library(books);
        BibliotecaApplication biblioteca = new BibliotecaApplication(mainMenu, library, welcomeMessage);
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
        ReadInput input = new ReadInput();
        MainMenu mainMenu = new MainMenu(menu, input);
        mainMenu.addOptions("1.List Book");
        mainMenu.addOptions("2.Exit");

        ArrayList<Book> books = new ArrayList<>();
        books.add(new Book("Da Vinci code", "DAN BROWN", 2003));
        books.add(new Book("Adventures of Sherlock Holmes", "Arthur Conan Doyle", 1892));

        WelcomeMessage welcomeMessage = new WelcomeMessage("WELCOME TO BIBLIOTECA");
        Library library = new Library(books);
        BibliotecaApplication biblioteca = new BibliotecaApplication(mainMenu, library, welcomeMessage);
        biblioteca.start();
        biblioteca.run();

        assertEquals("WELCOME TO BIBLIOTECA\n1.List Book\n2.Exit\nEnter choice :\nSELECT A VALID OPTION\n", outputContent.toString());
    }

    @Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();

    @Test
    public void shouldExitTheApplicationWhenUserSelectsExitOption() {
        String userChoice = "4";
        final ByteArrayInputStream inContent = new ByteArrayInputStream(userChoice.getBytes());
        System.setIn(inContent);

        ArrayList<String> menu = new ArrayList<>();
        ReadInput input = new ReadInput();
        MainMenu mainMenu = new MainMenu(menu, input);
        mainMenu.addOptions("1.List Book");
        mainMenu.addOptions("2.Checkout a Book");
        mainMenu.addOptions("3.Exit");

        ArrayList<Book> books = new ArrayList<>();
        books.add(new Book("Da Vinci code", "DAN BROWN", 2003));
        books.add(new Book("Adventures of Sherlock Holmes", "Arthur Conan Doyle", 1892));

        WelcomeMessage welcomeMessage = new WelcomeMessage("WELCOME TO BIBLIOTECA");
        Library library = new Library(books);

        BibliotecaApplication biblioteca = new BibliotecaApplication(mainMenu, library, welcomeMessage);

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
        ReadInput input = mock(ReadInput.class);
        when(input.read("Enter choice :")).thenReturn("2");

        MainMenu mainMenu = new MainMenu(menu, input);
        mainMenu.addOptions("1.List Book");
        mainMenu.addOptions("2.Checkout a Book");
        mainMenu.addOptions("3.Exit");

        ArrayList<Book> books = new ArrayList<>();
        books.add(new Book("Da Vinci code", "DAN BROWN", 2003));
        books.add(new Book("Adventures of Sherlock Holmes", "Arthur Conan Doyle", 1892));

        WelcomeMessage welcomeMessage = new WelcomeMessage("WELCOME TO BIBLIOTECA");
        Library library = new Library(books);

        BibliotecaApplication biblioteca = new BibliotecaApplication(mainMenu, library, welcomeMessage);
        biblioteca.start();
        biblioteca.run();

        assertEquals("WELCOME TO BIBLIOTECA\n1.List Book\n2.Checkout a Book\n3.Exit\nENTER BOOKNAME:\nThank you! Enjoy the book\n", outputContent.toString());
    }

    @Test
    public void shouldNotifyWhenBookIsNotAvailable() {
        String BookChoice = "Revolution 2020";
        final ByteArrayInputStream inContent = new ByteArrayInputStream(BookChoice.getBytes());
        System.setIn(inContent);

        ArrayList<String> menu = new ArrayList<>();
        ReadInput input = mock(ReadInput.class);
        when(input.read("Enter choice :")).thenReturn("2");

        MainMenu mainMenu = new MainMenu(menu, input);
        mainMenu.addOptions("1.List Book");
        mainMenu.addOptions("2.Checkout a Book");
        mainMenu.addOptions("3.Exit");

        ArrayList<Book> books = new ArrayList<>();
        books.add(new Book("Da Vinci code", "DAN BROWN", 2003));
        books.add(new Book("Adventures of Sherlock Holmes", "Arthur Conan Doyle", 1892));

        WelcomeMessage welcomeMessage = new WelcomeMessage("WELCOME TO BIBLIOTECA");
        Library library = new Library(books);

        BibliotecaApplication biblioteca = new BibliotecaApplication(mainMenu, library, welcomeMessage);
        biblioteca.start();
        biblioteca.run();

        assertEquals("WELCOME TO BIBLIOTECA\n1.List Book\n2.Checkout a Book\n3.Exit\nENTER BOOKNAME:\nThat book is not available\n", outputContent.toString());
    }

    @Test
    public void shouldSuccessfullyReturnABookBack() {
        String bookName = "Five Point Someone";
        final ByteArrayInputStream inContent = new ByteArrayInputStream(bookName.getBytes());
        System.setIn(inContent);

        ArrayList<String> menu = new ArrayList<>();
        ReadInput input = mock(ReadInput.class);
        when(input.read("Enter choice :")).thenReturn("3");

        MainMenu mainMenu = new MainMenu(menu,input);
        mainMenu.addOptions("1.List Books");
        mainMenu.addOptions("2.Checkout a Book");
        mainMenu.addOptions("3.Return a Book");
        mainMenu.addOptions("4.Quit");

        ArrayList<Book> books = new ArrayList<>();
        books.add(new Book("Five Point Someone", "Chetan Bhagat", 2004));
        books.add(new Book("One Night At the Call Center", "Chetan Bhagat", 2005));
        books.add(new Book("Revolution 2020", "Chetan Bhagat", 2011));

        WelcomeMessage welcomeMessage = new WelcomeMessage("WELCOME TO BIBLIOTECA");
        Library library = new Library(books);
        library.checkoutABook("Five Point Someone");

        BibliotecaApplication biblioteca = new BibliotecaApplication(mainMenu, library, welcomeMessage);
        biblioteca.start();
        biblioteca.run();

        assertEquals("WELCOME TO BIBLIOTECA\n1.List Books\n2.Checkout a Book\n3.Return a Book\n4.Quit\nENTER BOOK TO BE RETURNED:\nThank you for returning the book\n", outputContent.toString());
    }
}