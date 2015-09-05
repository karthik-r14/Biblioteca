package com.twu.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class TestBook {

    @Test
    public void shouldDisplayBookInformation() {
        Book book = new Book("The 3 mistakes of my Life", "Chetan Bhagat", 2008);

        assertEquals("THE 3 MISTAKES OF MY LIFE  CHETAN BHAGAT  2008", book.toString());
    }
}
