package com.software.jgodort.graffpaper.network;

/**
 * Created by javie on 08/04/2017.
 */


/**
 * This generic interface handle the response of the services through a listener.
 * @param <T>
 */
public interface ResponseListener<T> {

    void onSuccess(T response);

    void onFailure(T error);

}
