package com.example.moviereviewer.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.moviereviewer.exception.MovieNotFoundException;
import com.example.moviereviewer.model.Movie;
import com.example.moviereviewer.repository.MovieRepository;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movie> allMovies() {
        return movieRepository.findAll();
    }

    public Movie movieByImdbId(String imdbId) {
        return movieRepository.findMovieByImdbId(imdbId)
                .orElseThrow(() -> new MovieNotFoundException(imdbId));
    }
}
