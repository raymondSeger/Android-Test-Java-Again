package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Point;
import android.graphics.Rect;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.text.Text;
import com.google.mlkit.vision.text.TextRecognition;
import com.google.mlkit.vision.text.TextRecognizer;

import java.io.File;
import java.io.IOException;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class MLTextRecognitionActivity extends AppCompatActivity {

    // get the path
    public String getRealPathFromURI(Uri uri) {
        String[] projection = { MediaStore.Images.Media.DATA };
        @SuppressWarnings("deprecation")
        Cursor cursor       = managedQuery(uri, projection, null, null, null);
        int column_index    = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    pickPicture();
                } else {
                    // Permission Denied
                    Toast.makeText(getBaseContext(), "not allowed, app should exit", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    private void pickPicture() {
        Intent pickPhoto = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(pickPhoto , 1);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Uri selectedImageUri    = data.getData();
            String s                = getRealPathFromURI(selectedImageUri);
            textDetector( s );
        }
    }

    private void textDetector(String s) {
        InputImage image;
        try {
            File imgFile                = new  File(s);
            image                       = InputImage.fromFilePath(getApplicationContext(), Uri.parse(String.valueOf(imgFile.toURI())));
            TextRecognizer recognizer   = TextRecognition.getClient();
            Task<Text> result           = recognizer.process(image).addOnSuccessListener(new OnSuccessListener<Text>() {
                    @Override
                    public void onSuccess(Text visionText) {
                        // Task completed successfully
                        String resultText = visionText.getText();
                        Toast.makeText(MLTextRecognitionActivity.this, resultText, Toast.LENGTH_SHORT).show();
                        for (Text.TextBlock block : visionText.getTextBlocks()) {
                            String blockText            = block.getText();
                            Point[] blockCornerPoints   = block.getCornerPoints();
                            Rect blockFrame             = block.getBoundingBox();
                            for (Text.Line line : block.getLines()) {
                                String lineText             = line.getText();
                                Point[] lineCornerPoints    = line.getCornerPoints();
                                Rect lineFrame              = line.getBoundingBox();
                                for (Text.Element element : line.getElements()) {
                                    String elementText          = element.getText();
                                    Point[] elementCornerPoints = element.getCornerPoints();
                                    Rect elementFrame           = element.getBoundingBox();
                                }
                            }
                        }

                    }
                })
                .addOnFailureListener(
                    new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            // Task failed with an exception
                            Toast.makeText(MLTextRecognitionActivity.this, "failed", Toast.LENGTH_SHORT).show();
                        }
                    });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ml_text_recognition);

        if (ActivityCompat.checkSelfPermission(this, WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ) {
            ActivityCompat.requestPermissions(MLTextRecognitionActivity.this, new String[] { WRITE_EXTERNAL_STORAGE }, 1);
            return;
        }

        pickPicture();
    }
}