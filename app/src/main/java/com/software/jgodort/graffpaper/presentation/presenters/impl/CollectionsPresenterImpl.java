package com.software.jgodort.graffpaper.presentation.presenters.impl;

import com.software.jgodort.graffpaper.domain.executor.Executor;
import com.software.jgodort.graffpaper.domain.executor.MainThread;
import com.software.jgodort.graffpaper.domain.interactors.CollectionsInteractor;
import com.software.jgodort.graffpaper.domain.interactors.impl.CollectionsInteractorImpl;
import com.software.jgodort.graffpaper.domain.repository.UnsplashRepository;
import com.software.jgodort.graffpaper.network.model.Collection;
import com.software.jgodort.graffpaper.presentation.presenters.CollectionsPresenter;
import com.software.jgodort.graffpaper.presentation.presenters.base.AbstractPresenter;

import java.util.List;

/**
 * Presenter to manage the business logic of the collectionsFragment.
 * Created by jgodort on 9/07/17.
 */
public class CollectionsPresenterImpl extends AbstractPresenter
        implements CollectionsPresenter,
        CollectionsInteractor.GetCollectionsCallback {


    private CollectionsPresenter.View mView;

    private UnsplashRepository mUnsplashRepository;


    public CollectionsPresenterImpl(Executor executor, MainThread mainThread, View view, UnsplashRepository repository) {
        super(executor, mainThread);
        this.mView = view;
        this.mUnsplashRepository = repository;
    }


    @Override
    public void resume() {
        getCollections();
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
    public void onGetCollectionsRetrieved(List<Collection> collections) {

        mView.hideProgress();
        mView.setCollectionRetrieved(collections);

    }

    @Override
    public void onGetCollectionsRetrieveError(String error) {
        mView.hideProgress();
        onError(error);
    }


    @Override
    public void getCollections() {

        CollectionsInteractor interactor = new CollectionsInteractorImpl(
                mExecutor,
                mMainThread,
                this,
                mUnsplashRepository);

        interactor.execute();

    }
}
