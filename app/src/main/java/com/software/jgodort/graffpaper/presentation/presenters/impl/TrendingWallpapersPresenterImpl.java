package com.software.jgodort.graffpaper.presentation.presenters.impl;

import com.software.jgodort.graffpaper.domain.executor.Executor;
import com.software.jgodort.graffpaper.domain.executor.MainThread;
import com.software.jgodort.graffpaper.domain.interactors.TrendingWallpapersInteractor;
import com.software.jgodort.graffpaper.domain.interactors.impl.TrendingWallpapersInteractorImpl;
import com.software.jgodort.graffpaper.domain.repository.UnsplashRepository;
import com.software.jgodort.graffpaper.network.model.Image;
import com.software.jgodort.graffpaper.presentation.presenters.TrendingWallpapersPresenter;
import com.software.jgodort.graffpaper.presentation.presenters.base.AbstractPresenter;

import java.util.List;

/**
 * Created by javie on 14/04/2017.
 */

public class TrendingWallpapersPresenterImpl extends AbstractPresenter
        implements TrendingWallpapersPresenter,
        TrendingWallpapersInteractor.GetPhotosCallback {


    private TrendingWallpapersPresenter.View mView;

    private UnsplashRepository mUnsplashRepository;


    public TrendingWallpapersPresenterImpl(Executor executor, MainThread mainThread, View view, UnsplashRepository repository) {
        super(executor, mainThread);
        mView = view;
        mUnsplashRepository = repository;

    }

    @Override
    public void resume() {
        if (!mView.isImagesLoaded()) {
            mView.showProgress();
            getTrendingWallpapers();
        }

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
        mView.showError(message);
    }

    @Override
    public void onGetPhotosRetrieved(List<Image> photos) {
        mView.hideProgress();
        mView.setWallpapersRetrieved(photos);

    }

    @Override
    public void onGetPhotosRetrieveError(String error) {
        mView.hideProgress();
        onError(error);
    }

    @Override
    public void getTrendingWallpapers() {
        TrendingWallpapersInteractor interactor = new TrendingWallpapersInteractorImpl(
                mExecutor,
                mMainThread,
                this,
                mUnsplashRepository);

        interactor.execute();
    }
}
