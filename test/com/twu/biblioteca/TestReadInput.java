package com.twu.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class TestReadInput {

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
    public void shouldDisplayMessage() {
        ReadInput senseInput = new ReadInput();

        senseInput.display("Enter choice :");

        assertEquals("Enter choice :\n", outputContent.toString());
    }

    @Test
    public void shouldReturnChoiceInputByTheUser() {
        ReadInput senseInput = new ReadInput();
        ByteArrayInputStream inputStream = new ByteArrayInputStream("1".getBytes());

        System.setIn(inputStream);
        String input = senseInput.read("Enter Choice:");
        System.setIn(System.in);

        assertEquals("1", input);
    }
}