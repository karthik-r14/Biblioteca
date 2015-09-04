package com.twu.biblioteca;


public class WelcomeMessage {
    private String welcomeMessage;

    public WelcomeMessage(String welcomeMessage) {

        this.welcomeMessage = welcomeMessage;
    }

    public void display() {

        System.out.println(welcomeMessage);
    }
}
