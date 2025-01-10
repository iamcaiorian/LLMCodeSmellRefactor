package org.example.studyregistry;

import java.time.LocalDateTime;
import java.util.List;

public class StudyObjective extends Registry {
    private String title;
    private String description;
    private String topic;
    private Integer practicedDays;
    private LocalDateTime startDate;
    private Double duration;
    private String objectiveInOneLine;
    private String objectiveFullDescription;
    private String motivation;

    public static class TimeDetails {
        Integer practicedDays;
        int day;
        int month;
        int year;
        Double duration;

        public TimeDetails(Integer practicedDays, int day, int month, int year, Double duration) {
            this.practicedDays = practicedDays;
            this.day = day;
            this.month = month;
            this.year = year;
            this.duration = duration;
        }
    }

    public static class TextualInfo {
        String name;
        String title;
        String description;
        String topic;
        String objectiveInOneLine;
        String objectiveFullDescription;
        String motivation;

        public TextualInfo(String name, String title, String description, String topic, String objectiveInOneLine, String objectiveFullDescription, String motivation) {
            this.name = name;
            this.title = title;
            this.description = description;
            this.topic = topic;
            this.objectiveInOneLine = objectiveInOneLine;
            this.objectiveFullDescription = objectiveFullDescription;
            this.motivation = motivation;
        }
    }

    public static class ObjectiveDetails {
        Integer id;
        Integer priority;
        TimeDetails timeDetails;
        TextualInfo textualInfo;

        public ObjectiveDetails(Integer id, Integer priority, TimeDetails timeDetails, TextualInfo textualInfo) {
            this.id = id;
            this.priority = priority;
            this.timeDetails = timeDetails;
            this.textualInfo = textualInfo;
        }
    }

    public String getTitle() {
        return title;
    }

    public String getTopic() {
        return topic;
    }

    public Integer getPracticedDays() {
        return practicedDays;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public Double getDuration() {
        return duration;
    }

    public String getObjectiveInOneLine() {
        return objectiveInOneLine;
    }

    public String getObjectiveFullDescription() {
        return objectiveFullDescription;
    }

    public String getMotivation() {
        return motivation;
    }

    @Override
    public String toString() {
        return "StudyObjective [title:" + title + ", description:" + description + (topic != null ? ", topic:" + topic : "")
                + (practicedDays != null ? ", practicedDays:" + practicedDays : "") + (duration != null ? ", duration:" + duration : "")
                + (objectiveInOneLine != null ? ", objective summary:" + objectiveInOneLine : "") + (objectiveFullDescription != null ? ", objective full description:" + objectiveFullDescription : "")
                + (motivation != null ? ", motivation:" + motivation : "") + "]";
    }

    public StudyObjective(String title, String description) {
        this.title = title;
        this.description = description;
        this.name = title;
    }

    public void handleSetRegistry(Integer id, String name, Integer priority, boolean isActive) {
        this.id = id;
        this.name = name;
        this.priority = priority;
        this.isActive = isActive;
    }

    public void handleSetTextualInfo(TextualInfo textualInfo) {
        this.title = textualInfo.title;
        this.description = textualInfo.description;
        this.topic = textualInfo.topic;
        this.objectiveInOneLine = textualInfo.objectiveInOneLine;
        this.objectiveFullDescription = textualInfo.objectiveFullDescription;
        this.motivation = textualInfo.motivation;
    }

    public void handleSetTime(TimeDetails timeDetails) {
        this.practicedDays = timeDetails.practicedDays;
        this.duration = timeDetails.duration;
        this.startDate = LocalDateTime.of(timeDetails.year, timeDetails.month, timeDetails.day, 0, 0);
    }

    public void handleSetObjective(ObjectiveDetails details, boolean isActive) {
        handleSetRegistry(details.id, details.textualInfo.name, details.priority, isActive);
        handleSetTextualInfo(details.textualInfo);
        handleSetTime(details.timeDetails);
    }

    public int handleSetObjectiveAdapter(List<Integer> intProperties, List<String> stringProperties, Double duration, boolean isActive) {
        TimeDetails timeDetails = new TimeDetails(
                intProperties.get(2), intProperties.get(3), intProperties.get(4), intProperties.get(5), duration
        );
        TextualInfo textualInfo = new TextualInfo(
                stringProperties.get(0), stringProperties.get(1), stringProperties.get(2), stringProperties.get(3),
                stringProperties.get(4), stringProperties.get(5), stringProperties.get(6)
        );
        ObjectiveDetails details = new ObjectiveDetails(intProperties.get(0), intProperties.get(1), timeDetails, textualInfo);
        handleSetObjective(details, isActive);
        return intProperties.get(0);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}