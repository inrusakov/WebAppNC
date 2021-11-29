package com.example.model.Traveling.Journey;

public enum JourneyRole {
    participant("Участник", true),
    editor("Редактор", true),
    admin("Администратор", true),
    creator("Создатель", false);

    private final String title;
    private final Boolean canUseInForms;

    JourneyRole(String title, Boolean canUseInForms){
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
