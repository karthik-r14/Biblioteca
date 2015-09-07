package com.twu.biblioteca;

import org.junit.Test;

import java.io.ByteArrayInputStream;

import static org.junit.Assert.assertEquals;

public class TestReadInput {

    @Test
    public void shouldReturnChoiceInputByTheUser() {
        ReadInput senseinput = new ReadInput();

         senseinput.read();

        assertEquals("1", senseinput.toString());
    }
}
