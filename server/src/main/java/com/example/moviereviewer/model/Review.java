package com.example.moviereviewer.model;

import java.time.Instant;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

/** A single user review, stored in the "reviews" collection. */
@Document(collection = "reviews")
public class Review {

    @Id
    @JsonSerialize(using = ToStringSerializer.class)
    private ObjectId id;
    private String body;
    private Instant created;
    private Instant updated;

    public Review() {
    }

    public Review(String body, Instant created, Instant updated) {
        this.body = body;
        this.created = created;
        this.updated = updated;
    }

    public ObjectId getId() { return id; }
    public void setId(ObjectId id) { this.id = id; }

    public String getBody() { return body; }
    public void setBody(String body) { this.body = body; }

    public Instant getCreated() { return created; }
    public void setCreated(Instant created) { this.created = created; }

    public Instant getUpdated() { return updated; }
    public void setUpdated(Instant updated) { this.updated = updated; }
}
