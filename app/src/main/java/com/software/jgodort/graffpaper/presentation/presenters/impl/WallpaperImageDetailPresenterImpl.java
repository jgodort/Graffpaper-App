package com.software.jgodort.graffpaper.presentation.presenters.impl;

import android.app.WallpaperManager;
import android.content.Context;

import com.bumptech.glide.Glide;
import com.software.jgodort.graffpaper.domain.executor.Executor;
import com.software.jgodort.graffpaper.domain.executor.MainThread;
import com.software.jgodort.graffpaper.domain.interactors.WallpaperDetailInteractor;
import com.software.jgodort.graffpaper.domain.interactors.impl.WallpaperDetailInteractorImpl;
import com.software.jgodort.graffpaper.domain.repository.UnsplashRepository;
import com.software.jgodort.graffpaper.network.UnsplashAPI;
import com.software.jgodort.graffpaper.presentation.presenters.WallpaperImageDetailPresenter;
import com.software.jgodort.graffpaper.presentation.presenters.base.AbstractPresenter;

/**
 * Created by javie on 22/04/2017.
 */

public class WallpaperImageDetailPresenterImpl extends AbstractPresenter implements WallpaperImageDetailPresenter{

    private WallpaperImageDetailPresenter.View  mView;

    private UnsplashRepository mUnsplashRepository;


    public WallpaperImageDetailPresenterImpl(Executor executor, MainThread mainThread,View view,UnsplashRepository repository) {
        super(executor, mainThread);
        mUnsplashRepository=repository;
    }

    @Override
    public void resume() {

        getUserImages("");

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


    @Override
    public void setAsDeviceWallpaper(Context context) {


        WallpaperManager mWallpaperManager= WallpaperManager.getInstance(context);

       // mWallpaperManager.setBitmap(Glide.with(context).load("").asBitmap());
    }

    @Override
    public void getUserImages(String username) {

        WallpaperDetailInteractor interactor=new WallpaperDetailInteractorImpl(
                mExecutor,
                mMainThread,
                mUnsplashRepository,
                username);

        interactor.execute();


    }


}
