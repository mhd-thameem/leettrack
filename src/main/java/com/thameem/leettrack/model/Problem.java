package com.thameem.leettrack.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "problems")
public class Problem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title is required")
    private String title;

    @Enumerated(EnumType.STRING)
    private Difficulty difficulty;

    private String pattern; // e.g. "Two Pointers", "Sliding Window"

    private String url;

    @Enumerated(EnumType.STRING)
    private Status status = Status.TODO;

    // Required by JPA — it needs an empty constructor to create objects
    public Problem() {
    }

    public Problem(String title, Difficulty difficulty, String pattern, String url) {
        this.title = title;
        this.difficulty = difficulty;
        this.pattern = pattern;
        this.url = url;
        this.status = Status.TODO;
    }

    // Getters and setters — JPA and Spring use these to read/write field values

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}