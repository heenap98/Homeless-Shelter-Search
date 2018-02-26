package com.example.heenapatel.m4;

/**
 * Created by heenapatel on 2/26/18.
 */

public class DataItem {
    private int key;
    private String name;
    private String capacity;
    private String restrictions;
    private int longitude;
    private int latitude;
    private String address;
    private String special_notes;
    private String phone_number;

    public DataItem(int k, String n, String c, String r, int longit, int lat, String a, String sn, String pn) {
        key = k;
        name = n;
        capacity = c;
        restrictions = r;
        longitude = longit;
        latitude = lat;
        address = a;
        special_notes = sn;
        phone_number = pn;
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

    public String getCapacity() {
        return capacity;
    }

    public String getRestrictions() {
        return restrictions;
    }

    public int getLongitude() {
        return longitude;
    }

    public int getLatitude() {
        return latitude;
    }

    public String getAddress() {
        return address;
    }

    public String getSpecial_notes() {
        return special_notes;
    }

    public String getPhone_number() {
        return phone_number;
    }
}