package com.example.heenapatel.m4;

/**
 * Created by ves on 3/14/18.
 */

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;


public class DatabaseHandler extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "shelterManager";

    private static final String TABLE_USERS = "users";


    // UsersTable Columns names
    private static final String U_ID = "id";
    private static final String U_USERNAME = "username";
    private static final String U_PASSWORD = "password";
    private static final String U_EMAIL = "email";
    private static final String U_BOOKED_SHELTERID = "bookedShelterID";
    private static final String U_BOOKED_NUMBER = "bookedNumber";
    private static final String U_BOOKED_TYPE = "bookedType";


    // Shelterstable name
    private static final String TABLE_SHELTERS = "shelters";

// FORGOT ADDRESS - INCLUDE !!!!!!!!

    // Shelters Table Columns names
    private static final String S_ID = "id";
    private static final String S_NAME = "name";
    private static final String S_LAT = "lat";
    private static final String S_LON = "lon";
    private static final String S_NOTES = "notes";
    private static final String S_PHONE = "phone";
    private static final String S_RESTRICTIONS = "restrictons";
    private static final String S_BED_CAPACITY = "bedCapacity";
    private static final String S_ROOM_CAPACITY = "roomCapacity";
    private static final String S_BED_VACANCY = "bedVacancy";
    private static final String S_ROOM_VACANCY = "roomVacancy";
    private static final String S_ADDRESS = "address";


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        Log.d("oncreate", "happenend");
        String CreateUserTable = "CREATE TABLE TABLE_USERS" + " ( " + U_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + U_USERNAME + " VARCHAR2(50), " + U_PASSWORD + " VARCHAR2(50)," + U_EMAIL + " VARCHAR2(50)," + U_BOOKED_SHELTERID + " INT," + U_BOOKED_NUMBER + " INT," + U_BOOKED_TYPE + " VARCHAR2(30) )";
        db.execSQL(CreateUserTable);

        String CreateShelterTable = "CREATE TABLE TABLE_SHELTERS " + " ( " +
                S_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                S_NAME + " VARCHAR2(50), " +
                S_LAT + " VARCHAR2(50), " +
                S_LON + " VARCHAR2(50), " +
                S_NOTES + " TEXT," +
                S_PHONE + " VARCHAR2(50)," +
                S_RESTRICTIONS + " VARCHAR2(100), " +
                S_BED_CAPACITY + " INT," +
                S_ROOM_CAPACITY + " INT," +
                S_BED_VACANCY + " INT, " +
                S_ROOM_VACANCY + " INT, " +
                S_ADDRESS + " VARCHAR2(100))";
        db.execSQL(CreateShelterTable);


//        this.addShelter(new Shelter("My Sister's House", "33.780174", "-84.410142", "Temporary, Emergency", "860-234-2345", "Women/Children", 264, 0, 264, 0, "address of Sisters home"));

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d("onupdate", "happenend");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SHELTERS);
        onCreate(db);
    }




////All CRUD operations we might need for users db

    // Getting single User
    User getUser(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_USERS, new String[]{U_ID, U_USERNAME, U_PASSWORD, U_EMAIL, U_BOOKED_SHELTERID, U_BOOKED_NUMBER, U_BOOKED_TYPE}, U_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        User user = new User(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2), cursor.getString(3), Integer.parseInt(cursor.getString(4)), Integer.parseInt(cursor.getString(5)), cursor.getString(6));

        cursor.close();
        return user;
    }


    // Getting All Users
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<User>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_USERS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setId(Integer.parseInt(cursor.getString(0)));
                user.setUsername(cursor.getString(1));
                user.setPassword(cursor.getString(2));
                user.setEmail(cursor.getString(3));
                user.setBookedShelterId(Integer.parseInt(cursor.getString(4)));
                user.setBookedNumber(Integer.parseInt(cursor.getString(5)));
                user.setBookedType(cursor.getString(6));
                // Adding User to list
                userList.add(user);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return userList;
    }


    // Updating single User
    public int updateUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(U_USERNAME, user.getUsername());
        values.put(U_PASSWORD, user.getPassword());
        values.put(U_EMAIL, user.getEmail());
        values.put(U_BOOKED_SHELTERID, user.getBookedShelterId());
        values.put(U_BOOKED_NUMBER, user.getBookedNumber());
        values.put(U_BOOKED_TYPE, user.getBookedType());

        // updating row
        return db.update(TABLE_USERS, values, U_ID + " = ?",
                new String[] { String.valueOf(user.getId()) });
    }

    // Inserting single User
    public void insertUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

//        Log.d("q", user.getUsername());

        ContentValues values = new ContentValues();
        values.put(U_USERNAME, user.getUsername());
        values.put(U_PASSWORD, user.getPassword());
        values.put(U_EMAIL, user.getEmail());
        values.put(U_BOOKED_SHELTERID, user.getBookedShelterId());
        values.put(U_BOOKED_NUMBER, user.getBookedNumber());
        values.put(U_BOOKED_TYPE, user.getBookedType());

        // updating row
        db.insert(TABLE_USERS, null, values);
        db.close();
    }


    // Inserting single User
    public void insert2User(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        SQLiteStatement stmt = db.compileStatement("INSERT INTO users(bookedShelterID,bookedNumber,password,bookedType,email,username) VALUES (?,?,?,?,?,?)");
        stmt.bindString(1, String.valueOf(user.getBookedShelterId()));
        stmt.bindLong(2, user.getBookedNumber());
        stmt.bindString(3, user.getPassword());
        stmt.bindString(4, user.getBookedType());
        stmt.bindString(5, user.getEmail());
        stmt.bindString(6, user.getUsername());
        stmt.execute();
        stmt.close();
        db.close();
    }



    // Deleting single User
    public void deleteUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USERS, U_ID + " = ?",
                new String[] { String.valueOf(user.getId()) });
        db.close();
    }

    // Getting Users Count
    public int getUsersCount() {
        String countQuery = "SELECT  * FROM " + TABLE_USERS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();   //NOTE  is close before getCount ok?
    }



////All CRUD operations we might need for Shelters db
    // Adding new SHELTER
    void addShelter(Shelter shelter) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(S_NAME, shelter.getName());
        values.put(S_LAT, shelter.getLat());
        values.put(S_LON, shelter.getLon());
        values.put(S_NOTES, shelter.getNotes());
        values.put(S_PHONE, shelter.getPhone());
        values.put(S_RESTRICTIONS, shelter.getRestrictions());
        values.put(S_BED_CAPACITY, shelter.getBedCapacity());
        values.put(S_ROOM_CAPACITY, shelter.getRoomCapacity());
        values.put(S_BED_VACANCY, shelter.getBedVacancy());
        values.put(S_ROOM_VACANCY, shelter.getRoomVacancy());



        // Inserting Row
        db.insert(TABLE_SHELTERS, null, values);
        db.close(); // Closing database connection
    }

    // Getting single Shelter
    Shelter getShelter(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_SHELTERS, new String[] { S_ID, S_NAME, S_LAT, S_LON, S_NOTES, S_RESTRICTIONS, S_BED_CAPACITY, S_ROOM_CAPACITY, S_BED_VACANCY, S_ROOM_VACANCY }, S_ID + "=?", new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Shelter shelter = new Shelter(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), Integer.parseInt(cursor.getString(7)), Integer.parseInt(cursor.getString(8)), Integer.parseInt(cursor.getString(9)), Integer.parseInt(cursor.getString(10)), cursor.getString(11) );
        cursor.close();
        return shelter;
    }

    // Getting All Shelters
    public List<Shelter> getAllShelters() {
        List<Shelter> ShelterList = new ArrayList<Shelter>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_SHELTERS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Shelter shelter = new Shelter();
                shelter.setKey(Integer.parseInt(cursor.getString(0)));
                shelter.setName(cursor.getString(1));
                shelter.setLat(cursor.getString(2));
                shelter.setLon(cursor.getString(3));
                shelter.setNotes(cursor.getString(4));
                shelter.setPhone(cursor.getString(5));
                shelter.setRestrictions(cursor.getString(6));
                shelter.setBedCapacity(Integer.parseInt(cursor.getString(7)));
                shelter.setRoomCapacity(Integer.parseInt(cursor.getString(8)));
                shelter.setBedVacancy(Integer.parseInt(cursor.getString(9)));
                shelter.setRoomVacancy(Integer.parseInt(cursor.getString(10)));
                // Adding Shelter to list
                ShelterList.add(shelter);
            } while (cursor.moveToNext());
        }

        // return Shelter list
        cursor.close();
        return ShelterList;
    }

    // Updating single Shelter
    public int updateShelter(Shelter shelter) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(S_NAME, shelter.getName());
        values.put(S_LAT, shelter.getLat());
        values.put(S_LON, shelter.getLon());
        values.put(S_NOTES, shelter.getNotes());
        values.put(S_PHONE, shelter.getPhone());
        values.put(S_RESTRICTIONS, shelter.getRestrictions());
        values.put(S_BED_CAPACITY, shelter.getBedCapacity());
        values.put(S_ROOM_CAPACITY, shelter.getRoomCapacity());
        values.put(S_BED_VACANCY, shelter.getBedVacancy());
        values.put(S_ROOM_VACANCY, shelter.getRoomVacancy());


        // updating row
        return db.update(TABLE_SHELTERS, values, S_ID + " = ?",
            new String[] { String.valueOf(shelter.getKey()) });
    }

    // Deleting single Shelter
    public void deleteShelter(Shelter shelter) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_SHELTERS, S_ID + " = ?",
                new String[] { String.valueOf(shelter.getKey()) });
        db.close();
    }

    // Getting Shelters Count
    public int getSheltersCount() {
        String countQuery = "SELECT  * FROM " + TABLE_SHELTERS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }

}