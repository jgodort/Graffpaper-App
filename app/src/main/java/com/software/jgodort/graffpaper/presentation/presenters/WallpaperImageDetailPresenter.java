package com.software.jgodort.graffpaper.presentation.presenters;

import android.content.Context;

import com.software.jgodort.graffpaper.network.model.Image;
import com.software.jgodort.graffpaper.presentation.presenters.base.BasePresenter;
import com.software.jgodort.graffpaper.presentation.ui.BaseView;

import java.util.List;

/**
 * Created by javie on 22/04/2017.
 */

public interface WallpaperImageDetailPresenter extends BasePresenter {

    interface View extends BaseView {

        void setTitle(String title);

        void setUserName(String username);

        void setUserBio(String userBio);

        void setUserLocation(String userLocation);

        void setUserPhotoThumbnail(String userPhotoUrl);

        void setWallpaperImage(String imageUrl);

        void setWallpaperRetrieved(List<Image> images);

        void setImageListener(Image image);

    }


    void setAsDeviceWallpaper();

    void getUserImages(String username);

    void setImageData(Image image);


}

