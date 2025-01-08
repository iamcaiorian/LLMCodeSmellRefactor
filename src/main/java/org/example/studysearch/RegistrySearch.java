package org.example.studysearch;

import java.util.List;

public class RegistrySearch implements Search<String>{
    private SearchLog searchLog = new SearchLog("Registry Search");

    public RegistrySearch(){}

    @Override
    public List<String> search(String text) {
        return searchLog.handleRegistrySearch(text);
    }

    public SearchLog getSearchLog() {
        return searchLog;
    }
}