package com.example.heenapatel.m4;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import java.util.ArrayList;
import android.widget.EditText;
import android.widget.RadioButton;

/**
 * Created by heenapatel on 2/17/18.
 */

public class RegisterActivity extends AppCompatActivity {

    protected EditText name;
    protected EditText username;
    private EditText password;
    private RadioButton user;
    private RadioButton admin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Button register = (Button) findViewById(R.id.registerButton);
        Button cancel = (Button) findViewById(R.id.registerCancelButton);
        name = (EditText) findViewById(R.id.registerName);
        username = (EditText) findViewById(R.id.registerUsername);
        password = (EditText) findViewById(R.id.registerPassword);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser(v);
            }
        });
    }

    public void registerUser(View v) {
        if(name.getText().toString() != ""
                && username.getText().toString() != ""
                && password.getText().toString() != "") {
            String[] user = new String[2];
            user[0] = username.getText().toString();
            user[1] = password.getText().toString();
            Credentials.user_credentials.add(user);
            startActivity(new Intent(RegisterActivity.this, MainActivity.class));
        }
    }
}
