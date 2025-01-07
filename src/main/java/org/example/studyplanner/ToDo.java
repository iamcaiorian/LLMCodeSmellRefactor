package org.example.studyplanner;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ToDo implements PlannerMaterial {
    private Integer id;
    private String title;
    private String description;
    private int priority;
    private LocalDateTime createdAt;
    private LocalDateTime lastModified;
    private List<String> changes;
    private boolean isCompleted;

    public ToDo(Integer id, String title, String description, int priority) {
        validateFields(id, title, priority);
        this.id = id;
        this.title = title;
        this.description = description;
        this.priority = validatePriority(priority);
        this.createdAt = LocalDateTime.now();
        this.lastModified = LocalDateTime.now();
        this.changes = new ArrayList<>();
        this.isCompleted = false;
        logChange("Todo created");
    }

    public void appendToDoInfo(StringBuilder str) {
        str.append(this.toString()).append("\n");
    }

    public boolean containsSearchTerm(String searchTerm) {
        return title.toLowerCase().contains(searchTerm) ||
                description.toLowerCase().contains(searchTerm);
    }

    public void markAsComplete() {
        if (!isCompleted) {
            isCompleted = true;
            logChange("Marked as complete");
        }
    }

    public void markAsIncomplete() {
        if (isCompleted) {
            isCompleted = false;
            logChange("Marked as incomplete");
        }
    }

    public boolean isHighPriority() {
        return priority >= 7;
    }

    public boolean isMediumPriority() {
        return priority >= 4 && priority <= 6;
    }

    public boolean isLowPriority() {
        return priority <= 3;
    }

    public List<String> getChangeHistory() {
        return new ArrayList<>(changes);
    }

    public boolean isOverdue(LocalDateTime currentTime) {
        return !isCompleted && lastModified.plusDays(7).isBefore(currentTime);
    }

    public String getPriorityLevel() {
        return switch (priority) {
            case 8, 9, 10 -> "CRITICAL";
            case 6, 7 -> "HIGH";
            case 4, 5 -> "MEDIUM";
            case 2, 3 -> "LOW";
            case 1 -> "MINIMAL";
            default -> "UNDEFINED";
        };
    }

    // Métodos de validação
    private void validateFields(Integer id, String title, int priority) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be empty");
        }
        validatePriority(priority);
    }

    private int validatePriority(int priority) {
        if (priority < 1 || priority > 10) {
            throw new IllegalArgumentException("Priority must be between 1 and 10");
        }
        return priority;
    }

    private void logChange(String change) {
        changes.add(String.format("[%s] %s", LocalDateTime.now(), change));
        this.lastModified = LocalDateTime.now();
    }

    // Getters e Setters (mantidos para compatibilidade com testes)
    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        if (id != null) {
            String oldId = String.valueOf(this.id);
            this.id = id;
            logChange(String.format("ID updated from %s to %s", oldId, id));
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title != null && !title.trim().isEmpty()) {
            String oldTitle = this.title;
            this.title = title.trim();
            logChange(String.format("Title updated from '%s' to '%s'", oldTitle, title));
        }
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        String oldDescription = this.description;
        this.description = description;
        logChange(String.format("Description updated from '%s' to '%s'", oldDescription, description));
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        int validPriority = validatePriority(priority);
        int oldPriority = this.priority;
        this.priority = validPriority;
        logChange(String.format("Priority changed from %d to %d", oldPriority, validPriority));
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getLastModified() {
        return lastModified;
    }

    @Override
    public String toString() {
        return MessageFormat.format("[(Priority:{3}) ToDo {0}: {1}, {2}]",
                id,
                title,
                description,
                priority);
    }
}