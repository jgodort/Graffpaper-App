package com.software.jgodort.graffpaper.presentation.presenters.impl;

import com.software.jgodort.graffpaper.domain.executor.Executor;
import com.software.jgodort.graffpaper.domain.executor.MainThread;
import com.software.jgodort.graffpaper.presentation.presenters.WallpaperImageDetailPresenter;
import com.software.jgodort.graffpaper.presentation.presenters.base.AbstractPresenter;

/**
 * Created by javie on 22/04/2017.
 */

public class WallpaperImageDetailPresenterImpl extends AbstractPresenter implements WallpaperImageDetailPresenter{

    private WallpaperImageDetailPresenter.View  mView;


    public WallpaperImageDetailPresenterImpl(Executor executor, MainThread mainThread) {
        super(executor, mainThread);
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void onError(String message) {

    }
}
