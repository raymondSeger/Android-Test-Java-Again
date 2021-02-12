package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.play.core.review.ReviewInfo;
import com.google.android.play.core.review.ReviewManager;
import com.google.android.play.core.review.ReviewManagerFactory;
import com.google.android.play.core.review.testing.FakeReviewManager;
import com.google.android.play.core.tasks.Task;

public class InAppReviewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_app_review);

        ReviewManager manager = new FakeReviewManager(getBaseContext());
        Task<ReviewInfo> request = manager.requestReviewFlow();

        request.addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                // We can get the ReviewInfo object
                ReviewInfo reviewInfo = task.getResult();

                Task<Void> flow = manager.launchReviewFlow(this, reviewInfo);
                flow.addOnCompleteListener(task2 -> {
                    // The flow has finished. The API does not indicate whether the user
                    // reviewed or not, or even whether the review dialog was shown. Thus, no
                    // matter the result, we continue our app flow.
                    Toast.makeText(this, "done", Toast.LENGTH_SHORT).show();
                });

            } else {
                // There was some problem, continue regardless of the result.
            }
        });



    }
}