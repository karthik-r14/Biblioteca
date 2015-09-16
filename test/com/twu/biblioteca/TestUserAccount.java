package com.twu.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class TestUserAccount {

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
    public void shouldDisplayUserInfo() {

        UserAccount user = new UserAccount("karthik_r14", "abc-defg", "user");
        user.displayInfo();

        assertEquals("USER NAME :" + "karthik_r14\n" + "ROLE :" + "USER\n", outputContent.toString());
    }

    @Test
    public void shouldReturnTrueOnSameCredentials() {

        UserAccount user = new UserAccount("karthik_r14", "abc-defg", "user");
        assertEquals("USER", user.compareUser("karthik_r14", "abc-defg"));
    }

    @Test
    public void shouldReturnFalseOnDifferentCredentials() {

        UserAccount user = new UserAccount("karthik_r14", "abc-defg", "user");
        assertEquals("ACCESS DENIED", user.compareUser("karthik1408", "cdefgk"));
    }
}
