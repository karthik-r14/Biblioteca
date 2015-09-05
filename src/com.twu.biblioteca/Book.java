//A book has a name, author name and the year it is published which can be displayed.
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

    public void  displayBook() {
        System.out.println(bookName+"  "+authorName+"  "+yearOfPublish);
    }
}
