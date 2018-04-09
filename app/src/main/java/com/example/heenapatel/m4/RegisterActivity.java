package com.example.heenapatel.m4;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

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

            User newUser = new User(user[0], user[1]);

            Credentials obj = (Credentials) getApplicationContext();

            //*****************************************************
            //ATTEMPT AT BINARY SERIALIZATION
            String filename = "data.data";
            File file = new File(obj.getApplicationInfo().dataDir, "data.data");
            Credentials cred = (Credentials) getIntent().getSerializableExtra("Credentials");
            cred.add(newUser);

            System.out.println("REGISTER ACTIVITY");
            for (int i = 0; i < cred.size(); i++) {
                System.out.println(cred.get(i).getUsername());
                System.out.println(cred.get(i).getPassword());
            }

            try {
                ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
                // We basically can save our entire data model with one write, since this will follow
                // all the links and pointers to save everything.  Just save the top level object.
                out.writeObject(cred);
                Log.d("success", "in saving");
                out.close();
            } catch (IOException e) {
                Log.e("UserManagerFacade", "Error writing an entry from binary file", e);
            }
            //*****************************************************


            int id = cred.size() - 1;
            Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
            intent.putExtra("UserID", id);
            intent.putExtra("Credentials", cred);
            startActivity(intent);
        }
    }
}
