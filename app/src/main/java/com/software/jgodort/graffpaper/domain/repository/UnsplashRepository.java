package com.software.jgodort.graffpaper.domain.repository;

import com.software.jgodort.graffpaper.network.ResponseListener;

/**
 * Created by javie on 14/04/2017.
 */

public interface UnsplashRepository {

    void getTrendingPhotos(ResponseListener listener);

    void getUserPhotos(ResponseListener listener, String username);

    void getCollections(ResponseListener listener);
}
