package org.example.studymaterial;

import java.util.List;

public class AudioReference extends Reference {
    public enum AudioQuality {
        LOW, MEDIUM, HIGH, VERY_HIGH;
    }

    private AudioQuality audioQuality;

    public AudioReference(AudioQuality quality) {
        this.audioQuality = quality;
    }

    public AudioQuality getAudioQuality() {
        return audioQuality;
    }

    public static AudioQuality audioQualityAdapter(String quality) {
        return switch (quality.toLowerCase()) {
            case "low" -> AudioQuality.LOW;
            case "medium" -> AudioQuality.MEDIUM;
            case "high" -> AudioQuality.HIGH;
            case "very_high" -> AudioQuality.VERY_HIGH;
            default -> null;
        };
    }

    public void setAudioQuality(AudioQuality audioQuality) {
        this.audioQuality = audioQuality;
    }

    // Record to encapsulate audio attributes
    public record AudioAttributes(
            AudioQuality audioQuality,
            boolean isDownloadable,
            String title,
            String description,
            String link,
            String accessRights,
            String license,
            String language,
            int rating,
            int viewCount,
            int shareCount
    ) {}

    public void editAudio(AudioAttributes attributes) {
        editBasic(attributes.title(), attributes.description(), attributes.link());
        this.setAccessRights(attributes.accessRights());
        this.setLicense(attributes.license());
        this.setAudioQuality(attributes.audioQuality());
        editVideoAttributes(attributes);
    }

    public void editAudioAdapter(List<String> properties, List<Integer> intProperties, AudioQuality audioQuality,
                                 boolean isDownloadable) {
        AudioAttributes attributes = new AudioAttributes(
                audioQuality,
                isDownloadable,
                properties.get(0),
                properties.get(1),
                properties.get(2),
                properties.get(3),
                properties.get(4),
                properties.get(5),
                intProperties.get(0),
                intProperties.get(1),
                intProperties.get(2)
        );
        this.editAudio(attributes);
    }

    private void editVideoAttributes(AudioAttributes attributes) {
        this.setRating(attributes.rating());
        this.setShareCount(attributes.shareCount());
        this.setViewCount(attributes.viewCount());
        this.setDownloadable(attributes.isDownloadable());
        this.setLanguage(attributes.language());
    }

    public void editBasic(String title, String description, String link) {
        this.setTitle(title);
        this.setDescription(description);
        this.setLink(link);
    }
}