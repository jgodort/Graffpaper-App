package com.software.jgodort.graffpaper.presentation.ui.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.software.jgodort.graffpaper.R;
import com.software.jgodort.graffpaper.presentation.ui.fragments.WallpaperImageDetailFragment;

/**
 * Created by javie on 30/04/2017.
 */

public class WallpaperDetailActivity extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallpaper_detail);

        if (savedInstanceState == null) {

            Bundle arguments;
            arguments = getIntent().getExtras();

            if(arguments!=null){
                WallpaperImageDetailFragment fragment = WallpaperImageDetailFragment.newInstance();
                fragment.setArguments(arguments);

                getSupportFragmentManager().
                        beginTransaction().
                        add(R.id.wallpaper_detail_container, fragment).
                        commit();
            }

        }
    }


}
