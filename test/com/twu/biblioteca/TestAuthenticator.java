package com.twu.biblioteca;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestAuthenticator {

    @Test
    public void shouldValidateUserCredentials() {

        ReadInput input = mock(ReadInput.class);
        when(input.read()).thenReturn("kar-thik", "abcdef");

        ArrayList<UserAccount> users = new ArrayList<>();
        users.add(new UserAccount("kar-thik", "abcdef"));
        users.add(new UserAccount("car-tick", "ghijk"));
        Authenticator authenticator = new Authenticator(input, users);
        authenticator.takeCredentials();

        assertEquals(true, authenticator.validate());
    }
}