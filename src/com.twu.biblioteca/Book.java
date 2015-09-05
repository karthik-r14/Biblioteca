package com.twu.biblioteca;

public class Book {

    private String bookName;
    private String authorName;
    private int yearOfPublish;

    public Book(String bookName, String authorName, int yearOfPublish) {
        this.bookName = bookName.toUpperCase();
        this.authorName = authorName.toUpperCase();
        this.yearOfPublish = yearOfPublish;
    }

    @Override
    public String toString() {
       return bookName+"  "+authorName+"  "+yearOfPublish;
    }
}
