package com.example.heenapatel.m4;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import java.security.PublicKey;

public class ReserveBedActivity extends AppCompatActivity {

    public int familyNumber;
    public int apartmentNumber;
    public int roomNumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve_bed);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        RadioButton Family_zero = (RadioButton) findViewById(R.id.FamilyZero);
        RadioButton Family_one = (RadioButton) findViewById(R.id.FamilyOne);
        RadioButton Apartment_zero = (RadioButton) findViewById(R.id.ApartZero);
        RadioButton Apartment_one = (RadioButton) findViewById(R.id.ApartOne);
        RadioButton Room_one = (RadioButton) findViewById(R.id.RoomOne);
        RadioButton Room_two = (RadioButton) findViewById(R.id.RoomTwo);
        RadioButton Room_three = (RadioButton) findViewById(R.id.RoomThree);
        RadioButton Room_four = (RadioButton) findViewById(R.id.RoomFour);
        RadioButton Room_zero = (RadioButton) findViewById(R.id.RoomZero);

        setSupportActionBar(toolbar);
        int userID = getIntent().getIntExtra("userID", 0);

        Family_zero.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                familyNumber = 0;
            }
        });


        Family_one.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                familyNumber = 1;
            }
        });


        Apartment_zero.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                apartmentNumber = 0;
            }
        });


        Apartment_one.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                apartmentNumber = 1;
            }
        });


        Room_zero.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                roomNumber = 0;
            }
        });

        Room_one.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                roomNumber = 1;
            }
        });

        Room_two.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                roomNumber = 2;
            }
        });

        Room_three.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                roomNumber = 3;
            }
        });


        Room_four.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                roomNumber = 4;
            }
        });





    }



}
