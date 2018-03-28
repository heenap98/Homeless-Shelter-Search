package com.example.heenapatel.m4;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    private EditText emailField;
    private EditText passField;
    private TextView invalidText;
    private DatabaseHandler db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        DatabaseHandler db = new DatabaseHandler(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        emailField = (EditText)findViewById(R.id.editText);
        passField = (EditText)findViewById(R.id.editText2);
        invalidText = (TextView)findViewById(R.id.textView);
        Button login = (Button)findViewById(R.id.button4);
        Button cancel = (Button) findViewById(R.id.cancelButton);
        cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

    }

    public void logIn(View v) {

        if (Credentials.user_credentials.size() != 0) {
            System.out.println((Credentials.user_credentials.get(0))[0]);
        } else {
            System.out.println("SKIP");
        }

        String[] current_user = new String[2];
        current_user[0] = emailField.getText().toString();
        current_user[1] = passField.getText().toString();
//        Log.d("pass entered", current_user[1]);
//        Log.d("user entered", current_user[0]);
        boolean registeredUser = false;
        int userID = 0; // determined by i, used across all activities to keep track of which credentials to use
        for (int i = 0; i < Credentials.user_credentials.size(); i++) {
//            Log.d("pass true", Credentials.user_credentials.get(i)[1]);
//            Log.d("user true", Credentials.user_credentials.get(i)[0]);

            if (current_user[0].equals((Credentials.user_credentials.get(i))[0])
                    && current_user[1].equals((Credentials.user_credentials.get(i))[1])) {
                registeredUser = true;
                userID = i;
                break;
            }
        }

//        ArrayList<User> users = (ArrayList<User>) db.getAllUsers();
//
//        for (int i = 0; i < users.size(); i++) {
//            if (current_user[0].equals(users.get(i).getUsername())
//                    && current_user[1].equals(users.get(i).getPassword())) {
//                registeredUser = true;
//                userID = i;
//                break;
//            }
//        }
    }
}
