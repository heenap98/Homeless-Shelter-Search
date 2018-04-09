package com.example.heenapatel.m4;

import android.app.Application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.io.FileOutputStream;
import android.content.Context;
import android.util.Log;

import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;


/**
 * Created by heenapatel on 2/19/18.
 */

public class Credentials extends Application implements Serializable {
    public ArrayList<User> user_credentials = new ArrayList<>();

    public void add(User user) {
        user_credentials.add(user);

    }

    public int size() {
        return user_credentials.size();
    }

    public User get(int i) {
        return user_credentials.get(i);
    }

    public ArrayList<User> getUsers() {
        return user_credentials;
    }

    public void setUsers(ArrayList<User> users) {
        user_credentials = users;
    }


}
