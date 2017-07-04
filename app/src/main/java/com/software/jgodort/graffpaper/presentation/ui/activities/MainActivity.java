package com.software.jgodort.graffpaper.presentation.ui.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.software.jgodort.graffpaper.R;
import com.software.jgodort.graffpaper.presentation.ui.adapters.FragmentAdapter;
import com.software.jgodort.graffpaper.presentation.ui.fragments.TrendingWallpapersFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by javie on 02/07/2017.
 */

/**
 * Main activity with the DrawerLayout and the viewPager.
 */
public class MainActivity extends AppCompatActivity {


    @BindView(R.id.drawerlayout)
    DrawerLayout mDrawerLayout;


    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.viewPager)
    ViewPager mViewPager;

    @BindView(R.id.tabs)
    TabLayout mTabLayout;

    @BindView(R.id.nav_view)
    NavigationView mNavigationView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);

        final ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        actionBar.setDisplayHomeAsUpEnabled(true);

        if (mNavigationView != null) {
            setupDrawerContent(mNavigationView);
        }

        if (mViewPager != null) {
            setupDrawerContent(mViewPager);
        }


        mTabLayout.setupWithViewPager(mViewPager);
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                item.setChecked(true);
                mDrawerLayout.closeDrawers();
                return true;
            }
        });
    }

    /**
     * Method that setup the ViewPager and fill it with the proper fragments.
     *
     * @param viewPager ViewPager to contain the fragments.
     */
    private void setupDrawerContent(ViewPager viewPager) {
        FragmentAdapter adapter = new FragmentAdapter(getSupportFragmentManager());
        adapter.addFragment(new TrendingWallpapersFragment(), getString(R.string.trending_tab_text));
        adapter.addFragment(new TrendingWallpapersFragment(), getString(R.string.curated_tab_text));
        adapter.addFragment(new TrendingWallpapersFragment(), getString(R.string.collection_tab_text));
        viewPager.setAdapter(adapter);
    }


}
