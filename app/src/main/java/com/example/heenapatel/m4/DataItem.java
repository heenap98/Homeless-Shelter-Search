package com.example.heenapatel.m4;

/**
 * Created by heenapatel on 2/26/18.
 */

public class DataItem {

    private int key;
    private String name;
    private String[] capacityDescriptions;
    private String restrictions;
    private double longitude;
    private double latitude;
    private String address;
    private String special_notes;
    private String phone_number;
    private int[] intCapacity;
    private boolean isMaleFriendly;
    private boolean isFemaleFriendly;
    private AgeGroup ageGroup;

    public DataItem(int k, String n, String[] capDescription, String r, double longit, double lat, String a, String sn, String pn, int[] cap) {
        key = k;
        name = n;
        capacityDescriptions = capDescription;
        restrictions = r;
        longitude = longit;
        latitude = lat;
        address = a;
        special_notes = sn;
        phone_number = pn;
        intCapacity = cap;
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

    public DataItem(int k, String n, String r, double longit, double lat, String a, String sn, String pn) {
        this(k, n, null, r, longit, lat, a, sn, pn, null);
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
        String fullCapacity = "";
        for (int i = 0; i < intCapacity.length; i++) {
            System.out.println(capacityDescriptions[i]);
            fullCapacity += Integer.toString(intCapacity[i]) + capacityDescriptions[i] + '\n';
        }
        return fullCapacity;
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

    public int[] getIntCapacity() { return intCapacity; }

    public void setIntCapacity(int[] cap) {intCapacity = cap;}
}