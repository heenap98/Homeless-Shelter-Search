package com.example.heenapatel.m4;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.net.Uri;

/**
 * Created by heenapatel on 4/25/18.
 */

public class EmailPasswordRecovery extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.email_password_recovery);
        final EditText email = (EditText) findViewById(R.id.emailTextField);
        Button recoveryButton = (Button) findViewById(R.id.emailRecoveryButton);

        recoveryButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                User user = null;
                Credentials cred = (Credentials) getIntent().getSerializableExtra("Credentials");
                for (int i = 0; i < cred.size(); i++) {
                    System.out.println("For loop" + "going in for loop to check creds");
                    if (cred.get(i).getUsername().equals(email.getText().toString())) {
                        user = cred.get(i);
                        System.out.println("GOT USER");
                        break;
                    }
                }
                final String password = user.getPassword();

                Intent recovery = new Intent(Intent.ACTION_SEND);
                recovery.setData(Uri.parse("mailto:"));
                recovery.setType("text/plain");
                recovery.putExtra(Intent.EXTRA_EMAIL, new String[]{email.getText().toString()});
                recovery.putExtra(Intent.EXTRA_SUBJECT, "Recover password");
                recovery.putExtra(Intent.EXTRA_TEXT, "Your password is: " + password);

                try {
                    startActivity(Intent.createChooser(recovery, "Send mail..."));
                    finish();
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(EmailPasswordRecovery.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                }
            }

        });
    }
}
