package com.example.moviereviewer.exception;

public class MovieNotFoundException extends RuntimeException {

    public MovieNotFoundException(String imdbId) {
        super("No movie found with imdbId " + imdbId);
    }
}
