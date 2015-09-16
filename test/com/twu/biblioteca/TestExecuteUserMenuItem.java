package com.twu.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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

        Library library = new Library(books, new ArrayList<Movie>());
        executeMenu.execute(library);

        assertEquals("------------------------------------------------------------------------------------\n" + String.format("%-40S%-40S%-40S", "TITLE", "AUTHOR", "YEAR") + "\n------------------------------------------------------------------------------------\n" + String.format("%-40S%-40S%-40S", "FIVE POINT SOMEONE", "CHETAN BHAGAT", 2004) + "\n" + String.format("%-40S%-40S%-40S", "REVOLUTION 2020", "CHETAN BHAGAT", 2011) + "\n", outputContent.toString());
    }

    @Test
    public void shouldDisplayAllMoviesWhenInputsNumericTwo() {

        ExecuteUserMenuItem executeMenu = new ExecuteUserMenuItem("2");
        ArrayList<Movie> movies = new ArrayList<>();
        movies.add(new Movie("The Boy in the Striped pyjamas", "Mark Herman", 2008, 7.8f));

        Library library = new Library(new ArrayList<Book>(), movies);
        executeMenu.execute(library);

        assertEquals("---------------------------------------------------------------------------------------------------\n" + String.format("%-40S%-25S%-25S%-25S", "MOVIE", "DIRECTOR", "YEAR", "RATING") + "\n---------------------------------------------------------------------------------------------------\n" + String.format("%-40S%-25S%-25S%-25S", "The Boy in the Striped pyjamas", "Mark Herman", 2008, 7.8) + "\n", outputContent.toString());
    }
}
