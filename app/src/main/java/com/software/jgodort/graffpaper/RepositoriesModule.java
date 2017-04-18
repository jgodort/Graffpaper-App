package com.software.jgodort.graffpaper;

import com.software.jgodort.graffpaper.domain.repository.UnsplashRepository;
import com.software.jgodort.graffpaper.network.UnsplashRepositoryImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by javie on 16/04/2017.
 */

@Module
public class RepositoriesModule {



    public  RepositoriesModule(){

    }
    @Provides
    @Singleton
    UnsplashRepository providesUnsplashRepository() {
        return new UnsplashRepositoryImpl();
    }
}
