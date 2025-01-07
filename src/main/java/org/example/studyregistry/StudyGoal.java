package org.example.studyregistry;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class StudyGoal extends Registry {
    private String goal;
    private List<String> goalRequirements;
    private Boolean isCompleted;
    private LocalDateTime createdDate;
    private Double goalCompletion;
    private StudyObjective studyObjective;
    private StudyPlan studyPlan;
    private String summary;

    public StudyGoal(String name, StudyObjective objective, StudyPlan plan) {
        this.name = name;
        this.studyObjective = objective;
        this.studyPlan = plan;
        goalRequirements = new ArrayList<>();
    }

    public void editActiveCompleted(boolean active, boolean completed) {
        this.isActive = active;
        this.isCompleted = completed;
    }

    public String setGoalSummary() {
        StringBuilder summary = new StringBuilder();
        summary.append("Goal Summary:\n\n");

        appendGoalStatus(summary);
        appendRequirements(summary);
        appendPlanAndObjective(summary);

        this.summary = summary.toString();
        return summary.toString();
    }

    private void appendGoalStatus(StringBuilder summary) {
        if (this.isActive) {
            summary.append("Active Goal:\n").append(goal).append("\n\n");
        }
        if (this.isCompleted) {
            summary.append("Completed Goal:\n").append(goal).append("\n\n");
        }
    }

    private void appendRequirements(StringBuilder summary) {
        if (this.goalRequirements != null) {
            summary.append("Requirements:\n");
            for (String requirement : this.goalRequirements) {
                summary.append(requirement).append(", ");
            }
        }
    }

    private void appendPlanAndObjective(StringBuilder summary) {
        if (this.studyPlan != null) {
            summary.append("Plan:\n");
            summary.append(this.studyPlan.toString());
        }
        if (this.studyObjective != null) {
            summary.append("Objective:\n");
            summary.append(this.studyObjective.toString());
        }
    }

    public void addRequirement(String requirement) {
        this.goalRequirements.add(requirement);
    }

    public void resetRequirements() {
        this.goalRequirements.clear();
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void toggleIsCompleted() {
        this.isCompleted = !this.isCompleted;
    }

    public LocalDateTime getLimitDate() {
        return createdDate;
    }

    public void setLimitDate(LocalDateTime limitDate) {
        this.createdDate = limitDate;
    }

    public void addDaysLimitDate(int days) {
        this.createdDate = this.createdDate.plusDays(days);
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    // Getters and setters for other fields if needed
    public String getGoal() {
        return goal;
    }

    public List<String> getGoalRequirements() {
        return goalRequirements;
    }

    public Double getGoalCompletion() {
        return goalCompletion;
    }

    public void setGoalCompletion(Double goalCompletion) {
        this.goalCompletion = goalCompletion;
    }

    public StudyObjective getStudyObjective() {
        return studyObjective;
    }

    public void setStudyObjective(StudyObjective studyObjective) {
        this.studyObjective = studyObjective;
    }

    public StudyPlan getStudyPlan() {
        return studyPlan;
    }

    public void setStudyPlan(StudyPlan studyPlan) {
        this.studyPlan = studyPlan;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}