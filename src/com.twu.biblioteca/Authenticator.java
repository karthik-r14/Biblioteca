//An authenticator takes in credentials and validates it.
package com.twu.biblioteca;

import java.util.ArrayList;

public class Authenticator {

    private ReadInput input;
    private String userId;
    private String password;
    private ArrayList<UserAccount> userAccounts;

    public Authenticator(ReadInput input, ArrayList<UserAccount> userAccounts) {
        this.input = input;
        this.userAccounts = userAccounts;
    }

    public void takeCredentials() {
        display("Enter User Id :");
        userId = input.read();
        display("Enter password :");
        password = input.read();
    }

    void display(String message) {
        System.out.println(message);
    }

    public UserAccount validate() {
        for (UserAccount user : userAccounts) {
            if(user.compareUser(userId, password))
                return user;
        }
        return null;
    }
}