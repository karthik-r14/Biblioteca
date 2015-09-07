package com.twu.biblioteca;

import org.junit.Test;

import java.io.ByteArrayInputStream;

import static org.junit.Assert.assertEquals;

public class TestReadInput {

    @Test
    public void shouldReturnChoiceInputByTheUser() {
        ReadInput senseInput = new ReadInput();
        ByteArrayInputStream inputStream = new ByteArrayInputStream("1".getBytes());

        System.setIn(inputStream);
        String input= senseInput.read();
        System.setIn(System.in);

        assertEquals("1", input);
    }
}