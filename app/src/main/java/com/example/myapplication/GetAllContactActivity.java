package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.objects.ActivityObject;

import java.util.ArrayList;

import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.READ_CONTACTS;
import static android.view.View.generateViewId;

public class GetAllContactActivity extends AppCompatActivity implements View.OnClickListener {

    public LinearLayout linear_layout;

    @Override
    public void onClick(View view) {
        // retrieve secret message
        String message = (String) view.getTag();

        // show a message with the button's ID
        Toast toast = Toast.makeText(GetAllContactActivity.this, "You clicked " + message, Toast.LENGTH_LONG);
        toast.show();

        /*
        // get the parent layout and remove the clicked button
        LinearLayout parentLayout = (LinearLayout) view.getParent();
        parentLayout.removeView(view);
        */
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getContact();
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
        setContentView(R.layout.activity_get_all_contact);

        linear_layout = (LinearLayout) findViewById(R.id.linear_layout);

        if (ActivityCompat.checkSelfPermission(this, READ_CONTACTS) != PackageManager.PERMISSION_GRANTED ) {
            ActivityCompat.requestPermissions(GetAllContactActivity.this, new String[] { READ_CONTACTS }, 1);
            return;
        }

        getContact();

    }

    private void getContact() {
        // getContactList();
        ArrayList<ContactItem> the_list = getReadContacts();

        // debug here
        int ab = 0;

        for (ContactItem the_contact: the_list) {
            String displayName  = the_contact.displayName;
            String photoUrl     = the_contact.photoUrl;
            String address      = "";
            String email        = "";
            String phone        = "";

            for (PostalAddress arrayList: the_contact.getArrayListAddress()) {
                address += arrayList.getCountry() + " | " + arrayList.getState() + " | " + arrayList.getCity() + "\n";
            }

            for (EmailContact arrayList: the_contact.getArrayListEmail()) {
                email += arrayList.getEmail() + "\n";
            }

            for (PhoneContact arrayList: the_contact.getArrayListPhone() ) {
                phone += arrayList.getPhone() + "\n";
            }

            String text_to_show = String.format(" name is %s.\n Photo is %s.\n Address is %s.\n Email is %s.\n Phone is %s", displayName, photoUrl, address, email, phone);

            TextView text_view = new TextView(this);
            text_view.setText(text_to_show);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            params.setMargins(15, 15, 15, 0);
            text_view.setLayoutParams(params);

            // R.id won't be generated for us, so we need to create one
            text_view.setId(generateViewId());

            // enclose message
            text_view.setTag(displayName);

            // add our event handler (less memory than an anonymous inner class)
            text_view.setOnClickListener( this );
            // add generated button to view
            linear_layout.addView(text_view);

        }
    }

    private void getContactList() {
        ContentResolver cr = getContentResolver();
        Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);

        if ((cur != null ? cur.getCount() : 0) > 0) {
            while (cur != null && cur.moveToNext()) {
                String id   = cur.getString( cur.getColumnIndex(ContactsContract.Contacts._ID));
                String name = cur.getString(cur.getColumnIndex( ContactsContract.Contacts.DISPLAY_NAME));

                if (cur.getInt(cur.getColumnIndex( ContactsContract.Contacts.HAS_PHONE_NUMBER)) > 0) {
                    Cursor pCur = cr.query( ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?", new String[]{id}, null);
                    while (pCur.moveToNext()) {
                        String phoneNo = pCur.getString(pCur.getColumnIndex( ContactsContract.CommonDataKinds.Phone.NUMBER));
                        Log.i("TAG", "Name: " + name);
                        Log.i("TAG", "Phone Number: " + phoneNo);
                    }
                    pCur.close();
                }
            }
        }
        if(cur!=null){
            cur.close();
        }
    }

    // GET CONTACT LIST WITH ALL FIELD...
    public ArrayList<ContactItem> getReadContacts() {
        ArrayList<ContactItem> contactList  = new ArrayList<>();
        ContentResolver cr                  = getContentResolver();
        Cursor mainCursor                   = cr.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
        if (mainCursor != null) {
            while (mainCursor.moveToNext()) {
                ContactItem contactItem = new ContactItem();
                String id               = mainCursor.getString(mainCursor.getColumnIndex(ContactsContract.Contacts._ID));
                String displayName      = mainCursor.getString(mainCursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));

                Uri contactUri      = ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, Long.parseLong(id));
                Uri displayPhotoUri = Uri.withAppendedPath(contactUri, ContactsContract.Contacts.Photo.DISPLAY_PHOTO);

                //ADD NAME AND CONTACT PHOTO DATA...
                contactItem.setDisplayName(displayName);
                contactItem.setPhotoUrl(displayPhotoUri.toString());

                if (Integer.parseInt(mainCursor.getString(mainCursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) {
                    //ADD PHONE DATA...
                    ArrayList<PhoneContact> arrayListPhone = new ArrayList<>();
                    Cursor phoneCursor = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?", new String[] { id }, null);
                    if (phoneCursor != null) {
                        while (phoneCursor.moveToNext()) {
                            PhoneContact phoneContact   = new PhoneContact();
                            String phone                = phoneCursor.getString(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                            phoneContact.setPhone(phone);
                            arrayListPhone.add(phoneContact);
                        }
                    }

                    if (phoneCursor != null) {
                        phoneCursor.close();
                    }

                    contactItem.setArrayListPhone(arrayListPhone);

                    //ADD E-MAIL DATA...
                    ArrayList <EmailContact> arrayListEmail = new ArrayList<>();
                    Cursor emailCursor                      = cr.query(ContactsContract.CommonDataKinds.Email.CONTENT_URI, null, ContactsContract.CommonDataKinds.Email.CONTACT_ID + " = ?", new String[] { id }, null);

                    if (emailCursor != null) {
                        while (emailCursor.moveToNext()) {
                            EmailContact emailContact   = new EmailContact();
                            String email                = emailCursor.getString(emailCursor.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA));
                            emailContact.setEmail(email);
                            arrayListEmail.add(emailContact);
                        }
                    }

                    if (emailCursor != null) {
                        emailCursor.close();
                    }

                    contactItem.setArrayListEmail(arrayListEmail);

                    //ADD ADDRESS DATA...
                    ArrayList <PostalAddress> arrayListAddress = new ArrayList<>();
                    Cursor addrCursor = getContentResolver().query(ContactsContract.CommonDataKinds.StructuredPostal.CONTENT_URI, null, ContactsContract.CommonDataKinds.StructuredPostal.CONTACT_ID + " = ?", new String[] { id }, null);

                    if (addrCursor != null) {
                        while ( addrCursor.moveToNext() ) {
                            PostalAddress postalAddress = new PostalAddress();
                            String city                 = addrCursor.getString(addrCursor.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.CITY));
                            String state                = addrCursor.getString(addrCursor.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.REGION));
                            String country              = addrCursor.getString(addrCursor.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.COUNTRY));
                            postalAddress.setCity(city);
                            postalAddress.setState(state);
                            postalAddress.setCountry(country);
                            arrayListAddress.add(postalAddress);
                        }
                    }

                    if (addrCursor != null) {
                        addrCursor.close();
                    }

                    contactItem.setArrayListAddress(arrayListAddress);
                }
                contactList.add(contactItem);
            }
        }
        if (mainCursor != null) {
            mainCursor.close();
        }
        return contactList;
    }


    // MODEL...
    public class ContactItem {

        public String displayName;
        public String photoUrl;
        public ArrayList<PhoneContact> arrayListPhone = new ArrayList<>();
        public ArrayList<EmailContact> arrayListEmail = new ArrayList<>();
        public ArrayList<PostalAddress> arrayListAddress = new ArrayList<>();

        public String getDisplayName() {
            return displayName;
        }

        public void setDisplayName(String displayName) {
            this.displayName = displayName;
        }

        public String getPhotoUrl() {
            return photoUrl;
        }

        public void setPhotoUrl(String photoUrl) {
            this.photoUrl = photoUrl;
        }

        public ArrayList<PhoneContact> getArrayListPhone() {
            return arrayListPhone;
        }

        public void setArrayListPhone(ArrayList<PhoneContact> arrayListPhone) {
            this.arrayListPhone = arrayListPhone;
        }

        public ArrayList<EmailContact> getArrayListEmail() {
            return arrayListEmail;
        }

        public void setArrayListEmail(ArrayList<EmailContact> arrayListEmail) {
            this.arrayListEmail = arrayListEmail;
        }

        public ArrayList<PostalAddress> getArrayListAddress() {
            return arrayListAddress;
        }

        public void setArrayListAddress(ArrayList<PostalAddress> arrayListAddress) {
            this.arrayListAddress = arrayListAddress;
        }
    }

    public class EmailContact {
        public String email = "";

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }

    public class PhoneContact {
        public String phone="";

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }
    }


    public class PostalAddress {
        public String city     = "";
        public String state    = "";
        public String country  = "";

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }
    }

}