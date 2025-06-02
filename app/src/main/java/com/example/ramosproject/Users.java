package com.example.ramosproject;

public class Users {
    private String name;
    private String title;
    private String days_of_school;
    private String description;

    // Required empty constructor (for Firebase)
    public Users() {
    }

    // Full constructor
    public Users(String name, String title, String days_of_school, String description) {
        this.name = name;
        this.title = title;
        this.days_of_school = days_of_school;
        this.description = description;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDays_of_school() {
        return days_of_school;
    }

    public void setDays_of_school(String days_of_school) {
        this.days_of_school = days_of_school;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
