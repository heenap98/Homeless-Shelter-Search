package com.example.heenapatel.m4;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
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

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shelterdetails);
        String s = getIntent().getStringExtra("shelterName");
        TextView shelterInfo = (TextView) findViewById(R.id.shelterText);
        List<DataItem> shelterHolder = SimpleModel.INSTANCE.getItems();
        shelterInfo.setText(s + "testing");

    }



}
