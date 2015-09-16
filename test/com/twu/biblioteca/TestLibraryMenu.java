package com.twu.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class TestLibraryMenu {

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

        LibrarianMenu librarianMenu = new LibrarianMenu(new UserMenu(menu));
        librarianMenu.addOptions("List Books");
        librarianMenu.addOptions("List Movie");
        librarianMenu.displayMenu();

        assertEquals("List Books\nList Movie\n", outputContent.toString());
    }

}
