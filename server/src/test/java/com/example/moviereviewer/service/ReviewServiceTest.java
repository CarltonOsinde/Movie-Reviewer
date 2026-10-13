package com.example.moviereviewer.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.example.moviereviewer.exception.MovieNotFoundException;
import com.example.moviereviewer.model.Review;
import com.example.moviereviewer.repository.MovieRepository;
import com.example.moviereviewer.repository.ReviewRepository;

class ReviewServiceTest {

    private ReviewRepository reviewRepository;
    private MovieRepository movieRepository;
    private ReviewService reviewService;

    @BeforeEach
    void setUp() {
        reviewRepository = mock(ReviewRepository.class);
        movieRepository = mock(MovieRepository.class);
        reviewService = new ReviewService(reviewRepository, movieRepository, mock(MongoTemplate.class));
    }

    @Test
    void rejectsBlankReview() {
        assertThrows(IllegalArgumentException.class, () -> reviewService.createReview("   ", "tt0000001"));
        verify(reviewRepository, never()).insert(any(Review.class));
    }

    @Test
    void rejectsReviewOverMaxLength() {
        String tooLong = "a".repeat(ReviewService.MAX_REVIEW_LENGTH + 1);
        assertThrows(IllegalArgumentException.class, () -> reviewService.createReview(tooLong, "tt0000001"));
        verify(reviewRepository, never()).insert(any(Review.class));
    }

    @Test
    void rejectsReviewForUnknownMovie() {
        when(movieRepository.existsByImdbId("tt9999999")).thenReturn(false);
        assertThrows(MovieNotFoundException.class, () -> reviewService.createReview("Great film", "tt9999999"));
        verify(reviewRepository, never()).insert(any(Review.class));
    }
}
