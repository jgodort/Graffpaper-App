package com.software.jgodort.graffpaper.presentation.ui.fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
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
import com.software.jgodort.graffpaper.network.model.Image;
import com.software.jgodort.graffpaper.presentation.presenters.TrendingWallpapersPresenter;
import com.software.jgodort.graffpaper.presentation.presenters.impl.TrendingWallpapersPresenterImpl;
import com.software.jgodort.graffpaper.presentation.ui.activities.WallpaperDetailActivity;
import com.software.jgodort.graffpaper.presentation.ui.adapters.WallpaperAdapter;
import com.software.jgodort.graffpaper.threading.MainThreadImpl;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by javie on 14/04/2017.
 */

public class TrendingWallpapersFragment extends Fragment implements TrendingWallpapersPresenter.View, WallpaperAdapter.OnClickHandler {


    @BindView(R.id.wallpaper_recycler)
    RecyclerView mWallpaperRecycler;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.emptyWallpaperLinear)
    LinearLayout mEmptyView;

    WallpaperAdapter mWallpaperAdapter;

    ProgressDialog mProgressDialog;

    @Inject
    UnsplashRepository mUnsplashRepository;

    private TrendingWallpapersPresenter mPresenter;

    private boolean hasImagesLoaded;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_trending_wallpapers, container, false);
        ButterKnife.bind(this, rootView);
        GraffpaperApplication.getApp().getRepositoriesComponent().inject(this);

        init();

        return rootView;
    }

    /**
     * Method to init the components of the fragments.
     */
    private void init() {
        mWallpaperRecycler.setLayoutManager(new GridLayoutManager(getContext(), 2));
        mWallpaperRecycler.setHasFixedSize(true);
        mWallpaperAdapter = new WallpaperAdapter(getContext(), mEmptyView, this);
        mWallpaperRecycler.setAdapter(mWallpaperAdapter);


        mPresenter = new TrendingWallpapersPresenterImpl(
                ThreadExecutor.getInstance(),
                MainThreadImpl.getInstance(),
                this,
                mUnsplashRepository);
    }


    @Override
    public void onResume() {
        super.onResume();
        //let´s start the presenter.
        mPresenter.resume();
    }

    @Override
    public void showProgress() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(getActivity());
        }

        mProgressDialog.setTitle("Downloading Wallpapers");
        mProgressDialog.setMessage("Please wait, we are downloading a bunch of pretty wallpapers.");
        mProgressDialog.setCancelable(false);
        mProgressDialog.show();
    }

    @Override
    public void hideProgress() {
        if (mProgressDialog != null) {
            mProgressDialog.hide();
        }

    }

    @Override
    public void showError(String message) {
        Snackbar.make(getView(),message,Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showMessage(String message) {
        Snackbar.make(getView(),message,Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void setWallpapersRetrieved(List<Image> wallpapers) {
        hasImagesLoaded = true;
        mWallpaperAdapter.setmImages(wallpapers);
        mWallpaperAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean isImagesLoaded() {
        return hasImagesLoaded;
    }


    public void onWallpaperImageSelected(Image image, WallpaperAdapter.ViewHolder vh) {
        Intent intent = new Intent(getActivity(), WallpaperDetailActivity.class);
        Bundle extras = new Bundle();
        extras.putParcelable(WallpaperImageDetailFragment.SELECTED_WALLPAPER, image);
        intent.putExtras(extras);
        startActivity(intent);
    }

}
