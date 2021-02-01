package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.fragments.CustomFragmentForTutorial;
import com.viewpagerindicator.CirclePageIndicator;

import mehdi.sakout.fancybuttons.FancyButton;

public class CirclePageIndicatorActivity extends AppCompatActivity {

    public ViewPager pager;
    public CirclePageIndicator dot_indicator;
    public Button pick_language_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle_page_indicator);

        dot_indicator           = (CirclePageIndicator)findViewById(R.id.dot_indicator);
        pager                   = (ViewPager) findViewById(R.id.pager);
        pick_language_button    = (Button) findViewById(R.id.pick_language_button);

        pick_language_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getBaseContext(), "Text", Toast.LENGTH_SHORT).show();
            }
        });

        // bind the ViewPager to the Adapters that show the fragments
        pager.setAdapter(new TutorialPagerAdapter(getSupportFragmentManager()));
        dot_indicator.setViewPager(pager);
    }

    public class TutorialPagerAdapter extends FragmentStatePagerAdapter {
        public TutorialPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            if(i == 0 ) {
                pick_language_button.setVisibility(View.GONE);
                Fragment fragment = new CustomFragmentForTutorial(R.drawable.starting_point_3, "Buzzing", "Serunya bertemu dengan teman yang memiliki minat yang sama");
                return fragment;
            } else if (i == 1 ) {
                pick_language_button.setVisibility(View.GONE);
                Fragment fragment = new CustomFragmentForTutorial(R.drawable.starting_point_6, "Interest", "Temukan informasi berdasarkan minatmu");
                return fragment;
            } else if (i == 2 ) {
                pick_language_button.setVisibility(View.GONE);
                Fragment fragment = new CustomFragmentForTutorial(R.drawable.starting_point_1, "Polling", "Tanyakan apapun dan dapat jawaban terbanyak dari temanmu");
                return fragment;
            } else if (i == 3 ) {
                pick_language_button.setVisibility(View.VISIBLE);
                Fragment fragment = new CustomFragmentForTutorial(R.drawable.starting_point_2, "Update Terus", "Bagikan dan ceritakan momen serumu ke semua");
                return fragment;
            }

            // default one that will not be used
            Fragment fragment = new CustomFragmentForTutorial();
            return fragment;
        }

        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            // we don't use title because we will only show pictures
            return "";
        }
    }

}