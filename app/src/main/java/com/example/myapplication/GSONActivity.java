package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.myapplication.objects.Employee;
import com.google.gson.Gson;

public class GSONActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_g_s_o_n);

        Gson gson = new Gson();

        Employee employee2  = new Employee("John", 30, "john@gmail.com");
        String json2        = gson.toJson(employee2);

        String json         = "{\"first_name\":\"John\",\"age\":30,\"mail\":\"john@gmail.com\"}";
        Employee employee   = gson.fromJson(json, Employee.class);

        String a = "debug here";
    }
}