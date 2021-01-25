package com.example.myapplication;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.Manifest.permission.CAMERA;

public class PhotoActivity extends AppCompatActivity {

    public ActivityResultLauncher<Intent> mStartForResult_for_gallery;
    public ImageView image_view;

    String currentPhotoPath;

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 47 && resultCode == RESULT_OK) {
            // show the picture
            Picasso.get().load( new File(currentPhotoPath) ).into(image_view, new Callback() {
                @Override
                public void onSuccess() {
                    Toast.makeText(getApplicationContext(), "success", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onError(Exception e) {
                    Toast.makeText(getApplicationContext(), "failed to load image", Toast.LENGTH_SHORT).show();
                }
            });

            // add the photo to gallery - deprecated way
            Intent mediaScanIntent  = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
            File f                  = new File(currentPhotoPath);
            Uri contentUri          = Uri.fromFile(f);
            mediaScanIntent.setData(contentUri);
            sendBroadcast(mediaScanIntent);

            // add the photo to gallery - new way
            MediaScannerConnection.scanFile(getBaseContext(),
                    new String[]{f.toString()},
                    new String[]{f.getName()},null);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);

        Button from_web_button          = (Button) findViewById(R.id.from_web_button);
        Button from_gallery_button      = (Button) findViewById(R.id.from_gallery_button);
        Button from_take_photo_button   = (Button) findViewById(R.id.from_take_photo_button);
        image_view                      = (ImageView) findViewById(R.id.image_view);

        mStartForResult_for_gallery = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        final Intent the_intent = result.getData();
                        Picasso.get().load( the_intent.getData() ).into(image_view);
                    }
                }
        });

        from_gallery_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                mStartForResult_for_gallery.launch(intent);
            }
        });

        from_take_photo_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (ActivityCompat.checkSelfPermission(getBaseContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED ) {
                    ActivityCompat.requestPermissions(PhotoActivity.this, new String[] { CAMERA }, 1);
                    return;
                }

                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {

                    // Create the File where the photo should go
                    try {
                        File photoFile = createImageFile();

                        // Continue only if the File was successfully created
                        if (photoFile != null) {
                            Uri photoURI = FileProvider.getUriForFile( getBaseContext(),
                                    "com.example.myapplication.fileprovider", photoFile);
                            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                            startActivityForResult(takePictureIntent, 47);
                        }

                    } catch (IOException ex) {
                        // Error occurred while creating the File
                    }


                }
            }
        });

        from_web_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Picasso.get().load("https://cdn.mos.cms.futurecdn.net/LHcG66TebAwFGeaXRMRh7n.jpg").into(image_view, new Callback() {
                    @Override
                    public void onSuccess() {
                        Toast.makeText(getApplicationContext(), "success", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Exception e) {
                        Toast.makeText(getApplicationContext(), "failed to load image", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

    }
}