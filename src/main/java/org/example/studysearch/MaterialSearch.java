package org.example.studysearch;

import org.example.studyregistry.StudyMaterial;

import java.util.List;

public class MaterialSearch implements Search<String> {

    private SearchLog searchLog = new SearchLog("Material Search");

    public MaterialSearch() {}

    @Override
    public List<String> search(String text) {
        return searchLog.handleMaterialSearch(text);
    }

    public SearchLog getSearchLog() {
        return searchLog;
    }
}