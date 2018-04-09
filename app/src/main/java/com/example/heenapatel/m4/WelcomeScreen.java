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

public class WelcomeScreen extends AppCompatActivity {
//    public Credentials cred;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);
        Button login = (Button)findViewById(R.id.button);
        Button register = (Button)findViewById(R.id.locationButton);
        Button guest = (Button)findViewById(R.id.button3);

        login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //***********************************************************************
                //ATTEMPT AT LOADING BINARY SERIALIZATION
                Credentials obj = (Credentials) getApplicationContext();
                File file = new File(obj.getApplicationInfo().dataDir, "data.data");
                try {
                    ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
                    // assuming we saved our top level object, we read it back in with one line of code.
                    Credentials cred = (Credentials) in.readObject();

                    System.out.println("WELCOME SCREEN");
                    for (int i = 0; i < cred.size(); i++) {
                        System.out.println(cred.get(i).getUsername());
                        System.out.println(cred.get(i).getPassword());
                    }

                    Intent newIntent = new Intent(WelcomeScreen.this, LoginActivity.class);
                    newIntent.putExtra("Credentials", cred);
                    in.close();
                    Log.d("success", "success in loading");
                    System.out.println(cred.size());
                    startActivity(newIntent);
                } catch (IOException e) {
                    Log.e("UserManagementFacade", "Error reading an entry from binary file",e);
                } catch (ClassNotFoundException e) {
                    Log.e("UserManagementFacade", "Error casting a class from the binary file",e);
                }
                //***********************************************************************
            }
        });


        register.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //***********************************************************************
                //ATTEMPT AT LOADING BINARY SERIALIZATION
                Credentials obj = (Credentials) getApplicationContext();
                File file = new File(obj.getApplicationInfo().dataDir, "data.data");
                try {
                    ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
                    // assuming we saved our top level object, we read it back in with one line of code.
                    Credentials cred = (Credentials) in.readObject();

                    System.out.println("WELCOME SCREEN");
                    for (int i = 0; i < cred.size(); i++) {
                        System.out.println(cred.get(i).getUsername());
                        System.out.println(cred.get(i).getPassword());
                    }

                    Intent newIntent = new Intent(WelcomeScreen.this, RegisterActivity.class);
                    newIntent.putExtra("Credentials", cred);
                    in.close();
                    Log.d("success", "success in loading");
                    System.out.println(cred.size());
                    startActivity(newIntent);
                } catch (IOException e) {
                    Log.e("UserManagementFacade", "Error reading an entry from binary file",e);
                } catch (ClassNotFoundException e) {
                    Log.e("UserManagementFacade", "Error casting a class from the binary file",e);
                }
                //***********************************************************************

            }
        });


        guest.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(WelcomeScreen.this, SearchActivity.class));
            }
        });


    }
}
