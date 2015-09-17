package com.twu.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestAuthenticator {

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

        Authenticator authenticator = new Authenticator(new ReadInput(new Scanner(System.in)), new ArrayList<UserAccount>());

        authenticator.display("Enter user id:");
        assertEquals("Enter user id:\n", outputContent.toString());
    }

    @Test
    public void shouldValidateUserCredentials() {

        ReadInput input = mock(ReadInput.class);
        when(input.read()).thenReturn("kar-thik", "abcdef");

        ArrayList<UserAccount> users = new ArrayList<>();
        users.add(new UserAccount("kar-thik", "abcdef", "user"));
        users.add(new UserAccount("car-tick", "ghijk", "user"));
        Authenticator authenticator = new Authenticator(input, users);
        authenticator.takeCredentials();

        assertEquals("USER", authenticator.validate().getRole());
    }

    @Test
    public void shouldReturnDefaultUserOnInValidUserCredentials() {

        ReadInput input = mock(ReadInput.class);
        when(input.read()).thenReturn("Car-t", "abcdef");

        ArrayList<UserAccount> users = new ArrayList<>();
        users.add(new UserAccount("kar-thik", "abcdef", "user"));
        users.add(new UserAccount("car-tick", "ghijk", "user"));
        Authenticator authenticator = new Authenticator(input, users);
        authenticator.takeCredentials();

        assertEquals("DEFAULT", authenticator.validate().getRole());
    }
}