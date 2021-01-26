package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.auth.api.credentials.Credential;
import com.google.android.gms.auth.api.credentials.Credentials;
import com.google.android.gms.auth.api.credentials.HintRequest;

import java.util.Objects;

public class ShowDialogToChooseNumberActivity extends AppCompatActivity {

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 47) {// Obtain the phone number from the result
            if (resultCode == RESULT_OK) {
                Credential credential = data.getParcelableExtra(Credential.EXTRA_KEY);
                //credential.getId();  <-- will need to process phone number string
                Toast.makeText(this, "credentials\n" + Objects.requireNonNull(credential).getId(), Toast.LENGTH_SHORT).show();
            }
        } else if (requestCode == 48) {// Obtain the email from the result
            if (resultCode == RESULT_OK) {
                Credential credential = data.getParcelableExtra(Credential.EXTRA_KEY);
                //credential.getId();  <-- will need to process email string
                Toast.makeText(this, "credentials\n" + Objects.requireNonNull(credential).getId(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_dialog_to_choose_number);

        Button get_phone_btn = (Button) findViewById(R.id.get_phone_btn);
        Button get_email_btn = (Button) findViewById(R.id.get_email_btn);

        get_phone_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HintRequest hintRequest = new HintRequest.Builder()
                        .setPhoneNumberIdentifierSupported(true)
                        .build();
                PendingIntent intent = Credentials.getClient(ShowDialogToChooseNumberActivity.this).getHintPickerIntent(hintRequest);
                try {
                    startIntentSenderForResult(intent.getIntentSender(), 47, null, 0, 0, 0);
                } catch (IntentSender.SendIntentException e) {
                    e.printStackTrace();
                }
            }
        });

        get_email_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // get email
                HintRequest hintRequest2 = new HintRequest.Builder()
                        .setEmailAddressIdentifierSupported(true)
                        .build();
                PendingIntent intent2 = Credentials.getClient(ShowDialogToChooseNumberActivity.this).getHintPickerIntent(hintRequest2);
                try {
                    startIntentSenderForResult(intent2.getIntentSender(), 48, null, 0, 0, 0);
                } catch (IntentSender.SendIntentException e) {
                    e.printStackTrace();
                }

            }
        });

    }
}