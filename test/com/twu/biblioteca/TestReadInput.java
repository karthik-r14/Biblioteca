package com.twu.biblioteca;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

public class TestReadInput {

    @Test
    public void shouldReturnChoiceInputByTheUser() {
        final ByteArrayInputStream inputStream = new ByteArrayInputStream("1".getBytes());

        System.setIn(inputStream);
        ReadInput senseInput = new ReadInput(new Scanner(System.in));
        assertEquals("1", senseInput.read());
    }
}