package com.example.heenapatel.m4;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by heenapatel on 4/25/18.
 */

public class EmailPasswordRecovery extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.email_password_recovery);
        Button submit = (Button) findViewById(R.id.submitEmail);
        final TextView email = (TextView) findViewById(R.id.emailField);
        final TextView user = (TextView) findViewById(R.id.userField);
        final Credentials cred = (Credentials) getIntent().getSerializableExtra("Credentials");


        submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                TextView name = (TextView) findViewById(R.id.nameText);
                String username = user.getText().toString();
                String emailname = email.getText().toString();
                String pass = "User not available";
                for (int i = 0; i <= cred.size(); i++) {
                    if (username.equals(cred.get(i))) {
                        pass = cred.get(i).getPassword();
                    }
                }

                System.out.println(pass);
            }
        });
    }


}
