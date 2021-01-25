package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class FileSaveReadDeleteActivity extends AppCompatActivity {

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    writeFile();
                } else {
                    // Permission Denied
                    Toast.makeText(getBaseContext(), "not allowed, app should exit", Toast.LENGTH_SHORT).show();
                }
                break;
            case 2:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    readFile();
                } else {
                    // Permission Denied
                    Toast.makeText(getBaseContext(), "not allowed, app should exit", Toast.LENGTH_SHORT).show();
                }
                break;
            case 3:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    deleteFile();
                } else {
                    // Permission Denied
                    Toast.makeText(getBaseContext(), "not allowed, app should exit", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_save_read_delete);

        Button save_btn     = (Button) findViewById(R.id.save_btn);
        Button read_btn     = (Button) findViewById(R.id.read_btn);
        Button delete_btn   = (Button) findViewById(R.id.delete_btn);

        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityCompat.checkSelfPermission(getBaseContext(), WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getBaseContext(), READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ) {
                    ActivityCompat.requestPermissions(FileSaveReadDeleteActivity.this, new String[] { WRITE_EXTERNAL_STORAGE, READ_EXTERNAL_STORAGE }, 1);
                    return;
                }
                writeFile();
            }
        });

        read_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityCompat.checkSelfPermission(getBaseContext(), WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getBaseContext(), READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ) {
                    ActivityCompat.requestPermissions(FileSaveReadDeleteActivity.this, new String[] { WRITE_EXTERNAL_STORAGE, READ_EXTERNAL_STORAGE }, 2);
                    return;
                }
                readFile();
            }
        });

        delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityCompat.checkSelfPermission(getBaseContext(), WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getBaseContext(), READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ) {
                    ActivityCompat.requestPermissions(FileSaveReadDeleteActivity.this, new String[] { WRITE_EXTERNAL_STORAGE, READ_EXTERNAL_STORAGE }, 3);
                    return;
                }
                deleteFile();
            }
        });

    }

    private void writeFile() {
        String sFileName = "raymond.txt";
        String sBody     = "the content";

        File dir = new File(getBaseContext().getFilesDir(), "mydir");
        if(!dir.exists()){
            dir.mkdir();
        }

        try {
            File gpxfile        = new File(dir, sFileName);
            FileWriter writer   = new FileWriter(gpxfile);
            writer.append(sBody);
            writer.flush();
            writer.close();
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    private void readFile() {
        String sFileName    = "raymond.txt";
        File dir            = new File(getBaseContext().getFilesDir(), "mydir");

        try {
            FileInputStream fin = new FileInputStream ( new File( dir.getAbsolutePath() + "/" + sFileName ) );
            int c;
            String temp="";

            while( (c = fin.read()) != -1){
                temp = temp + Character.toString((char)c);
            }

            Toast.makeText(getBaseContext(), temp, Toast.LENGTH_SHORT).show();
        }
        catch(Exception e){
        }
    }

    private void deleteFile() {

        String sFileName    = "raymond.txt";
        File dir            = new File(getBaseContext().getFilesDir(), "mydir");
        File fdelete        = new File( dir.getAbsolutePath() + "/" + sFileName );

        if (fdelete.exists()) {
            if (fdelete.delete()) {
                Toast.makeText(this, "file deleted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "file not deleted", Toast.LENGTH_SHORT).show();
            }
        }
    }

}