//A user account has name,password and role.
package com.twu.biblioteca;

public class UserAccount {
    private String userName;
    private String password;
    private String role;

    public UserAccount(String userName, String password, String role) {
        this.userName = userName;
        this.password = password;
        this.role = role.toUpperCase();
    }

    public void displayInfo() {
        System.out.println("USER NAME :" + userName);
        System.out.println("ROLE :" + role);
    }

    public String compareUser(String thatuserName, String thatPassword) {
         if(userName.equals(thatuserName) && password.equals(thatPassword))
             return role;
         else
             return "ACCESS DENIED";
    }
}