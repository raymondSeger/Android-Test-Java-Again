package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;

import java.util.ArrayList;

public class GetAllContactActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_all_contact);

        // getContactList();

        ArrayList<ContactItem> a = getReadContacts();
        // debug here
        int ab = 0;
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

        private String displayName;
        private String photoUrl;
        private ArrayList<PhoneContact> arrayListPhone = new ArrayList<>();
        private ArrayList<EmailContact> arrayListEmail = new ArrayList<>();
        private ArrayList<PostalAddress> arrayListAddress = new ArrayList<>();

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
        private String email = "";

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }

    public class PhoneContact {
        private String phone="";

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }
    }


    public class PostalAddress {
        private String city     = "";
        private String state    = "";
        private String country  = "";

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