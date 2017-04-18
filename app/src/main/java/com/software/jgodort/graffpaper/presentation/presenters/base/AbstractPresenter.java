package com.software.jgodort.graffpaper.presentation.presenters.base;


import com.software.jgodort.graffpaper.RepositoriesComponent;
import com.software.jgodort.graffpaper.domain.executor.Executor;
import com.software.jgodort.graffpaper.domain.executor.MainThread;


/**
 * Created by javie on 08/04/2017.
 */

public abstract class AbstractPresenter {

    protected Executor mExecutor;
    protected MainThread mMainThread;

    private RepositoriesComponent mRepositoriesComponent;


    public AbstractPresenter( Executor executor, MainThread mainThread){
        mExecutor=executor;
        mMainThread=mainThread;

    }
}
