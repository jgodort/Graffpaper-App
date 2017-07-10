package com.software.jgodort.graffpaper.network;

import com.software.jgodort.graffpaper.BuildConfig;
import com.software.jgodort.graffpaper.GraffpaperApplication;
import com.software.jgodort.graffpaper.domain.repository.UnsplashRepository;
import com.software.jgodort.graffpaper.network.callback.ResponseCallback;
import com.software.jgodort.graffpaper.network.model.Collection;
import com.software.jgodort.graffpaper.network.model.Image;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;

/**
 * Created by javie on 14/04/2017.
 */

public class UnsplashRepositoryImpl implements UnsplashRepository {

    private static final String TAG = UnsplashRepository.class.getSimpleName();

    private String mApplicationId;

    @Inject
    UnsplashAPI unsplashAPI;


    public UnsplashRepositoryImpl() {
        this.mApplicationId = BuildConfig.UNSPLASH_CLIENT_ID;
        GraffpaperApplication.getApp().getmNetworkComponent().inject(this);
    }

    @Override
    public void getTrendingPhotos(ResponseListener listener) {
        ResponseCallback<List<Image>> responseCallback = new ResponseCallback<>(listener);
        Call<List<Image>> call = unsplashAPI.getPhotos(mApplicationId);
        call.enqueue(responseCallback);
    }

    @Override
    public void getUserPhotos(ResponseListener listener, String username) {
        ResponseCallback<List<Image>> responseCallback = new ResponseCallback<>(listener);
        Call<List<Image>> call = unsplashAPI.getUserPhotos(username, mApplicationId);
        call.enqueue(responseCallback);
    }

    @Override
    public void getCollections(ResponseListener listener) {
        ResponseCallback<List<Collection>> responseCallback = new ResponseCallback<>(listener);
        Call<List<Collection>> call = unsplashAPI.getCollections(mApplicationId);
        call.enqueue(responseCallback);
    }
}
