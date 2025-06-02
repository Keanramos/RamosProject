package com.example.ramosproject;

public class User {
    public String username;
    public String password;

    public User() {
        // Required for Firebase
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}