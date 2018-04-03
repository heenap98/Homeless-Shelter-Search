package com.example.heenapatel.m4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class WelcomeScreen extends AppCompatActivity implements Serializable {
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);
        Button login = (Button)findViewById(R.id.button);
        Button register = (Button)findViewById(R.id.locationButton);
        Button guest = (Button)findViewById(R.id.button3);

        login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent newIntent = new Intent(WelcomeScreen.this, LoginActivity.class);
                startActivity(newIntent);
            }
        });


        register.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(WelcomeScreen.this, RegisterActivity.class));
            }
        });


        guest.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(WelcomeScreen.this, SearchActivity.class));
            }
        });


    }
}
