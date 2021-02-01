package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class CopyPasteActivity extends AppCompatActivity {

    ClipboardManager clipboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_copy_paste);
        Button button = (Button) findViewById(R.id.button);
        Button button2 = (Button) findViewById(R.id.button2);

        clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Creates a new text clip to put on the clipboard
                ClipData clip = ClipData.newPlainText("simple text", "Hello, World!");
                clipboard.setPrimaryClip(clip);
                Toast.makeText(CopyPasteActivity.this, "data inserted to clipboard", Toast.LENGTH_SHORT).show();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    ClipData.Item item  = clipboard.getPrimaryClip().getItemAt(0);
                    String pasteData    = (String) item.getText();
                    Toast.makeText(CopyPasteActivity.this, pasteData, Toast.LENGTH_SHORT).show();
                } catch(Exception e) {
                }


            }
        });

    }
}