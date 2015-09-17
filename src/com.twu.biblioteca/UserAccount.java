//A user account has name,password and role.
package com.twu.biblioteca;

public class UserAccount {
    private String userName;
    private String password;
    private String role;
    private String name;
    private String emailAddress;
    private String phoneNumber;


    public UserAccount(String userName, String password, String role, String name, String emailAddress, String phoneNumber) {
        this.userName = userName;
        this.password = password;
        this.role = role.toUpperCase();
        this.name = name.toUpperCase();
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
    }

    public void displayInfo(int displayType) {

        switch (displayType) {


            case 1:
                System.out.println("USER NAME :" + userName);
                System.out.println("NAME :" + name);
                System.out.println("EMAIL ADDRESS :" + emailAddress);
                System.out.println("PHONE NUMBER :" + phoneNumber);
                System.out.println("ROLE :" + role);
                break;

            case 2:
                System.out.println("USER NAME :" + userName);
                System.out.println("NAME :" + name);
                System.out.println("ROLE :" + role);
        }

    }

    public boolean compareUser(String thatuserName, String thatPassword) {
        if (userName.equals(thatuserName) && password.equals(thatPassword))
            return true;
        else
            return false;
    }

    public String getRole() {
        return role;
    }
}