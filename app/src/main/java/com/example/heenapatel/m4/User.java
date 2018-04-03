package com.example.heenapatel.m4;

import java.io.Serializable;

/**
 * Created by ves on 3/14/18.
 */

public class User implements Serializable {
    public String username;
    public String password; //we're good at security
    public int bookedShelterId;
    public int bookedNumber;
    public boolean hasReservation;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
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

    public boolean getHasReservation() { return hasReservation; }

    public void setHasReservation(boolean reservation) { this.hasReservation = reservation; }

}
