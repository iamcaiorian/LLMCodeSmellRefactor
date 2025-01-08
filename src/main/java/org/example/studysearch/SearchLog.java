package org.example.studysearch;

import org.example.studyregistry.StudyMaterial;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@FunctionalInterface
interface SearchComponent {
    List<String> search(String text);
}

public class SearchLog {
    private List<String> searchHistory;
    private Map<String, Integer> searchCount;
    private boolean isLocked;
    private Integer numUsages;
    private String logName;

    public SearchLog(String logName) {
        searchHistory = new ArrayList<>();
        searchCount = new HashMap<>();
        this.logName = logName;
        numUsages = 0;
        isLocked = false;
    }

    public List<String> handleSearch(String text, List<SearchComponent> searchComponents) {
        List<String> results = new ArrayList<>();

        for (SearchComponent component : searchComponents) {
            results.addAll(component.search(text));
        }

        addSearchHistory(text);
        setNumUsages(getNumUsages() + 1);
        results.add("\nLogged in: " + getLogName());
        return results;
    }

    public List<String> handleMaterialSearch(String text) {
        List<String> results = new ArrayList<>();
        results.addAll(StudyMaterial.getStudyMaterial().searchInMaterials(text));
        this.addSearchHistory(text);
        this.setNumUsages(this.getNumUsages() + 1);
        results.add("\nLogged in: " + this.getLogName());
        return results;
    }

    public void addSearchHistory(String searchHistory) {
        this.searchHistory.add(searchHistory);
    }

    public List<String> getSearchHistory() {
        return searchHistory;
    }

    public void setSearchHistory(List<String> searchHistory) {
        this.searchHistory = searchHistory;
    }

    public Map<String, Integer> getSearchCount() {
        return searchCount;
    }

    public void setSearchCount(Map<String, Integer> searchCount) {
        this.searchCount = searchCount;
    }

    public boolean isLocked() {
        return isLocked;
    }

    public void setLocked(boolean locked) {
        isLocked = locked;
    }

    public Integer getNumUsages() {
        return numUsages;
    }

    public void setNumUsages(Integer numUsages) {
        this.numUsages = numUsages;
    }

    public String getLogName() {
        return logName;
    }

    public void setLogName(String logName) {
        this.logName = logName;
    }
}