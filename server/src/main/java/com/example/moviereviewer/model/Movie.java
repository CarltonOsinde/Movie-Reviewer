package com.example.moviereviewer.model;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

/**
 * A movie in the "movies" collection. Reviews are stored in their own
 * collection and referenced by id, so a movie document stays small no matter
 * how many reviews it collects.
 */
@Document(collection = "movies")
public class Movie {

    @Id
    @JsonSerialize(using = ToStringSerializer.class)
    private ObjectId id;
    private String imdbId;
    private String title;
    private String releaseDate;
    private String trailerLink;
    private String poster;
    private List<String> genres = new ArrayList<>();
    private List<String> backdrops = new ArrayList<>();

    @DocumentReference
    private List<Review> reviews = new ArrayList<>();

    public Movie() {
    }

    public ObjectId getId() { return id; }
    public void setId(ObjectId id) { this.id = id; }

    public String getImdbId() { return imdbId; }
    public void setImdbId(String imdbId) { this.imdbId = imdbId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getReleaseDate() { return releaseDate; }
    public void setReleaseDate(String releaseDate) { this.releaseDate = releaseDate; }

    public String getTrailerLink() { return trailerLink; }
    public void setTrailerLink(String trailerLink) { this.trailerLink = trailerLink; }

    public String getPoster() { return poster; }
    public void setPoster(String poster) { this.poster = poster; }

    public List<String> getGenres() { return genres; }
    public void setGenres(List<String> genres) { this.genres = genres; }

    public List<String> getBackdrops() { return backdrops; }
    public void setBackdrops(List<String> backdrops) { this.backdrops = backdrops; }

    public List<Review> getReviews() { return reviews; }
    public void setReviews(List<Review> reviews) { this.reviews = reviews; }
}
