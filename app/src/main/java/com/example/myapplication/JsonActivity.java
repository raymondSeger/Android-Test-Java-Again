package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class JsonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.json);

        Button button = (Button) findViewById(R.id.the_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // make sure the quotes are escaped
                String str                  = "{\"data\":{\"translations\":[{\"translatedText\":\"Bonjour tout le monde\"}]}}";
                JsonParser parser           = new JsonParser();
                JsonObject rootObj          = parser.parse(str).getAsJsonObject();
                JsonArray array             = rootObj.getAsJsonObject("data").getAsJsonArray("translations");
                JsonObject translatedText   = (JsonObject) array.get(0); // .get("translatedText").getAsString();
                JsonElement a               = translatedText.get("translatedText");
                String a_string             = a.getAsString();
                Toast.makeText(JsonActivity.this, a_string, Toast.LENGTH_SHORT).show();
            }
        });

        int b = 0;
    }
}