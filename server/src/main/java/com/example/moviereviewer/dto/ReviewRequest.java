package com.example.moviereviewer.dto;

/** Request body for POST /api/v1/reviews. */
public record ReviewRequest(String reviewBody, String imdbId) {
}
