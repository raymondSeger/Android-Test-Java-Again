package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.viewmodels.MyViewModel;

public class ViewModelActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_model);

        Button the_button_1         = (Button) findViewById(R.id.the_button_1);
        final TextView textview1    = (TextView) findViewById(R.id.textview1);
        final TextView textview2    = (TextView) findViewById(R.id.textview2);

        final MyViewModel model = new ViewModelProvider(this).get(MyViewModel.class);
        model.getUsers().observe(this, new Observer<String[]>() {
            @Override
            public void onChanged(String[] strings) {
                String a = strings[0];
                textview1.setText(a);
            }
        });

        model.getSpecialText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String string) {
                textview2.setText(string);
            }
        });

        the_button_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MutableLiveData<String[]> users_new_value = (MutableLiveData<String[]>) model.getUsers();
                users_new_value.setValue( new String[]{"Raymond", "Billy"} );

                MutableLiveData<String> special_text_new_value = (MutableLiveData<String>) model.getSpecialText();
                special_text_new_value.setValue( "Billy 24" );
            }
        });

    }
}