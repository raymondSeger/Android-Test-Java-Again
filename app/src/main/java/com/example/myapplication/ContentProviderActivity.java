package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.UserDictionary;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.providers.StudentsProvider;

public class ContentProviderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_provider);
    }

    public void onClickAddName(View view) {
        // Add a new student record
        ContentValues values = new ContentValues();
        values.put(StudentsProvider.NAME, ((EditText)findViewById(R.id.editText2)).getText().toString());
        values.put(StudentsProvider.GRADE, ((EditText)findViewById(R.id.editText3)).getText().toString());
        Uri uri = getContentResolver().insert( StudentsProvider.CONTENT_URI, values);
        Toast.makeText(getBaseContext(), uri.toString(), Toast.LENGTH_LONG).show();
    }

    public void onClickDeleteName(View view) {
        String selectionClause  = StudentsProvider.NAME + " LIKE ?";
        String the_name         = ( (EditText) findViewById(R.id.editText2) ).getText().toString();
        String[] selectionArgs  = { the_name }; // will search by name
        // Defines a variable to contain the number of rows deleted
        int rowsDeleted         = 0;

        // delete a new student record
        ContentValues values = new ContentValues();
        values.put(StudentsProvider.NAME, ((EditText)findViewById(R.id.editText2)).getText().toString());
        values.put(StudentsProvider.GRADE, ((EditText)findViewById(R.id.editText3)).getText().toString());
        rowsDeleted = getContentResolver().delete( StudentsProvider.CONTENT_URI, selectionClause, selectionArgs);
        Toast.makeText(getBaseContext(), String.valueOf(rowsDeleted), Toast.LENGTH_LONG).show();
    }

    public void onClickUpdateStudents(View view) {
        // Defines an object to contain the updated values
        ContentValues updateValues = new ContentValues();
        updateValues.put(StudentsProvider.NAME, ((EditText)findViewById(R.id.editText2)).getText().toString());
        updateValues.put(StudentsProvider.GRADE, ((EditText)findViewById(R.id.editText3)).getText().toString());

        // Defines selection criteria for the rows you want to update
        String selectionClause  = StudentsProvider.NAME +  " LIKE ?";
        String the_name         = ( (EditText) findViewById(R.id.editText2) ).getText().toString();
        String[] selectionArgs  = { the_name };

        // Defines a variable to contain the number of updated rows
        int rowsUpdated = 0;
        rowsUpdated     = getContentResolver().update(
                StudentsProvider.CONTENT_URI,   // the user dictionary content URI
                updateValues,                      // the columns to update
                selectionClause,                   // the column to select on
                selectionArgs                      // the value to compare to
        );

        Toast.makeText(getBaseContext(), String.valueOf(rowsUpdated), Toast.LENGTH_LONG).show();
    }

    public void onClickRetrieveStudents(View view) {
        // Retrieve student records
        String URL = "content://com.example.myapplication.StudentsProvider";

        Uri students    = Uri.parse(URL);
        Cursor c        = managedQuery(students, null, null, null, "name");

        if (c.moveToFirst()) {
            do{
                Toast.makeText(this,
                        c.getString(c.getColumnIndex(StudentsProvider._ID)) +
                                ", " +  c.getString(c.getColumnIndex( StudentsProvider.NAME)) +
                                ", " + c.getString(c.getColumnIndex( StudentsProvider.GRADE)),
                        Toast.LENGTH_SHORT).show();
            } while (c.moveToNext());
        }
    }

}