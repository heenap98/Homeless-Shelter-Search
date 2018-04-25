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
    //Credentials cred;
    private int loginCount = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

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

        Credentials cred = (Credentials) getIntent().getSerializableExtra("Credentials");

        System.out.println("LOG IN ACTIVITY");
        for (int i = 0; i < cred.size(); i++) {
            System.out.println(cred.get(i).getUsername());
            System.out.println(cred.get(i).getPassword());
        }

        String[] current_user = new String[2];
        current_user[0] = emailField.getText().toString();
        current_user[1] = passField.getText().toString();
//        Credentials cred = (Credentials) getApplicationContext();
        for (int i = 0; i < cred.size(); i++) {

            if (current_user[0].equals((cred.get(i).getUsername()))
                    && current_user[1].equals((cred.get(i).getPassword()))) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                intent.putExtra("Credentials", cred);
                intent.putExtra("UserID", i);
                loginCount = 0;
                startActivity(intent);
            } else if (i == cred.size() - 1) {
                loginCount++;
                if (loginCount >= 3) {
                    //***************************************
                    //DO EMAIL PASSWORD RECOVERY HERE
                    //***************************************
                    System.out.println("Test for log in recovery.");
                    startActivity(new Intent(LoginActivity.this, EmailPasswordRecovery.class));
                }

                invalidText.setVisibility(View.VISIBLE);
            }
        }
    }
}
