package com.example.myapplication.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class MyViewModel extends ViewModel {
    private MutableLiveData<String[]> users;
    private MutableLiveData<String> special_text;

    public LiveData<String[]> getUsers() {
        if (users == null) {
            users = new MutableLiveData<String[]>();
            // Do an asynchronous operation to fetch users.
            users = new MutableLiveData( new String[]{"string 1", "string2"} );
        }
        return users;
    }

    public LiveData<String> getSpecialText() {
        if (special_text == null) {
            special_text = new MutableLiveData<String>();
            // Do an asynchronous operation to fetch users.
            special_text = new MutableLiveData( "Raymond Seger" );
        }
        return special_text;
    }


}