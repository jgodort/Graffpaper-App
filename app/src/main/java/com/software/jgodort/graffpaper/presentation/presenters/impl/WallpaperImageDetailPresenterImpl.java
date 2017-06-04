package com.software.jgodort.graffpaper.presentation.presenters.impl;

import com.software.jgodort.graffpaper.domain.executor.Executor;
import com.software.jgodort.graffpaper.domain.executor.MainThread;
import com.software.jgodort.graffpaper.domain.interactors.WallpaperDetailInteractor;
import com.software.jgodort.graffpaper.domain.interactors.impl.ApplyWallpaperInteractorImpl;
import com.software.jgodort.graffpaper.domain.interactors.impl.WallpaperDetailInteractorImpl;
import com.software.jgodort.graffpaper.domain.repository.UnsplashRepository;
import com.software.jgodort.graffpaper.network.model.Image;
import com.software.jgodort.graffpaper.presentation.presenters.WallpaperImageDetailPresenter;
import com.software.jgodort.graffpaper.presentation.presenters.base.AbstractPresenter;

import java.util.List;

/**
 * Created by javie on 22/04/2017.
 */

public class WallpaperImageDetailPresenterImpl extends AbstractPresenter implements WallpaperImageDetailPresenter, WallpaperDetailInteractor.GetUserPhotosCallback, WallpaperDetailInteractor.SetWallpaperCallback {

    private Image mImageSelected;

    private WallpaperImageDetailPresenter.View mView;

    private UnsplashRepository mUnsplashRepository;


    public WallpaperImageDetailPresenterImpl(Executor executor, MainThread mainThread, View view, UnsplashRepository repository) {
        super(executor, mainThread);
        mUnsplashRepository = repository;
        mView = view;
    }

    @Override
    public void resume() {
        if (mImageSelected != null) {
            getUserImages(mImageSelected.getUser().getUsername());
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

    }


    @Override
    public void setAsDeviceWallpaper() {
        ApplyWallpaperInteractorImpl interactor = new ApplyWallpaperInteractorImpl(
                mExecutor,
                mMainThread,
                mImageSelected.getUrls().getFull(),
                this);

        interactor.execute();
    }

    @Override
    public void getUserImages(String username) {

        WallpaperDetailInteractor interactor = new WallpaperDetailInteractorImpl(
                mExecutor,
                mMainThread,
                mUnsplashRepository,
                username,
                this);

        interactor.execute();


    }

    @Override
    public void setImageData(Image image) {
        if (image != null) {
            mImageSelected = image;
            mView.setTitle(mImageSelected.getUser().getName().concat("").concat(" Photo"));
            mView.setUserBio(mImageSelected.getUser().getBio());
            mView.setUserLocation(mImageSelected.getUser().getLocation());
            mView.setUserName(mImageSelected.getUser().getUsername());
            mView.setUserPhotoThumbnail(mImageSelected.getUser().getProfileImage().getLarge());
            mView.setWallpaperImage(mImageSelected.getUrls().getRegular());

        } else {
            mView.showError("There are some error obtaining the Image data.");
        }
    }


    @Override
    public void onGetUserPhotosReceived(List<Image> photos) {
        mView.setWallpaperRetrieved(photos);
    }

    @Override
    public void onGetUserPhotosError(String error) {
        mView.showError(error);
    }


    @Override
    public void onSetWallpaper(String message) {
        mView.showProgress();
    }

    @Override
    public void onSettedWallpaper() {
        mView.hideProgress();
        mView.showMessage("Wallpaper setted");
    }

    @Override
    public void onSetWallpaperError(String error) {
        mView.hideProgress();
        mView.showError(error);

    }
}
