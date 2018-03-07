package com.example.heenapatel.m4;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.RadioGroup;
import android.widget.RadioButton;
import android.widget.Toast;

import org.w3c.dom.Text;


/**
 * Created by heenapatel on 3/5/18.
 */

public class SearchActivity extends AppCompatActivity {

    private RadioButton maleRadio;
    private RadioButton femaleRadio;
    private TextView nameText;
    private Button searchButton;
    String genderInfo;
    String shelterName;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        String[] agegroups = {"Anyone", "Families with newborns", "Children", "Young Adults"};

        RadioGroup radioGenderGroup = (RadioGroup) findViewById(R.id.genderRadioGroup);
        RadioButton male = (RadioButton) findViewById(R.id.maleRadio);
        RadioButton female = (RadioButton) findViewById(R.id.femaleRadio);
        TextView name = (TextView) findViewById(R.id.nameText);
        Button search = (Button) findViewById(R.id.searchButton);
        final Spinner age = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, agegroups);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        age.setAdapter(adapter);



        male.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //if male is clicked, the string holds "Male"
                genderInfo = "Male";
            }
        });

        female.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //if female is clicked, the string holds "Female"
                genderInfo = "Female";
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                TextView name = (TextView) findViewById(R.id.nameText);
                shelterName = name.getText().toString();
                Intent intent = new Intent(SearchActivity.this, MainActivity.class);

                int ageGroupIndex = age.getSelectedItemPosition();
                intent.putExtra("genderInfo", genderInfo);
                intent.putExtra("shelterName", shelterName);
                intent.putExtra("AgeGroup", "" + ageGroupIndex);
                startActivity(intent);
            }
        });


    }

}
