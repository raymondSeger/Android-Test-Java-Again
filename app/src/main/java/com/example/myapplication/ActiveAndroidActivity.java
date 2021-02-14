package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.Configuration;
import com.example.myapplication.activeandroid.Category;
import com.example.myapplication.activeandroid.Item;

public class ActiveAndroidActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_active_android);
        Configuration dbConfiguration = new Configuration.Builder(this).setDatabaseName("xxx.db").create();
        ActiveAndroid.initialize(dbConfiguration);


        // add items - start
        Category restaurants    = new Category();
        restaurants.name        = "Restaurants";
        restaurants.save();

        Item item       = new Item();
        item.category   = restaurants;
        item.name       = "Outback Steakhouse";
        item.save();

        Item item2      = new Item();
        item2.category  = restaurants;
        item2.name      = "Red Robin";
        item2.save();

        Item item3      = new Item();
        item3.category  = restaurants;
        item3.name      = "Olive Garden";
        item3.save();
        // add items - end

        // bulk insert - start
        ActiveAndroid.beginTransaction();
        try {
            for (int i = 0; i < 100; i++) {
                Item items = new Item();
                items.name = "Example " + i;
                items.save();
            }
            ActiveAndroid.setTransactionSuccessful();
        }
        finally {
            ActiveAndroid.endTransaction();
        }
        // bulk insert - end

        // delete - start
        /*
        Item.delete(Item.class, 1);

        Item item = Item.load(Item.class, 1);
        item.delete();

        new Delete().from(Item.class).where("Id = ?", 1).execute();
        */
        // delete - end

        Item a = Item.getRandom();
        Toast.makeText(this, a.name + " " + a.category, Toast.LENGTH_SHORT).show();

    }
}