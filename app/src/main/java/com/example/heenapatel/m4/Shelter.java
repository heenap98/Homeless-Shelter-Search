package com.example.heenapatel.m4;

/**
 * Created by ves on 3/15/18.
 */

public class Shelter {

    private int key;
    private String name;
    private String lat;
    private String lon;
    private String notes;
    private String phone;
    private String restrictions;
    private int bedCapacity;
    private int roomCapacity;
    private int bedVacancy;
    private int roomVacancy;
    private String address;

    public Shelter(int key, String name, String lat, String lon, String notes, String phone, String restrictions, int bedCapacity, int roomCapacity, int bedVacancy, int roomVacancy, String address) {
        this.key = key;
        this.name = name;
        this.lat = lat;
        this.lon = lon;
        this.notes = notes;
        this.phone = phone;
        this.restrictions = restrictions;
        this.bedCapacity = bedCapacity;
        this.roomCapacity = roomCapacity;
        this.bedVacancy = bedVacancy;
        this.roomVacancy = roomVacancy;
        this.address = address;

    }

    public Shelter() {
        key = 0;
        name = "";
        lat = "";
        lon = "";
        notes = "";
        phone = "";
        restrictions = "";
        bedCapacity = 0;
        roomCapacity = 0;
        bedVacancy = 0;
        roomVacancy = 0;
    }

    public String toString() {
        return name;
    }

    public int getKey() {
        return key;
    }

    public String getName() {
        return name;
    }

    public String getLat() {
        return lat;
    }

    public String getLon() {
        return lon;
    }

    public String getNotes() {

        return notes;
    }

    public String getPhone() {

        return phone;
    }

    public String getRestrictions() {
        return restrictions;
    }

    public int getBedCapacity() {
        return bedCapacity;
    }

    public int getRoomCapacity() {
        return roomCapacity;
    }

    public int getBedVacancy() {
        return bedVacancy;
    }

    public int getRoomVacancy() {
        return roomVacancy;
    }

    public String getAddress() {
        return address;
    }




    public void setKey(int key) {
        this.key = key;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLat(String lat) {
        this.name = name;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public void setNotes(String notes) {

        this.notes =  notes;
    }

    public void setPhone(String phone) {

        this.phone = phone;
    }

    public void setRestrictions(String restrictions) {
        this.restrictions = restrictions;
    }

    public void setBedCapacity(int bedCapacity) {
        this.bedCapacity = bedCapacity;
    }

    public void setRoomCapacity(int roomCapacity) {
        this.roomCapacity = roomCapacity;
    }

    public void setBedVacancy(int bedVacancy) {
        this.bedVacancy = bedVacancy;
    }

    public void setRoomVacancy(int roomVacancy) {
        this.roomVacancy = roomVacancy;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
