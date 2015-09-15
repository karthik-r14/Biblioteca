package com.twu.biblioteca;

public class UserAccount {
    private String userName;
    private String password;

    public UserAccount(String userName,String password) {
        this.userName = userName;
        this.password = password;
    }

    public void displayInfo() {
        System.out.println("USER NAME :" + userName);
    }

    public boolean compareUser(String thatuserName, String thatPassword) {
        return userName.equals(thatuserName) && password.equals(thatPassword);
    }
}

