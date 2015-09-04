package com.twu.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class TestWelcomeMessage {

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
        public void shouldDisplayWelcomeMessage()
        {
            WelcomeMessage welcomeMessage = new WelcomeMessage("Welcome to Biblioteca");

            welcomeMessage.display();

            assertEquals("Welcome to Biblioteca\n", outputContent.toString());
        }
}
