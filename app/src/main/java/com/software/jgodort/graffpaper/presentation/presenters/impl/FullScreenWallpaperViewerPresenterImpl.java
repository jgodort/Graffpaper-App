package com.software.jgodort.graffpaper.presentation.presenters.impl;

import com.software.jgodort.graffpaper.domain.executor.Executor;
import com.software.jgodort.graffpaper.domain.executor.MainThread;
import com.software.jgodort.graffpaper.network.model.Image;
import com.software.jgodort.graffpaper.presentation.presenters.FullScreenWallpaperViewerPresenter;
import com.software.jgodort.graffpaper.presentation.presenters.base.AbstractPresenter;

/**
 * Presenter to manage the business logic related with
 * <>{@link com.software.jgodort.graffpaper.presentation.ui.fragments.FullScreenWallpaperViewerFragment}</>
 *
 */
public class FullScreenWallpaperViewerPresenterImpl extends AbstractPresenter implements FullScreenWallpaperViewerPresenter {

    private FullScreenWallpaperViewerPresenter.View mView;

    private Image mImageFullScreen;

    public FullScreenWallpaperViewerPresenterImpl(Executor executor, MainThread mainThread, View view, Image image) {
        super(executor, mainThread);
        mView = view;
        mImageFullScreen = image;

    }

    @Override
    public void resume() {
        mView.setImageFullScreen(mImageFullScreen.getUrls().getFull());
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
