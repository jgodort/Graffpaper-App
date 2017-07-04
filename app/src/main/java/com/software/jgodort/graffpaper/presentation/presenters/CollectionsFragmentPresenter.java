package com.software.jgodort.graffpaper.presentation.presenters;

import com.software.jgodort.graffpaper.presentation.presenters.base.BasePresenter;
import com.software.jgodort.graffpaper.presentation.ui.BaseView;

/**
 * Created by javie on 02/07/2017.
 */

public interface CollectionsFragmentPresenter extends BasePresenter {


    interface View extends BaseView{

    }


    void getCollections();
}
