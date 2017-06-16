package com.software.jgodort.graffpaper.presentation.presenters;

import com.software.jgodort.graffpaper.presentation.presenters.base.BasePresenter;

/**
 * Created by javie on 10/06/2017.
 */

public interface FullScreenWallpaperViewerPresenter extends BasePresenter{

    interface View{
        void setImageFullScreen(String wallpaperUrl);
        void displaySnackBar();
    }
}
