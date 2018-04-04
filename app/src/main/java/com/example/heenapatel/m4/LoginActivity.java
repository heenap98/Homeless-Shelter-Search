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

        String[] current_user = new String[2];
        current_user[0] = emailField.getText().toString();
        current_user[1] = passField.getText().toString();
        Credentials cred = (Credentials) getApplicationContext();
        for (int i = 0; i < cred.size(); i++) {

            if (current_user[0].equals((cred.get(i).getUsername()))
                    && current_user[1].equals((cred.get(i).getPassword()))) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                intent.putExtra("UserID", i);
                startActivity(intent);
            }
        }

        invalidText.setVisibility(View.VISIBLE);
    }
}
