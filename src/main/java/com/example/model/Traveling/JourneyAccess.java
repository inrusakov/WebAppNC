package com.example.model.Traveling;

public enum JourneyAccess {

    PUBLIC("Открытые"),
    PRIVATE("Приватные");

    private final String title;

    JourneyAccess(String title){
        this.title = title;
    }

    public String getTitle(){
        return this.title;
    }
}
