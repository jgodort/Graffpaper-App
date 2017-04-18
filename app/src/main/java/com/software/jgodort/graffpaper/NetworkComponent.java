package com.software.jgodort.graffpaper;

import com.software.jgodort.graffpaper.network.UnsplashRepositoryImpl;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by javie on 16/04/2017.
 */

@Singleton
@Component(modules = {RetrofitModule.class})
public interface NetworkComponent {
    void inject(UnsplashRepositoryImpl repository);
}
