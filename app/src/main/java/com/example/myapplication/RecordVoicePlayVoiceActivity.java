package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import java.io.File;
import java.io.IOException;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.RECORD_AUDIO;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class RecordVoicePlayVoiceActivity extends AppCompatActivity {

    public MediaRecorder myAudioRecorder;
    public MediaPlayer mediaPlayer = new MediaPlayer();

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                } else {
                    // Permission Denied
                    Toast.makeText(getBaseContext(), "not allowed", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_voice_play_voice);

        // check permission
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(RecordVoicePlayVoiceActivity.this, new String[] { WRITE_EXTERNAL_STORAGE, READ_EXTERNAL_STORAGE, RECORD_AUDIO }, 1);
            return ; // in real app, you should make alert that this feature needs that permission, so basically these kind of stuff requires "on click" event handler
        }

        Button start_record_button  = (Button) findViewById(R.id.start_record_button);
        Button stop_record_button   = (Button) findViewById(R.id.stop_record_button);
        Button play_audio_button    = (Button) findViewById(R.id.play_audio_button);

        // Record to the external cache directory for visibility
        final File path_file = new File(getBaseContext().getFilesDir(), "audiorecordtest.mp4");

        myAudioRecorder = new MediaRecorder();
        myAudioRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        myAudioRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
        myAudioRecorder.setAudioEncoder(MediaRecorder.OutputFormat.MPEG_4);
        myAudioRecorder.setOutputFile(path_file.getPath());

        start_record_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    myAudioRecorder.prepare();
                    myAudioRecorder.start();
                    Toast.makeText(RecordVoicePlayVoiceActivity.this, "Recording started", Toast.LENGTH_LONG).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        stop_record_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myAudioRecorder.stop();
                Toast.makeText(RecordVoicePlayVoiceActivity.this, "Recording stopped", Toast.LENGTH_LONG).show();
            }
        });

        play_audio_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if( mediaPlayer.isPlaying() ) {
                    mediaPlayer.stop();
                    mediaPlayer.reset();
                }

                try {
                    mediaPlayer.setDataSource(path_file.getPath());
                    mediaPlayer.prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                mediaPlayer.start();
                Toast.makeText(RecordVoicePlayVoiceActivity.this, "Recording Playing", Toast.LENGTH_LONG).show();
            }
        });

    }
}