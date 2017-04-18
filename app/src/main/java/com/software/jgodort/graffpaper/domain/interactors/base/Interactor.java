package com.software.jgodort.graffpaper.domain.interactors.base;

/**
 * Created by javie on 08/04/2017.
 */


/**
 * This is the main interface of an interactor.
 * Each interactor serves a specific use case.
 */
public interface Interactor {


    /**
     * This is the main method tha starts an interactor. It will make sure that
     * the interactor operation is done on ab background thread.
     */
    void execute();

}
