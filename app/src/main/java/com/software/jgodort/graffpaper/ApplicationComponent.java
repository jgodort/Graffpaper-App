package com.software.jgodort.graffpaper;

import com.software.jgodort.graffpaper.domain.interactors.impl.ApplyWallpaperInteractorImpl;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by javie on 07/05/2017.
 */

@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {

    void inject(ApplyWallpaperInteractorImpl applyWallpaperInteractor);
}
