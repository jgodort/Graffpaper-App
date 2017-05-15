package com.software.jgodort.graffpaper.domain.interactors.impl;

import android.app.WallpaperManager;
import android.content.Context;

import com.bumptech.glide.Glide;
import com.software.jgodort.graffpaper.GraffpaperApplication;
import com.software.jgodort.graffpaper.domain.executor.Executor;
import com.software.jgodort.graffpaper.domain.executor.MainThread;
import com.software.jgodort.graffpaper.domain.interactors.WallpaperDetailInteractor;
import com.software.jgodort.graffpaper.domain.interactors.base.AbstractInteractor;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import javax.inject.Inject;

/**
 * Created by javie on 07/05/2017.
 */

public class ApplyWallpaperInteractorImpl extends AbstractInteractor implements WallpaperDetailInteractor {

    @Inject
    Context mContext;

    private WallpaperDetailInteractor.SetWallpaperCallback mCallback;
    private String mImageUrl;

    public ApplyWallpaperInteractorImpl(Executor threadExecutor, MainThread mainThread, String imageUrl, SetWallpaperCallback callback) {
        super(threadExecutor, mainThread);
        mImageUrl = imageUrl;
        mCallback = callback;
        GraffpaperApplication.getApp().getApplicationComponent().inject(this);

    }

    private void onSetDeviceWallpaper() {
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallback.onSetWallpaper("Setting Wallpaper...");
            }
        });

    }

    private void onSetDeviceWallpaperError() {
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallback.onSettedWallpaper();
                mCallback.onSetWallpaperError("An error occurs while setting the wallpaper.");
            }
        });

    }

    private void onSettedWallpaper() {
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallback.onSettedWallpaper();
            }
        });
    }


    @Override
    public void run() {
        onSetDeviceWallpaper();

        WallpaperManager wallpaperManager = WallpaperManager.getInstance(mContext);

        try {
            wallpaperManager.setBitmap(
                    Glide.with(mContext).
                            load(mImageUrl).
                            asBitmap().
                            into(-1, -1).
                            get());

            onSettedWallpaper();
        } catch (IOException |
                InterruptedException |
                ExecutionException e) {

            onSetDeviceWallpaperError();
        }

    }


}
