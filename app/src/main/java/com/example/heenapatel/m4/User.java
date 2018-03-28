package com.example.heenapatel.m4;

/**
 * Created by ves on 3/14/18.
 */

public class User {
    public int id;
    public String username;
    public String password; //we're good at security
    public String email;
    public int bookedShelterId;
    public int bookedNumber;
    public String bookedType;

    public User(int id, String username, String password, String email, int bookedShelterId, int bookedNumber, String bookedType) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.bookedShelterId = bookedShelterId;
        this.bookedNumber = bookedNumber;
        this.bookedType = bookedType;
    }

    public User(int id, String username, String password, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.bookedShelterId = 0;
        this.bookedNumber = 0;
        this.bookedType = "";
    }

    public User() {
        id = 0;
        username = "";
        password = "";
        email = "";
        bookedShelterId = 0;
        bookedNumber = 0;
        bookedType = "";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getBookedShelterId() {
        return bookedShelterId;
    }

    public void setBookedShelterId(int bookedShelterId) {
        this.bookedShelterId = bookedShelterId;
    }

    public int getBookedNumber() {
        return bookedNumber;
    }

    public void setBookedNumber(int bookedNumber) {
        this.bookedNumber = bookedNumber;
    }

    public String getBookedType() {
        return bookedType;
    }

    public void setBookedType(String bookedType) {
        this.bookedType = bookedType;
    }

}
