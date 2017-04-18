package com.software.jgodort.graffpaper.presentation.presenters;

import com.software.jgodort.graffpaper.network.model.Image;
import com.software.jgodort.graffpaper.presentation.presenters.base.BasePresenter;
import com.software.jgodort.graffpaper.presentation.ui.BaseView;

import java.util.List;

/**
 * Created by javie on 14/04/2017.
 */

public interface TrendingWallpapersPresenter extends BasePresenter {


    interface  View extends BaseView{

       void setWallpapersRetrieved(List<Image> wallpapers);
    }

    void getTrendingWallpapers();

}
