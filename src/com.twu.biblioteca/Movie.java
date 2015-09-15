package com.twu.biblioteca;

public class Movie {

    private String movieName;
    private String directorName;
    private int movieYear;
    private float movieRating;

    public Movie(String movieName, String directorName, int movieYear, float movieRating) {
        this.movieName = movieName.toUpperCase();
        this.directorName = directorName.toUpperCase();
        this.movieYear = movieYear;
        this.movieRating = movieRating;
    }

    public void displayMovie() {

        System.out.println(String.format("%-40s%-25s%-25s%-25s", movieName, directorName, movieYear, movieRating));
    }
}
