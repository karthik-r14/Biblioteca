package com.twu.biblioteca;

import com.twu.biblioteca.Movie;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class TestMovie {

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
    public void shouldDisplayMovideDetails() {
        Movie movie = new Movie("The Boy in the Striped pyjamas", "Mark Herman", 2008, 7.8f);
        movie.displayMovie();

        assertEquals(String.format("%-40s%-25s%-25s%-25s", "The Boy in the Striped pyjamas", "Mark Herman", 2008, 7.8)+"\n", outputContent.toString());
    }
}