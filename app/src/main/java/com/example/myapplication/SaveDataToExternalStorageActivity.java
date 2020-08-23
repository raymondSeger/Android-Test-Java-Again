package com.example.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.Array;

public class SaveDataToExternalStorageActivity extends AppCompatActivity {

    // Checks if a volume containing external storage is available
    // for read and write.
    private boolean isExternalStorageWritable() {
        return Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED;
    }

    // Checks if a volume containing external storage is available to at least read.
    private boolean isExternalStorageReadable() {
        return Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED ||
                Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED_READ_ONLY;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.save_data_to_external_storage_activity);

        Button button       = (Button) findViewById(R.id.the_button);
        Button the_button2  = (Button) findViewById(R.id.the_button2);
        Button the_button3  = (Button) findViewById(R.id.the_button3);
        Button the_button4  = (Button) findViewById(R.id.the_button4);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(isExternalStorageWritable() ) {

                    String filename     = "myfile";
                    String fileContents = "Hello world!";
                    try (FileOutputStream fos = getApplicationContext().openFileOutput(filename, Context.MODE_PRIVATE)) {
                        fos.write( fileContents.getBytes() );
                        Toast.makeText(view.getContext(), "file written", Toast.LENGTH_SHORT).show();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }

            }
        });

        the_button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if( isExternalStorageReadable() ) {

                    String filename = "myfile";
                    FileInputStream fis = null;

                    try {
                        fis = getApplicationContext().openFileInput(filename);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

                    InputStreamReader inputStreamReader = new InputStreamReader(fis, StandardCharsets.UTF_8);
                    StringBuilder stringBuilder         = new StringBuilder();

                    try (BufferedReader reader = new BufferedReader(inputStreamReader)) {
                        String line = reader.readLine();
                        Toast.makeText(view.getContext(), line, Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        // Error occurred when opening raw file for reading.
                    } finally {
                        String contents = stringBuilder.toString();
                    }

                }

            }
        });

        the_button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(isExternalStorageWritable() ) {
                    String filename = "myfile";
                    getApplicationContext().deleteFile( filename );
                    Toast.makeText(view.getContext(), "file deleted", Toast.LENGTH_SHORT).show();
                }

            }
        });

        the_button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(isExternalStorageWritable() ) {
                    String[] files = getApplicationContext().fileList();
                    for (String file : files)
                    {
                        Toast.makeText(view.getContext(), file, Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

    }
}