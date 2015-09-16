package com.twu.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class TestUserMenu {

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
    public void shouldDisplayMenu() {

        ArrayList<String> menu = new ArrayList<>();

        UserMenu userMenu = new UserMenu(menu);

        userMenu.addOptions("List Books");
        userMenu.addOptions("Checkout movie");
        userMenu.displayMenu();

        assertEquals("List Books\nCheckout movie\n", outputContent.toString());
    }
}
