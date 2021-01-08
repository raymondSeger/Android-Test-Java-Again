package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.example.myapplication.fragments.Fragment_With_Animation;

public class FragmentWithAnimationActivity extends AppCompatActivity implements Fragment_With_Animation.OnFragmentInteractionListener {

    private FrameLayout fragmentContainer;
    private EditText editText;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_with_animation);

        fragmentContainer   = (FrameLayout) findViewById(R.id.fragment_container);
        editText            = (EditText) findViewById(R.id.edittext);
        button              = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = editText.getText().toString();
                openFragment(text);
            }
        });
    }

    public void openFragment(String text) {
        Fragment_With_Animation fragment    = Fragment_With_Animation.newInstance(text);
        FragmentManager fragmentManager     = getSupportFragmentManager();
        FragmentTransaction transaction     = fragmentManager.beginTransaction();
        transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_right);
        transaction.addToBackStack(null);
        transaction.add(R.id.fragment_container, fragment, "Fragment_With_Animation").commit();
    }

    @Override
    public void onFragmentInteraction(String sendBackText) {
        editText.setText(sendBackText);
        onBackPressed();
    }
}