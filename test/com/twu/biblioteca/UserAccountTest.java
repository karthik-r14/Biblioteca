package com.twu.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class UserAccountTest {

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

        UserAccount user = new UserAccount("karthik_r14", "abc-defg", "user", "karthik", "kar@gmail.com ", "9880443410");
        user.displayInfo(1);

        assertEquals("USER NAME :" + "karthik_r14\nNAME :KARTHIK\nEMAIL ADDRESS :kar@gmail.com \nPHONE NUMBER :9880443410" + "\nROLE :" + "USER\n", outputContent.toString());
    }

    @Test
    public void shouldReturnTrueOnSameCredentials() {

        UserAccount user = new UserAccount("karthik_r14", "abc-defg", "user", "karthik", "kar@gmail.com ", "9880443410");
        assertEquals(true, user.compareUser("karthik_r14", "abc-defg"));
    }

    @Test
    public void shouldReturnFalseOnDifferentCredentials() {

        UserAccount user = new UserAccount("karthik_r14", "abc-defg", "user", "karthik", "kar@gmail.com ", "9880443410");
        assertEquals(false, user.compareUser("karthik1408", "cdefgk"));
    }

    @Test
    public void shouldReturnRoleOfUserAccountAsUser() {

        UserAccount user = new UserAccount("karthik_r14", "abc-defg", "user", "karthik", "kar@gmail.com ", "9880443410");
        assertEquals("USER", user.getRole());
    }

    @Test
    public void shouldReturnRoleOfUserAccountAsLibrarian() {

        UserAccount user = new UserAccount("karthik_r14", "abc-defg", "librarian", "karthik", "kar@gmail.com ", "9880443410");
        assertEquals("LIBRARIAN", user.getRole());
    }

    @Test
    public void shouldReturnRoleOfUserAccountAsDefault() {

        UserAccount user = new UserAccount("karthik_r14", "abc-defg", "DEFAULT", "karthik", "kar@gmail.com ", "9880443410");
        assertEquals("DEFAULT", user.getRole());
    }
}
