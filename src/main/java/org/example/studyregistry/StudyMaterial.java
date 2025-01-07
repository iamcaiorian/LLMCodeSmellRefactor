package org.example.studyregistry;
import org.example.studymaterial.AudioReference;
import org.example.studymaterial.Reference;
import org.example.studymaterial.TextReference;
import org.example.studymaterial.VideoReference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudyMaterial {
    List<Reference> references;
    private static StudyMaterial studyMaterial;
    private Map<String, Integer> referenceCount;

    private StudyMaterial() {
        references = new ArrayList<Reference>();
    }

    public static StudyMaterial getStudyMaterial() {
        if (studyMaterial == null) {
            studyMaterial = new StudyMaterial();
        }
        return studyMaterial;
    }

    public void addReference(Reference ref) {
        references.add(ref);
    }

    List<Reference> getReferences() {
        return references;
    }

    public List<Reference> getTypeReference(Reference type) {
        List<Reference> response = new ArrayList<>();
        for (Reference reference : references) {
            if (reference.getClass() == type.getClass()) {
                response.add(reference);
            }
        }
        return response;
    }

    public void setReferenceCount(Map<String, Integer> referenceCount) {
        this.referenceCount = referenceCount;
    }

    public List<String> searchInMaterials(String text) {
        List<String> response = new ArrayList<>();
        for (Reference reference : references) {
            String mix = (reference.getTitle() != null ? reference.getTitle() : "") +
                    (reference.getDescription() != null ? reference.getDescription() : "");
            if (mix.toLowerCase().contains(text.toLowerCase())) {
                response.add(reference.getTitle());
            }
        }
        return response;
    }

    public Map<String, Integer> getReferenceCountMap() {
        Map<String, Integer> response = initializeReferenceCountMap();
        countReferences(response);
        setReferenceCount(response);
        return response;
    }

    private Map<String, Integer> initializeReferenceCountMap() {
        Map<String, Integer> response = new HashMap<>();
        response.put("Audio References", 0);
        response.put("Video References", 0);
        response.put("Text References", 0);
        return response;
    }

    private void countReferences(Map<String, Integer> countMap) {
        for (Reference reference : references) {
            updateReferenceCount(countMap, reference);
        }
    }

    private void updateReferenceCount(Map<String, Integer> countMap, Reference reference) {
        if (reference instanceof AudioReference) {
            incrementCount(countMap, "Audio References");
        } else if (reference instanceof VideoReference) {
            updateVideoReferenceCount(countMap, (VideoReference) reference);
        } else if (reference instanceof TextReference) {
            updateTextReferenceCount(countMap, (TextReference) reference);
        }
    }

    private void updateVideoReferenceCount(Map<String, Integer> countMap, VideoReference video) {
        if (video.handleStreamAvailability()) {
            incrementCount(countMap, "Video References");
        }
    }

    private void updateTextReferenceCount(Map<String, Integer> countMap, TextReference text) {
        if (text.handleTextAccess()) {
            incrementCount(countMap, "Text References");
        }
    }

    private void incrementCount(Map<String, Integer> countMap, String referenceType) {
        countMap.put(referenceType, countMap.get(referenceType) + 1);
    }
}