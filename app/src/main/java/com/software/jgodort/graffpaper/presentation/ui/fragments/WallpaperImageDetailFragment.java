package com.software.jgodort.graffpaper.presentation.ui.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.software.jgodort.graffpaper.R;
import com.software.jgodort.graffpaper.domain.executor.impl.ThreadExecutor;
import com.software.jgodort.graffpaper.domain.repository.UnsplashRepository;
import com.software.jgodort.graffpaper.network.model.Image;
import com.software.jgodort.graffpaper.presentation.presenters.WallpaperImageDetailPresenter;
import com.software.jgodort.graffpaper.presentation.presenters.impl.WallpaperImageDetailPresenterImpl;
import com.software.jgodort.graffpaper.threading.MainThreadImpl;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by javie on 22/04/2017.
 */

public class WallpaperImageDetailFragment extends Fragment implements WallpaperImageDetailPresenter.View {

    public static final String SELECTED_WALLPAPER = "SLTWLPR";

    private Image selectedImage;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.title_wallpaper)
    TextView mWallpaperTitle;

    @BindView(R.id.fabWallpaper)
    FloatingActionButton mApplyWallpaperButton;

    @BindView(R.id.wallpaper_image)
    ImageView mWallpaperImage;

    @BindView(R.id.profile_image)
    ImageView mProfileImage;

    @BindView(R.id.user_name)
    TextView mUsername;

    @BindView(R.id.user_bio)
    TextView mUserBio;

    @BindView(R.id.location)
    TextView mProfileLocation;

    ProgressDialog mProgressDialog;

    @Inject
    UnsplashRepository mUnsplashRepository;


    private WallpaperImageDetailPresenter mPresenter;

    //Default constructor
    public WallpaperImageDetailFragment() {

    }

    public static WallpaperImageDetailFragment newInstance() {
        return new WallpaperImageDetailFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_wallpaper_detail, container, false);

        ButterKnife.bind(this, rootView);
        Bundle bundle = getArguments();
        if (selectedImage != null) {
            selectedImage=bundle.getParcelable(SELECTED_WALLPAPER);
        }

        init();
        return rootView;

    }

    /**
     * Method to initialize the requiered components of the fragment.
     */
    private void init() {

        mPresenter = new WallpaperImageDetailPresenterImpl(
                ThreadExecutor.getInstance(),
                MainThreadImpl.getInstance(),
                mUnsplashRepository);


        mApplyWallpaperButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.setAsDeviceWallpaper(getContext());
            }
        });
    }


    @Override
    public void onResume() {
        super.onResume();
        mPresenter.resume();
    }


    @Override
    public void showProgress() {

        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(getActivity());
        }

        mProgressDialog.setTitle("Setting as Wallpaper");
        mProgressDialog.setMessage("Ohhh dude!! This is going to looks awesome");
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

    }

    @Override
    public void setTitle(String title) {
        mWallpaperTitle.setText(title);
    }

    @Override
    public void setUserName(String username) {
        mUsername.setText(username);
    }

    @Override
    public void setUserBio(String userBio) {
        mUserBio.setText(userBio);
    }

    @Override
    public void setUserLocation(String userLocation) {

        mProfileLocation.setText(userLocation);
    }

    @Override
    public void setUserPhotoThumbnail(String userPhotoUrl) {

        Glide.
                with(this).
                load(userPhotoUrl).
                centerCrop().
                into(mProfileImage);
    }

    @Override
    public void setWallpaperImage(String imageUrl) {

        Glide.
                with(this).
                load(imageUrl).
                centerCrop().
                into(mWallpaperImage);
    }


}
