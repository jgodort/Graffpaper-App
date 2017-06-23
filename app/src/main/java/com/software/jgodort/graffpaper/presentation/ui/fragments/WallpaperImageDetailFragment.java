package com.software.jgodort.graffpaper.presentation.ui.fragments;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.github.jorgecastilloprz.FABProgressCircle;
import com.software.jgodort.graffpaper.GraffpaperApplication;
import com.software.jgodort.graffpaper.R;
import com.software.jgodort.graffpaper.domain.executor.impl.ThreadExecutor;
import com.software.jgodort.graffpaper.domain.repository.UnsplashRepository;
import com.software.jgodort.graffpaper.network.model.Image;
import com.software.jgodort.graffpaper.presentation.presenters.FullScreenWallpaperViewerPresenter;
import com.software.jgodort.graffpaper.presentation.presenters.WallpaperImageDetailPresenter;
import com.software.jgodort.graffpaper.presentation.presenters.impl.WallpaperImageDetailPresenterImpl;
import com.software.jgodort.graffpaper.presentation.ui.adapters.WallpaperAdapter;
import com.software.jgodort.graffpaper.threading.MainThreadImpl;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Fragment that represent the wallpaper and user details.
 * This fragment delegate the logic to the <>{@link WallpaperImageDetailPresenter}</>
 */
public class WallpaperImageDetailFragment extends Fragment implements WallpaperImageDetailPresenter.View, WallpaperAdapter.OnClickHandler {

    /**
     * Key to extract the selected image from the Bundle.
     */
    public static final String SELECTED_WALLPAPER = "SLTWLPR";

    public static final String FULLSCREEN_PREVIEW = "FSPWLPR";

    /**
     * Adapter with the user images.
     */
    private WallpaperAdapter mUserWallpaperAdapter;

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


    /**
     * RecyclerView to manage the list of related images previously uploaded by the user.
     */
    @BindView(R.id.wallpaper_detail_photos)
    RecyclerView mRecyclerViewUserPhotos;

    /**
     * View to display when there no user data.
     */
    @BindView(R.id.emptyWallpaperLinear)
    View mEmptyView;

    /**
     * Dependency of the Image Repository.
     */
    @Inject
    UnsplashRepository mUnsplashRepository;


    private WallpaperImageDetailPresenter mPresenter;

    //Default constructor
    public WallpaperImageDetailFragment() {

    }

    /**
     * Method to manage the instantiation of this fragment.
     *
     * @return a new Instance of <>{@link WallpaperImageDetailFragment}</>
     */
    public static WallpaperImageDetailFragment newInstance() {
        return new WallpaperImageDetailFragment();
    }

    /**
     * Lifecycle method to instantiate the view.
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //Inflate the layout XML.
        View rootView = inflater.inflate(R.layout.fragment_wallpaper_detail, container, false);

        //Inject the dependecies
        GraffpaperApplication.getApp().getRepositoriesComponent().inject(this);

        //Bind the views with members variables.
        ButterKnife.bind(this, rootView);

        //initialize the presenter.
        init();

        //initialize the adapter.
        setupUserPhotoAdapter();

        //Obtain the image from the bundle.
        Bundle bundle = getArguments();
        if (bundle != null) {
            Image selectedImage = bundle.getParcelable(SELECTED_WALLPAPER);
            mPresenter.setImageData(selectedImage);
        }

        return rootView;

    }

    /**
     * Method to initialize the required components of the fragment.
     */
    private void init() {

        mPresenter = new WallpaperImageDetailPresenterImpl(
                ThreadExecutor.getInstance(),
                MainThreadImpl.getInstance(),
                this,
                mUnsplashRepository);

        //Set the listener to the FAB.
        mApplyWallpaperButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.setAsDeviceWallpaper();
            }
        });

    }

    /**
     * Method to configure the user related photos adapter.
     */
    private void setupUserPhotoAdapter() {
        mRecyclerViewUserPhotos.setLayoutManager(new GridLayoutManager(getContext(), 2));
        mRecyclerViewUserPhotos.setHasFixedSize(true);
        mUserWallpaperAdapter = new WallpaperAdapter(getContext(), mEmptyView, this);
        mRecyclerViewUserPhotos.setAdapter(mUserWallpaperAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.resume();
    }


    @Override
    public void showProgress() {
    }

    @Override
    public void hideProgress() {

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

    /**
     * Method to set the location to the Textview.
     *
     * @param userLocation Location of the user.
     */
    @Override
    public void setUserLocation(String userLocation) {

        if (userLocation != null && !userLocation.isEmpty()) {
            mProfileLocation.setText(userLocation);
        } else {
            mProfileLocation.setText(getString(R.string.location_unknown));
        }
    }

    /**
     * Method to set the profile user photo to the Imageview.
     *
     * @param userPhotoUrl URL of the profile photo.
     */
    @Override
    public void setUserPhotoThumbnail(String userPhotoUrl) {
        //Load the image using Glide.
        Glide.with(getContext()).
                load(userPhotoUrl).
                asBitmap().
                centerCrop().
                into(new BitmapImageViewTarget(mProfileImage) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        //Customize the drawable to make a round ImageView
                        RoundedBitmapDrawable circularBitmapDrawable =
                                RoundedBitmapDrawableFactory.create(getContext().getResources(), resource);
                        circularBitmapDrawable.setCircular(true);
                        mProfileImage.setImageDrawable(circularBitmapDrawable);
                    }
                });
    }

    /**
     * Methot to load the Wallpaper Image selected by the user on the Header.
     *
     * @param imageUrl URL of the selected Wallpaper.
     */
    @Override
    public void setWallpaperImage(String imageUrl) {

        Glide.with(this).
                load(imageUrl).
                centerCrop().
                into(mWallpaperImage);
    }

    /**
     * Method to asign the previous user photos on the adapter.
     *
     * @param images
     */
    @Override
    public void setWallpaperRetrieved(List<Image> images) {
        if (images != null && !images.isEmpty()) {
            mUserWallpaperAdapter.setmImages(images);
            mUserWallpaperAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void setImageListener(final Image image) {
        mWallpaperImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToFullScreenPreview(image);
            }
        });
    }

    /**
     * Method to manage the image selection of the user
     *
     * @param image instance of selected <>{@link Image}</>
     * @param vh    ViewHolder.
     */
    @Override
    public void onWallpaperImageSelected(Image image, WallpaperAdapter.ViewHolder vh) {

        navigateToFullScreenPreview(image);
    }

    /**
     * Method that manage the instantiation  and navigation of the Fullscreen Fragment.
     *
     * @param image
     */
    private void navigateToFullScreenPreview(Image image) {
        Bundle bundleFullScreen = new Bundle();
        bundleFullScreen.putParcelable(FULLSCREEN_PREVIEW, image);
        Fragment fragment = FullScreenWallpaperViewerFragment.getNewInstance(bundleFullScreen);
        getFragmentManager().
                beginTransaction().
                add(R.id.wallpaper_detail_container,
                        fragment).
                addToBackStack("fullScreen").
                commit();
    }
}
