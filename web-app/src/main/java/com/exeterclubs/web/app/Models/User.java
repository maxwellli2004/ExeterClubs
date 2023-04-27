package com.exeterclubs.web.app.Models;

import java.util.UUID;

public class User {
    public String email;
    public String password;
    public UUID id;
//    public UUID[] member;
//    public UUID[] head;
    private boolean admin;
    //TODO: Add events

    public User(String email, String password, UUID id, boolean admin) {
        this.email = email;
        this.password = password;
        this.id = id;
//        this.member = member;
//        this.head = head;
        this.admin = admin;
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

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
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