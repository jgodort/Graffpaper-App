package com.software.jgodort.graffpaper.domain.interactors.impl;

import com.software.jgodort.graffpaper.domain.executor.Executor;
import com.software.jgodort.graffpaper.domain.executor.MainThread;
import com.software.jgodort.graffpaper.domain.interactors.CollectionsInteractor;
import com.software.jgodort.graffpaper.domain.interactors.base.AbstractInteractor;
import com.software.jgodort.graffpaper.domain.repository.UnsplashRepository;
import com.software.jgodort.graffpaper.network.ResponseListener;
import com.software.jgodort.graffpaper.network.model.Collection;

import java.util.List;

/**
 * Created by jgodort on 9/07/17.
 */

public class CollectionsInteractorImpl extends AbstractInteractor implements CollectionsInteractor {

    private CollectionsInteractor.GetCollectionsCallback mCallback;
    private UnsplashRepository mUnsplashRepository;


    public CollectionsInteractorImpl(Executor threadExecutor, MainThread mainThread, CollectionsInteractor.GetCollectionsCallback callback, UnsplashRepository repository) {
        super(threadExecutor, mainThread);
        this.mCallback = callback;
        this.mUnsplashRepository = repository;
    }


    private void notifyError(final String error) {
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallback.onGetCollectionsRetrieveError(error);
            }
        });
    }

    private void postCollections(final List<Collection> collections) {
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallback.onGetCollectionsRetrieved(collections);
            }
        });
    }

    @Override
    public void run() {

        ResponseListener listener = new ResponseListener() {
            @Override
            public void onSuccess(Object response) {
                List<Collection> retrievedCollections = (List<Collection>) response;
                postCollections(retrievedCollections);
            }

            @Override
            public void onFailure(Object error) {
                //TODO: GET THE PROPER ERROR
                String errorMessage = "Traza de Error";
                notifyError(errorMessage);
            }
        };


        mUnsplashRepository.getCollections(listener);
    }
}
