package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TextWatcherActivity extends AppCompatActivity {

    private EditText editTextUsername;
    private EditText editTextPassword;
    private Button buttonConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_watcher);

        editTextUsername    = findViewById(R.id.edit_text_username);
        editTextPassword    = findViewById(R.id.edit_text_password);
        buttonConfirm       = findViewById(R.id.button_confirm);
        editTextUsername.addTextChangedListener(loginTextWatcher);
        editTextPassword.addTextChangedListener(loginTextWatcher);
    }

    private TextWatcher loginTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }
        
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String usernameInput = editTextUsername.getText().toString().trim();
            String passwordInput = editTextPassword.getText().toString().trim();
            buttonConfirm.setEnabled(!usernameInput.isEmpty() && !passwordInput.isEmpty());
        }
        
        @Override
        public void afterTextChanged(Editable s) {
            Toast.makeText(TextWatcherActivity.this, "After Text Changed " + s.toString() , Toast.LENGTH_SHORT).show();
        }
        
    };
}