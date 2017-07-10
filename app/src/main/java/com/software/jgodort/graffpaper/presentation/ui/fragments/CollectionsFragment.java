package com.software.jgodort.graffpaper.presentation.ui.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.software.jgodort.graffpaper.GraffpaperApplication;
import com.software.jgodort.graffpaper.R;
import com.software.jgodort.graffpaper.domain.executor.impl.ThreadExecutor;
import com.software.jgodort.graffpaper.domain.repository.UnsplashRepository;
import com.software.jgodort.graffpaper.network.model.Collection;
import com.software.jgodort.graffpaper.presentation.presenters.CollectionsPresenter;
import com.software.jgodort.graffpaper.presentation.presenters.impl.CollectionsPresenterImpl;
import com.software.jgodort.graffpaper.presentation.ui.adapters.CollectionAdapter;
import com.software.jgodort.graffpaper.threading.MainThreadImpl;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by javie on 02/07/2017.
 */

public class CollectionsFragment extends Fragment implements CollectionsPresenter.View {


    @BindView(R.id.collection_recycler)
    RecyclerView mCollectionsRecycler;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.emptyCollectionsLinear)
    LinearLayout mEmptyView;

    CollectionAdapter mCollectionAdapter;

    ProgressDialog mProgressDialog;

    private CollectionsPresenter mPresenter;

    @Inject
    UnsplashRepository mUnsplashRepository;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_collections, container, false);
        ButterKnife.bind(this, rootView);
        GraffpaperApplication.getApp().getRepositoriesComponent().inject(this);
        init();

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.resume();

    }

    /**
     * Method to init the components of the fragments.
     */
    private void init() {
        mCollectionsRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        mCollectionsRecycler.setHasFixedSize(true);
        mCollectionAdapter = new CollectionAdapter(getContext(), mEmptyView);
        mCollectionsRecycler.setAdapter(mCollectionAdapter);

        mPresenter = new CollectionsPresenterImpl(
                ThreadExecutor.getInstance(),
                MainThreadImpl.getInstance(),
                this,
                mUnsplashRepository);

    }

    @Override
    public void showProgress() {

        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(getActivity());
        }

        mProgressDialog.setTitle("Downloading Collections");
        mProgressDialog.setMessage("Please wait, we are looking for some Collections.");
        mProgressDialog.setCancelable(false);
        mProgressDialog.show();
    }

    @Override
    public void hideProgress() {

        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    public void showError(String message) {
        Snackbar.make(getView(), message, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showMessage(String message) {
        Snackbar.make(getView(), message, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void setCollectionRetrieved(List<Collection> collections) {
        mCollectionAdapter.setmCollections(collections);
        mCollectionAdapter.notifyDataSetChanged();
    }
}
