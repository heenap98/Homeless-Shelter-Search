package com.example.heenapatel.m4;

/**
 * Created by heenapatel on 2/26/18.
 */

public class DataItem {

    private int key;
    private String name;
    private String capacity;
    private String restrictions;
    private double longitude;
    private double latitude;
    private String address;
    private String special_notes;
    private String phone_number;
    private boolean isMaleFriendly;
    private boolean isFemaleFriendly;
    private AgeGroup ageGroup;

    public DataItem(int k, String n, String c, String r, double longit, double lat, String a, String sn, String pn) {
        key = k;
        name = n;
        capacity = c;
        restrictions = r;
        longitude = longit;
        latitude = lat;
        address = a;
        special_notes = sn;
        phone_number = pn;
        isMaleFriendly = true;
        isFemaleFriendly = true;
        ageGroup = AgeGroup.Anyone;
    }

    public DataItem(int k, String n, String c, String r, double longit, double lat, String a,
                    String sn, String pn, boolean m, boolean f) {
        key = k;
        name = n;
        capacity = c;
        restrictions = r;
        longitude = longit;
        latitude = lat;
        address = a;
        special_notes = sn;
        phone_number = pn;
        isMaleFriendly = m;
        isFemaleFriendly = f;
        ageGroup = AgeGroup.Anyone;
    }

    public DataItem(int k, String n, String c, String r, double longit, double lat, String a,
                    String sn, String pn, AgeGroup ag) {
        key = k;
        name = n;
        capacity = c;
        restrictions = r;
        longitude = longit;
        latitude = lat;
        address = a;
        special_notes = sn;
        phone_number = pn;
        isMaleFriendly = true;
        isFemaleFriendly = true;
        ageGroup = ag;
    }

    public DataItem(int k, String n, String c, String r, double longit, double lat, String a, String sn,
                    String pn, boolean m, boolean f, AgeGroup ag) {
        key = k;
        name = n;
        capacity = c;
        restrictions = r;
        longitude = longit;
        latitude = lat;
        address = a;
        special_notes = sn;
        phone_number = pn;
        isMaleFriendly = m;
        isFemaleFriendly = f;
        ageGroup = ag;
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

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
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

    public boolean getMaleFriendly() {
        return isMaleFriendly;
    }

    public boolean getFemaleFriendly() {
        return isFemaleFriendly;
    }

    public AgeGroup getAgeGroup() {
        return ageGroup;
    }
}