package com.twu.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

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

        MainMenu menu = new MainMenu();
        menu.addOptions("1.List Book");
        menu.addOptions("2.Exit");

        Library library = new Library();
        library.addABook(new Book("Da Vinci code", "DAN BROWN", 2003));
        library.addABook(new Book("Adventures of Sherlock Holmes", "Arthur Conan Doyle", 1892));

        BibliotecaApplication biblioteca = new BibliotecaApplication(menu, library);
        biblioteca.start(true);

        assertEquals("WELCOME TO BIBLIOTECA\n1.List Book\n2.Exit\nEnter choice :\nDA VINCI CODE  DAN BROWN  2003\nADVENTURES OF SHERLOCK HOLMES  ARTHUR CONAN DOYLE  1892\n", outputContent.toString());
    }

    @Test
    public void shouldDisplayAMessageWhenUserEntersInvalidOption() {
        String userChoice = "8";
        final ByteArrayInputStream inContent = new ByteArrayInputStream(userChoice.getBytes());
        System.setIn(inContent);

        MainMenu menu = new MainMenu();
        menu.addOptions("1.List Book");
        menu.addOptions("2.Exit");

        Library library = new Library();
        library.addABook(new Book("Da Vinci code", "DAN BROWN", 2003));
        library.addABook(new Book("Adventures of Sherlock Holmes", "Arthur Conan Doyle", 1892));

        BibliotecaApplication biblioteca = new BibliotecaApplication(menu, library);

        biblioteca.start(true);

        assertEquals("WELCOME TO BIBLIOTECA\n1.List Book\n2.Exit\nEnter choice :\nSELECT A VALID OPTION\n", outputContent.toString());
    }

    @Test
    public void shouldDisplayAMessageWhenUserEntersNonIntegerInputs() {

        String userChoice = "list";
        final ByteArrayInputStream inContent = new ByteArrayInputStream(userChoice.getBytes());
        System.setIn(inContent);

        MainMenu menu = new MainMenu();
        menu.addOptions("1.List Book");
        menu.addOptions("2.Exit");

        Library library = new Library();
        library.addABook(new Book("Da Vinci code", "DAN BROWN", 2003));
        library.addABook(new Book("Adventures of Sherlock Holmes", "Arthur Conan Doyle", 1892));

        BibliotecaApplication biblioteca = new BibliotecaApplication(menu, library);

        biblioteca.start(true);

        assertEquals("WELCOME TO BIBLIOTECA\n1.List Book\n2.Exit\nEnter choice :\nSELECT A VALID OPTION\n", outputContent.toString());
    }

    @Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();

    @Test
    public void shouldExitTheApplicationWhenUserSelectsExitOption() {

        String userChoice = "2";
        final ByteArrayInputStream inContent = new ByteArrayInputStream(userChoice.getBytes());
        System.setIn(inContent);

        MainMenu menu = new MainMenu();
        menu.addOptions("1.List Book");
        menu.addOptions("2.Exit");

        Library library = new Library();
        library.addABook(new Book("Da Vinci code", "DAN BROWN", 2003));
        library.addABook(new Book("Adventures of Sherlock Holmes", "Sir Arthur Conan Doyle", 1892));

        BibliotecaApplication biblioteca = new BibliotecaApplication(menu, library);

        exit.expectSystemExitWithStatus(0);
        biblioteca.start(true);
        assertEquals("WELCOME TO BIBLIOTECA\n1.List Book\n2.Exit\nEnter choice :\n", outputContent.toString());
    }
}

