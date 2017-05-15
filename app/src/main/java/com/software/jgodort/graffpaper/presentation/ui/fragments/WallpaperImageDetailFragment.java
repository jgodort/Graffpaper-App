package com.software.jgodort.graffpaper.presentation.ui.fragments;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.github.jorgecastilloprz.FABProgressCircle;
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

    @BindView(R.id.fabProgressCircle)
    FABProgressCircle fabProgressCircle;

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
        //initialize the presenter.
        init();
        //Obtain the image from the bundle.
        Bundle bundle = getArguments();
        if (bundle != null) {
            selectedImage = bundle.getParcelable(SELECTED_WALLPAPER);
            mPresenter.setImageData(selectedImage);
        }

        return rootView;

    }

    /**
     * Method to initialize the requiered components of the fragment.
     */
    private void init() {

        mPresenter = new WallpaperImageDetailPresenterImpl(
                ThreadExecutor.getInstance(),
                MainThreadImpl.getInstance(),
                this,
                mUnsplashRepository);


        mApplyWallpaperButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.setAsDeviceWallpaper();
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

        fabProgressCircle.show();
//        if (mProgressDialog == null) {
//            mProgressDialog = new ProgressDialog(getActivity());
//        }
//
//        mProgressDialog.setTitle("Setting as Wallpaper");
//        mProgressDialog.setMessage("Ohhh dude!! This is going to looks awesome");
//        mProgressDialog.setCancelable(false);
//        mProgressDialog.show();
    }

    @Override
    public void hideProgress() {

        if (fabProgressCircle != null) {
            fabProgressCircle.beginFinalAnimation();
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

        if (userLocation != null && !userLocation.isEmpty()) {
            mProfileLocation.setText(userLocation);
        } else {
            mProfileLocation.setText(getString(R.string.location_unknown));
        }
    }

    @Override
    public void setUserPhotoThumbnail(String userPhotoUrl) {

        Glide.with(getContext()).load(userPhotoUrl).asBitmap().centerCrop().into(new BitmapImageViewTarget(mProfileImage) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(getContext().getResources(), resource);
                circularBitmapDrawable.setCircular(true);
                mProfileImage.setImageDrawable(circularBitmapDrawable);
            }
        });
    }

    @Override
    public void setWallpaperImage(String imageUrl) {

        Glide.with(this).
                load(imageUrl).
                centerCrop().
                into(mWallpaperImage);
    }

}
