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
    private int reservationLimit;
    private boolean isMaleFriendly;
    private boolean isFemaleFriendly;
    private AgeGroup ageGroup;

    public DataItem(int k, String n, String c, String r, double longit, double lat, String a, String sn, String pn, int rl) {
        key = k;
        name = n;
        capacity = c;
        restrictions = r;
        longitude = longit;
        latitude = lat;
        address = a;
        special_notes = sn;
        phone_number = pn;
        reservationLimit = rl;
        isMaleFriendly = true;
        isFemaleFriendly = true;
        ageGroup = AgeGroup.Anyone;
        if (r.contains("Men")) {
            isFemaleFriendly = false;
        } else if (r.contains("Women")) {
            isMaleFriendly = false;
        }
        if (r.toLowerCase().contains("newborn")) {
            ageGroup = AgeGroup.Newborns;
        }
        if (r.toLowerCase().contains("children")) {
            ageGroup = AgeGroup.Children;
        }
        if (r.toLowerCase().contains("young adult")) {
            ageGroup = AgeGroup.YoungAdults;
        }
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

    public int getReservationLimit() {return reservationLimit; }

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