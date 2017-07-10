package com.software.jgodort.graffpaper.domain.interactors;

import com.software.jgodort.graffpaper.domain.interactors.base.Interactor;
import com.software.jgodort.graffpaper.network.model.Collection;

import java.util.List;

/**
 * Created by jgodort on 9/07/17.
 */

public interface CollectionsInteractor extends Interactor {

    interface GetCollectionsCallback {

        void onGetCollectionsRetrieved(List<Collection> collections);

        void onGetCollectionsRetrieveError(String error);
    }
}
