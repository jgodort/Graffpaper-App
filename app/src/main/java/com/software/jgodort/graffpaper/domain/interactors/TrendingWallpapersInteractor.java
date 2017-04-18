package com.software.jgodort.graffpaper.domain.interactors;

import com.software.jgodort.graffpaper.domain.interactors.base.Interactor;
import com.software.jgodort.graffpaper.network.model.Image;

import java.util.List;

/**
 * Created by javie on 14/04/2017.
 */

public interface TrendingWallpapersInteractor extends Interactor {

    interface GetPhotosCallback{
        void onGetPhotosRetrieved(List<Image> photos);

        void onGetPhotosRetrieveError(String error);

    }
}
