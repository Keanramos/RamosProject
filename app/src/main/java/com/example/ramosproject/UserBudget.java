package com.example.ramosproject;

public class UserBudget {
    private String name;
    private String title;
    private String daysOfSchool;
    private String description;

    // Required for Firebase
    public UserBudget() {
    }

    public UserBudget(String name, String title, String daysOfSchool, String description) {
        this.name = name;
        this.title = title;
        this.daysOfSchool = daysOfSchool;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }

    public String getDaysOfSchool() {
        return daysOfSchool;
    }

    public String getDescription() {
        return description;
    }
}
