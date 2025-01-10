package org.example.studymaterial;

public abstract class Reference {
    private String title;
    private String description;
    private String link;
    private String accessRights;
    private String license;
    private boolean isDownloadable;
    private int rating;
    private String language;
    private int viewCount;
    private int downloadCount;
    private int shareCount;

    protected Reference() {
        initializeDefaults();
    }

    private void initializeDefaults() {
        this.rating = 0;
        this.viewCount = 0;
        this.downloadCount = 0;
        this.shareCount = 0;
        this.accessRights = "Private";
    }

    // Comportamentos de negócio
    public void incrementViews() {
        this.viewCount++;
        notifyAccess();
    }

    public void registerDownload() {
        if (canDownload()) {
            this.downloadCount++;
            notifyAccess();
        }
    }

    public void registerShare() {
        if (isShareable()) {
            this.shareCount++;
            notifyAccess();
        }
    }

    // Regras de negócio
    public boolean canDownload() {
        return isDownloadable && isAccessible();
    }

    public boolean isAccessible() {
        return "Public".equalsIgnoreCase(accessRights);
    }

    public boolean isShareable() {
        return isAccessible() && license != null;
    }

    public double getEngagementScore() {
        return (viewCount * 0.4) + (downloadCount * 0.3) + (shareCount * 0.3);
    }

    protected void notifyAccess() {
        // Template method para subclasses
    }

    // Getters - mantidos públicos para compatibilidade com testes
    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getLink() {
        return link;
    }

    public String getAccessRights() {
        return accessRights;
    }

    public String getLicense() {
        return license;
    }

    public boolean getIsDownloadable() {
        return isDownloadable;
    }

    public int getRating() {
        return rating;
    }

    public String getLanguage() {
        return language;
    }

    public int getViewCount() {
        return viewCount;
    }

    public int getShareCount() {
        return shareCount;
    }

    public void setTitle(String title) {
        if (title != null) {
            this.title = title;
            notifyUpdate();
        }
    }

    public void setDescription(String description) {
        this.description = description;
        notifyUpdate();
    }

    public void setLink(String link) {
        if (link != null) {
            this.link = link;
            notifyUpdate();
        }
    }

    public void setAccessRights(String accessRights) {
        this.accessRights = accessRights;
        notifyUpdate();
    }

    public void setLicense(String license) {
        this.license = license;
        notifyUpdate();
    }

    public void setDownloadable(boolean downloadable) {
        this.isDownloadable = downloadable;
        notifyUpdate();
    }

    public void setRating(int rating) {
        this.rating = rating;
        notifyUpdate();
    }

    public void setLanguage(String language) {
        this.language = language;
        notifyUpdate();
    }

    public void setViewCount(int viewCount) {
        if (viewCount >= 0) {
            this.viewCount = viewCount;
            notifyUpdate();
        }
    }

    public void setShareCount(int shareCount) {
        if (shareCount >= 0) {
            this.shareCount = shareCount;
            notifyUpdate();
        }
    }

    protected void notifyUpdate() {
    }

    public String getMetadata() {
        return String.format("%s (%s) - %s - Rating: %d/5",
                title,
                language != null ? language : "No language",
                accessRights,
                rating);
    }

    public boolean validate() {
        return title != null && !title.trim().isEmpty() &&
                link != null && !link.trim().isEmpty();
    }
}