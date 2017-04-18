package com.software.jgodort.graffpaper.network.callback;

import android.util.Log;

import com.software.jgodort.graffpaper.network.ResponseListener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by javie on 08/04/2017.
 */

public class ResponseCallback<T> implements Callback<T> {

    private static final String TAG = ResponseCallback.class.getSimpleName();

    protected ResponseListener<T> listener;

    public ResponseCallback(ResponseListener<T> listener) {
        this.listener = listener;

    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (response.isSuccessful()) {
            Log.d(TAG, "onResponse: RequestCallback retrieve a successful response from the API");
            this.listener.onSuccess(response.body());
        } else {
            Log.e(TAG, "onResponse: ResquestCallback retrive an error response from the API: " + response.code() + "-->" + response.message());
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {

    }
}
