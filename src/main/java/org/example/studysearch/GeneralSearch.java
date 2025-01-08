package org.example.studysearch;

import org.example.studycards.CardManager;
import org.example.studyplanner.HabitTracker;
import org.example.studyplanner.TodoTracker;
import org.example.studyregistry.StudyMaterial;
import org.example.studyregistry.StudyTaskManager;

import java.util.ArrayList;
import java.util.List;

public class GeneralSearch implements Search<String> {
    private SearchLog searchLog = new SearchLog("General Search");

    public GeneralSearch() {}

    @Override
    public List<String> search(String text) {
        return searchLog.handleSearch(text, getSearchComponents());
    }

    public SearchLog getSearchLog(){
        return searchLog;
    }

    private List<SearchComponent> getSearchComponents() {
        List<SearchComponent> components = new ArrayList<>();
        components.add(text -> CardManager.getCardManager().searchInCards(text));
        components.add(text -> HabitTracker.getHabitTracker().searchInHabits(text));
        components.add(text -> TodoTracker.getInstance().searchInTodos(text));
        components.add(text -> StudyMaterial.getStudyMaterial().searchInMaterials(text));
        components.add(text -> StudyTaskManager.getStudyTaskManager().searchInRegistries(text));
        return components;
    }
}