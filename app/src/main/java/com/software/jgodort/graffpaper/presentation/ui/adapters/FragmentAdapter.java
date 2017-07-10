package com.software.jgodort.graffpaper.presentation.ui.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by javie on 02/07/2017.
 */

public class FragmentAdapter extends FragmentStatePagerAdapter {


    private final List<Fragment> mFragments = new ArrayList<>();
    private final List<String> mFrgamentTitles = new ArrayList<>();

    public FragmentAdapter(FragmentManager fm) {
        super(fm);
    }


    public void addFragment(Fragment fragment, String title) {
        mFragments.add(fragment);
        mFrgamentTitles.add(title);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFrgamentTitles.get(position);
    }
}
