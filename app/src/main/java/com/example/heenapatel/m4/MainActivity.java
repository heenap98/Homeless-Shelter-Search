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
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static int count = 0;
    public DataItemRecyclerViewAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
                startActivity(new Intent(MainActivity.this, WelcomeScreen.class));
            }
        });


        recyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.getContext().startActivity(new Intent(recyclerView.getContext(), ShelterDetails.class));
            }
        });


        search.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SearchActivity.class));
            }
        });

        map.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MapActivity.class));
            }
        });

    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        recyclerView.setAdapter(new DataItemRecyclerViewAdapter(SimpleModel.INSTANCE.getItems()));
    }

    private void resetRecycleView(@NonNull RecyclerView recyclerView) {
        Log.i("frick this", ("" + SimpleModel.INSTANCE.getItems().size()));
        recyclerView.setAdapter(adapter);
    }

    public class DataItemRecyclerViewAdapter extends RecyclerView.Adapter<DataItemRecyclerViewAdapter.DataItemListViewHolder> {

        private final List<DataItem> mValues;

//        public DataItemRecyclerViewAdapter(List<DataItem> items) {
//            mValues = items;
//        }

        public DataItemRecyclerViewAdapter(List<DataItem> items) {
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
                                    continue;
                                }
                            }
                            intent.putExtra("shelterName", mValues.get(which).getName());
                            intent.putExtra("Address", mValues.get(which).getAddress());
                            intent.putExtra("capacityArray", mValues.get(which).getIntCapacity());
                            intent.putExtra("Capacity", mValues.get(which).getCapacity());
                            intent.putExtra("Maleok", mValues.get(which).getMaleFriendly());
                            intent.putExtra("Femaleok", mValues.get(which).getFemaleFriendly());
                            startActivity(intent);

                        }
                    });
            }
        }
    }

    private void readSDFile() {
        SimpleModel model = SimpleModel.INSTANCE;

        try {
            //Open a stream on the raw file
            InputStream is = getResources().openRawResource(R.raw.homeless_shelter_database);

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


