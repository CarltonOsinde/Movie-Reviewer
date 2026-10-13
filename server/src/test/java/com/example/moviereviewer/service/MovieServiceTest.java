package com.example.moviereviewer.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.moviereviewer.exception.MovieNotFoundException;
import com.example.moviereviewer.model.Movie;
import com.example.moviereviewer.repository.MovieRepository;

class MovieServiceTest {

    private MovieRepository movieRepository;
    private MovieService movieService;

    @BeforeEach
    void setUp() {
        movieRepository = mock(MovieRepository.class);
        movieService = new MovieService(movieRepository);
    }

    @Test
    void returnsMovieByImdbId() {
        Movie movie = new Movie();
        movie.setImdbId("tt0000001");
        when(movieRepository.findMovieByImdbId("tt0000001")).thenReturn(Optional.of(movie));

        assertEquals("tt0000001", movieService.movieByImdbId("tt0000001").getImdbId());
    }

    @Test
    void throwsWhenMovieMissing() {
        when(movieRepository.findMovieByImdbId("tt9999999")).thenReturn(Optional.empty());

        assertThrows(MovieNotFoundException.class, () -> movieService.movieByImdbId("tt9999999"));
    }
}
