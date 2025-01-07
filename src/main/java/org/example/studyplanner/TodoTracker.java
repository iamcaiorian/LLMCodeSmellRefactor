package org.example.studyplanner;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TodoTracker {
    private List<ToDo> toDos = new ArrayList<>();
    private Map<Integer, List<LocalDateTime>> tracker;
    private Integer nextId;
    private static TodoTracker instance;
    private static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    private TodoTracker() {
        this.tracker = new HashMap<>();
        this.toDos = new ArrayList<>();
        this.nextId = 1;
    }

    public static TodoTracker getInstance() {
        if (instance == null) {
            instance = new TodoTracker();
        }
        return instance;
    }

    @Override
    public String toString() {
        if (toDos.isEmpty()) {
            return "No ToDos found";
        }

        StringBuilder str = new StringBuilder();
        for (ToDo toDo : toDos) {
            toDo.appendToDoInfo(str);
            appendTrackingDates(str, toDo.getId());
        }
        return str.toString();
    }

    private void appendTrackingDates(StringBuilder str, Integer id) {
        List<LocalDateTime> todosDate = this.tracker.get(id);
        if (todosDate == null) {
            str.append("No tracks found\n");
            return;
        }

        appendFormattedDates(str, todosDate);
    }

    private void appendFormattedDates(StringBuilder str, List<LocalDateTime> dates) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);
        for (LocalDateTime ldt : dates) {
            str.append(formatter.format(ldt))
                    .append("\n");
        }
    }

    public void addToDoExecutionTime(Integer id) {
        List<LocalDateTime> et = tracker.computeIfAbsent(id, k -> new ArrayList<>());
        et.add(LocalDateTime.now());
    }

    public List<ToDo> getToDos() {
        return toDos;
    }

    public ToDo getToDoById(Integer id) {
        return toDos.stream()
                .filter(toDo -> toDo.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public Integer addToDo(String title, String description, Integer priority) {
        ToDo toAdd = new ToDo(nextId, title, description, priority);
        nextId++;
        this.toDos.add(toAdd);
        return toAdd.getId();
    }

    public void removeToDo(Integer id) {
        toDos.removeIf(toDo -> toDo.getId() == id);
    }

    public List<ToDo> sortTodosByPriority() {
        List<ToDo> sortedToDos = new ArrayList<>(toDos);
        sortedToDos.sort(Comparator.comparingInt(ToDo::getPriority));
        return sortedToDos;
    }

    public List<String> searchInTodos(String search) {
        return toDos.stream()
                .filter(toDo -> toDo.containsSearchTerm(search.toLowerCase()))
                .map(ToDo::toString)
                .toList();
    }
}