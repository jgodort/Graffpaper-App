package com.software.jgodort.graffpaper.network.callback;

import android.util.Log;

import com.software.jgodort.graffpaper.network.ResponseListener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Class to manage the Response of any call to the API.
 *
 * @param <T>
 */
public class ResponseCallback<T> implements Callback<T> {

    private static final String TAG = ResponseCallback.class.getSimpleName();

    private ResponseListener<T> listener;

    /**
     * Default constructor.
     *
     * @param listener to return the response.
     */
    public ResponseCallback(ResponseListener<T> listener) {
        this.listener = listener;

    }

    /**
     * Method to manage the response of the service.
     *
     * @param call     Object that represent the network call.
     * @param response Object that represent the response of the service.
     */
    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (response.isSuccessful()) {
            Log.d(TAG, "onResponse: RequestCallback retrieve a successful response from the API");
            this.listener.onSuccess(response.body());
        } else {
            Log.e(TAG, "onResponse: RequestCallback retrieve an error response from the API: " + response.code() + "-->" + response.message());
        }
    }

    /**
     * Method to manage the failures on the Api calls.
     *
     * @param call instance of <>{@link Call}</> with all the information about the error.
     * @param t    instance  of a throwable object.
     */
    @Override
    public void onFailure(Call<T> call, Throwable t) {

    }
}
