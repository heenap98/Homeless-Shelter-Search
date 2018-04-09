package com.example.heenapatel.m4;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.TextView;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static int count = 0;
    public int familyTaken;
    public int apartmentTaken;
    public int roomTaken;
    public String shelterName;
    public static SimpleModel model = SimpleModel.INSTANCE;
    public int userID;
    public int shelterID;
    private User user;

    public DataItemRecyclerViewAdapter adapter;

    public int[] capacity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userID = getIntent().getIntExtra("UserID", 999999);
        Log.d("UserID", "" + userID);
        final Credentials obj = (Credentials) getApplicationContext();

        shelterName = getIntent().getStringExtra("shelterReserved");
        familyTaken = getIntent().getIntExtra("familyTaken", 0);
        apartmentTaken = getIntent().getIntExtra("apartmentTaken", 0);
        roomTaken = getIntent().getIntExtra("roomTaken", 0);


//        if (reservationPlaced) {
//            Log.d("reservation", " is true");
//            Log.d("roomcap", "" + roomTaken);
//            Log.d("family", "" + roomTaken);
//            Log.d("apartment", "" + roomTaken);
//
//            SimpleModel.INSTANCE.modifyItems(shelterName, familyTaken, apartmentTaken, roomTaken);
//        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        Button logOut = (Button)findViewById(R.id.logOutButton);
        Button search = (Button) findViewById(R.id.searchButton);
        Button map = (Button) findViewById(R.id.mapButton);
        setSupportActionBar(toolbar);
        if (count == 0) {
            readSDFile();
            count++;
        }

        final RecyclerView recyclerView = findViewById(R.id.recyclerView);
        adapter = new DataItemRecyclerViewAdapter(SimpleModel.INSTANCE.getItems());

        resetRecycleView(recyclerView);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        logOut.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //*****************************************************
                //ATTEMPT AT BINARY SERIALIZATION
                String filename = "data.data";
                File file = new File(obj.getApplicationInfo().dataDir, "data.data");
                Credentials cred = (Credentials) getIntent().getSerializableExtra("Credentials");

                System.out.println("MAIN ACTIVITY");
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
                    System.out.println(cred.size());
                    out.close();
                } catch (IOException e) {
                    Log.e("UserManagerFacade", "Error writing an entry from binary file",e);
                }
                //*****************************************************


                startActivity(new Intent(MainActivity.this, WelcomeScreen.class));
            }
        });


        recyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(recyclerView.getContext(), ShelterDetails.class);
                intent.putExtra("UserID", userID);
                view.getContext().startActivity(intent);
            }
        });


        search.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SearchActivity.class));
            }
        });

        map.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                String genderInfo = getIntent().getStringExtra("genderInfo");
                String shelterNameSearch = getIntent().getStringExtra("shelterName");

                int ageGroupIndex = 0;
                if (getIntent().getStringExtra("AgeGroup") != null) {
                    ageGroupIndex = Integer.parseInt(getIntent().getStringExtra("AgeGroup"));
                }

                Intent newIntent = new Intent(MainActivity.this, MapsActivity.class);
                newIntent.putExtra("genderInfo",genderInfo);
                newIntent.putExtra("shelterNameSearch",shelterNameSearch);
                newIntent.putExtra("ageGroupIndex",ageGroupIndex);
                startActivity(newIntent);
            }
        });

    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        recyclerView.setAdapter(new DataItemRecyclerViewAdapter(SimpleModel.INSTANCE.getItems()));
    }

    private void resetRecycleView(@NonNull RecyclerView recyclerView) {
        recyclerView.setAdapter(adapter);
    }

    public class DataItemRecyclerViewAdapter extends RecyclerView.Adapter<DataItemRecyclerViewAdapter.DataItemListViewHolder> {

        private final List<DataItem> mValues;

//        public DataItemRecyclerViewAdapter(List<DataItem> items) {
//            mValues = items;
//        }

        public DataItemRecyclerViewAdapter(List<DataItem> items) {
            Log.d("testestest","DOES THIS HAPPEN MORE THAN 1NCE");
            mValues = new ArrayList<>();
            String genderInfo = getIntent().getStringExtra("genderInfo");
            String shelterNameSearch = getIntent().getStringExtra("shelterName");

            int ageGroupIndex = 0;
            if (getIntent().getStringExtra("AgeGroup") != null) {
                ageGroupIndex = Integer.parseInt(getIntent().getStringExtra("AgeGroup"));
            }

            AgeGroup ageGroup = AgeGroup.values()[ageGroupIndex];
            for (int i = 0; i < items.size(); i++) {
                if ((genderInfo == null
                        || (genderInfo.equalsIgnoreCase("Male") && items.get(i).getMaleFriendly())
                        || genderInfo.equalsIgnoreCase("Female") && items.get(i).getFemaleFriendly())) {

                    if (shelterNameSearch == null
                            || items.get(i).getName().toLowerCase().contains(shelterNameSearch.toLowerCase())) {


                        if (items.get(i).getAgeGroup().equals(ageGroup) || (ageGroup.equals(AgeGroup.Anyone))) {
                            mValues.add(items.get(i));
                        }
                    }
                }
            }



        }

        @Override
        public DataItemListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.dataitem_list_content, parent, false);
            return new DataItemListViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final DataItemListViewHolder holder, int position) {
            holder.name.setText(mValues.get(position).getName());
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }

        public class DataItemListViewHolder extends RecyclerView.ViewHolder {
            TextView name;

            DataItemListViewHolder(View itemView) {
                super(itemView);
                name = (TextView) itemView.findViewById(R.id.dataitem_row_textView);

                    itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v)
                        {
                            Intent intent = new Intent(MainActivity.this, ShelterDetails.class);
                            String selected = name.getText().toString();
                            int which = 0;
                            for(int i = 0; i < mValues.size(); i++) {
                                if (mValues.get(i).getName().equalsIgnoreCase(selected)) {
                                    which = i;
                                    break;
                                }
                            }
                            intent.putExtra("shelterName", mValues.get(which).getName());
                            intent.putExtra("Address", mValues.get(which).getAddress());
                            intent.putExtra("capacityArray", mValues.get(which).getIntCapacity());
                            intent.putExtra("Capacity", mValues.get(which).getCapacity());
                            intent.putExtra("Maleok", mValues.get(which).getMaleFriendly());
                            intent.putExtra("Femaleok", mValues.get(which).getFemaleFriendly());
                            intent.putExtra("UserID", userID);
                            intent.putExtra("ShelterID", which);
                            startActivity(intent);

                        }
                    });
            }
        }
    }

    private void readSDFile() {
        //SimpleModel model = SimpleModel.INSTANCE;

        try {
            //Open a stream on the raw file
            InputStream is = getResources().openRawResource(R.raw.homeless2_shelter_database);

            //From here we probably should call a model method and pass the InputStream
            //Wrap it in a BufferedReader so that we get the readLine() method

            BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));

            String line;
            br.readLine(); //get rid of header line

            int count = 0;

            while ((line = br.readLine()) != null) {
                for (int i = 0; i < line.length(); i++) {
                    if (line.charAt(i) == ',' && count % 2 == 0) {
                        line = line.substring(0, i) + ";" + line.substring(i + 1);
                    }
                    if (line.charAt(i) == '\"') {
                        count++;
                    }
                }

                String[] tokens = line.split(";");

//                if (tokens[2].contains("Famil") || tokens[2].contains("famil") || tokens[2].contains("apartment")
//                        || tokens[2].contains("Apartment") || tokens[2].contains("Room") || tokens[2].contains("room")) {
//                    reservationLimit = 1;
//                } else {
//                    reservationLimit = 4;
//                }

                String[] test = tokens[2].split(",");

                String tempIntCapacity;
                String capacityDescription;
                int[] capacities = new int[test.length];
                String[] capacityDescriptions = new String[test.length];

                for (int i = 0; i < test.length; i++) {
                    //EACH CAPACTIY
                    tempIntCapacity = "";
                    capacityDescription = "";

                    for (int j = 0; j < test[i].length(); j++) {
                        //EACH CHARACTER
                        if (test[i].charAt(j) >= '0' && test[i].charAt(j) <= '9') {
                            tempIntCapacity += test[i].charAt(j);
                        } else if (test[i].charAt(j) != '"') {
                            capacityDescription += test[i].charAt(j);
                        }
                    }



                    if (!tempIntCapacity.equals("")) {
                        int intCapacity = Integer.parseInt(tempIntCapacity);
                        capacities[i] = intCapacity;
                        capacityDescriptions[i] = capacityDescription;
                    }


                }

                int key = Integer.parseInt(tokens[0]);
                double longitude = Double.parseDouble(tokens[4]);
                double latitude = Double.parseDouble(tokens[5]);


                if (capacities.length != 0) {
                    model.addItem(new DataItem(key, tokens[1], capacityDescriptions, tokens[3], longitude, latitude, tokens[6], tokens[7], tokens[8], capacities));
                } else {
                    model.addItem(new DataItem(key, tokens[1], tokens[3], longitude, latitude, tokens[6], tokens[7], tokens[8]));
                }
            }

            br.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}


