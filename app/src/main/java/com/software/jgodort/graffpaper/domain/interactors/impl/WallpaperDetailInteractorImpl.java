package com.software.jgodort.graffpaper.domain.interactors.impl;

import com.software.jgodort.graffpaper.domain.executor.Executor;
import com.software.jgodort.graffpaper.domain.executor.MainThread;
import com.software.jgodort.graffpaper.domain.interactors.WallpaperDetailInteractor;
import com.software.jgodort.graffpaper.domain.interactors.base.AbstractInteractor;
import com.software.jgodort.graffpaper.domain.repository.UnsplashRepository;
import com.software.jgodort.graffpaper.network.ResponseListener;
import com.software.jgodort.graffpaper.network.model.Image;

import java.util.List;

/**
 * Created by javie on 30/04/2017.
 */

public class WallpaperDetailInteractorImpl extends AbstractInteractor implements WallpaperDetailInteractor {

    private WallpaperDetailInteractor.GetUserPhotosCallback mCallback;
    private UnsplashRepository mUnsplashRepository;

    private String mUsername;

    public WallpaperDetailInteractorImpl(Executor threadExecutor, MainThread mainThread, UnsplashRepository repository, String username, WallpaperDetailInteractor.GetUserPhotosCallback callback) {
        super(threadExecutor, mainThread);
        mUnsplashRepository = repository;
        mUsername = username;
        mCallback=callback;
    }

    private void notifyError(final String error) {
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallback.onGetUserPhotosError(error);
            }
        });
    }

    private void postUserImages(final List<Image> userPhotos) {
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallback.onGetUserPhotosReceived(userPhotos);
            }
        });
    }

    @Override
    public void run() {


        ResponseListener listener = new ResponseListener() {
            @Override
            public void onSuccess(Object response) {
                List<Image> retrievedImages = (List<Image>) response;
                postUserImages(retrievedImages);
            }

            @Override
            public void onFailure(Object error) {

                String errorMessage = "TRAZA DE ERROR";
                notifyError(errorMessage);

            }
        };


        mUnsplashRepository.getUserPhotos(listener, mUsername);


    }

}
