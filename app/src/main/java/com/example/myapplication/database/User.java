package com.example.myapplication.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "users")
public class User {

    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "first_name")
    public String firstName;

    @ColumnInfo(name = "last_name")
    public String lastName;

    @Ignore
    public User(String firstName, String lastName) {
        this.firstName   = firstName;
        this.lastName    = lastName;
    }

    public User(int uid, String firstName, String lastName) {
        this.uid         = uid;
        this.firstName   = firstName;
        this.lastName    = lastName;
    }

}