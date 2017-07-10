package com.software.jgodort.graffpaper.domain.interactors.impl;

import com.software.jgodort.graffpaper.domain.executor.Executor;
import com.software.jgodort.graffpaper.domain.executor.MainThread;
import com.software.jgodort.graffpaper.domain.interactors.TrendingWallpapersInteractor;
import com.software.jgodort.graffpaper.domain.interactors.base.AbstractInteractor;
import com.software.jgodort.graffpaper.domain.repository.UnsplashRepository;
import com.software.jgodort.graffpaper.network.ResponseListener;
import com.software.jgodort.graffpaper.network.model.Image;

import java.util.List;

/**
 * Created by javie on 16/04/2017.
 */

public class TrendingWallpapersInteractorImpl extends AbstractInteractor implements TrendingWallpapersInteractor {

    private TrendingWallpapersInteractor.GetPhotosCallback mCallback;
    private UnsplashRepository mUnsplashRepository;

    public TrendingWallpapersInteractorImpl(Executor threadExecutor, MainThread mainThread, GetPhotosCallback callback, UnsplashRepository repository) {
        super(threadExecutor, mainThread);
        mCallback = callback;
        mUnsplashRepository = repository;
    }

    /**
     * Method that notify the error to the main thread.
     *
     * @param error
     */
    private void notifyError(final String error) {
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallback.onGetPhotosRetrieveError(error);
            }
        });
    }

    /**
     * Method that returns the list of wallpapers to the main thread.
     *
     * @param wallpapers
     */
    private void postWallpapers(final List<Image> wallpapers) {
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallback.onGetPhotosRetrieved(wallpapers);
            }
        });
    }

    @Override
    public void run() {

        //Create the listener to retrieve the response of the service.
        ResponseListener listener=new ResponseListener() {
            @Override
            public void onSuccess(Object response) {
                //we have retrieved our list of wallpapers, notify the UI on the main Thread.
                List<Image> retrievedImages=(List<Image>) response;
                postWallpapers(retrievedImages);
            }

            @Override
            public void onFailure(Object error) {
                //TODO: GET THE PROPER ERROR
                String errorMessage = "TRAZA DE PRUEBA";
                notifyError(errorMessage);
            }
        };

        mUnsplashRepository.getTrendingPhotos(listener);

    }
}
