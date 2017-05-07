package com.software.jgodort.graffpaper.domain.interactors;

import com.software.jgodort.graffpaper.domain.interactors.base.Interactor;
import com.software.jgodort.graffpaper.network.model.Image;

import java.util.List;

/**
 * Created by javie on 30/04/2017.
 */

public interface WallpaperDetailInteractor extends Interactor {

    interface GetUserPhotosCallback {
        void onGetUserPhotosReceived(List<Image> photos);

        void onGetUserPhotosError(String error);
    }

    interface SetWallpaperCallback{
        void onSetWallpaper(String message);
        void onSettedWallpaper();
        void onSetWallpaperError(String error);
    }


}
