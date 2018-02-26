package com.example.heenapatel.m4;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.util.Log;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

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

//        login.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
//            }
//        });
    }

    public void logIn(View v) {
//        if(emailField.getText().toString().equals("user")
//            && passField.getText().toString().equals("pass")) {
//            startActivity(new Intent(this, MainActivity.class));
//        } else {
//            invalidText.setVisibility(View.VISIBLE);
//        }

        if (Credentials.user_credentials.size() != 0) {
            System.out.println((Credentials.user_credentials.get(0))[0]);
        } else {
            System.out.println("SKIP");
        }

        String[] current_user = new String[2];
        current_user[0] = emailField.getText().toString();
        current_user[1] = passField.getText().toString();
        boolean registeredUser = false;
        for (int i = 0; i < Credentials.user_credentials.size(); i++) {
            if (current_user[0].equals((Credentials.user_credentials.get(i))[0])
                    && current_user[1].equals((Credentials.user_credentials.get(i))[1])) {
                registeredUser = true;
                break;
            }
        }
        if (registeredUser) {
//            readSDFile();
            startActivity(new Intent(this, MainActivity.class));
        } else {
            invalidText.setVisibility(View.VISIBLE);
        }
    }


//    private void readSDFile() {
//        SimpleModel model = SimpleModel.INSTANCE;
//
//        try {
//            //Open a stream on the raw file
//            InputStream is = getResources().openRawResource(R.raw.homeless_shelter_database);
//            //From here we probably should call a model method and pass the InputStream
//            //Wrap it in a BufferedReader so that we get the readLine() method
//            BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
//
//            String line;
//            br.readLine(); //get rid of header line
//
//            int count = 0;
//
//            while ((line = br.readLine()) != null) {
//                for (int i = 0; i < line.length(); i++) {
//                    if (line.charAt(i) == ',' && count % 2 == 0) {
//                        line = line.substring(0, i) + ";" + line.substring(i + 1);
//                    }
//                    if (line.charAt(i) == '\"') {
//                        count++;
//                    }
//                }
//
//                String[] tokens = line.split(";");
//                for(int i = 0; i < tokens.length; i++) {
//                    System.out.println(tokens[i]);
//                }
//                System.out.println(tokens.length);
//                int key = Integer.parseInt(tokens[0]);
//                int longitude = Integer.parseInt(tokens[4]);
//                int latitude = Integer.parseInt(tokens[5]);
//                model.addItem(new DataItem(key, tokens[1], tokens[2], tokens[3], longitude, latitude, tokens[6], tokens[7], tokens[8]));
//            }
//            br.close();
//        } catch (IOException e) {
//        }
//
//    }

}
