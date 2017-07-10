package com.software.jgodort.graffpaper.presentation.presenters;

import com.software.jgodort.graffpaper.network.model.Collection;
import com.software.jgodort.graffpaper.presentation.presenters.base.BasePresenter;
import com.software.jgodort.graffpaper.presentation.ui.BaseView;

import java.util.List;

/**
 * Created by javie on 02/07/2017.
 */

public interface CollectionsPresenter extends BasePresenter {


    interface View extends BaseView{

        void setCollectionRetrieved(List<Collection> collections);
    }


    void getCollections();
}
