//An authenticator takes in credentials and validates it.
package com.twu.biblioteca;

import java.util.ArrayList;

public class Authenticator {

    private ReadInput input;
    private String userId;
    private String password;
    private ArrayList<UserAccount> userAccounts;

    public Authenticator(ReadInput input,  ArrayList<UserAccount> userAccounts) {
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

    public String validate() {

        String returnString;
        for(UserAccount user : userAccounts) {
            returnString = user.compareUser(userId, password);
                if(!("ACCESS DENIED".equals(returnString)))
                    return returnString;
        }
        return "ACCESS DENIED";
    }
}