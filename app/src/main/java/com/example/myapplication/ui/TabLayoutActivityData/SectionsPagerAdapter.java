package com.example.myapplication.ui.TabLayoutActivityData;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    private final Context mContext;
    private String[] the_tab_titles;

    public SectionsPagerAdapter(Context context, FragmentManager fm, String[] the_data) {
        super(fm);
        mContext        = context;
        the_tab_titles  = the_data;
    }


    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle bundle = new Bundle();
        bundle.putString("data_for_fragment", the_tab_titles[position]);
        fragment.setArguments(bundle);
        return fragment;

        /*
        Use this if you want to use custom fragments

        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new Frag1();
                break;
            case 1:
                fragment = new Frag2();
                break;
            case 2:
                fragment = new Frag3();
                break;
        }
        return fragment;
         */

    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return the_tab_titles[position];
    }

    @Override
    public int getCount() {
        return the_tab_titles.length;
    }
}