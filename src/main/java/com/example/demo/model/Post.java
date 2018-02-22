package com.example.demo.model;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Document(collection = "posts")
public class Post {
    @Id
    private String id;

    @NotBlank
    @Size(max = 140)
    private String text;

    @CreatedBy
    private String username;

    @NotNull
    private LocalDate createdAt = LocalDate.now();

    public Post() {

    }

    public Post( @NotBlank @Size(max = 140) String text, String username) {
        this.text = text;
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }
}
