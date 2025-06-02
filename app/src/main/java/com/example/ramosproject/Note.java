package com.example.ramosproject;

public class Note {
    private String title;
    private String name;
    private String days;
    private String description;

    public Note(String title, String name, String days, String description) {
        this.title = title;
        this.name = name;
        this.days = days;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public String getName() {
        return name;
    }

    public String getDays() {
        return days;
    }

    public String getDescription() {
        return description;
    }
}
