package org.example.controllers;

import org.example.studyplanner.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;


public class StudyPlannerController {
    private Map<String, Runnable> actions = new HashMap<>();
    private static TodoTracker todoTracker = TodoTracker.getInstance();
    private static HabitTracker habitTracker = HabitTracker.getHabitTracker();
    private static KanbanView kanbanView = new KanbanView(habitTracker, todoTracker);
    private static TimelineView timelineView = new TimelineView();

    public StudyPlannerController() {
        handlePlannerOptions();
        handleTodoMenuOptions();
        handleHabitMenuOptions();
        handleViewMenuOptions();
    }

    private void handlePlannerOptions(){
        actions.put("1", this::handleTodoInput);
        actions.put("2", this::handleHabitInput);
        actions.put("3", this::handleViewInput);
    }

    private void handleTodoMenuOptions(){
        actions.put("11", this::handleAddTodo);
        actions.put("12", this::handleRemoveTodo);
        actions.put("13", this::handleViewTodo);
        actions.put("14", this::handleViewByPriority);
        actions.put("15", this::handleAddTodoExecution);
    }

    private void handleHabitMenuOptions(){
        actions.put("21", this::handleAddHabit);
        actions.put("22", this::handleRemoveHabit);
        actions.put("23", this::handleViewHabits);
    }


    private void handleViewMenuOptions() {
        actions.put("31", () -> {
            try {
                handleViewKanban();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });
        actions.put("32", () -> {
            try {
                handleViewTimeline();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });
    }

    private String getInput(){
        return MainController.getInput();
    }

    private void handleAddTodoExecution(){
        System.out.println("Type todo id to add a new practiced time");
        Integer id = Integer.parseInt(getInput());
        todoTracker.addToDoExecutionTime(id);
    }

    private String showToDosByPriority(List<ToDo> todos){
        return todos.toString();
    }

    private void handleViewByPriority(){
        List<ToDo> todos = todoTracker.sortTodosByPriority();
        System.out.println(showToDosByPriority(todos));
    }

    public void handleRemoveTodo(){
        System.out.println("Type todo id to remove");
        Integer id = Integer.parseInt(getInput());
        todoTracker.removeToDo(id);
    }

    public void handleRemoveHabit(){
        System.out.println("Type habit id to remove");
        Integer id = Integer.parseInt(getInput());
        habitTracker.removeHabit(id);
    }

    private void handleAddTodo(){
        System.out.println("Type the todo: title, description, priority (number)");
        String title = Objects.requireNonNull(this.getInput().trim());
        String description = Objects.requireNonNull(this.getInput()).trim();
        Integer priority = Integer.valueOf(this.getInput());
        todoTracker.addToDo(title, description, priority);
    }

    private LocalDateTime handleGetStartDate(){
        int year = Integer.parseInt(getInput());
        int month = Integer.parseInt(getInput());
        int day = Integer.parseInt(getInput());
        int hour = Integer.parseInt(getInput());
        int minute = Integer.parseInt(getInput());
        int seconds = Integer.parseInt(getInput());
        return LocalDateTime.of(year, month, day, hour, minute, seconds);
    }

    private void handleAddHabit(){
        System.out.println("Separate the input with enter, type: name, motivation, daily Minutes Dedication, daily Hours Dedication, year, month, day, hour, minute, seconds");
        String name = Objects.requireNonNull(this.getInput().trim());
        String motivation = Objects.requireNonNull(this.getInput().trim());
        Integer dailyMinutesDedication = Integer.parseInt(Objects.requireNonNull(this.getInput().trim()));
        Integer dailyHoursDedication = Integer.parseInt(Objects.requireNonNull(this.getInput().trim()));
        LocalDateTime startDate = handleGetStartDate();
        habitTracker.addHabit(name, motivation, dailyMinutesDedication, dailyHoursDedication, startDate, false);
    }

    private String viewToDoHeader(){
        return "Todos and latest usages:";
    }

    private void handleViewTodo(){
        System.out.println(viewToDoHeader());
        System.out.println(todoTracker.toString());
    }


    private String viewHabitHeader(){
        return "Habits found: ";
    }

    private void handleViewHabits(){
        System.out.println(viewHabitHeader());
        System.out.println(habitTracker.toString());
    }

    private void handleTodoInput(){
        try{
            while(true){
                toDoOptions();
                String response = MainController.validateInput(actions);
                if(response == null) {return;}
                actions.get(response).run();
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private void handleHabitInput(){
        try{
            while(true){
                habitOptions();
                String response = MainController.validateInput(actions);
                if(response == null) {return;}
                actions.get(response).run();
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private void handleViewInput(){
        try{
            while(true){
                viewOptions();
                String response = MainController.validateInput(actions);
                if(response == null) {return;}
                actions.get(response).run();
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private void handleMethodHeader(String header){
        System.out.println("---" + header + "---");
    }

    private void handleViewKanban() throws Exception {
        try{
            handleMethodHeader("Kanban view: ");
            System.out.println(kanbanView.kanbanView());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private void handleViewTimeline() throws Exception {
        try{
            handleMethodHeader("Timeline view: ");
            System.out.println(timelineView.habitDateViewAll(habitTracker));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void handlePlannerInput(){
        try{
            while(true){
                controllerOptions();
                String response = MainController.validateInput(actions);
                if(response == null) {return;}
                actions.get(response).run();
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static void controllerOptions(){
        System.out.println("""
                0 - return
                1 - todo menu
                2 - habit menu
                3 - view menu
               """);
    }

    public static void toDoOptions(){
        System.out.println("""
                0 - return
                11 - add todo
                12 - remove todo
                13 - view todos
                14 - view by priority
                15 - add todo execution date (now)
               """);
    }

    public static void habitOptions(){
        System.out.println("""
                0 - return
                21 - add habit
                22 - remove habit
                23 - view habit
               """);
    }

    public static void viewOptions(){
        System.out.println("""
                0 - return
                31 - kanban view
                32 - timeline view
               """);
    }
}
