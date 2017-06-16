package com.software.jgodort.graffpaper.presentation.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.software.jgodort.graffpaper.R;
import com.software.jgodort.graffpaper.domain.executor.impl.ThreadExecutor;
import com.software.jgodort.graffpaper.network.model.Image;
import com.software.jgodort.graffpaper.presentation.presenters.FullScreenWallpaperViewerPresenter;
import com.software.jgodort.graffpaper.presentation.presenters.impl.FullScreenWallpaperViewerPresenterImpl;
import com.software.jgodort.graffpaper.threading.MainThreadImpl;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Fragment to display the selected image on Full Screen.
 */
public class FullScreenWallpaperViewerFragment extends Fragment implements FullScreenWallpaperViewerPresenter.View {

    /**
     * ImageView to display the image selected by the user.
     */
    @BindView(R.id.full_wallpaper_image)
    ImageView mFullScreenImageView;

    /**
     * Snackbar to display the close option on the screen.
     * The Snackbar has a indeterminate time and the option
     * to close this view and returning back to the user detail fragment.
     */
    Snackbar snackbar;

    /**
     * Instance of the presenter.
     */
    private FullScreenWallpaperViewerPresenter mPresenter;

    /**
     * Instance of the <>{@link Image}</> selected by the user.
     */
    private Image mFullScreenImage;

    /**
     * Default constructor
     */
    public FullScreenWallpaperViewerFragment() {

    }

    /**
     * Constructor to get an instance of the Fragment with the Bundle passed by parameter.
     *
     * @param bundle Bundle with selected image.
     * @return a instance of <>{@link FullScreenWallpaperViewerFragment}</>
     */
    public static Fragment getNewInstance(Bundle bundle) {

        FullScreenWallpaperViewerFragment fragment = new FullScreenWallpaperViewerFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    /**
     * Method that inflate the view of the fragment and bind the view components with the
     * member variables.
     *
     * @param inflater           Instance of the inflater.
     * @param container          Container of the views.
     * @param savedInstanceState Bundle with the saved instance.
     * @return The View inflated.
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.full_screen_wallpaper_viewer, container, false);

        ButterKnife.bind(this, rootView);

        if (getArguments() != null) {
            mFullScreenImage = getArguments().getParcelable(WallpaperImageDetailFragment.FULLSCREEN_PREVIEW);
        }
        init();

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.resume();
    }

    /**
     * Method to initialize the required components of the Fragment.
     */
    private void init() {

        mPresenter = new FullScreenWallpaperViewerPresenterImpl(
                ThreadExecutor.getInstance(),
                MainThreadImpl.getInstance(),
                this,
                mFullScreenImage);

    }

    /**
     * Method that set the full screen image to the view component.
     *
     * @param wallpaperUrl URL of the full size image.
     */
    @Override
    public void setImageFullScreen(String wallpaperUrl) {

        Glide.with(this).
                load(wallpaperUrl).
                into(new GlideDrawableImageViewTarget(mFullScreenImageView) {
                    @Override
                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> animation) {
                        super.onResourceReady(resource, animation);
                        displaySnackBar();
                    }
                });
    }

    /**
     * Method that display a Indefinite Snackbar with the back action.
     */
    @Override
    public void displaySnackBar() {

        snackbar = Snackbar.make(getActivity().findViewById(android.R.id.content),
                R.string.empty_string,
                Snackbar.LENGTH_INDEFINITE).
                setAction(
                        getString(R.string.snackbar_full_screen_action),
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //Return  to the previous fragment.
                                getFragmentManager().popBackStack();
                            }
                        });

        snackbar.show();
    }


    /**
     * Lifecycle method execute on destroy fragment process.
     * <p>
     * It´s called when the user touch on Back button.
     */
    @Override
    public void onDetach() {
        super.onDetach();
        //If the Snackbar is not null,we need to dismiss the snackbar.
        if (snackbar != null) {
            snackbar.dismiss();
        }
    }
}
