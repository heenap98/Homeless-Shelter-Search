package com.example.heenapatel.m4;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by heenapatel on 2/26/18.
 */

public class SimpleModel {
    public static final SimpleModel INSTANCE = new SimpleModel();

    private List<DataItem> items;

    private SimpleModel() {
        items = new ArrayList<>();
    }

    public void addItem(DataItem item) {
        items.add(item);
    }

    public List<DataItem> getItems() {
        return items;
    }



    public void modifyItems (String shelterName, int familyAvailable, int apartmentAvailable, int roomAvailable) {


        for (int i = 0; i < items.size(); i++) {
           if (shelterName == items.get(i).getName()) {
               Log.d("start cap", "" + items.get(i).getCapacity());
               Log.d("new cap", "" + (familyAvailable + apartmentAvailable + roomAvailable));
               items.get(i).setCapacity(familyAvailable + apartmentAvailable + roomAvailable);
               Log.d("changed? cap", "" + items.get(i).getCapacity());
           }
       }

    }

}
