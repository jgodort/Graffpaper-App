package com.software.jgodort.graffpaper;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by javie on 16/04/2017.
 */

@Module
public class ApplicationModule {

    GraffpaperApplication mGraffpaperApplication;

    public ApplicationModule(GraffpaperApplication application){
        mGraffpaperApplication=application;
    }

    @Provides
    @Singleton
    GraffpaperApplication providesApplication(){
        return mGraffpaperApplication;
    }

    @Provides
    @Singleton
    SharedPreferences providesSharedPreferences(){
        return PreferenceManager.getDefaultSharedPreferences(mGraffpaperApplication);
    }

}
