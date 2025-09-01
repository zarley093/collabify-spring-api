package com.collabify.app.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

@Entity
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    @Lob
    private String description;
    
    private boolean completed = false;

    protected Todo() {}

    public Todo(String title, String description, Boolean completed) {
        this.title = title;
        this.description = description;
        this.completed = completed;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String titleToSet) {
        this.title = titleToSet;
    }

    public String getDescription() {
        return description;
    }
    
    public void setDescription(String descriptionToSet) {
        this.description = descriptionToSet;
    }

    public boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completedToSet) {
        this.completed = completedToSet;
    }
}
