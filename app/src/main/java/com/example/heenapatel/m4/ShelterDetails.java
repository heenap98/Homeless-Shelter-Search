package com.example.heenapatel.m4;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.EditText;
import android.widget.TextView;
import android.app.Activity;
import java.util.ArrayList;

import java.util.List;

public class ShelterDetails extends AppCompatActivity {


    public static boolean reservationPlaced;
    public int familyAvailable;
    public int apartmentAvailable;
    public int roomAvailable;
    public String shelterName;



    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        reservationPlaced = getIntent().getBooleanExtra("reservationPlaced", false);
        Log.d("reservation", "" + reservationPlaced);

        setContentView(R.layout.activity_shelterdetails);
        Button reserve = (Button) findViewById(R.id.reserveButton);
        int userID = getIntent().getIntExtra("userID", 0);
        final String s = getIntent().getStringExtra("shelterName");
        String s1 = getIntent().getStringExtra("Address");
        String s2 = getIntent().getStringExtra("Capacity");
        final int[] capacityArray = getIntent().getIntArrayExtra("capacityArray");
        boolean s3 = getIntent().getBooleanExtra("Maleok", false);
        boolean s4 = getIntent().getBooleanExtra("Femaleok", false);
        boolean reserved = false;
        TextView shelterInfo = (TextView) findViewById(R.id.shelterText);
        List<DataItem> shelterHolder = SimpleModel.INSTANCE.getItems();
        shelterInfo.setText(s + "\n Address: " + s1 + "\n Capacity: " + s2 + "\n Male: " + s3 + "\n Female: " + s4);
        if (Credentials.reserved_status.get(userID)) {
            reserve.setVisibility(View.INVISIBLE);
        } else {
            reserve.setVisibility(View.VISIBLE);
        }
        reserve.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent newIntent = new Intent(ShelterDetails.this, ReserveBedActivity.class);
                newIntent.putExtra("shelterName", s);
                newIntent.putExtra("capacityArray", capacityArray);
                newIntent.putExtra("reservationPlaced", reservationPlaced);
                startActivity(newIntent);
            }
        });
    }
}
