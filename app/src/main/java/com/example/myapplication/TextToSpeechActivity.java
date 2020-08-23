package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;

import java.util.Locale;

public class TextToSpeechActivity extends AppCompatActivity {

    public TextToSpeech text_to_speech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_to_speech);

        Button the_button = (Button) findViewById(R.id.the_button);
        the_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                text_to_speech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int status) {

                        if(status != TextToSpeech.ERROR) {
                            text_to_speech.setLanguage(Locale.UK);

                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                text_to_speech.speak("Hello World", TextToSpeech.QUEUE_FLUSH, null,null);
                            } else {
                                text_to_speech.speak("Hello World", TextToSpeech.QUEUE_FLUSH, null);
                            }

                        }

                    }
                });

            }
        });

    }
}