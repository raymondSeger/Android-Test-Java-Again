package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.myapplication.objects.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FirebaseRealTimeDatabaseActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_real_time_database);

        textView = (TextView) findViewById(R.id.textView);

        // Write a message to the database
        FirebaseDatabase database   = FirebaseDatabase.getInstance();
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        DatabaseReference myRef     = database.getReference("message");
        myRef.setValue("Hello, World!");

        // write an object
        User user       = new User("Raymond",20, "raymond@tnpgroup.co");
        String user_id  = "1";
        mDatabase.child("users").child(user_id).setValue(user); // add the object
        mDatabase.child("users").child(user_id).child("username").setValue("Billy"); // change the object's value

        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                textView.setText(value);
                Log.d("TAG", "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("TAG", "Failed to read value.", error.toException());
            }
        });




    }
}