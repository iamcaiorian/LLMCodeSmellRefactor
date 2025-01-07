package org.example.studysearch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchLog {
    private final List<String> searchHistory;
    private final Map<String, Integer> searchCount;
    private boolean isLocked;
    private int numUsages;
    private String logName;

    public SearchLog(String logName) {
        this.searchHistory = new ArrayList<>();
        this.searchCount = new HashMap<>();
        this.logName = logName;
        this.numUsages = 0;
        this.isLocked = false;
    }

    // Mantendo o método original para compatibilidade
    public void addSearchHistory(String searchTerm) {
        if (isLocked) {
            throw new IllegalStateException("Cannot add search to a locked log");
        }

        searchHistory.add(searchTerm);
        searchCount.merge(searchTerm, 1, Integer::sum);
    }

    // Mantendo o setter do numUsages para compatibilidade
    public void setNumUsages(int numUsages) {
        this.numUsages = numUsages;
    }

    public int getSearchFrequency(String searchTerm) {
        return searchCount.getOrDefault(searchTerm, 0);
    }

    public List<String> getSearchHistory() {
        return Collections.unmodifiableList(searchHistory);
    }

    public Map<String, Integer> getSearchStatistics() {
        return Collections.unmodifiableMap(searchCount);
    }

    public String getMostFrequentSearch() {
        if (searchCount.isEmpty()) {
            return null;
        }

        return Collections.max(searchCount.entrySet(), Map.Entry.comparingByValue()).getKey();
    }

    public void clearHistory() {
        if (isLocked) {
            throw new IllegalStateException("Cannot clear a locked log");
        }

        searchHistory.clear();
        searchCount.clear();
        numUsages = 0;
    }

    public void lock() {
        isLocked = true;
    }

    public void unlock() {
        isLocked = false;
    }

    public boolean isLocked() {
        return isLocked;
    }

    public int getNumUsages() {
        return numUsages;
    }

    public String getLogName() {
        return logName;
    }

    public void setLogName(String logName) {
        if (logName == null || logName.trim().isEmpty()) {
            throw new IllegalArgumentException("Log name cannot be null or empty");
        }
        this.logName = logName;
    }
}