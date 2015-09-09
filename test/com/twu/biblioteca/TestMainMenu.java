package com.twu.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;


public class TestMainMenu {

    private final ByteArrayOutputStream outputContent = new ByteArrayOutputStream();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outputContent));
    }

    @After
    public void CleanUpStreams() {
        System.setOut(System.out);
    }

    @Test
    public void shouldDisplayListBooksInMainMenu() {
        ArrayList<String> menu = new ArrayList<>();
        ReadInput input = new ReadInput();
        MainMenu mainMenu = new MainMenu(menu, input);
        mainMenu.addOptions("1.List Books");
        mainMenu.displayMenu();

        assertEquals("1.List Books\n", outputContent.toString());
    }

    @Test
    public void shouldReadInputFromUser() {
        ArrayList<String> menu = new ArrayList<>();
        ReadInput input1 = new ReadInput();
        MainMenu mainMenu = new MainMenu(menu, input1);
        ByteArrayInputStream inputStream = new ByteArrayInputStream("1".getBytes());

        System.setIn(inputStream);
        String input2 = mainMenu.takeUserInput();
        System.setIn(System.in);

        assertEquals("1", input2);
    }

    @Test
    public void shouldDisplayAllBooksInLibraryWhenUserInputsNumericOne() {
        ArrayList<String> menu = new ArrayList<>();
        ReadInput input = new ReadInput();
        MainMenu mainMenu = new MainMenu(menu, input);

        ArrayList<Book> books = new ArrayList<>();
        books.add(new Book("Five Point Someone", "Chetan Bhagat", 2004));
        books.add(new Book("One Night At the Call Center", "Chetan Bhagat", 2005));
        books.add(new Book("Revolution 2020", "Chetan Bhagat", 2011));

        Library library = new Library(books);
        mainMenu.executeOption("1", library);

        assertEquals("FIVE POINT SOMEONE  CHETAN BHAGAT  2004\nONE NIGHT AT THE CALL CENTER  CHETAN BHAGAT  2005\nREVOLUTION 2020  CHETAN BHAGAT  2011\n", outputContent.toString());
    }

    @Test
    public void shouldNotifyWhenInvalidOptionIsChosen() {
        ArrayList<String> menu = new ArrayList<>();
        ReadInput input = new ReadInput();
        MainMenu mainMenu = new MainMenu(menu, input);

        ArrayList<Book> books = new ArrayList<>();
        books.add(new Book("Five Point Someone", "Chetan Bhagat", 2004));
        books.add(new Book("One Night At the Call Center", "Chetan Bhagat", 2005));
        books.add(new Book("Revolution 2020", "Chetan Bhagat", 2011));


        Library library = new Library(books);
        mainMenu.executeOption("-1", library);
        assertEquals("SELECT A VALID OPTION\n", outputContent.toString());
    }

    @Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();

    @Test
    public void shouldValidateQuit() {
        ArrayList<String> menu = new ArrayList<>();
        ReadInput input = new ReadInput();
        MainMenu mainMenu = new MainMenu(menu, input);
        ArrayList<Book> books = new ArrayList<>();
        books.add(new Book("Five Point Someone", "Chetan Bhagat", 2004));
        books.add(new Book("One Night At the Call Center", "Chetan Bhagat", 2005));
        books.add(new Book("Revolution 2020", "Chetan Bhagat", 2011));

        Library library = new Library(books);
        mainMenu.addOptions("List Books");
        mainMenu.addOptions("Checkout a Book");
        mainMenu.addOptions("Quit");
        exit.expectSystemExit();
        mainMenu.executeOption("3", library);
    }

    @Test
    public void shouldValidateCheckOutABook() {
        String bookChoice = "One Night At the Call Center";
        final ByteArrayInputStream inContent = new ByteArrayInputStream(bookChoice.getBytes());
        System.setIn(inContent);

        ArrayList<String> menu = new ArrayList<>();
        ReadInput input = new ReadInput();
        MainMenu mainMenu = new MainMenu(menu,input);

        ArrayList<Book> books = new ArrayList<>();
        books.add(new Book("Five Point Someone", "Chetan Bhagat", 2004));
        books.add(new Book("One Night At the Call Center", "Chetan Bhagat", 2005));
        books.add(new Book("Revolution 2020", "Chetan Bhagat", 2011));

        Library library = new Library(books);
        mainMenu.addOptions("List Books");
        mainMenu.addOptions("Checkout a Book");
        mainMenu.addOptions("Quit");
        mainMenu.executeOption("2", library);

        assertEquals("ENTER BOOKNAME:\nThank you! Enjoy the book\n", outputContent.toString());
    }
}