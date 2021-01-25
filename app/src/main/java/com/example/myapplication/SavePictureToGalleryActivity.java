package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import static android.Manifest.permission.CAMERA;

public class SavePictureToGalleryActivity extends AppCompatActivity {

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    takePhotoAndSaveIt();
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2 && resultCode == RESULT_OK) {
            Bundle extras       = data.getExtras();
            Bitmap imageBitmap  = (Bitmap) extras.get("data");
            // Save image to gallery
            String savedImageURL = MediaStore.Images.Media.insertImage(
                    getContentResolver(),
                    imageBitmap,
                    "Raymond App",
                    "Raymond App Description"
            );

            // Parse the gallery image url to uri
            Uri savedImageURI = Uri.parse(savedImageURL);
            Toast.makeText(this, savedImageURI.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_picture_to_gallery);

        Button btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                takePhotoAndSaveIt();

            }
        });
    }

    private void takePhotoAndSaveIt() {

        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED ) {
            ActivityCompat.requestPermissions(SavePictureToGalleryActivity.this, new String[] { CAMERA }, 1);
            return;
        }

        try {
            startActivityForResult(takePictureIntent, 2);
        } catch (ActivityNotFoundException e) {
            // display error state to the user
        }

    }
}