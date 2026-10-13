package com.example.moviereviewer.service;

import java.time.Instant;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.example.moviereviewer.exception.MovieNotFoundException;
import com.example.moviereviewer.model.Movie;
import com.example.moviereviewer.model.Review;
import com.example.moviereviewer.repository.MovieRepository;
import com.example.moviereviewer.repository.ReviewRepository;

@Service
public class ReviewService {

    static final int MAX_REVIEW_LENGTH = 2000;

    private final ReviewRepository reviewRepository;
    private final MovieRepository movieRepository;
    private final MongoTemplate mongoTemplate;

    public ReviewService(ReviewRepository reviewRepository,
                         MovieRepository movieRepository,
                         MongoTemplate mongoTemplate) {
        this.reviewRepository = reviewRepository;
        this.movieRepository = movieRepository;
        this.mongoTemplate = mongoTemplate;
    }

    /**
     * Saves the review in its own collection, then pushes its id onto the
     * movie's reviews array with a single atomic $push.
     */
    public Review createReview(String reviewBody, String imdbId) {
        String body = reviewBody == null ? "" : reviewBody.trim();
        if (body.isEmpty()) {
            throw new IllegalArgumentException("Review body must not be empty");
        }
        if (body.length() > MAX_REVIEW_LENGTH) {
            throw new IllegalArgumentException("Review must be " + MAX_REVIEW_LENGTH + " characters or fewer");
        }
        if (imdbId == null || !movieRepository.existsByImdbId(imdbId)) {
            throw new MovieNotFoundException(imdbId);
        }

        Instant now = Instant.now();
        Review review = reviewRepository.insert(new Review(body, now, now));

        mongoTemplate.update(Movie.class)
                .matching(Query.query(Criteria.where("imdbId").is(imdbId)))
                .apply(new Update().push("reviews").value(review))
                .first();

        return review;
    }
}
