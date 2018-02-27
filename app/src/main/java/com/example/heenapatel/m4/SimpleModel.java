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

}
