package com.example.heenapatel.m4;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

//        DataItemRecyclerViewAdapter adapter = new DataItemRecyclerViewAdapter(SimpleModel.INSTANCE.getItems());
//        DataItemRecyclerViewAdapter adapter = MainActivity.adapter;
        for (int i = 0; i < MainActivity.adapter.mValues.size(); i++) {
            LatLng current = new LatLng(MainActivity.adapter.mValues.get(i).getLatitude(), MainActivity.adapter.mValues.get(i).getLongitude());
            mMap.addMarker(new MarkerOptions().position(current).title(MainActivity.adapter.mValues.get(i).getName() + "\n" + MainActivity.adapter.mValues.get(i).getPhone_number()));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(current));
        }
    }

    public class DataItemRecyclerViewAdapter extends RecyclerView.Adapter<MainActivity.DataItemRecyclerViewAdapter.DataItemListViewHolder> {

        private final List<DataItem> mValues;

//        public DataItemRecyclerViewAdapter(List<DataItem> items) {
//            mValues = items;
//        }

        public DataItemRecyclerViewAdapter(List<DataItem> items) {
            mValues = new ArrayList<>();
            String genderInfo = getIntent().getStringExtra("genderInfo");
            String shelterNameSearch = getIntent().getStringExtra("shelterName");

            int ageGroupIndex = 0;
            if (getIntent().getStringExtra("ageGroupIndex") != null) {
                ageGroupIndex = Integer.parseInt(getIntent().getStringExtra("ageGroupIndex"));
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
        public MainActivity.DataItemRecyclerViewAdapter.DataItemListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return null;
        }

        @Override
        public void onBindViewHolder(MainActivity.DataItemRecyclerViewAdapter.DataItemListViewHolder holder, int position) {
            CharSequence details = mValues.get(position).getName() + "\n" + mValues.get(position).getPhone_number();
            holder.name.setText(details);
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }
    }
}
