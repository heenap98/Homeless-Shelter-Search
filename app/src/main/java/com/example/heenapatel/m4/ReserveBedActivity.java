package com.example.heenapatel.m4;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import java.util.List;

import java.security.PublicKey;

public class ReserveBedActivity extends AppCompatActivity {

    public int familyNumber = 0;
    public int apartmentNumber = 0;
    public int roomNumber = 0;
    public int familyAvailable;
    public int apartmentAvailable;
    public int roomAvailable;
    public String shelterName;
    public int[] capacityArray;
    SimpleModel shelters = MainActivity.model;


    public static boolean reservationPlaced;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        reservationPlaced = getIntent().getBooleanExtra("reservationPlaced", false);
        Log.d("reservation", "" + reservationPlaced);

        shelterName = getIntent().getStringExtra("shelterName");
        setContentView(R.layout.activity_reserve_bed);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        Button confirm = (Button) findViewById(R.id.confirmButton);
        final TextView errorMsg = (TextView) findViewById(R.id.errorReserve);
        RadioButton Family_zero = (RadioButton) findViewById(R.id.FamilyZero);
        RadioButton Family_one = (RadioButton) findViewById(R.id.FamilyOne);
        RadioButton Apartment_zero = (RadioButton) findViewById(R.id.ApartZero);
        RadioButton Apartment_one = (RadioButton) findViewById(R.id.ApartOne);
        RadioButton Room_one = (RadioButton) findViewById(R.id.RoomOne);
        RadioButton Room_two = (RadioButton) findViewById(R.id.RoomTwo);
        RadioButton Room_three = (RadioButton) findViewById(R.id.RoomThree);
        RadioButton Room_four = (RadioButton) findViewById(R.id.RoomFour);
        RadioButton Room_zero = (RadioButton) findViewById(R.id.RoomZero);

        if (reservationPlaced) {
            errorMsg.setText("You must cancel your previous reservation first");
        } else {
            errorMsg.setText("");
        }

        setSupportActionBar(toolbar);
        int userID = getIntent().getIntExtra("userID", 0);

        roomAvailable = 2;
        familyAvailable = 6;
        apartmentAvailable = 4;

        Family_zero.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                familyNumber = 0;
                errorMsg.setText("");
            }
        });


        Family_one.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                familyNumber = 1;
                if (familyNumber > familyAvailable) {
                    errorMsg.setText("There aren't any family rooms available");
                } else {
                    errorMsg.setText("");
                }
            }
        });


        Apartment_zero.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                apartmentNumber = 0;
                errorMsg.setText("");
            }
        });


        Apartment_one.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                apartmentNumber = 1;
                if (apartmentNumber > apartmentAvailable) {
                    errorMsg.setText("There aren't any apartments available");
                } else {
                    errorMsg.setText("");
                }
            }
        });


        Room_zero.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                roomNumber = 0;
                errorMsg.setText("");
            }
        });

        Room_one.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                roomNumber = 1;
                if (roomNumber > roomAvailable) {
                    errorMsg.setText("The rooms chosen is too high.");
                } else {
                    errorMsg.setText("");
                }

            }
        });

        Room_two.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                roomNumber = 2;
                if (roomNumber > roomAvailable) {
                    errorMsg.setText("The rooms chosen is too high.");
                } else {
                    errorMsg.setText("");
                }
            }
        });

        Room_three.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                roomNumber = 3;
                if (roomNumber > roomAvailable) {
                    errorMsg.setText("The rooms chosen is too high.");
                } else {
                    errorMsg.setText("");
                }
            }
        });


        Room_four.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                roomNumber = 4;
                if (roomNumber > roomAvailable) {
                    errorMsg.setText("The rooms chosen is too high.");
                } else {
                    errorMsg.setText("");
                }
            }
        });


        confirm.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (familyNumber > familyAvailable || apartmentNumber > apartmentAvailable || roomNumber > roomAvailable || reservationPlaced) {
                    errorMsg.setText("Enter valid values or cancel previous reservation");
                } else {
                    familyAvailable = familyAvailable - familyNumber;
                    apartmentAvailable = apartmentAvailable - apartmentNumber;
                    roomAvailable = roomAvailable - roomNumber;
                    int total = familyNumber + apartmentNumber + roomNumber;

                    List<DataItem> sheltersList = MainActivity.model.getItems();
                    for (int i = 0; i < sheltersList.size(); i++) {
                        if (shelterName.equals(sheltersList.get(i).getName())) {
                            int capacity = sheltersList.get(i).getIntCapacity()[0];
                            capacity = capacity - total;
                            int[] cap = new int[1];
                            cap[0] = capacity;
                            sheltersList.get(i).setIntCapacity(cap);
                        }
                    }

                    reservationPlaced = true;
                    Intent newIntent = new Intent(ReserveBedActivity.this, MainActivity.class);
                    newIntent.putExtra("reservationPlaced", reservationPlaced);
                    newIntent.putExtra("familyTaken", familyNumber);
                    newIntent.putExtra("apartmentTaken", apartmentNumber);
                    newIntent.putExtra("roomTaken", roomNumber);
                    newIntent.putExtra("shelterReserved", shelterName);

                    startActivity(newIntent);
                }
            }
        });


        }
    }



