package com.software.jgodort.graffpaper.presentation.presenters.base;

/**
 * Created by javie on 08/04/2017.
 */

/**
 * This interface define the common methods that every Presenter should implement.
 */
public interface BasePresenter {


    /**
     * Method that controls the lifecycle of the view. It should be called in the view´s
     * (Activity or Fragment) onResume() method.
     */
    void resume();

    /**
     * Method that controls the lifecycle of the view. It should be called in the view´s
     * (Activity or Fragment) onPause() method.
     */
    void pause();

    /**
     * Method that controls the lifecycle of the view. It should be called in the view´s
     * (Activity or Fragment) onStop() method.
     */
    void stop();

    /**
     * Method that controls the lifecycle of the view. It should be called in the view´s
     * (Activity or Fragment) onDestroy() method.
     */
    void  destroy();

    /**
     * Method that should signal the appropriate view to show the appropriate error with
     * the provided message.
     * @param message
     */
    void onError(String message);
}
