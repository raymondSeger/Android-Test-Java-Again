package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.myapplication.objects.User;

import java.util.ArrayList;
import java.util.List;

public class SpinnerCustomObjectActivity extends AppCompatActivity {

    private Spinner spinner;
    private Button button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner_custom_object);

        spinner                 = findViewById(R.id.spinner);
        button1                 = findViewById(R.id.button1);
        List<User> userList     = new ArrayList<>();
        User user1              = new User("Jim", 20, "jim@gmail.com");
        User user2              = new User("John", 23, "john@gmail.com");
        User user3              = new User("Jenny", 25, "jenny@gmail.com");
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
        ArrayAdapter<User> adapter = new ArrayAdapter<User>(this, android.R.layout.simple_spinner_item, userList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                User user = (User) parent.getSelectedItem();
                displayUserData(user);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = (User) spinner.getSelectedItem();
                displayUserData(user);
            }
        });
    }

    private void displayUserData(User user) {
        String name     = user.getName();
        int age         = user.getAge();
        String mail     = user.getMail();
        String userData = "Name: " + name + "\nAge: " + age + "\nMail: " + mail;
        Toast.makeText(this, userData, Toast.LENGTH_LONG).show();
    }
}