package com.twu.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class BookTest {

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
    public void shouldDisplayBookInformation() {
        Book book = new Book("The 3 mistakes of my Life", "Chetan Bhagat", 2008);

        book.displayBook();

        assertEquals(String.format("%-40S%-40S%-40S", "THE 3 MISTAKES OF MY LIFE", "CHETAN BHAGAT", "2008") + "\n", outputContent.toString());
    }

    @Test
    public void shouldReturnTrueWhenBookNameMatches() {
        Book book = new Book("The 3 mistakes of my Life", "Chetan Bhagat", 2008);

        book.compareWithBookName("The 3 mistakes of my Life");

        assertEquals(true, book.compareWithBookName("The 3 mistakes of my Life"));
    }
}