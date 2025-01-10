package org.example.controllers;

import org.example.studymaterial.AudioReference;
import org.example.studymaterial.Reference;
import org.example.studymaterial.TextReference;
import org.example.studymaterial.VideoReference;
import org.example.studyregistry.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.example.controllers.MainController.getInput;
import static org.example.controllers.MainController.validateInput;

public class StudyRegistryController {
    StudyTaskManager studyTaskManager = StudyTaskManager.getStudyTaskManager();
    StudyMaterial studyMaterial = StudyMaterial.getStudyMaterial();
    private Map<String, Runnable> actions = new HashMap<>();

    public StudyRegistryController() {
        assignActions();
    }

    void assignActions() {
        actions.put("1", this::handleAddStudyTask);
        actions.put("2", this::handleAddStudyGoal);
        actions.put("3", this::handleAddStudyMaterial);
        actions.put("4", this::handleAddStudyObjective);
        actions.put("5", this::handleAddStudyPlan);
        actions.put("6", this::handleSetUpWeek);
        actions.put("7", this::handleGetWeekResponsibilities);
    }

    private void handleMethodHeader(String header) {
        System.out.println("~~~~" + header + "~~~~\n");
    }

    private Task getStudyTaskInfo() {
        System.out.println("Type the following info: title, description, author \n");
        String title = getInput();
        String description = getInput();
        String author = getInput();
        return new Task(title, description, author, LocalDateTime.now());
    }

    private void handleAddStudyTask() {
        Task task = getStudyTaskInfo();
        studyTaskManager.addRegistry(task);
    }

    private void handleSetObjective(StudyObjective objective) {
        handleMethodHeader("(Study Objective Edit)");
        System.out.println("Type the following info: Integer id, Integer priority " +
                "Integer practicedDays, int day, int month, int year, String name, String title, String description, " +
                "String topic, String objectiveInOneLine, String objectiveFullDescription, String motivation, " +
                "Double duration, boolean isActive  \n");

        StudyObjective.TimeDetails timeDetails = new StudyObjective.TimeDetails(
                Integer.parseInt(getInput()), Integer.parseInt(getInput()), Integer.parseInt(getInput()), Integer.parseInt(getInput()), Double.parseDouble(getInput())
        );

        StudyObjective.TextualInfo textualInfo = new StudyObjective.TextualInfo(
                getInput(), getInput(), getInput(), getInput(), getInput(), getInput(), getInput()
        );

        StudyObjective.ObjectiveDetails details = new StudyObjective.ObjectiveDetails(
                Integer.parseInt(getInput()), Integer.parseInt(getInput()), timeDetails, textualInfo
        );

        objective.handleSetObjective(details, Boolean.parseBoolean(getInput()));
    }

    private StudyObjective getStudyObjectiveInfo() {
        handleMethodHeader("(Study Objective Creation)");
        System.out.println("Type the following info: title, description \n");
        String title = getInput();
        String description = getInput();
        StudyObjective studyObjective = new StudyObjective(title, description);
        handleSetObjective(studyObjective);
        studyTaskManager.addRegistry(studyObjective);
        return studyObjective;
    }

    private StudyPlan getStudyPlanInfo() {
        handleMethodHeader("(Study Plan Creation)");
        System.out.println("Type the following info: name \n");
        String name = getInput();
        StudyObjective studyObjective = getStudyObjectiveInfo();
        StudyPlan plan = new StudyPlan(name, studyObjective, new ArrayList<>());
        studyTaskManager.addRegistry(plan);
        return plan;
    }

    private void handleSetSteps(StudyPlan studyPlan) {
        handleMethodHeader("(Study Plan Edit)");
        System.out.println("Type the following info: String firstStep, String resetStudyMechanism, String consistentStep, " +
                "String seasonalSteps, String basicSteps, String mainObjectiveTitle, String mainGoalTitle, String mainMaterialTopic, " +
                "String mainTask, @NotNull Integer numberOfSteps, boolean isImportant. " +
                "The Date to start is today, the date to end is x days from now, type the quantity of days\n");

        LocalDateTime createdAT = LocalDateTime.now();
        StudyPlan.StepDetails stepDetails = new StudyPlan.StepDetails()
                .setFirstStep(getInput())
                .setResetStudyMechanism(getInput())
                .setConsistentStep(getInput())
                .setSeasonalSteps(getInput())
                .setBasicSteps(getInput())
                .setMainObjectiveTitle(getInput())
                .setMainGoalTitle(getInput())
                .setMainMaterialTopic(getInput())
                .setMainTask(getInput())
                .setNumberOfSteps(Integer.parseInt(getInput()))
                .setImportant(Boolean.parseBoolean(getInput()))
                .setStartDate(createdAT)
                .setEndDate(createdAT.plusDays(Long.parseLong(getInput())));

        studyPlan.assignSteps(stepDetails);
    }

    private StudyGoal getStudyGoalInfo() {
        handleMethodHeader("(Study Goal Creation)");
        System.out.println("Type the following info: name \n");
        String name = getInput();
        StudyPlan studyPlan = getStudyPlanInfo();
        handleSetSteps(studyPlan);
        StudyObjective studyObjective = studyPlan.getObjective();
        return new StudyGoal(name, studyObjective, studyPlan);
    }

    private void handleAddStudyGoal() {
        StudyGoal goal = getStudyGoalInfo();
        studyTaskManager.addRegistry(goal);
    }

    private void editAudio(AudioReference audioReference) {
        handleMethodHeader("(Audio Edit)");
        System.out.println("Type the following info:  AudioReference. AudioQuality audioQuality, boolean isDownloadable, " +
                "String title, String description, String link, String accessRights, String license, String language, int rating, " +
                "int viewCount, int shareCount \n");
        AudioReference.AudioQuality quality = AudioReference.audioQualityAdapter(getInput());
        AudioReference.AudioAttributes attributes = new AudioReference.AudioAttributes(
                quality, Boolean.parseBoolean(getInput()), getInput(), getInput(), getInput(), getInput(),
                getInput(), getInput(), Integer.parseInt(getInput()), Integer.parseInt(getInput()), Integer.parseInt(getInput())
        );
        audioReference.editAudio(attributes);
    }

    private AudioReference addAudioReference() {
        handleMethodHeader("(Audio Reference Creation)");
        System.out.println("Type the following info: Audio Quality ( LOW | MEDIUM | HIGH | VERY_HIGH) \n");
        AudioReference audioReference = new AudioReference(AudioReference.audioQualityAdapter(getInput()));
        editAudio(audioReference);
        return audioReference;
    }

    private VideoReference addVideoReference() {
        handleMethodHeader("(Video Reference Creation)");
        System.out.println("Type the following info: boolean isAvailable, String title, " +
                "String description, String resolution, String frameRate, String videoFormat, String accessRights \n");
        return new VideoReference(Boolean.parseBoolean(getInput()), getInput(), getInput(), getInput(), getInput(),
                getInput(), getInput());
    }

    private TextReference addTextReference() {
        handleMethodHeader("(Text Reference Creation)");
        System.out.println("Type the following info:  String title, String language, int wordCount, String format, String accessRights \n");
        return new TextReference(getInput(), getInput(), Integer.parseInt(getInput()), getInput(),
                getInput());
    }

    private Reference addStudyMaterial() {
        handleMethodHeader("(Study Material Creation)");
        System.out.println("Type the following info: ( AUDIO | VIDEO | TEXT ) \n");
        String type = getInput();
        return switch (type.toLowerCase()) {
            case "audio" -> addAudioReference();
            case "video" -> addVideoReference();
            case "text" -> addTextReference();
            default -> null;
        };
    }

    private void handleAddStudyMaterial() {
        Reference reference = addStudyMaterial();
        if (reference != null) {
            studyMaterial.addReference(reference);
        }
    }

    private void handleAddStudyObjective() {
        getStudyObjectiveInfo();
    }

    private void handleAddStudyPlan() {
        getStudyPlanInfo();
        System.out.println("Study Plan Added");
    }

    private void getWeekInfo() {
        System.out.println("(Study Task Manager Week Set Up) Type the following info: String planName, String objectiveTitle, " +
                "String objectiveDescription, String materialTopic, String materialFormat, String goal, String reminderTitle, " +
                "String reminderDescription, String mainTaskTitle, String mainHabit, String mainCardStudy");
        List<String> weekInfo = new ArrayList<>();
        for (int i = 0; i < 11; i++) {
            weekInfo.add(getInput());
        }
        StudyTaskManager.WeekSetupAttributes attributes = new StudyTaskManager.WeekSetupAttributes(
                weekInfo.get(0), weekInfo.get(1), weekInfo.get(2), weekInfo.get(3), weekInfo.get(4),
                weekInfo.get(5), weekInfo.get(6), weekInfo.get(7), weekInfo.get(8), weekInfo.get(9), weekInfo.get(10)
        );
        studyTaskManager.setUpWeek(attributes);
    }

    private void handleSetUpWeek() {
        getWeekInfo();
    }

    private void handleGetWeekResponsibilities() {
        List<String> responsibilities = studyTaskManager.getWeekResponsibilities();
        System.out.println(String.join(", ", responsibilities));
    }

    public void handleRegistryInput() {
        try {
            while (true) {
                controllerOptions();
                String response = validateInput(actions);
                if (response == null) {
                    return;
                }
                actions.get(response).run();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void controllerOptions() {
        System.out.println("""
                0 - return
                1 - add study task
                2 - add study goal
                3 - add study material (audio, video, text)
                4 - add study objective
                5 - add study plan
                6 - set up week
                7 - get week responsibilities
               """);
    }
}