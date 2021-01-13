package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class InsertNewContactActivity extends AppCompatActivity {

    private EditText emailAddress = null;
    private EditText phoneNumber = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_new_contact);

        /* Assumes EditText fields in your UI contain an email address
         * and a phone number.
         *
         */
        emailAddress    = (EditText) findViewById(R.id.email);
        phoneNumber     = (EditText) findViewById(R.id.phone);
        Button button   = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Creates a new Intent to insert a contact
                Intent intent = new Intent(ContactsContract.Intents.Insert.ACTION);
                // Sets the MIME type to match the Contacts Provider
                intent.setType(ContactsContract.RawContacts.CONTENT_TYPE);

                /*
                 * Inserts new data into the Intent. This data is passed to the
                 * contacts app's Insert screen
                 */

                // Inserts an email address
                intent.putExtra(ContactsContract.Intents.Insert.EMAIL, emailAddress.getText())

                    /*
                     * In this example, sets the email type to be a work email.
                     * You can set other email types as necessary.
                     */

                .putExtra(ContactsContract.Intents.Insert.EMAIL_TYPE, ContactsContract.CommonDataKinds.Email.TYPE_WORK)
                // Inserts a phone number
                .putExtra(ContactsContract.Intents.Insert.PHONE, phoneNumber.getText())
                    /*
                     * In this example, sets the phone type to be a work phone.
                     * You can set other phone types as necessary.
                     */
                .putExtra(ContactsContract.Intents.Insert.PHONE_TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_WORK);

                // Sends the Intent
                startActivity(intent);

            }
        });

    }
}