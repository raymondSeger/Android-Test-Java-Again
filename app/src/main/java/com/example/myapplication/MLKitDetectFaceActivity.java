package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.PointF;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.face.Face;
import com.google.mlkit.vision.face.FaceContour;
import com.google.mlkit.vision.face.FaceDetection;
import com.google.mlkit.vision.face.FaceDetector;
import com.google.mlkit.vision.face.FaceDetectorOptions;
import com.google.mlkit.vision.face.FaceLandmark;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class MLKitDetectFaceActivity extends AppCompatActivity {

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
            faceDetector( s );
        }
    }

    private void faceDetector(String s) {
        InputImage image;
        try {

            // High-accuracy landmark detection and face classification
            FaceDetectorOptions highAccuracyOpts =
                    new FaceDetectorOptions.Builder()
                            .setPerformanceMode(FaceDetectorOptions.PERFORMANCE_MODE_ACCURATE)
                            .setLandmarkMode(FaceDetectorOptions.LANDMARK_MODE_ALL)
                            .setClassificationMode(FaceDetectorOptions.CLASSIFICATION_MODE_ALL)
                            .build();

            File imgFile                = new  File(s);
            image                       = InputImage.fromFilePath(getApplicationContext(), Uri.parse(String.valueOf(imgFile.toURI())));
            FaceDetector detector       = FaceDetection.getClient(highAccuracyOpts);

            Task<List<Face>> result = detector.process(image)
                .addOnSuccessListener(
                        new OnSuccessListener<List<Face>>() {
                            @Override
                            public void onSuccess(List<Face> faces) {
                                // Task completed successfully

                                for (Face face : faces) {
                                    Rect bounds = face.getBoundingBox();
                                    float rotY = face.getHeadEulerAngleY();  // Head is rotated to the right rotY degrees
                                    float rotZ = face.getHeadEulerAngleZ();  // Head is tilted sideways rotZ degrees

                                    // If landmark detection was enabled (mouth, ears, eyes, cheeks, and
                                    // nose available):
                                    FaceLandmark leftEar = face.getLandmark(FaceLandmark.LEFT_EAR);
                                    if (leftEar != null) {
                                        PointF leftEarPos = leftEar.getPosition();
                                    }

                                    // If contour detection was enabled:
                                    List<PointF> leftEyeContour =
                                            face.getContour(FaceContour.LEFT_EYE).getPoints();
                                    List<PointF> upperLipBottomContour =
                                            face.getContour(FaceContour.UPPER_LIP_BOTTOM).getPoints();

                                    // If classification was enabled:
                                    if (face.getSmilingProbability() != null) {
                                        float smileProb = face.getSmilingProbability();
                                    }
                                    if (face.getRightEyeOpenProbability() != null) {
                                        float rightEyeOpenProb = face.getRightEyeOpenProbability();
                                    }

                                    // If face tracking was enabled:
                                    if (face.getTrackingId() != null) {
                                        int id = face.getTrackingId();
                                    }
                                }

                                Toast.makeText(MLKitDetectFaceActivity.this, "face detected", Toast.LENGTH_SHORT).show();

                            }
                        })
                .addOnFailureListener(
                        new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                // Task failed with an exception
                                Toast.makeText(MLKitDetectFaceActivity.this, "failed", Toast.LENGTH_SHORT).show();
                            }
                        });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ml_kit_detect_face);

        if (ActivityCompat.checkSelfPermission(this, WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ) {
            ActivityCompat.requestPermissions(MLKitDetectFaceActivity.this, new String[] { WRITE_EXTERNAL_STORAGE }, 1);
            return;
        }

        pickPicture();

    }
}