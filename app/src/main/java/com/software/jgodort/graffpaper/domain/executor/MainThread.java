package com.software.jgodort.graffpaper.domain.executor;

/**
 * Created by javie on 08/04/2017.
 */

/**
 * This interface will define a class that will enable interactors to run certain operations on the main (UI) thread.
 * For example, if an interactor needs to show an object to the UI this can be used to make sure the show methods is called
 * on the UI thread.
 */
public interface MainThread {


    /**
     * Make runnable operation run in the main thread.
     *
     * @param runnable the runnable to run.
     */
    void post(final Runnable runnable);

}
