package com.exeterclubs.web.app.Models;

import java.util.UUID;

public class User {
    private String email;
    private String password;
    public String id;
//    public UUID[] member;
//    public UUID[] head;
    private boolean admin = false;
    //TODO: Add events

    public User() { }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
        this.id = UUID.randomUUID().toString();
    }

    public User(String email, String id, String password) {
        this.email = email;
        this.password = password;
        this.id = id;
    }
    
    // MARK: Getters + Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /*
    public UUID[] getMember() {
        return member;
    }

    public void setMember(UUID[] member) {
        this.member = member;
    }

    public UUID[] getHead() {
        return head;
    }

    public void setHead(UUID[] head) {
        this.head = head;
    }
    */
    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
}