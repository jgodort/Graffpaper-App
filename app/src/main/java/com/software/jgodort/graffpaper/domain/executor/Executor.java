package com.software.jgodort.graffpaper.domain.executor;

/**
 * Created by javie on 08/04/2017.
 */


import com.software.jgodort.graffpaper.domain.interactors.base.AbstractInteractor;

/**
 * This executor is responsible for running interactors on background threads.
 */
public interface Executor {

    /**
     * This method should call the interactor´s run method
     * and thus start the interactor.
     * This should be called on a background thread as interactors
     * might do lengthy operations.
     */
    void execute(final AbstractInteractor interactor);
}
