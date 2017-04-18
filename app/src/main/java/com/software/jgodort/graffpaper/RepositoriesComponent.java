package com.software.jgodort.graffpaper;

import com.software.jgodort.graffpaper.presentation.ui.fragments.TrendingWallpapersFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by javie on 16/04/2017.
 */

@Singleton
@Component(modules = {RepositoriesModule.class})
public interface RepositoriesComponent {

    void inject(TrendingWallpapersFragment trendingWallpapersFragment);
}
