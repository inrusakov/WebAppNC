package com.example.model.Traveling.Journey;

public enum JourneyStatus {
    UPCOMING("Планируется", true),
    UNDER_WAY("Проходит", true),
    OVER("Закончилось", true),
    NONE("", false),
    STATIC("Статичное", false);

    private final String title;
    private final Boolean canUseInForms;

    JourneyStatus(String title, Boolean canUseInForms){
        this.title = title;
        this.canUseInForms = canUseInForms;
    }

    public Boolean CanUseInForms() {
        return canUseInForms;
    }

    public String getTitle() {
        return title;
    }
}
