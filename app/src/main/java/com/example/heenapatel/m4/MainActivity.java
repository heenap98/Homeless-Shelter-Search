package com.example.heenapatel.m4;

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
import android.widget.Button;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        Button logOut = (Button)findViewById(R.id.logOutButton);
        setSupportActionBar(toolbar);

        final RecyclerView recyclerView = findViewById(R.id.recyclerView);
        assert recyclerView != null;
        setupRecyclerView(recyclerView);

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


    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        recyclerView.setAdapter(new DataItemRecyclerViewAdapter(SimpleModel.INSTANCE.getItems()));
    }

    public class DataItemRecyclerViewAdapter extends RecyclerView.Adapter<DataItemRecyclerViewAdapter.DataItemListViewHolder> {

        private final List<DataItem> mValues;

        public DataItemRecyclerViewAdapter(List<DataItem> items) {
            mValues = items;
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
                            for(int i=0; i<mValues.size(); i++) {
                                if (mValues.get(i).getName().equalsIgnoreCase(selected)) {
                                    which = i;
                                    continue;
                                }
                            }
                            intent.putExtra("shelterName", mValues.get(which).getName());
                            intent.putExtra("Address", mValues.get(which).getAddress());
                            intent.putExtra("Capacity", mValues.get(which).getCapacity());
                            startActivity(intent);

                        }
                    });

            }



        }


        }



    }


