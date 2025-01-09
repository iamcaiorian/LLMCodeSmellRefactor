package org.example.studyregistry;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StudyPlan extends Registry {
    private StudyObjective objective;
    private List<String> steps;

    public StudyPlan(String planName, StudyObjective objective, List<StudyMaterial> materials) {
        this.name = planName;
        this.objective = objective;
        this.steps = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Plan: " + name + ",\nObjective: " + objective.getDescription() + ",\nSteps: " + String.join(", ", steps);
    }

    public List<String> getSteps() {
        return steps;
    }

    public StudyObjective getObjective() {
        return objective;
    }

    public void assignObjective(StudyObjective objective) {
        this.objective = objective;
    }

    public void addSingleStep(String toAdd) {
        steps.add(toAdd);
    }

    public void assignSteps(StepDetails stepDetails) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        this.steps = new ArrayList<>(Arrays.asList(
                stepDetails.getFirstStep(),
                stepDetails.getResetStudyMechanism(),
                stepDetails.getConsistentStep(),
                stepDetails.getSeasonalSteps(),
                stepDetails.getBasicSteps(),
                "Number of steps: " + stepDetails.getNumberOfSteps().toString(),
                "Is it important to you? " + stepDetails.isImportant(),
                stepDetails.getStartDate().format(formatter),
                stepDetails.getEndDate().format(formatter),
                stepDetails.getMainObjectiveTitle(),
                stepDetails.getMainGoalTitle(),
                stepDetails.getMainMaterialTopic(),
                stepDetails.getMainTask()
        ));
    }

    public void handleAssignSteps(List<String> stringProperties, Integer numberOfSteps, boolean isImportant, LocalDateTime startDate, LocalDateTime endDate) {
        StepDetails stepDetails = new StepDetails()
                .setFirstStep(stringProperties.get(0))
                .setResetStudyMechanism(stringProperties.get(1))
                .setConsistentStep(stringProperties.get(2))
                .setSeasonalSteps(stringProperties.get(3))
                .setBasicSteps(stringProperties.get(4))
                .setMainObjectiveTitle(stringProperties.get(5))
                .setMainGoalTitle(stringProperties.get(6))
                .setMainMaterialTopic(stringProperties.get(7))
                .setMainTask(stringProperties.get(8))
                .setNumberOfSteps(numberOfSteps)
                .setImportant(isImportant)
                .setStartDate(startDate)
                .setEndDate(endDate);
        assignSteps(stepDetails);
    }

    public static class StepDetails {
        private String firstStep;
        private String resetStudyMechanism;
        private String consistentStep;
        private String seasonalSteps;
        private String basicSteps;
        private String mainObjectiveTitle;
        private String mainGoalTitle;
        private String mainMaterialTopic;
        private String mainTask;
        private Integer numberOfSteps;
        private boolean important;
        private LocalDateTime startDate;
        private LocalDateTime endDate;

        public StepDetails setFirstStep(String firstStep) {
            this.firstStep = firstStep;
            return this;
        }

        public StepDetails setResetStudyMechanism(String resetStudyMechanism) {
            this.resetStudyMechanism = resetStudyMechanism;
            return this;
        }

        public StepDetails setConsistentStep(String consistentStep) {
            this.consistentStep = consistentStep;
            return this;
        }

        public StepDetails setSeasonalSteps(String seasonalSteps) {
            this.seasonalSteps = seasonalSteps;
            return this;
        }

        public StepDetails setBasicSteps(String basicSteps) {
            this.basicSteps = basicSteps;
            return this;
        }

        public StepDetails setMainObjectiveTitle(String mainObjectiveTitle) {
            this.mainObjectiveTitle = mainObjectiveTitle;
            return this;
        }

        public StepDetails setMainGoalTitle(String mainGoalTitle) {
            this.mainGoalTitle = mainGoalTitle;
            return this;
        }

        public StepDetails setMainMaterialTopic(String mainMaterialTopic) {
            this.mainMaterialTopic = mainMaterialTopic;
            return this;
        }

        public StepDetails setMainTask(String mainTask) {
            this.mainTask = mainTask;
            return this;
        }

        public StepDetails setNumberOfSteps(Integer numberOfSteps) {
            this.numberOfSteps = numberOfSteps;
            return this;
        }

        public StepDetails setImportant(boolean important) {
            this.important = important;
            return this;
        }

        public StepDetails setStartDate(LocalDateTime startDate) {
            this.startDate = startDate;
            return this;
        }

        public StepDetails setEndDate(LocalDateTime endDate) {
            this.endDate = endDate;
            return this;
        }

        public String getFirstStep() {
            return firstStep;
        }

        public String getResetStudyMechanism() {
            return resetStudyMechanism;
        }

        public String getConsistentStep() {
            return consistentStep;
        }

        public String getSeasonalSteps() {
            return seasonalSteps;
        }

        public String getBasicSteps() {
            return basicSteps;
        }

        public String getMainObjectiveTitle() {
            return mainObjectiveTitle;
        }

        public String getMainGoalTitle() {
            return mainGoalTitle;
        }

        public String getMainMaterialTopic() {
            return mainMaterialTopic;
        }

        public String getMainTask() {
            return mainTask;
        }

        public Integer getNumberOfSteps() {
            return numberOfSteps;
        }

        public boolean isImportant() {
            return important;
        }

        public LocalDateTime getStartDate() {
            return startDate;
        }

        public LocalDateTime getEndDate() {
            return endDate;
        }
    }
}