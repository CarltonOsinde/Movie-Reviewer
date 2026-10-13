package com.example.moviereviewer.repository;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.moviereviewer.model.Movie;

@Repository
public interface MovieRepository extends MongoRepository<Movie, ObjectId> {

    /** Spring Data derives the query from the method name: { imdbId: ? }. */
    Optional<Movie> findMovieByImdbId(String imdbId);

    boolean existsByImdbId(String imdbId);
}
