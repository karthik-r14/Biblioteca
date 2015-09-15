package com.twu.biblioteca;

public class UserAccount {
    private String userName;
    private String name;
    private String password;
    private String emailId;
    private String phoneNumber;

    public UserAccount(String userName, String name, String password, String emailId, String phoneNumber) {

        this.userName = userName;
        this.name = name.toUpperCase();
        this.password = password;
        this.emailId = emailId;
        this.phoneNumber = phoneNumber;
    }

    public void displayInfo() {
        System.out.println("USER NAME :" + userName);
        System.out.println("NAME :" + name);
        System.out.println("EMAIL ID :" + emailId);
        System.out.println("PHONE NUMBER :" + phoneNumber);
    }
}

