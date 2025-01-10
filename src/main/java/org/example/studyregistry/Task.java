package org.example.studyregistry;

import java.time.LocalDateTime;

public class Task extends Registry {
    private String title;
    private String description;
    private String author;
    private LocalDateTime date;

    public Task(String title, String description, String author, LocalDateTime date) {
        validateTitle(title);
        validateAuthor(author);
        validateDate(date);

        this.title = title;
        this.name = title;
        this.description = description;
        this.author = author;
        this.date = date;
    }

    public boolean isOverdue() {
        return LocalDateTime.now().isAfter(date);
    }

    public boolean isAssignedTo(String authorName) {
        return author.equals(authorName);
    }

    public void updateDetails(String newTitle, String newDescription) {
        validateTitle(newTitle);
        this.title = newTitle;
        this.name = newTitle;
        this.description = newDescription;
    }

    public void reschedule(LocalDateTime newDate) {
        validateDate(newDate);
        this.date = newDate;
    }

    public void reassign(String newAuthor) {
        validateAuthor(newAuthor);
        this.author = newAuthor;
    }

    // Validation methods
    private void validateTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be null or empty");
        }
    }

    private void validateAuthor(String author) {
        if (author == null || author.trim().isEmpty()) {
            throw new IllegalArgumentException("Author cannot be null or empty");
        }
    }

    private void validateDate(LocalDateTime date) {
        if (date == null) {
            throw new IllegalArgumentException("Date cannot be null");
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        validateTitle(title);
        this.title = title;
        this.name = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        validateAuthor(author);
        this.author = author;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        validateDate(date);
        this.date = date;
    }
}