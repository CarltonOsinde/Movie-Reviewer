package com.example.moviereviewer.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.moviereviewer.model.Review;

@Repository
public interface ReviewRepository extends MongoRepository<Review, ObjectId> {
}
